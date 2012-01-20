/*
 * $$Id: JGossipLog.java,v 1.3 2005/06/07 12:32:33 bel70 Exp $$
 *
 * ***** BEGIN LICENSE BLOCK *****
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License 
 * at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and 
 * limitations under the License.
 *
 * The Original Code is JGossip forum code.
 *
 * The Initial Developer of the Original Code is the JResearch, Org. 
 * Portions created by the Initial Developer are Copyright (C) 2004 
 * the Initial Developer. All Rights Reserved. 
 * 
 * Contributor(s): 
 *              Dmitry Belov <bel@jresearch.org>
 *        
 * ***** END LICENSE BLOCK ***** */
/*
 * Created on 07.09.2004
 *
 */
package org.jresearch.gossip.log.avalon;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log.ContextMap;
import org.apache.log.Hierarchy;
import org.apache.log.LogTarget;
import org.apache.log.Logger;
import org.apache.log.Priority;
import org.apache.log.format.PatternFormatter;
import org.apache.log.output.db.DefaultJDBCTarget;
import org.apache.log.output.io.WriterTarget;
import org.apache.log.output.io.rotate.RotateStrategyByDate;
import org.apache.log.output.io.rotate.RotatingFileTarget;
import org.apache.log.output.io.rotate.UniqueFileStrategy;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.LogLevel;

/**
 * @author dbelov
 * 
 */
public class JGossipLog {

	public static final Priority[] PRIORITIES = new Priority[] { Priority.INFO,
			Priority.WARN, Priority.ERROR, Priority.FATAL_ERROR, Priority.DEBUG };

	private Logger appLogger;

	private Logger auditLogger;

	private static SimpleDateFormat dateFormat;

	private static JGossipLog instance;

	private static Object lock = new Object();

	public synchronized static JGossipLog getInstance() throws SystemException {

		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new JGossipLog();
				}
			}
		}
		return instance;
	}

	public JGossipLog() throws SystemException {
		try {
			Properties prop = new Properties();
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"org/jresearch/gossip/resources/log.properties"));
			boolean writeToFile = "c".equalsIgnoreCase(prop
					.getProperty("logs.type"));
			// *************************** create and configure app logger

			appLogger = Hierarchy.getDefaultHierarchy().getLoggerFor(
					IConst.LOG.APPLICATION_LOG_NAME);
			String logLevel = prop.getProperty("logs.level");
			if (null == logLevel)
				logLevel = "DEBUG";

			Priority priority = Priority.getPriorityForName(logLevel);
			appLogger.setPriority(priority);

			final PatternFormatter appformatter = new PatternFormatter(prop
					.getProperty("logs.pattern.application"));

			LogTarget[] appLoggerTargets = new LogTarget[writeToFile ? 2 : 1];

			appLoggerTargets[0] = new WriterTarget(new PrintWriter(System.out),
					appformatter);
			if (writeToFile) {
				// open file target in append mode
				final File appfile = new File(prop.getProperty("logs.dir")
						+ File.separator + IConst.LOG.APPLICATION_LOG_NAME);

				appLoggerTargets[1] = new RotatingFileTarget(true,
						appformatter, new RotateStrategyByDate(),
						new UniqueFileStrategy(appfile, ".yyyy-MM-dd", ".log"));
			}
			// Set log targets of logger
			appLogger.setLogTargets(appLoggerTargets);

			// *************************** create and configure audit logger

			auditLogger = Hierarchy.getDefaultHierarchy().getLoggerFor(
					IConst.LOG.AUDIT_LOG_NAME);
			auditLogger.setPriority(Priority.INFO);
			final PatternFormatter auditformatter = new PatternFormatter(prop
					.getProperty("logs.pattern.audit"));

			LogTarget[] auditLoggerTargets = new LogTarget[writeToFile ? 2 : 1];
			// create JDBC target
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("jgossip_db");

			auditLoggerTargets[0] = new DefaultJDBCTarget(ds,
					IConst.LOG.LOG_TABLE, IConst.LOG.LOG_COLUMNS);
			if (writeToFile) {
				// open file target in append mode

				final File auditfile = new File(prop.getProperty("logs.dir")
						+ File.separator + IConst.LOG.AUDIT_LOG_NAME);

				auditLoggerTargets[1] = new RotatingFileTarget(
						true,
						auditformatter,
						new RotateStrategyByDate(),
						new UniqueFileStrategy(auditfile, ".yyyy-MM-dd", ".log"));
			}

			// Set log targets of logger
			auditLogger.setLogTargets(auditLoggerTargets);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * @return Returns the appLogger.
	 */
	public Logger getAppLogger() {
		return appLogger;
	}

	/**
	 * @return Returns the auditLogger.
	 */
	public Logger getAuditLogger() {
		return auditLogger;
	}

	/**
	 * @param level
	 * @param remoteIp
	 * @param message
	 */
	public static void audit(LogLevel level, String remoteIp, String message) {
		bindLogContext(" ", remoteIp, " ");
		audit(level, message);
	}

	/**
	 * @param level
	 * @param message
	 * @param t
	 */
	public static void audit(LogLevel level, String message, Throwable t) {
		bindLogContext(" ", " ", " ");
		try {
			Logger logger = JGossipLog.getInstance().auditLogger;
			switch (level.getLevel()) {
			case LogLevel.ERROR_INT:
				logger.error(message, t);
				break;
			case LogLevel.WARN_INT:
				logger.warn(message, t);
				break;
			case LogLevel.INFO_INT:
				logger.info(message, t);
				break;
			default:
				logger.debug(message, t);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param level
	 * @param user
	 * @param message
	 * @param session
	 */
	public static void audit(LogLevel level, User user, String message,
			HttpSession session) {

		bindLogContext((user.getName() == null) ? "<anonimous>" : user
				.getName(), (user.getIp() == null) ? " " : user.getIp(),
				session.getId());
		audit(level, message);
	}

	private static final void audit(LogLevel level, String message) {
		System.out.println(message);
		try {
			Logger logger = JGossipLog.getInstance().auditLogger;
			switch (level.getLevel()) {
			case LogLevel.ERROR_INT:
				logger.error(message);
				break;
			case LogLevel.WARN_INT:
				logger.warn(message);
				break;
			case LogLevel.INFO_INT:
				logger.info(message);
				break;
			default:
				logger.debug(message);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	private static SimpleDateFormat getDateFormat() {
		if (dateFormat == null) {
			synchronized (lock) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(
							IConst.VALUES.ISO_DATE_FORMAT);
					dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
				}
			}
		}
		return dateFormat;
	}

	private static void bindLogContext(String userName, String remoteIP,
			String sessionId) {
		final ContextMap context = new ContextMap();
		context.set("UserName", userName);
		context.set("RemoteIP", remoteIP);
		context.set("SessionId", sessionId);
		context.set("time", getDateFormat().format(new Date()));
		context.makeReadOnly();

		// bind new ContextMap to current thread and subthreads
		ContextMap.bind(context);
	}
}
/*
 * $$Id: JGossipStartUp.java,v 1.4 2005/06/07 12:32:36 bel70 Exp $$
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
 * Created on 19.05.2004
 * 
 */
package org.jresearch.gossip.plugin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.log.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.am.StrutsConfigurationHelperAction;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;
import org.jresearch.gossip.mail.MailProcessor;
import org.jresearch.gossip.mail.MailQueue;
import org.jresearch.gossip.scheduler.Scheduler;
import org.jresearch.gossip.tags.userstatus.Ranks;
import org.jresearch.gossip.util.MessageProcessor;
import org.quartz.SchedulerException;

/**
 * @author Bel
 * 
 */
public class JGossipStartUp implements PlugIn, IConst {

	private Scheduler scheduler = null;

	/**
	 * 
	 * 
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			try {
				JGossipLog.getInstance().getAppLogger().error(
						"Failed to shutdown a cheduler instance", e);
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
	 *      org.apache.struts.config.ModuleConfig)
	 */
	public void init(ActionServlet servlet, ModuleConfig moduleconfig)
			throws ServletException {
		Logger log = null;
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) {
			throw new ServletException(e);
		}
		if (log.isInfoEnabled()) {
			log.info("init is started");
		}
		ServletContext app = servlet.getServletContext();
		// start check for avaible translations for application messages
		Locale[] list = Locale.getAvailableLocales();
		ArrayList avaibleTranslations = new ArrayList();
		for (int i = 0; i < list.length; i++) {
			ResourceBundle bundle = ResourceBundle.getBundle(
					"org/jresearch/gossip/resources/lang/lang", list[i]);
			String curr = bundle.getLocale().toString();
			if (i == 0 || !avaibleTranslations.contains(curr)) {
				avaibleTranslations.add(curr);
			}
		}
		if (log.isDebugEnabled()) {
			log
					.debug("avaible translations :"
							+ avaibleTranslations.toString());
		}
		app.setAttribute(IConst.CONTEXT.AVAIBLE_TRANSLATIONS,
				avaibleTranslations);
		// end

		app.setAttribute(IConst.CONTEXT.LAST_UPDATE_DATE, new Date());
		app.setAttribute(IConst.CONTEXT.MAIL_QUEUE, new MailQueue(
				new MailProcessor()));
		DataSource ds;
		// Setting Up Datasource
		try {
			ds = setupDataSource();
			ForumDAO dao = ForumDAO.getInstance();
			dao.setDataSource(ds);
			UserDAO udao = UserDAO.getInstance();
			udao.setDataSource(ds);
		} catch (IOException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		// Setting up MessageProcessor
		MessageProcessor.setEmoticonsMap(ResourceBundle
				.getBundle("org/jresearch/gossip/resources/emoticon"));
		// Load configuration parameters
		Configurator conf = Configurator.getInstance();
		conf.setDataSource(ds);
		try {
			conf.load(app);
		} catch (SQLException e) {
			if (log.isErrorEnabled()) {
				log.error("jGossip Configurator is not loaded");
			}
			throw new ServletException(e);
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("jGossip Configurator is not loaded");
			}
			throw new ServletException(e);
		} catch (SystemException e) {
			throw new ServletException(e);
		}
		// Get the available struts configuration action mappings
		try {
			StrutsConfigurationHelperAction.retrieveStrutsActionMapping(app);
		} catch (SystemException e) {
			throw new ServletException(e);
		}
		try {
			// Load Post counted Ranks
			Ranks.getInstance().load();
		} catch (SystemException e) {
			throw new ServletException(e);
		}
		try {
			// Instantiate a scheduler here and load Jobs settings
			scheduler = initScheduler();
		} catch (java.security.AccessControlException e) {
			// TODO
		} catch (IOException e) {
			throw new ServletException(e);
		} catch (SchedulerException e) {
			throw new ServletException(e);
		}
	}

	// Create DataSource and store it in application context
	private DataSource setupDataSource() throws IOException, Exception {
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup("jgossip_db");
		return ds;
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	private Scheduler initScheduler() throws SchedulerException, IOException {
		Scheduler sched = new Scheduler();
		sched.start();
		return sched;
	}
}
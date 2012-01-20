/*
 * $$Id: Configurator.java,v 1.3 2005/06/07 12:32:29 bel70 Exp $$
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
 * Created on Oct 29, 2003
 *
 */
package org.jresearch.gossip.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.log.Logger;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.dao.drivers.DbDriver;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class Configurator implements IConst {

	private static Configurator ourInstance;

	private static Object lock = new Object();

	private Properties props;

	private DataSource dataSource;

	private DbDriver dbDriver;

	private Configurator() {
		this.props = new Properties();
		this.dbDriver = DbDriver.getInstance();

	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * @throws SystemException
	 */
	public static Configurator getInstance() {
		if (ourInstance == null) {
			synchronized (lock) {
				if (ourInstance == null) {
					ourInstance = new Configurator();
				}
			}
		}

		return ourInstance;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param key
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws ConfiguratorException
	 *             DOCUMENT ME!
	 */
	public String get(String key) throws ConfiguratorException {
		if (this.props.getProperty(key) == null) {
			throw new ConfiguratorException("Parameter by key=" + key
					+ " not found");
		}

		return this.props.getProperty(key);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param key
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws ConfiguratorException
	 *             DOCUMENT ME!
	 */
	public int getInt(String key) throws ConfiguratorException {
		if (this.props.getProperty(key) == null) {
			throw new ConfiguratorException("Parameter by key=" + key
					+ " not found");
		}

		return Integer.parseInt(this.props.getProperty(key));
	}

	/**
	 * 
	 * 
	 * @param key
	 * 
	 * 
	 * @return true if by key stored String 'Y' or 'y', otherwise return false
	 * 
	 * @throws ConfiguratorException
	 * 
	 */
	public boolean getBoolean(String key) throws ConfiguratorException {
		if (this.props.getProperty(key) == null) {
			throw new ConfiguratorException("Parameter by key=" + key
					+ " not found");
		}

		return this.props.getProperty(key).equalsIgnoreCase(VALUES.TRUE);
	}

	/**
	 * @param key
	 * @return
	 * @throws ConfiguratorException
	 */
	public Locale getLocale(String key) throws ConfiguratorException {
		if (this.props.getProperty(key) == null) {
			throw new ConfiguratorException("Parameter by key=" + key
					+ " not found");
		}

		return new Locale(this.props.getProperty(key));
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param servletContext
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 * @throws IOException
	 *             DOCUMENT ME!
	 * @throws SystemException
	 */
	public void load(ServletContext servletContext) throws SQLException,
			IOException, SystemException {
		Logger log = JGossipLog.getInstance().getAppLogger();
		if (log.isDebugEnabled()) {
			log.debug("try to load app.properties");
		}

		InputStream is = servletContext
				.getResourceAsStream("/WEB-INF/classes/org/jresearch/gossip/resources/app.properties");

		try {
			this.props.load(is);
			is.close();
		} catch (IOException e1) {
			if (log.isErrorEnabled()) {
				log.error("Loading of jGossip's configuration failed "
						+ e1.getMessage());
			}

			throw e1;
		}

		if (log.isDebugEnabled()) {
			log.debug("try to load configuration from Data Base");
		}

		Connection conn = this.dataSource.getConnection();

		PreparedStatement st = conn.prepareStatement(dbDriver.getQueries()
				.getForumQueries().getSql_GET_CONSTANTS());
		Statement st2 = conn.createStatement();
		ResultSet rs = null;

		try {
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				if (log.isDebugEnabled()) {
					log.debug(rs.getString("c_name") + " is loaded");
				}

				this.props.put(rs.getString("c_name"), rs.getString("c_value"));
			}

			// refresh all sessions(drop all entries)
			st2.executeUpdate(dbDriver.getQueries().getForumQueries()
					.getSql_DELETE_ALL_ENTRIES());
		} catch (SQLException e) {
			if (log.isErrorEnabled()) {
				log.error("Loading of jGossip's configuration failed "
						+ e.getMessage());
			}

			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}

			st2.close();
			st.close();
			conn.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param servletContext
	 *            DOCUMENT ME!
	 * @throws SQLException
	 * @throws IOException
	 * @throws SystemException
	 */
	public void reload(ServletContext servletContext) throws SystemException,
			SQLException, IOException {
		this.props = new Properties();
		load(servletContext);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param source
	 */
	public void setDataSource(DataSource source) {
		dataSource = source;
	}
}
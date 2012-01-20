/*
 * $Id: ContextListener.java,v 1.6 2005/06/08 08:04:12 bel70 Exp $
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
 *              Dmitriy Belov <bel@jresearch.org>
 *              Tariq Dweik <tariqd@gmail.com>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 08.08.2004
 *
 */
package org.jresearch.gossip.listeners;

import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.jresearch.gossip.dao.file.berkeleydb.FileDbEnv;
import org.jresearch.gossip.exception.SystemException;

/**
 * @author Dmitry Belov
 * 
 */
public class ContextListener implements ServletContextListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent evt) {
		boolean useDatasource = Boolean.valueOf(
				evt.getServletContext().getInitParameter("useDatasource"))
				.booleanValue();
		String datasourceName = evt.getServletContext().getInitParameter(
				"datasourceName");

		InitialContext ic;
		try {
			ic = new InitialContext();
			if (useDatasource) {
				if (datasourceName == null)
					throw new RuntimeException(
							"Using datasource is enabled but datasourceName parameter is not specified.");

				DataSource dataSource = (DataSource) PortableRemoteObject
						.narrow(ic.lookup(datasourceName),
								javax.sql.DataSource.class);
				ic.rebind("jgossip_db", dataSource);

			} else {
				Properties dbconf = new Properties();
				dbconf
						.load(evt
								.getServletContext()
								.getResourceAsStream(
										"/WEB-INF/classes/org/jresearch/gossip/resources/db.properties"));
				// Construct BasicDataSource reference
				Reference ref = new Reference("javax.sql.DataSource",
						"org.apache.commons.dbcp.BasicDataSourceFactory", null);
				ref.add(new StringRefAddr("driverClassName", dbconf
						.getProperty("driverClassName")));
				ref.add(new StringRefAddr("url", dbconf.getProperty("url")));
				ref.add(new StringRefAddr("password", dbconf
						.getProperty("password")));
				ref.add(new StringRefAddr("username", dbconf
						.getProperty("username")));
				ref.add(new StringRefAddr("maxActive", dbconf
						.getProperty("maxActive")));
				ref.add(new StringRefAddr("maxWait", dbconf
						.getProperty("maxWait")));
				ref.add(new StringRefAddr("initialSize", dbconf
						.getProperty("initialSize")));
				ref.add(new StringRefAddr("defaultAutoCommit", dbconf
						.getProperty("defaultAutoCommit")));
				ref.add(new StringRefAddr("defaultReadOnly", dbconf
						.getProperty("defaultReadOnly")));
				ref.add(new StringRefAddr("poolPreparedStatements", dbconf
						.getProperty("poolPreparedStatements")));
				ref.add(new StringRefAddr("maxOpenPreparedStatements", dbconf
						.getProperty("maxOpenPreparedStatements")));

				ic.rebind("jgossip_db", ref);
			}

		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			FileDbEnv.close();
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

}
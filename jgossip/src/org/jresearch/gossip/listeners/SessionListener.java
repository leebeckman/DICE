/*
 * $$Id: SessionListener.java,v 1.3 2005/06/07 12:32:34 bel70 Exp $$
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
 * Created on 25.05.2003
 *
 */
package org.jresearch.gossip.listeners;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.log.LogLevel;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class SessionListener implements HttpSessionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();

		// write message in forum log
		JGossipLog.audit(LogLevel.INFO, "", "new session is started "
				+ session.getId());
		session.setAttribute(IConst.SESSION.TIME_ZONE, IConst.VALUES.GMT);
		session.setAttribute(IConst.SESSION.LAST_INTIME, new HashMap());

		ServletContext app = session.getServletContext();

		ForumDAO forumdao = ForumDAO.getInstance();
		HashMap SkinParams = new HashMap();

		try {

			forumdao.loadSkinParams(IConst.CONFIG.DEFAULT_SKIN_ID, SkinParams);
		} catch (SQLException sqle) {
			// TODO load default params
			sqle.printStackTrace();
		}

		session.setAttribute(IConst.SESSION.STYLE_SETTINGS, SkinParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent evt) {
	}
}
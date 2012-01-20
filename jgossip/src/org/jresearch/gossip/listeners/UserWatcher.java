/*
 * $$Id: UserWatcher.java,v 1.3 2005/06/07 12:32:34 bel70 Exp $$
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
 * Created on 24.05.2003
 *
 */
package org.jresearch.gossip.listeners;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.am.PermissionGuardFactory;
import org.jresearch.gossip.beans.user.Entry;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.LogLevel;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class UserWatcher implements HttpSessionAttributeListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	private Entry getCurrentEntry(HttpSessionBindingEvent evt) {
		User user = (User) evt.getSession().getAttribute(
				IConst.SESSION.USER_KEY);
		Entry entry = new Entry(user.getName(), evt.getSession().getId(), user
				.getIp());

		return entry;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param evt
	 *            DOCUMENT ME!
	 */
	public void attributeAdded(HttpSessionBindingEvent evt) {
		if (evt.getName().equals(IConst.SESSION.USER_KEY)) {
			HttpSession session = evt.getSession();
			cleanUpPermissions(session);
			ServletContext app = session.getServletContext();
			ForumDAO forumdao = ForumDAO.getInstance();
			User user = (User) evt.getSession().getAttribute(
					IConst.SESSION.USER_KEY);

			try {
				savePermissionGuard(session);
				if (user.getStatus() > 0) {
					forumdao.removeEntry(user.getName());
				}

				forumdao.addEntry(getCurrentEntry(evt));

				// write message in forum log
				JGossipLog.audit(LogLevel.INFO, user, ((MessageResources) app
						.getAttribute(Globals.MESSAGES_KEY
								+ Configurator.getInstance().get(
										IConst.CONFIG.MODULE_PREFIX)))
						.getMessage("logs.LOG1"), session);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			}

			app.log("UserWatcher: User bean is added with login="
					+ user.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent evt) {
		if (evt.getName().equals(IConst.SESSION.USER_KEY)) {
			HttpSession session = evt.getSession();
			try {
				cleanUpPermissions(session);
			} catch (IllegalStateException e) {
				/* ignore this exception */
			}

			ServletContext app = session.getServletContext();

			User user = (User) evt.getValue();

			UserDAO dao = UserDAO.getInstance();
			ForumDAO forumdao = ForumDAO.getInstance();

			try {
				forumdao.removeEntry(session.getId());

				if (user.getStatus() != UserStatus.GUEST) {
					dao.updateIntime(user.getName());
				}
				// write message in forum log
				JGossipLog.audit(LogLevel.INFO, user, ((MessageResources) app
						.getAttribute(Globals.MESSAGES_KEY
								+ Configurator.getInstance().get(
										IConst.CONFIG.MODULE_PREFIX)))
						.getMessage("logs.LOG23"), session);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} catch (ConfiguratorException e) {
				e.printStackTrace();
			}

			app.log("UserWatcher: User bean with login=" + user.getName()
					+ " is removed");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent evt) {
		if (evt.getName().equals(IConst.SESSION.USER_KEY)) {
			HttpSession session = evt.getSession();
			cleanUpPermissions(session);
			ServletContext app = session.getServletContext();

			ForumDAO forumdao = ForumDAO.getInstance();
			User user = (User) evt.getSession().getAttribute(
					IConst.SESSION.USER_KEY);

			try {

				if (user.getStatus() > 0) {
					forumdao.removeEntry(user.getName());
				}

				if (!forumdao.isEntryExist(session.getId())) {
					forumdao.addEntry(getCurrentEntry(evt));
				} else {
					forumdao.updateEntry(getCurrentEntry(evt));
				}
				savePermissionGuard(session);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			}

			app.log("UserWatcher: User bean is replaced, new login="
					+ user.getName());
		}
	}

	private void savePermissionGuard(HttpSession session)
			throws SystemException {
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		if (user.getStatus() == UserStatus.GUEST
				|| (user.getInfo().getEmail() != null)) {
			session.setAttribute(IConst.SESSION.PERMISSION_GUARD_KEY,
					PermissionGuardFactory.getInstance().createGuard(
							user.getName()));
		} else if (Configurator.getInstance().getBoolean(
				IConst.CONFIG.ENABLE_EXT_SIGN_ON)) {
			session.setAttribute(IConst.SESSION.PERMISSION_GUARD_KEY,
					PermissionGuardFactory.getInstance().createGuard(
							user.getStatus()));
		}
	}

	private void cleanUpPermissions(HttpSession session) {
		session.removeAttribute(IConst.SESSION.PERMISSION_GUARD_KEY);
	}
}
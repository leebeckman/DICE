/*
 * $$Id: SaveProfileAction.java,v 1.3 2005/06/07 12:32:29 bel70 Exp $$
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
 * Created on 06.06.2003
 *
 */
package org.jresearch.gossip.actions.user;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.JGossipException;
import org.jresearch.gossip.exception.LogicException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ProfileForm;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class SaveProfileAction extends BaseAction {

	/**
	 * DOCUMENT ME!
	 * 
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param form
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * @param response
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JGossipException {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		UserDAO dao = UserDAO.getInstance();
		ProfileForm pForm = (ProfileForm) form;
		String forward = IConst.TOKEN.PAGE;
		try {
			if (Configurator.getInstance().getBoolean(
					IConst.CONFIG.ENABLE_EXT_SIGN_ON)
					&& !dao.isUserExist(user.getName())) {
				// add new forum user
				pForm.setLogin(user.getName());
				pForm.setPassword(user.getName());
				pForm.setPassword2(user.getName());
				dao.addUser(pForm);
				user = dao.getUser(pForm.getLogin(), pForm.getPassword());
				if (user.getStatus() == UserStatus.GUEST) {
					session.setAttribute(IConst.SESSION.USER_KEY, user);
					throw new LogicException(getResources(request).getMessage(
							"errors.ERR22")
							+ user.getName());
				}
				user.setIp(request.getRemoteAddr());
				forward = IConst.TOKEN.WELCOME;
				log(request, "logs.LOG7", user.getName());
			} else {
				// update user info
				dao.updateUser(pForm, user.getName());
				user = dao.getUserEncoded(user.getName(), user.getPassword());
				user.setIp(request.getRemoteAddr());
				log(request, "logs.LOG19");

			}

			if (Configurator.getInstance().getBoolean(
					IConst.CONFIG.ENABLE_AUTO_LOGIN)) {
				// set autolog cookies if needed...
				if (user.getSettings().isAutologin()) {
					Cookie userCookie = new Cookie(IConst.COOKIE.USER_COOKIE,
							user.getName() + "*" + user.getPassword());
					userCookie.setMaxAge(IConst.COOKIE.SECONDS_PER_YEAR);
					((HttpServletResponse) response).addCookie(userCookie);
				}
			}
			session.setAttribute(IConst.SESSION.USER_KEY, user);
			setStatusMessage(request, "status.UPDATE_DETAILS");
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}
		Logger log = JGossipLog.getInstance().getAppLogger();
		if (log.isDebugEnabled()) {
			log.debug("forward is " + forward);
		}
		return mapping.findForward(forward);
	}
}
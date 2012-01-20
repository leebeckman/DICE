/*
 * $$Id: LogoutAction.java,v 1.3 2005/06/07 12:32:29 bel70 Exp $$
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
 * Created on 16.05.2003
 *
 */
package org.jresearch.gossip.actions.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class LogoutAction extends Action {

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
	 * 
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (IConst.VALUES.FALSE.equals(Configurator.getInstance().get(
				IConst.CONFIG.ENABLE_FORUM_SIGN_ON))) {
			return (mapping.findForward(IConst.TOKEN.DENIED));
		}

		HttpSession session = request.getSession();
		MessageResources messages = getResources(request);
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);

		if (IConst.VALUES.TRUE.equals(Configurator.getInstance().get(
				IConst.CONFIG.ENABLE_AUTO_LOGIN))) {
			// unset autolog cookies if needed...
			if (user.getSettings().isAutologin()) {
				Cookie userCookie = new Cookie(IConst.COOKIE.USER_COOKIE, "");
				userCookie.setMaxAge(IConst.COOKIE.SECONDS_PER_YEAR);
				((HttpServletResponse) response).addCookie(userCookie);
			}
		}
		session.invalidate();

		return (mapping.findForward(IConst.TOKEN.WELCOME));
	}
}
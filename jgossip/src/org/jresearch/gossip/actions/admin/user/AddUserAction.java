/*
 * $$Id: AddUserAction.java,v 1.3 2005/06/07 12:31:53 bel70 Exp $$
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
 * Created on 01.06.2003
 *
 */
package org.jresearch.gossip.actions.admin.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ProfileForm;
import org.jresearch.gossip.mail.MailMessage;
import org.jresearch.gossip.mail.MailQueue;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class AddUserAction extends BaseAction {

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
			throws SystemException {
		if (IConst.VALUES.FALSE.equals(Configurator.getInstance().get(
				IConst.CONFIG.ENABLE_FORUM_SIGN_ON))) {
			return (mapping.findForward(IConst.TOKEN.DENIED));
		}

		HttpSession session = request.getSession();
		UserDAO dao = UserDAO.getInstance();

		try {

			ProfileForm pf = (ProfileForm) form;
			String tempPass = dao.generatePassword();
			pf.setPassword(tempPass);
			pf.setPassword2(tempPass);

			if (dao.addUser(pf)) {
				User newuser = dao.getUser(pf.getLogin(), pf.getPassword());
				log(request, "logs.LOG29", newuser.getName());
				setStatusMessage(request, "status.USER_ADDED", newuser
						.getName());

				/*
				 * MailMessage(String messagetext, String subject, String
				 * addrfrom, String namefrom, String addrto, String nameto)
				 */
				MessageResources messages = getResources(request);
				Configurator config = Configurator.getInstance();

				/*
				 * {0} - login {1} - new password {2} - site url {3} - site name
				 */
				StringBuffer siteUrl = new StringBuffer();
				siteUrl.append(request.getServerName());
				siteUrl.append(":");
				siteUrl.append(request.getServerPort());
				siteUrl.append(request.getContextPath());
				siteUrl.append(config.get(IConst.CONFIG.MODULE_PREFIX));
				siteUrl.append("/");

				Object[] messArgs = new Object[] { newuser.getName(), tempPass,
						siteUrl.toString(), config.get(IConst.CONFIG.SITE_NAME) };

				MailQueue queue = (MailQueue) session.getServletContext()
						.getAttribute(IConst.CONTEXT.MAIL_QUEUE);
				queue.push(new MailMessage(messages.getMessage(Configurator
						.getInstance().getLocale(IConst.CONFIG.DEFAULT_LOCALE),
						"mails.NEW_ACCOUNT", messArgs), messages.getMessage(
						Configurator.getInstance().getLocale(
								IConst.CONFIG.DEFAULT_LOCALE),
						"mails.NEW_USER_SUBJ", config
								.get(IConst.CONFIG.SITE_NAME)), config
						.get(IConst.CONFIG.ADMINMAIL), messages.getMessage(
						Configurator.getInstance().getLocale(
								IConst.CONFIG.DEFAULT_LOCALE),
						"mails.FORUM_ADMIN"), newuser.getInfo().getEmail(),
						newuser.getName()));
			} else {
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.ERR6"));

				saveErrors(request, errors);

				return (mapping.getInputForward());
			}
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}

		return (mapping.findForward(IConst.TOKEN.WELCOME));
	}
}
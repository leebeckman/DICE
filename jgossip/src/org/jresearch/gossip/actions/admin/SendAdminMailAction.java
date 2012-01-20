/*
 * $Id: SendAdminMailAction.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $
 * 
 * ***** BEGIN LICENSE BLOCK ***** 
 * The contents of this file are subject to the
 * Mozilla Public License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JGossip forum code.
 * 
 * The Initial Developer of the Original Code is the JResearch, Org. Portions
 * created by the Initial Developer are Copyright (C) 2004 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Dmitriy Belov <bel@jresearch.org> . * 
 * ***** END LICENSE BLOCK*****
 * 
 */
/*
 * Created on 29.04.2004
 *  
 */
package org.jresearch.gossip.actions.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.am.ban.BanGuard;
import org.jresearch.gossip.beans.NamedValue;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.BanType;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.SendAdminMailForm;
import org.jresearch.gossip.mail.MailMessage;
import org.jresearch.gossip.mail.MailQueue;
import org.jresearch.gossip.util.HtmlCodec;
import org.jresearch.gossip.util.MessageProcessor;

/**
 * DOCUMENT ME!
 * 
 * @author Dmitry Belov
 */
public class SendAdminMailAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.BaseAction#process(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SystemException {
		MessageResources messages = getResources(request);
		HttpSession session = request.getSession();
		SendAdminMailForm samForm = (SendAdminMailForm) form;
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		UserDAO dao = UserDAO.getInstance();
		MessageProcessor mp = MessageProcessor.getInstance();
		String mess = mp.prepareMessage(HtmlCodec.encode(samForm.getText()), 0,
				messages);
		StringBuffer siteUrl = new StringBuffer();
		siteUrl.append(request.getServerName());
		siteUrl.append(":");
		siteUrl.append(request.getServerPort());
		siteUrl.append(request.getContextPath());
		siteUrl.append(Configurator.getInstance().get(
				IConst.CONFIG.MODULE_PREFIX));
		siteUrl.append("/");
		/*
		 * {0} - login {1} - message text {2} - site url {3} - site name
		 */
		Object[] messArgs = new Object[] { "", mp.nl2br(mess),
				siteUrl.toString(),
				Configurator.getInstance().get(IConst.CONFIG.SITE_NAME) };
		MailQueue queue = (MailQueue) session.getServletContext().getAttribute(
				IConst.CONTEXT.MAIL_QUEUE);
		try {

			ArrayList userMails = dao.getUserEmails();
			Iterator it = userMails.iterator();
			BanGuard guard = BanGuard.getInstance();
			while (it.hasNext()) {
				NamedValue nv = (NamedValue) it.next();

				if (!guard.checkBan(nv.getValue(), BanType.EMAIL)
						&& !user.getName().equals(nv.getName())) {
					messArgs[0] = nv.getName();
					queue.push(new MailMessage(messages.getMessage(
							"mails.ADMIN_MAIL", messArgs),
							samForm.getSubject(), Configurator.getInstance()
									.get(IConst.CONFIG.ADMINMAIL), user
									.getName(), nv.getValue(), nv.getName()));
				}
			}
			request.removeAttribute("sendAdminMailForm");
			log(request, "status.ADMIN_MAIL_SENT");
			setStatusMessage(request, "status.ADMIN_MAIL_SENT");
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
		}
		return (mapping.getInputForward());
	}
}
/*
 * $$Id: UnsubscribeAction.java,v 1.3 2005/06/07 12:32:32 bel70 Exp $$
 * 
 * ***** BEGIN LICENSE BLOCK ***** 
 * 
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
 * Contributor(s): Dmitry Belov <bel@jresearch.org>
 * 
 * ***** END LICENSE BLOCK *****
 */
package org.jresearch.gossip.actions.subscription;

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
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.SubscribeForm;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class UnsubscribeAction extends BaseAction {
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
		ForumDAO dao = ForumDAO.getInstance();
		HttpSession session = request.getSession();
		ActionMessages errors = new ActionMessages();
		MessageResources messages = getResources(request);
		SubscribeForm sForm = (SubscribeForm) form;
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		String uname = null;
		String inputForward = null;
		String email = null;
		if (user.getStatus() == 0) {
			inputForward = "unsubscribe";
			if ((sForm.getEmail() == null) || sForm.getEmail().equals("")) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.required", new Object[] { messages
								.getMessage("user.U_MAIL") }));
				saveErrors(request, errors);
				return (mapping.findForward(inputForward));
			} else {
				email = sForm.getEmail();
				uname = "<%>";
			}
		} else {
			inputForward = "subscriptions";
			uname = user.getName();
			email = user.getInfo().getEmail();
		}
		try {

			if (dao.unsubscribe(email, uname, sForm.getTid())) {
				if (user.getStatus() == 0) {
					setStatusMessage(request, "mails.OK1");
				}
			} else if (user.getStatus() == 0) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"mails.NOSUB2"));
				saveErrors(request, errors);
			}
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}
		return (mapping.findForward(inputForward));
	}
}
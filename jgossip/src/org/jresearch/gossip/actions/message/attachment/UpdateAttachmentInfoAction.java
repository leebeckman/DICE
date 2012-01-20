/*
 * $Id: UpdateAttachmentInfoAction.java,v 1.4 2005/06/07 12:32:28 bel70 Exp $
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
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 04.07.2004
 *
 */
package org.jresearch.gossip.actions.message.attachment;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.forum.Message;
import org.jresearch.gossip.beans.forum.Topic;
import org.jresearch.gossip.beans.forum.attachment.FileDataInfo;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.JGossipException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.AttachmentInfoForm;

/**
 * @author Dmitry Belov
 * 
 */
public class UpdateAttachmentInfoAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.BaseAction#process(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JGossipException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		ForumDAO dao = ForumDAO.getInstance();
		AttachmentInfoForm aiForm = (AttachmentInfoForm) form;

		try {
			boolean isUserMod = dao.checkMod(Integer.parseInt(aiForm.getFid()),
					user);
			Topic currThread = dao.getThreadInfo(Integer.parseInt(aiForm
					.getTid()));
			Message mess = dao.getMessage(aiForm.getMid());
			// check user access rights
			getServlet().log("check user access rights ");
			if (isUserMod
					|| (user.getName().equals(mess.getSender()) && (currThread
							.getLocked() == IConst.Topic.STATUS_UNLOCKED))) {
				FileDataInfo fInfo = new FileDataInfo();
				BeanUtils.copyProperties(fInfo, aiForm);
				dao.updateAttachmentInfo(fInfo);
			} else {
				return (mapping.findForward(IConst.TOKEN.DENIED));
			}

		} catch (NumberFormatException e) {
			throw new SystemException(e);
		} catch (SQLException e) {
			throw new SystemException(e);
		} catch (IllegalAccessException e) {
			throw new SystemException(e);
		} catch (InvocationTargetException e) {
			throw new SystemException(e);
		}
		StringBuffer forward = new StringBuffer();
		forward.append("/ShowMessage.do?fid=");
		forward.append(aiForm.getFid());
		forward.append("&tid=");
		forward.append(aiForm.getTid());
		forward.append("&mid=");
		forward.append(aiForm.getMid());
		return (new ActionForward(forward.toString(), true));

	}

}
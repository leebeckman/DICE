/*
 * $$Id: UpdateMessageAction.java,v 1.3 2005/06/07 12:31:54 bel70 Exp $$
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
 * Created on Sep 20, 2003
 *
 */
package org.jresearch.gossip.actions.message;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.forum.Forum;
import org.jresearch.gossip.beans.forum.Message;
import org.jresearch.gossip.beans.forum.Topic;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.MessageForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class UpdateMessageAction extends BaseAction {
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
		HttpSession session = request.getSession();
		MessageForm messageForm = (MessageForm) form;
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		ForumDAO dao = ForumDAO.getInstance();

		try {

			boolean isUserMod = dao.checkMod(Integer.parseInt(messageForm
					.getFid()), user);
			Forum currForum = dao.getForumInfo(Integer.parseInt(messageForm
					.getFid()));

			// check access rights if forum invisible
			if ((currForum.getLocked() == IConst.Forum.STATUS_INVISIBLE)
					&& (user.getStatus() < Integer.parseInt(Configurator
							.getInstance().get(IConst.CONFIG.INVADER1)))) {
				return (mapping.findForward(IConst.TOKEN.DENIED));
			}

			Topic currThread = dao.getThreadInfo(Integer.parseInt(messageForm
					.getTid()));

			// check user access rights if current topic or completely forum is
			// locked
			if (((currThread.getLocked() == IConst.Topic.STATUS_LOCKED) || (currForum
					.getLocked() == IConst.Forum.STATUS_COMPLETELY_LOCKED))
					&& (!isUserMod)) {
				return (mapping.findForward(IConst.TOKEN.DENIED));
			}

			Message mess = dao.getMessage(messageForm.getMid());
			session.removeAttribute(IConst.REQUEST.CURR_THREAD);

			if (mess == null) {
				return (new ActionForward("/ShowThread.do?fid="
						+ messageForm.getFid() + "&tid=" + messageForm.getTid()
						+ "&block=" + messageForm.getBlock(), true));
			} else {
				// check user access rights
				if (dao.checkMod(Integer.parseInt(messageForm.getFid()), user)
						|| (user.getName().equals(mess.getSender()) && (currThread
								.getLocked() == IConst.Topic.STATUS_UNLOCKED))) {
					dao.updateMessage(messageForm);

					// subscribe user to e-mail from this thread...
					if (IConst.VALUES.TRUE.equals(messageForm.getSubscribe())) {
						dao.subscribe(messageForm.getTid(), user.getInfo()
								.getEmail(), user.getName());
					}

					log(request, "logs.LOG28", " mid=" + messageForm.getMid()
							+ " tid=" + messageForm.getTid() + " fid="
							+ messageForm.getFid());
				} else {
					return (mapping.findForward(IConst.TOKEN.DENIED));
				}
			}
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}

		StringBuffer forward = new StringBuffer();
		forward.append("/ShowMessage.do?fid=");
		forward.append(messageForm.getFid());
		forward.append("&tid=");
		forward.append(messageForm.getTid());
		forward.append("&mid=");
		forward.append(messageForm.getMid());

		return (new ActionForward(forward.toString(), true));
	}
}

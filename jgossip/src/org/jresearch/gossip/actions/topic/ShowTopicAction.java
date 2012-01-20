/*
 * $$Id: ShowTopicAction.java,v 1.5 2005/06/09 07:55:18 bel70 Exp $$
 * 
 * ***** BEGIN LICENSE BLOCK ***** The contents of this file are subject to the
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
/*
 * Created on Sep 16, 2003
 *  
 */
package org.jresearch.gossip.actions.topic;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

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
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ProcessTopicForm;
import org.jresearch.gossip.list.RecordsData;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class ShowTopicAction extends BaseAction {
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
		ProcessTopicForm ptForm = (ProcessTopicForm) form;
		ForumDAO dao = ForumDAO.getInstance();
		UserDAO userdao = UserDAO.getInstance();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		int fid = Integer.parseInt(ptForm.getFid());
		int tid = Integer.parseInt(ptForm.getTid());
		try {

			Forum currForum = dao.getForumInfo(fid);
			// check access rights if forum invisible
			if ((currForum.getLocked() == IConst.Forum.STATUS_INVISIBLE)
					&& (user.getStatus() < Integer.parseInt(Configurator
							.getInstance().get(IConst.CONFIG.INVADER1)))) {
				return (mapping.findForward(IConst.TOKEN.DENIED));
			}
			request.setAttribute(IConst.REQUEST.CURR_FORUM, currForum);
			session.setAttribute(IConst.SESSION.CURR_FORUM, currForum);
			updateLastVisitTime((HashMap) session
					.getAttribute(IConst.SESSION.LAST_INTIME), ptForm.getTid(),
					dao.now());
			RecordsData recordsData = new RecordsData();
			Topic currThread = dao.getThreadInfo(tid);
			dao.fillMessagesList(user, recordsData, ptForm);
			Iterator it = recordsData.getRecords().iterator();
			boolean attachEnabled = Configurator.getInstance().getBoolean(
					IConst.CONFIG.ENABLE_FILE_UPLOAD);
			if (it.hasNext()) {
				Message mess = (Message) it.next();
				mess.setSenderInfo(userdao.getSenderInfo(mess.getSender()));
				if (attachEnabled) {
					mess.setAttachments(dao.getAttachmentsInfo(mess.getId()));
				}
			} else {
				return (new ActionForward("/ShowForum.do?fid="
						+ ptForm.getFid(), true));
			}
			while (it.hasNext()) {
				Message mess = (Message) it.next();
				mess.setSenderInfo(userdao.getSenderInfo(mess.getSender()));
				if (attachEnabled) {
					mess.setAttachments(dao.getAttachmentsInfo(mess.getId()));
				}
			}

			request.setAttribute(IConst.REQUEST.RECORDS_DATA, recordsData);
			request.setAttribute(IConst.REQUEST.CURR_THREAD, currThread);
			if (dao.checkMod(fid, user)) {
				request.setAttribute(IConst.REQUEST.MOD_FLAG,
						IConst.VALUES.TRUE);
			}
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		} catch (InstantiationException e) {
			throw new SystemException(e);
		} catch (IllegalAccessException e) {
			throw new SystemException(e);
		} catch (InvocationTargetException e) {
			throw new SystemException(e);
		} catch (NoSuchMethodException e) {
			throw new SystemException(e);
		}
		return (mapping.findForward("showThread"));
	}

	// set(update) last visit date for topic
	private void updateLastVisitTime(HashMap last_intime, String tid, Date now) {
		if (last_intime.containsKey(tid)) {
			last_intime.remove(tid);
		}
		last_intime.put(tid, now);
	}
}
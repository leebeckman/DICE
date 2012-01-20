/*
 * $Id: MarkReadAction.java,v 1.3 2005/06/07 12:32:33 bel70 Exp $
 *
 * ***** BEGIN LICENSE BLOCK *****
 * 
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
 * Created on 01.05.2004
 *
 */
package org.jresearch.gossip.actions.forum;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ProcessForumForm;

/**
 * DOCUMENT ME!
 * 
 * @author Dmitry Belov
 */
public class MarkReadAction extends Action {
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
		ForumDAO dao = ForumDAO.getInstance();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		List updatedTopics = null;
		ActionForward forward = null;
		String fid = ((ProcessForumForm) form).getFid();

		try {

			if (fid == null || fid.equals("")) {
				updatedTopics = dao.getUpdatedTopics(user);
				forward = mapping.findForward(IConst.TOKEN.WELCOME);
			} else {
				updatedTopics = dao.getUpdatedTopics(user, Integer
						.parseInt(fid));
				forward = new ActionForward("/ShowForum.do?fid=" + fid, true);
			}

			Iterator it = updatedTopics.iterator();

			HashMap lastIntimeMap = (HashMap) session
					.getAttribute(IConst.SESSION.LAST_INTIME);
			Date now = dao.now();

			while (it.hasNext()) {
				updateLastVisitTime(lastIntimeMap, (String) it.next(), now);
			}
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}

		return (forward);
	}

	// set(update) last visit date for topic
	private void updateLastVisitTime(HashMap last_intime, String tid, Date now) {
		if (last_intime.containsKey(tid)) {
			last_intime.remove(tid);
		}

		last_intime.put(tid, now);
	}
}

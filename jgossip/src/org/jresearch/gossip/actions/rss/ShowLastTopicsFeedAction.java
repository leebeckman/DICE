/*
 * $$Id: ShowLastTopicsFeedAction.java,v 1.3 2005/06/07 12:32:32 bel70 Exp $$
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
 * Created on Jul 13, 2003
 *
 */
package org.jresearch.gossip.actions.rss;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.forum.Forum;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ProcessForumForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class ShowLastTopicsFeedAction extends Action {

	public static StringBuffer siteUrl;

	private User rssReader = new User();

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
		HttpSession session = request.getSession();
		String forward = "rss_main_page";

		if (siteUrl == null) {
			siteUrl = new StringBuffer();
			siteUrl.append(request.getServerName());
			siteUrl.append(":");
			siteUrl.append(request.getServerPort());
			siteUrl.append(request.getContextPath());
			siteUrl.append(Configurator.getInstance().get(
					IConst.CONFIG.MODULE_PREFIX));
			siteUrl.append("/");
		}

		request.setAttribute(IConst.REQUEST.SITE_URL, siteUrl.toString());

		ForumDAO dao = ForumDAO.getInstance();

		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		cl.add(Calendar.DATE, -1
				* Integer.parseInt(Configurator.getInstance().get(
						IConst.CONFIG.RSS_PERIOD)));
		rssReader.setIntime(cl.getTime());
		getServlet().log(
				"ShowLastThreadsAction: try to show LastThreads RSS Feed");

		String fid = ((ProcessForumForm) form).getFid();

		try {

			List recordsData = null;

			// TODO cache
			if (fid == null || fid.equals("")) {
				recordsData = dao.getLastTopics(Integer.parseInt(Configurator
						.getInstance().get(IConst.CONFIG.RSS_MAX_ITEM_COUNT)),
						cl.getTime());
			} else {
				int id = Integer.parseInt(fid);
				recordsData = dao.getLastTopics(id, Integer
						.parseInt(Configurator.getInstance().get(
								IConst.CONFIG.RSS_MAX_ITEM_COUNT)), cl
						.getTime());

				Forum currForum = dao.getForumInfo(id);
				currForum.setForumid(id);
				request.setAttribute(IConst.REQUEST.CURR_FORUM, currForum);
				forward = "rss_forum_page";
			}

			// TODO - set pubDate
			request.setAttribute(IConst.REQUEST.RSS_PUB_DATE, new Date());
			getServlet().log(
					"ShowLastThreadsAction: " + recordsData.size()
							+ " topic(s) since " + cl.getTime()
							+ " has been found, fid=" + fid);
			request.setAttribute(IConst.REQUEST.RECORDS_DATA, recordsData);
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}

		return (mapping.findForward(forward));
	}
}

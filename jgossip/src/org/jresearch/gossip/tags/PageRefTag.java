/*
 * $$Id$$
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
 * Created on Nov 9, 2003
 *
 */
package org.jresearch.gossip.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.forum.Topic;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class PageRefTag extends TagSupport {

	/**
	 * Logger instance.
	 */
	private Logger log;

	// name of topic bean from page scope
	private String threadBean;

	// name of forumid bean from page scope
	private String forumId;

	/**
	 * Default c'tor.
	 */
	public PageRefTag() {
		super();
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

	private void eval() throws JspException {
		forumId = ExpressionEvaluatorManager.evaluate("forumId", forumId,
				Integer.class, this, pageContext).toString();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws JspException
	 *             DOCUMENT ME!
	 */
	public int doStartTag() throws JspException {
		eval();

		try {
			HttpSession session = pageContext.getSession();
			HttpServletResponse response = (HttpServletResponse) pageContext
					.getResponse();
			User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
			Topic t = (Topic) pageContext.getAttribute(threadBean);
			int total = (int) t.getMessagesCount();

			if (total > user.getSettings().getMes_per_page()) {
				StringBuffer links = new StringBuffer("(");

				int rep = (int) Math.floor(total
						/ user.getSettings().getMes_per_page()) + 1;
				int i = 1;

				while ((i <= rep)
						&& (((i - 1) * user.getSettings().getMes_per_page()) < total)) {
					StringBuffer href = new StringBuffer();
					links.append("<a href=\"");
					href.append("ShowThread.do?fid=");
					href.append(forumId);
					href.append("&tid=");
					href.append(t.getThreadid());
					href.append("&block=");
					href.append((i - 1) * user.getSettings().getMes_per_page());
					links.append(response.encodeURL(href.toString()));
					links.append("\" class=\"thread_name\" >");
					links.append(i);
					links.append("</a>");

					if ((++i) <= rep) {
						links.append(", ");
					}
				}

				links.append(")");

				JspWriter out = pageContext.getOut();
				out.print(links.toString());
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("PageRefTag::", e);
			}
			throw new JspException("error in PageRefTag tag:", e);
		}

		return (SKIP_BODY);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getForumId() {
		return forumId;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getThreadBean() {
		return threadBean;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setForumId(String string) {
		forumId = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setThreadBean(String string) {
		threadBean = string;
	}
}

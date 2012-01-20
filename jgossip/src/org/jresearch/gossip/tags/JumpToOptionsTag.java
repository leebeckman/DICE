/*
 * Created on 09.05.2003
 *
 */
package org.jresearch.gossip.tags;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log.Logger;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.forum.Forum;
import org.jresearch.gossip.beans.forum.Group;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;
import org.jresearch.gossip.util.HtmlCodec;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class JumpToOptionsTag extends TagSupport {

	/**
	 * Logger instance.
	 */
	private Logger log;

	private boolean addUrl = true;

	/**
	 * Default c'tor.
	 */
	public JumpToOptionsTag() {
		super();
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
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
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest req = (HttpServletRequest) pageContext
					.getRequest();
			HttpServletResponse response = (HttpServletResponse) pageContext
					.getResponse();
			HttpSession session = req.getSession();
			ArrayList groups = (ArrayList) session
					.getAttribute(IConst.SESSION.GROUPS_KEY);
			Iterator it = groups.iterator();
			int fid = -1;

			if (req.getParameter("fid") != null) {
				try {
					fid = Integer.parseInt(req.getParameter("fid"));
				} catch (NumberFormatException ex) {
				}
			}

			while (it.hasNext()) {
				out.println("<option value=\"\">"
						+ IConst.JSP.OPTIONS_SEPERATOR + "</option>");

				ArrayList forums = ((Group) it.next()).getForums();
				Iterator itr = forums.iterator();

				while (itr.hasNext()) {
					Forum forum = (Forum) itr.next();
					StringBuffer option = new StringBuffer("<option value=\"");

					if (this.addUrl) {
						StringBuffer href = new StringBuffer(
								"ShowForum.do?fid=");
						href.append(forum.getForumid());
						option.append(response.encodeURL(href.toString()));
					} else {
						option.append(forum.getForumid());
					}

					option.append("\" title=\"");
					option.append(HtmlCodec.encode(forum.getTitle()));
					option.append("\" ");
					option
							.append((forum.getForumid() == fid) ? "selected"
									: "");
					option.append(" >");
					option.append(HtmlCodec.encode(forum.getTitle()));
					option.append("</option>");
					out.println(option.toString());
				}
			}
		} catch (Exception ex) {
			if (log.isErrorEnabled()) {
				log.error("JumpToOptionsTag::", ex);
			}
			throw new JspException("error in JumpToOptionsTag tag:", ex);
		}

		return (SKIP_BODY);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws JspException
	 *             DOCUMENT ME!
	 */
	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param b
	 */
	public void setAddUrl(boolean b) {
		addUrl = b;
	}
}

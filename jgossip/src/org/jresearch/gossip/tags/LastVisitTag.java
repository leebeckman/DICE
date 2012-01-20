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
 * Created on 11.06.2003
 *
 */
package org.jresearch.gossip.tags;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class LastVisitTag extends TagSupport {

	/**
	 * Logger instance.
	 */
	private Logger log;

	private String intime;

	private Date inTime;

	private String tid;

	private String sender;

	/**
	 * Default c'tor.
	 */
	public LastVisitTag() {
		super();
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @throws JspException
	 *             DOCUMENT ME!
	 */
	private void eval() throws JspException {
		inTime = (Date) ExpressionEvaluatorManager.evaluate("intime", intime,
				Date.class, this, pageContext);

		sender = (String) ExpressionEvaluatorManager.evaluate("sender", sender,
				String.class, this, pageContext);

		tid = ExpressionEvaluatorManager.evaluate("tid", tid, Integer.class,
				this, pageContext).toString();
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
			User user = (User) pageContext.getSession().getAttribute(
					IConst.SESSION.USER_KEY);

			Date lastvisit = null;
			HashMap visited = (HashMap) pageContext.getSession().getAttribute(
					IConst.SESSION.LAST_INTIME);

			if ((!visited.isEmpty()) && visited.containsKey(tid)) {
				lastvisit = (Date) visited.get(tid);
			} else {
				lastvisit = user.getIntime();
			}

			String username = (user.getName() != null) ? user.getName() : " ";

			if (lastvisit.before(getIntime()) && (!username.equals(sender))) {
				pageContext.setAttribute(IConst.PAGE.HAVE_AN_UPDATED_TOPICS,
						IConst.VALUES.TRUE);

				return super.EVAL_BODY_INCLUDE;
			}
		} catch (Exception ex) {
			if (log.isErrorEnabled()) {
				log.error("LastVisitTag::", ex);
			}
			throw new JspException("error in LastVisitTag tag:", ex);
		}

		return (SKIP_BODY);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	private Date getIntime() {
		return inTime;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setIntime(String string) {
		intime = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setSender(String string) {
		sender = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setTid(String string) {
		tid = string;
	}
}

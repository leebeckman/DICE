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
 * Created on Sep 19, 2003
 *
 */
package org.jresearch.gossip.tags.userstatus;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.RankInfoDTO;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.exception.ConfiguratorException;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class UserStatusTag extends TagSupport {

	private String status;

	private String count = "-1";

	private int countInt = -1;

	private int sts;

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the count.
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count
	 *            The count to set.
	 */
	public void setCount(String count) {
		this.count = count;
	}

	private void eval() throws JspException {
		status = ExpressionEvaluatorManager.evaluate("status", status,
				String.class, this, pageContext).toString();
		if (count == null) {
			count = "-1";
		}
		count = ExpressionEvaluatorManager.evaluate("count", count,
				String.class, this, pageContext).toString();
		try {
			this.countInt = Integer.parseInt(this.count);
		} catch (NumberFormatException e) {
			this.countInt = -1;
		}
		try {
			sts = Integer.parseInt(this.status);
		} catch (NumberFormatException e) {
			this.countInt = UserStatus.GUEST;
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
			eval();
			String statusString = new String();

			if (sts == UserStatus.USER
					&& !Ranks.getInstance().getRanks().isEmpty()
					&& countInt >= 0) {

				int delta = Integer.MAX_VALUE;
				Iterator it = Ranks.getInstance().getRanks().iterator();
				while (it.hasNext()) {
					RankInfoDTO rank = (RankInfoDTO) it.next();
					int d = rank.getCount() - countInt;
					if (d >= 0 && d < delta) {
						statusString = rank.getName();
					}
					delta = d;
				}
			} else {
				MessageResources messages = (MessageResources) pageContext
						.getServletContext().getAttribute(
								Globals.MESSAGES_KEY
										+ Configurator.getInstance().get(
												IConst.CONFIG.MODULE_PREFIX));

				String key = IConst.CONTEXT.STATUS_KEY_PREFIX + this.status;
				messages.setReturnNull(true);

				statusString = messages
						.getMessage((Locale) ((HttpServletRequest) pageContext
								.getRequest()).getSession().getAttribute(
								org.apache.struts.Globals.LOCALE_KEY), key);

				if (statusString == null) {
					statusString = messages
							.getMessage(IConst.CONTEXT.STATUS_KEY_PREFIX + "X");
				}
				messages.setReturnNull(false);
			}
			pageContext.getOut().print(statusString);

		} catch (IOException ex) {
			throw new JspException("IOException in UserStatusTag tag:", ex);
		} catch (ConfiguratorException ex) {
			throw new JspException(
					"ConfiguratorException in UserStatusTag tag:", ex);
		}

		return (SKIP_BODY);
	}
}
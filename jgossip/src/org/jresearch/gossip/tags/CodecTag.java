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
 * Created on Dec 9, 2003
 *
 */
package org.jresearch.gossip.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;
import org.jresearch.gossip.util.HtmlCodec;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class CodecTag extends BodyTagSupport {

	/**
	 * Logger instance.
	 */
	private Logger log;

	private String value = "";

	/**
	 * Default c'tor.
	 */
	public CodecTag() {
		super();
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param value
	 *            DOCUMENT ME!
	 */
	public void setValue(String value) {
		this.value = value;
	}

	private void eval() throws JspException {
		value = (String) ExpressionEvaluatorManager.evaluate("value", value,
				String.class, this, pageContext);
	}

	public int doStartTag() throws JspException {
		try {
			if (value != null) {
				eval();
				JspWriter out = pageContext.getOut();
				out.print(HtmlCodec.encode(value.trim()));
			}
		} catch (IOException ex) {
			if (log.isErrorEnabled()) {
				log.error("CodecTag::", ex);
			}
			throw new JspException("error in CodecTag tag:" + ex.getMessage());
		}
		return (EVAL_BODY_BUFFERED);
	}

	public int doAfterBody() throws JspException {
		try {
			BodyContent body = getBodyContent();
			if (body != null) {
				JspWriter out = body.getEnclosingWriter();
				String encodedBody = HtmlCodec.encode(body.getString());
				out.print(encodedBody.equalsIgnoreCase("null") ? ""
						: encodedBody);
			}

		} catch (IOException ex) {
			if (log.isErrorEnabled()) {
				log.error("CodecTag::", ex);
			}
			throw new JspException("error in CodecTag tag:" + ex.getMessage());
		}
		return (SKIP_BODY);
	}
}

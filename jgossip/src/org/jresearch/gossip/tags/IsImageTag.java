/*
 * $Id$
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
 * Created on 28.08.2004
 *
 */
package org.jresearch.gossip.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * @author Dmitry Belov
 * 
 */
public class IsImageTag extends TagSupport {

	private String var;

	private String contentType;

	/**
	 * Logger instance.
	 */
	private Logger log;

	/**
	 * Default c'tor.
	 */
	public IsImageTag() {
		super();
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

	/**
	 * @return Returns the var.
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var
	 *            The var to set.
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @return Returns the contentType.
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            The contentType to set.
	 */
	public void setContentType(String value) {
		this.contentType = value;
	}

	private void eval() throws JspException {
		contentType = (String) ExpressionEvaluatorManager.evaluate(
				"contentType", contentType, String.class, this, pageContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		eval();
		Boolean isImage = isSupportedMIMEType(contentType);
		if (this.var != null)
			pageContext.setAttribute(var, isImage);
		if (isImage.booleanValue()) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	private Boolean isSupportedMIMEType(String mime) {
		return new Boolean(IConst.JSP.JPG_CONTENT_TYPE.equals(mime)
				|| IConst.JSP.GIF_CONTENT_TYPE.equals(mime)
				|| IConst.JSP.PNG_CONTENT_TYPE.equals(mime));
	}
}
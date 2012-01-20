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
 * Created on Oct 10, 2003
 *
 */
package org.jresearch.gossip.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log.Logger;
import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;
import org.jresearch.gossip.util.HtmlCodec;
import org.jresearch.gossip.util.MessageProcessor;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class ProcessMessageTag extends BodyTagSupport {

	/**
	 * Logger instance.
	 */
	private Logger log;

	/** DOCUMENT ME! */
	private int cutToLength;

	private String value;

	private boolean skipSmiles;

	/**
	 * Default c'tor.
	 */
	public ProcessMessageTag() {
		super();
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

	private void eval() throws JspException {
		if (value != null) {
			value = (String) ExpressionEvaluatorManager.evaluate("value",
					value, String.class, this, pageContext);
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
			if (value != null) {
				eval();
				value = HtmlCodec.encode(value);

				JspWriter out = pageContext.getOut();
				out.print(process(value, skipSmiles));
			}
		} catch (IOException ex) {
			if (log.isErrorEnabled()) {
				log.error("ProcessMessageTag::", ex);
			}
			throw new JspException("error in ProcessMessageTag tag:"
					+ ex.getMessage());
		} catch (ConfiguratorException e) {
			if (log.isErrorEnabled()) {
				log.error("ProcessMessageTag::", e);
			}
			throw new JspException("error in ProcessMessageTag tag:"
					+ e.getMessage());
		}

		return (EVAL_BODY_BUFFERED);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws JspException
	 *             DOCUMENT ME!
	 */
	public int doAfterBody() throws JspException {
		try {
			String result = HtmlCodec.encode(bodyContent.getString());
			result = process(result, skipSmiles);
			bodyContent.clearBody();
			bodyContent.print(result);
			bodyContent.writeOut(getPreviousOut());
		} catch (IOException ex) {
			if (log.isErrorEnabled()) {
				log.error("ProcessMessageTag::", ex);
			}
			throw new JspException("error in ProcessMessageTag tag:"
					+ ex.getMessage());
		} catch (ConfiguratorException e) {
			if (log.isErrorEnabled()) {
				log.error("ProcessMessageTag::", e);
			}
			throw new JspException("error in ProcessMessageTag tag:"
					+ e.getMessage());
		}

		return SKIP_BODY;
	}

	private String process(String result, boolean skipSmiles)
			throws ConfiguratorException {
		MessageProcessor mp = MessageProcessor.getInstance();
		Configurator conf = Configurator.getInstance();
		result = mp
				.prepareMessage(
						result,
						this.cutToLength,
						(MessageResources) pageContext
								.getServletContext()
								.getAttribute(
										Globals.MESSAGES_KEY
												+ conf
														.get(IConst.CONFIG.MODULE_PREFIX)));

		if ((cutToLength == 0) && !skipSmiles) {
			result = mp.processEmoticons(result, (MessageResources) pageContext
					.getServletContext().getAttribute(
							Globals.MESSAGES_KEY
									+ conf.get(IConst.CONFIG.MODULE_PREFIX)));
		} else if (skipSmiles) {
			result = mp.cleanup(result);
		}

		return result;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getCutToLength() {
		return cutToLength;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setCutToLength(int i) {
		cutToLength = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public boolean isSkipSmiles() {
		return skipSmiles;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param b
	 */
	public void setSkipSmiles(boolean b) {
		skipSmiles = b;
	}
}

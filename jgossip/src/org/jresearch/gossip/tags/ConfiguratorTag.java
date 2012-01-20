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
package org.jresearch.gossip.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.util.HtmlCodec;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class ConfiguratorTag extends TagSupport {
	private static Configurator config;

	protected String key;

	private boolean quote;

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws JspException
	 *             DOCUMENT ME!
	 */
	public int doStartTag() throws JspException {
		String param = null;

		try {
			param = getConfigurator().get(key);
		} catch (ConfiguratorException e1) {
			throw new JspException(e1);
		}

		if (this.quote) {
			HtmlCodec.encode(param);
		}

		JspWriter out = pageContext.getOut();

		try {
			out.print(param);
		} catch (IOException e) {
			throw new JspException(e);
		}

		return (SKIP_BODY);
	}

	protected static Configurator getConfigurator() {
		if (config == null) {
			config = Configurator.getInstance();
		}

		return config;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setKey(String string) {
		key = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public boolean isQuote() {
		return quote;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param b
	 */
	public void setQuote(boolean b) {
		quote = b;
	}
}

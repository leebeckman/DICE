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
 * Created on Oct 6, 2003
 *
 */
package org.jresearch.gossip.tags.navbar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class NavElementTag extends TagSupport {
	private NavElement navElement;

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
			this.navElement = new NavElement();
		} catch (Exception ex) {
			throw new JspException("error in BlockOptionsTag tag:", ex);
		}

		return (super.EVAL_BODY_INCLUDE);
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
		try {
			((NavBarTag) super.findAncestorWithClass(this, NavBarTag.class))
					.addNavElement(this.navElement);
		} catch (Exception ex) {
			throw new JspException("error in BlockOptionsTag tag:", ex);
		}

		return (super.EVAL_PAGE);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setLink(String string) {
		this.navElement.setLink(string);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setTitle(String string) {
		this.navElement.setTitle(string);
	}
}

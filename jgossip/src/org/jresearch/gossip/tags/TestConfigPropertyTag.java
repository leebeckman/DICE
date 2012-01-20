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
 * Created on 03.07.2004
 *
 */
package org.jresearch.gossip.tags;

import javax.servlet.jsp.JspException;

import org.jresearch.gossip.exception.ConfiguratorException;

/**
 * @author Dmitry Belov
 * 
 */
public class TestConfigPropertyTag extends ConfiguratorTag {
	public int doStartTag() throws JspException {
		try {
			if (getConfigurator().getBoolean(key)) {
				return super.EVAL_BODY_INCLUDE;
			}
		} catch (ConfiguratorException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}

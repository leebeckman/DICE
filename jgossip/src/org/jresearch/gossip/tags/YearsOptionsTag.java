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
 * Created on 04.06.2003
 *
 */
package org.jresearch.gossip.tags;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class YearsOptionsTag extends NumericOptionsTag {
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
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			setEndValue(cal.get(Calendar.YEAR) - 100);
			setStartValue(cal.get(Calendar.YEAR));
		} catch (Exception ex) {
			throw new JspException("error in YearsOptionsTag tag:", ex);
		}

		return (super.doStartTag());
	}
}

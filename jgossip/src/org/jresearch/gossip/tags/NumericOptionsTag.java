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

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class NumericOptionsTag extends TagSupport {
	private int startValue;

	private int endValue;

	private int step = 1;

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
			ArrayList options = new ArrayList();

			if (startValue < endValue) {
				while (startValue <= endValue) {
					OptionBean option = new OptionBean();
					option.setLabelProperty(startValue + "");
					option.setProperty(startValue + "");
					options.add(option);
					startValue += step;
				}
			} else {
				while (startValue >= endValue) {
					OptionBean option = new OptionBean();
					option.setLabelProperty(startValue + "");
					option.setProperty(startValue + "");
					options.add(option);
					startValue -= step;
				}
			}

			pageContext.setAttribute("NumericOptions", options);
		} catch (Exception ex) {
			throw new JspException("error in NumericOptionsTag tag:", ex);
		}

		return (SKIP_BODY);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setEndValue(int i) {
		endValue = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setStartValue(int i) {
		startValue = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setStep(int i) {
		step = i;
	}
}

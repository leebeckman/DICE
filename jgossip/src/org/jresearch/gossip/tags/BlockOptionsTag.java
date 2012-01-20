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
package org.jresearch.gossip.tags;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.list.RecordsData;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class BlockOptionsTag extends TagSupport {

	private String id;

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
			if (pageContext.getAttribute(id) != null) {
				return (SKIP_BODY);
			}
			HttpServletResponse response = (HttpServletResponse) pageContext
					.getResponse();
			ServletRequest req = pageContext.getRequest();
			Enumeration pnames = req.getParameterNames();
			StringBuffer url = new StringBuffer((String) req
					.getAttribute(IConst.REQUEST.REQUEST_URI)
					+ "?");

			while (pnames.hasMoreElements()) {
				String name = (String) pnames.nextElement();

				if (!name.equals("block") && req.getParameter(name) != null
						&& !req.getParameter(name).equals("")) {
					url.append(name);
					url.append("=");
					url.append(req.getParameter(name));

					if (pnames.hasMoreElements()) {
						url.append("&");
					}
				}
			}

			RecordsData recordsData = (RecordsData) req
					.getAttribute(IConst.REQUEST.RECORDS_DATA);
			ArrayList options = new ArrayList();

			int i = 0;

			while (i < recordsData.getRecordsCount()) {
				int endRecord = ((i + recordsData.getBlockSize()) < recordsData
						.getRecordsCount()) ? (i + recordsData.getBlockSize())
						: recordsData.getRecordsCount();
				OptionBean ob = new OptionBean();

				ob.setProperty(response.encodeURL(url.toString() + "&block="
						+ i));
				ob.setLabelProperty((i + 1) + "-" + endRecord);
				options.add(ob);

				if (i == recordsData.getCurrBlock()) {
					pageContext.setAttribute(IConst.PAGE.SELECTED_BLOCK, ob);
				}

				i = endRecord;
			}

			pageContext.setAttribute(id, options);
		} catch (Exception ex) {
			throw new JspException("error in BlockOptionsTag tag:", ex);
		}

		return (SKIP_BODY);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}
}
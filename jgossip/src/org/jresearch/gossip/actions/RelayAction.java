/*
 * $$Id: RelayAction.java,v 1.3 2005/06/07 12:32:34 bel70 Exp $$
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
 * Created on 27.05.2004
 *
 */
package org.jresearch.gossip.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.exception.SystemException;

/**
 * @author dbelov
 * 
 */
public class RelayAction extends Action {

	public static final String DISPATCH_PARAM = "dispatch";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dispatch = request.getParameter(DISPATCH_PARAM);
		if (dispatch == null || dispatch.equals("")) {
			throw new SystemException(new NullPointerException(
					"dispatch param can't be empty"));
		}
		ActionForward forward = mapping.findForward(request
				.getParameter(DISPATCH_PARAM));
		if (forward == null) {
			throw new SystemException(new NullPointerException(" forward '"
					+ dispatch + "' not found"));
		}
		return forward;

	}

}
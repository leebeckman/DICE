/*
 * $$Id: UpdateGroupAction.java,v 1.3 2005/06/07 12:32:30 bel70 Exp $$
 * 
 * ***** BEGIN LICENSE BLOCK ***** The contents of this file are subject to the
 * Mozilla Public License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JGossip forum code.
 * 
 * The Initial Developer of the Original Code is the JResearch, Org. Portions
 * created by the Initial Developer are Copyright (C) 2004 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Dmitry Belov <bel@jresearch.org>
 * 
 * ***** END LICENSE BLOCK *****
 */
/*
 * Created on Oct 1, 2003
 *  
 */
package org.jresearch.gossip.actions.admin.group;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jresearch.gossip.actions.admin.ManageGroupForumAction;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.GroupForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class UpdateGroupAction extends ManageGroupForumAction {
	/**
	 * DOCUMENT ME!
	 * 
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param form
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * @param response
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SystemException {
		GroupForm gForm = (GroupForm) form;
		ActionMessages errors = new ActionMessages();
		if (gForm.getGid().equals("")) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.required", "group ID"));
		}
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			return (mapping.getInputForward());
		}
		ForumDAO dao = ForumDAO.getInstance();
		try {

			dao.updateGroup(gForm);
			log(request, "logs.LOG18", gForm.getGroup_name());
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}
		return (new ActionForward("/ShowGroupList.do"));
	}
}
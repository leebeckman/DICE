/*
 * $$Id: DropModAction.java,v 1.3 2005/06/07 12:31:53 bel70 Exp $$
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
 * Created on Oct 9, 2003
 *
 */
package org.jresearch.gossip.actions.admin.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ProcessModForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class DropModAction extends ManageUserAction {
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
		MessageResources messages = getResources(request);
		ForumDAO dao = ForumDAO.getInstance();
		UserDAO userdao = UserDAO.getInstance();
		Configurator config = Configurator.getInstance();
		ProcessModForm pmForm = (ProcessModForm) form;

		try {

			dao.dropMod(pmForm.getFid(), pmForm.getName());

			int newStatus = 0;

			if (dao.isUserMod(pmForm.getName())) {
				newStatus = UserStatus.MOD;
			} else {
				newStatus = UserStatus.USER;
			}

			userdao.setUserStatus(pmForm.getName(), newStatus);
			log(request, "logs.LOG10", newStatus + " name=" + pmForm.getName());

			setUpdatedLogin(pmForm.getName());
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}

		return (new ActionForward("ShowUser.do?uid=" + pmForm.getName(), true));
	}
}

/*
 * $$Id: EditConstantsAction.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $$
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
 * Created on Oct 30, 2003
 *
 */
package org.jresearch.gossip.actions.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.dao.file.berkeleydb.FileDbEnv;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.mail.MailProcessor;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class EditConstantsAction extends BaseAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.BaseAction#process(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SystemException {
		ForumDAO dao = ForumDAO.getInstance();

		try {

			dao.updateConstants(((DynaActionForm) form).getMap());
			Configurator.getInstance().reload(getServlet().getServletContext());
			if (Configurator.getInstance().getBoolean(
					IConst.CONFIG.ENABLE_FILE_UPLOAD)) {
				FileDbEnv.load();
			}
			MailProcessor._mailSession = null;
			log(request, "status.UPDATE_CONSTANTS");
			setStatusMessage(request, "status.UPDATE_CONSTANTS");
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		} catch (IOException e) {
			throw new SystemException(e);
		}

		return mapping.getInputForward();
	}
}

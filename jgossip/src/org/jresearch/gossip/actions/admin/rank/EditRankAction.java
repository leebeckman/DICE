/*
 * $Id: EditRankAction.java,v 1.3 2005/06/07 12:31:54 bel70 Exp $
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
 * Created on 31.07.2004
 *
 */
package org.jresearch.gossip.actions.admin.rank;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.RankInfoDTO;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.JGossipException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ProcessItemForm;
import org.jresearch.gossip.forms.RankForm;

/**
 * @author Dmitry Belov
 * 
 */
public class EditRankAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.BaseAction#process(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JGossipException {
		ForumDAO dao = ForumDAO.getInstance();
		ProcessItemForm piForm = (ProcessItemForm) form;
		RankForm rForm;
		if (request.getAttribute("rankForm") == null) {
			try {
				RankInfoDTO info = dao.getRankInfo(Integer.parseInt(piForm
						.getId()));
				rForm = new RankForm();
				BeanUtils.copyProperties(rForm, info);
			} catch (SQLException e) {
				throw new SystemException(e);
			} catch (IllegalAccessException e) {
				throw new SystemException(e);
			} catch (InvocationTargetException e) {
				throw new SystemException(e);
			}
			request.setAttribute("rankForm", rForm);
		}
		return mapping.getInputForward();
	}

}
/*
 * $Id: SaveRankAction.java,v 1.3 2005/06/07 12:31:54 bel70 Exp $
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
 * Created on 06.08.2004
 *
 */
package org.jresearch.gossip.actions.admin.rank;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.RankInfoDTO;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.JGossipException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.RankForm;
import org.jresearch.gossip.tags.userstatus.Ranks;

/**
 * @author dbelov
 * 
 */
public abstract class SaveRankAction extends BaseAction {

	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JGossipException {
		ForumDAO dao = ForumDAO.getInstance();
		RankForm rForm = (RankForm) form;
		try {
			int count = Integer.parseInt(rForm.getCount());
			int id = -1;
			try {
				id = Integer.parseInt(rForm.getId());
			} catch (NumberFormatException e) {
				id = -1;
			}
			List records = dao.getRankList();
			Iterator it = records.iterator();
			while (it.hasNext()) {
				RankInfoDTO curr = (RankInfoDTO) it.next();
				if (curr.getCount() == count && curr.getId() != id) {
					ActionMessages errors = new ActionMessages();
					errors
							.add(ActionMessages.GLOBAL_MESSAGE,
									new ActionMessage("errors.ERR23", rForm
											.getCount()));
					saveErrors(request, errors);
					return mapping.getInputForward();
				}
			}
			saveRank(rForm);
			// Reload Post counted Ranks
			Ranks.getInstance().load();
		} catch (SQLException e) {
			throw new SystemException(e);
		}
		return mapping.findForward(IConst.TOKEN.PAGE);
	}

	protected abstract void saveRank(RankForm rForm) throws SQLException;
}
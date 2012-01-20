/*
 * $Id: UpdateRankAction.java,v 1.3 2005/06/07 12:31:54 bel70 Exp $
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
 * Created on 30.07.2004
 *
 */
package org.jresearch.gossip.actions.admin.rank;

import java.sql.SQLException;

import org.jresearch.gossip.beans.RankInfoDTO;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.forms.RankForm;

/**
 * @author Dmitry Belov
 * 
 */
public class UpdateRankAction extends SaveRankAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.admin.rank.SaveRankAction#saveRank(org.jresearch.gossip.forms.RankForm)
	 */
	protected void saveRank(RankForm rForm) throws SQLException {
		ForumDAO dao = ForumDAO.getInstance();
		RankInfoDTO rank = new RankInfoDTO(Integer.parseInt(rForm.getId()),
				rForm.getName(), Integer.parseInt(rForm.getCount()));
		dao.updateRank(rank);

	}

}
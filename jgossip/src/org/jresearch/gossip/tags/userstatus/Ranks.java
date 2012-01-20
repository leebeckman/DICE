/*
 * $Id$
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
 * Created on Aug 6, 2004
 *
 */
package org.jresearch.gossip.tags.userstatus;

import java.sql.SQLException;
import java.util.ArrayList;

import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.SystemException;

/**
 * @author Bel
 * 
 */
public class Ranks {
	private static ArrayList ranks = new ArrayList();

	private static Ranks instance;

	private Ranks() {

	}

	/**
	 * @return
	 */
	public static synchronized Ranks getInstance() {
		if (instance == null) {
			synchronized (ranks) {
				if (instance == null) {
					instance = new Ranks();
				}
			}
		}
		return instance;
	}

	/**
	 * @throws SystemException
	 */
	public void load() throws SystemException {
		ForumDAO dao = ForumDAO.getInstance();
		try {
			ranks = (ArrayList) dao.getRankList();
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * @return Returns the ranks.
	 */
	public ArrayList getRanks() {
		return ranks;
	}
}
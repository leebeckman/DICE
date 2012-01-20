/*
 * $Id: BanGuard.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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
 * Created on 29.08.2004
 *
 */
package org.jresearch.gossip.am.ban;

import java.sql.SQLException;
import java.util.HashSet;

import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;

/**
 * @author Dmitry Belov
 * 
 */
public class BanGuard {

	private BanMap banMap = new BanMap();

	private static BanGuard instance;

	private static Object lock = new Object();

	private BanGuard() throws SystemException {
		load();
	}

	/**
	 * @throws SystemException
	 */
	public void load() throws SystemException {
		UserDAO dao = UserDAO.getInstance();
		try {
			banMap = new BanMap();
			dao.fillBanMap(banMap);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * @return
	 * @throws SystemException
	 */
	public static BanGuard getInstance() throws SystemException {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new BanGuard();
				}
			}
		}
		return instance;
	}

	public BanMap getBanMap() throws SystemException {
		try {
			return (BanMap) this.banMap.clone();
		} catch (CloneNotSupportedException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * @param name
	 * @param login
	 * @throws SystemException
	 */
	public boolean checkBan(String name, int type) throws SystemException {
		HashSet set = banMap.get(type);
		if (set == null) {
			return false;
		}
		return set.contains(name);
	}

}
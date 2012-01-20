/*
 * $Id: GenericSqlUserQueries.java,v 1.3 2005/06/07 12:32:24 bel70 Exp $
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
 *              Alexey Pavlov <alexnet@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */
package org.jresearch.gossip.dao.drivers.generic;

import org.jresearch.gossip.dao.drivers.UserQueries;

/**
 * OracleUserQueries
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ 21.03.2004
 */
class GenericSqlUserQueries extends UserQueries {

	/**
	 * @see org.jresearch.gossip.dao.drivers.UserQueries#getSql_GET_USER_INFO()
	 */
	public String getSql_GET_USER_INFO() {
		return GET_USER_INFO;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.UserQueries#getSql_GET_USERS()
	 */
	public String getSql_GET_USERS() {
		return GET_USERS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.UserQueries#getSql_SET_LAST_INTIME()
	 */
	public String getSql_SET_LAST_INTIME() {
		return SET_LAST_INTIME;
	}

	private static final String GET_USER_INFO = "SELECT user_signature, user_name, user_city, user_status, count(jrf_message.id) as tot_mes"
			+ " FROM jrf_user, jrf_message WHERE user_name =?  AND user_name = jrf_message.sender GROUP BY user_name, user_signature, user_city, user_status";

	private static final String GET_USERS = "SELECT * FROM "
			+ "	(SELECT user_name, id, user_status, "
			+ "		ROW_NUMBER() OVER(ORDER BY user_name) AS R"
			+ "	FROM jrf_user " + "	ORDER BY user_name"
			+ ") WHERE R BETWEEN ? AND ?"; // block, user_toshow

	private final static String SET_LAST_INTIME = "UPDATE jrf_user SET last_intime=sysdate WHERE user_name = ?";

}

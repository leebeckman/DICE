/*
 * $Id: MSSqlUserQueries.java,v 1.3 2005/06/07 12:32:25 bel70 Exp $
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
 *              Simone Chiaretta <simo@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */
package org.jresearch.gossip.dao.drivers.mssql;

import org.jresearch.gossip.dao.drivers.UserQueries;

/**
 * MSSqlUserQueries
 * 
 * @author <a href="simo@sourceforge.net">S. Chiaretta</a>
 * @version $version$ 21.03.2004
 */
class MSSqlUserQueries extends UserQueries {

	private static final String GET_USER_INFO = "SELECT user_name, user_city, user_signature, count(jrf_message.id) as tot_mes, user_status"
			+ " FROM jrf_user, jrf_message WHERE user_name =?  AND user_name = jrf_message.sender GROUP BY user_name, user_signature, user_city, user_status";

	private static final String GET_USERS = "EXEC sp_GET_USERS ?,?"; // block,
																		// user_toshow

	private final static String SET_LAST_INTIME = "UPDATE jrf_user SET last_intime=GETDATE() WHERE user_name = ?";

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

}

/*
 * $Id: UserQueries.java,v 1.3 2005/06/07 12:32:24 bel70 Exp $
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
package org.jresearch.gossip.dao.drivers;

/**
 * UserQueries
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ 21.03.2004
 */
public abstract class UserQueries {

	/**
	 * @return
	 */
	public String getSql_ADD_USER() {
		return ADD_USER;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_PENDING_USER() {
		return ADD_PENDING_USER;
	}

	/**
	 * @return
	 */
	public String getSql_CHANGE_PASSWORD() {
		return CHANGE_PASSWORD;
	}

	/**
	 * @return
	 */
	public String getSql_CHECK_USER() {
		return CHECK_USER;
	}

	/**
	 * @return
	 */
	public String getSql_CHECK_PENDING_USER() {
		return CHECK_PENDING_USER;
	}

	/**
	 * @return
	 */
	public String getSql_CHECK_USER_WITH_EMAIL() {
		return CHECK_USER_WITH_EMAIL;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_USERS() {
		return COUNT_USERS;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_USER() {
		return DELETE_USER;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_PENDING_USER() {
		return DELETE_PENDING_USER;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_EXPIRED_PENDING_USER() {
		return DELETE_EXPIRED_PENDING_USER;
	}

	/**
	 * @return
	 */
	public String getSql_GET_USER() {
		return GET_USER;
	}

	/**
	 * @return
	 */
	public String getSql_GET_USER_BY_ID() {
		return GET_USER_BY_ID;
	}

	/**
	 * @return
	 */
	public String getSql_GET_USER_ENCODED() {
		return GET_USER_ENCODED;
	}

	public String getSql_GET_USER_EMAILS() {
		return GET_USER_EMAILS;
	}

	/**
	 * @return
	 */
	public abstract String getSql_GET_USER_INFO();

	/**
	 * @return
	 */
	public String getSql_GET_USER_INFO_BY_ID() {
		return GET_USER_INFO_BY_ID;
	}

	/**
	 * @return
	 */
	public String getSql_GET_USER_INFO_FULL() {
		return GET_USER_INFO_FULL;
	}

	/**
	 * @return
	 */
	public String getSql_GET_USER_STATUS() {
		return GET_USER_STATUS;
	}

	/**
	 * @return
	 */
	public abstract String getSql_GET_USERS();

	/**
	 * @return
	 */
	public abstract String getSql_SET_LAST_INTIME();

	/**
	 * @return
	 */
	public String getSql_UPDATE_USER() {
		return UPDATE_USER;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_USER_STATUS() {
		return UPDATE_USER_STATUS;
	}

	/**
	 * @return
	 */
	public String getSql_GET_BAN_MAP() {
		return GET_BAN_MAP;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_BAN() {
		return DELETE_BAN;
	}

	/**
	 * @return
	 */
	public String getADD_BAN() {
		return ADD_BAN;
	}

	private static final String ADD_USER = "INSERT INTO jrf_user(user_name, user_pass, user_mail, user_hp, user_icq,"
			+ "user_dob, user_city, user_occupation, user_status, user_signature, mes_per_page,"
			+ "auto_login, show_user_mail,last_intime,id) VALUES(?,?,?,?,?,?,?,?,1,?,?,?,?,?,?)";

	private static final String CHANGE_PASSWORD = "UPDATE jrf_user SET user_pass = ? WHERE user_name = ?";

	private static final String CHECK_USER = "SELECT user_name FROM jrf_user WHERE user_name = ?";

	private static final String CHECK_USER_WITH_EMAIL = "SELECT * FROM jrf_user WHERE user_mail = ? AND user_name=?";

	private static final String COUNT_USERS = "SELECT count(id) as tot FROM jrf_user";

	private static final String DELETE_USER = "DELETE FROM jrf_user WHERE id = ? AND id != 1";

	private static final String GET_USER = "SELECT * FROM jrf_user WHERE user_name = ?  AND user_pass = ?";

	private static final String GET_USER_BY_ID = "SELECT * FROM jrf_user WHERE id = ?";

	private static final String GET_USER_EMAILS = "SELECT user_name,user_mail FROM jrf_user";

	private static final String GET_USER_ENCODED = "SELECT * FROM jrf_user WHERE user_name = ? AND user_pass = ?";

	private static final String GET_USER_INFO_FULL = "SELECT * FROM jrf_user WHERE user_name = ?";

	private static final String GET_USER_INFO_BY_ID = "SELECT user_name,user_status FROM jrf_user WHERE id = ?";

	private final static String GET_USER_STATUS = "SELECT user_status FROM jrf_user WHERE user_name =?";

	private static final String UPDATE_USER = "UPDATE jrf_user SET "
			+ "user_mail=?,user_hp=?,user_icq=?,user_dob=?,user_city=?,user_occupation=?,"
			+ "user_signature =?,mes_per_page=?,auto_login=?,show_user_mail=? WHERE user_name = ?";

	private static final String UPDATE_USER_STATUS = "UPDATE jrf_user SET user_status = ? WHERE user_name =?";

	private static final String ADD_PENDING_USER = "INSERT INTO jrf_pending_user(user_name, user_mail, confirm_code, intime) VALUES(?,?,?,?)";

	private static final String CHECK_PENDING_USER = "SELECT user_name FROM jrf_pending_user WHERE user_name = ? AND confirm_code = ?";

	private static final String DELETE_PENDING_USER = "DELETE FROM jrf_pending_user WHERE user_name = ? ";

	private static final String DELETE_EXPIRED_PENDING_USER = "DELETE FROM jrf_pending_user WHERE intime < ? ";

	private final static String GET_BAN_MAP = "SELECT ban_mask,type_id FROM jrf_ban";

	private static final String DELETE_BAN = "DELETE FROM jrf_ban WHERE type_id = ? AND ban_mask = ?";

	private static final String ADD_BAN = "INSERT INTO jrf_ban(type_id,ban_mask) VALUES(?,?)";

}

/*
 * $Id: MSSqlForumQueries.java,v 1.3 2005/06/07 12:32:25 bel70 Exp $
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

import org.jresearch.gossip.dao.drivers.ForumQueries;

/**
 * MSSqlForumQueries
 * 
 * @author <a href="simo@sourceforge.net">S. Chiaretta</a>
 * @version $version$ $Date: 2005/06/07 12:32:25 $
 */
class MSSqlForumQueries extends ForumQueries {

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_COUNT_LOG_ENTRIES()
	 */
	public String getSql_COUNT_LOG_ENTRIES() {
		return COUNT_LOG_ENTRIES;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_LAST_MESS()
	 */
	public String getSql_GET_LAST_MESS() {
		return GET_LAST_MESS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_ROOT_MESS()
	 */
	public String getSql_GET_ROOT_MESS() {
		return GET_ROOT_MESS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_LAST_TOPICS_IN_FORUM()
	 */
	public String getSql_GET_LAST_TOPICS_IN_FORUM() {
		return GET_LAST_TOPICS_IN_FORUM;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_LAST_TOPICS()
	 */
	public String getSql_GET_LAST_TOPICS() {
		return GET_LAST_TOPICS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_NEW_THREADS_ALL()
	 */
	public String getSql_GET_LAST_UPDATED_TOPICS_ALL() {
		return GET_NEW_THREADS_ALL;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_NEW_THREADS()
	 */
	public String getSql_GET_LAST_UPDATED_TOPICS() {
		return GET_NEW_THREADS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_LOG_ENTRIES_ASC()
	 */
	public String getSql_GET_LOG_ENTRIES_ASC() {
		return GET_LOG_ENTRIES_DESC;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_LOG_ENTRIES_DESC()
	 */
	public String getSql_GET_LOG_ENTRIES_DESC() {
		return GET_LOG_ENTRIES_DESC;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_OLD_TOPICS()
	 */
	public String getSql_GET_OLD_TOPICS() {
		return GET_OLD_TOPICS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_THREAD_LAST_INTIME()
	 */
	public String getSql_GET_THREAD_LAST_INTIME() {
		return GET_THREAD_LAST_INTIME;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_THREAD_LAST_MESS()
	 */
	public String getSql_GET_THREAD_LAST_MESS() {
		return GET_THREAD_LAST_MESS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_THREAD_MESSAGES()
	 */
	public String getSql_GET_THREAD_MESSAGES() {
		return GET_THREAD_MESSAGES;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_THREAD_SUBJ()
	 */
	public String getSql_GET_THREAD_SUBJ() {
		return GET_THREAD_SUBJ;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_THREADS()
	 */
	public String getSql_GET_THREADS() {
		return GET_THREADS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_USER_SUBSCRIPTIONS()
	 */
	public String getSql_GET_USER_SUBSCRIPTIONS() {
		return GET_USER_SUBSCRIPTIONS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_SEARCH_QUERY_END()
	 */
	public String getSql_SEARCH_QUERY_END() {
		return SEARCH_QUERY_END;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_SEARCH_QUERY_SUFF_COUNT()
	 */
	public String getSql_SEARCH_QUERY_SUFF_COUNT() {
		return SEARCH_QUERY_SUFF_COUNT;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_SEARCH_QUERY_SUFF()
	 */
	public String getSql_SEARCH_QUERY_SUFF() {
		return SEARCH_QUERY_SUFF;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_MESS_POS_BY_INTIME()
	 */
	public String getSql_GET_MESS_POS_BY_INTIME() {
		return GET_MESS_POS_BY_INTIME;
	}

	private static final String COUNT_LOG_ENTRIES = "SELECT count(log_date) as tot_log_entries "
			+ "	FROM jrf_audit_log "
			+ "	WHERE CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) >=  ? AND CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) <= ? AND "
			+ "		logger LIKE ? AND "
			+ "		log_level LIKE ? AND "
			+ "		remote_ip LIKE ? AND "
			+ "		session_id LIKE ? AND "
			+ "		user_name LIKE ? ";

	private static final String GET_LAST_MESS = "SELECT TOP 1 jrf_message.sender AS m_from,jrf_thread.lintime AS t_stamp,"
			+ "jrf_message.centents AS cont,jrf_thread.sortby ,jrf_message.id, jrf_thread.threadid AS tid  FROM jrf_message, jrf_thread WHERE "
			+ "jrf_thread.lintime = jrf_message.intime AND jrf_thread.threadid = jrf_message.threadid "
			+ "AND jrf_thread.forumid =? ORDER BY lintime DESC";

	private static final String GET_ROOT_MESS = "SELECT TOP 1"
			+ "	jrf_message.sender,jrf_thread.lintime AS t_stamp, jrf_message.centents,  "
			+ "	jrf_message.id, jrf_message.heading as subject, jrf_message.ip, jrf_thread.threadid AS tid , jrf_thread.sortby "
			+ "FROM jrf_thread, jrf_message " + "WHERE "
			+ "	jrf_thread.threadid = ? AND "
			+ "	jrf_message.threadid = jrf_thread.threadid "
			+ "ORDER BY jrf_thread.lintime ASC ";

	private static final String GET_LAST_TOPICS_IN_FORUM = "EXEC sp_GET_LAST_TOPICS_IN_FORUM ?,?,?";

	private static final String GET_LAST_TOPICS = "EXEC sp_GET_LAST_TOPICS ?,?";

	private static final String GET_LOG_ENTRIES_ASC = "EXEC sp_GET_LOG_ENTRIES_ASC ?,?,?,?,?,?,?,?,?";

	private static final String GET_LOG_ENTRIES_DESC = "EXEC sp_GET_LOG_ENTRIES_DESC ?,?,?,?,?,?,?,?,?";

	private static final String GET_NEW_THREADS = "EXEC sp_GET_NEW_THREADS ?,?,?";

	private static final String GET_NEW_THREADS_ALL = "EXEC sp_GET_NEW_THREADS_ALL ?,?,?";

	private static final String GET_OLD_TOPICS = "SELECT jrf_thread.threadid, count(jrf_message.id) AS cc FROM jrf_thread, jrf_message WHERE jrf_thread.threadid = jrf_message.threadid AND TO_DAYS(GETDATE()) - TO_DAYS(jrf_thread.lintime) > ? GROUP BY jrf_thread.threadid";

	private static final String GET_THREAD_LAST_INTIME = "SELECT TOP 1 intime FROM jrf_message WHERE threadid = ? ORDER BY intime DESC";

	private static final String GET_THREAD_LAST_MESS = "SELECT TOP 1 sender, intime, centents,id FROM jrf_message WHERE jrf_message.threadid =?  ORDER BY intime DESC";

	private static final String GET_THREAD_MESSAGES = "EXEC sp_GET_THREAD_MESSAGES ?,?,?";

	private static final String GET_THREAD_SUBJ = "SELECT TOP 1 heading FROM jrf_message WHERE threadid = ? ORDER BY intime";

	private static final String GET_THREADS = "EXEC sp_GET_THREADS ?,?,?";

	private static final String GET_USER_SUBSCRIPTIONS = "EXEC sp_GET_USER_SUBSCRIPTIONS ?,?,?";

	private static final String SEARCH_QUERY_END = " ORDER BY jrf_message.intime DESC";

	private static final String SEARCH_QUERY_SUFF = "SELECT TOP 50 jrf_thread.forumid, jrf_message.centents, jrf_message.sender, jrf_thread.threadid, jrf_message.intime, jrf_message.id, jrf_message.heading, jrf_forum.locked FROM jrf_message LEFT JOIN jrf_thread on jrf_message.threadid=jrf_thread.threadid LEFT JOIN jrf_forum ON jrf_thread.forumid=jrf_forum.forumid";

	private static final String SEARCH_QUERY_SUFF_COUNT = "SELECT count(jrf_message.id),1 as intime FROM jrf_message LEFT JOIN jrf_thread on jrf_message.threadid=jrf_thread.threadid LEFT JOIN jrf_forum ON jrf_thread.forumid=jrf_forum.forumid";

	private static final String GET_MESS_POS_BY_INTIME = "SELECT count(id) FROM jrf_message WHERE threadid=? AND intime<=?  GROUP BY intime ORDER BY intime";
}

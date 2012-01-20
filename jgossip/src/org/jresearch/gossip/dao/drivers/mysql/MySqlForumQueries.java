/*
 * $Id: MySqlForumQueries.java,v 1.3 2005/06/07 12:32:35 bel70 Exp $
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
package org.jresearch.gossip.dao.drivers.mysql;

import org.jresearch.gossip.dao.drivers.ForumQueries;

/**
 * MySqlForumQueries
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:35 $
 */
class MySqlForumQueries extends ForumQueries {

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
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_LOG_ENTRIES_ASC()
	 */
	public String getSql_GET_LOG_ENTRIES_ASC() {
		return GET_LOG_ENTRIES_ASC;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_LOG_ENTRIES_DESC()
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

	private static final String COUNT_LOG_ENTRIES = "SELECT count(log_date) as tot_log_entries "
			+ "	FROM jrf_audit_log "
			+ "	WHERE "
			+ "		CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) >= ? AND "
			+ "		CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) <= ? AND "
			+ "		logger LIKE ? AND "
			+ "		log_level LIKE ? AND "
			+ "		remote_ip LIKE ? AND "
			+ "		session_id LIKE ? AND "
			+ "		user_name LIKE ? " + "	ORDER BY log_date ";

	private static final String GET_LAST_MESS = "SELECT jrf_message.centents AS cont, jrf_message.sender AS m_from,jrf_message.id,"
			+ "jrf_thread.lintime AS t_stamp, jrf_thread.threadid AS tid ,jrf_thread.sortby FROM jrf_message, jrf_thread WHERE "
			+ "jrf_thread.lintime = jrf_message.intime AND jrf_thread.threadid = jrf_message.threadid "
			+ "AND jrf_thread.forumid =? ORDER BY lintime DESC LIMIT 1";

	private static final String GET_ROOT_MESS = "SELECT "
			+ "	jrf_message.heading as subject, jrf_message.centents, jrf_message.sender, "
			+ "	jrf_message.id, jrf_thread.lintime AS t_stamp, jrf_message.ip, jrf_thread.threadid AS tid , jrf_thread.sortby "
			+ "FROM jrf_thread, jrf_message " + "WHERE "
			+ "	jrf_thread.threadid = ? AND "
			+ "	jrf_message.threadid = jrf_thread.threadid "
			+ "ORDER BY jrf_thread.lintime ASC " + "LIMIT 1";

	private static final String GET_LAST_TOPICS_IN_FORUM = "SELECT jrf_thread.threadid AS tid ,jrf_thread.sortby, "
			+ "		jrf_thread.forumid as fid, jrf_thread.LOCKED as locked, "
			+ "		count(jrf_message.threadid) AS tot_mes, jrf_forum.FORUMTITLE as forumtitle "
			+ "FROM jrf_thread, jrf_forum, jrf_message "
			+ "WHERE "
			+ "		jrf_forum.forumid = ? AND"
			+ "		jrf_thread.forumid = jrf_forum.forumid AND "
			+ "		jrf_message.threadid = jrf_thread.threadid AND "
			+ "		jrf_message.intime > ? "
			+ "GROUP BY jrf_message.threadid "
			+ "ORDER BY lintime ASC " + "LIMIT ?";

	private static final String GET_LAST_TOPICS = "SELECT jrf_thread.threadid AS tid ,jrf_thread.sortby, "
			+ "		jrf_thread.forumid as fid, jrf_thread.LOCKED as locked, "
			+ "		count(jrf_message.threadid) AS tot_mes, jrf_forum.FORUMTITLE as forumtitle "
			+ "FROM jrf_thread, jrf_forum, jrf_message "
			+ "WHERE "
			+ "		jrf_thread.forumid = jrf_forum.forumid AND "
			+ "		jrf_message.threadid = jrf_thread.threadid AND "
			+ "		jrf_message.intime > ? "
			+ "GROUP BY jrf_message.threadid "
			+ "ORDER BY lintime ASC " + "LIMIT ?";

	private static final String GET_LOG_ENTRIES_ASC = "SELECT log_date, logger, log_level, message, remote_ip, user_name, session_id "
			+ "	FROM jrf_audit_log "
			+ "	WHERE "
			+ "		CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) >= ? AND "
			+ "		CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) <= ? AND "
			+ "		logger LIKE ? AND "
			+ "		log_level LIKE ? AND "
			+ "		remote_ip LIKE ? AND "
			+ "		session_id LIKE ? AND "
			+ "		user_name LIKE ? " + "	ORDER BY log_date ASC " + "	LIMIT ?, ?";

	private static final String GET_LOG_ENTRIES_DESC = "SELECT log_date, logger, log_level, message, remote_ip, user_name, session_id "
			+ "	FROM jrf_audit_log "
			+ "	WHERE "
			+ "		CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) >= ? AND "
			+ "		CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) <= ? AND "
			+ "		logger LIKE ? AND "
			+ "		log_level LIKE ? AND "
			+ "		remote_ip LIKE ? AND "
			+ "		session_id LIKE ? AND "
			+ "		user_name LIKE ? "
			+ "	ORDER BY log_date DESC "
			+ "	LIMIT ?, ?";

	private static final String GET_NEW_THREADS = "SELECT jrf_thread.threadid AS id,jrf_thread.sortby AS sortby, jrf_thread.locked AS locked, "
			+ "jrf_message.heading AS subject,jrf_thread.forumid AS fid,count(jrf_message.threadid) AS tot_mes "
			+ "FROM jrf_thread, jrf_message LEFT JOIN jrf_forum ON (jrf_thread.forumid = jrf_forum.forumid) "
			+ "WHERE	jrf_thread.threadid = jrf_message.threadid AND jrf_thread.lintime > ? "
			+ "GROUP BY jrf_thread.threadid ORDER BY jrf_thread.sortby, jrf_thread.lintime DESC LIMIT ?,?";

	private static final String GET_NEW_THREADS_ALL = "SELECT jrf_thread.threadid AS id,jrf_thread.sortby AS sortby, jrf_thread.locked AS locked, "
			+ "jrf_message.heading AS subject,jrf_thread.forumid AS fid,count(jrf_message.threadid) AS tot_mes "
			+ "FROM jrf_thread, jrf_message LEFT JOIN jrf_forum ON (jrf_thread.forumid = jrf_forum.forumid) "
			+ "WHERE	jrf_thread.threadid = jrf_message.threadid AND jrf_thread.lintime > ?  AND jrf_forum.locked < 3 "
			+ "GROUP BY jrf_thread.threadid ORDER BY jrf_thread.sortby, jrf_thread.lintime DESC LIMIT ?,?";

	private static final String GET_OLD_TOPICS = "SELECT jrf_thread.threadid, count(jrf_message.id) AS cc FROM jrf_thread, jrf_message WHERE jrf_thread.threadid = jrf_message.threadid AND TO_DAYS(NOW()) - TO_DAYS(jrf_thread.lintime) > ? GROUP BY jrf_thread.threadid";

	private static final String GET_THREAD_LAST_INTIME = "SELECT intime FROM jrf_message WHERE threadid = ? ORDER BY intime DESC LIMIT 1";

	private static final String GET_THREAD_LAST_MESS = "SELECT centents, intime, sender,id FROM jrf_message WHERE jrf_message.threadid =?  ORDER BY intime DESC LIMIT 1";

	private static final String GET_THREAD_MESSAGES = "SELECT * FROM jrf_message WHERE threadid = ? ORDER BY intime LIMIT ?,?";

	private static final String GET_THREAD_SUBJ = "SELECT heading FROM jrf_message WHERE threadid = ? ORDER BY intime LIMIT 1";

	private static final String GET_THREADS = "SELECT jrf_thread.threadid AS id, jrf_thread.sortby AS sortby, jrf_thread.locked AS locked,"
			+ " jrf_message.heading AS subject,count(jrf_thread.threadid) AS tot_mes "
			+ "FROM jrf_thread, jrf_message WHERE jrf_thread.threadid = jrf_message.threadid AND jrf_thread.forumid =?"
			+ " GROUP BY jrf_thread.threadid ORDER BY jrf_thread.sortby, jrf_thread.lintime DESC LIMIT ?,?";

	private static final String GET_USER_SUBSCRIPTIONS = "SELECT DISTINCT jrf_message.heading, jrf_thread.forumid, jrf_subscribe.threadid "
			+ "FROM jrf_subscribe, jrf_message,jrf_thread "
			+ "WHERE user_name = ?  AND jrf_message.threadid = jrf_subscribe.threadid AND jrf_thread.threadid = jrf_subscribe.threadid "
			+ "GROUP BY jrf_subscribe.threadid ORDER BY jrf_message.intime ASC LIMIT ?,?";

	private static final String SEARCH_QUERY_END = " ORDER BY jrf_message.intime DESC LIMIT 50";

	private static final String SEARCH_QUERY_SUFF = "SELECT jrf_message.id, jrf_message.centents, jrf_message.heading, jrf_message.sender, jrf_message.intime, jrf_thread.forumid, jrf_thread.threadid, jrf_forum.locked FROM jrf_message LEFT JOIN jrf_thread USING(threadid) LEFT JOIN jrf_forum USING(forumid)";

	private static final String SEARCH_QUERY_SUFF_COUNT = "SELECT count(jrf_message.id) FROM jrf_message LEFT JOIN jrf_thread USING(threadid) LEFT JOIN jrf_forum USING(forumid)";
}

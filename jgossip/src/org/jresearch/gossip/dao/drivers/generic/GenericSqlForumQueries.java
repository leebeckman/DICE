/*
 * $Id: GenericSqlForumQueries.java,v 1.3 2005/06/07 12:32:24 bel70 Exp $
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

import org.jresearch.gossip.dao.drivers.ForumQueries;

/**
 * GenericSqlForumQueries
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:24 $
 */
class GenericSqlForumQueries extends ForumQueries {

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
		// TODO: Optimize query, change selection criteria for intime of root
		// message.
		return GET_LAST_TOPICS_IN_FORUM;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_LAST_TOPICS()
	 */
	public String getSql_GET_LAST_TOPICS() {
		// TODO: Optimize query, change selection criteria for intime of root
		// message.
		return GET_LAST_TOPICS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_NEW_THREADS_ALL()
	 */
	public String getSql_GET_LAST_UPDATED_TOPICS_ALL() {
		// TODO: Fix bug for grouping over threadid(behaves incorrectly).
		return GET_NEW_THREADS_ALL;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_NEW_THREADS()
	 */
	public String getSql_GET_LAST_UPDATED_TOPICS() {
		// TODO: Fix bug for grouping over threadid(behaves incorrectly).
		return GET_NEW_THREADS;
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.ForumQueries#getSql_GET_LOG_ENTRIES_ASC()
	 */
	public String getSql_GET_LOG_ENTRIES_ASC() {
		return GET_LOG_ENTRIES_ASC;
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

	private static final String COUNT_LOG_ENTRIES = "SELECT count(log_date) as tot_log_entries "
			+ "	FROM jrf_audit_log "
			+ "	WHERE TO_DATE(SUBSTR(log_date, 0, 19), 'YYYY-MM-DD HH24:MI:SS') BETWEEN  ? AND ? AND "
			+ "		logger LIKE ? AND "
			+ "		log_level LIKE ? AND "
			+ "		remote_ip LIKE ? AND "
			+ "		session_id LIKE ? AND "
			+ "		user_name LIKE ? " + "	ORDER BY log_date ";

	private static final String GET_LAST_MESS = "select * from ( "
			+ "		SELECT jrf_message.centents AS cont, jrf_message.sender AS m_from,jrf_message.id, "
			+ "			jrf_thread.lintime AS t_stamp, jrf_thread.threadid AS tid ,jrf_thread.sortby, "
			+ "			ROW_NUMBER() OVER(ORDER BY jrf_thread.lintime DESC) AS R"
			+ "		FROM jrf_thread, jrf_message "
			+ "		WHERE   jrf_thread.forumid = ? AND jrf_message.threadid = jrf_thread.threadid AND "
			+ "				jrf_thread.lintime = jrf_message.intime "
			+ "		ORDER BY jrf_thread.lintime DESC " + ") WHERE R = 1";

	private static final String GET_ROOT_MESS = "select * from ( "
			+ "	SELECT "
			+ "		jrf_message.heading as subject, jrf_message.centents, jrf_message.sender, "
			+ "		jrf_message.id, jrf_thread.lintime AS t_stamp, jrf_message.ip, jrf_thread.threadid AS tid , "
			+ "		jrf_thread.sortby, ROW_NUMBER() OVER(ORDER BY jrf_thread.lintime ASC) AS R "
			+ "	FROM jrf_thread, jrf_message " + "	WHERE "
			+ "		jrf_thread.threadid = ? AND "
			+ "		jrf_message.threadid = jrf_thread.threadid "
			+ "	ORDER BY jrf_thread.lintime ASC " + ") WHERE R = 1";

	private static final String GET_LAST_TOPICS_IN_FORUM = "select * from ( "
			+ "	SELECT  jrf_thread.threadid AS tid ,jrf_thread.sortby, "
			+ "			jrf_thread.forumid as fid, jrf_thread.LOCKED as locked, "
			+ "			count(jrf_message.threadid) AS tot_mes, jrf_forum.FORUMTITLE as forumtitle, "
			+ "			ROW_NUMBER() OVER(ORDER BY jrf_thread.lintime ASC) AS R "
			+ "	FROM jrf_thread, jrf_forum, jrf_message "
			+ "	WHERE "
			+ "		jrf_forum.forumid = ? AND"
			+ "		jrf_thread.forumid = jrf_forum.forumid AND "
			+ "		jrf_message.threadid = jrf_thread.threadid AND "
			+ "		jrf_message.intime > ? "
			+ "   GROUP BY "
			+ "		 jrf_thread.threadid, jrf_message.threadid, jrf_thread.sortby, jrf_thread.forumid, "
			+ "		 jrf_thread.LOCKED, jrf_thread.lintime, jrf_forum.FORUMTITLE "
			+ "   ORDER BY jrf_thread.lintime ASC "
			+ ") WHERE R between 0 and ?";

	private static final String GET_LAST_TOPICS = "select * from ( "
			+ "	SELECT  jrf_thread.threadid AS tid ,jrf_thread.sortby, "
			+ "			jrf_thread.forumid as fid, jrf_thread.LOCKED as locked, "
			+ "			count(jrf_message.threadid) AS tot_mes, jrf_forum.FORUMTITLE as forumtitle, "
			+ "			ROW_NUMBER() OVER(ORDER BY jrf_thread.lintime ASC) AS R "
			+ "	FROM jrf_thread, jrf_forum, jrf_message "
			+ "	WHERE "
			+ "		jrf_thread.forumid = jrf_forum.forumid AND "
			+ "		jrf_message.threadid = jrf_thread.threadid AND "
			+ "		jrf_message.intime > ? "
			+ "   GROUP BY "
			+ "		 jrf_thread.threadid, jrf_message.threadid, jrf_thread.sortby, jrf_thread.forumid, "
			+ "		 jrf_thread.LOCKED, jrf_thread.lintime, jrf_forum.FORUMTITLE "
			+ "   ORDER BY jrf_thread.lintime ASC "
			+ ") WHERE R between 0 and ?";

	private static final String GET_LOG_ENTRIES_ASC = "SELECT * FROM ("
			+ "SELECT log_date, logger, log_level, message, remote_ip, session_id, user_name, "
			+ "		ROW_NUMBER() OVER(ORDER BY log_date ASC ) AS R"
			+ "	FROM jrf_audit_log "
			+ "	WHERE TO_DATE(SUBSTR(log_date, 0, 19), 'YYYY-MM-DD HH24:MI:SS') BETWEEN  ? AND ? AND "
			+ "		logger LIKE ? AND " + "		log_level LIKE ? AND "
			+ "		remote_ip LIKE ? AND " + "		session_id LIKE ? AND "
			+ "		user_name LIKE ? " + "	ORDER BY log_date  ASC "
			+ ") WHERE R BETWEEN ? AND ? ";

	private static final String GET_LOG_ENTRIES_DESC = "SELECT * FROM ("
			+ "SELECT log_date, logger, log_level, message, remote_ip, session_id, user_name, "
			+ "		ROW_NUMBER() OVER(ORDER BY log_date DESC ) AS R"
			+ "	FROM jrf_audit_log "
			+ "	WHERE TO_DATE(SUBSTR(log_date, 0, 19), 'YYYY-MM-DD HH24:MI:SS') BETWEEN  ? AND ? AND "
			+ "		logger LIKE ? AND " + "		log_level LIKE ? AND "
			+ "		remote_ip LIKE ? AND " + "		session_id LIKE ? AND "
			+ "		user_name LIKE ? " + "	ORDER BY log_date DESC "
			+ ") WHERE R BETWEEN ? AND ? ";

	private static final String GET_NEW_THREADS = "SELECT * from "
			+ "	(SELECT "
			+ "		jrf_thread.threadid AS id, jrf_thread.sortby AS sortby, jrf_thread.locked AS locked, jrf_message.heading AS subject, "
			+ "		jrf_thread.forumid AS fid, count(jrf_message.threadid) AS tot_mes, jrf_thread.lintime AS lintime, "
			+ "		ROW_NUMBER() OVER(ORDER BY sortby, jrf_thread.lintime DESC) AS R "
			+ "	FROM "
			+ "		jrf_thread, jrf_message, jrf_forum "
			+ "	WHERE "
			+ "		jrf_thread.forumid = jrf_forum.forumid AND "
			+ "		jrf_thread.threadid = jrf_message.threadid AND "
			+ "		jrf_thread.lintime > ? AND jrf_forum.locked < 3 "
			+ "	GROUP BY "
			+ "		jrf_thread.threadid, jrf_thread.sortby, jrf_thread.locked, jrf_message.heading, jrf_thread.forumid, jrf_thread.lintime "
			+ "	ORDER BY " + "		jrf_thread.sortby, jrf_thread.lintime DESC "
			+ ") WHERE R BETWEEN ? AND ? ORDER BY sortby, lintime";

	private static final String GET_NEW_THREADS_ALL = "select * from ( "
			+ "	SELECT "
			+ "		jrf_thread.threadid AS id, jrf_thread.sortby AS sortby, jrf_thread.locked AS locked, jrf_message.heading AS subject, "
			+ "		jrf_thread.forumid AS fid, count(jrf_message.threadid) AS tot_mes, jrf_thread.lintime AS lintime, "
			+ "		ROW_NUMBER() OVER(ORDER BY sortby, lintime) AS R "
			+ "	FROM "
			+ "		jrf_thread, jrf_message "
			+ "	WHERE "
			+ "		jrf_thread.threadid = jrf_message.threadid AND "
			+ "		jrf_thread.lintime > ? "
			+ "	GROUP BY "
			+ "		jrf_thread.threadid, jrf_thread.sortby, jrf_thread.locked, jrf_message.heading, jrf_thread.forumid, jrf_thread.lintime "
			+ "	ORDER BY " + "		jrf_thread.sortby, jrf_thread.lintime DESC"
			+ ") WHERE R BETWEEN ? AND ? ORDER BY sortby, lintime";

	private static final String GET_OLD_TOPICS = "SELECT jrf_thread.threadid, count(jrf_message.id) AS cc "
			+ "FROM jrf_thread, jrf_message "
			+ "WHERE jrf_thread.threadid = jrf_message.threadid AND (sysdate - TO_DATE(TO_CHAR(jrf_thread.lintime, 'MM-dd-YYYY'), 'MM-dd-YYYY')) > ? "
			+ "GROUP BY jrf_thread.threadid";

	private static final String GET_THREAD_LAST_INTIME = "SELECT * from "
			+ " (SELECT intime, ROW_NUMBER() OVER(ORDER BY intime DESC) AS R FROM jrf_message WHERE threadid = ? ORDER BY intime DESC "
			+ " ) WHERE R = 1";

	private static final String GET_THREAD_LAST_MESS = "SELECT * FROM "
			+ "	(SELECT centents, intime, sender, id, "
			+ "		ROW_NUMBER() OVER(ORDER BY intime DESC) AS R "
			+ "	FROM jrf_message WHERE jrf_message.threadid = ? "
			+ ") WHERE R = 1";

	private static final String GET_THREAD_MESSAGES = "select * from "
			+ "	 (SELECT ID, SENDER, CENTENTS, INTIME, HEADING, THREADID, IP,"
			+ "			 ROW_NUMBER() OVER(ORDER BY intime) AS R "
			+ "		 FROM jrf_message " + " 		 WHERE threadid = ? "
			+ "		 ORDER BY intime" + "	) WHERE R BETWEEN ? AND ?";

	private static final String GET_THREAD_SUBJ = "SELECT heading FROM"
			+ "	(SELECT heading, ROW_NUMBER() OVER(ORDER BY intime) AS R "
			+ "		FROM jrf_message WHERE threadid = ? " + "		ORDER BY intime"
			+ ") WHERE R = 1";

	private static final String GET_THREADS = "SELECT * FROM "
			+ "	(SELECT "
			+ "		t1.threadid as id, t1.sortby as sortby,	t1.locked as locked, "
			+ "		t1.forumid as fid, t1.lintime as lintime, m2.heading as subject, "
			+ "		t2.mes_cnt as tot_mes, "
			+ "		ROW_NUMBER() OVER(ORDER BY t1.sortby, t1.lintime DESC) AS R "
			+ "	FROM jrf_thread t1, jrf_message m2, "
			+ "			(SELECT "
			+ "					jrf_thread.threadid AS id, count(jrf_thread.threadid) AS mes_cnt "
			+ "			FROM "
			+ "					jrf_thread, jrf_message "
			+ "			WHERE "
			+ "				jrf_thread.threadid = jrf_message.threadid AND "
			+ "				jrf_thread.forumid = ?"
			+ "			GROUP BY "
			+ "				jrf_thread.threadid "
			+ "			ORDER BY"
			+ "				jrf_thread.threadid "
			+ "			) t2 "
			+ "	WHERE "
			+ "		t1.threadid=t2.id AND m2.threadid=t2.id AND m2.intime=t1.lintime "
			+ ") master " + "WHERE " + "	master.R BETWEEN ? AND ?";

	private static final String GET_USER_SUBSCRIPTIONS = "select * from "
			+ "	(SELECT DISTINCT "
			+ "		t1.threadid, jrf_message.heading, t1.intime, jrf_thread.forumid, "
			+ "		ROW_NUMBER() OVER(ORDER BY jrf_message.intime) AS R "
			+ "		FROM jrf_message, jrf_thread, " + "			(SELECT DISTINCT "
			+ "				jrf_message.threadid, MIN(jrf_message.intime) as intime "
			+ "			FROM jrf_subscribe, jrf_message " + "			WHERE "
			+ "				jrf_message.threadid = jrf_subscribe.threadid AND "
			+ "				user_name = ? " + "			GROUP BY jrf_message.threadid "
			+ "			) t1 " + "		WHERE "
			+ "			jrf_message.intime = t1.intime AND "
			+ "				jrf_message.threadid = t1.threadid AND "
			+ "				jrf_thread.threadid = t1.threadid "
			+ "				ORDER BY t1.intime " + ") WHERE R BETWEEN ? AND ? ";

	private static final String SEARCH_QUERY_END = " AND jrf_thread.threadid = jrf_message.THREADID AND jrf_forum.forumid = jrf_thread.FORUMID AND ROWNUM >= 1 AND ROWNUM <= 50 ORDER BY jrf_message.intime DESC";

	private static final String SEARCH_QUERY_SUFF = "SELECT jrf_message.id, jrf_message.centents, jrf_message.heading, jrf_message.sender, jrf_message.intime, jrf_thread.forumid, jrf_thread.threadid, jrf_forum.locked FROM jrf_message, jrf_thread, jrf_forum ";

	private static final String SEARCH_QUERY_SUFF_COUNT = "SELECT count(jrf_message.id) FROM jrf_message, jrf_thread, jrf_forum ";

}

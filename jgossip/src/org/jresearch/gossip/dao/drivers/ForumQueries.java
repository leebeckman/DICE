/*
 * $Id: ForumQueries.java,v 1.5 2005/06/07 12:32:24 bel70 Exp $
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
 * ForumQueries
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ 21.03.2004
 */
public abstract class ForumQueries {

	public abstract String getSql_COUNT_LOG_ENTRIES();

	public abstract String getSql_GET_LAST_MESS();

	public abstract String getSql_GET_ROOT_MESS();

	public abstract String getSql_GET_LAST_TOPICS();

	public abstract String getSql_GET_LAST_TOPICS_IN_FORUM();

	public abstract String getSql_GET_LAST_UPDATED_TOPICS();

	public abstract String getSql_GET_LAST_UPDATED_TOPICS_ALL();

	public abstract String getSql_GET_LOG_ENTRIES_ASC();

	public abstract String getSql_GET_LOG_ENTRIES_DESC();

	public abstract String getSql_GET_OLD_TOPICS();

	public abstract String getSql_GET_THREAD_LAST_INTIME();

	public abstract String getSql_GET_THREAD_LAST_MESS();

	public abstract String getSql_GET_THREAD_MESSAGES();

	public abstract String getSql_GET_THREAD_SUBJ();

	public abstract String getSql_GET_THREADS();

	public abstract String getSql_GET_USER_SUBSCRIPTIONS();

	public abstract String getSql_SEARCH_QUERY_END();

	public abstract String getSql_SEARCH_QUERY_SUFF();

	public abstract String getSql_SEARCH_QUERY_SUFF_COUNT();

	/**
	 * @return
	 */
	public String getSql_ADD_ENTRY() {
		return ADD_ENTRY;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_FORUM() {
		return ADD_FORUM;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_GROUP() {
		return ADD_GROUP;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_MESSAGE() {
		return ADD_MESSAGE;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_MOD() {
		return ADD_MOD;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_SUBSCRIPTION() {
		return ADD_SUBSCRIPTION;
	}

	/**
	 * @return
	 */
	public String getSql_CHECK_ENTRY() {
		return CHECK_ENTRY;
	}

	/**
	 * @return
	 */
	public String getSql_CHECK_USER_MOD() {
		return CHECK_USER_MOD;
	}

	/**
	 * @return
	 */
	public String getSql_CLEAN_USER_MOD() {
		return CLEAN_USER_MOD;
	}

	/**
	 * @return
	 */
	public String getSql_CLEAN_USER_SUBSCR() {
		return CLEAN_USER_SUBSCR;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_ENTRIES() {
		return COUNT_ENTRIES;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_NEW_THREADS() {
		return COUNT_NEW_THREADS;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_NEW_THREADS_ALL() {
		return COUNT_NEW_THREADS_ALL;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_SUBSCRIPTIONS() {
		return COUNT_SUBSCRIPTIONS;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_THREAD_MESSAGES() {
		return COUNT_THREAD_MESSAGES;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_FORUM_MESSAGES() {
		return COUNT_FORUM_MESSAGES;
	}

	/**
	 * @return
	 */
	public String getSql_COUNT_THREADS() {
		return COUNT_THREADS;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_ALL_ENTRIES() {
		return DELETE_ALL_ENTRIES;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_ENTRY() {
		return DELETE_ENTRY;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_FORUM() {
		return DELETE_FORUM;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_GROUP() {
		return DELETE_GROUP;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_MESSAGE() {
		return DELETE_MESSAGE;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_SUBSCRIPTION() {
		return DELETE_SUBSCRIPTION;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_SUBSCRIPTION_ALL() {
		return DELETE_SUBSCRIPTION_ALL;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_THREAD() {
		return DELETE_THREAD;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_THREAD_MESSAGES() {
		return DELETE_THREAD_MESSAGES;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_THREAD_SUBSCRIPTIONS() {
		return DELETE_THREAD_SUBSCRIPTIONS;
	}

	/**
	 * @return
	 */
	public String getSql_DROP_USER_MOD() {
		return DROP_USER_MOD;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_LOG_ALL() {
		return DELETE_LOG_ALL;
	}

	/**
	 * @return
	 */
	public String getSql_FORUM_INFO() {
		return FORUM_INFO;
	}

	/**
	 * @return
	 */
	public String getSql_GET_ALL_FORUMS() {
		return GET_ALL_FORUMS;
	}

	/**
	 * @return
	 */
	public String getSql_GET_CONSTANTS() {
		return GET_CONSTANTS;
	}

	/**
	 * @return
	 */
	public String getSql_GET_ENTRY_LIST() {
		return GET_ENTRY_LIST;
	}

	/**
	 * @return
	 */
	public String getSql_GET_ENTRY_LIST_FULL() {
		return GET_ENTRY_LIST_FULL;
	}

	/**
	 * @return
	 */
	public String getSql_GET_FORUM_LOCKED() {
		return GET_FORUM_LOCKED;
	}

	/**
	 * @return
	 */
	public String getSql_GET_FORUMS() {
		return GET_FORUMS;
	}

	/**
	 * @return
	 */
	public String getSql_GET_FORUMS_FOR_MOD() {
		return GET_FORUMS_FOR_MOD;
	}

	/**
	 * @return
	 */
	public String getSql_GET_FORUMS_ID_LIST() {
		return GET_FORUMS_ID_LIST;
	}

	/**
	 * @return
	 */
	public String getSql_GET_GROUP_INFO() {
		return GET_GROUP_INFO;
	}

	/**
	 * @return
	 */
	public String getSql_GET_GROUP_LIST() {
		return GET_GROUP_LIST;
	}

	/**
	 * @return
	 */
	public String getSql_GET_GROUPS() {
		return GET_GROUPS;
	}

	/**
	 * @return
	 */
	public String getSql_GET_MESS_INTIME() {
		return GET_MESS_INTIME;
	}

	/**
	 * @return
	 */
	public String getSql_GET_MESS_POS_BY_INTIME() {
		return GET_MESS_POS_BY_INTIME;
	}

	/**
	 * @return
	 */
	public String getSql_GET_MESSAGE() {
		return GET_MESSAGE;
	}

	/**
	 * @return
	 */
	public String getSql_GET_SKIN_PARAMS() {
		return GET_SKIN_PARAMS;
	}

	/**
	 * @return
	 */
	public String getSql_GET_SUBSCRIBERS_LIST() {
		return GET_SUBSCRIBERS_LIST;
	}

	/**
	 * @return
	 */
	public String getSql_GET_SUBSCRIPTION() {
		return GET_SUBSCRIPTION;
	}

	/**
	 * @return
	 */
	public String getSql_GET_SUBSCRIPTION_ALL() {
		return GET_SUBSCRIPTION_ALL;
	}

	/**
	 * @return
	 */
	public String getSql_GET_THREAD_ID_LIST() {
		return GET_THREAD_ID_LIST;
	}

	/**
	 * @return
	 */
	public String getSql_GET_THREAD_LOCKED() {
		return GET_THREAD_LOCKED;
	}

	/**
	 * @return
	 */
	public String getSql_GET_UPDATED_TOPICS() {
		return GET_UPDATED_TOPICS;
	}

	/**
	 * @return
	 */
	public String getSql_GET_UPDATED_TOPICS_IN_FORUM() {
		return GET_UPDATED_TOPICS_IN_FORUM;
	}

	/**
	 * @return
	 */
	public String getSql_GET_USER_MESS_COUNT() {
		return GET_USER_MESS_COUNT;
	}

	/**
	 * @return
	 */
	public String getSql_GET_USER_MOD_FORUMS() {
		return GET_USER_MOD_FORUMS;
	}

	/**
	 * @return
	 */
	public String getSql_INSERT_THREAD() {
		return INSERT_THREAD;
	}

	/**
	 * @return
	 */
	public String getSql_IS_USER_MOD() {
		return IS_USER_MOD;
	}

	/**
	 * @return
	 */
	public String getSql_MARK_USER_MESS() {
		return MARK_USER_MESS;
	}

	/**
	 * @return
	 */
	public String getSql_MOVE_THREAD() {
		return MOVE_THREAD;
	}

	/**
	 * @return
	 */
	public String getSql_SET_FORUM_LOCKED_STATUS() {
		return SET_FORUM_LOCKED_STATUS;
	}

	/**
	 * @return
	 */
	public String getSql_SET_THREAD_LOCKED_STATUS() {
		return SET_THREAD_LOCKED_STATUS;
	}

	/**
	 * @return
	 */
	public String getSql_SET_THREAD_SORT_BY() {
		return SET_THREAD_SORT_BY;
	}

	/**
	 * @return
	 */
	public String getSql_THREAD_INFO() {
		return THREAD_INFO;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_CONSTANTS() {
		return UPDATE_CONSTANTS;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_ENTRY() {
		return UPDATE_ENTRY;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_FORUM() {
		return UPDATE_FORUM;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_GROUP() {
		return UPDATE_GROUP;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_MESSAGE() {
		return UPDATE_MESSAGE;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_SKIN_PARAM() {
		return UPDATE_SKIN_PARAM;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_SUBSCRIBE() {
		return UPDATE_SUBSCRIBE;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_THREAD_LINTIME() {
		return UPDATE_THREAD_LINTIME;
	}

	/**
	 * @return
	 */
	public String getSql_GET_RANKS() {
		return GET_RANKS;
	}

	/**
	 * @return
	 */
	public String getSql_REMOVE_RANK() {
		return REMOVE_RANK;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_RANK() {
		return ADD_RANK;
	}

	/**
	 * @return
	 */
	public String getSql_GET_RANK() {
		return GET_RANK;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_RANK() {
		return UPDATE_RANK;
	}

	/**
	 * @return
	 */
	public String getSql_GET_ATTACH_INFO() {
		return GET_ATTACH_INFO;
	}

	/**
	 * @return
	 */
	public String getSql_GET_ATTACHMENTS() {
		return GET_ATTACHMENTS;
	}

	/**
	 * @return
	 */
	public String getSql_ADD_ATTACH_INFO() {
		return ADD_ATTACH_INFO;
	}

	/**
	 * @return
	 */
	public String getSql_DELETE_ATTACH() {
		return DELETE_ATTACH;
	}

	/**
	 * @return
	 */
	public String getSql_UPDATE_ATTACH_INFO() {
		return UPDATE_ATTACH_INFO;
	}

	private static final String UPDATE_SKIN_PARAM = "UPDATE jrf_skin_params SET param_value = ? WHERE param_name = ? and skinid =?";

	private static final String GET_SKIN_PARAMS = "SELECT param_name,param_value FROM jrf_skin_params WHERE skinid=?";

	private static final String GET_CONSTANTS = "SELECT * FROM jrf_constants";

	private static final String GET_ENTRY_LIST_FULL = "SELECT * FROM jrf_whois ORDER BY user_name DESC";

	private static final String GET_MESS_POS_BY_INTIME = "SELECT count(id) FROM jrf_message WHERE threadid=? AND intime<=? ORDER BY intime";

	private static final String GET_MESS_INTIME = "SELECT intime FROM jrf_message WHERE id=?";

	private static final String ADD_ENTRY = "INSERT INTO jrf_whois(sessionid, ip, user_name, id) VALUES(?,?,?,?)";

	private static final String ADD_FORUM = "INSERT INTO jrf_forum(forumtitle,forumdesc,groupid,forum_sort,forumid) VALUES(?,?,?,?,?)";

	private static final String ADD_GROUP = "INSERT INTO jrf_group(group_name,group_sort,groupid) VALUES(?,?,?)";

	private static final String ADD_MESSAGE = "INSERT INTO jrf_message(sender,centents,intime,heading,threadid,ip,id) VALUES(?,?,?,?,?,?,?)";

	private static final String ADD_MOD = "INSERT INTO jrf_mod VALUES(?,?)";

	private static final String ADD_SUBSCRIPTION = "INSERT INTO jrf_subscribe VALUES(?,?,?)";

	private static final String CHECK_ENTRY = "SELECT id FROM jrf_whois WHERE sessionid=?";

	private static final String CHECK_USER_MOD = "SELECT * FROM jrf_mod WHERE forum_id = ? AND user_name = ?";

	private static final String CLEAN_USER_MOD = "DELETE FROM jrf_mod WHERE user_name =?";

	private static final String CLEAN_USER_SUBSCR = "DELETE FROM jrf_subscribe WHERE user_name = ?";

	private static final String COUNT_ENTRIES = "SELECT count(id) AS totus FROM jrf_whois";

	private static final String COUNT_NEW_THREADS = "SELECT count(threadid) as tot FROM jrf_thread WHERE jrf_thread.lintime >?";

	private static final String COUNT_NEW_THREADS_ALL = "SELECT count(threadid) as tot FROM jrf_thread WHERE jrf_thread.lintime >?";

	private static final String COUNT_SUBSCRIPTIONS = "SELECT count(threadid) FROM jrf_subscribe WHERE user_name = ?";

	private static final String COUNT_THREAD_MESSAGES = "SELECT count(id) as tot FROM jrf_message WHERE threadid=?";

	private static final String COUNT_FORUM_MESSAGES = "SELECT count(jrf_message.id) AS tot FROM jrf_message, jrf_thread WHERE jrf_message.threadid=jrf_thread.threadid and jrf_thread.forumid=?";

	private static final String COUNT_THREADS = "SELECT count(threadid) as tot_threads FROM jrf_thread WHERE forumid =?";

	private static final String DELETE_ENTRY = "DELETE FROM jrf_whois WHERE sessionid=? OR user_name=?";

	private static final String DELETE_ALL_ENTRIES = "DELETE FROM jrf_whois WHERE user_name IS NULL or user_name NOT LIKE 'Administrator' ";

	private static final String DELETE_FORUM = "DELETE FROM jrf_forum WHERE forumid = ?";

	private static final String DELETE_GROUP = "DELETE FROM jrf_group WHERE groupid = ?";

	private static final String DELETE_MESSAGE = "DELETE FROM jrf_message WHERE id = ?";

	private static final String DELETE_SUBSCRIPTION = "DELETE FROM jrf_subscribe WHERE (threadid=? AND user_mail=? AND user_name=?)";

	private static final String DELETE_SUBSCRIPTION_ALL = "DELETE FROM jrf_subscribe WHERE (user_mail=? AND user_name LIKE ?)";

	private static final String DELETE_THREAD = "DELETE FROM jrf_thread WHERE threadid =? ";

	private static final String DELETE_THREAD_MESSAGES = "DELETE FROM jrf_message WHERE threadid = ?";

	private static final String DELETE_THREAD_SUBSCRIPTIONS = "DELETE FROM jrf_subscribe WHERE threadid = ?";

	private static final String DROP_USER_MOD = "DELETE FROM jrf_mod WHERE user_name = ? AND forum_id = ?";

	private static final String FORUM_INFO = "SELECT * FROM jrf_forum WHERE forumid =?";

	private static final String GET_ALL_FORUMS = "SELECT forumtitle, forumdesc, forumid, locked FROM jrf_forum WHERE jrf_forum.groupid = ? ORDER BY forum_sort";

	private static final String GET_ENTRY_LIST = "SELECT * FROM jrf_whois WHERE user_name IS NOT NULL";

	private static final String GET_FORUM_LOCKED = "SELECT locked FROM jrf_forum WHERE forumid = ?";

	private static final String GET_FORUMS = "SELECT forumtitle, forumdesc, forumid, locked FROM jrf_forum WHERE jrf_forum.groupid = ? AND locked < 3 ORDER BY forum_sort";

	private static final String GET_FORUMS_FOR_MOD = "SELECT jrf_forum.forumid AS fid, jrf_forum.forumtitle AS forumtitle,"
			+ " jrf_group.groupid AS gid FROM jrf_forum, jrf_group WHERE "
			+ " jrf_forum.groupid = jrf_group.groupid  ORDER BY group_sort, jrf_group.groupid, jrf_forum.forum_sort";

	private static final String GET_FORUMS_ID_LIST = "SELECT forumid FROM jrf_forum WHERE groupid = ?";

	private static final String GET_GROUP_INFO = "SELECT * FROM jrf_group WHERE groupid = ?";

	private static final String GET_GROUP_LIST = "SELECT * FROM jrf_group ORDER BY group_sort";

	private static final String GET_GROUPS = "SELECT * FROM jrf_group ORDER BY group_sort";

	private static final String GET_MESSAGE = "SELECT centents,heading,id,intime,ip,sender FROM jrf_message WHERE id = ?";

	private static final String GET_SUBSCRIBERS_LIST = "SELECT DISTINCT user_mail, user_name FROM jrf_subscribe WHERE threadid = ? AND user_name != ?";

	private static final String GET_SUBSCRIPTION = "SELECT * FROM jrf_subscribe WHERE (threadid=? AND user_mail=? AND user_name=?)";

	private static final String GET_SUBSCRIPTION_ALL = "SELECT * FROM jrf_subscribe WHERE (user_mail=? AND user_name LIKE ?)";

	private static final String GET_THREAD_ID_LIST = "SELECT threadid FROM jrf_thread WHERE forumid = ?";

	private static final String GET_THREAD_LOCKED = "SELECT locked FROM jrf_thread WHERE threadid =?";

	private static final String GET_UPDATED_TOPICS = "SELECT jrf_thread.THREADID as tid "
			+ "	FROM jrf_user, jrf_thread, jrf_forum "
			+ "	WHERE "
			+ "		jrf_thread.LINTIME > jrf_user.LAST_INTIME AND "
			+ "		jrf_thread.FORUMID = jrf_forum.FORUMID AND "
			+ "		( ( jrf_forum.locked < 3 AND jrf_user.USER_STATUS < 7) OR "
			+ "			jrf_user.USER_STATUS >= 7 ) AND "
			+ "		jrf_user.user_name = ? ";

	private static final String GET_UPDATED_TOPICS_IN_FORUM = "SELECT jrf_thread.THREADID as tid "
			+ "	FROM jrf_user, jrf_thread, jrf_forum "
			+ "	WHERE "
			+ "		jrf_thread.LINTIME > jrf_user.LAST_INTIME AND "
			+ "		jrf_thread.FORUMID = jrf_forum.FORUMID AND "
			+ "		( ( jrf_forum.locked < 3 AND jrf_user.USER_STATUS < 7) OR "
			+ "			jrf_user.USER_STATUS >= 7 ) AND "
			+ "		jrf_user.user_name = ? AND jrf_thread.forumid = ? ";

	private static final String GET_USER_MESS_COUNT = "SELECT count(id) as tot FROM jrf_message WHERE sender = ?";

	private static final String GET_USER_MOD_FORUMS = "SELECT forumtitle, forumid FROM jrf_forum, jrf_mod WHERE jrf_mod.forum_id = forumid AND jrf_mod.user_name = ?";

	private static final String INSERT_THREAD = "INSERT INTO jrf_thread(forumid, threadid) VALUES(?,?)";

	private static final String IS_USER_MOD = "SELECT * FROM jrf_mod WHERE user_name = ?";

	private static final String MARK_USER_MESS = "UPDATE jrf_message SET sender = ? WHERE sender = ?";

	private static final String MOVE_THREAD = "UPDATE jrf_thread SET forumid =? WHERE threadid = ?";

	private static final String SET_FORUM_LOCKED_STATUS = "UPDATE jrf_forum SET locked = ? WHERE forumid = ?";

	private static final String SET_THREAD_LOCKED_STATUS = "UPDATE jrf_thread SET locked = ? WHERE threadid = ?";

	private static final String SET_THREAD_SORT_BY = "UPDATE jrf_thread SET sortby = ? WHERE threadid = ?";

	private static final String THREAD_INFO = "SELECT locked FROM jrf_thread WHERE threadid =?";

	private static final String UPDATE_CONSTANTS = "UPDATE jrf_constants SET  c_value=?  WHERE c_name=?";

	private static final String UPDATE_ENTRY = "UPDATE jrf_whois SET user_name = ? WHERE sessionid=? ";

	private static final String UPDATE_FORUM = "UPDATE jrf_forum SET forumtitle = ?, forumdesc = ?,groupid = ?, forum_sort=? WHERE forumid = ?";

	private static final String UPDATE_GROUP = "UPDATE jrf_group SET group_name = ?, group_sort = ? WHERE groupid = ?";

	private static final String UPDATE_MESSAGE = "UPDATE jrf_message SET centents = ?, heading = ? WHERE id =?";

	private static final String UPDATE_SUBSCRIBE = "UPDATE jrf_subscribe SET user_mail = ? WHERE user_name = ?";

	private static final String UPDATE_THREAD_LINTIME = "UPDATE jrf_thread SET lintime =? WHERE threadid = ?";

	private static final String GET_RANKS = "SELECT * FROM jrf_rank  ORDER BY rank_count";

	private static final String REMOVE_RANK = "DELETE FROM jrf_rank WHERE id = ?";

	private static final String ADD_RANK = "INSERT INTO jrf_rank(id,rank_count, rank_name) VALUES(?,?,?)";

	private static final String GET_RANK = "SELECT * FROM jrf_rank WHERE id = ?";

	private static final String UPDATE_RANK = "UPDATE jrf_rank SET rank_count = ?, rank_name = ? WHERE id = ?";

	private static final String GET_ATTACH_INFO = "SELECT * FROM jrf_attach WHERE id = ?";

	private static final String GET_ATTACHMENTS = "SELECT * FROM jrf_attach WHERE message_id = ?";

	private static final String ADD_ATTACH_INFO = "INSERT INTO jrf_attach(id, attach_content_type, attach_name, attach_description,attach_size,message_id)"
			+ " VALUES(?,?,?,?,?,?)";

	private static final String DELETE_ATTACH = "DELETE FROM jrf_attach WHERE id = ?";

	private static final String UPDATE_ATTACH_INFO = "UPDATE jrf_attach SET attach_name = ?, attach_description = ? WHERE id = ?";

	private static final String DELETE_LOG_ALL = "DELETE FROM jrf_audit_log";

}

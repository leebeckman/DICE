/*
 * $$Id: ForumDAO.java,v 1.6 2005/06/09 07:15:12 bel70 Exp $$
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
 *              Dmitry Belov <bel@jresearch.org>
 *
 * ***** END LICENSE BLOCK ***** */
/*
 * Created on 08.05.2003
 *
 */
package org.jresearch.gossip.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.LogEntry;
import org.jresearch.gossip.beans.LogSearchCriteria;
import org.jresearch.gossip.beans.RankInfoDTO;
import org.jresearch.gossip.beans.forum.Forum;
import org.jresearch.gossip.beans.forum.Group;
import org.jresearch.gossip.beans.forum.LastTopic;
import org.jresearch.gossip.beans.forum.Message;
import org.jresearch.gossip.beans.forum.NewTopic;
import org.jresearch.gossip.beans.forum.SearchResult;
import org.jresearch.gossip.beans.forum.Topic;
import org.jresearch.gossip.beans.forum.attachment.FileData;
import org.jresearch.gossip.beans.forum.attachment.FileDataInfo;
import org.jresearch.gossip.beans.subscription.Subscriber;
import org.jresearch.gossip.beans.user.Entry;
import org.jresearch.gossip.beans.user.EntryList;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.drivers.DbDriver;
import org.jresearch.gossip.dao.drivers.keygen.IKeyGenConst;
import org.jresearch.gossip.dao.drivers.keygen.KeyGenerator;
import org.jresearch.gossip.dao.drivers.keygen.KeyGeneratorFactory;
import org.jresearch.gossip.dao.file.FileProcessorFactory;
import org.jresearch.gossip.dao.file.IFileProcConst;
import org.jresearch.gossip.dao.file.IFileProcessor;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ForumForm;
import org.jresearch.gossip.forms.GroupForm;
import org.jresearch.gossip.forms.MessageForm;
import org.jresearch.gossip.forms.ProcessForumForm;
import org.jresearch.gossip.forms.ProcessMessageForm;
import org.jresearch.gossip.forms.ProcessTopicForm;
import org.jresearch.gossip.forms.SearchForm;
import org.jresearch.gossip.forms.StylesForm;
import org.jresearch.gossip.list.Mapping;
import org.jresearch.gossip.list.RecordsData;
import org.jresearch.gossip.util.MySQLCodec;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class ForumDAO extends DAO {

	private static ForumDAO instance;

	private final DbDriver dbDriver;

	private final KeyGenerator keyGen;

	private static Object lock = new Object();

	private Map fileProcessors = Collections.synchronizedMap(new HashMap());

	private ForumDAO() {
		try {
			this.dbDriver = DbDriver.getInstance();
			this.keyGen = KeyGeneratorFactory
					.getKeyGenerator(IKeyGenConst.DEFAULT_KEYGEN);
		} catch (SQLException ex) {
			throw new RuntimeException("ForumDAO not initialized", ex);
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static ForumDAO getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new ForumDAO();
				}
			}
		}
		return instance;
	}

	private IFileProcessor getFileProcessor(String fileProcessorName)
			throws SystemException {
		IFileProcessor fileProcessor = (IFileProcessor) fileProcessors
				.get(fileProcessorName);
		if (fileProcessor == null) {
			synchronized (fileProcessors) {
				if (fileProcessor == null) {
					fileProcessor = FileProcessorFactory.getInstance()
							.getFileProcessor(fileProcessorName);
					fileProcessors.put(fileProcessorName, fileProcessor);
				}
			}
		}
		return fileProcessor;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void addForum(ForumForm form) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_ADD_FORUM());

		try {
			int fid = ((Integer) keyGen.generateKey(
					IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_FORUM], connection))
					.intValue();
			;
			st.setString(1, form.getForum_name());
			st.setString(2, form.getForum_desc());
			st.setInt(3, Integer.parseInt(form.getGroupid()));
			st.setString(4, form.getForum_sort());
			st.setInt(5, fid);
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void addGroup(GroupForm form) throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_ADD_GROUP());

		int gid = ((Integer) keyGen.generateKey(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_GROUP], connection))
				.intValue();
		try {
			st.setString(1, form.getGroup_name());
			st.setString(2, form.getGroup_sort());
			st.setInt(3, gid);
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * @param ip
	 *            DOCUMENT ME!
	 * @param announce
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public int addMessage(MessageForm form, String ip, boolean announce)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_ADD_MESSAGE());
		int mid = ((Integer) keyGen.generateKey(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_MESSAGE], connection))
				.intValue();

		try {
			Timestamp now = new Timestamp(now().getTime());
			st.setString(1, form.getName());
			st.setString(2, form.getText());
			st.setTimestamp(3, now);
			st.setString(4, form.getTitle());
			st.setInt(5, Integer.parseInt(form.getTid()));
			st.setString(6, ip);
			st.setInt(7, mid);
			st.execute();

			if (announce) {
				setThreadSortBy(form.getTid(), 5);
			}

			updateThreadIntime(form.getTid(), now);
		} finally {
			st.close();
			connection.close();
		}

		return mid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param fid
	 * @param uid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void addMod(String fid, String uid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_CHECK_USER_MOD());
		ResultSet rs = null;

		try {
			st.setString(1, uid);
			st.setInt(2, Integer.parseInt(fid));
			rs = st.executeQuery();

			if (!rs.next()) {
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_ADD_MOD());
				st.setString(1, uid);
				st.setInt(2, Integer.parseInt(fid));
				st.execute();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param forumId
	 *            DOCUMENT ME!
	 * @param user
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public boolean checkMod(int forumId, User user) throws SQLException {
		boolean mod = false;
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_CHECK_USER_MOD());
		ResultSet rs = null;

		try {
			st.setInt(1, forumId);
			st.setString(2, user.getName());
			rs = (ResultSet) st.executeQuery();
			mod = (rs.next() || (user.getStatus() > 7));
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return mod;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param forumid
	 * @param st
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	private int countForumTopics(int forumid, PreparedStatement st)
			throws SQLException {
		ResultSet rs = null;

		try {
			st.setInt(1, forumid);
			rs = (ResultSet) st.executeQuery();
			rs.next();

			return rs.getInt(1);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public int countForumMessages(int fid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_COUNT_FORUM_MESSAGES());
		ResultSet rs = null;

		try {
			st.setInt(1, fid);
			rs = st.executeQuery();
			rs.next();

			return rs.getInt(1);
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public int countThreadMessages(int tid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_COUNT_THREAD_MESSAGES());
		ResultSet rs = null;

		try {
			st.setInt(1, tid);
			rs = st.executeQuery();
			rs.next();

			return rs.getInt(1);
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	private int countLogEntries(LogSearchCriteria criteria) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_COUNT_LOG_ENTRIES());
		ResultSet rs = null;

		try {
			st.setTimestamp(1, new Timestamp(criteria.getFrom().getTime()));
			st.setTimestamp(2, new Timestamp(criteria.getTo().getTime()));
			if (IConst.VALUES.ALL.equals(criteria.getLogger())) {
				st.setString(3, "%");
			} else {
				st.setString(3, criteria.getLogger());
			}
			if (IConst.VALUES.ALL.equals(criteria.getLog_level())) {
				st.setString(4, "%");
			} else {
				st.setString(4, criteria.getLog_level());
			}
			if (IConst.VALUES.ALL.equals(criteria.getRemote_ip())) {
				st.setString(5, "%");
			} else {
				st.setString(5, criteria.getRemote_ip());
			}
			if (IConst.VALUES.ALL.equals(criteria.getSession_id())) {
				st.setString(6, "%");
			} else {
				st.setString(6, criteria.getSession_id());
			}
			if (IConst.VALUES.ALL.equals(criteria.getUser_name())) {
				st.setString(7, "%");
			} else {
				st.setString(7, criteria.getUser_name());
			}

			rs = st.executeQuery();
			rs.next();

			return rs.getInt(1);
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param fid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void deleteForum(String fid) throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_DELETE_FORUM());
		ResultSet rs = null;

		try {
			st.setInt(1, Integer.parseInt(fid));
			st.execute();
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_THREAD_ID_LIST());
			st.setInt(1, Integer.parseInt(fid));
			rs = st.executeQuery();

			while (rs.next()) {
				deleteThread(rs.getString(1), true);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param gid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void deleteGroup(String gid) throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_DELETE_GROUP());
		ResultSet rs = null;

		try {
			st.setInt(1, Integer.parseInt(gid));
			st.execute();
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_FORUMS_ID_LIST());
			st.setInt(1, Integer.parseInt(gid));
			rs = st.executeQuery();

			while (rs.next()) {
				deleteForum(rs.getString(1));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 * @throws SystemException
	 */
	public void deleteMessage(ProcessMessageForm form) throws SQLException,
			SystemException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_DELETE_MESSAGE());
		ResultSet rs = null;

		try {

			// remove all attachments if exists
			Message mes = getMessage(form.getMid());
			if (mes.isHasAttachment()) {
				FileDataInfo[] attach = mes.getAttachments();
				for (int i = 0; i < attach.length; i++) {
					removeAttachment(attach[i].getId());
				}
			}
			// delete the message...
			st.setInt(1, Integer.parseInt(form.getMid()));
			st.execute();

			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_THREAD_LAST_INTIME());
			st.setInt(1, Integer.parseInt(form.getTid()));
			rs = st.executeQuery();

			if (rs.next()) {
				updateThreadIntime(form.getTid(), rs.getTimestamp(1));
			} else { // erase thread if this is the last message in thread...
				deleteThread(form.getTid());
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void deleteThread(String tid) throws SQLException {
		deleteThread(tid, false);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 *            DOCUMENT ME!
	 * @param clear
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void deleteThread(String tid, boolean clear) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		int id = Integer.parseInt(tid);
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_DELETE_THREAD());

		try {
			st.setInt(1, id);
			st.execute();

			if (clear) { // DELETE ALL MESSAGES FROM THREAD
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_DELETE_THREAD_MESSAGES());
				st.setInt(1, id);
				st.execute();
			}

			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_DELETE_THREAD_SUBSCRIPTIONS());
			st.setInt(1, id);
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param fid
	 * @param uid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void dropMod(String fid, String uid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		String queryString = (dbDriver.getQueries().getForumQueries()
				.getSql_DROP_USER_MOD());
		PreparedStatement st = connection.prepareStatement(queryString);

		try {
			st.setString(1, uid);
			st.setInt(2, Integer.parseInt(fid));
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * Retreive records from audit database according to search criteria.
	 * Support paging of results
	 * 
	 * @param criteria
	 * @param recordsData
	 * @param block
	 */
	public void fillLogEntryList(LogSearchCriteria criteria,
			RecordsData recordsData, int currBlock)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();
		String sqlStatement;
		if (criteria.isSortOrder()) {
			sqlStatement = dbDriver.getQueries().getForumQueries()
					.getSql_GET_LOG_ENTRIES_DESC();
		} else {
			sqlStatement = dbDriver.getQueries().getForumQueries()
					.getSql_GET_LOG_ENTRIES_ASC();
		}
		PreparedStatement st = connection.prepareStatement(sqlStatement);
		ResultSet rs = null;

		try {
			st.setTimestamp(1, new Timestamp(criteria.getFrom().getTime()));
			st.setTimestamp(2, new Timestamp(criteria.getTo().getTime()));
			if (IConst.VALUES.ALL.equals(criteria.getLogger())) {
				st.setString(3, "%");
			} else {
				st.setString(3, criteria.getLogger());
			}
			if (IConst.VALUES.ALL.equals(criteria.getLog_level())) {
				st.setString(4, "%");
			} else {
				st.setString(4, criteria.getLog_level());
			}
			if (IConst.VALUES.ALL.equals(criteria.getRemote_ip())) {
				st.setString(5, "%");
			} else {
				st.setString(5, criteria.getRemote_ip());
			}
			if (IConst.VALUES.ALL.equals(criteria.getSession_id())) {
				st.setString(6, "%");
			} else {
				st.setString(6, criteria.getSession_id());
			}
			if (IConst.VALUES.ALL.equals(criteria.getUser_name())) {
				st.setString(7, "%");
			} else {
				st.setString(7, criteria.getUser_name());
			}
			st.setInt(8, currBlock);
			st.setInt(9, dbDriver.getLastRowIdx(currBlock, recordsData
					.getBlockSize()));
			rs = (ResultSet) st.executeQuery();
			recordsData.fillRecords(rs, Mapping.getInstance().LogEntryMapping,
					LogEntry.class);
			recordsData.setCurrBlock(currBlock);
			recordsData.setRecordsCount(countLogEntries(criteria));
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * delete all records in audit database.
	 * 
	 * @throws SQLException
	 */
	public void clearLog() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_DELETE_LOG_ALL());

		try {
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 * @param recordsData
	 * @param form
	 * 
	 * @throws InstantiationException
	 *             DOCUMENT ME!
	 * @throws IllegalAccessException
	 *             DOCUMENT ME!
	 * @throws InvocationTargetException
	 *             DOCUMENT ME!
	 * @throws NoSuchMethodException
	 *             DOCUMENT ME!
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillMessagesList(User user, RecordsData recordsData,
			ProcessTopicForm form) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_THREAD_MESSAGES());
		ResultSet rs = null;

		try {
			int currBlock = Integer.parseInt(form.getBlock());
			int tid = Integer.parseInt(form.getTid());
			st.setInt(1, tid);
			st.setInt(2, currBlock);
			st.setInt(3, dbDriver.getLastRowIdx(currBlock, user.getSettings()
					.getMes_per_page()));
			rs = (ResultSet) st.executeQuery();
			recordsData.fillRecords(rs, Mapping.getInstance().MessageMapping,
					Message.class);
			recordsData.setBlockSize(user.getSettings().getMes_per_page());
			recordsData.setCurrBlock(currBlock);
			recordsData.setRecordsCount(countThreadMessages(tid));
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 * @param recordsData
	 * @param form
	 * 
	 * @throws InstantiationException
	 *             DOCUMENT ME!
	 * @throws IllegalAccessException
	 *             DOCUMENT ME!
	 * @throws InvocationTargetException
	 *             DOCUMENT ME!
	 * @throws NoSuchMethodException
	 *             DOCUMENT ME!
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillThreadList(User user, RecordsData recordsData,
			ProcessForumForm form) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_THREADS());
		ResultSet rs = null;

		try {
			int currBlock = Integer.parseInt(form.getBlock());
			st.setInt(1, Integer.parseInt(form.getFid()));
			st.setInt(2, currBlock);
			st.setInt(3, dbDriver.getLastRowIdx(currBlock, user.getSettings()
					.getMes_per_page()));
			rs = (ResultSet) st.executeQuery();
			recordsData.fillRecords(rs, Mapping.getInstance().TreadMapping,
					Topic.class);

			Iterator it = recordsData.getRecords().iterator();
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_THREAD_LAST_MESS());

			while (it.hasNext()) {
				setLastMessage((Topic) it.next(), st);
			}

			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_COUNT_THREADS());
			recordsData.setRecordsCount(countForumTopics(Integer.parseInt(form
					.getFid()), st));
			recordsData.setBlockSize(user.getSettings().getMes_per_page());

			recordsData.setCurrBlock(currBlock);
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * @param mess_id
	 * @return
	 */
	public ArrayList getAttachmentsInfo(int mess_id) throws SQLException {
		ArrayList attachments = new ArrayList();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_ATTACHMENTS());
		ResultSet rs = null;
		try {

			st.setInt(1, mess_id);
			rs = (ResultSet) st.executeQuery();
			while (rs.next()) {
				FileDataInfo fileData = new FileDataInfo();
				fileData.setId(rs.getInt("id"));
				fileData.setName(rs.getString("attach_name"));
				fileData.setContentType(rs.getString("attach_content_type"));
				fileData.setDescription(rs.getString("attach_description"));
				fileData.setMessageId(rs.getInt("message_id"));
				fileData.setSize(rs.getInt("attach_size"));
				attachments.add(fileData);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
		return attachments;

	}

	/**
	 * @param id -
	 *            attachment Id
	 * @return
	 * @throws SQLException
	 * @throws SystemException
	 */
	public FileData getAttachment(int id) throws SQLException, SystemException {
		FileData fd = new FileData();
		fd.setInfo(getAttachmentInfo(id));
		IFileProcessor fProc = getFileProcessor(IFileProcConst.ATTACH_FILE_PROCESSOR);
		byte[] data = fProc.getFileData(IFileProcConst.ATTACH_KEY_PREFIX + id);
		if (data == null) {
			data = new byte[0];
		}
		fd.setData(data);
		return fd;

	}

	/**
	 * @param files
	 * @throws SystemException
	 */
	public void saveAttachments(FileData[] files) throws SQLException,
			SystemException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_ADD_ATTACH_INFO());
		IFileProcessor fProc = getFileProcessor(IFileProcConst.ATTACH_FILE_PROCESSOR);
		try {
			for (int i = 0; i < files.length; i++) {
				int id = ((Integer) keyGen.generateKey(
						IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_ATTACH],
						connection)).intValue();
				FileData fd = files[i];
				st.setInt(1, id);
				st.setString(2, fd.getInfo().getContentType());
				st.setString(3, fd.getInfo().getName());
				st.setString(4, fd.getInfo().getDescription());
				st.setInt(5, fd.getInfo().getSize());
				st.setInt(6, fd.getInfo().getMessageId());
				st.execute();

				fProc.saveFileData(fd.getData(),
						IFileProcConst.ATTACH_KEY_PREFIX + id);
			}
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * @param id
	 * @throws SystemException
	 */
	public void removeAttachment(int id) throws SQLException, SystemException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_DELETE_ATTACH());
		IFileProcessor fProc = getFileProcessor(IFileProcConst.ATTACH_FILE_PROCESSOR);
		try {
			st.setInt(1, id);
			st.execute();
			fProc.removeFileData(IFileProcConst.ATTACH_KEY_PREFIX + id);
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * @param fileInfo
	 */
	public void updateAttachmentInfo(FileDataInfo fileInfo) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_ATTACH_INFO());
		try {
			st.setInt(3, fileInfo.getId());
			st.setString(1, fileInfo.getName());
			st.setString(2, fileInfo.getDescription());
			st.executeUpdate();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param forumid
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public Forum getForumInfo(int forumid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_FORUM_INFO());
		ResultSet rs = null;
		Forum _forum = new Forum();

		try {
			st.setInt(1, forumid);
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				_forum.setLocked(rs.getInt("locked"));
				_forum.setTitle(rs.getString("forumtitle"));
				_forum.setDescription(rs.getString("forumdesc"));
				_forum.setSort(rs.getString("forum_sort"));
				_forum.setGroupid(rs.getInt("groupid"));
			}

			return _forum;
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param group
	 *            DOCUMENT ME!
	 * @param userStatus
	 *            DOCUMENT ME!
	 * @param messages
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void getForums(Group group, int userStatus)
			throws NumberFormatException, SQLException, ConfiguratorException {
		getForums(group, userStatus, true);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param group
	 *            DOCUMENT ME!
	 * @param userStatus
	 *            DOCUMENT ME!
	 * @param messages
	 *            DOCUMENT ME!
	 * @param filled
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void getForums(Group group, int userStatus, boolean filled)
			throws SQLException, NumberFormatException, ConfiguratorException {
		String queryString = ((userStatus < Integer.parseInt(Configurator
				.getInstance().get(IConst.CONFIG.INVADER1))) ? dbDriver
				.getQueries().getForumQueries().getSql_GET_FORUMS() : dbDriver
				.getQueries().getForumQueries().getSql_GET_ALL_FORUMS());
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(queryString);
		PreparedStatement st2 = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_LAST_MESS());
		PreparedStatement st3 = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_COUNT_THREADS());
		ResultSet rs = null;

		try {
			st.setInt(1, group.getGroupid());
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				Forum forum = new Forum();
				forum.setDescription(rs.getString("forumdesc"));
				forum.setForumid(rs.getInt("forumid"));
				forum.setLocked(rs.getInt("locked"));
				forum.setTitle(rs.getString("forumtitle"));

				if (filled) {

					forum.setThreadsCount(countForumTopics(forum.getForumid(),
							st3));
					forum.setMessCount(this.countForumMessages(forum
							.getForumid()));
					setLastMessage(forum, st2);
				}

				group.addForum(forum);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st3.close();
			st2.close();
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public ArrayList getForumsForMod() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_FORUMS_FOR_MOD());
		ResultSet rs = null;
		ArrayList forumsForMod = new ArrayList();

		try {
			rs = st.executeQuery();

			int gid = -1;

			while (rs.next()) {
				if (rs.getInt("gid") != gid) {
					gid = rs.getInt("gid");

					Forum f = new Forum();
					f.setTitle(IConst.JSP.OPTIONS_SEPERATOR);
					forumsForMod.add(f);
				}

				Forum f = new Forum();
				f.setForumid(rs.getInt("fid"));
				f.setTitle(rs.getString("forumtitle"));
				forumsForMod.add(f);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return forumsForMod;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param recordsData
	 * 
	 * @throws InstantiationException
	 *             DOCUMENT ME!
	 * @throws IllegalAccessException
	 *             DOCUMENT ME!
	 * @throws InvocationTargetException
	 *             DOCUMENT ME!
	 * @throws NoSuchMethodException
	 *             DOCUMENT ME!
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void getGroupList(RecordsData recordsData)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_GROUP_LIST());
		ResultSet rs = null;

		try {
			rs = st.executeQuery();
			recordsData.fillRecords(rs, Mapping.getInstance().GroupMapping,
					Group.class);
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param userStatus
	 *            DOCUMENT ME!
	 * @param messages
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public ArrayList getGroups(int userStatus, MessageResources messages)
			throws NumberFormatException, SQLException, ConfiguratorException {
		return getGroups(userStatus, true);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param userStatus
	 *            DOCUMENT ME!
	 * @param messages
	 *            DOCUMENT ME!
	 * @param filled
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public ArrayList getGroups(int userStatus, boolean filled)
			throws NumberFormatException, SQLException, ConfiguratorException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_GROUPS());
		ResultSet rs = null;

		try {
			ArrayList groups = new ArrayList();
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				Group gr = new Group();
				gr.setGroupid(rs.getInt("groupid"));
				gr.setName(rs.getString("group_name"));
				getForums(gr, userStatus, filled);
				groups.add(gr);
			}

			return groups;
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * Returns java.util.List collection populated with
	 * org.jresearch.gossip.beans.forum.LastTopic objects.
	 * 
	 * @param maxCount
	 * @param since
	 * 
	 * @return
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 *             DOCUMENT ME!
	 */
	public List getLastTopics(int maxCount, Date since) throws SQLException {
		if (null == since) {
			throw new IllegalArgumentException();
		}

		java.sql.Date dateSince = new java.sql.Date(since.getTime());
		ArrayList topics = new ArrayList();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_LAST_TOPICS());
		PreparedStatement st2 = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_ROOT_MESS());
		ResultSet rs = null;

		try {
			st.setTimestamp(1, new Timestamp(dateSince.getTime()));
			st.setInt(2, maxCount);
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				LastTopic topic = new LastTopic();
				topic.setForumid(rs.getInt("fid"));
				topic.setThreadid(rs.getInt("tid"));
				topic.setLocked(rs.getInt("locked"));
				topic.setSortby(rs.getInt("sortby"));
				topic.setMessagesCount(rs.getLong("tot_mes"));
				topic.setForumName(rs.getString("forumtitle"));
				setRootMessage(topic, st2);
				topics.add(topic);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			st2.close();
			st.close();
			connection.close();
		}

		return topics;
	}

	/**
	 * Returns java.util.List collection populated with
	 * org.jresearch.gossip.beans.forum.NewTopic objects.
	 * 
	 * @param fid
	 * @param maxCount
	 * @param since
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 *             DOCUMENT ME!
	 */
	public List getLastTopics(int fid, int maxCount, Date since)
			throws SQLException {
		if (null == since) {
			throw new IllegalArgumentException();
		}

		java.sql.Date dateSince = new java.sql.Date(since.getTime());
		ArrayList topics = new ArrayList();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries()
				.getSql_GET_LAST_TOPICS_IN_FORUM());
		PreparedStatement st2 = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_ROOT_MESS());
		ResultSet rs = null;

		try {
			st.setInt(1, fid);
			st.setTimestamp(2, new Timestamp(dateSince.getTime()));
			st.setInt(3, maxCount);
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				LastTopic topic = new LastTopic();
				topic.setForumid(rs.getInt("fid"));
				topic.setThreadid(rs.getInt("tid"));
				topic.setLocked(rs.getInt("locked"));
				topic.setMessagesCount(rs.getLong("tot_mes"));
				topic.setForumName(rs.getString("forumtitle"));
				setRootMessage(topic, st2);
				topics.add(topic);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			st2.close();
			st.close();
			connection.close();
		}

		return topics;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param mid
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public Message getMessage(String mid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_MESSAGE());
		Message mess = null;
		ResultSet rs = null;

		try {
			st.setInt(1, Integer.parseInt(mid));
			rs = st.executeQuery();

			if (rs.next()) {
				mess = new Message();
				mess.setCentents(rs.getString("centents"));
				mess.setHeading(rs.getString("heading"));
				mess.setId(rs.getInt("id"));
				mess.setIntime(rs.getDate("intime"));
				mess.setIp(rs.getString("ip"));
				mess.setSender(rs.getString("sender"));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return mess;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param uid
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public int getMessCount(String uid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_USER_MESS_COUNT());
		ResultSet rs = null;
		int count = 0;

		try {
			st.setString(1, uid);
			rs = st.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return count;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public Topic getThreadInfo(int tid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_THREAD_INFO());
		ResultSet rs = null;
		Topic _thread = new Topic();

		try {
			st.setInt(1, tid);
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				_thread.setLocked(rs.getInt("locked"));
			}
			_thread.setSubject(getThreadSubject(Integer.toString(tid)));
			return _thread;
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public String getThreadSubject(String tid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_THREAD_SUBJ());
		ResultSet rs = null;
		String subject = "";

		try {
			st.setInt(1, Integer.parseInt(tid));
			rs = st.executeQuery();

			if (rs.next()) {
				subject = rs.getString(1);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return subject;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param login
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public ArrayList getUserModForums(String login) throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_USER_MOD_FORUMS());
		ResultSet rs = null;
		ArrayList userModForums = new ArrayList();

		try {
			st.setString(1, login);
			rs = st.executeQuery();

			while (rs.next()) {
				Forum f = new Forum();
				f.setForumid(rs.getInt("forumid"));
				f.setTitle(rs.getString("forumtitle"));
				userModForums.add(f);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return userModForums;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param fid
	 *            DOCUMENT ME!
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public String insertNewThread(String fid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_INSERT_THREAD());
		try {
			int tid = ((Integer) keyGen
					.generateKey(
							IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_THREAD],
							connection)).intValue();
			st.setInt(1, Integer.parseInt(fid));
			st.setInt(2, tid);
			st.execute();
			return String.valueOf(tid);
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param uid
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public boolean isUserMod(String uid) throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_IS_USER_MOD());
		ResultSet rs = null;
		boolean modFlag = false;

		try {
			st.setString(1, uid);
			rs = st.executeQuery();

			modFlag = rs.next();
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return modFlag;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param fid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void lockForum(String fid, int locked) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_FORUM_LOCKED());
		ResultSet rs = null;
		int id = Integer.parseInt(fid);

		try {
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_SET_FORUM_LOCKED_STATUS());
				st.setInt(1, locked);
				st.setInt(2, id);
				st.execute();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void lockThread(String tid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_THREAD_LOCKED());
		ResultSet rs = null;
		int id = Integer.parseInt(tid);

		try {
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next() && (rs.getInt(1) < 2)) {
				int newStatus = Math.abs(rs.getInt(1) - 1);
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_SET_THREAD_LOCKED_STATUS());
				st.setInt(1, newStatus);
				st.setInt(2, id);
				st.execute();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * @param nfid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void moveThread(String tid, String nfid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_MOVE_THREAD());

		try {
			st.setInt(1, Integer.parseInt(nfid));
			st.setInt(2, Integer.parseInt(tid));
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param forum
	 * @param st
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	private void setLastMessage(Forum forum, PreparedStatement st)
			throws SQLException {
		ResultSet rs = null;

		try {
			st.setInt(1, forum.getForumid());
			rs = (ResultSet) st.executeQuery();

			Message last = new Message();

			if (rs.next()) {
				last.setSender(rs.getString("m_from"));
				last.setIntime(rs.getTimestamp("t_stamp"));
				last.setCentents(rs.getString("cont"));
				last.setThreadSort(rs.getInt("sortby"));
				last.setId(rs.getInt("id"));
				forum.setLastMessThreadId(rs.getInt("tid"));
			}

			forum.setLastMessage(last);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	private void setLastMessage(Topic thread, PreparedStatement st)
			throws SQLException {
		ResultSet rs = null;

		try {
			st.setInt(1, thread.getThreadid());
			rs = (ResultSet) st.executeQuery();

			Message last = new Message();

			if (rs.next()) {
				last.setSender(rs.getString("sender"));
				last.setIntime(rs.getTimestamp("intime"));
				last.setCentents(rs.getString("centents"));
				last.setId(rs.getInt("id"));
			}

			thread.setLastMessage(last);

			// TODO work around bug 863191
			thread.setSubject(getThreadSubject(Integer.toString(thread
					.getThreadid())));
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	private void setRootMessage(LastTopic topic, PreparedStatement st)
			throws SQLException {
		ResultSet rs = null;

		try {
			st.setInt(1, topic.getThreadid());
			rs = (ResultSet) st.executeQuery();

			Message root = new Message();

			if (rs.next()) {
				root.setSender(rs.getString("sender"));
				root.setIntime(rs.getTimestamp("t_stamp"));
				root.setCentents(rs.getString("centents"));
				root.setId(rs.getInt("id"));
				root.setHeading(rs.getString("subject"));
				root.setIp(rs.getString("ip"));
				root.setThreadid(rs.getInt("tid"));
				root.setThreadSort(rs.getInt("sortby"));
			}

			topic.setRootMessage(root);
			topic.setSubject(root.getHeading());
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param messageForm
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void updateMessage(MessageForm messageForm) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_MESSAGE());

		try {
			st.setString(1, messageForm.getText());
			st.setString(2, messageForm.getTitle());
			st.setInt(3, Integer.parseInt(messageForm.getMid()));
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	private void updateThreadIntime(String tid, Timestamp intime)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_THREAD_LINTIME());

		try {
			st.setTimestamp(1, intime);
			st.setInt(2, Integer.parseInt(tid));
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param gid
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public Group getGroupInfo(String gid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_GROUP_INFO());
		ResultSet rs = null;
		Group group = new Group();

		try {
			st.setInt(1, Integer.parseInt(gid));
			rs = st.executeQuery();

			if (rs.next()) {
				group.setName(rs.getString("group_name"));
				group.setSort(rs.getString("group_sort"));
				group.setGroupid(rs.getInt("groupid"));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return group;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void updateGroup(GroupForm form) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_GROUP());

		try {
			st.setString(1, form.getGroup_name());
			st.setString(2, form.getGroup_sort());
			st.setInt(3, Integer.parseInt(form.getGid()));
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void updateForum(ForumForm form) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_FORUM());

		try {
			st.setString(1, form.getForum_name());
			st.setString(2, form.getForum_desc());
			st.setInt(3, Integer.parseInt(form.getGroupid()));
			st.setString(4, form.getForum_sort());
			st.setInt(5, Integer.parseInt(form.getForumid()));
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * @param recordsData
	 * @param showInvisible
	 * 
	 * @throws InstantiationException
	 *             DOCUMENT ME!
	 * @throws IllegalAccessException
	 *             DOCUMENT ME!
	 * @throws InvocationTargetException
	 *             DOCUMENT ME!
	 * @throws NoSuchMethodException
	 *             DOCUMENT ME!
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void processSearch(SearchForm form, RecordsData recordsData,
			boolean showInvisible) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();
		StringBuffer queryString = new StringBuffer();

		if (showInvisible) {
			queryString.append(" WHERE (");
		} else {
			queryString.append(" WHERE jrf_forum.locked < 3 AND (");
		}

		String[] fields = { " centents", " heading" };
		String searchType = (form.getType());
		String search = MySQLCodec.encode(form.getSearch());

		if (searchType.equals(IConst.VALUES.WHOLE)) {
			queryString.append(fields[0]);
			queryString.append(dbDriver.getQueries().getSql_LIKE());
			queryString.append("'%");
			queryString.append(search);
			queryString.append("%'");
			queryString.append(dbDriver.getQueries().getSql_OR());
			queryString.append(fields[1]);
			queryString.append(dbDriver.getQueries().getSql_LIKE());
			queryString.append("'%");
			queryString.append(search);
			queryString.append("%'");
		} else {
			String term = "";

			if (searchType.equals(IConst.VALUES.ALL)) {
				term = dbDriver.getQueries().getSql_AND();
			} else {
				term = dbDriver.getQueries().getSql_OR();
			}

			for (int i = 0; i < fields.length; i++) {
				queryString.append("(");

				StringTokenizer tokens = new StringTokenizer(search, " ");

				while (tokens.hasMoreTokens()) {
					String token = tokens.nextToken();
					queryString.append(fields[i]);
					queryString.append(dbDriver.getQueries().getSql_LIKE());
					queryString.append("'%");
					queryString.append(token);
					queryString.append("%'");

					if (tokens.hasMoreTokens()) {
						queryString.append(term);
					}
				}

				queryString.append(")");

				if (i < (fields.length - 1)) {
					queryString.append(dbDriver.getQueries().getSql_OR());
				}
			}
		}

		queryString.append(")");
		queryString.append(dbDriver.getQueries().getForumQueries()
				.getSql_SEARCH_QUERY_END());

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_SEARCH_QUERY_SUFF()
				+ queryString.toString());
		ResultSet rs = null;

		try {
			rs = st.executeQuery();
			recordsData.fillRecords(rs, Mapping.getInstance().SearchMapping,
					SearchResult.class);
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_SEARCH_QUERY_SUFF_COUNT()
					+ queryString.toString());
			rs = st.executeQuery();

			if (rs.next()) {
				recordsData.setRecordsCount(rs.getInt(1));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * @param i
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void setThreadSortBy(String tid, int i) throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_SET_THREAD_SORT_BY());

		try {
			st.setInt(1, i);
			st.setInt(2, Integer.parseInt(tid));
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param map
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void updateConstants(Map map) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		Set keySet = map.keySet();
		Iterator it = keySet.iterator();

		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_UPDATE_CONSTANTS());

			while (it.hasNext()) {
				String key = (String) it.next();

				st.setString(1, (String) map.get(key));
				st.setString(2, key);
				st.execute();
			}
		} finally {
			if (st != null) {
				st.close();
			}

			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * @param uname
	 *            DOCUMENT ME!
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public ArrayList getSubscribersList(String tid, String uname)
			throws SQLException {
		ArrayList list = new ArrayList();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_SUBSCRIBERS_LIST());
		ResultSet rs = null;

		try {
			st.setInt(1, Integer.parseInt(tid));
			st.setString(2, uname);
			rs = st.executeQuery();

			while (rs.next()) {
				Subscriber s = new Subscriber();
				s.setEmail(rs.getString("user_mail"));
				s.setName(rs.getString("user_name"));
				list.add(s);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return list;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param tid
	 * @param email
	 * @param name
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void subscribe(String tid, String email, String name)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_SUBSCRIPTION());
		ResultSet rs = null;

		try {
			st.setInt(1, Integer.parseInt(tid));
			st.setString(2, email);
			st.setString(3, name);
			rs = st.executeQuery();

			if (!rs.next()) {
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_ADD_SUBSCRIPTION());
				st.setInt(1, Integer.parseInt(tid));
				st.setString(2, email);
				st.setString(3, name);
				st.execute();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param email
	 * @param name
	 * @param tid
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public boolean unsubscribe(String email, String name, String tid)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = null;

		if (Integer.parseInt(tid) > 0) {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_SUBSCRIPTION());
			st.setInt(1, Integer.parseInt(tid));
			st.setString(2, email);
			st.setString(3, name);
		} else {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_SUBSCRIPTION_ALL());
			st.setString(1, email);
			st.setString(2, name);
		}

		ResultSet rs = null;
		boolean success = false;


		try {
			rs = st.executeQuery();

			if (rs.next()) {
				if (Integer.parseInt(tid) > 0) {
					st = connection.prepareStatement(dbDriver.getQueries()
							.getForumQueries().getSql_DELETE_SUBSCRIPTION());
					st.setInt(1, Integer.parseInt(tid));
					st.setString(2, email);
					st.setString(3, name);
				} else {
					st = connection
							.prepareStatement(dbDriver.getQueries()
									.getForumQueries()
									.getSql_DELETE_SUBSCRIPTION_ALL());
					st.setString(1, email);
					st.setString(2, name);
				}

				st.execute();
				success = true;
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return success;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 * @param recordsData
	 * @param block
	 * 
	 * @throws InstantiationException
	 *             DOCUMENT ME!
	 * @throws IllegalAccessException
	 *             DOCUMENT ME!
	 * @throws InvocationTargetException
	 *             DOCUMENT ME!
	 * @throws NoSuchMethodException
	 *             DOCUMENT ME!
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillSubscriptionList(User user, RecordsData recordsData,
			String block) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_COUNT_SUBSCRIPTIONS());
		ResultSet rs = null;

		try {
			st.setString(1, user.getName());
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				int blockSize = user.getSettings().getMes_per_page() * 2;
				int currBlock = Integer.parseInt(block);

				recordsData.setRecordsCount(rs.getInt(1));
				recordsData.setBlockSize(blockSize);
				recordsData.setCurrBlock(currBlock);
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_GET_USER_SUBSCRIPTIONS());
				st.setString(1, user.getName());
				st.setInt(2, currBlock);
				st.setInt(3, dbDriver.getLastRowIdx(currBlock, blockSize));
				rs = st.executeQuery();
				recordsData.fillRecords(rs,
						Mapping.getInstance().SubscriptionMapping,
						NewTopic.class);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param period
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public int[] dropOld(String period) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_OLD_TOPICS());
		ResultSet rs = null;
		int[] wasDeleted = new int[2];

		try {
			st.setInt(1, Integer.parseInt(period));
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				wasDeleted[0]++;
				wasDeleted[1] += rs.getInt("cc");
				this.deleteThread(rs.getString("threadid"), true);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return wasDeleted;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 * @param recordsData
	 * @param block
	 * 
	 * @throws InstantiationException
	 *             DOCUMENT ME!
	 * @throws IllegalAccessException
	 *             DOCUMENT ME!
	 * @throws InvocationTargetException
	 *             DOCUMENT ME!
	 * @throws NoSuchMethodException
	 *             DOCUMENT ME!
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillLastUpdatedTopicList(User user, RecordsData recordsData,
			String block) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = null;

		if (user.getStatus() < 7) {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_LAST_UPDATED_TOPICS());
		} else {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_LAST_UPDATED_TOPICS_ALL());
		}

		ResultSet rs = null;

		try {
			int blockSize = user.getSettings().getMes_per_page() * 2;
			int currBlock = Integer.parseInt(block);
			st.setTimestamp(1, new Timestamp(user.getIntime().getTime()));
			st.setInt(2, currBlock);
			st.setInt(3, dbDriver.getLastRowIdx(currBlock, blockSize));
			rs = (ResultSet) st.executeQuery();
			recordsData.fillRecords(rs, Mapping.getInstance().NewTreadMapping,
					NewTopic.class);
			recordsData.setBlockSize(blockSize);

			recordsData.setCurrBlock(currBlock);
			Iterator it = recordsData.getRecords().iterator();
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_THREAD_LAST_MESS());

			while (it.hasNext()) {
				NewTopic nt = (NewTopic) it.next();
				setLastMessage(nt, st);
				nt.setForumName(getForumInfo(nt.getForumid()).getTitle());
			}

			if (user.getStatus() < 7) {
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_COUNT_NEW_THREADS());
			} else {
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_COUNT_NEW_THREADS_ALL());
			}

			st.setTimestamp(1, new Timestamp(user.getIntime().getTime()));
			rs = (ResultSet) st.executeQuery();
			rs.next();
			recordsData.setRecordsCount(rs.getInt(1));
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 * @param form
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public int getMessBlock(User user, ProcessMessageForm form)
			throws SQLException {
		int block = 0;
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_MESS_INTIME());
		ResultSet rs = null;

		try {
			st.setInt(1, Integer.parseInt(form.getMid()));
			rs = st.executeQuery();

			int position = 0;
			Timestamp intime = null;

			if (rs.next()) {
				intime = rs.getTimestamp(1);
			} else {
				return IConst.VALUES.NOT_EXIST;
			}

			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_GET_MESS_POS_BY_INTIME());
			st.setInt(1, Integer.parseInt(form.getTid()));
			st.setTimestamp(2, intime);
			rs = st.executeQuery();

			if (rs.next()) {
				position = rs.getInt(1);
			}
			int mpp = user.getSettings().getMes_per_page();
			block = (int) (((Math.floor(position / mpp)) * mpp));
			block = (block > 0 && (position % mpp == 0)) ? block - mpp : block;
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return block;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param entrylist
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillEntryList(EntryList entrylist) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_ENTRY_LIST());
		ResultSet rs = null;

		try {
			rs = st.executeQuery();

			while (rs.next()) {
				entrylist.put(new Entry(rs.getString("user_name"), rs
						.getString("sessionid"), rs.getString("ip")));
			}

			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_COUNT_ENTRIES());
			rs = st.executeQuery();

			if (rs.next()) {
				entrylist.setTotal(rs.getInt(1));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param entry
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void updateEntry(Entry entry) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_ENTRY());

		try {
			st.setString(1, entry.getLogin());
			st.setString(2, entry.getSessionId());
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param entry
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void addEntry(Entry entry) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_ADD_ENTRY());

		try {
			int id = ((Integer) keyGen.generateKey(
					IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_WHOIS], connection))
					.intValue();
			;
			st.setString(1, entry.getSessionId());
			st.setString(2, entry.getIp());
			st.setString(3, entry.getLogin());
			st.setInt(4, id);
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void removeEntry(String string) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_DELETE_ENTRY());

		try {
			st.setString(1, string);
			st.setString(2, string);
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public boolean isEntryExist(String string) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_CHECK_ENTRY());
		ResultSet rs = null;
		boolean exist = false;

		try {
			st.setString(1, string);
			rs = rs = st.executeQuery();
			exist = rs.next();
		} finally {
			if (null != rs) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return exist;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public ArrayList getEntryList() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_ENTRY_LIST_FULL());
		ResultSet rs = null;
		ArrayList list = new ArrayList();

		try {
			rs = st.executeQuery();

			while (rs.next()) {
				list.add(new Entry(rs.getString("user_name"), rs
						.getString("sessionid"), rs.getString("ip")));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return list;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param id
	 * @param SkinParams
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void loadSkinParams(int id, HashMap SkinParams) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_SKIN_PARAMS());
		ResultSet rs = null;

		try {
			st.setInt(1, id);
			rs = st.executeQuery();

			while (rs.next()) {
				SkinParams.put(rs.getString("param_name"), rs
						.getString("param_value"));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param form
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void updateStyles(StylesForm form) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_SKIN_PARAM());
		Set keys = form.getKeys();

		try {
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				st.setInt(3, Integer.parseInt(form.getSkinid()));
				st.setString(2, key);
				st.setString(1, (String) form.getValue(key));
				st.executeUpdate();
			}
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * Return List contain UpdatedTopics ids (as String ) for all forums.
	 * 
	 * @param user
	 * @return List of (String) id
	 */
	public List getUpdatedTopics(User user) throws SQLException {
		if (null == user) {
			throw new IllegalArgumentException();
		}

		ArrayList topics = new ArrayList();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_UPDATED_TOPICS());
		ResultSet rs = null;

		try {
			st.setString(1, user.getName());
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				topics.add(rs.getString("tid"));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			st.close();
			connection.close();
		}

		return topics;
	}

	/**
	 * Return List contain UpdatedTopics ids (as String ) for specified forum.
	 * 
	 * @param user
	 * @param fid
	 * @return
	 */
	public List getUpdatedTopics(User user, int fid) throws SQLException {
		if (null == user) {
			throw new IllegalArgumentException();
		}

		ArrayList topics = new ArrayList();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries()
				.getSql_GET_UPDATED_TOPICS_IN_FORUM());
		ResultSet rs = null;

		try {
			st.setString(1, user.getName());
			st.setInt(2, fid);
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				topics.add(rs.getString("tid"));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			st.close();
			connection.close();
		}

		return topics;
	}

	/**
	 * retrive attachment info bean
	 * 
	 * @param id
	 */
	public FileDataInfo getAttachmentInfo(int id) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_ATTACH_INFO());
		ResultSet rs = null;
		FileDataInfo fileData = new FileDataInfo();

		try {
			st.setInt(1, id);
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				fileData.setId(rs.getInt("id"));
				fileData.setName(rs.getString("attach_name"));
				fileData.setContentType(rs.getString("attach_content_type"));
				fileData.setDescription(rs.getString("attach_description"));
				fileData.setMessageId(rs.getInt("message_id"));
				fileData.setSize(rs.getInt("attach_size"));
			}

			return fileData;
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

	}

	/**
	 * @return
	 */
	public List getRankList() throws SQLException {
		ArrayList ranks = new ArrayList();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_RANKS());
		ResultSet rs = null;

		try {
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				ranks.add(new RankInfoDTO(rs.getInt("id"), rs
						.getString("rank_name"), rs.getInt("rank_count")));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			st.close();
			connection.close();
		}

		return ranks;
	}

	/**
	 * 
	 */
	public void removeRank(int id) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_REMOVE_RANK());

		try {
			st.setInt(1, id);
			st.execute();

		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * @param i
	 * @return
	 */
	public RankInfoDTO getRankInfo(int id) throws SQLException {
		RankInfoDTO rank = null;
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_GET_RANK());
		ResultSet rs = null;

		try {
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				rank = new RankInfoDTO(rs.getInt("id"), rs
						.getString("rank_name"), rs.getInt("rank_count"));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			st.close();
			connection.close();
		}

		return rank;
	}

	/**
	 * @param rank
	 */
	public void addRank(RankInfoDTO rank) throws SQLException {
		if (null == rank) {
			throw new IllegalArgumentException("rank can't have null value");
		}
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_ADD_RANK());

		try {
			int id = ((Integer) keyGen.generateKey(
					IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_RANK], connection))
					.intValue();
			st.setInt(1, id);
			st.setInt(2, rank.getCount());
			st.setString(3, rank.getName());
			st.execute();

		} finally {
			st.close();
			connection.close();
		}

	}

	/**
	 * @param rank
	 */
	public void updateRank(RankInfoDTO rank) throws SQLException {
		if (null == rank) {
			throw new IllegalArgumentException("rank can't have null value");
		}
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getForumQueries().getSql_UPDATE_RANK());

		try {
			st.setInt(3, rank.getId());
			st.setInt(1, rank.getCount());
			st.setString(2, rank.getName());
			st.execute();

		} finally {
			st.close();
			connection.close();
		}

	}

	/**
	 * @param user
	 * @param data
	 * @throws SystemException
	 */
	public void saveAvatar(User user, byte[] data) throws SystemException {
		IFileProcessor fProc = getFileProcessor(IFileProcConst.AVATAR_PROCESSOR);
		fProc.saveFileData(data, IFileProcConst.AVATAR_KEY_PREFIX
				+ user.getName());
	}

	/**
	 * @param uid
	 * @return
	 * @throws SystemException
	 */
	public byte[] getAvatar(String uid) throws SystemException {
		IFileProcessor fProc = getFileProcessor(IFileProcConst.AVATAR_PROCESSOR);
		byte[] data = fProc.getFileData(IFileProcConst.AVATAR_KEY_PREFIX + uid);
		if (data == null) {
			data = IConst.VALUES.BLANK_GIF;
		}
		return data;
	}

	/**
	 * @param uid
	 * @throws SystemException
	 */
	public void removeAvatar(String uid) throws SystemException {
		IFileProcessor fProc = getFileProcessor(IFileProcConst.AVATAR_PROCESSOR);
		fProc.removeFileData(IFileProcConst.AVATAR_KEY_PREFIX + uid);
	}
}

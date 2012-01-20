/*
 * $$Id: UserDAO.java,v 1.3 2005/06/07 12:32:29 bel70 Exp $$
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
package org.jresearch.gossip.dao;

import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.am.ban.BanMap;
import org.jresearch.gossip.beans.Ban;
import org.jresearch.gossip.beans.NamedValue;
import org.jresearch.gossip.beans.user.Sender;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.beans.user.UserInfo;
import org.jresearch.gossip.beans.user.UserSettings;
import org.jresearch.gossip.dao.drivers.DbDriver;
import org.jresearch.gossip.dao.drivers.keygen.IKeyGenConst;
import org.jresearch.gossip.dao.drivers.keygen.KeyGenerator;
import org.jresearch.gossip.dao.drivers.keygen.KeyGeneratorFactory;
import org.jresearch.gossip.forms.ListForm;
import org.jresearch.gossip.forms.ProfileForm;
import org.jresearch.gossip.list.Mapping;
import org.jresearch.gossip.list.RecordsData;
import org.jresearch.gossip.util.MD5Digest;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class UserDAO extends DAO {

	private static UserDAO instance;

	private static Object lock = new Object();

	private final DbDriver dbDriver;

	private final KeyGenerator keyGen;

	private UserDAO() {
		try {
			this.dbDriver = DbDriver.getInstance();
			this.keyGen = KeyGeneratorFactory
					.getKeyGenerator(IKeyGenConst.DEFAULT_KEYGEN);
		} catch (SQLException ex) {
			throw new RuntimeException("UserDAO not initialized", ex);
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static UserDAO getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new UserDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param profile
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public boolean addUser(ProfileForm profile) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean notExist = !isUserExist(profile.getLogin());

		try {
			if (notExist) {
				int uid = ((Integer) keyGen.generateKey(
						IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_USER],
						connection)).intValue();
				Calendar cl = Calendar.getInstance();
				cl.set(Integer.parseInt(profile.getDOB_year()), Integer
						.parseInt(profile.getDOB_month()), Integer
						.parseInt(profile.getDOB_day()));
				st = connection.prepareStatement(dbDriver.getQueries()
						.getUserQueries().getSql_ADD_USER());
				st.setString(1, profile.getLogin());
				st.setString(2, MD5Digest.digest(profile.getLogin(), profile
						.getPassword2()));
				st.setString(3, profile.getEmail());
				st.setString(4, profile.getHomePage());
				st.setString(5, profile.getIcq());
				st.setDate(6, new java.sql.Date(cl.getTime().getTime()));
				st.setString(7, profile.getPlace());
				st.setString(8, profile.getOccupation());
				st.setString(9, profile.getSignature());
				st.setInt(10, Integer.parseInt(profile.getMessPerPage()));
				st.setInt(11, Integer.parseInt(profile.getAutoLogin()));
				st.setInt(12, Integer.parseInt(profile.getShowEmail()));
				st.setDate(13, new java.sql.Date(0L));
				st.setInt(14, uid);

				st.executeUpdate();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}

			connection.close();
		}

		return notExist;
	}

	/**
	 * 
	 * @param login
	 * @param email
	 * @param confirmcode
	 * @return
	 * @throws SQLException
	 */
	public boolean addPendingUser(String login, String email, String confirmcode)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean notExist = !isUserExist(login)
				&& !checkPendingUser(login, confirmcode);

		try {
			if (notExist) {
				st = connection.prepareStatement(dbDriver.getQueries()
						.getUserQueries().getSql_ADD_PENDING_USER());
				st.setString(1, login);
				st.setString(2, email);
				st.setString(3, confirmcode);
				st.setTimestamp(4, new java.sql.Timestamp(
						(new java.util.Date()).getTime()));

				st.executeUpdate();
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}

			connection.close();
		}

		return notExist;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param pass
	 *            DOCUMENT ME!
	 * @param login
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void changePassword(String pass, String login) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_CHANGE_PASSWORD());

		try {
			st.setString(1, MD5Digest.digest(login, pass));
			st.setString(2, login);
			st.execute();
		} finally {
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
	public int countUsers() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_COUNT_USERS());
		ResultSet rs = null;
		int count = 0;

		try {
			rs = (ResultSet) st.executeQuery();

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
	 * @param login
	 * @param confirmcode
	 * @return
	 * @throws SQLException
	 */
	public boolean checkPendingUser(String login, String confirmcode)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_CHECK_PENDING_USER());
		ResultSet rs = null;
		boolean success = false;

		try {
			st.setString(1, login);
			st.setString(2, confirmcode);
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
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
	 * @param uid
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void deleteUser(String uid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getUserQueries().getSql_GET_USER_BY_ID());
			st.setInt(1, Integer.parseInt(uid));

			User user = new User();
			fillUser(st, user, false);
			st = connection.prepareStatement(dbDriver.getQueries()
					.getUserQueries().getSql_DELETE_USER());
			st.setInt(1, Integer.parseInt(uid));
			st.execute();
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_CLEAN_USER_MOD());
			st.setString(1, user.getName());
			st.execute();
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_CLEAN_USER_SUBSCR());
			st.setString(1, user.getName());
			st.execute();
			st = connection.prepareStatement(dbDriver.getQueries()
					.getForumQueries().getSql_MARK_USER_MESS());
			st.setString(1, "<" + user.getName() + ">");
			st.setString(2, user.getName());
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * @param login
	 * @return
	 * @throws SQLException
	 */
	public boolean deletePendingUser(String login) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getUserQueries().getSql_DELETE_PENDING_USER());
			st.setString(1, login);
			st.execute();
			return true;
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * @param expirationTimeout
	 * @return
	 * @throws SQLException
	 */
	public boolean deletePendingUser(long expirationTimeout)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(dbDriver.getQueries()
					.getUserQueries().getSql_DELETE_EXPIRED_PENDING_USER());
			st.setTimestamp(1, new java.sql.Timestamp(expirationTimeout));
			st.execute();
			return true;
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param st
	 *            DOCUMENT ME!
	 * @param bean
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillUser(PreparedStatement st, User bean) throws SQLException {
		fillUser(st, bean, true);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param st
	 *            DOCUMENT ME!
	 * @param bean
	 *            DOCUMENT ME!
	 * @param fullinfo
	 *            DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillUser(PreparedStatement st, User bean, boolean fullinfo)
			throws SQLException {
		ResultSet rs = null;

		try {
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				UserInfo info = new UserInfo();
				UserSettings settings = new UserSettings();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("user_name"));
				info.setEmail(rs.getString("user_mail"));
				bean.setStatus(rs.getInt("user_status"));
				bean.setIntime(rs.getTimestamp("last_intime"));

				if (fullinfo) {
					settings.setAutologin(rs.getBoolean("auto_login"));
					bean.setPassword(rs.getString("user_pass"));
					settings.setMes_per_page(rs.getInt("mes_per_page"));
				}

				info.setCity(rs.getString("user_city"));
				info.setIcq(rs.getString("user_icq"));
				info.setBirthday(rs.getDate("user_dob"));
				info.setHomepage(rs.getString("user_hp"));
				info.setOccupation(rs.getString("user_occupation"));
				settings.setShow_user_mail(rs.getBoolean("show_user_mail"));
				settings.setSignature(rs.getString("user_signature"));
				bean.setInfo(info);
				bean.setSettings(settings);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 *            DOCUMENT ME!
	 * @param recordsData
	 * @param dform
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
	public void fillUserList(User user, RecordsData recordsData, ListForm lform)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USERS());
		ResultSet rs = null;

		try {
			int blokSize = user.getSettings().getMes_per_page() * 2;
			int currBlok = Integer.parseInt(lform.getBlock());
			st.setInt(1, currBlok);
			st.setInt(2, dbDriver.getLastRowIdx(currBlok, blokSize));
			rs = (ResultSet) st.executeQuery();
			recordsData.fillRecords(rs, Mapping.getInstance().UserMapping,
					User.class);
			recordsData.setBlockSize(blokSize);
			recordsData.setCurrBlock(currBlok);
			recordsData.setRecordsCount(countUsers());
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}
	}

	public String generatePassword() {
		SecureRandom random = new SecureRandom();
		String charset = IConst.VALUES.PASSWORD_DICTIONARY;
		int length = charset.length();
		StringBuffer newpass = new StringBuffer();

		for (int i = 0; i < IConst.VALUES.DEFAULT_PASSWORD_LENGTH; i++) {
			newpass.append(charset.charAt(random.nextInt(length)));
		}

		return newpass.toString();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param username
	 *            DOCUMENT ME!
	 * @param password
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public User getUser(String username, String password) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER());
		User bean = new User();
		st.setString(1, username);
		st.setString(2, MD5Digest.digest(username, password));

		try {
			fillUser(st, bean);
		} finally {
			st.close();
			connection.close();
		}

		return bean;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public ArrayList getUserEmails() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER_EMAILS());
		ResultSet rs = null;
		ArrayList list = new ArrayList();

		try {
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				NamedValue nv = new NamedValue();
				nv.setName(rs.getString("user_name"));
				nv.setValue(rs.getString("user_mail"));
				list.add(nv);
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
	 * @param username
	 *            DOCUMENT ME!
	 * @param password
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public User getUserEncoded(String username, String password)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER_ENCODED());
		User bean = new User();
		st.setString(1, username);
		st.setString(2, password);

		try {
			fillUser(st, bean);
		} finally {
			st.close();
			connection.close();
		}

		return bean;
	}

	/**
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public User getUser(String username) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER_INFO_FULL());
		User bean = new User();

		try {
			st.setString(1, username);
			fillUser(st, bean);
		} finally {
			st.close();
			connection.close();
		}

		return bean;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param username
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public User getUserInfo(String username) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER_INFO_FULL());
		User bean = new User();

		try {
			st.setString(1, username);
			fillUser(st, bean, false);
		} finally {
			st.close();
			connection.close();
		}

		return bean;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param username
	 *            DOCUMENT ME!
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public Sender getSenderInfo(String username) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER_INFO());
		ResultSet rs = null;
		Sender bean = new Sender();

		try {
			st.setString(1, username);
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				UserInfo info = new UserInfo();
				UserSettings settings = new UserSettings();
				bean.setName(rs.getString("user_name"));
				info.setCity(rs.getString("user_city"));
				settings.setSignature(rs.getString("user_signature"));
				bean.setTotalMess(rs.getInt("tot_mes"));
				bean.setStatus(rs.getInt("user_status"));
				bean.setSettings(settings);
				bean.setInfo(info);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return bean;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param id
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public String getUserName(int id) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER_INFO_BY_ID());
		ResultSet rs = null;
		String name = null;

		try {
			st.setInt(1, id);
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				name = rs.getString("user_name");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return name;
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
	public boolean isUserExist(String login) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_CHECK_USER());
		ResultSet rs = null;
		boolean success = false;

		try {
			st.setString(1, login);
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
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
	 * @param email
	 * @param login
	 *            DOCUMENT ME!
	 * 
	 * @return
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public User setNewPassword(String email, String login) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_CHECK_USER_WITH_EMAIL());
		ResultSet rs = null;
		String newpass = null;
		User user = new User();

		try {
			st.setString(1, email);
			st.setString(2, login);
			rs = (ResultSet) st.executeQuery();
			fillUser(st, user, false);

			if (user.getStatus() > 0) {
				newpass = generatePassword();
				st = connection.prepareStatement(dbDriver.getQueries()
						.getUserQueries().getSql_CHANGE_PASSWORD());
				st.setString(1, MD5Digest.digest(login, newpass));
				st.setString(2, login);
				st.execute();
				user.setPassword(newpass);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return user;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param login
	 * @param status
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void setUserStatus(String login, int status) throws SQLException {
		Connection connection = this.dataSource.getConnection();

		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_UPDATE_USER_STATUS());

		try {
			st.setInt(1, status);
			st.setString(2, login);
			st.execute();

			if (status < 7) {
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_CLEAN_USER_MOD());
				st.setString(1, login);
				st.execute();
			}
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
	public User getUserInfoShort(int uid) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_USER_INFO_BY_ID());
		ResultSet rs = null;
		User bean = new User();

		try {
			st.setInt(1, uid);
			rs = st.executeQuery();

			if (rs.next()) {
				bean.setName(rs.getString("user_name"));
				bean.setStatus(rs.getInt("user_status"));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return bean;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param profile
	 *            DOCUMENT ME!
	 * @param login
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public boolean updateUser(ProfileForm profile, String login)
			throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_CHECK_USER());
		ResultSet rs = null;

		try {
			st.setString(1, profile.getLogin());
			rs = (ResultSet) st.executeQuery();

			if (rs.next()) {
				return false;
			} else {
				Calendar cl = Calendar.getInstance();
				cl.set(Integer.parseInt(profile.getDOB_year()), Integer
						.parseInt(profile.getDOB_month()), Integer
						.parseInt(profile.getDOB_day()));
				st = connection.prepareStatement(dbDriver.getQueries()
						.getUserQueries().getSql_UPDATE_USER());

				st.setString(1, profile.getEmail());
				st.setString(2, profile.getHomePage());
				st.setString(3, profile.getIcq());
				st.setDate(4, new java.sql.Date(cl.getTime().getTime()));
				st.setString(5, profile.getPlace());
				st.setString(6, profile.getOccupation());
				st.setString(7, profile.getSignature());
				st.setInt(8, Integer.parseInt(profile.getMessPerPage()));
				st.setInt(9, Integer.parseInt(profile.getAutoLogin()));
				st.setInt(10, Integer.parseInt(profile.getShowEmail()));
				st.setString(11, login);

				st.execute();
				st = connection.prepareStatement(dbDriver.getQueries()
						.getForumQueries().getSql_UPDATE_SUBSCRIBE());
				st.setString(1, profile.getEmail());
				st.setString(2, login);
				st.execute();

				return true;
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
	 * @param login
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void updateIntime(String login) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_SET_LAST_INTIME());

		try {
			st.setString(1, login);
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}

	/**
	 * @param banMap
	 * @throws SQLException
	 */
	public void fillBanMap(BanMap banMap) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_GET_BAN_MAP());
		ResultSet rs = null;
		try {
			rs = (ResultSet) st.executeQuery();

			while (rs.next()) {
				banMap.add(rs.getString("ban_mask"), rs.getInt("type_id"));
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
	 * @param ban
	 */
	public void deleteBan(Ban ban) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getSql_DELETE_BAN());

		try {
			st.setInt(1, ban.getType());
			st.setString(2, ban.getMask());
			st.execute();
		} finally {
			st.close();
			connection.close();
		}

	}

	/**
	 * @param ban
	 */
	public void addBan(Ban ban) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(dbDriver
				.getQueries().getUserQueries().getADD_BAN());

		try {
			st.setInt(1, ban.getType());
			st.setString(2, ban.getMask());
			st.execute();
		} finally {
			st.close();
			connection.close();
		}
	}
}
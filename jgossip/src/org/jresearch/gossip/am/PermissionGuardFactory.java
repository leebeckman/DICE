/*
 * Created on 26.07.2004
 *
 */
package org.jresearch.gossip.am;

import java.sql.SQLException;

import org.jresearch.gossip.am.constants.Permissions;
import org.jresearch.gossip.am.model.IPermissionGuard;
import org.jresearch.gossip.am.model.IPermissionGuardFactory;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;

/**
 * @author dbelov
 * 
 */
public class PermissionGuardFactory implements IPermissionGuardFactory {

	public final static int GUEST_ID = 0;

	private static PermissionGuardFactory instance;

	private ForumDAO forumDAO;

	private UserDAO userDAO;

	private static Object lock = new Object();

	private PermissionGuardFactory() {
		this.forumDAO = ForumDAO.getInstance();
		this.userDAO = UserDAO.getInstance();
	}

	public static PermissionGuardFactory getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new PermissionGuardFactory();
				}
			}
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.am.model.IPermissionGuardFactory#createGuard(int)
	 */
	public IPermissionGuard createGuard(int userId) throws SystemException {
		try {
			int status = UserStatus.GUEST;
			if (userId != GUEST_ID) {
				User user = userDAO.getUserInfoShort(userId);
				status = user.getStatus();
			}
			PermissionGuard pg = new PermissionGuard(Permissions.getInstance()
					.getPermissions(status));
			return pg;
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.am.model.IPermissionGuardFactory#createGuard(java.lang.String)
	 */
	public IPermissionGuard createGuard(String login) throws SystemException {
		User user;
		try {
			int id = GUEST_ID;
			if (login != null) {
				user = userDAO.getUserInfo(login);

				if (user != null) {
					id = user.getId();
				}
			}
			return createGuard(id);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	public IPermissionGuard getGuardForStatus(int status)
			throws SystemException {

		PermissionGuard pg = new PermissionGuard(Permissions.getInstance()
				.getPermissions(status));
		return pg;

	}

}
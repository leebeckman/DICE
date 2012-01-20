/*
 * $$Id: AutoLoginFilter.java,v 1.3 2005/06/07 12:32:26 bel70 Exp $$
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
 * Created on Oct 22, 2003
 *
 */
package org.jresearch.gossip.filters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.singlesign.IUser;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class AutoLoginFilter implements Filter {

	private String _editInfo;

	private String _userRole;

	private FilterConfig _config;

	private static final String SAVE_PROFILE_ACTION_PATH = "/SaveProfile.do";

	private HashSet _publicUris = new HashSet();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		_config = config;

		/* editInfo action */
		_editInfo = config.getInitParameter("editInfo.action");

		/* JAAS user role */
		_userRole = config.getInitParameter("JAAS.role");

		/* Public Uri */
		String uri = config.getServletContext().getInitParameter("public.uri");
		StringTokenizer tok = new StringTokenizer(uri, ",");

		while (tok.hasMoreTokens()) {
			String url = tok.nextToken().trim();
			_publicUris.add(url);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		try {
			/* Uri */
			String uri = request.getRequestURI();

			int i = uri.lastIndexOf("/");

			if (i >= 0) {
				uri = uri.substring(i + 1);
			}

			if (isPublic(uri)) {// do not add user is session for public screen
				_config.getServletContext().getRequestDispatcher(
						request.getServletPath()).forward(req, res);
			} else {
				// setting up start time for this request processing
				req.setAttribute(IConst.REQUEST.START_TIME_KEY, new Date());

				HttpSession session = request.getSession();
				ServletContext application = session.getServletContext();

				// setting up max inactive interval for current session
				int inactiveInterval;

				inactiveInterval = Integer.parseInt(Configurator.getInstance()
						.get(IConst.CONFIG.SESSION_LENGTH));

				if (session.getMaxInactiveInterval() != inactiveInterval) {
					session.setMaxInactiveInterval(inactiveInterval);
				}

				// try to get user from session
				User user = (User) session
						.getAttribute(IConst.SESSION.USER_KEY);

				if (user != null) {
					// check that this user(or session) was not kicked by admin
					ForumDAO dao = ForumDAO.getInstance();

					try {

						if (!dao.isEntryExist(session.getId())) {
							application.log("AutoLoginFilter: Entry for login="
									+ user.getName()
									+ " not found - User is removed  ");
							session.removeAttribute(IConst.SESSION.USER_KEY);
							user = null;
						}
					} catch (SQLException sqle) {
						throw new ServletException(sqle);
					}
				}

				if ((user == null)
						|| (IConst.VALUES.TRUE.equals(Configurator
								.getInstance().get(
										IConst.CONFIG.ENABLE_EXT_SIGN_ON)) && (user
								.getStatus() == 0))) {
					if (IConst.VALUES.TRUE.equals(Configurator.getInstance()
							.get(IConst.CONFIG.ENABLE_AUTO_LOGIN))
							&& (user == null)) {
						// try to get user from autologin cookie
						user = getUserFromCookie(request);
						application
								.log("AutoLoginFilter: try to get user from autologin cookie");
					}

					if (IConst.VALUES.TRUE.equals(Configurator.getInstance()
							.get(IConst.CONFIG.ENABLE_EXT_SIGN_ON))
							&& ((user == null) || (user.getStatus() == 0))) {
						// try to get external user from session
						application
								.log("AutoLoginFilter: try to get external user ");
						user = getExternalUser(request);
					}

					if (user == null) {
						// set empty user for guest's logon
						application
								.log("AutoLoginFilter: user isn't loaded ,so sign on as guest ");

						user = new User(req.getRemoteAddr());
					}

					// save user bean in session
					session.setAttribute(IConst.SESSION.USER_KEY, user);
				}

				if (IConst.VALUES.TRUE.equals(Configurator.getInstance().get(
						IConst.CONFIG.ENABLE_EXT_SIGN_ON))
						&& (user.getName() != null)
						&& (user.getInfo().getEmail() == null)) {
					// forward to editInfo action if external user have not
					// filled info
					if (request.getRequestURI().indexOf(
							SAVE_PROFILE_ACTION_PATH) < 0) {
						application
								.log("AutoLoginFilter: info is empty for external user with login="
										+ user.getName()
										+ ", so forward to "
										+ _editInfo);

						_config.getServletContext().getRequestDispatcher(
								_editInfo).forward(req, res);
					}
				}
				chain.doFilter(req, res);
			}

		} catch (NumberFormatException e) {
			throw new ServletException(e);
		} catch (ConfiguratorException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param request
	 * 
	 * @return
	 * @throws ServletException
	 *             DOCUMENT ME!
	 */
	private User getExternalUser(HttpServletRequest request)
			throws ServletException {
		String extUserLogin = null;
		User user = null;
		ServletContext application = request.getSession().getServletContext();

		// try to get ext user from session
		IUser extUser = (IUser) request.getSession().getAttribute(
				IConst.SESSION.EXT_USER_KEY);

		if (extUser != null) {
			extUserLogin = extUser.getName();
		} else {
			// try to get user login from JAAS
			if ((request.getUserPrincipal() != null)
					&& ((_userRole == null) || request.isUserInRole(_userRole))) {
				extUserLogin = request.getUserPrincipal().getName();
			}
		}

		application.log("AutoLoginFilter: external user login=" + extUserLogin);

		if (extUserLogin != null) {
			UserDAO dao = UserDAO.getInstance();

			try {

				if (!dao.isUserExist(extUserLogin)) {
					// create empty user with status=1 and name=extUserLogin
					user = new User(request.getRemoteAddr());
					user.setName(extUserLogin);
					user.setStatus(UserStatus.USER);
				} else {
					user = dao.getUser(extUserLogin);
					user.setIp(request.getRemoteAddr());
				}

				application
						.log("AutoLoginFilter: external user is loaded with login="
								+ user.getName());
			} catch (SQLException sqle) {
				throw new ServletException(sqle);
			}
		}

		// TODO fill user info and save user in forum db
		return user;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param cookies
	 * @param name
	 * 
	 * @return
	 */
	private String getCookieValue(Cookie[] cookies, String name) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];

				if (name.equals(c.getName())) {
					return c.getValue();
				}
			}
		}

		return null;
	}

	private User getUserFromCookie(HttpServletRequest request)
			throws ServletException {
		Cookie[] cookies = request.getCookies();
		String userCookie = getCookieValue(cookies, IConst.COOKIE.USER_COOKIE);

		User user = null;

		if ((userCookie != null)) {
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			UserDAO dao = UserDAO.getInstance();
			StringTokenizer login = new StringTokenizer(userCookie, "*");

			if (login.countTokens() >= 2) {
				try {
					user = dao.getUserEncoded(login.nextToken(), login
							.nextToken());
				} catch (SQLException sqle) {
					throw new ServletException(sqle);
				}
			}

			if ((user != null) && user.getSettings().isAutologin()) {
				user.setIp(request.getRemoteAddr());
				application
						.log("AutoLoginFilter: user from cookie is loaded with login="
								+ user.getName());
			} else {
				return null;
			}
		}

		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param uri
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean isPublic(String uri) {
		return _publicUris.contains(uri);
	}
}
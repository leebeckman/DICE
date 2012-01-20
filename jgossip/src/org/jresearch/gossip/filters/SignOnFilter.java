/*
 * $$Id: SignOnFilter.java,v 1.3 2005/06/07 12:32:26 bel70 Exp $$
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
 * Created on 26-Feb-2003
 */
package org.jresearch.gossip.filters;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.am.StrutsConfigurationHelperAction;
import org.jresearch.gossip.am.StrutsPermissionMapping;
import org.jresearch.gossip.am.model.IPermissionGuard;
import org.jresearch.gossip.am.values.PermissionPoint;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.LogLevel;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * This filter protects some URI and make sure that only signed-on users can
 * access them
 */
public class SignOnFilter implements Filter {

	private String _signon;

	private String _denied;

	private FilterConfig _config;

	private static HashMap permissionMapping;

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		_config = config;

		/* SignOn action */
		_signon = config.getInitParameter("signon.action");

		_denied = config.getInitParameter("denied.action");
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		try {
			if (checkPermission(req)) {
				chain.doFilter(request, response);
			} else {
				HttpSession session = req.getSession();
				JGossipLog.audit(LogLevel.WARN, (User) session
						.getAttribute(IConst.SESSION.USER_KEY),
						" attempted to access " + req.getRequestURI(), session);
				if (!isSignedIn(req.getSession())) {
					String uri = req.getRequestURI();
					int i = uri.lastIndexOf("/");
					if (i >= 0) {
						uri = uri.substring(i);
					}
					request.setAttribute(IConst.REQUEST.REDIRECT_URL, uri
							+ ((req.getQueryString() != null) ? ("?" + req
									.getQueryString()) : ""));
					_config.getServletContext().getRequestDispatcher(
							getSignOnUrl()).forward(request, response);

				} else {
					_config.getServletContext().getRequestDispatcher(_denied)
							.forward(request, response);
				}
			}
		} catch (SystemException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @param req
	 * @return
	 */
	private String getPath(HttpServletRequest req) {
		String uri = req.getRequestURI();

		int i = uri.lastIndexOf("/");
		int j = uri.lastIndexOf(".do");
		if (i >= 0 && j > 0) {
			uri = uri.substring(i, j);
		}
		req.getSession().getServletContext().log(uri);
		return uri;
	}

	/**
	 * @return
	 * @throws ServletException
	 */
	private String getSignOnUrl() throws ServletException {
		try {
			if (IConst.VALUES.FALSE.equals(Configurator.getInstance().get(
					IConst.CONFIG.ENABLE_FORUM_SIGN_ON))) {
				return Configurator.getInstance().get(
						IConst.CONFIG.EXT_LOGON_ACTION_URL);
			}
		} catch (ConfiguratorException e) {
			throw new ServletException(e);
		}

		return _signon;
	}

	/**
	 * @param request
	 * @return
	 * @throws SystemException
	 */
	private boolean checkPermission(HttpServletRequest request)
			throws SystemException {
		IPermissionGuard guard = getGuard(request);
		PermissionPoint point = getPermissionPoint(request);

		if (point == null) {
			return true;
		}
		return guard.checkPermission(point);
	}

	/**
	 * @param request
	 * @return
	 * @throws SystemException
	 */
	private IPermissionGuard getGuard(HttpServletRequest request)
			throws SystemException {

		HttpSession session = request.getSession();
		IPermissionGuard guard = (IPermissionGuard) session
				.getAttribute(IConst.SESSION.PERMISSION_GUARD_KEY);
		return guard;
	}

	/**
	 * @param session
	 * @return
	 */
	private boolean isSignedIn(HttpSession session) {
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		if (user.getStatus() != UserStatus.GUEST) {
			return true;
		}

		return false;
	}

	/**
	 * @param request
	 * @return
	 * @throws ConfiguratorException
	 */
	private PermissionPoint getPermissionPoint(HttpServletRequest request)
			throws SystemException {
		if (permissionMapping == null) {
			permissionMapping = StrutsConfigurationHelperAction
					.retrieveStrutsActionMapping(request.getSession()
							.getServletContext());
		}
		StrutsPermissionMapping spm = (StrutsPermissionMapping) permissionMapping
				.get(getPath(request));
		PermissionPoint point = null;
		if (spm != null) {
			point = new PermissionPoint(spm.getAmObjectId().intValue(), spm
					.getAmOperationId().intValue());
		}
		return point;
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

}
/*
 * $Id: BanFilter.java,v 1.3 2005/06/07 12:32:26 bel70 Exp $
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
 *              Dmitriy Belov <bel@jresearch.org>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 29.08.2004
 *
 */
package org.jresearch.gossip.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.am.ban.BanGuard;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.constants.BanType;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.LogLevel;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * @author Dmitry Belov
 * 
 */
public class BanFilter implements Filter {

	private FilterConfig _config;

	private String _denied;

	private String _signon;

	private String _suri;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		_config = config;
		_denied = config.getInitParameter("denied.action");
		_signon = config.getInitParameter("signon.action");

		int i = _signon.lastIndexOf("/");
		_suri = _signon;
		if (i >= 0) {
			_suri = _suri.substring(i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		try {
			BanGuard guard = BanGuard.getInstance();
			String forward = "";
			if (guard.checkBan(req.getRemoteAddr(), BanType.IP)) {
				forward = _denied;
			} else if (guard.checkBan(user.getName(), BanType.LOGIN)) {
				String uri = req.getRequestURI();
				int i = uri.lastIndexOf("/");
				if (i >= 0) {
					uri = uri.substring(i);
				}

				if (_suri.equals(uri)) {
					forward = _signon;
				} else {
					forward = _denied;
				}

			}
			if (!forward.equals("")) {
				JGossipLog.audit(LogLevel.WARN, (User) session
						.getAttribute(IConst.SESSION.USER_KEY),
						" access denied according ban rules "
								+ req.getRequestURI(), session);
				_config.getServletContext().getRequestDispatcher(forward)
						.forward(request, response);
			}
		} catch (SystemException e) {
			throw new ServletException(e);
		}
		chain.doFilter(request, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

}
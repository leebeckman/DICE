/*
 * $$Id: BaseAction.java,v 1.3 2005/06/07 12:32:34 bel70 Exp $$
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
 * Created on 09.05.2003
 *
 */
package org.jresearch.gossip.actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.am.StrutsConfigurationHelperAction;
import org.jresearch.gossip.am.StrutsPermissionMapping;
import org.jresearch.gossip.am.model.IPermissionGuard;
import org.jresearch.gossip.am.values.PermissionPoint;
import org.jresearch.gossip.beans.user.EntryList;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.JGossipException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.LogLevel;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public abstract class BaseAction extends Action {

	/**
	 * DOCUMENT ME!
	 * 
	 * @param request
	 *            DOCUMENT ME!
	 * @param mess
	 *            DOCUMENT ME!
	 * @param append
	 *            DOCUMENT ME!
	 */
	public void log(HttpServletRequest request, String mess, String append) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		MessageResources messages = getResources(request);

		StringBuffer sb = new StringBuffer();
		sb.append(messages.getMessage(mess));
		sb.append(" ");
		sb.append(append);

		JGossipLog.audit(LogLevel.INFO, user, sb.toString(), session);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param session
	 *            DOCUMENT ME!
	 * @param key
	 *            DOCUMENT ME!
	 * @param arg0
	 *            DOCUMENT ME!
	 * @param arg1
	 *            DOCUMENT ME!
	 */
	public void setStatusMessage(HttpServletRequest request, String key,
			String arg0, String arg1) {

		MessageResources messages = getResources(request);
		String message = messages.getMessage(super.getLocale(request), key,
				arg0, arg1);
		request.getSession().setAttribute(IConst.SESSION.STATUS_MESSAGE,
				message);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param session
	 *            DOCUMENT ME!
	 * @param key
	 *            DOCUMENT ME!
	 * @param arg0
	 *            DOCUMENT ME!
	 */
	public void setStatusMessage(HttpServletRequest request, String key,
			String arg0) {
		setStatusMessage(request, key, arg0, "");
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param session
	 *            DOCUMENT ME!
	 * @param key
	 *            DOCUMENT ME!
	 */
	public void setStatusMessage(HttpServletRequest request, String key) {
		setStatusMessage(request, key, "");
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param request
	 *            DOCUMENT ME!
	 * @param mess
	 *            DOCUMENT ME!
	 */
	public void log(HttpServletRequest request, String mess) {
		log(request, mess, "");
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param form
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * @param response
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!haveAccess(request, mapping)) {
			return (mapping.findForward(IConst.TOKEN.DENIED));
		}
		HttpSession session = request.getSession();
		MessageResources messages = getResources(request);

		ActionForward forward = this.process(mapping, form, request, response);
		afterProcess(mapping, form, request, response);
		request.setAttribute(IConst.REQUEST.REQUEST_URI, request
				.getRequestURI());

		ForumDAO forumDAO = ForumDAO.getInstance();

		if (session.getAttribute(IConst.SESSION.LAST_UPDATE_DATE) == null) {
			session.setAttribute(IConst.SESSION.LAST_UPDATE_DATE, new Date());
		}

		try {

			EntryList elist = new EntryList();
			forumDAO.fillEntryList(elist);
			request.setAttribute(IConst.REQUEST.ENTRY_LIST, elist);

			if ((session.getAttribute(IConst.SESSION.GROUPS_KEY) == null)
					|| (((Date) session
							.getAttribute(IConst.SESSION.LAST_UPDATE_DATE))
							.before((Date) getServlet().getServletContext()
									.getAttribute(
											IConst.CONTEXT.LAST_UPDATE_DATE)))) {
				User user = (User) session
						.getAttribute(IConst.SESSION.USER_KEY);
				session.setAttribute(IConst.SESSION.GROUPS_KEY, forumDAO
						.getGroups(user.getStatus(), false));
				session.setAttribute(IConst.SESSION.LAST_UPDATE_DATE,
						new Date());
			}
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		}

		return (forward);
	}

	/**
	 * @param request
	 * @param mapping
	 * @return
	 * @throws SystemException
	 */
	private boolean haveAccess(HttpServletRequest request, ActionMapping mapping)
			throws SystemException {
		HttpSession session = request.getSession();
		if (session.getAttribute(IConst.SESSION.PERMISSION_GUARD_KEY) == null) {
			throw new SystemException("PERMISSION_GUARD not found in session");
		}
		IPermissionGuard guard = (IPermissionGuard) session
				.getAttribute(IConst.SESSION.PERMISSION_GUARD_KEY);
		HashMap permissionMapping = StrutsConfigurationHelperAction
				.retrieveStrutsActionMapping(request.getSession()
						.getServletContext());
		StrutsPermissionMapping spm = (StrutsPermissionMapping) permissionMapping
				.get(mapping.getPath());
		if (spm != null) {
			PermissionPoint point = new PermissionPoint(spm.getAmObjectId()
					.intValue(), spm.getAmOperationId().intValue());
			return guard.checkPermission(point);
		}
		return true;

	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param form
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * @param response
	 *            DOCUMENT ME!
	 */
	protected void afterProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param form
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * @param response
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * @throws SystemException
	 */
	protected abstract ActionForward process(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws JGossipException;

}
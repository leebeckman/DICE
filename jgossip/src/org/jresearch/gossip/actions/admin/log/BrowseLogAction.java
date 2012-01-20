/*
 * $$Id: BrowseLogAction.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $$
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
 * Created on 13.08.2004
 *
 */
package org.jresearch.gossip.actions.admin.log;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.LogSearchCriteria;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.JGossipException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.ListForm;
import org.jresearch.gossip.list.RecordsData;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * @author dbelov
 * 
 */
public class BrowseLogAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.BaseAction#process(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JGossipException {
		ForumDAO dao = ForumDAO.getInstance();
		HttpSession session = request.getSession();
		ListForm lForm = (ListForm) form;
		try {
			RecordsData records = new RecordsData();

			User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
			records.setBlockSize((null != user) ? user.getSettings()
					.getMes_per_page() : 25);
			if (session.getAttribute(IConst.SESSION.SEARCH_CRITERIA_LOG) == null) {
				session.setAttribute(IConst.SESSION.SEARCH_CRITERIA_LOG,
						new LogSearchCriteria());
			}
			LogSearchCriteria criteria = (LogSearchCriteria) session
					.getAttribute(IConst.SESSION.SEARCH_CRITERIA_LOG);
			int block = Integer.parseInt(lForm.getBlock());
			dao.fillLogEntryList(criteria, records, block);
			request.setAttribute(IConst.REQUEST.RECORDS_DATA, records);
			ArrayIterator it = new ArrayIterator();
			it.setArray(JGossipLog.PRIORITIES);
			request.setAttribute("log_level", it);
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		} catch (InstantiationException e) {
			throw new SystemException(e);
		} catch (IllegalAccessException e) {
			throw new SystemException(e);
		} catch (InvocationTargetException e) {
			throw new SystemException(e);
		} catch (NoSuchMethodException e) {
			throw new SystemException(e);
		}

		return (mapping.findForward(IConst.TOKEN.PAGE));
	}

}
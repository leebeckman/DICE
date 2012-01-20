/*
 * $Id: ExceptionHandlerAction.java,v 1.3 2005/06/07 12:32:34 bel70 Exp $
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
 * Created on 31.05.2004
 *
 */
package org.jresearch.gossip.actions;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * @author Dmitry Belov
 * 
 */
public class ExceptionHandlerAction extends ExceptionHandler {

	private Logger log;

	/**
	 * Default c'tor.
	 */
	public ExceptionHandlerAction() {
		super();
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception,
	 *      org.apache.struts.config.ExceptionConfig,
	 *      org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(Exception ex, ExceptionConfig econf,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ActionForward forward = super.execute(ex, econf, mapping, formInstance,
				request, response);
		StringBuffer message = new StringBuffer();
		if (ex instanceof SystemException) {
			SystemException se = (SystemException) ex;
			if (se.getNestedException() == null) {
				message.append(se.getTrace());
			} else {
				Exception e = se.getNestedException();
				message.append(e.getMessage());
				message.append("\n");
				message.append(se.getTrace());
			}
		} else {
			message.append(ex.getMessage());
			ex.printStackTrace();
		}
		logException(ex);
		request.setAttribute(IConst.CONTEXT.SYSTEM_EXCEPTION_MESSAGE, message
				.toString());
		return forward;
	}

	protected void logException(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		log.error(sw.toString());
	}
}
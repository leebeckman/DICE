/*
 * $Id: LogSearchCriteria.java,v 1.3 2005/06/07 12:32:26 bel70 Exp $
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
 * Created on 12.08.2004
 *
 */
package org.jresearch.gossip.beans;

import java.util.Date;

import org.jresearch.gossip.IConst;

/**
 * @author Dmitry Belov
 * 
 */
public class LogSearchCriteria {

	private Date from;

	private Date to;

	private String logger;

	private String log_level;

	private String remote_ip;

	private String user_name;

	private String session_id;

	/**
	 * Sort direction:
	 * <li>FALSE - reversed order - oldest data first
	 * <li>TRUE - normal order - newest data first.
	 */
	private boolean sortOrder;

	/**
	 * @param from
	 * @param to
	 * @param logger
	 * @param log_level
	 * @param remote_ip
	 * @param user_name
	 * @param session_id
	 * @deprecated - Use LogSearchCriteria(Date, Date, String, String, String,
	 *             String, String, boolean)
	 */
	public LogSearchCriteria(Date from, Date to, String logger,
			String log_level, String remote_ip, String user_name,
			String session_id) {
		super();
		this.from = from;
		this.to = to;
		this.logger = logger;
		this.log_level = log_level;
		this.remote_ip = remote_ip;
		this.user_name = user_name;
		this.session_id = session_id;
		this.sortOrder = true;
	}

	/**
	 * Document me!
	 * 
	 * @param from
	 * @param to
	 * @param logger
	 * @param log_level
	 * @param remote_ip
	 * @param user_name
	 * @param session_id
	 * @param sortOrder
	 */
	public LogSearchCriteria(Date from, Date to, String logger,
			String log_level, String remote_ip, String user_name,
			String session_id, boolean sortOrder) {
		super();
		this.from = from;
		this.to = to;
		this.logger = logger;
		this.log_level = log_level;
		this.remote_ip = remote_ip;
		this.user_name = user_name;
		this.session_id = session_id;
		this.sortOrder = sortOrder;
	}

	/**
	 * 
	 */
	public LogSearchCriteria() {
		this.from = new Date(0);
		this.to = new Date();
		this.logger = IConst.VALUES.ALL;
		this.log_level = IConst.VALUES.ALL;
		this.remote_ip = IConst.VALUES.ALL;
		this.user_name = IConst.VALUES.ALL;
		this.session_id = IConst.VALUES.ALL;
		this.sortOrder = true;
	}

	/**
	 * @return Returns the from.
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            The from to set.
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * @return Returns the log_level.
	 */
	public String getLog_level() {
		return log_level;
	}

	/**
	 * @param log_level
	 *            The log_level to set.
	 */
	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}

	/**
	 * @return Returns the logger.
	 */
	public String getLogger() {
		return logger;
	}

	/**
	 * @param logger
	 *            The logger to set.
	 */
	public void setLogger(String logger) {
		this.logger = logger;
	}

	/**
	 * @return Returns the remote_ip.
	 */
	public String getRemote_ip() {
		return remote_ip;
	}

	/**
	 * @param remote_ip
	 *            The remote_ip to set.
	 */
	public void setRemote_ip(String remote_ip) {
		this.remote_ip = remote_ip;
	}

	/**
	 * @return Returns the session_id.
	 */
	public String getSession_id() {
		return session_id;
	}

	/**
	 * @param session_id
	 *            The session_id to set.
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	/**
	 * @return Returns the to.
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * @param to
	 *            The to to set.
	 */
	public void setTo(Date to) {
		this.to = to;
	}

	/**
	 * @return Returns the user_name.
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            The user_name to set.
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return Returns the sortOrder.
	 */
	public boolean isSortOrder() {
		return sortOrder;
	}
}
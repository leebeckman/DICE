/*
 * $Id: LogEntry.java,v 1.3 2005/06/07 12:32:26 bel70 Exp $
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

/**
 * @author Dmitry Belov
 * 
 */
public class LogEntry {

	private String log_date;

	private String logger;

	private String log_level;

	private String message;

	private String remote_ip;

	private String user_name;

	private String session_id;

	/**
	 * @param log_date
	 * @param logger
	 * @param log_level
	 * @param message
	 * @param remote_ip
	 * @param user_name
	 * @param session_id
	 */
	public LogEntry(String log_date, String logger, String log_level,
			String message, String remote_ip, String user_name,
			String session_id) {
		super();
		this.log_date = log_date;
		this.logger = logger;
		this.log_level = log_level;
		this.message = message;
		this.remote_ip = remote_ip;
		this.user_name = user_name;
		this.session_id = session_id;
	}

	/**
	 * 
	 */
	public LogEntry() {

	}

	/**
	 * @param log_date
	 *            The log_date to set.
	 */
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}

	/**
	 * @param log_level
	 *            The log_level to set.
	 */
	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}

	/**
	 * @param logger
	 *            The logger to set.
	 */
	public void setLogger(String logger) {
		this.logger = logger;
	}

	/**
	 * @param message
	 *            The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param remote_ip
	 *            The remote_ip to set.
	 */
	public void setRemote_ip(String remote_ip) {
		this.remote_ip = remote_ip;
	}

	/**
	 * @param session_id
	 *            The session_id to set.
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	/**
	 * @param user_name
	 *            The user_name to set.
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return Returns the log_date.
	 */
	public String getLog_date() {
		return log_date;
	}

	/**
	 * @return Returns the log_level.
	 */
	public String getLog_level() {
		return log_level;
	}

	/**
	 * @return Returns the logger.
	 */
	public String getLogger() {
		return logger;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return Returns the remote_ip.
	 */
	public String getRemote_ip() {
		return remote_ip;
	}

	/**
	 * @return Returns the session_id.
	 */
	public String getSession_id() {
		return session_id;
	}

	/**
	 * @return Returns the user_name.
	 */
	public String getUser_name() {
		return user_name;
	}

}
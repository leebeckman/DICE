/*
 * $$Id: SearchLogForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $$
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
package org.jresearch.gossip.forms;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.LogSearchCriteria;

/**
 * @author dbelov
 * 
 */
public class SearchLogForm extends ListForm {

	private String from_d;

	private String from_M;

	private String from_y;

	private String from_H;

	private String from_m;

	private String to_d;

	private String to_M;

	private String to_y;

	private String to_H;

	private String to_m;

	private String logger;

	private String log_level;

	private String remote_ip;

	private String user_name;

	private String session_id;

	private TimeZone tzone = TimeZone.getTimeZone(IConst.VALUES.GMT);

	/**
	 * 
	 */
	public SearchLogForm() {
		setFields();
	}

	/**
	 * 
	 */
	private void setFields() {

		Calendar cl = Calendar.getInstance(this.tzone);
		cl.setTime(new Date(0));

		this.from_d = Integer.toString(cl.get(Calendar.DATE));
		this.from_M = Integer.toString(cl.get(Calendar.MONTH));
		this.from_y = Integer.toString(cl.get(Calendar.YEAR));
		this.from_H = Integer.toString(cl.get(Calendar.HOUR_OF_DAY));
		this.from_m = Integer.toString(cl.get(Calendar.MINUTE));

		cl = Calendar.getInstance(this.tzone);

		this.to_d = Integer.toString(cl.get(Calendar.DATE));
		this.to_M = Integer.toString(cl.get(Calendar.MONTH));
		this.to_y = Integer.toString(cl.get(Calendar.YEAR));
		this.to_H = Integer.toString(cl.get(Calendar.HOUR_OF_DAY));
		this.to_m = Integer.toString(cl.get(Calendar.MINUTE));

		this.logger = "";
		this.log_level = "";
		this.remote_ip = "";
		this.user_name = "";
		this.session_id = "";
		super.setBlock("0");

	}

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.tzone = TimeZone.getTimeZone((String) request.getSession()
				.getAttribute(IConst.SESSION.TIME_ZONE));
		setFields();
	}

	/**
	 * @return
	 */
	public LogSearchCriteria getSearchCriteria() {
		return new LogSearchCriteria(getFrom(), getTo(),
				logger.equals("") ? IConst.VALUES.ALL : logger.trim(),
				log_level.equals("") ? IConst.VALUES.ALL : log_level.trim(),
				remote_ip.equals("") ? IConst.VALUES.ALL : remote_ip.trim(),
				user_name.equals("") ? IConst.VALUES.ALL : user_name.trim(),
				session_id.equals("") ? IConst.VALUES.ALL : session_id.trim(),
				true);
	}

	/**
	 * @return Returns the from_d.
	 */
	public String getFrom_d() {
		return this.from_d;
	}

	/**
	 * @param from_d
	 *            The from_d to set.
	 */
	public void setFrom_d(String from_d) {
		this.from_d = from_d;
	}

	/**
	 * @return Returns the from_H.
	 */
	public String getFrom_H() {
		return from_H;
	}

	/**
	 * @param from_H
	 *            The from_H to set.
	 */
	public void setFrom_H(String from_H) {
		this.from_H = from_H;
	}

	/**
	 * @return Returns the from_m.
	 */
	public String getFrom_m() {
		return from_m;
	}

	/**
	 * @param from_m
	 *            The from_m to set.
	 */
	public void setFrom_m(String from_m) {
		this.from_m = from_m;
	}

	/**
	 * @return Returns the from_M.
	 */
	public String getFrom_M() {
		return from_M;
	}

	/**
	 * @param from_M
	 *            The from_M to set.
	 */
	public void setFrom_M(String from_M) {
		this.from_M = from_M;
	}

	/**
	 * @return Returns the from_y.
	 */
	public String getFrom_y() {
		return from_y;
	}

	/**
	 * @param from_y
	 *            The from_y to set.
	 */
	public void setFrom_y(String from_y) {
		this.from_y = from_y;
	}

	/**
	 * @return Returns the to_d.
	 */
	public String getTo_d() {
		return to_d;
	}

	/**
	 * @param to_d
	 *            The to_d to set.
	 */
	public void setTo_d(String to_d) {
		this.to_d = to_d;
	}

	/**
	 * @return Returns the to_H.
	 */
	public String getTo_H() {
		return to_H;
	}

	/**
	 * @param to_H
	 *            The to_H to set.
	 */
	public void setTo_H(String to_H) {
		this.to_H = to_H;
	}

	/**
	 * @return Returns the to_m.
	 */
	public String getTo_m() {
		return to_m;
	}

	/**
	 * @param to_m
	 *            The to_m to set.
	 */
	public void setTo_m(String to_m) {
		this.to_m = to_m;
	}

	/**
	 * @return Returns the to_M.
	 */
	public String getTo_M() {
		return to_M;
	}

	/**
	 * @param to_M
	 *            The to_M to set.
	 */
	public void setTo_M(String to_M) {
		this.to_M = to_M;
	}

	/**
	 * @return Returns the to_y.
	 */
	public String getTo_y() {
		return to_y;
	}

	/**
	 * @param to_y
	 *            The to_y to set.
	 */
	public void setTo_y(String to_y) {
		this.to_y = to_y;
	}

	/**
	 * @return Returns the from.
	 */
	public Date getFrom() {
		Calendar cl = Calendar.getInstance(this.tzone);
		cl.set(Calendar.DATE, Integer.parseInt(this.from_d));
		cl.set(Calendar.MONTH, Integer.parseInt(this.from_M));
		cl.set(Calendar.YEAR, Integer.parseInt(this.from_y));
		cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt(this.from_H));
		cl.set(Calendar.MINUTE, Integer.parseInt(this.from_m));
		return cl.getTime();
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
		Calendar cl = Calendar.getInstance(this.tzone);
		cl.set(Calendar.DATE, Integer.parseInt(this.to_d));
		cl.set(Calendar.MONTH, Integer.parseInt(this.to_M));
		cl.set(Calendar.YEAR, Integer.parseInt(this.to_y));
		cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt(this.to_H));
		cl.set(Calendar.MINUTE, Integer.parseInt(this.to_m));
		return cl.getTime();
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
}
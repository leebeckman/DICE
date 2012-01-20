/*
 * $$Id: User.java,v 1.3 2005/06/07 12:32:15 bel70 Exp $$
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
 * Created on 07.05.2003
 *
 */
package org.jresearch.gossip.beans.user;

import java.io.Serializable;
import java.util.Date;

import org.jresearch.gossip.singlesign.IUser;
import org.jresearch.gossip.singlesign.IUserInfo;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class User implements Serializable, IUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2266450136190891886L;

	private String ip;

	private Date intime = new Date();

	private int id;

	private String name;

	private String password;

	private int status;

	private UserInfo info;

	private UserSettings settings;

	/**
	 * Creates a new User object.
	 */
	public User() {
		this.info = new UserInfo();
		this.settings = new UserSettings();
	}

	/**
	 * Creates a new User object.
	 * 
	 * @param ipaddr
	 *            DOCUMENT ME!
	 */
	public User(String ipaddr) {
		this.info = new UserInfo();
		this.settings = new UserSettings();
		this.ip = ipaddr;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getId() {
		return id;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getName() {
		return name;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param id
	 *            DOCUMENT ME!
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param name
	 *            DOCUMENT ME!
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param password
	 *            DOCUMENT ME!
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param status
	 *            DOCUMENT ME!
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param intime
	 *            DOCUMENT ME!
	 */
	public void setIntime(Date intime) {
		this.intime = intime;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Date getIntime() {
		return intime;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public IUserInfo getInfo() {
		return info;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public UserSettings getSettings() {
		return settings;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param info
	 */
	public void setInfo(UserInfo info) {
		this.info = info;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param settings
	 */
	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setIp(String string) {
		ip = string;
	}
}

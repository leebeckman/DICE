/*
 * $Id: UserInfo.java,v 1.3 2005/06/07 12:32:02 bel70 Exp $
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
 * Created on 05.04.2004
 *
 */
package org.jresearch.gossip.beans.user;

import java.io.Serializable;
import java.util.Date;

import org.jresearch.gossip.singlesign.IUserInfo;

/**
 * @author Dmitry Belov
 * 
 */
public class UserInfo implements Serializable, IUserInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7915333755149750008L;

	private String icq;

	private Date birthday;

	private String city;

	private String email;

	private String homepage;

	private String occupation;

	/**
	 * @return
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * @return
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param date
	 */
	public void setBirthday(Date date) {
		birthday = date;
	}

	/**
	 * @param string
	 */
	public void setCity(String string) {
		city = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 */
	public void setHomepage(String string) {
		homepage = string;
	}

	/**
	 * @param string
	 */
	public void setOccupation(String string) {
		occupation = string;
	}

	/**
	 * @return
	 */
	public String getIcq() {
		return icq;
	}

	/**
	 * @param string
	 */
	public void setIcq(String string) {
		icq = string;
	}

}

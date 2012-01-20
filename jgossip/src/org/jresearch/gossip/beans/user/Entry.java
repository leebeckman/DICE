/*
 * $$Id: Entry.java,v 1.3 2005/06/07 12:32:15 bel70 Exp $$
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
 * Created on Oct 16, 2003
 *
 */
package org.jresearch.gossip.beans.user;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class Entry {
	private String sessionId;

	private String login;

	private String ip;

	/**
	 * Creates a new Entry object.
	 * 
	 * @param sessionId
	 *            DOCUMENT ME!
	 * @param login
	 *            DOCUMENT ME!
	 * @param ip
	 *            DOCUMENT ME!
	 */
	public Entry(String login, String sessionId, String ip) {
		this.sessionId = sessionId;
		this.login = login;
		this.ip = ip;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param en
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean equals(Entry en) {
		return this.sessionId.equals(en.getSessionId());
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int hashCode() {
		return this.sessionId.hashCode();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setLogin(String string) {
		login = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setSessionId(String string) {
		sessionId = string;
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

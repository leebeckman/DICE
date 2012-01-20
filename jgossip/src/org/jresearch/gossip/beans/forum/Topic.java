/*
 * $$Id: Topic.java,v 1.3 2005/06/07 12:31:55 bel70 Exp $$
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
package org.jresearch.gossip.beans.forum;

import java.io.Serializable;
import java.util.Date;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class Topic implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3890758454824674572L;

	private long messagesCount;

	private Message lastMessage;

	private Date lintime;

	private int threadid;

	private int sortby;

	private int locked;

	private String subject;

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Date getLintime() {
		return lintime;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getThreadid() {
		return threadid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param lintime
	 *            DOCUMENT ME!
	 */
	public void setLintime(Date lintime) {
		this.lintime = lintime;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param threadid
	 *            DOCUMENT ME!
	 */
	public void setThreadid(int threadid) {
		this.threadid = threadid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public Message getLastMessage() {
		return lastMessage;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param message
	 */
	public void setLastMessage(Message message) {
		lastMessage = message;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setSubject(String string) {
		subject = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public long getMessagesCount() {
		return messagesCount;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setMessagesCount(long i) {
		messagesCount = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getSortby() {
		return sortby;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setSortby(int i) {
		sortby = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getLocked() {
		return locked;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setLocked(int i) {
		locked = i;
	}

}

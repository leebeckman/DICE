/*
 * $$Id: Forum.java,v 1.4 2005/06/07 12:31:55 bel70 Exp $$
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

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class Forum implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4496160881013569622L;

	private String description;

	private int groupid;

	private int forumid;

	private int locked;

	private String sort;

	private String title;

	private int threadsCount;

	private int messCount;

	private Message lastMessage;

	private int lastMessThreadId;

	/**
	 * @return Returns the messCount.
	 */
	public int getMessCount() {
		return messCount;
	}

	/**
	 * @param messCount
	 *            The messCount to set.
	 */
	public void setMessCount(int messCount) {
		this.messCount = messCount;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getForumid() {
		return forumid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getLocked() {
		return locked;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param description
	 *            DOCUMENT ME!
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param forumid
	 *            DOCUMENT ME!
	 */
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param locked
	 *            DOCUMENT ME!
	 */
	public void setLocked(int locked) {
		this.locked = locked;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param sort
	 *            DOCUMENT ME!
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param title
	 *            DOCUMENT ME!
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param threadsCount
	 *            DOCUMENT ME!
	 */
	public void setThreadsCount(int threadsCount) {
		this.threadsCount = threadsCount;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getThreadsCount() {
		return threadsCount;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param lastMessage
	 *            DOCUMENT ME!
	 */
	public void setLastMessage(Message lastMessage) {
		this.lastMessage = lastMessage;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Message getLastMessage() {
		return lastMessage;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param lastMessThreadId
	 *            DOCUMENT ME!
	 */
	public void setLastMessThreadId(int lastMessThreadId) {
		this.lastMessThreadId = lastMessThreadId;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getLastMessThreadId() {
		return lastMessThreadId;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setGroupid(int i) {
		groupid = i;
	}
}

/*
 * $$Id: Message.java,v 1.4 2005/06/08 08:04:12 bel70 Exp $$
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
import java.util.ArrayList;

import org.jresearch.gossip.beans.forum.attachment.FileDataInfo;
import org.jresearch.gossip.beans.user.Sender;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class Message extends SimpleMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4640143925442759279L;

	private String ip;

	private Sender senderInfo;

	private int threadSort;

	private ArrayList attachments = new ArrayList();

	/**
	 * @param attachments
	 *            The attachments to set.
	 */
	public void setAttachments(ArrayList attachments) {
		this.attachments = attachments;
	}


	/**
	 * @param fd
	 */
	public void addFile(FileDataInfo fd) {
		this.attachments.add(fd);
	}

	/**
	 * @return
	 */
	public FileDataInfo[] getAttachments() {
		int count = this.attachments.size();
		FileDataInfo[] fdArray = new FileDataInfo[count];
		for (int i = 0; i < count; i++) {
			fdArray[i] = (FileDataInfo) this.attachments.get(i);
		}
		return fdArray;
	}

	/**
	 * @return
	 */
	public boolean isHasAttachment() {
		return this.attachments.size() > 0;
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

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public Sender getSenderInfo() {
		return senderInfo;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 */
	public void setSenderInfo(Sender user) {
		senderInfo = user;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getThreadSort() {
		return threadSort;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setThreadSort(int i) {
		threadSort = i;
	}
}
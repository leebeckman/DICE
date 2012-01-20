/*
 * $Id: RootNode.java,v 1.3 2005/06/07 12:32:24 bel70 Exp $
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
 * Created on 23.06.2004
 *
 */
package org.jresearch.gossip.beans.forum.tree;

import java.util.ArrayList;

import org.jresearch.gossip.beans.forum.Message;

/**
 * @author Dmitry Belov
 * 
 */
public class RootNode implements ITreeNode {
	private Message message;

	private ArrayList children = new ArrayList();

	/**
	 * @return Returns the children.
	 */
	public ArrayList getChildren() {
		return children;
	}

	/**
	 * @param child
	 */
	public void addChild(ITreeNode child) {
		this.children.add(child);
	}

	/**
	 * @return
	 */
	public boolean isLeaf() {
		return this.children.isEmpty();
	}

	/**
	 * @return Returns the message.
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            The message to set.
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
}
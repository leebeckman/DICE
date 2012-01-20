/*
 * $$Id: NewTopic.java,v 1.3 2005/06/07 12:31:55 bel70 Exp $$
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
package org.jresearch.gossip.beans.forum;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class NewTopic extends Topic {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2310575769966102169L;

	private int forumid;

	private String forumName;

	private int mod_flag;

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getForumid() {
		return forumid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getMod_flag() {
		return mod_flag;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setForumid(int i) {
		forumid = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setMod_flag(int i) {
		mod_flag = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getForumName() {
		return forumName;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setForumName(String string) {
		forumName = string;
	}
}

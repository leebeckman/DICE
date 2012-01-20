/*
 * $$Id: Group.java,v 1.3 2005/06/07 12:31:55 bel70 Exp $$
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

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2959013922514398247L;

	private ArrayList forums;

	private int groupid;

	private String name;

	private String sort;

	/**
	 * Creates a new Group object.
	 */
	public Group() {
		this.forums = new ArrayList();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param forum
	 *            DOCUMENT ME!
	 */
	public void addForum(Forum forum) {
		this.forums.add(forum);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public ArrayList getForums() {
		return forums;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getGroupid() {
		return groupid;
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
	public String getSort() {
		return sort;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param groupid
	 *            DOCUMENT ME!
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
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
	 * @param sort
	 *            DOCUMENT ME!
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
}

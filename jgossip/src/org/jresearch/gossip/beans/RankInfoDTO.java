/*
 * $Id: RankInfoDTO.java,v 1.3 2005/06/07 12:32:26 bel70 Exp $
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
 * Created on 31.07.2004
 *
 */
package org.jresearch.gossip.beans;

/**
 * @author Dmitry Belov
 * 
 */
public class RankInfoDTO {

	private int id;

	private String name;

	private int count;

	/**
	 * @param id
	 * @param name
	 * @param count
	 */
	public RankInfoDTO(int id, String name, int count) {
		super();
		if (id <= 0 || name == null || count <= 0) {
			throw new IllegalArgumentException(
					"arguments can't have a null values");
		}
		this.id = id;
		this.name = name;
		this.count = count;
	}

	/**
	 * @param name
	 * @param count
	 */
	public RankInfoDTO(String name, int count) {
		super();
		if (name == null || count <= 0) {
			throw new IllegalArgumentException(
					"arguments can't have a null values");
		}
		this.name = name;
		this.count = count;
	}

	/**
	 * @return Returns the count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
}
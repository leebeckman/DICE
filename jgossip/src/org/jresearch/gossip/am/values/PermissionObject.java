/*
 * 
 * ***** BEGIN LICENSE BLOCK ***** The contents of this file are subject to the
 * Mozilla Public License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JGossip forum code.
 * 
 * The Initial Developer of the Original Code is the JResearch, Org. Portions
 * created by the Initial Developer are Copyright (C) 2004 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Stanislav Spiridonov <stas@jresearch.org>, Dmitry Belov
 * <bel@jresearch.org>
 * 
 * ***** END LICENSE BLOCK *****
 */
package org.jresearch.gossip.am.values;

import java.io.Serializable;
import java.util.Hashtable;

public class PermissionObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2314370572011936088L;

	/**
	 * Field <code>USER</code>
	 */
	public static final int USER = 1;

	/**
	 * Field <code>GROUP</code>
	 */
	public static final int GROUP = 2;

	/**
	 * Field <code>FORUM</code>
	 */
	public static final int FORUM = 3;

	/**
	 * Field <code>TOPIC</code>
	 */
	public static final int TOPIC = 4;

	/**
	 * Field <code>MESSAGE</code>
	 */
	public static final int MESSAGE = 5;

	/**
	 * Field <code>SYSTEM</code>
	 */
	public static final int SYSTEM = 6;

	/**
	 * Field <code>ATTACH</code>
	 */
	public static final int ATTACH = 7;

	/**
	 * Represents access to all objects if used in permission points set and
	 * present not applicable attribute if used as protection point
	 */
	public static final PermissionObject ALL_OBJECTS = new PermissionObject(
			PermissionPoint.ALL);

	private int id;

	/**
	 * @param id
	 */
	public PermissionObject(int id) {
		super();
		this.id = id;
	}

	private String name;

	/**
	 * @param id
	 * @param name
	 */
	public PermissionObject(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return a hash code value for this object.
	 * @see Object#equals(Object)
	 * @see Hashtable
	 */
	public int hashCode() {
		return id;
	}

	/**
	 * @param obj
	 *            the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj
	 *         argument; <code>false</code> otherwise.
	 * @see Boolean#hashCode()
	 * @see Hashtable
	 */
	public boolean equals(Object obj) {
		if (obj instanceof PermissionObject) {
			return id == ((PermissionObject) obj).id
					|| id == PermissionPoint.ALL
					|| ((PermissionObject) obj).id == PermissionPoint.ALL;
		}
		return false;
	}
}
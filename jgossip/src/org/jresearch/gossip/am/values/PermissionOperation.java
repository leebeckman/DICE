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

public class PermissionOperation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1587895912669011068L;

	/**
	 * Field <code>READ</code>
	 */
	public static final int READ = 1;

	/**
	 * Field <code>ADD</code>
	 */
	public static final int ADD = 2;

	/**
	 * Field <code>UPDATE</code>
	 */
	public static final int UPDATE = 3;

	/**
	 * Field <code>DELETE</code>
	 */
	public static final int DELETE = 4;

	/**
	 * Field <code>LOCK</code>
	 */
	public static final int CHANGE_STATUS = 5;

	/**
	 * @author dbelov
	 * 
	 */
	public static class Forum {

		/**
		 * Field <code>SUBSCRIBE</code>
		 */
		public static final int SUBSCRIBE = 7;

		/**
		 * Field <code>ATTACH_FILE</code>
		 */
		public static final int MOVE_TOPIC = 8;
	}

	/**
	 * Represents access to all operations if used in permission points set and
	 * present not applicable attribute if used as protection point
	 */
	public static final PermissionOperation ALL_OPERATIONS = new PermissionOperation(
			PermissionPoint.ALL);

	private int id;

	private String name;

	/**
	 * @param id
	 */
	public PermissionOperation(int id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param name
	 */
	public PermissionOperation(int id, String name) {
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
		if (obj instanceof PermissionOperation) {
			return id == ((PermissionOperation) obj).id
					|| id == PermissionPoint.ALL
					|| ((PermissionOperation) obj).id == PermissionPoint.ALL;
		}
		return false;
	}
}
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

import org.jresearch.gossip.constants.UserStatus;

public class PermissionRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5551042452899523417L;

	private int id;

	private String name;

	/**
	 * Field <code>GUEST</code>
	 */
	public static final PermissionRole GUEST = new PermissionRole(
			UserStatus.GUEST);

	/**
	 * Field <code>USER</code>
	 */
	public static final PermissionRole USER = new PermissionRole(
			UserStatus.USER);

	/**
	 * Field <code>MOD</code>
	 */
	public static final PermissionRole MOD = new PermissionRole(UserStatus.MOD);

	/**
	 * Field <code>MAINMOD</code>
	 */
	public static final PermissionRole MAINMOD = new PermissionRole(
			UserStatus.MAINMOD);

	/**
	 * Field <code>JRADM</code>
	 */
	public static final PermissionRole JRADM = new PermissionRole(
			UserStatus.JRADM);

	/**
	 * Field <code>ADM</code>
	 */
	public static final PermissionRole ADM = new PermissionRole(UserStatus.ADM);

	/**
	 * @param id
	 */
	public PermissionRole(int id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param name
	 */
	public PermissionRole(int id, String name) {
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
		if (obj instanceof PermissionRole) {
			return id == ((PermissionRole) obj).id;
		}
		return false;
	}
}
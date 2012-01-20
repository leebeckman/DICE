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
 * Contributor(s):  Dmitry Belov <bel@jresearch.org>
 * 
 * ***** END LICENSE BLOCK *****
 */
/*
 * Created on 22.07.2004
 *
 */
package org.jresearch.gossip.am.values;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;

import org.jresearch.gossip.exception.SystemException;

/**
 * @author dbelov
 * 
 */
public class PermissionAttributes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2871133928578166536L;

	/**
	 * Field <code>ALL_ATTRIBUTES</code>
	 */
	public static final PermissionAttributes ALL_ATTRIBUTES = new PermissionAttributes();

	private HashMap values = new HashMap();

	/**
	 * 
	 */
	public PermissionAttributes() {
		super();
	}

	/**
	 * @param key
	 * @param value
	 * @throws SystemException
	 */
	public void addValue(String key, Integer value) throws SystemException {
		if (this != ALL_ATTRIBUTES) {
			this.values.put(key, value);
		} else {
			throw new SystemException("value can't be added in ALL_ATTRIBUTES");
		}
	}

	/**
	 * @param key
	 * @param value
	 * @throws SystemException
	 */
	public void addValue(String key, String value) throws SystemException {
		if (this != ALL_ATTRIBUTES) {
			this.values.put(key, value);
		} else {
			throw new SystemException("value can't be added in ALL_ATTRIBUTES");
		}
	}

	/**
	 * @return a hash code value for this object.
	 * @see Object#equals(Object)
	 * @see Hashtable
	 */
	public int hashCode() {
		if (values.isEmpty()) {
			return 0;
		} else {
			return values.hashCode();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof PermissionAttributes) {
			PermissionAttributes pa = (PermissionAttributes) obj;
			return this == ALL_ATTRIBUTES || pa == ALL_ATTRIBUTES
					|| this.values.equals(pa.values);
		}

		return false;

	}
}
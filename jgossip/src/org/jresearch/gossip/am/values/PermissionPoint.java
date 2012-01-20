/*
 * $Id: PermissionPoint.java,v 1.3 2005/06/07 12:32:30 bel70 Exp $
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
 * Created on 16.07.2004
 *
 */
package org.jresearch.gossip.am.values;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * @author Dmitry Belov
 * 
 */
public class PermissionPoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5408127418759175026L;

	/**
	 * Field <code>ALL_PERMISSIONS</code>
	 */
	public static PermissionPoint ALL_PERMISSIONS = new PermissionPoint(
			PermissionObject.ALL_OBJECTS, PermissionOperation.ALL_OPERATIONS);

	/**
	 * Field <code>ADM_USER</code>
	 */
	public static PermissionPoint ADM_USER = new PermissionPoint(
			new PermissionObject(PermissionObject.USER),
			PermissionOperation.ALL_OPERATIONS);

	/**
	 * Field <code>ADM_FORUM</code>
	 */
	public static PermissionPoint ADM_FORUM = new PermissionPoint(
			new PermissionObject(PermissionObject.FORUM),
			PermissionOperation.ALL_OPERATIONS);

	/**
	 * Field <code>ADM_GROUP</code>
	 */
	public static PermissionPoint ADM_GROUP = new PermissionPoint(
			new PermissionObject(PermissionObject.GROUP),
			PermissionOperation.ALL_OPERATIONS);

	/**
	 * Field <code>ADM_TOPIC</code>
	 */
	public static PermissionPoint ADM_TOPIC = new PermissionPoint(
			new PermissionObject(PermissionObject.TOPIC),
			PermissionOperation.ALL_OPERATIONS);

	/**
	 * Field <code>ADM_MESSAGE</code>
	 */
	public static PermissionPoint ADM_MESSAGE = new PermissionPoint(
			new PermissionObject(PermissionObject.MESSAGE),
			PermissionOperation.ALL_OPERATIONS);

	private PermissionObject object;

	public static final int ALL = -1;

	public static final int UNKNOWN = -2;

	private PermissionOperation operation;

	private PermissionAttributes args;

	private StringBuffer sb = new StringBuffer();

	/**
	 * @param object
	 * @param operation
	 * @param args
	 */
	public PermissionPoint(int object, int operation, PermissionAttributes args) {
		this(new PermissionObject(object), new PermissionOperation(operation),
				args);
	}

	/**
	 * @param object
	 * @param operation
	 * @param args
	 */
	public PermissionPoint(PermissionObject object,
			PermissionOperation operation, PermissionAttributes args) {
		if (object == null || operation == null || args == null) {
			throw new IllegalArgumentException(
					"parameters for creating PermissionPoint can't be null");
		}
		this.object = object;
		this.operation = operation;
		this.args = args;
	}

	/**
	 * @param object
	 * @param operation
	 */
	public PermissionPoint(int object, int operation) {
		this(new PermissionObject(object), new PermissionOperation(operation),
				PermissionAttributes.ALL_ATTRIBUTES);
	}

	/**
	 * @param object
	 * @param operation
	 */
	public PermissionPoint(PermissionObject object,
			PermissionOperation operation) {
		this(object, operation, PermissionAttributes.ALL_ATTRIBUTES);
	}

	/**
	 * @return a hash code value for this object.
	 * @see Object#equals(Object)
	 * @see Hashtable
	 */
	public int hashCode() {
		if (sb.length() > 0) {
			sb.delete(0, sb.length());
		}
		sb.append(object.hashCode());
		sb.append(operation.hashCode());
		sb.append(args.hashCode());
		return sb.toString().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof PermissionPoint) {
			PermissionPoint p = (PermissionPoint) obj;
			return (this.object.equals(p.object)
					&& this.operation.equals(p.operation) && this.args
					.equals(p.args));
		}
		return false;
	}
}
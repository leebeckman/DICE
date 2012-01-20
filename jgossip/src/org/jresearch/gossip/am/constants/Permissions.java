/*
 * $Id: Permissions.java,v 1.4 2005/06/07 12:32:36 bel70 Exp $
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
 * Portions created by the Werner Ramaekers are Copyright (C) 2003-2004 
 * the Werner Ramaekers (Shift@). All Rights Reserved. 
 * 
 * Contributor(s): 
 *              Dmitriy Belov <bel@jresearch.org>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 22.07.2004
 *
 */
package org.jresearch.gossip.am.constants;

import java.util.ArrayList;

import org.jresearch.gossip.am.values.PermissionObject;
import org.jresearch.gossip.am.values.PermissionOperation;
import org.jresearch.gossip.am.values.PermissionPoint;
import org.jresearch.gossip.constants.UserStatus;

/**
 * @author dbelov
 * 
 */
public class Permissions {

	private ArrayList ADM_PERMISSIONS = new ArrayList();

	private ArrayList JRADM_PERMISSIONS = new ArrayList();

	private ArrayList MAINMOD_PERMISSIONS = new ArrayList();

	private ArrayList MOD_PERMISSIONS = new ArrayList();

	private ArrayList GUEST_PERMISSIONS = new ArrayList();

	private ArrayList USER_PERMISSIONS = new ArrayList();

	private static Permissions instance;

	private static Object lock = new Object();

	/**
	 * @return
	 */
	public static Permissions getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new Permissions();
				}
			}
		}
		return instance;
	}

	private Permissions() {
		ADM_PERMISSIONS.add(PermissionPoint.ALL_PERMISSIONS);

		JRADM_PERMISSIONS.add(PermissionPoint.ADM_FORUM);
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.SYSTEM), new PermissionOperation(
				PermissionOperation.READ)));
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.USER), new PermissionOperation(
				PermissionOperation.READ)));
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.USER), new PermissionOperation(
				PermissionOperation.CHANGE_STATUS)));
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.USER), new PermissionOperation(
				PermissionOperation.UPDATE)));
		JRADM_PERMISSIONS.add(PermissionPoint.ADM_TOPIC);
		JRADM_PERMISSIONS.add(PermissionPoint.ADM_MESSAGE);
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.READ)));
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.ATTACH), PermissionOperation.ALL_OPERATIONS));
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.Forum.MOVE_TOPIC)));
		JRADM_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.Forum.SUBSCRIBE)));

		MAINMOD_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.READ)));
		MAINMOD_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.Forum.SUBSCRIBE)));
		MAINMOD_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.ATTACH), PermissionOperation.ALL_OPERATIONS));
		MAINMOD_PERMISSIONS.add(PermissionPoint.ADM_TOPIC);
		MAINMOD_PERMISSIONS.add(PermissionPoint.ADM_MESSAGE);
		MAINMOD_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.USER), new PermissionOperation(
				PermissionOperation.READ)));
		MAINMOD_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.USER), new PermissionOperation(
				PermissionOperation.UPDATE)));

		GUEST_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.Forum.SUBSCRIBE)));
		GUEST_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.READ)));
		GUEST_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.MESSAGE), new PermissionOperation(
				PermissionOperation.ADD)));

		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.Forum.SUBSCRIBE)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.FORUM), new PermissionOperation(
				PermissionOperation.READ)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.ATTACH), new PermissionOperation(
				PermissionOperation.ADD)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.ATTACH), new PermissionOperation(
				PermissionOperation.READ)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.ATTACH), new PermissionOperation(
				PermissionOperation.UPDATE)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.TOPIC), new PermissionOperation(
				PermissionOperation.ADD)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.MESSAGE), new PermissionOperation(
				PermissionOperation.ADD)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.MESSAGE), new PermissionOperation(
				PermissionOperation.UPDATE)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.USER), new PermissionOperation(
				PermissionOperation.READ)));
		USER_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.USER), new PermissionOperation(
				PermissionOperation.UPDATE)));

		MOD_PERMISSIONS.addAll(USER_PERMISSIONS);
		MOD_PERMISSIONS.add(new PermissionPoint(new PermissionObject(
				PermissionObject.ATTACH), PermissionOperation.ALL_OPERATIONS));
		MOD_PERMISSIONS.add(PermissionPoint.ADM_TOPIC);
		MOD_PERMISSIONS.add(PermissionPoint.ADM_MESSAGE);

	}

	/**
	 * @param roleId
	 * @return
	 */
	public ArrayList getPermissions(int roleId) {
		switch (roleId) {
		case UserStatus.GUEST:
			return GUEST_PERMISSIONS;
		case UserStatus.USER:
			return USER_PERMISSIONS;
		case UserStatus.ADM:
			return ADM_PERMISSIONS;
		case UserStatus.JRADM:
			return JRADM_PERMISSIONS;
		case UserStatus.MAINMOD:
			return MAINMOD_PERMISSIONS;
		case UserStatus.MOD:
			return MOD_PERMISSIONS;
		}
		return new ArrayList();
	}

}
/*
 * $Id: IPermissionGuardFactory.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $
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
package org.jresearch.gossip.am.model;

import org.jresearch.gossip.exception.SystemException;

/**
 * @author dbelov
 * 
 */
public interface IPermissionGuardFactory {

	/**
	 * @param userId
	 * @return
	 * @throws SystemException
	 */
	public IPermissionGuard createGuard(int userId) throws SystemException;

	/**
	 * @param login
	 * @return
	 * @throws SystemException
	 */
	public IPermissionGuard createGuard(String login) throws SystemException;
}
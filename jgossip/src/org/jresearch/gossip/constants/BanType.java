/*
 * $Id: BanType.java,v 1.3 2005/06/07 12:32:32 bel70 Exp $
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
 * Created on 29.08.2004
 *
 */
package org.jresearch.gossip.constants;

/**
 * @author Dmitry Belov
 * 
 */
public class BanType {

	/**
	 * <code>LOGIN</code>
	 */
	public static final int LOGIN = 1;

	/**
	 * <code>IP</code>
	 */
	public static final int IP = 2;

	/**
	 * <code>EMAIL</code>
	 */
	public static final int EMAIL = 3;

	private BanType() {
	}

}
/*
 * $Id: IKeyGenConst.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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
 *              Alexey Pavlov <alexnet@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */
package org.jresearch.gossip.dao.drivers.keygen;

/**
 * IKeyGenConst. Holds constant definitions for key generation.
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:31 $
 */
public interface IKeyGenConst {

	String DEFAULT_KEYGEN = "@keygen_default@";

	String KEY_KEEPER_TABLE_NAME = "keygen.highlow.table";

	String KEY_KEEPER_TABLE_COLUMN = "keygen.highlow.table_column";

	String KEY_KEEPER_KEY_COLUMN = "keygen.highlow.key_column";

	String KEY_KEEPER_INCREMENT = "keygen.highlow.increment";

	int KEY_MESSAGE = 0;

	int KEY_THREAD = 1;

	int KEY_USER = 2;

	int KEY_WHOIS = 3;

	int KEY_FORUM = 4;

	int KEY_GROUP = 5;

	int KEY_RANK = 6;

	int KEY_ATTACH = 7;

	String[][] KEY_NAMES = { { "jrf_message", "id" },
			{ "jrf_thread", "threadid" }, { "jrf_user", "id" },
			{ "jrf_whois", "id" }, { "jrf_forum", "forumid" },
			{ "jrf_group", "groupid" }, { "jrf_rank", "id" },
			{ "jrf_attach", "id" } };

}

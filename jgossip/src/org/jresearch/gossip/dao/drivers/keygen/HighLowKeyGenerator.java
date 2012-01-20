/*
 * $Id: HighLowKeyGenerator.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Obtains a key by adding 1 to the highest key in the table.
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:31 $
 */
public class HighLowKeyGenerator implements KeyGenerator {
	/**
	 * Holds a KeyKeeper for each table. The table's name is the key and the
	 * value is a KeyKeeper.
	 */
	Map keys = Collections.synchronizedMap(new HashMap());

	/**
	 * Returns a generated key.
	 * 
	 * @param classMap
	 *            provides parameters for retreiving the key
	 * @param db
	 *            the source of database connections for the key-values table
	 * @return the generated key
	 * @exception PersistenceException
	 *                if an error occurs
	 */
	public Object generateKey(String[] primaryKeyName, Connection connection)
			throws SQLException {
		final String tableName = primaryKeyName[0];

		KeyKeeper keyKeeper;
		synchronized (keys) {
			keyKeeper = (KeyKeeper) keys.get(tableName);
			if (keyKeeper == null) {
				keyKeeper = new KeyKeeper(tableName, primaryKeyName);
				keys.put(tableName, keyKeeper);
			}
		}
		return keyKeeper.nextKey(connection);
	}

	/**
	 * Generation of key is before insert.
	 */
	public boolean isBeforeInsert() {
		return true;
	}
}

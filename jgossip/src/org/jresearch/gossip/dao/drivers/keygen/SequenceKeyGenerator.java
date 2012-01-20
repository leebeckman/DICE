/*
 * $Id: SequenceKeyGenerator.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Obtains a key from a sequence table.
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:31 $
 */
public class SequenceKeyGenerator implements KeyGenerator {
	static Map sequenceDefs = new HashMap();

	static {
		SequenceKeyGenerator.sequenceDefs.put(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_MESSAGE][0],
				"SELECT JRF_KEY_MESSAGE_SEQ.NEXTVAL FROM DUAL");
		SequenceKeyGenerator.sequenceDefs.put(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_THREAD][0],
				"SELECT JRF_KEY_THREAD_SEQ.NEXTVAL FROM DUAL)");
		SequenceKeyGenerator.sequenceDefs.put(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_USER][0],
				"SELECT JRF_KEY_USER_SEQ.NEXTVAL FROM DUAL");
		SequenceKeyGenerator.sequenceDefs.put(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_WHOIS][0],
				"SELECT JRF_KEY_WHOIS_SEQ.NEXTVAL FROM DUAL");
		SequenceKeyGenerator.sequenceDefs.put(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_FORUM][0],
				"SELECT JRF_KEY_FORUM_SEQ.NEXTVAL FROM DUAL");
		SequenceKeyGenerator.sequenceDefs.put(
				IKeyGenConst.KEY_NAMES[IKeyGenConst.KEY_GROUP][0],
				"SELECT JRF_KEY_GROUP_SEQ.NEXTVAL FROM DUAL");
	}

	public boolean isBeforeInsert() {
		return true;
	}

	/**
	 * Obtains a key from a sequence table.
	 * 
	 * @param classMap
	 *            provides mapping of class to sql persistence
	 * @param db
	 *            the database for persisting the class described in the
	 *            'ClassMap'
	 * @return the key
	 * @exception PersistenceException
	 *                if an error occurs
	 */
	public Object generateKey(String[] primaryKeyName, Connection conn)
			throws SQLException {
		Object identity;
		ResultSet rs = null;
		;
		Statement stmt = null;
		final String tableName = primaryKeyName[0];

		try {
			// Create SQL statements of the form "SELECT
			// nextval(JRF_KEY_SEQUENCE)"
			String sql = (String) SequenceKeyGenerator.sequenceDefs
					.get(tableName);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				identity = rs.getObject(1);
			} else
				throw new SQLException("persist.sequenceKeyGenFailed");
		} finally {
			try {
				if (rs != null)
					rs.close();
				stmt.close();
			} catch (SQLException excep) {

			} finally {
				rs = null;
				stmt = null;
			}
		}
		return identity;
	}

}

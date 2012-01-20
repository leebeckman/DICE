/*
 * $Id: KeyKeeper.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import org.apache.log.Logger;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * This class provides generated keys.
 * 
 * With this method, there's a multi-row table which has two columns, one for
 * the identifier for the table name, and another for the next key value for
 * that table. In this variation, a grab size is specified to get a block of
 * keys to reduce database accesses.
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:31 $
 */
public class KeyKeeper {

	/**
	 * Logger instance.
	 */
	private Logger log;

	private static final String DATA_TYPE = "DATA_TYPE";

	private static int increment = -1;

	private int columnType = -1;

	/**
	 * The high value is combined with the low value to produce the key.
	 */
	Object high;

	int low;

	String[] primaryKey;

	/**
	 * The key is generated for this table.
	 */
	String tableName;

	KeyKeeper(String tableName, String[] primaryKey) {
		this.tableName = tableName;
		this.primaryKey = primaryKey;
		try {
			log = JGossipLog.getInstance().getAppLogger();
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

	/**
	 * Obtains a block of available keys from the key-values table.
	 * 
	 * @return the value beyond the block of available keys,
	 * @exception PersistenceException
	 *                if an error occurs
	 */
	Object fetchHigh(Connection connection) throws SQLException {
		Object identity = null;

		String tableColumn;
		String table;
		String keyColumn;
		try {
			// Get the name of the table used to store two columns.
			table = KeyGeneratorFactory.conf
					.get(IKeyGenConst.KEY_KEEPER_TABLE_NAME);
			// column name for identifier for table name
			tableColumn = KeyGeneratorFactory.conf
					.get(IKeyGenConst.KEY_KEEPER_TABLE_COLUMN);
			// column name for the next key for that table
			keyColumn = KeyGeneratorFactory.conf
					.get(IKeyGenConst.KEY_KEEPER_KEY_COLUMN);
		} catch (ConfiguratorException ce) {
			if (log.isErrorEnabled()) {
				log.error(
						"Configurator error. Check your config files. Cause: ",
						ce);
			}
			// Nothing we can do - configuration info corrupted or incomplete.
			// set default values here:
			table = "JRF_KEY_KEEPER";
			tableColumn = "table_name";
			keyColumn = "next_key";
		}
		StringBuffer condition = new StringBuffer();
		condition.append(tableColumn + " = ?");

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			// TODO: start db transaction here, if it is available

			// First get column type of key.
			if (columnType == -1) {
				columnType = getColumnType(connection, table, keyColumn);
			}

			if (columnType != java.sql.Types.INTEGER
					&& columnType != java.sql.Types.NUMERIC
					&& columnType != java.sql.Types.DECIMAL) {
				throw new SQLException(MessageFormat.format(
						"Key keeper has a SQL type[{0}] of key column[{1}]",
						new Object[] { new Integer(columnType), keyColumn }));

			}

			String sql = getStatement(keyColumn, table, condition.toString());
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tableName);

			rs = pstmt.executeQuery();

			// Count of records updated by SQL database
			int count = 0;
			if (rs.next()) {
				Object lastIdentity = rs.getObject(1);
				rs.close();

				if (columnType == java.sql.Types.INTEGER) {
					identity = new Integer(((Number) lastIdentity).intValue()
							+ getGrabSize());
				} else {
					identity = new BigDecimal(((Number) lastIdentity)
							.doubleValue()
							+ getGrabSize());
				}

				condition.append(" and " + keyColumn + "=?");

				// Update value
				sql = getUpdateStatement(keyColumn, table, condition.toString());
				pstmt2 = connection.prepareStatement(sql);
				pstmt2.setObject(1, identity);
				pstmt2.setString(2, tableName);
				pstmt2.setObject(3, lastIdentity);
				count = pstmt2.executeUpdate();
			}

			if (count < 1)
				throw new SQLException("persist.highlowFailed");
			if (connection.getAutoCommit() == false) {
				connection.commit();
			}
		} catch (SQLException sqle) {
			log.error("persist.highlowFailed", sqle);
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (pstmt != null) {
					pstmt.close();
				}
				pstmt = null;

				if (pstmt2 != null) {
					pstmt2.close();
				}
				pstmt2 = null;

			} catch (SQLException excep) {
				// We can safely ignore all SQLException here, since we are not
				// in the position
				// to do much about it anyway.
			}
		}

		return identity;
	}

	private final int getColumnType(Connection conn, String table, String column)
			throws SQLException {
		return java.sql.Types.INTEGER;
		/*
		 * ResultSet rs = null; try { // Using meta data because many DBMS can
		 * not // pre-compile statements. DatabaseMetaData dmd =
		 * conn.getMetaData(); //TODO: //provide concrete references to
		 * tablespace. // jgossip% rs = dmd.getColumns(null, null,
		 * primaryKey[0].toUpperCase(), primaryKey[1].toUpperCase());
		 * synchronized (this) { if (rs.next()) { return rs.getInt(DATA_TYPE); }
		 * throw new SQLException( "mapping.checkKeyGen", getClass().getName()); } }
		 * finally { if (rs != null) { rs.close(); rs = null; } }
		 */
	}

	private final String getStatement(String column, String table,
			String condition) {
		// todo: make it tunable.
		final String sql = "SELECT {0} FROM {1} WHERE {2} "; // FOR UPDATE?
		return MessageFormat.format(sql, new Object[] { column, table,
				condition });
	}

	private final String getUpdateStatement(String column, String table,
			String condition) {
		// todo: make it tunable.
		final String sql = "UPDATE {1} SET {0}=? WHERE {2}";
		return MessageFormat.format(sql, new Object[] { column, table,
				condition });
	}

	/**
	 * Returns the next generated key.
	 * 
	 * @param db
	 *            the source of database connections for the key-values table
	 * @return the generated key
	 * @exception PersistenceException
	 *                if an error occurs
	 */
	synchronized Object nextKey(Connection con) throws SQLException {
		if (high == null || low >= getGrabSize()) {
			high = fetchHigh(con);
			low = 0;
		}

		Class highClass = high.getClass();
		if (highClass.equals(Integer.class)) {
			return new Integer(((Integer) high).intValue() - getGrabSize()
					+ low++);
		} else if (highClass.equals(BigDecimal.class)) {
			return new BigDecimal(((BigDecimal) high).intValue()
					- getGrabSize() + low++);

		}
		throw new SQLException(MessageFormat.format(
				"KeyKeeper[{0}] can't handle primary key of type[{1}]",
				new Object[] { getClass().getName(), highClass }));
	}

	private static int getGrabSize()

	{
		if (-1 == increment) {
			synchronized (KeyKeeper.class) {
				if (-1 == increment) {
					try {
						final String value = KeyGeneratorFactory.conf
								.get(IKeyGenConst.KEY_KEEPER_INCREMENT);
						increment = Integer.parseInt(value);
					} catch (Exception e) {
						increment = 10;
						try {
							if (JGossipLog.getInstance().getAppLogger()
									.isErrorEnabled()) {
								JGossipLog
										.getInstance()
										.getAppLogger()
										.error(
												"Can't retreive increment value from configuration",
												e);
							}
						} catch (SystemException e1) { /* Ignore Exception! */
						}
					}
				}
			}
		}
		return increment;
	}
}

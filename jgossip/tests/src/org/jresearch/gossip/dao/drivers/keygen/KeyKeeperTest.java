/*
 * $Id: KeyKeeperTest.java,v 1.1 2004/11/23 11:09:04 bel70 Exp $
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
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import junit.framework.TestCase;

/**
 * KeyKeeperTest
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2004/11/23 11:09:04 $
 */
public class KeyKeeperTest extends TestCase {
	
	private Connection conn;

	/**
	 * Constructor for KeyKeeperTest.
	 * @param arg0
	 */
	public KeyKeeperTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(KeyKeeperTest.class);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@frodo:1521:ORACLEDB", "jgossip", "Welcome1");;
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		if (null != conn) conn.close();
		conn = null;
	}

	final public void testGetColumnTypeTC() 
			throws Exception
	{
		ResultSet rs = null;
		String table = "JRF_KEY_KEEPER";
		String column = "next_key";
		DatabaseMetaData dmd = conn.getMetaData();
		System.out.println("database metadata obtained: "+dmd.toString());
		System.out.println("Columns info obtaining for table[" + table +"] column["+column+"].");
		//schema - "JGOSSIP"
		rs = dmd.getColumns(null, null, table.toUpperCase(), column.toUpperCase());
		System.out.println("Columns info obtained: "+rs);
		if(null != rs )
		{
			for(int i = rs.getMetaData().getColumnCount(); i > 0; i--) {
				System.out.println(rs.getMetaData().getColumnName(i));
			}
			while(rs.next()) {
					System.out.println("TABLE_CAT:"+rs.getObject("TABLE_CAT"));
					System.out.println("TABLE_SCHEM:"+rs.getObject("TABLE_SCHEM"));
					System.out.println("TABLE_NAME:"+rs.getObject("TABLE_NAME"));
					System.out.println("COLUMN_NAME:"+rs.getObject("COLUMN_NAME"));
					System.out.println("DATA_TYPE:"+rs.getObject("DATA_TYPE"));
					System.out.println("TYPE_NAME:"+rs.getObject("TYPE_NAME"));
					System.out.println("COLUMN_SIZE:"+rs.getObject("COLUMN_SIZE"));
					System.out.println("DECIMAL_DIGITS:"+rs.getObject("DECIMAL_DIGITS"));
					System.out.println("NULLABLE:"+rs.getObject("NULLABLE"));
					System.out.println("IS_NULLABLE:"+rs.getObject("IS_NULLABLE"));
					//System.out.println("SCOPE_CATLOG:"+rs.getObject("SCOPE_CATLOG"));
					//System.out.println("SCOPE_SCHEMA:"+rs.getObject("SCOPE_SCHEMA"));
					//System.out.println("SCOPE_TABLE:"+rs.getObject("SCOPE_TABLE"));
			}
			rs.close();
		}
	}

		final public void testGetColumnTypePK()
				throws Exception 
		{
			ResultSet rs = null;
			String [] primaryKey = {"jrf_message", "id"};
			DatabaseMetaData dmd = conn.getMetaData();
			System.out.println("database metadata obtained: "+dmd.toString());
			System.out.println("Primary keys:");
			for (int i = 0; i < primaryKey.length; ++i)
				System.out.println("primary key ["+i+"]="+primaryKey[i]);
			System.out.println("Columns info obtaining for table[" + primaryKey[0] +"] column["+primaryKey[1]+"].");
			// schema - "JGOSSIP"
			rs = dmd.getColumns(null, null, primaryKey[0].toUpperCase(), primaryKey[1].toUpperCase());
			System.out.println("Columns info obtained: "+rs);
			if(null != rs )
			{
				for(int i = rs.getMetaData().getColumnCount(); i > 0; i--) {
					System.out.println(rs.getMetaData().getColumnName(i));
				}
				while(rs.next()) {
					System.out.println("TABLE_CAT:"+rs.getObject("TABLE_CAT"));
					System.out.println("TABLE_SCHEM:"+rs.getObject("TABLE_SCHEM"));
					System.out.println("TABLE_NAME:"+rs.getObject("TABLE_NAME"));
					System.out.println("COLUMN_NAME:"+rs.getObject("COLUMN_NAME"));
					System.out.println("DATA_TYPE:"+rs.getObject("DATA_TYPE"));
					System.out.println("TYPE_NAME:"+rs.getObject("TYPE_NAME"));
					System.out.println("COLUMN_SIZE:"+rs.getObject("COLUMN_SIZE"));
					System.out.println("DECIMAL_DIGITS:"+rs.getObject("DECIMAL_DIGITS"));
					System.out.println("NULLABLE:"+rs.getObject("NULLABLE"));
					System.out.println("IS_NULLABLE:"+rs.getObject("IS_NULLABLE"));
					//System.out.println("SCOPE_CATLOG:"+rs.getObject("SCOPE_CATLOG"));
					//System.out.println("SCOPE_SCHEMA:"+rs.getObject("SCOPE_SCHEMA"));
					//System.out.println("SCOPE_TABLE:"+rs.getObject("SCOPE_TABLE"));
				}
				rs.close();
			}
		}

}

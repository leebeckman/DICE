/*
 * $Id: DbDriver.java,v 1.4 2005/06/14 09:35:44 bel70 Exp $
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
package org.jresearch.gossip.dao.drivers;

import java.util.ResourceBundle;

/**
 * DbDriver
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ 21.03.2004
 */
public abstract class DbDriver {

	static {
		String dataBaseVendor = null;
		try {
			ResourceBundle dbconf = ResourceBundle
					.getBundle("org/jresearch/gossip/resources/db");
			dataBaseVendor = dbconf.getString("dataBaseVendor");
			Class clazz = DbDriverRegistry.getInstance().getDriverClass(
					dataBaseVendor);
			if (null == clazz)
				throw new RuntimeException("Database driver [" + dataBaseVendor
						+ "] not supported.");
			DbDriver.instance = (DbDriver) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException("Database driver [" + dataBaseVendor
					+ "] can not be initialized.");
		} catch (IllegalAccessException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException("Database driver [" + dataBaseVendor
					+ "] can not be initialized.");
		}
	}

	private static DbDriver instance;

	public static DbDriver getInstance() {
		return instance;
	}

	protected Queries queries;

	/**
	 * @return
	 */
	public Queries getQueries() {
		return this.queries;
	}

	/**
	 * Map data type of object from resultset to internal type. Database data
	 * type NUMBER mapped to java.math.BigDecimal according to JDBCv2. This is
	 * not a case for MySql.
	 * 
	 * @param object
	 *            Object from resultset.
	 * @return Boject of mapped data type
	 * @throws ClassCastException
	 */
	public abstract Object mapObjectType(Object object)
			throws ClassCastException;

	/**
	 * Generic: Create value of last row number for expression BETWEEN
	 * ROW_IDX_START AND (ROW_IDX_START+LENGTH AS ROW_IDX_END). MySQL: Returns
	 * length for expression LIMIT ROW_IDX_START, LENGTH MSSQL: Returns length.
	 * 
	 * @param startIdx
	 * @param length
	 * @return
	 */
	public abstract int getLastRowIdx(int startIdx, int length);

}

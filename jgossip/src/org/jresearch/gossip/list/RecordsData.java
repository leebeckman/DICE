/*
 * $$Id: RecordsData.java,v 1.3 2005/06/07 12:32:35 bel70 Exp $$
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
 *              Dmitry Belov <bel@jresearch.org>
 *
 * ***** END LICENSE BLOCK ***** */
/*
 * Created on Jul 13, 2003
 *
 */
package org.jresearch.gossip.list;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.jresearch.gossip.dao.drivers.DbDriver;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class RecordsData {
	private ArrayList records;

	private int recordsCount;

	private int currBlock;

	private int blockSize;

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getCurrBlock() {
		return currBlock;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getCurrentPage() {
		return Math.round(currBlock / blockSize) + 1;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean isLastBlock() {
		return ((recordsCount - currBlock) <= blockSize);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean isHaveSplit() {
		return (recordsCount > blockSize);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public ArrayList getRecords() {
		return records;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getRecordsCount() {
		return recordsCount;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setCurrBlock(int i) {
		currBlock = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setRecordsCount(int i) {
		recordsCount = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param rs
	 *            DOCUMENT ME!
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param klass
	 *            DOCUMENT ME!
	 * 
	 * @throws InstantiationException
	 *             DOCUMENT ME!
	 * @throws IllegalAccessException
	 *             DOCUMENT ME!
	 * @throws InvocationTargetException
	 *             DOCUMENT ME!
	 * @throws NoSuchMethodException
	 *             DOCUMENT ME!
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public void fillRecords(ResultSet rs, HashMap mapping, java.lang.Class klass)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SQLException {
		Set keys = mapping.keySet();
		this.records = new ArrayList();

		DbDriver dvDriver = DbDriver.getInstance();

		while (rs.next()) {
			Object item = klass.newInstance();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				PropertyUtils.setProperty(item, key, dvDriver.mapObjectType(rs
						.getObject((String) mapping.get(key))));
			}

			this.records.add(item);
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setBlockSize(int i) {
		blockSize = i;
	}
}

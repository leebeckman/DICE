/*
 * $Id: GenericSqlDriver.java,v 1.4 2005/06/14 09:35:47 bel70 Exp $
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
package org.jresearch.gossip.dao.drivers.generic;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.jresearch.gossip.dao.drivers.DbDriver;

/**
 * Generic SQL Driver
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ 21.03.2004
 */
public class GenericSqlDriver extends DbDriver {

	/**
	 * Create instance of dictionary of Generic SQL statetements.
	 */
	public GenericSqlDriver() {
		this.queries = new GenericSqlQueries();
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.DbDriver#mapObjectType(java.lang.Object)
	 */
	public Object mapObjectType(Object object) throws ClassCastException {
		if (null == object)
			return null;
		// remap BigDecimal type to Integer.
		if (object instanceof BigDecimal) {
			return new Integer(((BigDecimal) object).intValue());
		}
		if (object instanceof oracle.sql.TIMESTAMP) {
			try {
				return new Date(((oracle.sql.TIMESTAMP) object)
						.timestampValue().getTime());
			} catch (SQLException sqle) {
				throw new ClassCastException(
						"Can't convert oracle.sql.TIMESTAMP to java.util.Date"
								+ sqle.getLocalizedMessage());
			}
		}
		// rest of datatypes.
		return object;
	}

	public static final String VENDOR_ORACLE = "oracle";

	/**
	 * Create value of last row for expression BETWEEN ROW_IDX_START AND
	 * ROW_IDX_END.
	 * 
	 * @see org.jresearch.gossip.dao.drivers.DbDriver#getLastRowIdx(int, int)
	 */
	public int getLastRowIdx(int startIdx, int length) {
		return startIdx + length;
	}
}

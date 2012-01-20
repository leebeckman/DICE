/*
 * $Id: MSSqlDriver.java,v 1.4 2005/06/14 09:35:45 bel70 Exp $
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
 *              Simone Chiaretta <simo@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */
package org.jresearch.gossip.dao.drivers.mssql;

import org.jresearch.gossip.dao.drivers.DbDriver;

/**
 * MSSqlDriver
 * 
 * @author <a href="simo@sourceforge.net">S. Chiaretta</a>
 * @version $version$ $Date: 2005/06/14 09:35:45 $
 */
public class MSSqlDriver extends DbDriver {

	/**
	 * Create instance of dictionary of MSSql specific SQL statetements.
	 */
	public MSSqlDriver() {
		this.queries = new MSSqlQueries();
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.DbDriver#mapObjectType(java.lang.Object)
	 */
	public Object mapObjectType(Object object) throws ClassCastException {
		// There is no needs to map anything for MSSql database.
		return object;
	}

	/**
	 * MSSQL: Returns length.
	 * 
	 * @see org.jresearch.gossip.dao.drivers.DbDriver#getLastRowIdx(int, int)
	 */
	public int getLastRowIdx(int startIdx, int length) {
		return length;
	}

	public static final String VENDOR_NAME = "MSSQL";

}

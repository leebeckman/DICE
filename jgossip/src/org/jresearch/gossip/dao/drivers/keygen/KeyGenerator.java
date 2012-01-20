/*
 * $Id: KeyGenerator.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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
import java.sql.SQLException;

/**
 * The primarey key generator contract.
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:31 $
 */
public interface KeyGenerator {
	BigDecimal ONE = new BigDecimal(1);

	/**
	 * This generates a key for a field in the object described by the given
	 * primary key name.
	 * 
	 * @param primaryKeyNames
	 *            provides mapping of class to sql persistence
	 * @param con
	 *            The database for persisting the object described in String[]
	 *            primaryKeyNames
	 * @return Generated primary key.
	 * @exception SQLException
	 *                if an error occurs
	 */
	Object generateKey(String[] primaryKeyName, Connection connection)
			throws SQLException;

	/**
	 * @return
	 */
	boolean isBeforeInsert();

}

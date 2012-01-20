/*
 * $Id: DbDriverRegistry.java,v 1.4 2005/06/14 09:35:44 bel70 Exp $
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

import java.util.HashMap;

import org.jresearch.gossip.dao.drivers.generic.GenericSqlDriver;
import org.jresearch.gossip.dao.drivers.mssql.MSSqlDriver;
import org.jresearch.gossip.dao.drivers.mysql.MySqlDriver;

/**
 * DriverRegistry
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ 21.03.2004
 */
final class DbDriverRegistry {

	final static DbDriverRegistry instance = new DbDriverRegistry();

	static DbDriverRegistry getInstance() {
		return instance;
	}

	HashMap driverMap = new HashMap();

	DbDriverRegistry() {
		driverMap.put(GenericSqlDriver.VENDOR_ORACLE,
				GenericSqlDriver.class);
		driverMap.put(MSSqlDriver.VENDOR_NAME, MSSqlDriver.class);
		driverMap.put(MySqlDriver.VENDOR_NAME, MySqlDriver.class);
	}

	final Class getDriverClass(String name) {
		return (Class) driverMap.get(name);
	}

}

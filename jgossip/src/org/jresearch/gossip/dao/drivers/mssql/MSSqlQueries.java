/*
 * $Id: MSSqlQueries.java,v 1.3 2005/06/07 12:32:25 bel70 Exp $
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

import org.jresearch.gossip.dao.drivers.Queries;

/**
 * MSSqlQueries
 * 
 * @author <a href="simo@sourceforge.net">S. Chiaretta</a>
 * @version $version$ $Date: 2005/06/07 12:32:25 $
 */
class MSSqlQueries extends Queries {

	/**
	 * C'tor for Oracle specific queries collection.
	 */
	MSSqlQueries() {
		this.forumQueries = new MSSqlForumQueries();
		this.userQueries = new MSSqlUserQueries();
	}

	/**
	 * @see org.jresearch.gossip.dao.drivers.Queries#getNOW()
	 */
	public String getSql_NOW() {
		return NOW;
	}

	private static final String NOW = "SELECT GETDATE()";

}

/*
 * $$Id: DAO.java,v 1.3 2005/06/07 12:32:30 bel70 Exp $$
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
 * Created on 07.05.2003
 *
 */
package org.jresearch.gossip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.jresearch.gossip.dao.drivers.DbDriver;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public abstract class DAO {
	protected DataSource dataSource;

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param source
	 */
	public void setDataSource(DataSource source) {
		if (this.dataSource == null) {
			dataSource = source;
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws SQLException
	 *             DOCUMENT ME!
	 */
	public Date now() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement st = connection.prepareStatement(DbDriver
				.getInstance().getQueries().getSql_NOW());
		ResultSet rs = null;
		Date now = null;

		try {
			rs = st.executeQuery();

			if (rs.next()) {
				now = rs.getTimestamp(1);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			st.close();
			connection.close();
		}

		return now;
	}
}

/*
 * $Id: Queries.java,v 1.3 2005/06/07 12:32:24 bel70 Exp $
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

/**
 * Queries
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ 21.03.2004
 */
public abstract class Queries {

	/**
	 * @return
	 */
	public String getSql_AND() {
		return AND;
	}

	/**
	 * @return
	 */
	public String getSql_AND_USER_NAME() {
		return AND_USER_NAME;
	}

	/**
	 * @return
	 */
	public String getSql_END_1() {
		return END_1;
	}

	/**
	 * @return
	 */
	public String getSql_END_2() {
		return END_2;
	}

	/**
	 * @return
	 */
	public String getSql_LIKE() {
		return LIKE;
	}

	/**
	 * @return
	 */
	public String getSql_DATE_FORMAT() {
		return MYSQL_DATE_FORMAT;
	}

	/**
	 * @return
	 */
	public String getSql_DATETIME_FORMAT() {
		return MYSQL_DATETIME_FORMAT;
	}

	/**
	 * @return
	 */
	public String getSql_OR() {
		return OR;
	}

	public ForumQueries getForumQueries() {
		return this.forumQueries;
	}

	public UserQueries getUserQueries() {
		return this.userQueries;
	}

	/**
	 * @return
	 */
	public abstract String getSql_NOW();

	protected ForumQueries forumQueries;

	protected UserQueries userQueries;

	private static final String AND = " AND ";

	private static final String AND_USER_NAME = "' AND user_name = '";

	private static final String END_1 = "')";

	private static final String END_2 = "'";

	private static final String LIKE = " LIKE ";

	private static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd";

	private static final String MYSQL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String OR = " OR ";
}

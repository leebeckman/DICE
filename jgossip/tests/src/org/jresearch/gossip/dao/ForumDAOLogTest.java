/*
 * $Id: ForumDAOLogTest.java,v 1.1 2004/11/23 11:08:28 bel70 Exp $
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
package org.jresearch.gossip.dao;

import java.util.Date;
import java.util.Iterator;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.LogEntry;
import org.jresearch.gossip.beans.LogSearchCriteria;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.list.RecordsData;

/**
 * ForumDAOLogTest.
 * Don't forget define in system properties DB driver info: driver class, DB URL, username and password:
 * -DdriverClassName=@db_driver@ -Durl=@db_url@ -Dpassword=@db_pass@ -Dusername=@db_user@
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2004/11/23 11:08:28 $
 */
public class ForumDAOLogTest extends TestCase {
	
	private ForumDAO dao = null;
	private DataSource ds = null;
	private Date to = null;
	private Date from = null;

	/**
	 * Constructor for ForumDAOLogTest.
	 * @param arg0
	 */
	public ForumDAOLogTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ForumDAOLogTest.class);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		DataSource ds = BasicDataSourceFactory.createDataSource(System.getProperties());
		dao = ForumDAO.getInstance();
		dao.setDataSource(ds);
		Date date = new Date();
		from = new Date(date.getTime()-180000000);		
		to = new Date(date.getTime()+180000000);		
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	final public void testFillLogEntryList() throws Exception
	{
		RecordsData records = new RecordsData();
		User user = null;
		records.setBlockSize((null != user)?user.getSettings().getMes_per_page():25);
		LogSearchCriteria criteria = new LogSearchCriteria();
		criteria.setFrom(from);
		criteria.setTo(to);
		criteria.setLog_level(IConst.VALUES.ALL);
		criteria.setLogger(IConst.VALUES.ALL);
		criteria.setRemote_ip(IConst.VALUES.ALL);
		criteria.setSession_id(IConst.VALUES.ALL);
		criteria.setUser_name(IConst.VALUES.ALL);
		int block = 1;
		dao.fillLogEntryList(criteria, records, block);
		Iterator it = records.getRecords().iterator();
		assertTrue(it.hasNext());
		assertTrue(records.getRecordsCount() > 0);
		while(it.hasNext()) {
			LogEntry obj = (LogEntry)it.next();
			System.out.print(String.valueOf(obj.getLog_date()));
			System.out.print(", "+ String.valueOf(obj.getLog_level()));
			System.out.print(", "+String.valueOf(obj.getLogger()));
			System.out.print(", "+String.valueOf(obj.getMessage()));
			System.out.print(", "+String.valueOf(obj.getRemote_ip()));
			System.out.print(", "+String.valueOf(obj.getSession_id()));
			System.out.println(", "+String.valueOf(obj.getUser_name()));
		}
	}

	final public void testFillLogEntryListWARN() throws Exception
	{
		RecordsData records = new RecordsData();
		User user = null;
		records.setBlockSize((null != user)?user.getSettings().getMes_per_page():25);
		LogSearchCriteria criteria = new LogSearchCriteria();
		criteria.setFrom(from);
		criteria.setTo(to);
		criteria.setLog_level("WARN");
		criteria.setLogger(IConst.VALUES.ALL);
		criteria.setRemote_ip(IConst.VALUES.ALL);
		criteria.setSession_id(IConst.VALUES.ALL);
		criteria.setUser_name(IConst.VALUES.ALL);
		int block = 1;
		dao.fillLogEntryList(criteria, records, block);
		Iterator it = records.getRecords().iterator();
		assertTrue(!it.hasNext());
		assertTrue(records.getRecordsCount() == 0);
	}

	final public void testFillLogEntryListINFO() throws Exception
	{
		RecordsData records = new RecordsData();
		User user = null;
		records.setBlockSize((null != user)?user.getSettings().getMes_per_page():25);
		LogSearchCriteria criteria = new LogSearchCriteria();
		criteria.setFrom(from);
		criteria.setTo(to);
		criteria.setLog_level("INFO");
		criteria.setLogger("FORUM");
		criteria.setRemote_ip(IConst.VALUES.ALL);
		criteria.setSession_id(IConst.VALUES.ALL);
		criteria.setUser_name(IConst.VALUES.ALL);
		int block = 1;
		dao.fillLogEntryList(criteria, records, block);
		Iterator it = records.getRecords().iterator();
		assertTrue(it.hasNext());
		assertTrue(records.getRecordsCount() > 0);
	}

}

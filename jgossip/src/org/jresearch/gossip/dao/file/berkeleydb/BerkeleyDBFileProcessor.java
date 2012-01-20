/*
 * $Id: BerkeleyDBFileProcessor.java,v 1.3 2005/06/07 12:32:34 bel70 Exp $
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
 *              Dmitriy Belov <bel@jresearch.org>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 08.08.2004
 *
 */
package org.jresearch.gossip.dao.file.berkeleydb;

import java.io.UnsupportedEncodingException;

import org.jresearch.gossip.dao.file.IFileProcessor;
import org.jresearch.gossip.exception.SystemException;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

/**
 * @author Dmitry Belov
 * 
 */
public class BerkeleyDBFileProcessor implements IFileProcessor {

	private String dbname;

	private FileDbEnv env;

	/**
	 * @throws SystemException
	 * 
	 */
	public BerkeleyDBFileProcessor() throws SystemException {
		env = FileDbEnv.getInstance();
	}

	private Database getFileDB() throws SystemException {
		return env.getFileDb(dbname);
	}

	/**
	 * @return Returns the dbname.
	 */
	public String getDbname() {
		return dbname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.dao.file.IFileProcessor#setDbname(java.lang.String)
	 */
	public void setStoreName(String dbname) {
		this.dbname = dbname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.dao.file.IFileProcessor#saveFileData(byte[],
	 *      java.lang.String)
	 */
	public void saveFileData(byte[] data, String key) throws SystemException {
		Database db = null;
		try {
			db = getFileDB();
			DatabaseEntry theKey = new DatabaseEntry(key.getBytes("UTF-8"));
			DatabaseEntry theData = new DatabaseEntry(data);
			db.put(null, theKey, theData);
		} catch (UnsupportedEncodingException e) {
			throw new SystemException(e);
		} catch (DatabaseException e) {
			throw new SystemException(e);
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (DatabaseException e) {
					throw new SystemException(e);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.dao.file.IFileProcessor#getFileData(java.lang.String)
	 */
	public byte[] getFileData(String key) throws SystemException {
		Database db = null;
		try {
			db = getFileDB();
			DatabaseEntry theKey = new DatabaseEntry(key.getBytes("UTF-8"));
			DatabaseEntry theData = new DatabaseEntry();
			if (db.get(null, theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				return theData.getData();
			}
			return null;
		} catch (UnsupportedEncodingException e) {
			throw new SystemException(e);
		} catch (DatabaseException e) {
			throw new SystemException(e);
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (DatabaseException e) {
					throw new SystemException(e);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.dao.file.IFileProcessor#removeFileData(java.lang.String)
	 */
	public void removeFileData(String key) throws SystemException {
		Database db = null;
		try {
			db = getFileDB();
			DatabaseEntry theKey = new DatabaseEntry(key.getBytes("UTF-8"));
			db.delete(null, theKey);
		} catch (UnsupportedEncodingException e) {
			throw new SystemException(e);
		} catch (DatabaseException e) {
			throw new SystemException(e);
		} finally {
			if (db != null) {
				try {
					db.close();
				} catch (DatabaseException e) {
					throw new SystemException(e);
				}
			}
		}

	}

}
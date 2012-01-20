/*
 * $Id: FileDbEnv.java,v 1.3 2005/06/07 12:32:34 bel70 Exp $
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

import java.io.File;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.SystemException;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/**
 * @author Dmitry Belov
 * 
 */
public class FileDbEnv {

	private Environment env;

	private DatabaseConfig dbConfig;

	private FileDbEnv() {

	}

	private static FileDbEnv instance;

	/**
	 * @return
	 * @throws SystemException
	 */
	public static synchronized FileDbEnv getInstance() throws SystemException {
		if (instance == null) {
			load();
		}
		return instance;
	}

	/**
	 * @throws SystemException
	 */
	public static void load() throws SystemException {
		try {
			if (instance != null) {
				instance.stop();
			}
			instance = new FileDbEnv();
			File path;

			path = new File(Configurator.getInstance().get(
					IConst.CONFIG.ATTACH_STORE_PATH));

			instance.setup(path, false);
		} catch (DatabaseException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * @throws SystemException
	 */
	public static void close() throws SystemException {

		if (instance != null) {
			instance.stop();
		}

	}

	/**
	 * @param envHome
	 * @param readOnly
	 * @throws DatabaseException
	 */
	private void setup(File envHome, boolean readOnly) throws DatabaseException {

		EnvironmentConfig myEnvConfig = new EnvironmentConfig();
		dbConfig = new DatabaseConfig();

		// If the environment is read-only, then
		// make the databases read-only too.
		myEnvConfig.setReadOnly(readOnly);
		dbConfig.setReadOnly(readOnly);

		// If the environment is opened for write, then we want to be
		// able to create the environment and databases if
		// they do not exist.
		myEnvConfig.setAllowCreate(!readOnly);
		dbConfig.setAllowCreate(!readOnly);

		// Allow transactions if we are writing to the database
		myEnvConfig.setTransactional(!readOnly);
		dbConfig.setTransactional(!readOnly);
		// Open the environment
		env = new Environment(envHome, myEnvConfig);
	}

	// Close the environment
	/**
	 * @throws SystemException
	 */
	private void stop() throws SystemException {
		if (env != null) {
			try {
				// Finally, close the environment.
				env.close();

			} catch (DatabaseException dbe) {
				throw new SystemException("Error closing environment: ", dbe);
			}
		}
	}

	/**
	 * @return Returns the env.
	 */
	public Environment getEnv() {
		return env;
	}

	/**
	 * @param dbname
	 * @return Returns the fileDb.
	 * @throws SystemException
	 */
	public Database getFileDb(String dbname) throws SystemException {
		try {
			return env.openDatabase(null, dbname, dbConfig);
		} catch (DatabaseException dbe) {
			throw new SystemException("Error opening " + dbname + ": ", dbe);
		}
	}

}
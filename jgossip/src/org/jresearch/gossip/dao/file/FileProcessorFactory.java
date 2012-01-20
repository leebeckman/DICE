/*
 * $Id: FileProcessorFactory.java,v 1.3 2005/06/07 12:32:16 bel70 Exp $
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
 * Created on Aug 8, 2004
 *
 */
package org.jresearch.gossip.dao.file;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jresearch.gossip.exception.SystemException;

/**
 * @author Bel
 * 
 */
public class FileProcessorFactory {

	private Map registry = Collections.synchronizedMap(new HashMap());

	private Properties props = new Properties();

	private static FileProcessorFactory instance;

	public final String SUFF_STORENAME = ".storename";

	public final String SUFF_CLASSNAME = ".processor.class";

	private FileProcessorFactory() throws SystemException {
		try {
			// load(or fill) properties
			props.load(this.getClass().getClassLoader().getResourceAsStream(
					"org/jresearch/gossip/dao/file/filedb.properties"));
		} catch (IOException e) {
			throw new SystemException(e);
		}

	}

	/**
	 * @return
	 * @throws SystemException
	 */
	public static synchronized FileProcessorFactory getInstance()
			throws SystemException {
		if (null == instance) {
			instance = new FileProcessorFactory();
		}
		return instance;
	}

	/**
	 * @param name
	 * @return
	 * @throws SystemException
	 */
	public IFileProcessor getFileProcessor(String name) throws SystemException {
		IFileProcessor fp = null;
		if (!registry.containsKey(name)) {
			loadFileProcessor(name);
		}
		fp = (IFileProcessor) registry.get(name);
		return fp;
	}

	/**
	 * @param name
	 * @throws SystemException
	 */
	private void loadFileProcessor(String name) throws SystemException {
		String className = props.getProperty(name + SUFF_CLASSNAME);
		String storeName = props.getProperty(name + SUFF_STORENAME);
		try {
			IFileProcessor fp = (IFileProcessor) Class.forName(className)
					.newInstance();
			fp.setStoreName(storeName);
			registry.put(name, fp);

		} catch (InstantiationException e) {
			throw new SystemException(e);
		} catch (IllegalAccessException e) {
			throw new SystemException(e);
		} catch (ClassNotFoundException e) {
			throw new SystemException(e);
		}
	}
}
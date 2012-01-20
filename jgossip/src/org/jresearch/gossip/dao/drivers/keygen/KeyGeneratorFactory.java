/*
 * $Id: KeyGeneratorFactory.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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

import java.sql.SQLException;
import java.util.ArrayList;

import org.jresearch.gossip.configuration.Configurator;

/**
 * KeyGeneratorFactory holds a list of key generators.
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:31 $
 */
public class KeyGeneratorFactory {
	private KeyGeneratorFactory() {
	}

	/**
	 * List of <code>SqlInterfaceEntry</code> holding the instantiated
	 * interfaces
	 */
	static ArrayList generators = new ArrayList();

	static Configurator conf = Configurator.getInstance();

	static class KeyGeneratorEntry {
		String name;

		KeyGenerator keyGenerator;

		KeyGeneratorEntry(String name, KeyGenerator keyGenerator) {
			this.name = name;
			this.keyGenerator = keyGenerator;
		}
	}

	static interface KeyGeneratorCreator {
		KeyGenerator create();
	}

	/** A list of the available key generators */
	private static KeyGeneratorCreator[] keyGenerators = new KeyGeneratorCreator[] {
			new KeyGeneratorCreator() {
				public KeyGenerator create() {
					return new HighLowKeyGenerator();
				}

				public String toString() {
					return "highlow";
				}
			}, new KeyGeneratorCreator() {
				public KeyGenerator create() {
					return new SequenceKeyGenerator();
				}

				public String toString() {
					return "sequence";
				}
			} };

	/**
	 * Adds a <code>SqlInterface</code> to the list of instantiated
	 * interfaces.
	 * 
	 * @param the
	 *            short name of the <code>SqlInterface</code>
	 * @return the <code>SqlInterface</code> added to the list
	 */
	private static KeyGenerator addKeyGenerator(String name)
			throws SQLException {
		KeyGenerator generator = null;
		for (int i = 0; i < keyGenerators.length; i++) {
			if (name.equals(keyGenerators[i].toString())) {
				generator = keyGenerators[i].create();
				generators.add(new KeyGeneratorEntry(keyGenerators[i]
						.toString(), generator));
				return generator;
			}
		}
		return null;
	}

	/**
	 * Return an <code>KeyGenerator</code> for the given class name.
	 * 
	 * @param the
	 *            short name of the <code>SqlInterface</code>
	 * @return a <code>KeyGenerator</code> for the given class name.
	 */
	private static KeyGenerator getKeyGeneratorIntern(String name)
			throws SQLException {
		KeyGeneratorEntry entry = null;
		int size = generators.size();
		for (int i = 0; i < size; i++) {
			entry = (KeyGeneratorEntry) generators.get(i);
			if (entry.name.equals(name))
				return entry.keyGenerator;
		}
		return null;
	}

	/**
	 * @param name
	 *            of the key generator
	 * @return a <code>KeyGenerator</code> indexed by the given name
	 */
	public synchronized static KeyGenerator getKeyGenerator(String name)
			throws SQLException {
		String low = name.toLowerCase();
		KeyGenerator generator = getKeyGeneratorIntern(low);
		if (generator != null)
			return generator;

		generator = addKeyGenerator(low);
		if (generator != null)
			return generator;

		throw new SQLException("mapping.keyGenNotFound", name);

	}

}

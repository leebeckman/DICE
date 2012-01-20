/*
 * $$Id: EntryList.java,v 1.3 2005/06/07 12:32:15 bel70 Exp $$
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
 * Created on Oct 16, 2003
 *
 */
package org.jresearch.gossip.beans.user;

import java.util.HashMap;
import java.util.Set;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class EntryList {
	private int total;

	private HashMap logged = new HashMap();

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setTotal(int i) {
		total = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param entry
	 *            DOCUMENT ME!
	 */
	public void put(Entry entry) {
		logged.put(entry.getLogin(), entry);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param login
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Entry get(String login) {
		return (Entry) logged.get(login);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getLoggedCount() {
		return logged.size();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param login
	 *            DOCUMENT ME!
	 */
	public void remove(String login) {
		logged.remove(login);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Set getLoggedUsers() {
		return logged.keySet();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getGuestsCount() {
		return this.total - logged.size();
	}

}

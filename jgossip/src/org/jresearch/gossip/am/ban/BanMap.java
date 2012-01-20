/*
 * $Id: BanMap.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $
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
 * Created on 29.08.2004
 *
 */
package org.jresearch.gossip.am.ban;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Dmitry Belov
 * 
 */
public class BanMap implements Cloneable {

	private HashMap map = new HashMap();

	/**
	 * @param mask
	 * @param type
	 */
	public void add(String mask, int type) {
		String key = Integer.toString(type);
		if (!map.containsKey(key)) {
			map.put(key, new HashSet());
		}
		((HashSet) map.get(key)).add(mask);
	}

	/**
	 * @param type
	 * @return
	 */
	public HashSet get(int type) {
		return (HashSet) map.get(Integer.toString(type));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Map getMap() {
		return (Map) map.clone();
	}
}
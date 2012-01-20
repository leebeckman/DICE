/*
 * $$Id: Mapping.java,v 1.3 2005/06/07 12:32:35 bel70 Exp $$
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
 * Created on Jul 14, 2003
 *
 */
package org.jresearch.gossip.list;

import java.util.HashMap;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class Mapping {
	private static Mapping instance;

	private static Object lock = new Object();

	public HashMap TreadMapping = new HashMap();

	public HashMap NewTreadMapping = new HashMap();

	public HashMap MessageMapping = new HashMap();

	public HashMap UserMapping = new HashMap();

	public HashMap GroupMapping = new HashMap();

	public HashMap SearchMapping = new HashMap();

	public HashMap SubscriptionMapping = new HashMap();

	public HashMap LogEntryMapping = new HashMap();

	private Mapping() {
		TreadMapping.put("threadid", "id");
		TreadMapping.put("sortby", "sortby");
		TreadMapping.put("subject", "subject");
		TreadMapping.put("messagesCount", "tot_mes");
		TreadMapping.put("locked", "locked");

		NewTreadMapping.putAll(TreadMapping);
		NewTreadMapping.put("forumid", "fid");

		MessageMapping.put("sender", "sender");
		MessageMapping.put("centents", "centents");
		MessageMapping.put("intime", "intime");
		MessageMapping.put("heading", "heading");
		MessageMapping.put("ip", "ip");
		MessageMapping.put("id", "id");

		SearchMapping.put("sender", "sender");
		SearchMapping.put("centents", "centents");
		SearchMapping.put("intime", "intime");
		SearchMapping.put("heading", "heading");
		SearchMapping.put("id", "id");
		SearchMapping.put("threadid", "threadid");
		SearchMapping.put("forumid", "forumid");

		SubscriptionMapping.put("forumid", "forumid");
		SubscriptionMapping.put("subject", "heading");
		SubscriptionMapping.put("threadid", "threadid");

		UserMapping.put("id", "id");
		UserMapping.put("name", "user_name");
		UserMapping.put("status", "user_status");

		GroupMapping.put("groupid", "groupid");
		GroupMapping.put("name", "group_name");

		LogEntryMapping.put("log_date", "log_date");
		LogEntryMapping.put("logger", "logger");
		LogEntryMapping.put("log_level", "log_level");
		LogEntryMapping.put("message", "message");
		LogEntryMapping.put("remote_ip", "remote_ip");
		LogEntryMapping.put("user_name", "user_name");
		LogEntryMapping.put("session_id", "session_id");
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static Mapping getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new Mapping();
				}
			}
		}
		return instance;
	}
}

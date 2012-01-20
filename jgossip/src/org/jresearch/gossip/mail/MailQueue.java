/*
 * $$Id: MailQueue.java,v 1.3 2005/06/07 12:32:02 bel70 Exp $$
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
 * Created on Oct 28, 2003
 *
 */
package org.jresearch.gossip.mail;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

import com.evelopers.common.concurrent.AbstractQueue;

/**
 * @author dbelov
 * 
 * 
 */
public class MailQueue extends AbstractQueue {

	/**
	 * @param arg0
	 */
	public MailQueue(MailProcessor arg0) {

		super(arg0, IConst.CONFIG.MAIL_QUEUE_THREADS_COUNT);
		try {
			JGossipLog.getInstance().getAppLogger().info(
					"jGossip's MailQueue is created");
		} catch (SystemException e) { /* Ignore Exception! */
		}
	}

}

/*
 * $Id: JobListener.java,v 1.3 2005/06/07 12:32:21 bel70 Exp $
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
package org.jresearch.gossip.scheduler.listeners;

import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * JobListener
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:21 $
 */
public class JobListener implements org.quartz.JobListener {

	/**
	 * @see org.quartz.JobListener#getName()
	 */
	public String getName() {
		return "Failed Job Listener";
	}

	/**
	 * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext)
	 */
	public void jobExecutionVetoed(JobExecutionContext ctx) {
	}

	/**
	 * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext)
	 */
	public void jobToBeExecuted(JobExecutionContext ctx) {
	}

	/**
	 * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext,
	 *      org.quartz.JobExecutionException)
	 */
	public void jobWasExecuted(JobExecutionContext ctx, JobExecutionException ex) {
		if (null != ex) {
			try {
				JGossipLog.getInstance().getAppLogger().error(
						"Job was executed with errors at [" + ctx.getFireTime()
								+ " details[" + ctx.getJobDetail() + "] ", ex);
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
	}

}

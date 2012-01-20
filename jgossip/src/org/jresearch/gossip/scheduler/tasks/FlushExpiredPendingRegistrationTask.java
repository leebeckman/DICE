/*
 * $Id: FlushExpiredPendingRegistrationTask.java,v 1.3 2005/06/07 12:32:35 bel70 Exp $
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
package org.jresearch.gossip.scheduler.tasks;

import java.sql.SQLException;

import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;
import org.jresearch.gossip.scheduler.SchedulerJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * FlushExpiredPendingRegistrationTask
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:35 $
 */
public class FlushExpiredPendingRegistrationTask implements SchedulerJob {

	private static UserDAO ms_dao = UserDAO.getInstance();

	/**
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		if (null != ctx.getPreviousFireTime()) {
			try {
				ms_dao.deletePendingUser(ctx.getPreviousFireTime().getTime());

				try {
					JGossipLog.getInstance().getAppLogger().info(
							"Task executed sucessfully: " + ctx);
				} catch (SystemException e1) {
					e1.printStackTrace();
				}

			} catch (SQLException e) {
				throw new JobExecutionException(e);
			}
		}
	}

}

/*
 * $Id: TriggerListener.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $
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
import org.quartz.Trigger;

/**
 * TriggerListener
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:17 $
 */
public class TriggerListener implements org.quartz.TriggerListener {

	/**
	 * @see org.quartz.TriggerListener#getName()
	 */
	public String getName() {
		return "Misfired Trigger Listener";
	}

	/**
	 * @see org.quartz.TriggerListener#triggerFired(org.quartz.Trigger,
	 *      org.quartz.JobExecutionContext)
	 */
	public void triggerFired(Trigger trigger, JobExecutionContext ctx) {
	}

	/**
	 * @see org.quartz.TriggerListener#vetoJobExecution(org.quartz.Trigger,
	 *      org.quartz.JobExecutionContext)
	 */
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext ctx) {
		return false;
	}

	/**
	 * @see org.quartz.TriggerListener#triggerMisfired(org.quartz.Trigger)
	 */
	public void triggerMisfired(Trigger trigger) {

		StringBuffer message = new StringBuffer();
		message.append("Trigger[").append(trigger.getName()).append(
				"], description[").append(trigger.getDescription()).append(
				"], volatile[").append(trigger.isVolatile()).append(
				"] misfered:\n").append("\tMay fire again[").append(
				trigger.mayFireAgain()).append("], next firing time[").append(
				trigger.getNextFireTime()).append("], misfire instructions[")
				.append(trigger.getMisfireInstruction()).append("].\n\tJob [")
				.append(trigger.getJobName()).append("], description[").append(
						trigger.getFullJobName()).append("], instance[")
				.append(trigger.getFireInstanceId()).append("].");
		try {
			JGossipLog.getInstance().getAppLogger().warn(message.toString());
		} catch (SystemException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see org.quartz.TriggerListener#triggerComplete(org.quartz.Trigger,
	 *      org.quartz.JobExecutionContext, int)
	 */
	public void triggerComplete(Trigger trigger, JobExecutionContext ctx,
			int triggerInstructionCodetriggerInstructionCode) {
	}

}

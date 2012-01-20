/*
 * $Id: Scheduler.java,v 1.3 2005/06/07 12:32:28 bel70 Exp $
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
package org.jresearch.gossip.scheduler;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import org.jresearch.gossip.scheduler.listeners.JobListener;
import org.jresearch.gossip.scheduler.listeners.TriggerListener;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Scheduler
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:28 $
 */
public class Scheduler {

	private org.quartz.Scheduler m_scheduler = null;

	/**
	 * 
	 */
	public Scheduler() throws SchedulerException, IOException {
		super();
		try {
			StdSchedulerFactory factory = new StdSchedulerFactory();
			Properties config = new Properties();
			InputStream configSource = Thread
					.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(
							"org/jresearch/gossip/resources/scheduler.properties");
			config.load(configSource);
			factory.initialize(config);
			m_scheduler = factory.getScheduler();
			m_scheduler.addGlobalJobListener(new JobListener());
			m_scheduler.addGlobalTriggerListener(new TriggerListener());
			JobDetail jobDetail = new JobDetail(getConfValue(config,
					"task.name"), getConfValue(config, "task.group"), Class
					.forName(getConfValue(config, "task.class")));
			jobDetail.setDescription(getConfValue(config, "task.description"));
			CronTrigger trigger = new CronTrigger(getConfValue(config,
					"trigger.name"), getConfValue(config, "trigger.group"));
			trigger.setCronExpression(getConfValue(config,
					"trigger.cronExpression"));
			m_scheduler.scheduleJob(jobDetail, trigger);
		} catch (ClassNotFoundException e) {
			throw new SchedulerException(
					"Failed to find job implementation class:", e);
		} catch (ParseException e) {
			throw new SchedulerException(
					"Failed to parse cron trigger expression:", e);
		}
	}

	/**
	 * @see org.quartz.Scheduler#getSchedulerName()
	 */
	public String getSchedulerName() throws SchedulerException {
		return m_scheduler.getSchedulerName();
	}

	/**
	 * @see org.quartz.Scheduler#getSchedulerInstanceId()
	 */
	public String getSchedulerInstanceId() throws SchedulerException {
		return m_scheduler.getSchedulerInstanceId();
	}

	/**
	 * @see org.quartz.Scheduler#getContext()
	 */
	public SchedulerContext getContext() throws SchedulerException {
		return m_scheduler.getContext();
	}

	/**
	 * @see org.quartz.Scheduler#start()
	 */
	public void start() throws SchedulerException {
		m_scheduler.start();
	}

	/**
	 * @see org.quartz.Scheduler#pause()
	 */
	public void pause() throws SchedulerException {
		m_scheduler.pause();
	}

	/**
	 * @see org.quartz.Scheduler#isPaused()
	 */
	public boolean isPaused() throws SchedulerException {
		return m_scheduler.isPaused();
	}

	/**
	 * @see org.quartz.Scheduler#shutdown()
	 */
	public void shutdown() throws SchedulerException {
		m_scheduler.shutdown();
	}

	/**
	 * @see org.quartz.Scheduler#shutdown(boolean)
	 */
	public void shutdown(boolean waitForJobsToComplete)
			throws SchedulerException {
		m_scheduler.shutdown(waitForJobsToComplete);
	}

	/**
	 * @see org.quartz.Scheduler#isShutdown()
	 */
	public boolean isShutdown() throws SchedulerException {
		return m_scheduler.isShutdown();
	}

	private final String getConfValue(Properties conf, String param) {
		StringBuffer key = new StringBuffer().append(PREFIX).append(param);
		return conf.getProperty(key.toString());
	}

	private static final String PREFIX = "org.jgossip.scheduler.tasks.1.";
}

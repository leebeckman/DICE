/*
 * $$Id: SimpleMessage.java,v 1.3 2005/06/07 12:31:55 bel70 Exp $$
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
 * Created on Nov 2, 2003
 *
 */
package org.jresearch.gossip.beans.forum;

import java.io.Serializable;
import java.util.Date;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class SimpleMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6711897730907194178L;

	private String centents;

	private String heading;

	private int id;

	private Date intime;

	private String sender;

	private int threadid;

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getCentents() {
		return centents;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public Date getIntime() {
		return intime;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setCentents(String string) {
		centents = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setHeading(String string) {
		heading = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param date
	 */
	public void setIntime(Date date) {
		intime = date;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setSender(String string) {
		sender = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getThreadid() {
		return threadid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setThreadid(int i) {
		threadid = i;
	}
}

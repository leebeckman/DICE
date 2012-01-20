/*
 * $$Id: ForumForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $$
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
 * Created on 07.05.2003
 *
 */
package org.jresearch.gossip.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class ForumForm extends ValidatorForm {
	private String forum_desc;

	private String forumid;

	private String groupid;

	private String forum_sort = "aa";

	private String forum_name;

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.forum_desc = null;
		this.forumid = null;
		this.groupid = null;
		this.forum_sort = "aa";
		this.forum_name = null;
	}

	/**
	 * Validate the properties that have been set from this HTTP request, and
	 * return an <code>ActionErrors</code> object that encapsulates any
	 * validation errors that have been found. If no errors are found, return
	 * <code>null</code> or an <code>ActionErrors</code> object with no
	 * recorded error messages.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 * 
	 * @return DOCUMENT ME!
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// Perform validator framework validations
		ActionErrors errors = super.validate(mapping, request);

		return errors;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getForum_desc() {
		return forum_desc;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getForum_name() {
		return forum_name;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param description
	 *            DOCUMENT ME!
	 */
	public void setForum_desc(String description) {
		this.forum_desc = description;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param title
	 *            DOCUMENT ME!
	 */
	public void setForum_name(String title) {
		this.forum_name = title;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getForumid() {
		return forumid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setForumid(String string) {
		forumid = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setGroupid(String string) {
		groupid = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getGroupid() {
		return groupid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getForum_sort() {
		return forum_sort;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setForum_sort(String string) {
		forum_sort = string;
	}
}

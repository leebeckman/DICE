/*
 * $Id: GroupForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $
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
 * Created on 20.05.2004
 *
 */
package org.jresearch.gossip.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author Dmitry Belov
 * 
 */
public class GroupForm extends ValidatorForm {
	private String group_name;

	private String group_sort = "aa";

	private String gid;

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.group_sort = "aa";
	}

	/**
	 * @return Returns the gid.
	 */
	public String getGid() {
		return gid;
	}

	/**
	 * @param gid
	 *            The gid to set.
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * @return Returns the group_name.
	 */
	public String getGroup_name() {
		return group_name;
	}

	/**
	 * @param group_name
	 *            The group_name to set.
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	/**
	 * @return Returns the group_sort.
	 */
	public String getGroup_sort() {
		return group_sort;
	}

	/**
	 * @param group_sort
	 *            The group_sort to set.
	 */
	public void setGroup_sort(String group_sort) {
		this.group_sort = group_sort;
	}
}

/*
 * $$Id: ChangePasswordForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $$
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
 * Created on 09.06.2003
 *
 */
package org.jresearch.gossip.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class ChangePasswordForm extends ValidatorForm {
	private String password = null;

	private String password1 = null;

	private String password2 = null;

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.password = null;
		this.password1 = null;
		this.password2 = null;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getPassword1() {
		return password1;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setPassword1(String string) {
		password1 = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setPassword2(String string) {
		password2 = string;
	}
}

/*
 * $Id: FogotPasswordForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $
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
 * Created on 18.05.2004
 *
 */
package org.jresearch.gossip.forms;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Dmitry Belov
 * 
 */
public class FogotPasswordForm extends ValidatorForm {
	private String email;

	private String uid;

	/**
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param string
	 */
	public void setUid(String string) {
		uid = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

}

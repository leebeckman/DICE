/*
 * $Id: UserSettings.java,v 1.3 2005/06/07 12:32:15 bel70 Exp $
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
 * Created on 05.04.2004
 *
 */
package org.jresearch.gossip.beans.user;

import java.io.Serializable;

/**
 * @author Dmitry Belov
 * 
 */
public class UserSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5401544852113980611L;

	private boolean autologin;

	private int mes_per_page = 15;

	private boolean show_user_mail;

	private String signature;

	/**
	 * @return
	 */
	public boolean isAutologin() {
		return autologin;
	}

	/**
	 * @return
	 */
	public int getMes_per_page() {
		return mes_per_page;
	}

	/**
	 * @return
	 */
	public boolean isShow_user_mail() {
		return show_user_mail;
	}

	/**
	 * @return
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param b
	 */
	public void setAutologin(boolean b) {
		autologin = b;
	}

	/**
	 * @param i
	 */
	public void setMes_per_page(int i) {
		mes_per_page = i;
	}

	/**
	 * @param b
	 */
	public void setShow_user_mail(boolean b) {
		show_user_mail = b;
	}

	/**
	 * @param string
	 */
	public void setSignature(String string) {
		signature = string;
	}

}

/*
 * $$Id: ProfileForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $$
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
package org.jresearch.gossip.forms;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;
import org.jresearch.gossip.beans.user.User;

/**
 * DOCUMENT ME!
 * 
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class ProfileForm extends ValidatorForm {

	// ----------------------------------------------------- Instance Variables
	private String email = null;

	protected String password = null;

	protected String password2 = null;

	private String homePage = null;

	private String login = null;

	private String icq = null;

	private String signature = null;

	private String DOB_day = "1";

	private String DOB_year = "2004";

	private String DOB_month = "0";

	private String occupation = null;

	private String place = null;

	private String messPerPage = "15";

	private String autoLogin = "2";

	private String showEmail = "2";

	private String confirmCode = "";

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getAutoLogin() {
		return autoLogin;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getDOB_day() {
		return DOB_day;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getIcq() {
		return icq;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getMessPerPage() {
		return messPerPage;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getOccupation() {
		return occupation;
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
	public String getPassword2() {
		return password2;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getShowEmail() {
		return showEmail;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setAutoLogin(String string) {
		autoLogin = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setDOB_day(String string) {
		DOB_day = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setHomePage(String string) {
		if ((string != null) && !string.equals("")) {
			if (!(string.startsWith("http://") || string.startsWith("https://"))) {
				string = "http://" + string;
			}
		}

		homePage = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setIcq(String string) {
		icq = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setLogin(String string) {
		login = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setMessPerPage(String string) {
		messPerPage = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setOccupation(String string) {
		occupation = string;
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
	public void setPassword2(String string) {
		password2 = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setPlace(String string) {
		place = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setShowEmail(String string) {
		showEmail = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setSignature(String string) {
		signature = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getDOB_month() {
		return DOB_month;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getDOB_year() {
		return DOB_year;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setDOB_month(String string) {
		DOB_month = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setDOB_year(String string) {
		DOB_year = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param user
	 *            DOCUMENT ME!
	 */
	public void setUserInfo(User user) {
		setAutoLogin(user.getSettings().isAutologin() ? "1" : "2");
		setEmail(user.getInfo().getEmail());
		setHomePage(user.getInfo().getHomepage());
		setIcq(user.getInfo().getIcq());
		setLogin(user.getName());
		setMessPerPage(Integer.toString(user.getSettings().getMes_per_page()));
		setOccupation(user.getInfo().getOccupation());
		setPassword(user.getPassword());
		setPassword2(user.getPassword());
		setPlace(user.getInfo().getCity());
		setShowEmail(user.getSettings().isShow_user_mail() ? "1" : "2");
		setSignature(user.getSettings().getSignature());

		Calendar cl = Calendar.getInstance();
		Date bd = null;

		if (user.getInfo().getBirthday() != null) {
			bd = user.getInfo().getBirthday();
		} else {
			bd = new Date();
		}

		cl.setTime(bd);
		setDOB_day(Integer.toString(cl.get(Calendar.DATE)));
		setDOB_month(Integer.toString(cl.get(Calendar.MONTH)));
		setDOB_year(Integer.toString(cl.get(Calendar.YEAR)));
	}

	/**
	 * @return Returns the confirmCode.
	 */
	public String getConfirmCode() {
		return confirmCode;
	}

	/**
	 * @param confirmCode
	 *            The confirmCode to set.
	 */
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
}
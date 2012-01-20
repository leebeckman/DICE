/*
 * $$Id: MessageForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $$
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
 * Created on Sep 20, 2003
 *
 */
package org.jresearch.gossip.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.jresearch.gossip.util.HtmlCodec;
import org.jresearch.gossip.util.MessageProcessor;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class MessageForm extends ValidatorForm {

	private String fid;

	private String tid;

	private String block = "0";

	private String mid;

	private String title;

	private String text;

	private String subscribe;

	private String announce;

	private String email;

	private String name;

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.fid = null;
		this.tid = null;
		this.block = "0";
		this.mid = null;
		this.title = null;
		this.text = null;
		this.subscribe = null;
		this.announce = null;
		this.email = null;
		this.name = null;
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

		if ((this.tid == null || this.tid.equals(""))
				&& (this.title == null || this.title.equals(""))) {
			errors.add("title", new ActionError("errors.ERR18"));
		}

		return errors;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getFid() {
		return fid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setFid(String i) {
		fid = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setTid(String i) {
		tid = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setBlock(String i) {
		block = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setMid(String string) {
		mid = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSubscribe() {
		return subscribe;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	public String getCleanText() {
		return MessageProcessor.getInstance().cleanup(this.text).trim();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setSubscribe(String string) {
		subscribe = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setText(String string) {
		text = HtmlCodec.trim(string);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setTitle(String string) {
		title = HtmlCodec.trim(string);
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
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getAnnounce() {
		return announce;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param string
	 */
	public void setAnnounce(String string) {
		announce = string;
	}
}
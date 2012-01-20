/*
 * $$Id: StylesForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $$
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
 * Created on Jan 11, 2004
 *
 */
package org.jresearch.gossip.forms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.oro.text.perl.Perl5Util;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class StylesForm extends ValidatorForm {
	private final Map values = new HashMap();

	private String skinid;

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.skinid = null;
		this.values.clear();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// Perform validator framework validations
		ActionErrors errors = super.validate(mapping, request);
		Iterator it = getKeys().iterator();
		Perl5Util util = new Perl5Util();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (!util.match("/^[a-fA-F0-9]{6}$/", (String) getValue(key))) {
				errors.add(key, new ActionError("errors.color", key));
			}
		}

		return errors;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param key
	 *            DOCUMENT ME!
	 * @param value
	 *            DOCUMENT ME!
	 */
	public void setValue(String key, Object value) {
		values.put(key, value);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param key
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Object getValue(String key) {
		return values.get(key);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Set getKeys() {
		return values.keySet();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSkinid() {
		return skinid;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setSkinid(String i) {
		skinid = i;
	}
}

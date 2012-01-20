/*
 * $$Id: Validator.java,v 1.4 2005/06/09 10:03:35 bel70 Exp $$
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
 * Created on Nov 8, 2003
 *
 */
package org.jresearch.gossip.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.oro.text.perl.Perl5Util;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;
import org.jresearch.gossip.IConst;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class Validator {
	/**
	 * DOCUMENT ME!
	 * 
	 * @param bean
	 *            DOCUMENT ME!
	 * @param va
	 *            DOCUMENT ME!
	 * @param field
	 *            DOCUMENT ME!
	 * @param errors
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean validateEmail(Object bean, ValidatorAction va, Field field,
			ActionMessages errors, HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean, field
				.getProperty());
		Perl5Util util = new Perl5Util();

		if (!GenericValidator.isBlankOrNull(value)) {
			if ((!util
					.match(
							"/( )|(@.*@)|(\\.\\.)|(@\\.)|(\\.@)|(^\\.)|(^_+@)|(^\\-+@)/",
							value))
					&& util
							.match(
									"/^[\\w\\'\\.\\-]+@((\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)|[a-zA-Z0-9\\-]{2,})$/",
									value)) {
				return true;
			} else {

				errors.add(field.getKey(), Resources.getActionMessage(request,
						va, field));

				return false;
			}
		}

		return true;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param bean
	 *            DOCUMENT ME!
	 * @param va
	 *            DOCUMENT ME!
	 * @param field
	 *            DOCUMENT ME!
	 * @param errors
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public boolean validateTwoFields(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean, field
				.getProperty());
		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!value.equals(value2)) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, va, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request,
						va, field));

				return false;
			}
		}

		return true;
	}

	public boolean validateConfirmCode(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean, field
				.getProperty());
		HttpSession session = request.getSession();
		String value2 = (String) session
				.getAttribute(IConst.SESSION.CONFIRM_CODE);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!value.equals(value2)) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, va, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request,
						va, field));

				return false;
			}
		}

		return true;
	}
}

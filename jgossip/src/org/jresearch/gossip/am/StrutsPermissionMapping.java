/*
 * $Id: StrutsPermissionMapping.java,v 1.3 2005/06/07 12:32:23 bel70 Exp $
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
 * Created on 16.07.2004
 *
 */
package org.jresearch.gossip.am;

import org.apache.struts.action.ActionMapping;

/**
 * @author Dmitry Belov
 * 
 */
public class StrutsPermissionMapping extends ActionMapping {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4737343936336931821L;

	private Integer amObjectId = null;

	private Integer amOperationId = null;

	private String amAttr1 = null;

	private String amAttr2 = null;

	private String amAttr3 = null;

	public StrutsPermissionMapping() {
		super();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[StrutsPermissionMapping:");
		buffer.append(" amObjectId: ");
		buffer.append(amObjectId);
		buffer.append(" amOperationId: ");
		buffer.append(amOperationId);
		buffer.append(" amAttr1: ");
		buffer.append(amAttr1);
		buffer.append(" amAttr2: ");
		buffer.append(amAttr2);
		buffer.append(" amAttr3: ");
		buffer.append(amAttr3);
		buffer.append("]");
		return buffer.toString();
	}

	/**
	 * @return Returns the amAttr1.
	 */
	public String getAmAttr1() {
		return amAttr1;
	}

	/**
	 * @param amAttr1
	 *            The amAttr1 to set.
	 */
	public void setAmAttr1(String attr1) {
		this.amAttr1 = attr1;
	}

	/**
	 * @return Returns the amAttr2.
	 */
	public String getAmAttr2() {
		return amAttr2;
	}

	/**
	 * @param amAttr2
	 *            The amAttr2 to set.
	 */
	public void setAmAttr2(String attr2) {
		this.amAttr2 = attr2;
	}

	/**
	 * @return Returns the amAttr3.
	 */
	public String getAmAttr3() {
		return amAttr3;
	}

	/**
	 * @param amAttr3
	 *            The amAttr3 to set.
	 */
	public void setAmAttr3(String attr3) {
		this.amAttr3 = attr3;
	}

	/**
	 * @return Returns the amObjectId.
	 */
	public Integer getAmObjectId() {
		return amObjectId;
	}

	/**
	 * @param amObjectId
	 *            The amObjectId to set.
	 */
	public void setAmObjectId(Integer objectId) {
		this.amObjectId = objectId;
	}

	/**
	 * @return Returns the amOperationId.
	 */
	public Integer getAmOperationId() {
		return amOperationId;
	}

	/**
	 * @param amOperationId
	 *            The amOperationId to set.
	 */
	public void setAmOperationId(Integer operationId) {
		this.amOperationId = operationId;
	}
}
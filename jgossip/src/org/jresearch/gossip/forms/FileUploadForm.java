/*
 * $Id: FileUploadForm.java,v 1.3 2005/06/07 12:32:17 bel70 Exp $
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
 * Created on 01.07.2004
 *
 */
package org.jresearch.gossip.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.ConfiguratorException;

/**
 * @author Dmitry Belov
 * 
 */
public class FileUploadForm extends MessageForm {

	private FormFile[] file;

	private String[] fdesc;

	private int maxFileCount;

	/**
	 * 
	 */
	public FileUploadForm() {
		Configurator config = Configurator.getInstance();
		try {
			this.maxFileCount = config
					.getInt(IConst.CONFIG.MAX_ATTACHMENT_COUNT);
		} catch (ConfiguratorException e) {
			e.printStackTrace();
			maxFileCount = 1;
		}
		this.file = new FormFile[maxFileCount];
		this.fdesc = new String[maxFileCount];
	}

	/**
	 * @return
	 */
	public List[] getFileData() {
		List result[] = { new ArrayList(), new ArrayList() };
		for (int i = 0; i < this.file.length; i++) {
			if (this.file[i] != null && this.file[i].getFileSize() > 0) {
				result[0].add(this.file[i]);
				result[1].add(this.fdesc[i]);
			}
		}
		return result;
	}

	/**
	 * @return Returns the desc.
	 */
	public String getDesc(int key) {
		return this.fdesc[key];
	}

	/**
	 * @param desc
	 *            The desc to set.
	 */
	public void setDesc(int key, String desc) {
		this.fdesc[key] = desc;
	}

	/**
	 * @param file
	 *            The file to set.
	 */
	public void setFile(int key, FormFile file) {
		this.file[key] = file;
	}

	/**
	 * @return Returns the file.
	 */
	public FormFile getFile(int key) {
		return this.file[key];
	}

	/**
	 * 
	 * /** Check to make sure the client hasn't exceeded the maximum allowed
	 * upload size inside of this validate method.
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		// has the maximum length been exceeded
		Boolean maxLengthExceeded = (Boolean) request
				.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
		if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
			errors = new ActionErrors();
			errors.add("maxLengthExceeded", new ActionError(
					"error.maxLengthExceeded", "maxLengthExceeded"));
		}
		return errors;

	}

}
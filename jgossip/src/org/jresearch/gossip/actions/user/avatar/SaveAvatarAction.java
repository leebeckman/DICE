/*
 * $$Id: SaveAvatarAction.java,v 1.3 2005/06/07 12:32:32 bel70 Exp $$
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
 * Created on 18.08.2004
 *
 */
package org.jresearch.gossip.actions.user.avatar;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.exception.JGossipException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.util.PictureGenerator;

/**
 * @author dbelov
 * 
 */
public abstract class SaveAvatarAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.BaseAction#process(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JGossipException {
		if (!Configurator.getInstance().getBoolean(IConst.CONFIG.ENABLE_AVATAR)) {
			return (mapping.findForward(IConst.TOKEN.DENIED));
		}

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		ForumDAO dao = ForumDAO.getInstance();
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			BufferedImage image = getImage(form);
			if (image == null) {
				throw new SystemException("Unable to parse image");
			}
			PictureGenerator.getInstance().prepareImage(image, buffer);
			byte[] data = buffer.toByteArray();
			dao.saveAvatar(user, data);
		} catch (Exception e) {
			super.setStatusMessage(request, "errors.general", "<br>"
					+ e.getMessage());
			return mapping.getInputForward();
		}
		return (mapping.findForward(IConst.TOKEN.PAGE));
	}

	protected abstract BufferedImage getImage(ActionForm form)
			throws SystemException;

}
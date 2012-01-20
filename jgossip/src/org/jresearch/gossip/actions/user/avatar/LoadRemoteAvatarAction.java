/*
 * $Id: LoadRemoteAvatarAction.java,v 1.3 2005/06/07 12:32:32 bel70 Exp $
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
 * Created on 21.08.2004
 *
 */
package org.jresearch.gossip.actions.user.avatar;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.struts.action.ActionForm;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.LoadAvatarForm;

/**
 * @author Dmitry Belov
 * 
 */
public class LoadRemoteAvatarAction extends SaveAvatarAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.user.avatar.SaveAvatarAction#getImage(org.apache.struts.action.ActionForm)
	 */
	protected BufferedImage getImage(ActionForm form) throws SystemException {
		LoadAvatarForm laForm = (LoadAvatarForm) form;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new URL(laForm.getUrl()));
		} catch (MalformedURLException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		}
		return img;
	}

}

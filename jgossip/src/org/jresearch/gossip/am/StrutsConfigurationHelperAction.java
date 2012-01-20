/*
 * $Id: StrutsConfigurationHelperAction.java,v 1.3 2005/06/07 12:32:23 bel70 Exp $
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
 * Portions created by the Werner Ramaekers are Copyright (C) 2003-2004 
 * the Werner Ramaekers (Shift@). All Rights Reserved. 
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

import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.log.Logger;
import org.apache.struts.Globals;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * @author Dmitry Belov
 * 
 */
public class StrutsConfigurationHelperAction implements IConst {

	private static HashMap actionMappingMap = null;

	private static ModuleConfig mConfig = null;

	/**
	 * @param app
	 * @return
	 * @throws ConfiguratorException
	 * @throws SystemException
	 *             TODO
	 */
	public static HashMap retrieveStrutsActionMapping(ServletContext app)
			throws SystemException {
		Logger log = JGossipLog.getInstance().getAppLogger();
		if (actionMappingMap == null) {
			mConfig = (ModuleConfig) app.getAttribute(Globals.MODULE_KEY
					+ Configurator.getInstance().get(CONFIG.MODULE_PREFIX));

			if (mConfig != null) {
				actionMappingMap = new HashMap();
				ActionConfig[] acfg = mConfig.findActionConfigs();
				for (int i = 0; i < acfg.length; i++) {
					ActionConfig actionConfig = acfg[i];
					if (actionConfig instanceof StrutsPermissionMapping) {

						StrutsPermissionMapping amp = (StrutsPermissionMapping) actionConfig;
						actionMappingMap.put(amp.getPath(), amp);
						if (log.isDebugEnabled()) {
							log.debug(amp.getPath()
									+ " permission mapping is loaded");
						}
					}
				}
			} else {
				if (log.isErrorEnabled()) {
					log.error("NO MAPPINGS RETRIEVED");
				}
			}
		}
		return actionMappingMap;
	}

}
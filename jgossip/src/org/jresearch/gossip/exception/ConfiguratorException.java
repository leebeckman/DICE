/*
 * $Id: ConfiguratorException.java,v 1.3 2005/06/07 12:32:23 bel70 Exp $
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
 * Created on 20.04.2004
 *
 */
package org.jresearch.gossip.exception;

/**
 * @author Dmitry Belov
 * 
 */
public class ConfiguratorException extends SystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6585464074684768181L;

	/**
	 * Constructor <code>ApplicationConfigurationException</code> create
	 * exception with message.
	 * 
	 * @param <code>msg</code> error message
	 */
	public ConfiguratorException(String msg) {
		super(msg);
	}

	/**
	 * Constructor <code>ApplicationConfigurationException</code> create
	 * exception with message and originally thrown exception
	 * 
	 * @param <code>msg</code> error message
	 * @param <code>ex</code> original exception
	 */
	public ConfiguratorException(String msg, Exception ex) {
		super(msg, ex);
	}

}

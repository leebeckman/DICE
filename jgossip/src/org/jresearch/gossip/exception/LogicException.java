/*
 * $Id: LogicException.java,v 1.3 2005/06/07 12:32:23 bel70 Exp $
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

package org.jresearch.gossip.exception;

/**
 * Class <code>LogicException</code> represents logic exception. To be thrown
 * only in cases of not critical errors.
 * 
 * @author <code>$Author: bel70 $</code>
 * @version <code>$Revision: 1.3 $</code>
 */
public class LogicException extends JGossipException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5658010920234584195L;

	/**
	 * Constructor <code>LogicException</code> create empty exception
	 */
	protected LogicException() {
	}

	/**
	 * Constructor <code>LogicException</code> create exception with
	 * originally thrown exception
	 * 
	 * @param <code>nestedException</code> original exception
	 */
	public LogicException(Exception nestedException) {
		super(nestedException);
	}

	/**
	 * Constructor <code>LogicException</code> create exception with error
	 * message
	 * 
	 * @param <code>message</code> error message
	 */
	public LogicException(String message) {
		super(message);
	}
}

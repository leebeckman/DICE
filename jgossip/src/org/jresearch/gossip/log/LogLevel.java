/*
 * $Id: LogLevel.java,v 1.3 2005/06/07 12:32:37 bel70 Exp $
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
 *              Alexey Pavlov <alexnet@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */
package org.jresearch.gossip.log;

/**
 * LogLevel
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:37 $
 */
public class LogLevel {

	public static final int ERROR_INT = 2;

	public static final LogLevel ERROR = new LogLevel(ERROR_INT);

	public static final int WARN_INT = 3;

	public static final LogLevel WARN = new LogLevel(WARN_INT);

	public static final int INFO_INT = 7;

	public static final LogLevel INFO = new LogLevel(INFO_INT);

	private int level;

	/**
	 * C'tor LogLevel object.
	 * 
	 * @param level
	 */
	private LogLevel(int level) {
		this.level = level;
	}

	/**
	 * @return
	 */
	public int getLevel() {
		return level;
	}

}

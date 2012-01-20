/*
 * $Id: MD5Digest.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $
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
package org.jresearch.gossip.util;

import java.security.MessageDigest;

import org.apache.log.Logger;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * Utility class MD5Digest. Calculates MD5 hash from input information.
 * 
 * @author <a href="alexnet@sourceforge.net">A. Pavlov</a>
 * @version $version$ $Date: 2005/06/07 12:32:27 $
 */
public class MD5Digest {

	/**
	 * Calculate MD5 hash from username and password combination.
	 * 
	 * @param username
	 *            String with username to digest
	 * @param password
	 *            String with username to digest
	 * @return MD5 hash.
	 */
	public static String digest(String username, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(username.getBytes("UTF8"));
			md.update(password.getBytes("UTF8"));
			byte[] bytes = md.digest();
			return byteArrayToHexString(bytes);
		} catch (Exception e) {
			try {
				Logger log = JGossipLog.getInstance().getAppLogger();
				if (log.isFatalErrorEnabled()) {
					log.fatalError("Can't calculate MD5 hash.", e);
				}
			} catch (SystemException e1) { /* Ignore this exception. */
			}
			return null;
		}
	}

	/**
	 * Convert a byte[] array to readable string format. This makes the "hex"
	 * readable!
	 * 
	 * @return result String buffer in String format
	 * @param in
	 *            byte[] buffer to convert to string format
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		byte ch = 0x00;
		int i = 0;
		if (bytes == null || bytes.length <= 0)
			return null;

		String pseudo[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F" };
		StringBuffer out = new StringBuffer(bytes.length * 2);

		while (i < bytes.length) {
			ch = (byte) (bytes[i] & 0xF0); // Strip off high nibble
			ch = (byte) (ch >>> 4);
			// shift the bits down
			ch = (byte) (ch & 0x0F);
			// must do this is high order bit is on!
			out.append(pseudo[(int) ch]);
			// convert the nibble to a String Character
			ch = (byte) (bytes[i] & 0x0F);
			// Strip off low nibble
			out.append(pseudo[(int) ch]);
			// convert the nibble to a String Character
			i++;
		}
		return new String(out.toString());
	}

}

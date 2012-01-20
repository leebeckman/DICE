/*
 * $$Id: DesEncrypter.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $$
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
 * Created on 23.07.2004
 *
 */
package org.jresearch.gossip.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import Acme.Crypto.DesCipher;

/**
 * @author dbelov
 * 
 */
public class DesEncrypter {

	private DesCipher cipher;

	private final static String LENGTH_SUFF = "@";

	private final static String ENCODING = "UTF8";

	/**
	 * @param passPhrase
	 */
	public DesEncrypter(String passPhrase) {
		cipher = new DesCipher(passPhrase.getBytes());
	}

	/**
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt(String str) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer(str);
		sb.append(LENGTH_SUFF);
		str = sb.toString();
		int padlength = padlength(str);
		byte[] inputBytes = new byte[padlength], // The input text bytes.
		cryptBytes = new byte[padlength]; // The encrypted bytes.
		System.arraycopy(str.getBytes(), 0, inputBytes, 0,
				str.getBytes().length);
		for (int i = 0; i < padlength; i += 8) {
			cipher.encrypt(inputBytes, i, cryptBytes, i);
		}
		return new BASE64Encoder().encode(cryptBytes);
	}

	/**
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public String decrypt(String str) throws IOException {
		byte[] inputBytes = new BASE64Decoder().decodeBuffer(str);// The input
		// text bytes.
		byte[] clearBytes = new byte[inputBytes.length]; // The decrypted
															// bytes.
		for (int i = 0; i < inputBytes.length; i += 8) {
			cipher.decrypt(inputBytes, i, clearBytes, i);
		}
		String result = new String(clearBytes, ENCODING);

		return result.substring(0, result.lastIndexOf(LENGTH_SUFF));
	}

	// To use block ciphers like DES, you need input that is in 8-byte blocks.
	// If your input byte length isn't divisible by 8, we will need to pad it.

	private static int padlength(String s) throws UnsupportedEncodingException {
		int len = s.getBytes(ENCODING).length;
		int mod = len % 8;
		return len + ((mod == 0) ? 0 : 8 - mod);
	}
}
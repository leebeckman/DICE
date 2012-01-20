/*
 * $$Id: MySQLCodec.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $$
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
package org.jresearch.gossip.util;

/**
 * DOCUMENT ME!
 * 
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class MySQLCodec {
	/** It is the default array of symbols to be encoded into the SQL safe form. */
	public static char[] defSpecSymbols = new char[] { '"', '\'', '\\' };

	public static final char ESCAPE = '\\';

	/**
	 * This method is a wrapper for the <code>encode(String text , char[]
	 * specSymbols)</code>
	 * method. It uses the defSpecialSymbols array as special symbols.
	 * 
	 * @param text
	 *            It is the string to be encoded using the default array of
	 *            special symbols.
	 * 
	 * @return the encoded representation of the "text" parameter
	 */
	public static String encode(final String text) {
		return encode(text, defSpecSymbols);
	}

	/**
	 * This method is a wrapper for the <code>encode(String text , char[]
	 * specSymbols)</code>
	 * method. It allows to specify special symbols as elements of the String
	 * object ("String specSymbols" parameter).
	 * 
	 * @param text
	 *            It is the string to be encoded using special symbols from the
	 *            "specSymbols" parameter.
	 * @param specSymbols
	 *            It is the set of special symbols to be encoded within the
	 *            "text" parameter.
	 * 
	 * @return the encoded representation of the "text" parameter
	 */
	public static String encode(final String text, final String specSymbols) {
		final char[] cSpecSymbols;

		if ((specSymbols == null) || (specSymbols.length() == 0)) {
			cSpecSymbols = defSpecSymbols;
		} else {
			cSpecSymbols = specSymbols.toCharArray();
		}

		return encode(text, cSpecSymbols);
	}

	/**
	 * It is the main encoding method. It encodes all special symbols in the
	 * "text" parameter, using the specSymbols parameter as an array of special
	 * symbols to be changed. AlSpecial symbols are translated to the "&#xxx;"
	 * code, where the "xxx" part is the decimal code of the correspondent
	 * special symbol.
	 * 
	 * @param text
	 *            It is the string to be encoded using special symbols from the
	 *            "specSymbols" array.
	 * @param specSymbols
	 *            It is the array of special symbols to be encoded within the
	 *            "text" parameter.
	 * 
	 * @return the encoded representation of the "text" parameter
	 */
	public static String encode(final String text, char[] specSymbols) {
		if (text == null) {
			return null;
		}

		if ((specSymbols == null) || (specSymbols.length == 0)) {
			specSymbols = defSpecSymbols;
		}

		StringBuffer buffer = new StringBuffer(text.length());
		char[] cText = text.toCharArray();

		for (int i = 0; i < cText.length; i++) {
			char cTextChar = cText[i]; // Speed up acess speed
			boolean isSpecial = false;

			for (int ss = 0; ss < specSymbols.length; ss++) {
				if (specSymbols[ss] == cTextChar) {
					isSpecial = true;
					buffer.append(ESCAPE);
					buffer.append(cTextChar);

					break; // Exit the for cycle
				}
			}

			if (!isSpecial) {
				buffer.append(cTextChar);
			}
		}

		return buffer.toString();
	}
}

/*
 * $$Id: MessageProcessor.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $$
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.text.perl.Perl5Util;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.tiling.memo.LruCacheFactory;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class MessageProcessor {

	private static MessageProcessor ourInstance;

	private PatternCacheLRU cache = new PatternCacheLRU(50);

	private Map resultCache = new LruCacheFactory(1000).createCache();

	private static HashMap emoticonsMap;

	private static final String EMOTICON_IMG_TAG = "<img src=\"{0}images/emoticons/{1}.gif\" alt=\"{2}\" title=\"{2}\">";

	private static final String TAG_NOSMILE_NAME = "NOSMILE";

	private static final String TAG_NOSMILE = "[" + TAG_NOSMILE_NAME + "]";

	private static final String TAG_NOSMILE_END = "[/" + TAG_NOSMILE_NAME + "]";

	private Perl5Util perlUtil;

	private static Object lock = new Object();

	/**
	 * DOCUMENT ME!
	 * 
	 * @param bundle
	 *            DOCUMENT ME!
	 */
	public synchronized static void setEmoticonsMap(ResourceBundle bundle) {
		Enumeration keys = bundle.getKeys();
		emoticonsMap = new HashMap();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String pattern = bundle.getString(key);

			emoticonsMap.put(key, HtmlCodec.encode(pattern));
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static HashMap getEmoticonsMap() {
		return emoticonsMap;
	}

	/**
	 * Method <code>getInstance</code> return instance
	 * 
	 * @return instance
	 */
	public static MessageProcessor getInstance() {
		if (ourInstance == null) {
			synchronized (lock) {
				if (ourInstance == null) {
					ourInstance = new MessageProcessor();
				}
			}
		}

		return ourInstance;
	}

	private MessageProcessor() {
		perlUtil = new Perl5Util(cache);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param result
	 *            DOCUMENT ME!
	 * @param cutToLength
	 *            DOCUMENT ME!
	 * @param messages
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String prepareMessage(String result, int cutToLength,
			MessageResources messages) {
		String key = Integer.toString(result.hashCode()) + cutToLength;
		Object value = resultCache.get(key);

		if ((value == null) && !resultCache.containsKey(key)) {
			value = prepare(result, cutToLength, messages);
			resultCache.put(key, value);
		}

		return (String) value;
	}

	/**
	 * @param result
	 * @return
	 */
	public String cleanup(String result) {
		return perlUtil.substitute("s/\\[(.*?)\\]/\r/gsi", result);
	}

	private String prepare(String result, int cutToLength,
			MessageResources messages) {

		if (cutToLength > 0) {
			result = cleanup(result);

			if (result.length() > cutToLength) {
				result = result.substring(0, cutToLength);
				result += "...";
			}
		} else {
			// String expression="s/pattern/replacement/gsi"
			result = perlUtil.substitute(
					"s/\\[b\\](.*?)\\[\\/b\\]/<strong>$1<\\/strong>/gsi",
					result);
			result = perlUtil.substitute(
					"s/\\[i\\](.*?)\\[\\/i\\]/<em>$1<\\/em>/gsi", result);
			result = perlUtil
					.substitute(
							"s/\\[url\\](http[s]?:\\/\\/){1}(.*?)\\[\\/url\\]/<A HREF=\"$1$2\" TARGET=\"_blank\">$2<\\/A>/gsi",
							result);
			result = perlUtil
					.substitute(
							"s/\\[url=(http[s]?:\\/\\/){1}(.*?)\\](.*?)\\[\\/url\\]/<A HREF=\"$1$2\" TARGET=\"_blank\">$3<\\/A>/gsi",
							result);
			result = perlUtil
					.substitute(
							"s/\\[img\\](http[s]?:\\/\\/){1}(.*?)\\[\\/img\\]/<IMG SRC=\"$1$2\" BORDER=0>/gsi",
							result);
			result = perlUtil.substitute(
					"s/\\[HR\\]/<hr size=\"1\" width=\"90%\" noshade>/gsi",
					result);
			result = perlUtil.substitute("s/\\[QUOTE\\]/<blockquote>/gsi",
					result);
			result = perlUtil.substitute(
					"s/\\[\\/QUOTE\\]/<\\/blockquote>/gsi", result);
			result = perlUtil.substitute(
					"s/\\[code\\](.*?)\\[\\/code\\]/<code>$1<\\/code>/gsi",
					result);
			result = nl2br(result);
		}

		return result;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param result
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String nl2br(String result) {
		return perlUtil.substitute("s/\\n/<br>/g", result);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param result
	 *            DOCUMENT ME!
	 * @param messages
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws ConfiguratorException
	 *             DOCUMENT ME!
	 */

	public String processEmoticons(String result, MessageResources messages)
			throws ConfiguratorException {
		// normalize NOSMILE tag to upper case
		result = perlUtil.substitute("s/" + TAG_NOSMILE_NAME + "\\]/"
				+ TAG_NOSMILE_NAME + "\\]/gsi", result);

		List tokens = splitOnNoSmileTag(result);
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < tokens.size(); i++) {
			MPToken token = (MPToken) tokens.get(i);
			if (token.processSmiles) {
				res.append(replaceEmoticons(token.text, messages));
			} else {
				res.append(token.text);
			}
		}
		return res.toString();
	}

	private String replaceEmoticons(String result, MessageResources messages)
			throws ConfiguratorException {
		Iterator it = emoticonsMap.keySet().iterator();
		Object[] args = {
				Configurator.getInstance().get(IConst.CONFIG.WEB_ROOT), "", "" };
		// For tests - use next string
		// Object[] args = { "WEB_ROOT/", "", "" };

		while (it.hasNext()) {
			String key = (String) it.next();
			String pattern = (String) emoticonsMap.get(key);
			args[1] = key;
			args[2] = messages.getMessage(Configurator.getInstance().getLocale(
					IConst.CONFIG.DEFAULT_LOCALE), key);
			result = replace(result, pattern, MessageFormat.format(
					EMOTICON_IMG_TAG, args));
		}

		return result;
	}

	private List splitOnNoSmileTag(String result) {
		ArrayList tokens = new ArrayList();
		StringBuffer sb = new StringBuffer(result);
		int ind1 = 0;
		int ind2 = 0;
		int length = sb.length();
		while (ind1 < length && ind2 < length) {
			ind2 = sb.indexOf(TAG_NOSMILE, ind1);
			if (ind2 == -1) {
				ind2 = length;
			}
			tokens.add(new MPToken(true, sb.substring(ind1, ind2)));
			if (ind2 != length) {
				ind1 = ind2 + TAG_NOSMILE.length();
				ind2 = sb.indexOf(TAG_NOSMILE_END, ind1);
				if (ind2 == -1) {
					ind2 = length;
				}
				tokens.add(new MPToken(false, sb.substring(ind1, ind2)));
				if (ind2 != length) {
					ind1 = ind2 + TAG_NOSMILE_END.length();
				}
			}
		}
		return tokens;
	}

	private class MPToken {

		/**
		 * @param processSmiles
		 * @param text
		 */
		protected MPToken(boolean processSmiles, String text) {
			super();
			this.processSmiles = processSmiles;
			this.text = text;
		}

		boolean processSmiles;

		String text;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param result
	 *            DOCUMENT ME!
	 * @param pattern
	 *            DOCUMENT ME!
	 * @param replace
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws IllegalArgumentException
	 *             DOCUMENT ME!
	 */
	public String replace(String result, String pattern, String replace) {
		if (result == null) {
			throw new IllegalArgumentException("result parameter, value = "
					+ result);
		}

		if (replace == null) {
			throw new IllegalArgumentException("replace parameter, value = "
					+ replace);
		}

		if ((pattern == null) || "".equals(pattern)) {
			throw new IllegalArgumentException("pattern parameter, value = "
					+ pattern);
		}

		if ("".equals(result)) {
			return result;
		}

		int s = 0;
		int e = 0;
		StringBuffer sb = new StringBuffer(result);

		while ((e = sb.indexOf(pattern, s)) > -1) {
			sb.replace(e, e + pattern.length(), replace);
			s = e + replace.length();
		}

		return sb.toString();
	}
}
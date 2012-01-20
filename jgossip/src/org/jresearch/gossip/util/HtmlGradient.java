/*
 * $Id: HtmlGradient.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $
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
 * Created on 11.07.2004
 *
 */
package org.jresearch.gossip.util;

import java.util.Map;

import org.tiling.memo.LruCacheFactory;

/**
 * @author Dmitry Belov
 * 
 */
public class HtmlGradient {

	private static Map resultCache = new LruCacheFactory(1000).createCache();

	private final static String ASTR = "*";

	private final static String TABLE_START1 = "<table  style=\"empty-cells: show;\" cellspacing=\"0\" cellpadding=\"0\" height=\"";

	private final static String TABLE_START2 = "\" width=\"";

	private final static String TABLE_START3 = "\" >";

	private final static String TABLE_END = "</table>";

	private final static String TR_START = "<tr>";

	private final static String TR_END = "</tr>";

	private final static String TD_START = "<td width=\"2\" bgcolor=\"";

	private final static String TD_END = "\"></td>";

	private final static String SIZE = "100%";

	/**
	 * Comment for <code>DIRECTION_V</code>
	 */
	public final static int DIRECTION_V = 0;

	/**
	 * Comment for <code>DIRECTION_H</code>
	 */
	public final static int DIRECTION_H = 1;

	/**
	 * @param startColor
	 * @param endColor
	 * @param dens
	 * @param height
	 * @return
	 */
	public static String makeHGradientHtml(String startColor, String endColor,
			int dens, String height) {

		return makeGradientHtml(startColor, endColor, dens, height, DIRECTION_H);

	}

	/**
	 * @param startColor
	 * @param endColor
	 * @param dens
	 * @param width
	 * @return
	 */
	public static String makeVGradientHtml(String startColor, String endColor,
			int dens, String width) {
		return makeGradientHtml(startColor, endColor, dens, width, DIRECTION_V);
	}

	/**
	 * @param startColor
	 * @param endColor
	 * @param dens
	 * @param size
	 * @param direction
	 * @return
	 */
	public static String makeGradientHtml(String startColor, String endColor,
			int dens, String size, int direction) {
		StringBuffer key = new StringBuffer();
		key.append(startColor);
		key.append(ASTR);
		key.append(endColor);
		key.append(ASTR);
		key.append(dens);
		key.append(ASTR);
		key.append(size);
		key.append(ASTR);
		key.append(direction);
		Object value = resultCache.get(key);

		if ((value == null) && !resultCache.containsKey(key)) {
			String height = SIZE;
			String width = SIZE;
			String cell_start = TD_START;
			String cell_end = TD_END;
			switch (direction) {
			case DIRECTION_V:
				width = size;
				cell_start = TR_START + cell_start;
				cell_end += TR_END;
				break;
			case DIRECTION_H:
				height = size;
				break;
			}

			int[] sColor = { Integer.parseInt(startColor.substring(0, 2), 16),
					Integer.parseInt(startColor.substring(2, 4), 16),
					Integer.parseInt(startColor.substring(4), 16) };
			int[] cDelta = {
					Integer.parseInt(endColor.substring(0, 2), 16) - sColor[0],
					Integer.parseInt(endColor.substring(2, 4), 16) - sColor[1],
					Integer.parseInt(endColor.substring(4), 16) - sColor[2] };

			StringBuffer result = new StringBuffer();
			result.append(TABLE_START1);
			result.append(height);
			result.append(TABLE_START2);
			result.append(width);
			result.append(TABLE_START3);
			if (direction == DIRECTION_H) {
				result.append(TR_START);
			}

			for (int i = 0; i < dens; i++) {
				result.append(cell_start);
				result.append(Integer.toHexString(sColor[0]
						+ Math.round(i * cDelta[0] / dens)));
				result.append(Integer.toHexString(sColor[1]
						+ Math.round(i * cDelta[1] / dens)));
				result.append(Integer.toHexString(sColor[2]
						+ Math.round(i * cDelta[2] / dens)));
				result.append(cell_end);
			}
			if (direction == DIRECTION_H) {
				result.append(TR_END);
			}
			result.append(TABLE_END);
			value = result.toString();
			resultCache.put(key, value);
		}

		return (String) value;
	}
}
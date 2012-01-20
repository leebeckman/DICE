/*
 * $$Id: CacheResponseWrapper.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $$
 * 
 * ***** BEGIN LICENSE BLOCK ***** The contents of this file are subject to the
 * Mozilla Public License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JGossip forum code.
 * 
 * The Initial Developer of the Original Code is the JResearch, Org. Portions
 * created by the Initial Developer are Copyright (C) 2004 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Dmitry Belov <bel@jresearch.org>, Jayson Falkner
 * <jayson@jspinsider.com>
 * 
 * ***** END LICENSE BLOCK *****
 */
package org.jresearch.gossip.filters.cache;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CacheResponseWrapper extends HttpServletResponseWrapper {

	protected HttpServletResponse origResponse = null;

	protected ServletOutputStream stream = null;

	protected PrintWriter writer = null;

	protected OutputStream cache = null;

	public CacheResponseWrapper(HttpServletResponse response, OutputStream cache) {
		super(response);
		origResponse = response;
		this.cache = cache;
	}

	public ServletOutputStream createOutputStream() throws IOException {
		return (new CacheResponseStream(origResponse, cache));
	}

	public void flushBuffer() throws IOException {
		stream.flush();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (writer != null) {
			throw new IllegalStateException(
					"getWriter() has already been called!");
		}

		if (stream == null)
			stream = createOutputStream();
		return (stream);
	}

	public PrintWriter getWriter() throws IOException {
		if (writer != null) {
			return (writer);
		}

		if (stream != null) {
			throw new IllegalStateException(
					"getOutputStream() has already been called!");
		}

		stream = createOutputStream();
		// Reuse content's encoding
		writer = new PrintWriter(new OutputStreamWriter(stream, origResponse
				.getCharacterEncoding()));
		return (writer);
	}
}
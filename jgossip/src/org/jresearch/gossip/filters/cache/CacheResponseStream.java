/*
 * $$Id: CacheResponseStream.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $$
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class CacheResponseStream extends ServletOutputStream {

	protected boolean closed = false;

	protected HttpServletResponse response = null;

	protected ServletOutputStream output = null;

	protected OutputStream cache = null;

	public CacheResponseStream(HttpServletResponse response, OutputStream cache)
			throws IOException {
		super();
		closed = false;
		this.response = response;
		this.cache = cache;
	}

	public void close() throws IOException {
		if (closed) {
			throw new IOException("This output stream has already been closed");
		}
		cache.close();
		closed = true;
	}

	public void flush() throws IOException {
		if (closed) {
			throw new IOException("Cannot flush a closed output stream");
		}
		cache.flush();
	}

	public void write(int b) throws IOException {
		if (closed) {
			throw new IOException("Cannot write to a closed output stream");
		}
		cache.write((byte) b);
	}

	public void write(byte b[]) throws IOException {
		write(b, 0, b.length);
	}

	public void write(byte b[], int off, int len) throws IOException {
		if (closed) {
			throw new IOException("Cannot write to a closed output stream");
		}
		cache.write(b, off, len);
	}

	public boolean closed() {
		return (this.closed);
	}

	public void reset() {
		// noop
	}
}
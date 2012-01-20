/*
 * $$Id: CompressionFilter.java,v 1.3 2005/06/07 12:31:56 bel70 Exp $$
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
 *              Dmitry Belov <bel@jresearch.org>,
 *              Jayson Falkner <jayson@jspinsider.com> 
 *
 * ***** END LICENSE BLOCK ***** */
package org.jresearch.gossip.filters.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log.Logger;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * Filter that compresses output with gzip (assuming that browser supports
 * gzip).
 */
public class CompressionFilter implements Filter {

	public static final String GZIP_NOT_ALLOWED = "GZIP_NOT_ALLOWED";

	private FilterConfig config;

	/**
	 * If browser does not support gzip, invoke resource normally. If browser
	 * <I>does </I> support gzip, set the Content-Encoding response header and
	 * invoke resource with a wrapped response that collects all the output.
	 * Extract the output and write it into a gzipped byte array. Finally, write
	 * that array to the client's output stream.
	 * 
	 * @param request
	 *            DOCUMENT ME!
	 * @param response
	 *            DOCUMENT ME!
	 * @param chain
	 *            DOCUMENT ME!
	 * 
	 * @throws ServletException
	 *             DOCUMENT ME!
	 * @throws IOException
	 *             DOCUMENT ME!
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		try {
			if ((!isGzipSupported(req))
					|| (IConst.VALUES.FALSE.equals(Configurator.getInstance()
							.get(IConst.CONFIG.GZIP_COMPRESS)))
					|| (req.getParameter(GZIP_NOT_ALLOWED) != null)) {
				try {
					Logger log = JGossipLog.getInstance().getAppLogger();
					if (log.isDebugEnabled()) {
						log.debug("Invoke resource normally.");
					}
				} catch (SystemException e1) { /* Ignore this exception. */
				}
				chain.doFilter(req, res);
			} else {
				GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(
						res);
				chain.doFilter(req, wrappedResponse);
				wrappedResponse.finishResponse();
			}
		} catch (ConfiguratorException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Store the FilterConfig object in case subclasses want it.
	 * 
	 * @param config
	 *            DOCUMENT ME!
	 * 
	 * @throws ServletException
	 *             DOCUMENT ME!
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	protected FilterConfig getFilterConfig() {
		return (config);
	}

	/**
	 * DOCUMENT ME!
	 */
	public void destroy() {
	}

	private boolean isGzipSupported(HttpServletRequest req) {
		String browserEncodings = req.getHeader("Accept-Encoding");

		return ((browserEncodings != null) && (browserEncodings.indexOf("gzip") != -1));
	}
}
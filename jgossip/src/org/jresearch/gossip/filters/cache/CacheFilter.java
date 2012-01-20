/*
 * $$Id: CacheFilter.java,v 1.3 2005/06/07 12:32:31 bel70 Exp $$
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.user.User;

public class CacheFilter implements Filter {

	ServletContext sc;

	FilterConfig fc;

	long cacheTimeout = Long.MAX_VALUE;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// check uri -- fix this, shouldn't have to ignore these
		String uri = request.getRequestURI();
		if (uri == null || uri.equals("") || uri.equals("/")) {
			chain.doFilter(request, response);
			return;
		}
		// check if was a resource that shouldn't be cached.
		String r = sc.getRealPath("");
		String path = fc.getInitParameter(uri);
		if (path != null && path.equals("nocache")) {
			chain.doFilter(request, response);
			return;
		}
		path = r + path;

		// customize to match parameters
		String id = request.getRequestURI()
				+ request.getQueryString()
				+ "__"
				+ ((User) request.getSession().getAttribute(
						IConst.SESSION.USER_KEY)).getName();
		// optionally append i18n sensitivity
		String localeSensitive = fc.getInitParameter("locale-sensitive");
		if (localeSensitive != null) {
			StringWriter ldata = new StringWriter();
			Enumeration locales = request.getLocales();
			while (locales.hasMoreElements()) {
				Locale locale = (Locale) locales.nextElement();
				ldata.write(locale.getISO3Language());
			}
			id = id + ldata.toString();
		}
		File tempDir = (File) sc.getAttribute("javax.servlet.context.tempdir");

		// get possible cache
		String temp = tempDir.getAbsolutePath();
		System.out.println(temp);
		File file = new File(temp + id);

		// get current resource
		if (path == null) {
			path = sc.getRealPath(request.getRequestURI());
		}
		File current = new File(path);

		try {
			long now = Calendar.getInstance().getTimeInMillis();
			// set timestamp check
			if (!file.exists()
					|| (file.exists() && current.lastModified() > file
							.lastModified())
					|| cacheTimeout < now - file.lastModified()) {
				String name = file.getAbsolutePath();

				name = name.substring(0,
						name.lastIndexOf(File.separatorChar) == -1 ? 0 : name
								.lastIndexOf(File.separatorChar));
				new File(name).mkdirs();

				FileOutputStream fos = new FileOutputStream(file);
				CacheResponseWrapper wrappedResponse = new CacheResponseWrapper(
						response, fos);
				chain.doFilter(req, wrappedResponse);

				fos.flush();
				fos.close();
			}
		} catch (ServletException e) {
			if (!file.exists()) {
				throw new ServletException(e);
			}
		} catch (IOException e) {
			if (!file.exists()) {
				throw e;
			}
		}

		FileInputStream fis = new FileInputStream(file);
		String mt = sc.getMimeType(request.getRequestURI());
		response.setContentType(mt);
		ServletOutputStream sos = res.getOutputStream();
		for (int i = fis.read(); i != -1; i = fis.read()) {
			sos.write((byte) i);
		}
	}

	public void init(FilterConfig filterConfig) {
		this.fc = filterConfig;
		// set the inital timeout
		String ct = fc.getInitParameter("cacheTimeout");
		if (ct != null) {
			cacheTimeout = 60 * 1000 * Long.parseLong(ct);
		}
		// set reference to servlet context
		this.sc = filterConfig.getServletContext();
	}

	public void destroy() {
		this.sc = null;
		this.fc = null;
	}
}
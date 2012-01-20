/*
 * Created on Oct 6, 2003
 *
 */
package org.jresearch.gossip.tags;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.list.RecordsData;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class BlockUrlTag extends TagSupport {

	private int block;

	private int increase;

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws JspException
	 *             DOCUMENT ME!
	 */
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest req = (HttpServletRequest) pageContext
					.getRequest();
			HttpServletResponse response = (HttpServletResponse) pageContext
					.getResponse();
			Enumeration pnames = req.getParameterNames();
			StringBuffer url = new StringBuffer((String) req
					.getAttribute(IConst.REQUEST.REQUEST_URI)
					+ "?");

			this.block = ((RecordsData) req
					.getAttribute(IConst.REQUEST.RECORDS_DATA)).getCurrBlock();

			while (pnames.hasMoreElements()) {
				String name = (String) pnames.nextElement();

				if (req.getParameter(name) != null
						&& !req.getParameter(name).equals("")) {
					url.append(name);
					url.append("=");

					if (name.equals("block")) {
						url
								.append(this.block
										+ (this.increase * ((RecordsData) req
												.getAttribute(IConst.REQUEST.RECORDS_DATA))
												.getBlockSize()));
					} else {
						url.append(req.getParameter(name));
					}

					if (pnames.hasMoreElements()) {
						url.append("&");
					}

				}
			}

			if (req.getParameter("block") == null) {
				url.append("&block=");
				url.append(this.block
						+ (this.increase * ((RecordsData) req
								.getAttribute(IConst.REQUEST.RECORDS_DATA))
								.getBlockSize()));
			}

			JspWriter out = pageContext.getOut();
			out.print(response.encodeURL(url.toString()));
		} catch (Exception ex) {
			throw new JspException("error in BlockUrlTag tag:", ex);
		}

		return (SKIP_BODY);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getBlock() {
		return this.block;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setBlock(int i) {
		this.block = i;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public int getIncrease() {
		return this.increase;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param i
	 */
	public void setIncrease(int i) {
		this.increase = (i > 0) ? 1 : (-1);
	}
}
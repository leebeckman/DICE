/*
 * Created on 09.05.2003
 *
 */
package org.jresearch.gossip.tags;

import java.text.DecimalFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jresearch.gossip.IConst;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class BuildTimeTag extends TagSupport {
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
			if (pageContext.getRequest().getAttribute(
					IConst.REQUEST.START_TIME_KEY) == null) {
				pageContext.getRequest().setAttribute(
						IConst.REQUEST.START_TIME_KEY, new Date());
			}
		} catch (Exception ex) {
			throw new JspException("error in BuildTimeTag tag:", ex);
		}

		return (EVAL_BODY_INCLUDE);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 * 
	 * @throws JspException
	 *             DOCUMENT ME!
	 */
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();

			if (pageContext.getRequest().getAttribute(
					IConst.REQUEST.START_TIME_KEY) != null) {
				DecimalFormat df = new DecimalFormat();
				df.applyPattern("#0.00####");

				double buildtime = ((new Date()).getTime() - ((Date) pageContext
						.getRequest().getAttribute(
								IConst.REQUEST.START_TIME_KEY)).getTime()) * 0.001;
				out.print(df.format(buildtime));
			}
		} catch (Exception ex) {
			throw new JspException("error in BuildTimeTag tag:", ex);
		}

		return (EVAL_PAGE);
	}
}

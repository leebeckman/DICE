/*
 * Created on 12.07.2004
 *
 */
package org.jresearch.gossip.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jresearch.gossip.util.HtmlGradient;

/**
 * @author dbelov
 * 
 */
public class GradientTag extends TagSupport {

	private String startColor;

	private String endColor;

	private int dens = 128;

	private String height;

	/**
	 * @return Returns the dens.
	 */
	public int getDens() {
		return dens;
	}

	/**
	 * @param dens
	 *            The dens to set.
	 */
	public void setDens(int dens) {
		this.dens = dens;
	}

	/**
	 * @return Returns the endColor.
	 */
	public String getEndColor() {
		return endColor;
	}

	/**
	 * @param endColor
	 *            The endColor to set.
	 */
	public void setEndColor(String endColor) {
		this.endColor = endColor;
	}

	/**
	 * @return Returns the height.
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            The height to set.
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return Returns the startColor.
	 */
	public String getStartColor() {
		return startColor;
	}

	/**
	 * @param startColor
	 *            The startColor to set.
	 */
	public void setStartColor(String startColor) {
		this.startColor = startColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(HtmlGradient.makeHGradientHtml(startColor, endColor,
					dens, height));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return super.SKIP_BODY;
	}
}
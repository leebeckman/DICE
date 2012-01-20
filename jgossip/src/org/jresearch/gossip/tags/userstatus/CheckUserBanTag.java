/*
 * Created on 01.09.2004
 *
 */
package org.jresearch.gossip.tags.userstatus;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.jresearch.gossip.am.ban.BanGuard;
import org.jresearch.gossip.constants.BanType;
import org.jresearch.gossip.exception.SystemException;

/**
 * @author dbelov
 * 
 */
public class CheckUserBanTag extends TagSupport {

	private String var;

	private String login;

	/**
	 * @return Returns the login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            The login to set.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Returns the var.
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var
	 *            The var to set.
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @throws JspException
	 */
	private void eval() throws JspException {
		this.login = (String) ExpressionEvaluatorManager.evaluate("login",
				this.login, String.class, this, pageContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		eval();
		BanGuard guard;
		try {
			guard = BanGuard.getInstance();

			Boolean isBanned = new Boolean(guard.checkBan(this.login,
					BanType.LOGIN));
			if (this.var != null)
				pageContext.setAttribute(var, isBanned);
			if (isBanned.booleanValue()) {
				return EVAL_BODY_INCLUDE;
			}
			return SKIP_BODY;
		} catch (SystemException e) {
			throw new JspException(e);
		}
	}
}
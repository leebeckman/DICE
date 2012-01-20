/*
 * Created on 01.09.2004
 *
 */
package org.jresearch.gossip.tags.userstatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.am.ban.BanGuard;
import org.jresearch.gossip.am.model.IPermissionGuard;
import org.jresearch.gossip.am.values.PermissionPoint;
import org.jresearch.gossip.exception.SystemException;

/**
 * @author dbelov
 * 
 */
public class CheckAccessTag extends TagSupport {

	private String var;

	private String objectId;

	private String operationId;

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
	 * @return Returns the objectId.
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            The objectId to set.
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return Returns the operationId.
	 */
	public String getOperationId() {
		return operationId;
	}

	/**
	 * @param operationId
	 *            The operationId to set.
	 */
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		BanGuard guard;
		try {
			Boolean isAlloved = haveAccess((HttpServletRequest) pageContext
					.getRequest());
			if (this.var != null)
				pageContext.setAttribute(var, isAlloved);
			if (isAlloved.booleanValue()) {
				return EVAL_BODY_INCLUDE;
			}
			return SKIP_BODY;
		} catch (SystemException e) {
			throw new JspException(e);
		}
	}

	private Boolean haveAccess(HttpServletRequest request)
			throws SystemException {
		HttpSession session = request.getSession();
		if (session.getAttribute(IConst.SESSION.PERMISSION_GUARD_KEY) == null) {
			throw new SystemException("PERMISSION_GUARD not found in session");
		}
		IPermissionGuard guard = (IPermissionGuard) session
				.getAttribute(IConst.SESSION.PERMISSION_GUARD_KEY);

		PermissionPoint point = new PermissionPoint(Integer
				.parseInt(this.objectId), Integer.parseInt(this.operationId));
		return new Boolean(guard.checkPermission(point));

	}
}
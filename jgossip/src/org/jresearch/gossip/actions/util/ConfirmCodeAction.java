/*
 * Created on 07.07.2004
 *
 */
package org.jresearch.gossip.actions.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.log.avalon.JGossipLog;
import org.jresearch.gossip.util.PictureGenerator;

/**
 * @author dbelov
 * 
 */
public class ConfirmCodeAction extends Action {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		HttpSession session = request.getSession();
		UserDAO dao = UserDAO.getInstance();
		String confirmcode = dao.generatePassword();
		session.setAttribute(IConst.SESSION.CONFIRM_CODE, confirmcode);
		res.setContentType(IConst.JSP.JPG_CONTENT_TYPE);
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setDateHeader("Expires", 0);
		Logger log = JGossipLog.getInstance().getAppLogger();
		try {

			if (log.isDebugEnabled()) {
				log.debug("ConfirmCodeAction : image generator is started");
			}
			PictureGenerator.getInstance().generatePicture(confirmcode,
					res.getOutputStream());
			res.flushBuffer();
			if (log.isDebugEnabled()) {
				log.debug("ConfirmCodeAction : image generator is finished");
			}
		} catch (Exception e) {
			log.error(" error in ConfirmCodeAction :", e);
			throw new SystemException(e);
		}
		return null;
	}
}
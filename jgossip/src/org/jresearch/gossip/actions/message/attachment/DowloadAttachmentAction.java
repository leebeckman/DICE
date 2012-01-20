/*
 * Created on 06.07.2004
 *
 */
package org.jresearch.gossip.actions.message.attachment;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.forum.attachment.FileData;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.forms.ProcessAttachForm;

/**
 * @author dbelov
 * 
 */
public class DowloadAttachmentAction extends Action {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		ForumDAO dao = ForumDAO.getInstance();
		ProcessAttachForm paForm = (ProcessAttachForm) form;
		if (Configurator.getInstance().getBoolean(
				IConst.CONFIG.ENABLE_FILE_UPLOAD)) {
			FileData fData = dao
					.getAttachment(Integer.parseInt(paForm.getId()));
			String mime = fData.getInfo().getContentType();
			response.setContentType(mime);
			if (!(IConst.JSP.JPG_CONTENT_TYPE.equals(mime)
					|| IConst.JSP.GIF_CONTENT_TYPE.equals(mime) || IConst.JSP.PNG_CONTENT_TYPE
					.equals(mime))) {
				response.setHeader("Content-disposition",
						"attachment;filename=\"" + fData.getInfo().getName()
								+ "\"");
			}
			response.addHeader("Content-description", fData.getInfo()
					.getDescription());
			ServletOutputStream outStream = response.getOutputStream();
			outStream.write(fData.getData());
			outStream.flush();
			outStream.close();
		} else {
			return (mapping.findForward(IConst.TOKEN.DENIED));
		}

		return null;
	}
}
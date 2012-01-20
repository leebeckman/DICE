/*
 * $$Id: AddMessageAction.java,v 1.5 2005/06/07 12:31:54 bel70 Exp $$
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
 *              Dmitry Belov <bel@jresearch.org>
 *
 * ***** END LICENSE BLOCK ***** */
/*
 * Created on Sep 20, 2003
 *
 */
package org.jresearch.gossip.actions.message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.am.ban.BanGuard;
import org.jresearch.gossip.beans.forum.Forum;
import org.jresearch.gossip.beans.forum.attachment.FileData;
import org.jresearch.gossip.beans.forum.attachment.FileDataInfo;
import org.jresearch.gossip.beans.subscription.Subscriber;
import org.jresearch.gossip.beans.user.User;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.constants.BanType;
import org.jresearch.gossip.constants.UserStatus;
import org.jresearch.gossip.dao.ForumDAO;
import org.jresearch.gossip.dao.UserDAO;
import org.jresearch.gossip.exception.SystemException;
import org.jresearch.gossip.forms.FileUploadForm;
import org.jresearch.gossip.forms.MessageForm;
import org.jresearch.gossip.mail.MailMessage;
import org.jresearch.gossip.mail.MailQueue;
import org.jresearch.gossip.util.HtmlCodec;
import org.jresearch.gossip.util.MessageProcessor;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class AddMessageAction extends BaseAction {

	/**
	 * DOCUMENT ME!
	 * 
	 * @param mapping
	 *            DOCUMENT ME!
	 * @param form
	 *            DOCUMENT ME!
	 * @param request
	 *            DOCUMENT ME!
	 * @param response
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SystemException {
		HttpSession session = request.getSession();
		MessageResources messages = getResources(request);
		MessageForm messageForm = (MessageForm) form;
		User user = (User) session.getAttribute(IConst.SESSION.USER_KEY);
		ForumDAO dao = ForumDAO.getInstance();
		UserDAO userdao = UserDAO.getInstance();
		ActionMessages errors = new ActionMessages();
		StringBuffer forward = new StringBuffer();
		Configurator config = Configurator.getInstance();

		if ((user.getStatus() > 0)) {
			messageForm.setEmail(user.getInfo().getEmail());
			messageForm.setName(user.getName());
		} else { // validation if user is not registered yet

			if (messageForm.getName().trim().equals("")) {

				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.ERR20"));
			}

			if (messageForm.getEmail().trim().equals("")) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.ERR19"));
			}
		}

		// Report any errors we have discovered back to the original form
		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			return (mapping.getInputForward());
		}

		try {

			boolean announce = false;
			boolean isUserMod = dao.checkMod(Integer.parseInt(messageForm
					.getFid()), user);
			Forum currForum = dao.getForumInfo(Integer.parseInt(messageForm
					.getFid()));

			// check access rights if forum invisible
			if ((currForum.getLocked() == IConst.Forum.STATUS_INVISIBLE)
					&& (user.getStatus() < Integer.parseInt(Configurator
							.getInstance().get(IConst.CONFIG.INVADER1)))) {
				return (mapping.findForward(IConst.TOKEN.DENIED));
			}

			// check access rights if forum topics are locked
			if (((currForum.getLocked() == IConst.Forum.STATUS_TOPICS_LOCKED) && messageForm
					.getTid().equals(""))
					&& (!isUserMod)) {
				return (mapping.findForward(IConst.TOKEN.DENIED));
			}

			// check user access rights if completely forum is
			// locked
			if ((currForum.getLocked() == IConst.Forum.STATUS_COMPLETELY_LOCKED)
					&& (!isUserMod)) {
				return (mapping.findForward(IConst.TOKEN.DENIED));
			}
			// insert new thread if it is necessary
			if (messageForm.getTid().equals("")) {
				messageForm.setTid(dao.insertNewThread(messageForm.getFid()));

				announce = ((dao.isUserMod(user.getName()) || (user.getStatus() > 7)) && IConst.VALUES.TRUE
						.equals(messageForm.getAnnounce()));
			} else {

				// check user access rights if current topic is
				// locked
				if ((dao.getThreadInfo(Integer.parseInt(messageForm.getTid()))
						.getLocked() == IConst.Topic.STATUS_LOCKED)
						&& (!isUserMod)) {
					return (mapping.findForward(IConst.TOKEN.DENIED));
				}
			}
			// mark username for not registered users
			if (user.getStatus() == 0) {
				messageForm.setName("<" + messageForm.getName() + ">");
			}

			int mid = dao.addMessage(messageForm, request.getRemoteAddr(),
					announce);
			// save attachment
			if (Configurator.getInstance().getBoolean(
					IConst.CONFIG.ENABLE_FILE_UPLOAD)) {
				saveAttach(request, mid, ((FileUploadForm) messageForm)
						.getFileData());
			}
			forward.append("/ShowMessage.do?fid=");
			forward.append(messageForm.getFid());
			forward.append("&tid=");
			forward.append(messageForm.getTid());
			forward.append("&mid=");
			forward.append(mid);

			log(request, "logs.LOG5", messageForm.getTid() + " fid="
					+ messageForm.getFid());

			// e-mail all the subscribors of this thread...
			ArrayList subscrbe = dao.getSubscribersList(messageForm.getTid(),
					messageForm.getName());

			if (subscrbe.size() > 0) {
				StringBuffer siteUrl = new StringBuffer();
				siteUrl.append(request.getServerName());
				siteUrl.append(":");
				siteUrl.append(request.getServerPort());
				siteUrl.append(request.getContextPath());
				siteUrl.append(config.get(IConst.CONFIG.MODULE_PREFIX));

				String tSubj = dao.getThreadSubject(messageForm.getTid());
				MessageProcessor mp = MessageProcessor.getInstance();
				String mess = mp.prepareMessage(HtmlCodec.encode(messageForm
						.getText()), 0, messages);
				mess = mp.cleanup(mess);

				/*
				 * {0} - subsriber's login {1} - site url {2} - show message url
				 * {3} - topic title {4} - new message text {5} - new message
				 * sender {6} - site name
				 */
				Object[] messArgs = new Object[] { "", siteUrl.toString(),
						forward.toString(), tSubj, mess,
						HtmlCodec.encode(messageForm.getName()),
						config.get(IConst.CONFIG.SITE_NAME) };
				MailQueue queue = (MailQueue) session.getServletContext()
						.getAttribute(IConst.CONTEXT.MAIL_QUEUE);

				Iterator it = subscrbe.iterator();
				BanGuard guard = BanGuard.getInstance();
				while (it.hasNext()) {
					Subscriber s = (Subscriber) it.next();
					if (!guard.checkBan(s.getEmail(), BanType.EMAIL)) {
						messArgs[0] = HtmlCodec.encode(s.getName());

						queue
								.push(new MailMessage(
										messages
												.getMessage(
														config
																.getLocale(IConst.CONFIG.DEFAULT_LOCALE),
														"mails.NEW_MESSAGE",
														messArgs),
										messages
												.getMessage(
														config
																.getLocale(IConst.CONFIG.DEFAULT_LOCALE),
														"mails.NEW_MESSAGE_SUBJ"),
										config.get(IConst.CONFIG.ADMINMAIL),
										messages
												.getMessage(
														config
																.getLocale(IConst.CONFIG.DEFAULT_LOCALE),
														"mails.FORUM_ADMIN"), s
												.getEmail(), s.getName()));
					}
				}
			}

			// subscribe user to e-mail from this thread...
			if (IConst.VALUES.TRUE.equals(messageForm.getSubscribe())) {
				dao.subscribe(messageForm.getTid(), messageForm.getEmail(),
						messageForm.getName());
			}

			session.removeAttribute(IConst.REQUEST.CURR_THREAD);

			if (user.getStatus() != UserStatus.GUEST) {

				// set(update) last visit date for this thread
				HashMap last_intime = (HashMap) session
						.getAttribute(IConst.SESSION.LAST_INTIME);

				if (last_intime.containsKey(messageForm.getTid())) {
					last_intime.remove(messageForm.getTid());
				}

				last_intime.put(messageForm.getTid(), new Date());
			}
		} catch (SQLException sqle) {
			getServlet().log("Connection.process", sqle);
			throw new SystemException(sqle);
		} catch (FileNotFoundException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		}

		return (new ActionForward(forward.toString(), true));
	}

	private void saveAttach(HttpServletRequest request, int mid, List[] files)
			throws FileNotFoundException, IOException, SystemException,
			SQLException {
		ForumDAO dao = ForumDAO.getInstance();

		FileData[] filedata = new FileData[files[0].size()];
		// populate filedata
		for (int i = 0; i < filedata.length; i++) {
			FileData fdata = new FileData();
			FileDataInfo finfo = new FileDataInfo();
			FormFile file = (FormFile) files[0].get(i);

			finfo.setContentType(file.getContentType());
			fdata.setData(file.getFileData());

			finfo.setDescription((String) files[1].get(i));
			finfo.setMessageId(mid);
			finfo.setName(file.getFileName());
			finfo.setSize(file.getFileSize());

			fdata.setInfo(finfo);

			filedata[i] = fdata;
		}
		// save filedata
		dao.saveAttachments(filedata);

	}
}
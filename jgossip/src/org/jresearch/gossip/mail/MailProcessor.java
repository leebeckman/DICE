/*
 * $$Id: MailProcessor.java,v 1.4 2005/06/09 08:27:45 bel70 Exp $$
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
 *              Simone Chiaretta <simo@users.sourceforge.net>
 *
 * ***** END LICENSE BLOCK ***** */
/*
 * Created on Oct 28, 2003
 *
 */
package org.jresearch.gossip.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.log.LogLevel;
import org.jresearch.gossip.log.avalon.JGossipLog;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class MailProcessor implements
		com.evelopers.common.concurrent.QueueMessageProcessor {
	public static Session _mailSession;

	private static SmtpAuthenticator _authenticator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.evelopers.common.concurrent.QueueMessageProcessor#processMessage(java.lang.Object)
	 */
	public void processMessage(Object mess) {
		MailMessage mailmess = (MailMessage) mess;

		// Get (or create and cache) mail session
		Session s = null;
		Transport trans = null;
		try {
			s = getMailSession();

			// Create new message
			MimeMessage msg = new MimeMessage(s);
			Configurator config = Configurator.getInstance();
			// Put data from request into message
			msg.setText(mailmess.getMessagetext(),config.get(IConst.CONFIG.CHARSET));
			msg.setSubject(mailmess.getSubject(),config.get(IConst.CONFIG.CHARSET));

			Address fromAddr = new InternetAddress(mailmess.getAddrfrom(),
					mailmess.getNamefrom());
			msg.setFrom(fromAddr);

			Address toAddr = new InternetAddress(mailmess.getAddrto(), mailmess
					.getNameto());
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.addHeaderLine("Content-Type: text/html; charset=\""+config.get(IConst.CONFIG.CHARSET)+"\"");
			msg.saveChanges();
			// Send the message
			trans = s.getTransport("smtp");
			trans.connect();
			trans.sendMessage(msg, new Address[] { toAddr });
			trans.close();
		} catch (Exception e) {
			JGossipLog.audit(LogLevel.ERROR, e.getMessage(), e);
			e.printStackTrace();
		}
	}

	// Open the mail session if it isn't already open.
	protected Session getMailSession() throws Exception {
		// Create mail session if it doesn't exist
		if (_mailSession == null) {
			_authenticator = new SmtpAuthenticator();

			Configurator config = Configurator.getInstance();
			Properties props = new Properties();
			props.put("mail.smtp.user", config.get(IConst.CONFIG.MAILUSER));
			props.put("mail.smtp.host", config.get(IConst.CONFIG.MAILHOST));
			props.put("mail.smtp.port", config
					.get(IConst.CONFIG.SMTP_SERVER_PORT));
			props.put("mail.mime.charset", config.get(IConst.CONFIG.CHARSET));
			String smtpUser = config.get(IConst.CONFIG.MAILUSER);
			if (!smtpUser.equals("anonymous"))
				props.put("mail.smtp.auth", "true");
			_mailSession = Session.getInstance(props, _authenticator);
		}

		return _mailSession;
	}
}

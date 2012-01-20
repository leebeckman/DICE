/*
 * $Id: SmtpAuthenticator.java,v 1.3 2005/06/07 12:32:01 bel70 Exp $
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
 *              Dmitriy Belov <bel@jresearch.org>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 25.04.2004
 *
 */
package org.jresearch.gossip.mail;

import java.io.IOException;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.util.DesEncrypter;

/**
 * DOCUMENT ME!
 * 
 * @author Dmitry Belov
 */
public class SmtpAuthenticator extends Authenticator {
	private static Configurator config = Configurator.getInstance();

	private String mailuser;

	private String mailpassword;

	/**
	 * Creates a new SmtpAuthenticator object.
	 * 
	 * @throws ConfiguratorException
	 * @throws IOException
	 */
	public SmtpAuthenticator() throws ConfiguratorException, IOException {
		super();

		DesEncrypter encrypter = new DesEncrypter(IConst.VALUES.ENCRYPTER_KEY);
		mailuser = config.get(IConst.CONFIG.MAILUSER);
		mailpassword = encrypter
				.decrypt(config.get(IConst.CONFIG.MAILPASSWORD));

	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(mailuser, mailpassword);
	}
}

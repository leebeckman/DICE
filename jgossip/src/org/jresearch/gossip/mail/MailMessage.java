/*
 * $$Id: MailMessage.java,v 1.3 2005/06/07 12:32:02 bel70 Exp $$
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
 * Created on Oct 28, 2003
 *
 */
package org.jresearch.gossip.mail;

/**
 * DOCUMENT ME!
 * 
 * @author dbelov
 */
public class MailMessage {
	private String messagetext;

	private String subject;

	private String addrfrom;

	private String namefrom;

	private String addrto;

	private String nameto;

	/**
	 * Creates a new MailMessage object.
	 * 
	 * @param messagetext
	 *            DOCUMENT ME!
	 * @param subject
	 *            DOCUMENT ME!
	 * @param addrfrom
	 *            DOCUMENT ME!
	 * @param namefrom
	 *            DOCUMENT ME!
	 * @param addrto
	 *            DOCUMENT ME!
	 * @param nameto
	 *            DOCUMENT ME!
	 */
	public MailMessage(String messagetext, String subject, String addrfrom,
			String namefrom, String addrto, String nameto) {
		if (messagetext == null) {
			throw new NullPointerException("messagetext parameter");
		} else if (messagetext.length() < 1) {
			throw new IllegalArgumentException(
					"messagetext parameter, value = " + messagetext);
		}

		if (subject == null) {
			throw new NullPointerException("subject parameter");
		} else if (subject.length() < 1) {
			throw new IllegalArgumentException("subject parameter, value = "
					+ subject);
		}

		if (addrfrom == null) {
			throw new NullPointerException("addrfrom parameter");
		} else if (addrfrom.length() < 1) {
			throw new IllegalArgumentException("addrfrom parameter, value = "
					+ addrfrom);
		}

		if (namefrom == null) {
			throw new NullPointerException("namefrom parameter");
		} else if (namefrom.length() < 1) {
			throw new IllegalArgumentException("namefrom parameter, value = "
					+ namefrom);
		}

		if (addrto == null) {
			throw new NullPointerException("addrto parameter");
		} else if (addrto.length() < 1) {
			throw new IllegalArgumentException("addrto parameter, value = "
					+ addrto);
		}

		if (nameto == null) {
			throw new NullPointerException("nameto parameter");
		} else if (nameto.length() < 1) {
			throw new IllegalArgumentException("nameto parameter, value = "
					+ nameto);
		}

		this.messagetext = messagetext;
		this.subject = subject;
		this.addrfrom = addrfrom;
		this.namefrom = namefrom;
		this.addrto = addrto;
		this.nameto = nameto;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getAddrfrom() {
		return addrfrom;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getAddrto() {
		return addrto;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getMessagetext() {
		return messagetext;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getNamefrom() {
		return namefrom;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getNameto() {
		return nameto;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}
}

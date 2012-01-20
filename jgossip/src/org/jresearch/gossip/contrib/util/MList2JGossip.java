/*
 * $Id: MList2JGossip.java,v 1.3 2005/06/07 12:32:33 bel70 Exp $
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
 *              Tim Endres <timendres@users.sourceforge.net>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 13.06.2004
 *
 */
package org.jresearch.gossip.contrib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * This class will read a directory of MH mail messages, and insert those
 * messages into a jGossip forum. The messages will be sorted as best as
 * possible based on the Subjects.
 * 
 * The resulting SQL for MySQL is output to stdout. This can be processed using
 * the mysql command line client by passing the SQL into mysql's stdin.
 * 
 * Usage: MList2Gossip [options...] --archive path [--archive path]...
 * 
 * Options:
 * 
 * --archive path Path to a directory of MH mail messages to insert into a
 * forum. You can specify any number of archive options to process.
 * 
 * --forum id Id number of forum into which the messages will be inserted.
 * 
 * --sender name Name of sender of inserted messages.
 * 
 * --ipaddr id IP address of inserted messages.
 * 
 * --msgid id Starting id for inserted messages.
 * 
 * --threadid id Starting id for inserted threads.
 * 
 * --debug Turn on debugging.
 * 
 * --verbose Turn on verbosity.
 */

public class MList2JGossip {

	private static String VERSION_STR = "1.1";

	private boolean debug = false;

	private boolean verbose = false;

	private Session session = null;

	private int fForumId = 25;

	private int fThreadId = 100;

	private int fMsgId = 500;

	private String fSender = "anonymous";

	private String fIpAddress = "127.0.0.1";

	private int errDupes = 0;

	private HashMap allMsgs = null;

	private HashMap subjects = null;

	private HashMap bySubject = null;

	private HashMap byMsgId = null;

	private SimpleDateFormat dateFmt = null;

	public static void main(String[] argv) {
		MList2JGossip app = new MList2JGossip();
		app.instanceMain(argv);
	}

	public void instanceMain(String[] argv) {
		this.allMsgs = new HashMap(1024);
		this.subjects = new HashMap(1024);
		this.bySubject = new HashMap(1024);
		this.byMsgId = new HashMap(1024);

		this.dateFmt = new SimpleDateFormat("yyyyMMddHHmmss");

		this.session = Session.getDefaultInstance(System.getProperties(), null);

		this.processArguments(argv);

		try {
			this.processArchives();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}

	private String escapeString(String str) {
		StringBuffer buf = new StringBuffer();

		int chIdx = 0;
		int offset = 0;
		int strLen = str.length();
		for (chIdx = 0; chIdx < strLen; ++chIdx) {
			char ch = str.charAt(chIdx);

			if (ch == '\'') {
				buf.append("''");
			} else if (ch == '\\') {
				buf.append("\\\\");
			} else {
				buf.append(ch);
			}
		}

		return buf.toString();
	}

	private void processArchives() throws IOException, MessagingException {
		Iterator threads = this.bySubject.values().iterator();
		for (; threads.hasNext();) {
			boolean insertedThread = false;
			ArrayList thread = (ArrayList) threads.next();

			for (int ti = 0; ti < thread.size(); ++ti) {
				String msgId = (String) thread.get(ti);

				try {
					MimeMessage msg = (MimeMessage) this.allMsgs.get(msgId);
					String subject = msg.getSubject();
					Date sentDate = msg.getSentDate();
					String dateStr = this.dateFmt.format(sentDate);

					if (msg != null) {
						if (!insertedThread) {
							++this.fThreadId;
							insertedThread = true;

							System.out
									.println("INSERT INTO jrf_thread "
											+ "( threadid, forumid, lintime, locked, sortby )");

							System.out.println("   VALUES " + "( "
									+ this.fThreadId + ", " + this.fForumId
									+ ", " + "'" + dateStr + "', " + "0, "
									+ "9" + " ); ");
						}

						if (subject == null)
							subject = "";

						if (subject.length() > 252)
							subject = subject.substring(0, 252);

						subject = this.escapeString(subject);

						String bodyStr = null;
						Object bodyObj = msg.getContent();
						if (bodyObj instanceof String) {
							bodyStr = this.escapeString((String) bodyObj);
						} else if (bodyObj instanceof MimeMultipart) {
							System.err.println("MULTIPART [" + msgId + "] ");

							MimeMultipart multi = (MimeMultipart) bodyObj;
							int cnt = multi.getCount();
							for (int bpi = 0; bpi < cnt; ++bpi) {
								BodyPart bp = multi.getBodyPart(bpi);
								if (bp instanceof MimeBodyPart) {
									MimeBodyPart mbp = (MimeBodyPart) bp;
									String cType = mbp.getContentType();
									if (cType.startsWith("text/")) {
										bodyObj = mbp.getContent();
										bodyStr = this
												.escapeString((String) bodyObj);
										break;
									} else {
										System.err.println("   SKIP " + cType);
									}
								} else {
									System.err.println("MSG [" + msgId
											+ "] BP [" + bpi + "] isa "
											+ bp.getClass().getName());
								}
							}
						} else {
							System.err.println("MSG [" + msgId + "] isa "
									+ bodyObj.getClass().getName());
						}

						if (bodyStr != null) {
							System.out
									.println("INSERT INTO jrf_message "
											+ "( id, sender, centents, intime, heading, threadid, ip )");

							System.out.println("   VALUES " + "( "
									+ this.fMsgId++ + ", " + "'" + this.fSender
									+ "', " + "'" + bodyStr + "', " + "'"
									+ dateStr + "', " + "'" + subject + "', "
									+ this.fThreadId + ", " + "'"
									+ this.fIpAddress + "'" + " ); ");
						}
					}
				} catch (Exception ex) {
					System.err.println(ex.getClass().getName() + ": "
							+ ex.getMessage());
					System.err.println("        " + msgId);
					ex.printStackTrace(System.err);
				}
			}
		}
	}

	private void addBySubject(String subject, String msgId) {
		String sub = subject;
		if (sub.length() > 50)
			sub = sub.substring(0, 50);

		ArrayList ma = (ArrayList) this.bySubject.get(sub);

		if (ma == null) {
			// This is a NEW THREAD
			//
			ma = new ArrayList();
			this.bySubject.put(sub, ma);
		}

		if (!ma.contains(msgId))
			ma.add(msgId);

		this.byMsgId.put(msgId, ma);
	}

	private void loadArchive(String archivePath) throws IOException,
			NumberFormatException, ParseException {
		int numMsgs = 0;
		long begMillis = System.currentTimeMillis();

		File archDirF = new File(archivePath);

		if (this.verbose)
			System.err.println("LOAD MESSAGES FROM: " + archDirF.getPath());

		if (!archDirF.exists()) {
			System.err.println("Directory '" + archivePath
					+ "' does not exist.");
			return;
		}

		if (!archDirF.isDirectory()) {
			System.err
					.println("Path '" + archivePath + "' is not a directory.");
			return;
		}

		String[] dirList = archDirF.list();

		for (int idx = 0; idx < dirList.length; ++idx) {
			File f = new File(archDirF, dirList[idx]);

			if (f.exists() && f.isFile()) {
				FileInputStream fis = null;

				try {
					fis = new FileInputStream(f);
					MimeMessage msg = new MimeMessage(this.session, fis);

					String msgId = msg.getMessageID();
					if (this.debug)
						System.err.println("MSGID: " + msgId);

					if (this.allMsgs.containsKey(msgId)) {
						++this.errDupes;
						System.err.println("ERROR: duplicate msgID '" + msgId
								+ "'");
					} else {
						this.allMsgs.put(msgId, msg);
					}

					Date msgDate = msg.getSentDate();
					if (this.debug)
						System.err.println("   DATE: " + msgDate);

					String subject = msg.getSubject();
					this.subjects.put(msgId, subject);
					if (this.debug)
						System.err.println("   SUBJ: " + subject);

					String sub = (subject == null ? "" : subject);
					while (sub.toUpperCase().startsWith("RE: ")) {
						sub = sub.substring(4);
					}

					this.addBySubject(sub, msgId);

					++numMsgs;
				} catch (Exception ex) {
					System.err.println("Error loading '" + f.getPath() + "', "
							+ ex.getMessage());
					ex.printStackTrace(System.err);
				} finally {
					if (fis != null)
						fis.close();
				}
			}
		}

		long endMillis = System.currentTimeMillis();

		if (this.verbose)
			System.err.println("Processed " + numMsgs + " messages " + " in "
					+ ((endMillis - begMillis) / 1000) + " seconds.");

		if (this.verbose)
			System.err.println("Processed " + numMsgs + " messages "
					+ " producing " + this.bySubject.size() + " threads.");

		if (this.verbose)
			System.err.println("ERRORS: Duplicate MsgIDs = " + this.errDupes);
	}

	private void processArguments(String[] argv) {
		int i = 0;

		for (; i < argv.length; ++i) {
			if (!argv[i].startsWith("-") || argv[i].equals("--")) {
				break;
			} else if (argv[i].equals("-?") || argv[i].equals("--help")) {
				this.printUsageAndExit();
			} else if (argv[i].equals("--debug")) {
				this.debug = true;
			} else if (argv[i].equals("--verbose")) {
				this.verbose = true;
			} else if (argv[i].equals("--sender")) {
				this.fSender = argv[++i];
			} else if (argv[i].equals("--ipaddr")) {
				this.fIpAddress = argv[++i];
			} else if (argv[i].equals("--forum")) {
				++i;
				try {
					this.fForumId = Integer.parseInt(argv[i]);
				} catch (Exception ex) {
					ex.printStackTrace(System.err);
					this.printUsageAndExit();
				}
			} else if (argv[i].equals("--threadid")) {
				++i;
				try {
					this.fThreadId = Integer.parseInt(argv[i]);
				} catch (Exception ex) {
					ex.printStackTrace(System.err);
					this.printUsageAndExit();
				}
			} else if (argv[i].equals("--msgid")) {
				++i;
				try {
					this.fMsgId = Integer.parseInt(argv[i]);
				} catch (Exception ex) {
					ex.printStackTrace(System.err);
					this.printUsageAndExit();
				}
			} else if (argv[i].equals("--archive")) {
				try {
					this.loadArchive(argv[++i]);
				} catch (Exception ex) {
					ex.printStackTrace(System.err);
				}
			} else {
				System.err.println("UNKNOWN OPTION: " + argv[i]);
				this.printUsageAndExit();
			}
		}
	}

	public void printUsageAndExit() {
		System.err.println("usage: " + this.getClass().getName()
				+ " [options] --archive path [--archive path]...");
		System.err.println("version: " + VERSION_STR);
		System.err.println("options:");
		System.err
				.println("  --archive path         Mailing list archive path.");
		System.err
				.println("  --debug                Turn on debugging output.");
		System.err
				.println("  --verbose              Turn on operational verbosity.");
		System.err
				.println("  --forum id             Forum ID assigned to each message.");
		System.err.println("  --msgid id             Beginning message id.");
		System.err.println("  --threadid id          Beginning thread id.");
		System.err
				.println("  --sender name          Sender name assigned to each message.");
		System.err
				.println("  --ipaddr addr          IP Address assigned to each message.");
		System.exit(1);
	}

}

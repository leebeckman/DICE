/*
 * $Id: JGossipException.java,v 1.3 2005/06/07 12:32:23 bel70 Exp $
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

package org.jresearch.gossip.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Class <code>JGossipException</code> is a base class for all exceptions in
 * application/
 * 
 * @author <code>$Author: bel70 $</code>
 * @version <code>$Revision: 1.3 $</code>
 */
public class JGossipException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2799181321033636504L;

	/** Field <code>nestedException</code> */
	private Exception nestedException;

	/**
	 * Constructor <code>JGossipException</code> creates empty exception
	 */
	protected JGossipException() {
		super();
		nestedException = null;
	}

	/**
	 * Constructor <code>JGossipException</code> creates exception with error
	 * message
	 * 
	 * @param <code>msg</code> error message
	 */
	protected JGossipException(String msg) {
		super(msg);
		nestedException = null;
	}

	/**
	 * Constructor <code>JGossipException</code> create exception with
	 * originaly thrown one
	 * 
	 * @param <code>cause</code> original exception
	 */
	protected JGossipException(Exception cause) {
		super();
		nestedException = cause;
	}

	/**
	 * Constructor <code>JGossipException</code> create exception with error
	 * message and original exception
	 * 
	 * @param <code>msg</code> error message
	 * @param <code>cause</code> original exception
	 */
	protected JGossipException(String msg, Exception cause) {
		super(msg);
		nestedException = cause;
	}

	/**
	 * Method <code>getException</code> return original exception
	 * 
	 * @return original exception
	 */
	public Exception getNestedException() {
		return nestedException;
	}

	/**
	 * Method <code>getMessage</code> return erorr message
	 * 
	 * @return error message
	 */
	public String getMessage() {

		String msg = super.getMessage();
		Exception ex = getNestedException();

		if (ex != null) {
			String s = ex.getMessage();

			if (s != null) {
				if (msg == null)
					msg = "";
				else
					msg += "\n";
				msg += "CAUSE: " + s;
			}
		}
		return msg;
	}

	/**
	 * Method <code>getStackTrace</code> return exception stack trace
	 * 
	 * @return stack trace as String
	 */
	public String getTrace() {

		StringWriter strwrt = new StringWriter();

		super.printStackTrace(new PrintWriter(strwrt));
		return strwrt.toString();
	}

	/**
	 * Method <code>printStackTrace</code> print stack trace in given stream
	 * 
	 * @param <code>out</code> stream to write trace to
	 */
	public void printStackTrace(PrintStream out) {

		Exception ex = getNestedException();

		if (ex != null) {
			ex.printStackTrace(out);
			out.println("WRAPPING EXCEPTION:");
		}
		super.printStackTrace(out);
	}

	/**
	 * Method <code>printStackTrace</code> print stack trace in given stream
	 * 
	 * @param <code>out</code> stream to write trace to
	 */
	public void printStackTrace(PrintWriter out) {

		Exception ex = getNestedException();

		if (ex != null) {
			ex.printStackTrace(out);
			out.println("WRAPPING EXCEPTION:");
		}
		super.printStackTrace(out);
	}

	/**
	 * Method <code>printStackTrace</code> print stack trace to output
	 */
	public void printStackTrace() {
		printStackTrace(System.err);
	}
}

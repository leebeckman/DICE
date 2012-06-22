/*
 * Copyright (c) JForum Team
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above 
 * copyright notice, this list of conditions and the 
 * following  disclaimer.
 * 2)  Redistributions in binary form must reproduce the 
 * above copyright notice, this list of conditions and 
 * the following disclaimer in the documentation and/or 
 * other materials provided with the distribution.
 * 3) Neither the name of "Rafael Steil" nor 
 * the names of its contributors may be used to endorse 
 * or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT 
 * HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 * 
 * This file creation date: 02/08/2003 / 02:23:50
 * The JForum Project
 * http://www.jforum.net
 */
package net.jforum.util.bbcode;

import java.io.Serializable;

/**
 * Represents each bbcode.
 * 
 * @author Rafael Steil
 * @version $Id: BBCode.java,v 1.11 2006/08/23 02:13:55 rafaelsteil Exp $
 */
public class BBCode implements Serializable
{
	private String tagName = "";
	private String regex;
	private String replace;
	private boolean removQuotes;
	private boolean alwaysProcess;
	
	public BBCode() {}

	/**
	 * BBCode class constructor
	 * @param tagName The tag name we are going to match
	 * @param regex Regular expression relacted to the tag
	 * @param replace The replacement string
	 */
	public BBCode(String tagName, String regex, String replace)
	{
		this.tagName = tagName;
		this.regex = regex;
		this.replace = replace;
	}

	/**
	 * Gets the regex
	 * @return String witht the regex
	 */
	public String getRegex() 
	{
		return this.regex;
	}

	/**
	 * Gets the replacement string
	 * @return string with the replacement data
	 */
	public String getReplace() 
	{
		return this.replace;
	}

	/**
	 * Getst the tag name
	 * @return The tag name
	 */
	public String getTagName() 
	{
		return this.tagName;
	}
	
	public boolean removeQuotes()
	{
		return this.removQuotes;
	}

	/**
	 * Sets the regular expression associated to the tag
	 * @param regex Regular expression string
	 */
	public void setRegex(String regex) 
	{
		this.regex = regex;
	}

	/**
	 * Sets the replacement string, to be aplyied when matching the code
	 * @param replace The replacement string data
	 */
	public void setReplace(String replace) 
	{
		this.replace = replace;
	}

	/**
	 * Setst the tag name
	 * @param tagName The tag name
	 */
	public void setTagName(String tagName) 
	{
		this.tagName = tagName;
	}
	
	public void enableAlwaysProcess()
	{
		this.alwaysProcess = true;
	}
	
	public boolean alwaysProcess()
	{
		return this.alwaysProcess;
	}
	
	public void enableRemoveQuotes()
	{
		this.removQuotes = true;
	}
}

/*
 * $$Id: IConst.java,v 1.3 2005/06/07 12:32:36 bel70 Exp $$
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

package org.jresearch.gossip;

import org.apache.log.output.db.ColumnInfo;
import org.apache.log.output.db.ColumnType;

/**
 * DOCUMENT ME!
 * 
 * @author $author$
 * @version $Revision: 1.3 $
 */
public interface IConst {

	public interface TOKEN {

		public static final String PAGE = "page";

		public static final String WELCOME = "welcome";

		public static final String DENIED = "jgossip-denied";
	}

	/**
	 * @author Dmitry Belov
	 * 
	 */
	public interface MISC {

		public static final String REGISTRATION_PICTURE_HEIGHT = "registration_picture.height";

		public static final String REGISTRATION_PICTURE_WIDTH = "registration_picture.width";

		public static final String REGISTRATION_PICTURE_GRID = "registration_picture.grid";

		public static final String THUMBNAIL_HEIGHT = "thumbnail.height";

		public static final String THUMBNAIL_WIDTH = "thumbnail.width";

		public static final String AVATAR_HEIGHT = "avatar.height";

		public static final String AVATAR_WIDTH = "avatar.width";

	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface CONFIG {

		public static final String JNDI_DS_NAME = "JNDI_DS_NAME";

		public static final String CHARSET = "charset";

		public static final String DEFAULT_LOCALE = "defaultLocale";

		public static final int DEFAULT_SKIN_ID = 1;

		public static final int MAIL_QUEUE_THREADS_COUNT = 5;

		public static final String ADMINMAIL = "adminmail";

		public static final String DOMAIN_NAME = "domainname";

		public static final String DOMAIN_URL = "domainurl";

		public static final String SESSION_LENGTH = "sessionlength";

		public static final String SITE_NAME = "sitename";

		public static final String MOTTO = "motto";

		public static final String GZIP_COMPRESS = "gzipCompress";

		public static final String MAX_ATTACHMENT_COUNT = "maxAttachCount";

		public static final String ENABLE_FILE_UPLOAD = "enableFileUpload";

		public static final String ATTACH_STORE_PATH = "attachStorePath";

		public static final String ENABLE_AVATAR = "enableAvatar";

		public static final String MAILHOST = "mailhost";

		public static final String MAILUSER = "mailuser";

		public static final String MAILPASSWORD = "mailpassword";

		public static final String SMTP_SERVER_PORT = "smtpServerPort";

		public static final String INVADER1 = "invader";

		public static final String ENABLE_AUTO_LOGIN = "enableAutoLogin";

		public static final String ENABLE_FORUM_SIGN_ON = "enableForumSignOn";

		public static final String ENABLE_EMAIL_CONFIRMATION = "enableEmailConfiramtion";

		public static final String PERIOD_FOR_CONFIRMATION = "periodForConfirmation";

		public static final String ENABLE_FORUM_REGISTRATION = "enableForumRegistration";

		public static final String ENABLE_EXT_SIGN_ON = "enableExtSignOn";

		public static final String EXT_LOGON_ACTION_URL = "extLogOnActionUrl";

		public static final String EXT_LOGOUT_ACTION_URL = "extLogOutActionUrl";

		public static final String EXT_REGISTRATION_ACTION_URL = "extRegistrationActionUrl";

		public static final String RSS_MAX_ITEM_COUNT = "rssMaxItemCount";

		public static final String RSS_PERIOD = "rssPeriod";

		public static final String RSS_TTL = "rssTtl";

		public static final String MODULE_PREFIX = "modulePrefix";

		public static final String WEB_ROOT = "webroot";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface CONTEXT {

		public static final String AVAIBLE_TRANSLATIONS = "JRF_AVAIBLE_TRANSLATIONS";

		public static final String SYSTEM_EXCEPTION_MESSAGE = "JRF_SYSTEM_EXCEPTION_MESSAGE";

		public static final String LAST_UPDATE_DATE = "JRF_LAST_UPDATE";

		public static final String MAIL_QUEUE = "_JRF_MAIL_QUEUE";

		public static final String STATUS_KEY_PREFIX = "user.STAT";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author dbelov
	 */
	public interface COOKIE {

		public static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;

		public static final String USER_COOKIE = "JRF_USER";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface JSP {

		public static final String JPG_CONTENT_TYPE = "image/jpeg";

		public static final String GIF_CONTENT_TYPE = "image/gif";

		public static final String PNG_CONTENT_TYPE = "image/png";

		public static final String DEFAULT_CONTENT_TYPE = "text/html";

		public static final String OPTIONS_SEPERATOR = "--------";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface LOG {

		public static final String APP_LOG_PREF = "jGossip";

		public static final String FORUM = "FORUM";

		public static final String APPLICATION_LOG_NAME = "JGOSSIP-APP";

		public static final String AUDIT_LOG_NAME = "JGOSSIP-AUDIT";

		public static final String LOG_TABLE = "jrf_audit_log";

		public static final ColumnInfo LOG_COLUMNS[] = new ColumnInfo[] {
				new ColumnInfo("log_date", ColumnType.CONTEXT, "time"),
				new ColumnInfo("logger", ColumnType.CATEGORY, "category"),
				new ColumnInfo("log_level", ColumnType.PRIORITY, "priority"),
				new ColumnInfo("message", ColumnType.MESSAGE, "message"),
				new ColumnInfo("remote_ip", ColumnType.CONTEXT, "RemoteIP"),
				new ColumnInfo("user_name", ColumnType.CONTEXT, "UserName"),
				new ColumnInfo("session_id", ColumnType.CONTEXT, "SessionId") };
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface PAGE {

		public static final String CURR_THREAD_ID = "JRF_CURR_THREAD_ID";

		public static final String MESSAGE_ACTION = "JRF_MESSAGE_ACTION";

		public static final String SELECTED_BLOCK = "JRF_SELECTED_BLOCK";

		public static final String TITLE_NAV_BAR = "JRF_TITLE_NAV_BAR";

		public static final String HAVE_AN_UPDATED_TOPICS = "JRF_HAVE_AN_UPDATED_TOPICS";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface REQUEST {

		public static final String SITE_URL = "JRF_SITE_URL";

		public static final String ENTRY_LIST = "JRF_ENTRY_LIST";

		public static final String CURR_FORUM = "JRF_CURR_FORUM";

		public static final String CURR_THREAD = "JRF_CURR_THREAD";

		public static final String FORUMS_FOR_MOD = "JRF_FORUMS_FOR_MOD";

		public static final String GROUPS_KEY = SESSION.GROUPS_KEY;

		public static final String LIST_RECORDS = "JRF_LIST_RECORDS";

		public static final String MOD_FLAG = "JRF_MOD_FLAG";

		public static final String RECORDS_DATA = "JRF_RECORDS_DATA";

		public static final String REDIRECT_URL = "JRF_REDIRECT_URL";

		public static final String REQUEST_URI = "JRF_REQUEST_URI";

		public static final String START_TIME_KEY = "JRF_START_TIME";

		public static final String UPDATED_USER = "JRF_UPDATED_USER";

		public static final String USER_MOD_FORUMS = "JRF_USER_MOD_FORUMS";

		public static final String USER_TO_SHOW = "JRF_USER_TO_SHOW";

		public static final String RSS_PUB_DATE = "JRF_RSS_PUB_DATE";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface SESSION {

		public static final String SEARCH_CRITERIA_LOG = "JRF_SEARCH_CRITERIA_LOG";

		public static final String PERMISSION_GUARD_KEY = "JRF_PERMISSION_GUARD";

		public static final String EXT_USER_KEY = "JGOSSIP_EXT_USER";

		public static final String CURR_FORUM = "JRF_CURR_FORUM";

		public static final String GROUPS_KEY = "JRF_GROUPS";

		public static final String LAST_INTIME = "JRF_LAST_INTIME";

		public static final String LAST_UPDATE_DATE = "JRF_LAST_UPDATE";

		public static final String STATUS_MESSAGE = "JRF_STATUS_MESSAGE";

		public static final String TIME_ZONE = "JRF_TIME_ZONE";

		public static final String USER_KEY = "JRF_USER";

		public static final String USER_TIME_ZONE = "JRF_USER_TIME_ZONE";

		public static final String STYLE_SETTINGS = "JRF_STYLE_SETTINGS";

		public static final String CONFIRM_CODE = "JRF_CONFIRM_CODE";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @author $author$
	 * @version $Revision: 1.3 $
	 */
	public interface VALUES {

		public static final String ENCRYPTER_KEY = "Fvbweuehbweafhbgfhbgfhjdjp";

		public static final int NOT_EXIST = -1;

		public static final String ALL = "ALL";

		public static final String ANY = "ANY";

		public static final int DEFAULT_PASSWORD_LENGTH = 8;

		public static final String FALSE = "N";

		public static final String GMT = "GMT";

		public static final String PASSWORD_DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

		public static final String TRUE = "Y";

		public static final String WHOLE = "WHOLE";

		public static final String RFC822_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";

		public static final String ISO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

		public static final byte[] BLANK_GIF = { 71, 73, 70, 56, 57, 97, 1, 0,
				1, 0, -111, -1, 0, -1, -1, -1, 0, 0, 0, -1, -1, -1, 0, 0, 0,
				33, -1, 11, 65, 68, 79, 66, 69, 58, 73, 82, 49, 46, 48, 2, -34,
				-19, 0, 33, -7, 4, 1, 0, 0, 2, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0,
				0, 2, 2, 84, 1, 0, 59 };
	}

	public interface Forum {

		public static final int STATUS_UNLOCKED = 0;

		public static final int STATUS_TOPICS_LOCKED = 1;

		public static final int STATUS_COMPLETELY_LOCKED = 2;

		public static final int STATUS_INVISIBLE = 3;
	}

	public interface Topic {

		public static final int STATUS_UNLOCKED = 0;

		public static final int STATUS_LOCKED = 1;
	}
}
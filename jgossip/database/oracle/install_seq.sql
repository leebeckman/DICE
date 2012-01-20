/*$$Id$$
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
 *              Alexey Pavlov <alexnet@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */


DROP SEQUENCE JRF_KEY_MESSAGE_SEQ;

CREATE SEQUENCE JRF_KEY_MESSAGE_SEQ
  START WITH 1
  MAXVALUE 99999999999999
  MINVALUE 0
  NOCYCLE
  CACHE 20
  NOORDER;

DROP SEQUENCE JRF_KEY_THREAD_SEQ;

CREATE SEQUENCE JRF_KEY_THREAD_SEQ
  START WITH 1
  MAXVALUE 99999999999999
  MINVALUE 0
  NOCYCLE
  CACHE 20
  NOORDER;

DROP SEQUENCE JRF_KEY_USER_SEQ;

CREATE SEQUENCE JRF_KEY_USER_SEQ
  START WITH 1
  MAXVALUE 99999999999999
  MINVALUE 0
  NOCYCLE
  CACHE 20
  NOORDER;

DROP SEQUENCE JRF_KEY_WHOIS_SEQ;

CREATE SEQUENCE JRF_KEY_WHOIS_SEQ
  START WITH 1
  MAXVALUE 99999999999999
  MINVALUE 0
  NOCYCLE
  CACHE 20
  NOORDER;

DROP SEQUENCE JRF_KEY_FORUM_SEQ;

CREATE SEQUENCE JRF_KEY_FORUM_SEQ
  START WITH 1
  MAXVALUE 99999999999999
  MINVALUE 0
  NOCYCLE
  CACHE 20
  NOORDER;

DROP SEQUENCE JRF_KEY_GROUP_SEQ;

CREATE SEQUENCE JRF_KEY_GROUP_SEQ
  START WITH 1
  MAXVALUE 99999999999999
  MINVALUE 0
  NOCYCLE
  CACHE 20
  NOORDER;

/*$$Id$$
 * ***** BEGIN LICENSE BLOCK *****
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"), you may not use this file except in 
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
 *              Alexey Pavlov <alexnet@sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */
/**
 * Create temporary table for script parameters
 */
CREATE TABLE T_PARAMS  
  (
  uname varchar(32) NOT NULL,
  upass varchar2(100) NOT NULL,
  NOW TIMESTAMP NOT NULL
  ) ;
  
-- Set script parameters  
INSERT INTO T_PARAMS VALUES (
'Administrator',
 /**
  * MD5 hash for string: password and uname 'Administrator'
  */
'46897B121788784DC3CFD2FF456986B0',
sysdate
);
 
INSERT INTO jrf_group VALUES (1,'First Group','aa');
  
INSERT INTO jrf_forum VALUES (1,'First Forum','This is the First forum ',1,0,'aa');
  
INSERT INTO jrf_thread VALUES (1,1,(SELECT NOW FROM T_PARAMS),0,9);

INSERT INTO jrf_message VALUES (1,(SELECT UNAME FROM T_PARAMS),'If you see this message, you have  Forum configured and running ',(SELECT NOW FROM T_PARAMS),'Congratulations!',1,'127.0.0.1');
         
INSERT INTO jrf_user_status values(1,'USER');

INSERT INTO jrf_user_status values(7,'MOD');

INSERT INTO jrf_user_status values(8,'MAINMOD');

INSERT INTO jrf_user_status values(9,'JRADM');

INSERT INTO jrf_user_status values(10,'ADM');

INSERT INTO jrf_user VALUES(1,(SELECT UNAME FROM T_PARAMS),(SELECT UPASS FROM T_PARAMS),
          'admin@this.forum',10,'http://','','01-01-2001','','',
          2,1,20,'',(SELECT NOW FROM T_PARAMS));
 
  
INSERT INTO jrf_constants values('mailhost','localhost');
INSERT INTO jrf_constants values('mailuser','anonymous');
INSERT INTO jrf_constants values('mailpassword',' ');
INSERT INTO jrf_constants values('smtpServerPort','25');

INSERT INTO jrf_constants values('adminmail','info@localhost');
INSERT INTO jrf_constants values('sessionlength','3600');
INSERT INTO jrf_constants values('sitename','jGossip');
INSERT INTO jrf_constants values('domainname','localhost');
INSERT INTO jrf_constants values('domainurl','http://localhost');
INSERT INTO jrf_constants values('motto','Site motto');
INSERT INTO jrf_constants values('gzipCompress','Y');
INSERT INTO jrf_constants values('defaultLocale','en');

INSERT INTO jrf_constants values('invader','1');
INSERT INTO jrf_constants values('enableExtSignOn','N');
INSERT INTO jrf_constants values('enableForumSignOn','Y');
INSERT INTO jrf_constants values('enableEmailConfiramtion','N');
INSERT INTO jrf_constants values('periodForConfirmation','48');
INSERT INTO jrf_constants values('extLogOnActionUrl','#');
INSERT INTO jrf_constants values('extLogOutActionUrl','#');
INSERT INTO jrf_constants values('extRegistrationActionUrl','#');
INSERT INTO jrf_constants values('enableAutoLogin','Y');
INSERT INTO jrf_constants values('enableForumRegistration','Y');

INSERT INTO jrf_constants values('enableFileUpload','Y');
INSERT INTO jrf_constants values('maxAttachCount','4');
INSERT INTO jrf_constants values('attachStorePath','change me');

INSERT INTO jrf_constants values('rssMaxItemCount','12');
INSERT INTO jrf_constants values('rssPeriod','7');
INSERT INTO jrf_constants values('rssTtl','40');
INSERT INTO jrf_constants values('enableAvatar','Y');

INSERT INTO jrf_skins values(1,'default');

INSERT INTO jrf_skin_params values(1,'bgColor','FFFFFF');
INSERT INTO jrf_skin_params values(1,'textColor','000000');
INSERT INTO jrf_skin_params values(1,'clmnTitleColor','999999');
INSERT INTO jrf_skin_params values(1,'rowBgDrk','EEEEEE');
INSERT INTO jrf_skin_params values(1,'rowBgLght','FFFFFF');
INSERT INTO jrf_skin_params values(1,'border2','FF8000');
INSERT INTO jrf_skin_params values(1,'border1','AAA9A9');

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_forum', (select max(forumid)+10 from JRF_FORUM GROUP BY forumid));

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_group', (select max(groupid)+10 from JRF_GROUP GROUP BY groupid));

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_message',  (select max(id)+10 from JRF_MESSAGE GROUP BY id));

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_thread',  (select max(threadid)+10 from JRF_THREAD GROUP BY threadid));

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_user',  (select max(id)+10 from JRF_USER GROUP BY id));

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_whois',  10);

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_rank',  10);

INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_attach',  10);

INSERT INTO jrf_ban_type values('1','login');

INSERT INTO jrf_ban_type values('2','ip');

INSERT INTO jrf_ban_type values('3','email');  	

DROP TABLE T_PARAMS;  

commit;

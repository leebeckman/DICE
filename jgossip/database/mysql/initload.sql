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
 *              Dmitry Belov <bel@jresearch.org>
 *        
 * ***** END LICENSE BLOCK ***** */
SET @ADMINNAME="Administrator";
SET @PASSWORD="46897B121788784DC3CFD2FF456986B0";
SET @NOW= NOW();
 


INSERT INTO jrf_group VALUES (31,'First Group','aa');

INSERT INTO jrf_forum VALUES (25,'First Forum','This is the First forum ',31,0,'aa');

INSERT INTO jrf_thread VALUES (60,25,@NOW,0,9);

INSERT INTO jrf_message VALUES (205,@ADMINNAME,'If you see this message, you have  Forum configured and running ',@NOW,'Congratulations!',60,'');

INSERT INTO jrf_user_status values(1,'USER');

INSERT INTO jrf_user_status values(7,'MOD');

INSERT INTO jrf_user_status values(8,'MAINMOD');

INSERT INTO jrf_user_status values(9,'JRADM');

INSERT INTO jrf_user_status values(10,'ADM');

INSERT INTO jrf_user(id, user_name, user_pass, user_mail, user_hp, user_icq,
          user_dob, user_city, user_occupation, user_status, user_signature, mes_per_page,
          auto_login, show_user_mail,last_intime) VALUES(1,@ADMINNAME,@PASSWORD,
          'admin@this.forum','http://','','2001-01-01','','',10,
          '',20,2,1,NOW());
	
INSERT INTO jrf_constants values('mailhost','localhost');
INSERT INTO jrf_constants values('mailuser','anonymous');
INSERT INTO jrf_constants values('mailpassword','');
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
INSERT INTO jrf_constants values('enableAvatar','Y');

INSERT INTO jrf_constants values('rssMaxItemCount','12');
INSERT INTO jrf_constants values('rssPeriod','7');
INSERT INTO jrf_constants values('rssTtl','40');

INSERT INTO jrf_skins values(1,'default');

INSERT INTO jrf_skin_params values(1,'bgColor','FFFFFF');
INSERT INTO jrf_skin_params values(1,'textColor','000000');
INSERT INTO jrf_skin_params values(1,'clmnTitleColor','999999');
INSERT INTO jrf_skin_params values(1,'rowBgDrk','EEEEEE');
INSERT INTO jrf_skin_params values(1,'rowBgLght','FFFFFF');
INSERT INTO jrf_skin_params values(1,'border2','FF8000');
INSERT INTO jrf_skin_params values(1,'border1','AAA9A9');

INSERT INTO jrf_ban_type values('1','login');
INSERT INTO jrf_ban_type values('2','ip');
INSERT INTO jrf_ban_type values('3','email');

select @INIT_ID:=(max(forumid)+10) from jrf_forum;
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_forum', @INIT_ID);
select @INIT_ID:=(max(groupid)+10) from jrf_group;
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_group', @INIT_ID);
select @INIT_ID:=(max(id)+10) from jrf_message;
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_message', @INIT_ID);
select @INIT_ID:=(max(threadid)+10) from jrf_thread;
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_thread',  @INIT_ID);
delete from jrf_whois;
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_whois',  10);
select @INIT_ID:=(max(id)+10) from jrf_user;
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_user',  @INIT_ID);
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_rank',  10);
INSERT INTO JRF_KEY_KEEPER VALUES ('jrf_attach',  10);

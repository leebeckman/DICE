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
 *              Dmitry Belov <bel@jresearch.org>
 *              Alexey Pavlov <alexnet@sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */

CREATE TABLE JRF_KEY_KEEPER (
    table_name varchar (255) default NULL,
 	next_key INTEGER NOT NULL,
	constraint xp_last_inserted_id 
	PRIMARY KEY ( table_name,  next_key) ) ;

CREATE TABLE jrf_forum (
 	forumid number (12) NOT NULL,
    forumtitle varchar (255) default NULL,
	forumdesc varchar (255) default NULL,
    groupid number (12) default 0, 
    locked number (1) default 0,
	forum_sort varchar (2) default 'aa',
	constraint xp_jrf_forum 
	PRIMARY KEY ( forumid ) ) ;

CREATE TABLE jrf_group 
  (
  	groupid INTEGER NOT NULL,
 	 group_name varchar(255) default NULL,
 	 group_sort char(2) default NULL,
	constraint xp_jrf_group 
  	PRIMARY KEY (groupid) ) ;
  

CREATE TABLE jrf_message  
  (
  id number(12) NOT NULL,
  sender varchar(32) NOT NULL,
  centents clob default(''),
  intime TIMESTAMP NOT NULL,
  heading varchar(255) default NULL,
  threadid number(12) default 1,
  ip varchar(15) DEFAULT 'N/A',
	constraint xp_jrf_message 
  PRIMARY KEY (id) ) ;

  
CREATE TABLE jrf_subscribe (
  	threadid number (12) default 0 NOT NULL,
  	user_mail varchar (128) NOT NULL,
 	user_name varchar (32) NOT NULL ) ;
 
CREATE TABLE jrf_thread 
 (
 	 threadid number(12) NOT NULL,
 	 forumid number(12) default 1,
 	 lintime TIMESTAMP default NULL,
 	 locked number(1) DEFAULT 0,
 	 sortby number(1) DEFAULT 9,
	 constraint xp_jrf_thread 
 	 PRIMARY KEY  (threadid) ) ;

INSERT INTO jrf_thread VALUES (0,0,sysdate,0,9);

CREATE TABLE jrf_user_status (
    status number ( 2 ) DEFAULT 0,
    status_name VARCHAR ( 255 ) DEFAULT NULL,
    PRIMARY KEY ( status ) ); 

CREATE TABLE jrf_user (
  id number(12) NOT NULL,
  user_name varchar(32) NOT NULL,
  user_pass varchar(100) NOT NULL,
  user_mail varchar(64) NOT NULL,
  user_status number(2) default 0,
  user_hp varchar(128) default NULL,
  user_icq varchar(32) default NULL,
  user_dob date default NULL,
  user_city varchar(32) default NULL,
  user_occupation varchar(32) default NULL,
  auto_login char(1) default 1,
  show_user_mail char(1) default 1,
  mes_per_page number(2) default 20,
  user_signature CLOB,
  last_intime TIMESTAMP NOT NULL,
  constraint xp_jrf_user 
  PRIMARY KEY  (id) ) ;
  
CREATE unique INDEX xp_jrf_username_IDX ON jrf_user (user_name) ; 

create index xp_jrf_user_status on jrf_user(user_status);

alter table jrf_user add constraint fk_user_status foreign key(user_status) references jrf_user_status(status) on delete set null;

CREATE TABLE jrf_pending_user (
  user_name varchar(32) NOT NULL,
  user_mail varchar(64) NOT NULL,
  confirm_code varchar(128) NOT NULL,
  intime TIMESTAMP NOT NULL,
  constraint xp_jrf_pending_user 
  PRIMARY KEY  (user_name, confirm_code) ) ;
  
CREATE TABLE jrf_whois(
	   id number(12) not null,
	   ip varchar(16) not null , 
	   sessionid varchar(255) not null , 
	   user_name varchar(32), 
	   primary key(id) ) ;

CREATE TABLE jrf_mod(
	   user_name varchar(32) NOT NULL, 
	   forum_id number(12) ) ;

alter table jrf_mod add constraint xp_jrf_mod UNIQUE (user_name,forum_id);

CREATE TABLE jrf_constants (
	c_name varchar(32) NOT NULL,
	c_value varchar(255) NOT NULL,
  	primary key (c_name) ) ;
  	
CREATE TABLE jrf_skin_params (
  	skinid number(12) NOT NULL ,
  	param_name varchar(128) NOT NULL,
 	param_value varchar(255) NOT NULL ,
  	PRIMARY KEY (skinid, param_name) ) ;
        
CREATE TABLE jrf_skins (
  	skinid number(12) NOT NULL ,
  	skin_name varchar(128) NOT NULL,
  	PRIMARY KEY  (skinid) ) ;
  	
CREATE TABLE jrf_rank (
	id number(12) NOT NULL,
  	rank_count number(4) NOT NULL,
 	rank_name varchar(255) NOT NULL,
  	PRIMARY KEY (id) ) ;

CREATE UNIQUE INDEX XP_JRF_RANK ON jrf_rank  (rank_count);
	
ALTER TABLE jrf_rank ADD (
  CONSTRAINT XP_JRF_RANK UNIQUE (rank_count)
    USING INDEX ) ;
	
CREATE TABLE jrf_attach(
	id number(12) NOT NULL,
	message_id number(12) NOT NULL, 
	attach_name varchar(255) NOT NULL,
  	attach_description varchar(255),
 	attach_content_type varchar(255) NOT NULL,
 	attach_size number(12) NOT NULL,
 	PRIMARY KEY (id));
 	
create index xp_jrf_attach_message on jrf_attach(message_id);

alter table jrf_attach add constraint fk_attach_message foreign key(message_id) references jrf_message(id);

CREATE TABLE jrf_audit_log (
  	log_date varchar(80) NOT NULL ,
  	logger varchar(512) NOT NULL,
  	log_level varchar(32) NOT NULL,
  	message CLOB NOT NULL,
	remote_ip varchar(16),
	user_name varchar(32),
	session_id varchar(256)
	) ;
  	
create index xp_jrf_audit_log on jrf_audit_log(log_date, logger, log_level);

CREATE TABLE jrf_ban (
    type_id number(12) NOT NULL ,
  	ban_mask varchar(255) NOT NULL,
  	PRIMARY KEY (ban_mask)
  	);

CREATE TABLE jrf_ban_type(
  	id number(12) NOT NULL ,
  	type_name varchar(80) NOT NULL,
  	PRIMARY KEY (id)
  	);

create index ban_type on jrf_ban(type_id);
alter table jrf_ban add constraint fk_ban_type foreign key(type_id) references jrf_ban_type(id);

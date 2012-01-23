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
 *              Cathy Gorshkova <cathy@jresearch.org>
 *        
 * ***** END LICENSE BLOCK ***** */
CREATE TABLE JRF_KEY_KEEPER (
    table_name varchar (255) default NULL,
 	next_key INTEGER NOT NULL,
	constraint xp_last_inserted_id 
	PRIMARY KEY ( table_name,  next_key) )type=innodb ;

CREATE TABLE jrf_forum (
 	forumid int(12) NOT NULL,
    forumtitle varchar(255) default NULL,
	forumdesc varchar(255) default NULL,
    groupid int(12) default 0, 
    locked int(1) default 0,
	forum_sort varchar(2) default 'aa', 
	PRIMARY KEY (forumid),
	index jrf_forum_ind(groupid)) type=innodb;
        
CREATE TABLE jrf_group (
  	groupid int(12) NOT NULL,
 	group_name varchar(255) default NULL,
 	group_sort char(2) default NULL,
  	PRIMARY KEY (groupid))type=innodb;
  
CREATE TABLE jrf_message  (
    id int(12) NOT NULL,
    sender varchar(32) NOT NULL,
    centents text NOT NULL,
    intime DATETIME NOT NULL,
    heading varchar(255) default NULL,
    threadid int(12) default 1,
    ip varchar(15) DEFAULT 'N/A',
    PRIMARY KEY (id),
    index jrf_message_ind(threadid)) type=innodb;
    
CREATE TABLE jrf_attach(
	id int(12) NOT NULL,
	message_id int(12) NOT NULL, 
	attach_name varchar(255) NOT NULL,
  	attach_description varchar(255) NOT NULL,
 	attach_content_type varchar(255) NOT NULL,
 	attach_size int(12) NULL NULL,
 	PRIMARY KEY (id))type=innodb;
 	
CREATE TABLE jrf_subscribe (
  	threadid int(12) NOT NULL default 0,
  	user_mail varchar(128) NOT NULL,
 	user_name varchar(32) NOT NULL,
 	index jrf_subscribe_ind(threadid)) type=innodb;
  
CREATE TABLE jrf_thread (
 	 threadid int(12) NOT NULL,
 	 forumid int(12) default 1,
 	 lintime DATETIME default NULL,
 	 locked INT(1) DEFAULT 0,
 	 sortby INT(1) DEFAULT 9,
 	 PRIMARY KEY  (threadid),
 	 index jrf_thread_ind(forumid)) type=innodb;

CREATE TABLE jrf_user (
    id int(12) NOT NULL,
    user_name varchar(32) NOT NULL,
    user_pass varchar(100) NOT NULL,
    user_mail varchar(64) NOT NULL,
    user_status int(2),
    user_hp varchar(128) default NULL,
    user_icq varchar(32) default NULL,
    user_dob date default NULL,
    user_city varchar(32) default NULL,
    user_occupation varchar(32) default NULL,
    auto_login char(1) default 1,
    show_user_mail char(1) default 1,
    mes_per_page int(2) default 20,
    user_signature text,
    last_intime DATETIME NOT NULL,
    PRIMARY KEY  (id),
    UNIQUE KEY user_name (user_name))type=innodb;

CREATE TABLE jrf_user_status (
  	status int(2) NOT NULL,
 	status_name varchar(255) default NULL,
  	PRIMARY KEY (status))type=innodb;
  	
CREATE TABLE jrf_pending_user (
    user_name varchar(32) NOT NULL,
    user_mail varchar(64) NOT NULL,
    confirm_code varchar(128) NOT NULL,
    intime DATETIME NOT NULL,
    PRIMARY KEY  (user_name,confirm_code),
    index jrf_pending_user_idx (user_name,confirm_code))type=innodb;
  
CREATE TABLE jrf_whois(id int(12) not null,
    ip varchar(16) not null ,
    sessionid varchar(255) not null , 
    user_name varchar(32), 
    primary key(id))type=innodb;
 
CREATE TABLE jrf_mod(
	user_name varchar(32) NOT NULL, 
	forum_id int(12),
	UNIQUE KEY(user_name,forum_id),
	index jrf_mod_ind(forum_id))type=innodb;

CREATE TABLE jrf_constants (
	c_name varchar(32) NOT NULL,
	c_value varchar(255) NOT NULL,
  	UNIQUE KEY c_name (c_name))type=innodb;
  	
CREATE TABLE jrf_skin_params (
  	skinid int(12) NOT NULL ,
  	param_name varchar(128) NOT NULL,
 	param_value varchar(255) NOT NULL ,
  	PRIMARY KEY (skinid, param_name))type=innodb;

CREATE TABLE jrf_skins (
  	skinid int(12) NOT NULL ,
  	skin_name varchar(128) NOT NULL,
  	PRIMARY KEY  (skinid))type=innodb;

CREATE TABLE jrf_rank (
	id int(12) NOT NULL,
  	rank_count int(4) NOT NULL,
 	rank_name varchar(255) NOT NULL,
 	UNIQUE KEY (rank_count),
  	PRIMARY KEY (id))type=innodb;

CREATE TABLE jrf_audit_log (
  	log_date varchar(80) NOT NULL ,
  	logger varchar(255) NOT NULL,
  	log_level varchar(32) NOT NULL,
  	message text NOT NULL,
	remote_ip varchar(16),
	user_name varchar(32),
	session_id varchar(255)
  	)type=innodb ;
  	
CREATE TABLE jrf_ban (
    type_id int(12) NOT NULL ,
  	ban_mask varchar(255) NOT NULL,
  	UNIQUE KEY (ban_mask))type=innodb ;

CREATE TABLE jrf_ban_type(
  	id int(12) NOT NULL ,
  	type_name varchar(80) NOT NULL,
  	PRIMARY KEY (id))type=innodb ;
  	
create index forum_group on jrf_forum(groupid);
create index message_thread on jrf_message(threadid);
create index thread on jrf_subscribe(threadid);
create index thread_forum on jrf_thread(forumid);
create index mod_forum on jrf_mod(forum_id);
create index user_status on jrf_user(user_status);
create index attach_message on jrf_attach(message_id);
create index jrf_audit_log_idx  on jrf_audit_log(log_date, logger, log_level);
create index ban_type on jrf_ban(type_id);


alter table jrf_forum add constraint fk_forum_group foreign key (groupid) references jrf_group(groupid) on delete set null;
alter table jrf_message add constraint fk_message_thread foreign key (threadid) references jrf_thread(threadid) on delete set null;
alter table jrf_subscribe add constraint fk_thread foreign key(threadid) references jrf_thread(threadid) on delete cascade;
alter table jrf_thread add constraint fk_thread_forum foreign key(forumid) references jrf_forum(forumid) on delete set null;
alter table jrf_mod add constraint fk_mod_forum foreign key(forum_id) references jrf_forum(forumid) on delete set null;
alter table jrf_user add constraint fk_user_status foreign key(user_status) references jrf_user_status(status) on delete set null;
alter table jrf_attach add constraint fk_attach_message foreign key(message_id) references jrf_message(id);
alter table jrf_ban add constraint fk_ban_type foreign key(type_id) references jrf_ban_type(id);
  	
 	
/*
 * $Id: MSSqlDriver.java,v 1.1 2004/06/29 22:36:02 simo Exp $
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
 *		Simone Chiaretta <simo@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */
USE FORUM_DB

go

CREATE TABLE [dbo].[jrf_attach] (
	[id] [int] NOT NULL ,
	[message_id] [int] NOT NULL ,
	[attach_name] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[attach_description] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[attach_content_type] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[attach_size] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_audit_log] (
	[log_date] [varchar] (80) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[logger] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[log_level] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[message] [varchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[remote_ip] [varchar] (16) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[user_name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[session_id] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_ban] (
	[type_id] [int] NOT NULL ,
	[ban_mask] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_ban_type] (
	[id] [int] NOT NULL ,
	[type_name] [varchar] (80) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_constants] (
	[c_name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[c_value] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_forum] (
	[forumid] [int] NOT NULL ,
	[forumtitle] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[forumdesc] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[groupid] [int] NOT NULL ,
	[locked] [int] NULL ,
	[forum_sort] [char] (2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_group] (
	[groupid] [int] NOT NULL ,
	[group_name] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[group_sort] [char] (2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_key_keeper] (
	[table_name] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[next_key] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_message] (
	[id] [int] NOT NULL ,
	[sender] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[centents] [text] COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[intime] [datetime] NOT NULL ,
	[heading] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[threadid] [int] NOT NULL ,
	[ip] [varchar] (15) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_mod] (
	[user_name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[forum_id] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_pending_user] (
	[user_name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[user_mail] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[confirm_code] [varchar] (128) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[intime] [timestamp] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_rank] (
	[id] [int] NOT NULL ,
	[rank_count] [tinyint] NOT NULL ,
	[rank_name] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_skin_params] (
	[skinid] [int] NOT NULL ,
	[param_name] [varchar] (128) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[param_value] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_skins] (
	[skinid] [int] NOT NULL ,
	[skin_name] [varchar] (128) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_subscribe] (
	[threadid] [int] NOT NULL ,
	[user_mail] [varchar] (128) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[user_name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_thread] (
	[threadid] [int] NOT NULL ,
	[forumid] [int] NOT NULL ,
	[lintime] [datetime] NULL ,
	[locked] [int] NULL ,
	[sortby] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_user] (
	[id] [int] NOT NULL ,
	[user_name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[user_pass] [varchar] (100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[user_mail] [varchar] (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[user_status] [int] NULL ,
	[user_hp] [varchar] (128) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[user_icq] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[user_dob] [datetime] NULL ,
	[user_city] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[user_occupation] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[auto_login] [bit] NULL ,
	[show_user_mail] [bit] NULL ,
	[mes_per_page] [int] NULL ,
	[user_signature] [varchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[last_intime] [datetime] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_user_status] (
	[status] [int] NOT NULL ,
	[status_name] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[jrf_whois] (
	[id] [int] NOT NULL ,
	[ip] [varchar] (16) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[sessionid] [varchar] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[user_name] [varchar] (32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_attach] WITH NOCHECK ADD 
	CONSTRAINT [PK_jrf_attach] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_ban_type] WITH NOCHECK ADD 
	CONSTRAINT [PK_jrf_ban_type] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_constants] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[c_name]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_forum] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[forumid]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_group] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[groupid]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_key_keeper] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[table_name],
		[next_key]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_message] WITH NOCHECK ADD 
	CONSTRAINT [PK__jrf_message__5070F446] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_pending_user] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[user_name],
		[confirm_code]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_rank] WITH NOCHECK ADD 
	CONSTRAINT [PK_jrf_rank] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_skin_params] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[skinid],
		[param_name]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_skins] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[skinid]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_thread] WITH NOCHECK ADD 
	CONSTRAINT [PK__jrf_thread__5812160E] PRIMARY KEY  CLUSTERED 
	(
		[threadid]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_user] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_whois] WITH NOCHECK ADD 
	 PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_attach] ADD 
	CONSTRAINT [DF_jrf_attach_id] DEFAULT (0) FOR [id],
	CONSTRAINT [DF_jrf_attach_message_id] DEFAULT (0) FOR [message_id]
GO

 CREATE  INDEX [IX_jrf_attach] ON [dbo].[jrf_attach]([message_id]) ON [PRIMARY]
GO

 CREATE  INDEX [IX_jrf_audit_log] ON [dbo].[jrf_audit_log]([log_date], [logger], [log_level]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_ban] ADD 
	CONSTRAINT [DF_jrf_ban_type_id] DEFAULT (0) FOR [type_id],
	CONSTRAINT [IX_jrf_ban_1] UNIQUE  NONCLUSTERED 
	(
		[ban_mask]
	)  ON [PRIMARY] 
GO

 CREATE  INDEX [IX_jrf_ban] ON [dbo].[jrf_ban]([type_id]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_ban_type] ADD 
	CONSTRAINT [DF_jrf_ban_type_id_1] DEFAULT (0) FOR [id]
GO

ALTER TABLE [dbo].[jrf_forum] ADD 
	CONSTRAINT [DF__jrf_forum__group__47DBAE45] DEFAULT (0) FOR [groupid],
	CONSTRAINT [DF__jrf_forum__locke__48CFD27E] DEFAULT (0) FOR [locked],
	CONSTRAINT [DF__jrf_forum__forum__49C3F6B7] DEFAULT ('aa') FOR [forum_sort]
GO

 CREATE  INDEX [IX_jrf_forum] ON [dbo].[jrf_forum]([groupid]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_message] ADD 
	CONSTRAINT [DF__jrf_messa__threa__4E88ABD4] DEFAULT (1) FOR [threadid],
	CONSTRAINT [DF__jrf_message__ip__4F7CD00D] DEFAULT ('N/A') FOR [ip]
GO

 CREATE  INDEX [IX_jrf_message] ON [dbo].[jrf_message]([threadid]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_mod] ADD 
	CONSTRAINT [IX_jrf_mod] UNIQUE  NONCLUSTERED 
	(
		[user_name]
	)  ON [PRIMARY] 
GO

 CREATE  INDEX [IX_jrf_mod_1] ON [dbo].[jrf_mod]([forum_id]) ON [PRIMARY]
GO

 CREATE  UNIQUE  INDEX [pend_usr_idx] ON [dbo].[jrf_pending_user]([user_name], [confirm_code]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_rank] ADD 
	CONSTRAINT [DF_jrf_rank_id] DEFAULT (0) FOR [id],
	CONSTRAINT [DF_jrf_rank_rank_count] DEFAULT (0) FOR [rank_count],
	CONSTRAINT [IX_jrf_rank] UNIQUE  NONCLUSTERED 
	(
		[rank_count]
	)  ON [PRIMARY] 
GO

ALTER TABLE [dbo].[jrf_subscribe] ADD 
	CONSTRAINT [DF__jrf_subsc__threa__534D60F1] DEFAULT (0) FOR [threadid]
GO

 CREATE  INDEX [IX_jrf_subscribe] ON [dbo].[jrf_subscribe]([threadid]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_thread] ADD 
	CONSTRAINT [DF__jrf_threa__forum__5535A963] DEFAULT (1) FOR [forumid],
	CONSTRAINT [DF__jrf_threa__locke__5629CD9C] DEFAULT (0) FOR [locked],
	CONSTRAINT [DF__jrf_threa__sortb__571DF1D5] DEFAULT (9) FOR [sortby]
GO

 CREATE  INDEX [IX_jrf_thread] ON [dbo].[jrf_thread]([forumid]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_user] ADD 
	CONSTRAINT [DF__jrf_user__user_s__3E52440B] DEFAULT (0) FOR [user_status],
	CONSTRAINT [DF__jrf_user__auto_l__3F466844] DEFAULT (1) FOR [auto_login],
	CONSTRAINT [DF__jrf_user__show_u__403A8C7D] DEFAULT (1) FOR [show_user_mail],
	CONSTRAINT [DF__jrf_user__mes_pe__412EB0B6] DEFAULT (20) FOR [mes_per_page],
	CONSTRAINT [IX_jrf_user] UNIQUE  NONCLUSTERED 
	(
		[user_name]
	)  ON [PRIMARY] 
GO

 CREATE  UNIQUE  INDEX [user_name] ON [dbo].[jrf_user]([user_name]) ON [PRIMARY]
GO

 CREATE  INDEX [IX_jrf_user_1] ON [dbo].[jrf_user]([user_status]) ON [PRIMARY]
GO

ALTER TABLE [dbo].[jrf_user_status] ADD 
	CONSTRAINT [DF_jrf_user_status_status] DEFAULT (0) FOR [status]
GO


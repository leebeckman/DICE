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
 *				Simone Chiaretta <simo@users.sourceforge.net>
 *        
 * ***** END LICENSE BLOCK ***** */


/****** Object:  Database forum_db    Script Date: 6/2/2004 5:03:20 PM ******/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'forum_db')
	DROP DATABASE [forum_db]
GO

CREATE DATABASE forum_db 

go

USE forum_db

GO 

/****** Object:  Login DB_USER    Script Date: 6/2/2004 5:03:20 PM ******/
if not exists (select * from master.dbo.syslogins where loginname = N'DB_USER')
BEGIN
	declare @logindb nvarchar(132), @loginlang nvarchar(132) select @logindb = N'forum_db', @loginlang = N'us_english'
	if @logindb is null or not exists (select * from master.dbo.sysdatabases where name = @logindb)
		select @logindb = N'master'
	if @loginlang is null or (not exists (select * from master.dbo.syslanguages where name = @loginlang) and @loginlang <> N'us_english')
		select @loginlang = @@language
	exec sp_addlogin N'DB_USER', null, @logindb, @loginlang
END

GO

if not exists (select * from dbo.sysusers where name = N'DB_USER' and uid < 16382)
	EXEC sp_grantdbaccess N'DB_USER', N'DB_USER'
GO

if not exists (select * from dbo.sysusers where name = N'FORUM' and uid > 16399)
	EXEC sp_addrole N'FORUM'
GO

exec sp_addrolemember N'db_owner', N'DB_USER'
GO

exec sp_addrolemember N'db_datareader', N'DB_USER'
GO

exec sp_addrolemember N'db_datawriter', N'DB_USER'
GO

exec sp_addrolemember N'db_ddladmin', N'DB_USER'
GO

exec sp_addrolemember N'FORUM', N'DB_USER'
GO

GRANT CREATE TABLE TO FORUM
go


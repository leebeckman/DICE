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

 *              Simone Chiaretta <simo@users.sourceforge.net>

 *        

 * ***** END LICENSE BLOCK ***** */



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_LOG_ENTRIES_ASC]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_LOG_ENTRIES_ASC]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_LOG_ENTRIES_DESC]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_LOG_ENTRIES_DESC]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_NEW_THREADS]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_NEW_THREADS]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_NEW_THREADS_ALL]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_NEW_THREADS_ALL]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_THREADS]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_THREADS]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_THREAD_MESSAGES]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_THREAD_MESSAGES]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_USERS]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_USERS]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_GET_USER_SUBSCRIPTIONS]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)

drop procedure [dbo].[sp_GET_USER_SUBSCRIPTIONS]

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO



CREATE PROCEDURE  sp_GET_LOG_ENTRIES_ASC



@intimePar varchar(255),

@endtimePar varchar(255),

@loggerPar varchar(255),

@log_levelPar varchar(32),

@remote_ipPar varchar(16),

@session_idPar varchar(255),

@user_namePar varchar(32),

@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int



DECLARE	@log_date varchar(80)

DECLARE	@logger varchar(255)

DECLARE	@log_level varchar(32)

DECLARE	@message varchar(2000)

DECLARE	@remote_ip varchar(16)

DECLARE	@user_name varchar(32)

DECLARE	@session_id varchar(255)





SET @count=0

SET @realStart=@start+1





CREATE TABLE #tmp

	(

         log_date varchar(80),

         logger varchar(255),

         log_level varchar(32),

         message varchar(2000),

         remote_ip varchar(16),

         session_id varchar(255),

         user_name varchar(32)

	)  



DECLARE log_cursor SCROLL CURSOR FOR 

SELECT log_date, logger, log_level, message, remote_ip, session_id, user_name

FROM jrf_audit_log

	WHERE CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) >=  @intimePar AND CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) <= @endtimePar AND 

		logger LIKE @loggerPar AND 

		log_level LIKE @log_levelPar AND 

		remote_ip LIKE @remote_ipPar AND 

		session_id LIKE @session_idPar AND 

		user_name LIKE @user_namePar 

ORDER BY log_date



OPEN log_cursor



FETCH ABSOLUTE @realStart FROM log_cursor

INTO @log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name

IF @@FETCH_STATUS = 0

	insert into #tmp values (@log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name)

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM log_cursor

		INTO @log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name

		IF @@FETCH_STATUS = 0

			insert into #tmp values (@log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name)

	END

END



CLOSE log_cursor

DEALLOCATE log_cursor



select * from #tmp

GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO



CREATE PROCEDURE  sp_GET_LOG_ENTRIES_DESC



@intimePar varchar(255),

@endtimePar varchar(255),

@loggerPar varchar(255),

@log_levelPar varchar(32),

@remote_ipPar varchar(16),

@session_idPar varchar(255),

@user_namePar varchar(32),

@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int



DECLARE	@log_date varchar(80)

DECLARE	@logger varchar(255)

DECLARE	@log_level varchar(32)

DECLARE	@message varchar(2000)

DECLARE	@remote_ip varchar(16)

DECLARE	@user_name varchar(32)

DECLARE	@session_id varchar(255)





SET @count=0

SET @realStart=@start+1





CREATE TABLE #tmp

	(

         log_date varchar(80),

         logger varchar(255),

         log_level varchar(32),

         message varchar(2000),

         remote_ip varchar(16),

         session_id varchar(255),

         user_name varchar(32)

	)  



DECLARE log_cursor SCROLL CURSOR FOR 

SELECT log_date, logger, log_level, message, remote_ip, session_id, user_name

FROM jrf_audit_log

	WHERE CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) >=  @intimePar AND CAST(SUBSTRING(log_date, 1, 19) AS DATETIME) <= @endtimePar AND 

		logger LIKE @loggerPar AND 

		log_level LIKE @log_levelPar AND 

		remote_ip LIKE @remote_ipPar AND 

		session_id LIKE @session_idPar AND 

		user_name LIKE @user_namePar 

ORDER BY log_date DESC



OPEN log_cursor



FETCH ABSOLUTE @realStart FROM log_cursor

INTO @log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name

IF @@FETCH_STATUS = 0

	insert into #tmp values (@log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name)

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM log_cursor

		INTO @log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name

		IF @@FETCH_STATUS = 0

			insert into #tmp values (@log_date,@logger,@log_level,@message,@remote_ip,@session_id,@user_name)

	END

END



CLOSE log_cursor

DEALLOCATE log_cursor



select * from #tmp

GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO







CREATE PROCEDURE  sp_GET_NEW_THREADS



@lintimePar datetime,

@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int



DECLARE	@id int

DECLARE	@sortby int

DECLARE	@locked int

DECLARE	@subject varchar(255)

DECLARE	@fid int

DECLARE	@tot_mes int

DECLARE	@lintime datetime





SET @count=0

SET @realStart=@start+1





CREATE TABLE #tmp

	(

	id int,

	sortby int,

	locked int,

	subject varchar(255),

	fid int,

	tot_mes int,

	lintime datetime

	)  



DECLARE thread_cursor SCROLL CURSOR FOR 

SELECT 

		jrf_thread.threadid AS id, jrf_thread.sortby AS sortby, jrf_thread.locked AS locked, jrf_message.heading AS subject, 

		jrf_thread.forumid AS fid, count(jrf_message.threadid) AS tot_mes, jrf_thread.lintime AS lintime

	FROM 

		jrf_thread, jrf_message, jrf_forum 

	WHERE 

		jrf_thread.forumid = jrf_forum.forumid AND 

		jrf_thread.threadid = jrf_message.threadid AND 

		jrf_thread.lintime > @lintimePar AND jrf_forum.locked < 3

	GROUP BY 

		jrf_thread.threadid, jrf_thread.sortby, jrf_thread.locked, jrf_message.heading, jrf_thread.forumid, jrf_thread.lintime 

	ORDER BY 

		jrf_thread.sortby, jrf_thread.lintime DESC 



OPEN thread_cursor



FETCH ABSOLUTE @realStart FROM thread_cursor

INTO @id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime

insert into #tmp values (@id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime)

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM thread_cursor

		INTO @id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime

		IF @@FETCH_STATUS = 0

			insert into #tmp values (@id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime)

	END

END



CLOSE thread_cursor

DEALLOCATE thread_cursor



select * from #tmp

GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO



CREATE PROCEDURE  sp_GET_NEW_THREADS_ALL



@lintimePar datetime,

@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int



DECLARE	@id int

DECLARE	@sortby int

DECLARE	@locked int

DECLARE	@subject varchar(255)

DECLARE	@fid int

DECLARE	@tot_mes int

DECLARE	@lintime datetime





SET @count=0

SET @realStart=@start+1





CREATE TABLE #tmp

	(

	id int,

	sortby int,

	locked int,

	subject varchar(255),

	fid int,

	tot_mes int,

	lintime datetime

	)  



DECLARE thread_cursor SCROLL CURSOR FOR 

SELECT 

		jrf_thread.threadid AS id, jrf_thread.sortby AS sortby, jrf_thread.locked AS locked, jrf_message.heading AS subject, 

		jrf_thread.forumid AS fid, count(jrf_message.threadid) AS tot_mes, jrf_thread.lintime AS lintime

	FROM 

		jrf_thread, jrf_message, jrf_forum 

	WHERE 

		jrf_thread.forumid = jrf_forum.forumid AND 

		jrf_thread.threadid = jrf_message.threadid AND 

		jrf_thread.lintime > @lintimePar 

	GROUP BY 

		jrf_thread.threadid, jrf_thread.sortby, jrf_thread.locked, jrf_message.heading, jrf_thread.forumid, jrf_thread.lintime 

	ORDER BY 

		jrf_thread.sortby, jrf_thread.lintime DESC 



OPEN thread_cursor



FETCH ABSOLUTE @realStart FROM thread_cursor

INTO @id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime

insert into #tmp values (@id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime)

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM thread_cursor

		INTO @id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime

		IF @@FETCH_STATUS = 0

			insert into #tmp values (@id,@sortby,@locked,@subject,@fid,@tot_mes,@lintime)

	END

END



CLOSE thread_cursor

DEALLOCATE thread_cursor



select  * from #tmp

GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO







CREATE PROCEDURE  sp_GET_THREADS



@forumId int,

@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int



DECLARE	@id int

DECLARE	@sortby int

DECLARE	@locked int

DECLARE	@subject varchar(255)

DECLARE	@tot_mes int





SET @count=0

SET @realStart=@start+1





CREATE TABLE #tmp

	(

	id int,

	sortby int,

	locked int,

	subject varchar(255),

	tot_mes int

	)  



DECLARE thread_cursor SCROLL CURSOR FOR 

SELECT     jrf_thread.threadid AS id, jrf_thread.sortby AS sortby, jrf_thread.locked AS locked, 'subject' AS subject, COUNT(jrf_thread.threadid) 

                      AS tot_mes

FROM         jrf_thread INNER JOIN

                      jrf_message ON jrf_thread.threadid = jrf_message.threadid

WHERE     (jrf_thread.forumid = @forumId)

GROUP BY jrf_thread.threadid,jrf_thread.sortby,jrf_thread.locked, jrf_thread.lintime

ORDER BY jrf_thread.sortby, jrf_thread.lintime DESC



OPEN thread_cursor



FETCH ABSOLUTE @realStart FROM thread_cursor

INTO @id,@sortby,@locked,@subject,@tot_mes

insert into #tmp values (@id,@sortby,@locked,@subject,@tot_mes)

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM thread_cursor

		INTO @id,@sortby,@locked,@subject,@tot_mes

		IF @@FETCH_STATUS = 0

			insert into #tmp values (@id,@sortby,@locked,@subject,@tot_mes)

	END

END



CLOSE thread_cursor

DEALLOCATE thread_cursor



select * from #tmp





GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO







CREATE PROCEDURE  sp_GET_THREAD_MESSAGES



@threadIdPar int,

@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int





DECLARE	@id int

DECLARE	@sender varchar(32)

DECLARE	@cententsPtr varbinary(16)

DECLARE	@cententsPtr1 varbinary(16)

DECLARE	@intime datetime

DECLARE	@heading varchar(255)

DECLARE	@threadid int

DECLARE	@ip varchar(15)





SET @count=0

SET @realStart=@start+1



CREATE TABLE #tmp (

	id int,

	sender varchar(32),

	centents text,

	intime datetime ,

	heading varchar(255),

	threadid int,

	ip varchar(15)

) 





DECLARE thread_cursor SCROLL CURSOR FOR 

SELECT id,sender,TEXTPTR(centents),intime,heading,threadid,ip FROM jrf_message WHERE threadid = @threadIdPar ORDER BY intime



OPEN thread_cursor



FETCH ABSOLUTE @realStart FROM thread_cursor

INTO @id,@sender,@cententsPtr,@intime,@heading,@threadid,@ip

insert into #tmp values (@id,@sender,'',@intime,@heading,@threadid,@ip)

SELECT @cententsPtr1=TEXTPTR(centents) from #tmp where id=@id

UPDATETEXT #tmp.centents @cententsPtr1 0 0 jrf_message.centents @cententsPtr

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM thread_cursor

		INTO @id,@sender,@cententsPtr,@intime,@heading,@threadid,@ip

		IF @@FETCH_STATUS = 0

			BEGIN

			insert into #tmp values (@id,@sender,'',@intime,@heading,@threadid,@ip)

			Select @cententsPtr1=TEXTPTR(centents) from #tmp where id=@id

			UPDATETEXT #tmp.centents @cententsPtr1 0 0 jrf_message.centents @cententsPtr

			END

	END

END



CLOSE thread_cursor

DEALLOCATE thread_cursor



select centents,sender,ip,intime,id,heading from #tmp





GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO







CREATE PROCEDURE  sp_GET_USERS



@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int



DECLARE	@id int

DECLARE	@user_status int

DECLARE	@user_name varchar(32)





SET @count=0

SET @realStart=@start+1





CREATE TABLE #tmp

	(

	user_name varchar(32),

	id int,

	user_status int

	)  



DECLARE user_cursor SCROLL CURSOR FOR 

SELECT user_name, id, user_status FROM jrf_user ORDER BY user_name



OPEN user_cursor



FETCH ABSOLUTE @realStart FROM user_cursor

INTO @user_name,@id,@user_status

insert into #tmp values (@user_name,@id,@user_status)

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM user_cursor

		INTO @user_name,@id,@user_status

		IF @@FETCH_STATUS = 0

			insert into #tmp values (@user_name,@id,@user_status)

	END

END



CLOSE user_cursor

DEALLOCATE user_cursor



select * from #tmp





GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO



SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS OFF 

GO





CREATE PROCEDURE  sp_GET_USER_SUBSCRIPTIONS



@userName varchar(32),

@start int,

@pageSize int



  AS

  

DECLARE @count int

DECLARE @realStart int



DECLARE	@heading varchar(255)

DECLARE	@forumId int

DECLARE	@threadid int

DECLARE	@intime datetime





SET @count=0

SET @realStart=@start+1





CREATE TABLE #tmp

	(

	heading varchar(255),

	forumId int,

	threadid int,

	intime datetime

	)  



SELECT DISTINCT 

	jrf_message.heading, jrf_thread.forumid,t1.threadid,  t1.intime



FROM jrf_message, jrf_thread,



(SELECT DISTINCT 

			jrf_message.threadid, MIN(jrf_message.intime) as intime

		FROM jrf_subscribe, jrf_message

		WHERE 

			jrf_message.threadid = jrf_subscribe.threadid AND 

			user_name =@userName

		GROUP BY jrf_message.threadid ) t1 

		WHERE 

		jrf_message.intime = t1.intime AND 

				jrf_message.threadid = t1.threadid AND 

			jrf_thread.threadid = t1.threadid 

			ORDER BY t1.intime 





OPEN thread_cursor



FETCH ABSOLUTE @realStart FROM thread_cursor

INTO @heading,@forumId,@threadid,@intime

insert into #tmp values (@heading,@forumId,@threadid,@intime)

WHILE @@FETCH_STATUS = 0 and @count<@pageSize

BEGIN

	SET @count=@count+1

	IF @count<@pageSize

	BEGIN

		FETCH NEXT FROM thread_cursor

		INTO @heading,@forumId,@threadid,@intime

		IF @@FETCH_STATUS = 0

			insert into #tmp values (@heading,@forumId,@threadid,@intime)

	END

END



CLOSE thread_cursor

DEALLOCATE thread_cursor



select heading,forumid,threadid from #tmp



GO

SET QUOTED_IDENTIFIER OFF 

GO

SET ANSI_NULLS ON 

GO




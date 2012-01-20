<%@ page import="org.jresearch.gossip.IConst,
                org.jresearch.gossip.beans.user.User" %>
<HTML>
<HEAD>
<TITLE>SetUpExtUser</TITLE>
</HEAD>

<BODY BGCOLOR="#FFFFFF" TEXT="#000000" LINK="#FF0000" VLINK="#800000" ALINK="#FF00FF" >
<%
User user=new User();
user.setName(request.getParameter("extUid"));
session.setAttribute(IConst.SESSION.EXT_USER_KEY,user);
%>
Ext User is set as <b>"<%=user.getName()%>"</b>
<%if(request.getParameter("forwardUrl")!=null){%>
<%
        response.sendRedirect(request.getParameter("forwardUrl"));%>
<%}%>
</BODY>
</HTML>

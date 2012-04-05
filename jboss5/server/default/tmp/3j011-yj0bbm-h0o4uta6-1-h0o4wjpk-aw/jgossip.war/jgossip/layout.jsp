<%@ include file="jspf/jsp_header.jspf"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html:html locale="true">
<head>	
<title><gossip:config key="<%=IConst.CONFIG.DOMAIN_NAME%>"/> :: <gossip:config key="<%=IConst.CONFIG.SITE_NAME%>"/></title>
<meta http-equiv="content-type" content="text/html;charset=ISO-8859-5"> 
    <script>
    function unQuote(x) {
	    var s = String(x);
	    var test = /(\&\#)([0-9]+)(\;)/g
	    return s.replace(test,function ($0,$1,$2) {return String.fromCharCode($2);})
    }
    var WEB_ROOT="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>";
    </script>
<link rel="icon" href="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/favicon.ico" type="image/x-icon">
<link rel="top" href="Main.do" title="<fmt:message key="forum.ROOT"/>" />
<link rel="search" href="showSearch.do" title="<fmt:message key="forum.SEARCH"/>" />
<link rel="help" href="showTipsAndTricks.do" title="<fmt:message key="global.TRICKS"/>" />
<c:if test="${!empty requestScope.JRF_GROUPS}" >
    <c:forEach items="${requestScope.JRF_GROUPS}" var="group">
       <c:forEach items="${group.forums}" var="forum" >		
  	       <link rel="chapter forum" href="<c:url value="ShowForum.do">
			                                       <c:param name="fid" value="${forum.forumid}"/>
                                            </c:url>" title="<c:out value="${forum.title}"/>" />
  	   </c:forEach>
    </c:forEach>
</c:if>
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="RSSLastTopics.do" />
<tiles:insert attribute="css"/>
<tiles:insert attribute="validate_js" ignore="true"/>
</head>
<body>

<tiles:insert attribute="guard"  ignore="true"/>
<tiles:insert attribute="header"/>
<tiles:insert attribute="content"/>
<tiles:insert attribute="statistics"/>
<tiles:insert attribute="footer"/>


<span style="font-size: 10px;">
 <br>
 <strong>Powered by <a href="http://sourceforge.net/projects/jgossipforum/">jGossip</a> <jsp:include page="version.html"/></strong>
</span>
 <br>
 <span style="font-size: 9px;">
  This product includes software developed by the <a href="http://www.apache.org/" style="text-decoration:none" target="_blank">Apache Software Foundation</a>
 </span>
 <br>
  <a href="http://sourceforge.net"><img src="http://sourceforge.net/sflogo.php?group_id=91944&amp;type=1" width="88" height="31" border="0" alt="SourceForge.net Logo" /></a>
</body>
</html:html>
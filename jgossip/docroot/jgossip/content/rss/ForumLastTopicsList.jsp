<?xml version="1.0" encoding="@charset@"?>
<%@ page contentType="text/xml;charset=@charset@"
      pageEncoding="@charset@"%>
<%--
/* ***** BEGIN LICENSE BLOCK *****
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
 * ***** END LICENSE BLOCK ***** */
--%>  <%@ page import="org.jresearch.gossip.IConst" %>
<%@ page errorPage="../errorInJsp.jsp" %>
<%@ taglib uri="/WEB-INF/gossip.tld" prefix="gossip" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<fmt:setTimeZone value="GMT"/>
<fmt:setLocale value="en"/>
<rss version="2.0">
    <channel>
        <title>
            <gossip:codec><gossip:config key="<%=IConst.CONFIG.SITE_NAME%>"/>&#xA0;::&#xA0;<c:out value="${requestScope.JRF_CURR_FORUM.title}" escapeXml="false"/></gossip:codec>
        </title>
        <link>
            http://<c:out value="${requestScope.JRF_SITE_URL}"/>ShowForum.do?fid=<c:out value="${requestScope.JRF_CURR_FORUM.forumid}"/>
        </link>
        <image>
            <title><gossip:codec><gossip:config key="<%=IConst.CONFIG.SITE_NAME%>"/></gossip:codec></title>
            <width>90</width>
            <height>91</height>
            <url>
                http://<%=request.getServerName()%>:<%=request.getServerPort()%><gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/logo_top.gif
            </url>
            <link>http://<c:out value="${requestScope.JRF_SITE_URL}"/>Main.do</link>
        </image>
        <description>
            <gossip:codec value="${requestScope.JRF_CURR_FORUM.description}"/>
        </description>
        <category>
            <gossip:codec value="${requestScope.JRF_CURR_FORUM.title}"/>
        </category>
        <lastBuildDate>
            <fmt:formatDate  value="${requestScope.JRF_RSS_PUB_DATE}" pattern="EEE, dd MMM yyyy HH:mm:ss z" />
        </lastBuildDate>
        <pubDate>
            <fmt:formatDate  value="${requestScope.JRF_RSS_PUB_DATE}" pattern="EEE, dd MMM yyyy HH:mm:ss z" />
        </pubDate>
        <generator>jGossip <jsp:include page="../version.html"/></generator>
        <ttl><gossip:config key="<%=IConst.CONFIG.RSS_TTL%>"/></ttl>
            <c:forEach var="thread" items="${requestScope.JRF_RECORDS_DATA}">
                <item>
                    <title><gossip:codec value="${thread.subject}"/></title>
                     <description>
                        <gossip:codec><gossip:process value="${thread.rootMessage.centents}" skipSmiles="true"/></gossip:codec>
                    </description>
                    <author>
                        <![CDATA[<c:out value="${thread.rootMessage.sender}"/>]]>&lt;a@b.cd&gt;
                    </author>
                    <link>
                        http://<c:out value="${requestScope.JRF_SITE_URL}"/>ShowThread.do?fid=<c:out value="${thread.forumid}"/>&amp;tid=<c:out value="${thread.threadid}"/>
                    </link>
                    <guid isPermaLink="true">
                        http://<c:out value="${requestScope.JRF_SITE_URL}"/>ShowThread.do?fid=<c:out value="${thread.forumid}"/>&amp;tid=<c:out value="${thread.threadid}"/>
                    </guid>
                      <pubDate>
                        <fmt:formatDate value="${rootMessage.intime}" pattern="EEE, dd MMM yyyy HH:mm:ss z" />
                    </pubDate>
                    <comments>
                        http://<c:out value="${requestScope.JRF_SITE_URL}"/>Quote.do?fid=<c:out value="${thread.forumid}"/>&amp;tid=<c:out value="${thread.threadid}"/>&amp;mid=<c:out value="${thread.rootMessage.id}" />
                    </comments>    
                </item>
            </c:forEach>
    </channel>
</rss>             



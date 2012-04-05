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
 *        
 * ***** END LICENSE BLOCK ***** */
--%> <%@ include file="../jspf/jsp_header.jspf"%>

<c:if test="${empty sessionScope.JRF_CURR_FORUM}">
  <c:choose>
    <c:when test="${empty param.tid}">
	    <logic:forward name='jgossip-show-topic'/>
    </c:when>
    <c:otherwise>
	    <logic:forward name='jgossip-show-forum'/>
    </c:otherwise>
  </c:choose>
</c:if>


<c:choose>
    <c:when test="${empty requestScope.addMessageForm||empty requestScope.addMessageForm.tid}">
	    <c:set var="MESSAGE_ACTION_KEY" value="messages.ADD_MESSAGE"/>
    </c:when>
    <c:otherwise>
	    <c:set var="MESSAGE_ACTION_KEY" value="messages.REPLY"/>
    </c:otherwise>
</c:choose>
<c:set var="message_form_name" value="addMessageForm"/>
<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:link>
			ShowForum.do?fid=<%=request.getParameter("fid")%>
		</gossip:link>
		<gossip:title>
			<c:out value="${sessionScope.JRF_CURR_FORUM.title}"/>
		</gossip:title>
	</gossip:navElement>
<c:if test="${!empty param.tid&&!empty sessionScope.JRF_CURR_THREAD}">
	<gossip:navElement>
		<gossip:link>
			ShowThread.do?fid=<%=request.getParameter("fid")%>&tid=<%=request.getParameter("tid")%>&block=<%=request.getParameter("block")!=null?request.getParameter("block"):"0"%>
		</gossip:link>
		<gossip:title>
			<gossip:codec><c:out value="${sessionScope.JRF_CURR_THREAD.subject}" escapeXml="false"/></gossip:codec>
		</gossip:title>
	</gossip:navElement>
</c:if>
	<gossip:navElement>
		<gossip:title>
			<fmt:message key='${pageScope.MESSAGE_ACTION_KEY}'/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>

<%@ include file="../jspf/topbar.jspf"%>

<html:form method="post" action="/ProcessMessage" onsubmit="hideErrors();return validateAddMessageForm(this);" >
	<%@ include file="../jspf/messageForm.jspf"%>
</html:form>
<html:javascript formName="addMessageForm" staticJavascript="false"/>



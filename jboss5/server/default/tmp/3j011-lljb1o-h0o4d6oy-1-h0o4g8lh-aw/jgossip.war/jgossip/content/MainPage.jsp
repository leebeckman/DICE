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
<fmt:setTimeZone value="${sessionScope.JRF_TIME_ZONE}" scope="session"/>

<%@ include file="../jspf/topbar.jspf"%>

 

 <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption"><gossip:config key="<%=IConst.CONFIG.SITE_NAME%>"/></span>&nbsp;&nbsp;
			</td>
			<td width="50%" colspan="3">
			</td>
			<td align="right">
				<html:link action="RSSLastTopics" titleKey="global.RSS" target="_blank"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/xml.gif" alt="" width="36" height="14" border="0"></html:link>
			</td>
		</tr>
		<tr>
			<td class="tb_o" height="4"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g_tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tb_o" colspan="4"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td height="8"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="b_g" colspan="4"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td class="icn">
				<span class="c_title">&nbsp;<fmt:message key="forum.STATUS"/>&nbsp;</span>
			</td>
			<td class="l_g">	
				<span class="c_title">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="forum.FORUM_NAME"/>&nbsp;</span>
			</td>
			<td width="60%">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td class="l_g" align="center">
				<span class="c_title">&nbsp;<fmt:message key="forum.MESGS"/>&nbsp;</span>
			</td>
			<td class="l_g" align="center">
				<span class="c_title">&nbsp;<fmt:message key="forum.TOPICS"/>&nbsp;</span>
			</td>
			<td class="lr_g" align="center">
				<span class="c_title">&nbsp;<fmt:message key="forum.L_MSG"/>&nbsp;</span>
			</td>
		</tr>
  <c:choose>		
	<c:when test="${!empty requestScope.JRF_GROUPS}" >
		<c:forEach items="${requestScope.JRF_GROUPS}" var="group"> 
		<tr class="lght">
			<td class="t_g" colspan="6">
				<br><span class="caption">&nbsp;<c:out value="${group.name}"/><br></span>
			</td>
		</tr>
		   <c:choose>		
	        <c:when test="${!empty group.forums}" >
      		<c:forEach items="${group.forums}" var="forum" varStatus="status">
				<c:set var="lastmessage" value="${forum.lastMessage}" />
		<tr class="strip<c:out value="${status.count%2}"/>">
			<td class="lt_g_txt_b" align="center">
			<%-- set icon type--%>
                	<%String icon="off";%>
                	<c:if test="${forum.threadsCount>0}">    		
                		<gossip:hasNewMess intime="${lastmessage.intime}" tid="${forum.lastMessThreadId}" sender="${lastmessage.sender}">
                			<%icon="on";%>
                		</gossip:hasNewMess>
                	</c:if>
                 <img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/<%=icon%>.gif" width="25" height="25" border="0">
			</td>
			<td class="lt_g_txt_b" colspan="2"><a href="<c:url value="ShowForum.do">
			                                                <c:param name="fid" value="${forum.forumid}"/>
                                                       </c:url>" class="forum_name"><c:out value="${forum.title}"/></a><br>
                  <span class="txt"><c:out value="${forum.description}"/></span>
			</td>
			<td class="lt_g_txt_b" align="center"><c:out value="${forum.messCount}"/>
			</td>
			<td class="lt_g_txt_b" align="center"><c:out value="${forum.threadsCount}"/>
			</td>
			<td class="lrt_g_txt_b" nowrap> 
              <c:choose>		
	            <c:when test="${forum.threadsCount>0}" >
                <c:set var="senderInfo" value="${lastmessage.senderInfo}"/>
                  <span class="txt"><a href="<c:url value="ShowMessage.do">
                                       <c:param name="fid" value="${forum.forumid}"/>
                                       <c:param name="tid" value="${forum.lastMessThreadId}"/>
                                       <c:param name="mid" value="${lastmessage.id}"/>
                                   </c:url>"><gossip:process cutToLength="26" value="${lastmessage.centents}" /></a> (
                
                      <c:if test="${senderInfo.status>0}" var="isReg">
                          <a href="<c:url value="ShowUser.do">
                                       <c:param name="uid" value="${lastmessage.sender}"/>
                                   </c:url>">
                      </c:if>       	
                          		<c:out value="${lastmessage.sender}"/>
                      <c:if test="${isReg}">
                          	</a>
                      </c:if>
                  )
                  <br>
                 	<fmt:formatDate value="${lastmessage.intime}" type="both" dateStyle="short" timeStyle="short"/>
                  </span>
                 </c:when>
	             <c:otherwise>
                     <fmt:message key="global.NONE"/>
                 </c:otherwise>
              </c:choose>
                 
			</td>
		</tr>
		   </c:forEach>
		   </c:when>
	       <c:otherwise>
            <tr class="drk">
              <td class="lrt_g_txt_b" colspan="6">
                  <fmt:message key="forum.NO_FORUMS"/>!
                  	<c:if test="${sessionScope.JRF_USER.status==10}">
                     [ <a href="<c:url value="showAddForum.do">
			                       <c:param name="groupid" value="${group.groupid}"/>
                                </c:url>"><fmt:message key="global.CREATE_SOME"/></a> ]
                	</c:if>
              </td>
            </tr>
           </c:otherwise>
        </c:choose>
		</c:forEach>
	</c:when>
	<c:otherwise>
		 <tr class="lght">
			<td class="t_g" colspan="6">
      			<fmt:message key="global.ERROR"/>!<br>
                    <fmt:message key="forum.NFTD"/>!
                    <c:if test="${sessionScope.JRF_USER.status==10}">
                      <html:link styleClass="control" action="showAddGroup"><fmt:message key="global.CREATE_SOME"/></html:link> 
                    </c:if>
              <br>
              	</td>
		   </tr>
    </c:otherwise>
</c:choose>
		<tr class="lght">
			<td class="t_g" colspan="6">	&nbsp;
			</td>
		</tr>
	</table> 
	

<table width="98%">
	<tr>
	    <c:if test="${!empty pageScope.JRF_HAVE_AN_UPDATED_TOPICS}">
	    <td align="left">
	        <html:link action="MarkRead"><fmt:message key="global.MARK_READ"/></html:link>	
	    </td>
	    </c:if>
		<td align="right">
		<c:choose>		
	       <c:when test="${empty sessionScope.JRF_USER_TIME_ZONE}" >
			    <fmt:message key="global.TIME_GMT"/>
		   </c:when>
		   <c:otherwise>
			    <fmt:message key="global.TIME_LOCAL"/> (<c:out value="${sessionScope.JRF_TIME_ZONE}"/>)
		   </c:otherwise>
		</c:choose>
		</td>
	</tr>
</table>






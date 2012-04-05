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
 *              Simone Chiaretta <simone@piyosailing.com>
 * ***** END LICENSE BLOCK ***** */
--%>  <%@ include file="../jspf/jsp_header.jspf"%>

<fmt:setTimeZone value="${sessionScope.JRF_TIME_ZONE}" scope="session"/>


<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<c:out value="${requestScope.JRF_CURR_FORUM.title}"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

  <table width="98%" cellspacing="0" cellpadding="0">
			<tr>
				<td width="70">	&nbsp;&nbsp;
				</td>
				<td class="top_tab" nowrap>	&nbsp;&nbsp;<fmt:message key="forum.FORUM"/>: <span class="caption"> <gossip:process cutToLength="40" value="${requestScope.JRF_CURR_FORUM.title}"/></span>&nbsp;&nbsp;
				</td>
				<td width="50%" colspan="2" align="right">
					&nbsp;
				</td>
				<td>
					<table width="100%">
						<tr>
							<td>
							<c:choose>
					          <c:when test="${(requestScope.JRF_CURR_FORUM.locked==1||requestScope.JRF_CURR_FORUM.locked==2)&&sessonScope.JRF_USER.status<9}" >
						           <img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/locked.gif" alt="" width="25" height="25" hspace="4" border="0" align="middle"><span class="caption"><nobr><fmt:message key="forum.LOCK1T"/></nobr>&nbsp;&nbsp;&nbsp;</span>
						     </c:when>
						     <c:otherwise>
								<img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/new.gif" alt="" width="25" height="25" hspace="4" border="0" align="middle">
								<a class="control" href="<c:url value="showAddMessage.do">
								                             <c:param name="fid" value="${param.fid}"/>                                      
								                        </c:url>"><fmt:message key="threads.N_TOPIC"/></a>
					         </c:otherwise>
					       </c:choose>
							<td>
							<td align="right">
								<a href="<c:url value="RSSLastTopics.do">
								                             <c:param name="fid" value="${param.fid}"/>                                      
								                        </c:url>" title="<fmt:message key="global.RSS"/>" target="_blank" ><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/xml.gif" alt="" width="36" height="14" border="0"></a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
			<td class="tb_o" height="4"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g_tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tb_o" colspan="3"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td height="8"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="b_g" colspan="3"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td class="icn">
				<span class="c_title">&nbsp;Status&nbsp;</span>
			</td>
			<td class="l_g">	&nbsp;&nbsp;&nbsp;
				<span class="c_title">&nbsp;<fmt:message key="threads.T_TIT"/>&nbsp;</span>
			</td>
			<td width="50%">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td class="l_g" align="center">
				<span class="c_title">&nbsp;<fmt:message key="forum.MESGS"/>&nbsp;</span>
			</td>
			<td class="lr_g" align="center">
				<span class="c_title"><fmt:message key="forum.L_MSG"/>&nbsp;</span>
			</td>
		</tr>
		      <c:forEach var="thread" items="${requestScope.JRF_RECORDS_DATA.records}" varStatus="status">
              		<c:set var="lastMessage" value="${thread.lastMessage}"/>
             <%-- set icon type--%>
              <%String icon="off";%>
                	<gossip:hasNewMess intime="${lastMessage.intime}" tid="${thread.threadid}" sender="${lastMessage.sender}">
                		<%icon="on";%>
                	</gossip:hasNewMess>
              <tr class="strip<c:out value="${status.count%2}"/>">
                <td class="lt_g_txt_b" align="center">
				<c:if test="${thread.sortby<6}" var="isAnnounce">
					<%icon+="_adm";%>
				</c:if>
					<c:if test="${!empty requestScope.JRF_MOD_FLAG}" var="isUserMod">
					   <c:if test="${isAnnounce}">
					    <a href="<c:url value="UnAnnounce.do">
					         <c:param name="fid" value="${param.fid}"/>
					         <c:param name="tid" value="${thread.threadid}"/>
					       </c:url>">
					    </c:if>
					</c:if>
				
                  <img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/<%=icon%>.gif" width="25" height="25" border=0>				
					<c:if test="${isUserMod&&isAnnounce}">
					</a>
					</c:if>
                </td>
                <td class="lt_g_txt_b" colspan="2">	
					<table width="100%" cellspacing="0" cellpadding="0">
			        	<tr>
				        	<td class="txt_b">
		                      <a href="<c:url value="ShowThread.do">
					                        <c:param name="fid" value="${param.fid}"/>
					                        <c:param name="tid" value="${thread.threadid}"/>
					                   </c:url>" class="thread_name">
		                      	<gossip:codec value="${thread.subject}"/>
		                      </a>
							 &nbsp;<em><gossip:pageRef threadBean="thread" forumId="${param.fid}"/></em>
		                	</td>
	                	<td align="right">&nbsp;
	                <c:choose>	
	                    <c:when test="${isUserMod}">
							<a class="control" href="<c:url value="DeleteThread.do">
					                                  <c:param name="fid" value="${param.fid}"/>
					                                  <c:param name="tid" value="${thread.threadid}"/>
					                                </c:url>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" width="25" height="25" alt="<fmt:message key="forum.DELETE"/>" title="<fmt:message key="forum.DELETE"/>" border=0></a>
							<a class="control" href="<c:url value="LockThread.do">
					                                  <c:param name="fid" value="${param.fid}"/>
					                                  <c:param name="tid" value="${thread.threadid}"/>
					                                </c:url>">
							<c:choose>	
	                           <c:when test="${thread.locked==0}">
								<img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/unlocked.gif" width="25" height="25" alt="<fmt:message key="forum.TLOCK"/>" title="<fmt:message key="forum.TLOCK"/>" border=0>
							   </c:when>
							   <c:otherwise>
								<img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/locked.gif" width="25" height="25" alt="<fmt:message key="forum.TUNLOCK"/>" title="<fmt:message key="forum.TUNLOCK"/>" border=0>
							   </c:otherwise>
							</c:choose>
							</a>
	                    	<c:if test="${sessionScope.JRF_USER.status>8}">	
								<a class="control" href="<c:url value="showMoveThread.do">
					                                  <c:param name="fid" value="${param.fid}"/>
					                                  <c:param name="tid" value="${thread.threadid}"/>
					                                </c:url>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/move.gif" width="25" height="25" alt="<fmt:message key="global.MOVE"/>" title="<fmt:message key="global.MOVE"/>" border=0></a>
							</c:if>
	                    </c:when>
	                    <c:when test="${!isUserMod&&thread.locked==1}">
								<img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/locked.gif" width="25" height="25" alt="<fmt:message key="forum.TLOCKED"/>"  title="<fmt:message key="forum.TLOCKED"/>" border=0>
						</c:when>
					 </c:choose>
						</td>
			        	</tr>
			        </table>	
				</td>
                <td class="lt_g_txt_b" align="center"><c:out value="${thread.messagesCount}"/></td>
                <td class="lrt_g_txt_b" nowrap>
                <nobr><a href="<c:url value="ShowMessage.do">
					                                  <c:param name="fid" value="${param.fid}"/>
					                                  <c:param name="tid" value="${thread.threadid}"/>
					                                  <c:param name="mid" value="${lastMessage.id}"/>
					                                </c:url>"><gossip:process cutToLength="26" value="${lastMessage.centents}" /></a>
						( <c:if test="${lastMessage.senderInfo.status>0}" var="isReg">
                          <a href="<c:url value="ShowUser.do">
                                       <c:param name="uid" value="${lastMessage.sender}"/>
                                   </c:url>">
                          </c:if>       	
                          		<c:out value="${lastMessage.sender}"/>
                          <c:if test="${isReg}">
                          	</a>
                          </c:if>
                         )
                  </nobr><br><b>
                  <fmt:formatDate value="${lastMessage.intime}" type="both" dateStyle="short" timeStyle="short"/>&nbsp;</b></td>
            </tr>
            
         </c:forEach>
         <c:choose>
             <c:when test="${requestScope.JRF_RECORDS_DATA.recordsCount>0}">
					 <tr class="lght">
                        <td class="t_g" colspan="5">&nbsp;
							<%@ include file="../jspf/pageSplit.jspf"%>
                        </td>
                    </tr>
                   
             </c:when>
             <c:otherwise>
                      <tr class="lght">
                        <td class="t_g" colspan="5"><fmt:message key="threads.NO_THREADS"/>
                        </td>
                      </tr>
             </c:otherwise>
        </c:choose>
            </table>
<c:if test="${!empty requestScope.JRF_RECORDS_DATA&&requestScope.JRF_RECORDS_DATA.recordsCount>0}">
<table width="98%">
	<tr>
	    <c:if test="${!empty pageScope.JRF_HAVE_AN_UPDATED_TOPICS}">
	    <td align="left">
	        <a href="<c:url value="MarkRead.do">
					     <c:param name="fid" value="${param.fid}"/>
					 </c:url>"/><fmt:message key="forum.MARK_READ"/></a>	
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
</c:if>

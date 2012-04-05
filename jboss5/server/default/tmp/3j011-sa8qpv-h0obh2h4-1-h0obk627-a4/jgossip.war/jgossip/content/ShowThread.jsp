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
--%>  <%@ include file="../jspf/jsp_header.jspf"%>

<fmt:setTimeZone value="${sessionScope.JRF_TIME_ZONE}" scope="session"/>


<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:link>
			ShowForum.do?fid=<%=request.getParameter("fid")%>
		</gossip:link>
		<gossip:title>
			<gossip:codec value="${requestScope.JRF_CURR_FORUM.title}" />
		</gossip:title>
	</gossip:navElement>
	<gossip:navElement>
		<gossip:title>
			<gossip:codec value="${requestScope.JRF_CURR_THREAD.subject}"  />
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>

<%@ include file="../jspf/topbar.jspf"%>


<%String icon_state = "answer";%>
<c:if test="${empty param.block||param.block==0}">
    <%icon_state = "question";%> 
</c:if>
<table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap><fmt:message key="messages.THREAD"/>: <gossip:process cutToLength="26" value="${requestScope.JRF_CURR_THREAD.subject}"  />
			</td>
			<td width="70%" align="right">
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
								<%@ include file="../jspf/pageSplit.jspf"%>							
							</td>
						</tr>
					</table>
			
			</td>
		</tr>
		<tr>
			<td class="tb_o" height="4"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g_tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tb_o" ><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td height="8"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="b_g" ><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td class="icn">
				<span class="c_title">&nbsp;<fmt:message key="messages.SENDER"/>&nbsp;</span>
			</td>
			<td class="l_g">	&nbsp;&nbsp;&nbsp;
				<span class="c_title">&nbsp;<fmt:message key="messages.MESSAGE"/>	&nbsp;</span>
			</td>
			<td class="r_g">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
    <c:forEach var="message" items="${requestScope.JRF_RECORDS_DATA.records}" varStatus="status">
        <c:set var="sender" value="${message.senderInfo}"/>
        <c:set var="senderInfo" value="${sender.info}"/>
        <c:set var="senderSettings" value="${sender.settings}"/>
		<tr class="strip<c:out value="${status.count%2}"/>">
			<td class="tl_g" align="left" valign="top" style="padding:3px;">
				<a name="<c:out value="${message.id}"/>">&nbsp;</a>
                		<span class="txt_b">
                  		<c:if test="${sender.status>0}" var="isReg">
                  		<gossip:ifconfig key="enableAvatar">
                  			<img src="<c:url value="ShowAvatar.do">
                  					  <c:param name="GZIP_NOT_ALLOWED" value="Y"/>
                                      <c:param name="uid" value="${message.sender}"/>
                                   </c:url>"/>
                        </gossip:ifconfig>
                  		<br>
                          <a href="<c:url value="ShowUser.do">
                                      <c:param name="uid" value="${message.sender}"/>
                                   </c:url>">
                        </c:if>
                          	<c:out value="${message.sender}"/>
                        <c:if test="${isReg}">
                          </a>&nbsp;
                        </c:if>
                  </span><br>
                  <gossip:userStatus status="${sender.status}" count="${sender.totalMess}"/><br>
                  <br>
                  <b><fmt:message key="messages.FROM"/>:</b> <gossip:codec value="${senderInfo.city}" /><br>
                  <b><fmt:message key="forum.MESGS"/>:</b> <c:out value="${sender.totalMess}"/><br>
				  <c:if test="${sessionScope.JRF_USER.status>6}">
				  <b>IP:</b> <c:out value="${message.ip}"/>
				  </c:if>
			</td>
			<td class="ltr_g" colspan="2">	
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2"  style="padding:3px;"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/<%=icon_state%>.gif" align="middle" alt="" width="25" height="25" border="0">&nbsp;<gossip:codec value="${message.heading}" /></td>
					</tr>
					<tr>
						<td class="tb_g"  style="padding:3px;"><nobr><span class="txt_b">&nbsp;<fmt:message key="messages.SENT"/>:</span> <fmt:formatDate value="${message.intime}" type="both" dateStyle="short" timeStyle="short"/></nobr></td>
						<td class="tb_g" align="right" style="padding:3px;">
							<nobr>
							 <c:set value="${(requestScope.JRF_CURR_FORUM.locked==2&&sessionScope.JRF_USER.status<9)||(requestScope.JRF_CURR_THREAD.locked==1&&empty requestScope.JRF_MOD_FLAG)}" var="locked"/>

							  <c:if test="${!empty requestScope.JRF_MOD_FLAG}" var="can_edit">
                              	<a href="<c:url value="DeleteMessage.do">
                              	        <c:param name="tid" value="${param.tid}"/>
                              	        <c:param name="fid" value="${param.fid}"/>
                              	        <c:param name="mid" value="${message.id}"/>
                              	    </c:url>" title="<fmt:message key="messages.DELETE"/>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" width="25" height="25" border="0"></a>&nbsp;&nbsp;                             
                              </c:if>
                              <c:if test="${can_edit||(!locked&&sessionScope.JRF_USER.name==message.sender)}"> 
                              	<a href="<c:url value="EditMessage.do">
                              	        <c:param name="tid" value="${param.tid}"/>
                              	        <c:param name="fid" value="${param.fid}"/>
                              	        <c:param name="mid" value="${message.id}"/>
                              	    </c:url>" title="<fmt:message key="messages.EDIT"/>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/edit.gif" width="25" height="25" border="0"></a>&nbsp;&nbsp;
                              </c:if>
                             

                              <c:choose>	
                                  <c:when test="${locked}">
                                    <img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/locked.gif" width="25" height="25" border="0">
										<c:choose>
										   <c:when test="${requestScope.JRF_CURR_THREAD.locked==1}">
                              				<fmt:message key="forum.TLOCKED" />
                              			   </c:when>
                              			   <c:when test="${requestScope.JRF_CURR_FORUM.locked==2}">
                              				<fmt:message key="forum.LOCK1T"  />
                              			  </c:when>
                              			</c:choose>  								
                               	  </c:when>
                               	  <c:otherwise>
                                  <a href="<c:url value="Quote.do">
                              	        <c:param name="tid" value="${param.tid}"/>
                              	        <c:param name="fid" value="${param.fid}"/>
                              	        <c:param name="mid" value="${message.id}"/>
                              	    </c:url>" title="<fmt:message key="messages.QUOTE"/>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/quote.gif" width="25" height="25" border="0"></a>&nbsp;&nbsp;
                                  <a href="<c:url value="Reply.do">
                              	        <c:param name="tid" value="${param.tid}"/>
                              	        <c:param name="fid" value="${param.fid}"/>
                              	        <c:param name="mid" value="${message.id}"/>
                              	    </c:url>" title="<fmt:message key="messages.REPLY"/>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/reply.gif" width="25" height="25" border="0"></a>&nbsp;&nbsp;
                              	  </c:otherwise>
                              </c:choose>
							</nobr>
						</td>
					</tr>
					<tr>
						<td colspan="2" valign="top"  style="padding:3px;"><br>
                  <gossip:process value="${message.centents}" />
                  <c:if test="${!empty senderSettings.signature}">
                  	<br><hr align="left" width="50%" size="1" noshade>
                  	<gossip:process value="${senderSettings.signature}" />
                  </c:if>
                  <br><br>
           <gossip:ifconfig key="<%=IConst.CONFIG.ENABLE_FILE_UPLOAD%>">
           <gossip:checkAccess objectId="7" operationId="1">
           	 <c:if test="${!empty message.attachments}">
                  <table cellpadding="3" cellspacing="0" width="400" class="tblr_g">
                  	<tr>
                  		<td align="center"><span class="c_title"><fmt:message key="messages.FILE"/></span>
                  		</td>
                  		<td align="center"  class="l_g"><span class="c_title"><fmt:message key="messages.attach.FILE_SIZE"/>(bytes)</span>
                  		</td>
                  		<td  class="l_g">&nbsp;
                  		</td>
                  	<tr>
                 <c:forEach var="attach" items="${message.attachments}" varStatus="status">
                  	<tr class="strip<c:out value="${current%2}"/>">
                  		<td  class="t_g">
                  			<c:out value="${attach.name}"/>
                  			<br>
                  			<em><c:out value="${attach.description}"/></em>
                  		</td>
                  		<td  class="tl_g" width="10">
							<fmt:formatNumber value="${attach.size}"/>&nbsp;
                  		</td>
                  		<td  class="tl_g" width="40">
                  		   <nobr>
                  		   	 <gossip:isImage var="isImage" contentType="${attach.contentType}"/>
                  		   	 <c:choose>
                  		   	 	<c:when test="${!isImage}">
                  		     	   <a href="<c:url value="DownloadAttachment.do">
                              	        <c:param name="GZIP_NOT_ALLOWED" value="Y"/>
                              	        <c:param name="id" value="${attach.id}"/>
                              	        </c:url>" title="<fmt:message key="messages.attach.DOWNLOAD"/>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/move.gif" width="25" height="25" border="0"></a>&nbsp;&nbsp;
                              	</c:when>
                              	<c:otherwise>
                              		<a href="<c:url value="showAttachedImage.do">
                              	        <c:param name="id" value="${attach.id}"/>
                              	        <c:param name="name" value="${attach.name}"/>
                              	        </c:url>" title="<fmt:message key="messages.attach.SHOW_FULL_SIZE"/>" target="_blank"><img src="<c:url value="ShowAttachThumbnail.do">
                  					  		<c:param name="GZIP_NOT_ALLOWED" value="Y"/>
                                      		<c:param name="id" value="${attach.id}"/>
                                   			</c:url>" width="<gossip:config key="<%=IConst.MISC.THUMBNAIL_WIDTH%>"/>" height="<gossip:config key="<%=IConst.MISC.THUMBNAIL_HEIGHT%>"/>" border="0"></a>&nbsp;&nbsp;
                              	</c:otherwise>
                             </c:choose>
 							 <c:if test="${!empty requestScope.JRF_MOD_FLAG}" var="can_edit">
                              	<a href="<c:url value="DeleteAttachment.do">
                              	        <c:param name="tid" value="${param.tid}"/>
                              	        <c:param name="fid" value="${param.fid}"/>
                              	        <c:param name="mid" value="${message.id}"/>
                              	        <c:param name="id" value="${attach.id}"/>
                              	    </c:url>" title="<fmt:message key="messages.attach.DELETE"/>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" width="25" height="25" border="0"></a>&nbsp;&nbsp;                             
                              </c:if>
                              <c:if test="${can_edit||(!locked&&sessionScope.JRF_USER.name==message.sender)}"> 
                              	<a href="<c:url value="EditAttachment.do">
                              	        <c:param name="tid" value="${param.tid}"/>
                              	        <c:param name="fid" value="${param.fid}"/>
                              	        <c:param name="mid" value="${message.id}"/>
                              	        <c:param name="id" value="${attach.id}"/>
                              	    </c:url>" title="<fmt:message key="messages.attach.EDIT"/>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/edit.gif" width="25" height="25" border="0"></a>&nbsp;&nbsp;
                              </c:if>
                            </nobr>
                  		</td>
                  	<tr>
                 </c:forEach>
                  </table> 
           </c:if>
          </gossip:checkAccess>
          </gossip:ifconfig>
                  <br></td>
					</tr>
				</table>
			</td>
		</tr>
		<%icon_state = "answer";%>
    </c:forEach>
		<tr class="lght">
			<td class="t_g">	&nbsp;
			</td>
			<td 
		<c:choose>
			<c:when test="${requestScope.JRF_RECORDS_DATA.haveSplit}">
			class="bot_tab_nav" >
			</c:when>
			<c:otherwise>
			class="t_g" > &nbsp;
			</c:otherwise>
		</c:choose>
			<%@ include file="../jspf/pageSplit.jspf"%></td>
			<td class="t_g">&nbsp;	
			</td>

		</tr>
	</table>
	<br>
	<br>
<c:if test="${!locked}">
    <c:set var="message_form_name" value="addMessageForm"/>
	<html:form method="post" action="/ProcessMessage">
		<%@ include file="../jspf/messageForm.jspf"%>
	</html:form>
</c:if>	
<c:if test="${!empty requestScope.JRF_RECORDS_DATA&&requestScope.JRF_RECORDS_DATA.recordsCount>0}">
   		<%@ include file="../jspf/timezone.jspf"%>
</c:if>




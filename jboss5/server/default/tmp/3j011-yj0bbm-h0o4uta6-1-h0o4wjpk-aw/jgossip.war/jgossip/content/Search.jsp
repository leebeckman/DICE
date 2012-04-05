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
		<gossip:title>
			<fmt:message key="forum.SEARCH"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

	<table width="98%" cellspacing="0" cellpadding="0">
		<html:form action="/Search" onsubmit="hideErrors();return validateSearchForm(this);">
		<tr>
			<td width="120">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l">
			<fmt:message key="forum.SEARCH"/>
                 &nbsp;&nbsp;
			</td>
			<td>&nbsp;
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td class="tb_o" height="4"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g_tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tb_o" colspan="2"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td height="8"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="b_g" colspan="2"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td  align="right">
				&nbsp;
			</td>
			<td class="l_g">&nbsp;
			</td>
			<td width="70%">&nbsp;
			</td>
			<td class="r_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="center"><fmt:message key="forum.SEARCH_FOR"/>
			</td>
			<td class="tl_g" colspan="2"><br>&nbsp;&nbsp;
					<html:text property="search" size="30" styleClass="ieform"  maxlength="255" />
                    &nbsp;
                    <html:select property="type">
                    	<html:option value="<%=IConst.VALUES.ANY%>" key="forum.SEARCH_ANY"/>
                    	<html:option value="<%=IConst.VALUES.WHOLE%>" key="forum.SEARCH_WHOLE"/> 
                    	<html:option value="<%=IConst.VALUES.ALL%>" key="forum.SEARCH_ALL"/>
                    </html:select><br><br>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="t_g" >	&nbsp;
			</td>
			<td class="tl_g" align="middle">&nbsp;
			</td>
			<td class="t_g" >&nbsp;	
			</td>
			<td class="r_g" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td >	&nbsp;
			</td>
			<td class="bot_tab" nowrap>
				<input class="but_b" type="submit" value="<fmt:message key="global.buttons.SUBMIT"/>">
			</td>
			<td class="t_g" colspan="2">&nbsp;	
			</td>

		</tr>
        
   </html:form>
<html:javascript formName="searchForm" staticJavascript="false"/>
      <c:if test="${!empty requestScope.JRF_RECORDS_DATA}">
      	<tr class="lght">
			<td  colspan="4">	&nbsp;
			</td>
		</tr>
      		<c:forEach var="search_result" items="${requestScope.JRF_RECORDS_DATA.records}" varStatus="status">
                            <tr class="strip<c:out value="${status.count%2}"/>">
                            <td class="ltr_g" colspan="4" style="padding:5px;">
                              <b><a href="<c:url value="ShowMessage.do">
                                        <c:param name="tid" value="${search_result.threadid}"/>
                              	        <c:param name="fid" value="${search_result.forumid}"/>
                              	        <c:param name="mid" value="${search_result.id}"/>
                                     </c:url>">
                                    <c:choose>
                                       <c:when test="${!empty search_result.heading}">
                                         <gossip:codec value="${search_result.heading}"/>
                                       </c:when>
                                       <c:otherwise>
                                         <gossip:process cutToLength="12" value="${search_result.centents}"/>
                                       </c:otherwise>
                                    </c:choose>
                                 </a></b><br>
                             <gossip:process cutToLength="255" value="${search_result.centents}"/><br>
                              <i><fmt:message key="messages.SENT"/>:</i><i><fmt:formatDate value="${search_result.intime}" type="both" dateStyle="short" timeStyle="short"/>
                              	<fmt:message key="mails.BY"/>:
                              	<c:if test="${search_result.senderInfo.status>0}" var="isReg">
                          			<a href="<c:url value="ShowUser.do">
                                      <c:param name="uid" value="${search_result.sender}"/>
                                   </c:url>">
                          	  	</c:if>
                          		<c:out value="${search_result.sender}"/>
                          		<c:if test="${isReg}">
                          			</a>&nbsp;
                          		</c:if></i>
                            </td>
                            </tr>
             </c:forEach>
      		<c:if test="${requestScope.JRF_RECORDS_DATA.recordsCount==0}">
         		<tr  class="drk">
                   <td class="lrt_g_txt_b"align="center" colspan="4"><br>
                      <fmt:message key="errors.ERR17"/><br><br>
                   </td>
            	</tr>
           </c:if>
        <tr class="lght">
			<td class="t_g" colspan="4">	&nbsp;
			</td>
		</tr>
      </c:if>
     
     </table>

<c:if test="${!empty requestScope.JRF_RECORDS_DATA&&requestScope.JRF_RECORDS_DATA.recordsCount>0}">
   		<%@ include file="../jspf/timezone.jspf"%>
</c:if>




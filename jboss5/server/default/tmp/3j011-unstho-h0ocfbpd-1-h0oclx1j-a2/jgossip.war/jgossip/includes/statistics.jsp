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
--%>
<%@ include file="../jspf/jsp_header.jspf"%>
    <br>
	<table width="98%" cellspacing="0" cellpadding="0" border=0>
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" width="70" height="25" nowrap align="center"><span class="caption_l"><fmt:message key="global.STATS"/></span></td>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td class="tb_o" height="4"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g_tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tb_o" colspan="2" ><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tr_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td height="8"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="b_g" width="60%"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td width="6"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="6" height="1" border="0"></td>
			<td class="lr_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td align="center" valign="middle"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/statistic.gif" alt="" width="37" height="25" border="0"></td>
			<td class="lrb_g" colspan="2" style="padding:5px;">
              <c:if test="${!empty requestScope.JRF_ENTRY_LIST}">
                  <span class="txt_caption"><fmt:message key="forum.ONLINE"/>:</span>&nbsp;<span class="txt">
                  <c:if test="${requestScope.JRF_ENTRY_LIST.loggedCount>0}">
                  	<c:forEach var="loggedUser" items="${requestScope.JRF_ENTRY_LIST.loggedUsers}" varStatus="status">
                  		<c:out value="${loggedUser}"/>
                  		<c:if test="${!status.last}">
                  	,&nbsp;
                  		</c:if>
                  	</c:forEach>
                 
                  	<c:if test="${requestScope.JRF_ENTRY_LIST.guestsCount>0}">
                  		<fmt:message key="mails.AND"/>
                  	</c:if>
                  </c:if>
                  <c:if test="${requestScope.JRF_ENTRY_LIST.guestsCount>0}">
                  	<c:out value="${requestScope.JRF_ENTRY_LIST.guestsCount}"/>
                  	  <c:choose>
                        <c:when test="${requestScope.JRF_ENTRY_LIST.guestsCount>1}">
                  		   <fmt:message key="forum.GUESTS"/>
                  	    </c:when>
                  	    <c:otherwise>
                  		    <fmt:message key="forum.GUEST"/>
                  	    </c:otherwise>
                  	  </c:choose>
                  </c:if>
                  <br>
               </c:if>
                  <span class="txt_caption"><fmt:message key="forum.GENERATEDIN"/>:</span>&nbsp;<span class="txt"><gossip:buildtime/> <fmt:message key="global.SECONDS"/></span>
			</td>
			<td><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td width="150" align="center" valign="middle" class="lrb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/logo_btm.gif" alt="Powered by jGossip" width="100" height="47" border="0" title="Powered by jGossip"></td>
		</tr>
	</table>
	<br>
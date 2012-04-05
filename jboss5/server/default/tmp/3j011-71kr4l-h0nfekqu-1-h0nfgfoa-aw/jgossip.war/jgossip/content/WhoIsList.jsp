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

<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="forum.ONLINE"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

  <table width="98%" cellspacing="0" cellpadding="0">
  		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap align="center"><fmt:message key="forum.ONLINE"/></td>
			<td colspan="2">&nbsp;
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
			<td class="icn">
				<span class="c_title">&nbsp;<fmt:message key="user.IP"/>&nbsp;</span>
			</td>
			<td class="l_g">	&nbsp;&nbsp;&nbsp;
				<span class="c_title">&nbsp;<fmt:message key="user.LOGIN"/>&nbsp;</span>
			</td>
			<td width="50%">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td class="lr_g" align="center">
				<span class="c_title">&nbsp;<fmt:message key="user.SESSIONID"/>&nbsp;</span>
			</td>
		</tr>
		<c:forEach var="user" items="${requestScope.JRF_RECORDS_DATA}" varStatus="status">
          <tr class="strip<c:out value="${status.count%2}"/>">
          <td class="lt_g_txt_b" align="center"><c:out value="${user.ip}"/>
          </td>
          <td class="lt_g_txt_b" colspan=2>
          <c:choose>
            <c:when test="${!empty user.login}">
            	<a href="<c:url value="ShowUser.do">
            	            <c:param name="uid" value="${user.login}"/>
            	         </c:url>"><c:out value="${user.login}"/></a>
          	</c:when>
          	<c:otherwise>
          		<fmt:message key="forum.GUEST"/>
          	</c:otherwise>
          </c:choose>
          </td>
          <td class="lrt_g_txt_b">
           <c:out value="${user.sessionId}"/>
          </td>
          </tr> 
		</c:forEach>
		 <tr class="lght">
            <td class="t_g" colspan="5">&nbsp;</td>
        </tr>
      </table><br>





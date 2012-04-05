-<%--
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
			<fmt:message key="forum.FGM"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

  <table width="98%" cellspacing="0" cellpadding="0">
			<tr>
				<td width="70">	&nbsp;&nbsp;
				</td>
				<td class="top_tab" nowrap>	&nbsp;&nbsp;<fmt:message key="forum.FGM"/></span>&nbsp;&nbsp;
				</td>
				<td width="50%" colspan="2">
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
				&nbsp;
			</td>
			<td class="l_g">	&nbsp;&nbsp;&nbsp;
				<span class="c_title">&nbsp;<fmt:message key="forum.GROUP_NAME"/>&nbsp;</span>
			</td>
			<td width="50%">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td class="lr_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
             <c:choose>		
	            <c:when test="${!empty requestScope.JRF_RECORDS_DATA.records}" >
	             <c:forEach items="${requestScope.JRF_RECORDS_DATA.records}" var="group" varStatus="status"> 
                  <tr class="strip<c:out value="${status.count%2}"/>">
                   <td class="lt_g_txt_b" align="center">
                    <img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/on.gif" width="25" height="25">
                  </td>
                  <td class="lt_g_txt_b" colspan="2">
                    <c:out value="${group.name}"/>
                  </td>
                  <td class="lrt_g_txt_b" width="20%">
                    <div align="center"><a href="<c:url value="EditGroup.do">
                                                    <c:param name="gid" value="${group.groupid}" />
                                                </c:url>"><fmt:message key="forum.EDIT"/></a> <b>|</b> <a href="<c:url value="DeleteGroup.do">
                                                                                                                    <c:param name="gid" value="${group.groupid}" />
                                                                                                                </c:url>"><fmt:message key="forum.DELETE"/></a></div>
                  </td>
                  </tr>
                </c:forEach>
				 <tr class="lght">
                        <td class="t_g" colspan="4">&nbsp;</td>
                    </tr>
              </c:when>
              <c:otherwise>
                <tr class="drk">
                  <td colspan="4" class="lrt_g_txt_b" style="border-bottom-width: 1px;">
                    <fmt:message key="forum.NOGROUPS"/>
                  </td>
                </tr>
              </c:otherwise>
            </c:choose>
            </table>
<br>






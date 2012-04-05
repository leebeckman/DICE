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
			<fmt:message key="user.UM"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

  <table width="98%" cellspacing="0" cellpadding="0">
  		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap align="center"><fmt:message key="user.UM"/></td>
			<td colspan="5">&nbsp;&nbsp;&nbsp;<a class="control" href="<c:url value="ShowRegistration.do">
			                       											<c:param name="dispatch" value="direct"/>
			                       								 	   </c:url>" ><fmt:message key="user.ADD_NEW_USER"/></a>
			               &nbsp;&nbsp;&nbsp;<a class="control" href="<c:url value="BanList.do"/>" ><fmt:message key="user.BAN_LIST"/></a>
			                       								 	   
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
				<span class="c_title">&nbsp;</span>
			</td>
			<td class="l_g">&nbsp;&nbsp;&nbsp;
				<span class="c_title">&nbsp;<fmt:message key="user.LOGIN"/>&nbsp;</span>
			</td>
			<td width="50%">&nbsp;
			</td>
			<td class="l_g" align="center">
				<span class="c_title">&nbsp;<fmt:message key="user.STATUS"/>&nbsp;</span>
			</td>
			<td class="l_g" align="center">
				&nbsp;
			</td>
			<td class="lr_g" align="center">
				<span class="c_title">&nbsp;</span>
			</td>
		</tr>
				<c:forEach var="user" items="${requestScope.JRF_RECORDS_DATA.records}" varStatus="status">
                  <tr class="strip<c:out value="${status.count%2}"/>">
                  <td class="lt_g_txt_b" align="center" width="40">
                    <img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/on.gif" width="25" height="25">
                  </td>
                  <td class="lt_g_txt_b" colspan=2>
                    <a href="<c:url value="ShowUser.do">
                                 <c:param name="uid" value="${user.name}"/>
                             </c:url>"><c:out value="${user.name}"/></a>
                  </td>
                  <td class="lt_g_txt_b" align="center">
                    <nobr><gossip:userStatus status="${user.status}"/>(<c:out value="${user.status}"/>/10)</nobr></div>
                  </td>
                  <html:form action="/PromoteUser">
                  <td class="lt_g_txt_b"  nowrap align="center">
                  <c:choose>
                     <c:when test="${user.status!=10}">
                     <table>
                           <tr>
                               <td>
                                   <html:hidden name="user" property="id"/>
				                   <html:select name="user" property="status" value="-1">
				                        <html:option value="-1" key="user.SELECT_NEW_STAT"/>
				                        <c:if test="${user.status!=1}">
                    					<html:option value="1" key="user.STAT1"/>
                    					</c:if>
                    					 <c:if test="${user.status!=8}">
                    					<html:option value="8" key="user.STAT8"/>
                    					</c:if>
                    					 <c:if test="${user.status!=9}">
                    					<html:option value="9" key="user.STAT9"/> 
                    					</c:if>
                					</html:select>
                               </td>
                		       <td>
                                   <input  type="image" value="<fmt:message key="global.buttons.SUBMIT"/>"  src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/move.gif" width="25" height="25" alt="<fmt:message key="global.buttons.SUBMIT"/>" title="<fmt:message key="global.buttons.SUBMIT"/>" border=0 align="baseline">
                               </td>
				          </tr> 
				    	</table>  
                    </c:when>
                    <c:otherwise>
                    	&nbsp;
                    </c:otherwise>
                   </c:choose>
                  </td>
                  </html:form>
                   <td class="lrt_g_txt_b" >
                     <c:if test="${user.status!=10}">
                     	<nobr>
                     		<a href="<c:url value="DeleteUser.do">
                                    <c:param name="uid" value="${user.id}"/>
                                 </c:url>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" width="25" height="25" alt="<fmt:message key="forum.DELETE"/>" title="<fmt:message key="forum.DELETE"/>" align="baseline" border=0></a>
                            &nbsp;
                            <gossip:isBanned var="banned" login="${user.name}"/>
                            <c:choose>
                            	<c:when test="${banned}">
                                   <a href="<c:url value="DeleteBan.do">
                            		  <c:param name="type" value="1"/>
                                      <c:param name="mask" value="${user.name}"/>
                                      </c:url>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/locked.gif" width="25" height="25" alt="<fmt:message key="user.REMOVE_BAN"/>" title="<fmt:message key="user.REMOVE_BAN"/>" align="baseline" border=0></a>
                                </c:when>
                                <c:otherwise>
                                   <a href="<c:url value="AddBan.do">
                            		  <c:param name="type" value="1"/>
                                      <c:param name="mask" value="${user.name}"/>
                                      </c:url>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/unlocked.gif" width="25" height="25" alt="<fmt:message key="user.ADD_BAN"/>" title="<fmt:message key="user.ADD_BAN"/>" align="baseline" border=0></a>
                                </c:otherwise>
                            </c:choose>
                        </nobr>         
                	 </c:if> 
					</td>
                  </tr>
				</c:forEach>
				 <tr class="lght">
                    <td class="t_g" colspan="6">&nbsp;
						<%@ include file="../jspf/pageSplit.jspf"%>
                    </td>
                </tr>

      </table><br>





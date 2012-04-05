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
			<fmt:message key="user.RANKS"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>
 <html:form action="/AddRank" onsubmit="hideErrors();return validateAddRankForm(this);">
        <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="120">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l">
                  <fmt:message key="user.NEW_RANK"/>
                  </span>
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
			<td class="lt_g_txt_b" align="center"><fmt:message key="user.RANK_NAME"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;<html:text property="name" size="30"  maxlength="64" />
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		
		<tr class="lght">
			<td class="lt_g_txt_b" align="center"><fmt:message key="user.RANK_COUNT"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;<html:text property="count" size="6" maxlength="5" />
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
	</table>

   </html:form>
   <html:javascript formName="addRankForm" staticJavascript="false"/>
  <table width="98%" cellspacing="0" cellpadding="0">
			<tr>
				<td width="70">	&nbsp;&nbsp;
				</td>
				<td class="top_tab" nowrap>	&nbsp;&nbsp;<fmt:message key="user.RANKS"/></span>&nbsp;&nbsp;
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
			
			<td class="l_g" nowrap align="center">
				<span class="c_title">&nbsp;<fmt:message key="user.RANK_NAME"/>&nbsp;/&nbsp;<fmt:message key="user.RANK_COUNT"/></span>
			</td>
			
			<td width="50%">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td class="lr_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
             <c:choose>		
	            <c:when test="${!empty requestScope.JRF_RECORDS_DATA}" >
	             <c:forEach items="${requestScope.JRF_RECORDS_DATA}" var="rank" varStatus="status"> 
                  <tr class="strip<c:out value="${status.count%2}"/>">
                   <td class="lt_g_txt_b" align="center">
                    &nbsp;
                  </td>
                  <html:form action="/UpdateRank">
                  <html:hidden name="rank" property="id"/>
                  <td class="lt_g_txt_b" colspan="2" nowrap>
                  	<table>
                  		<tr>
                  			<td>
                                 <html:text name="rank" property="name" size="30"  maxlength="64" /> / <html:text name="rank" property="count" size="6" maxlength="5" />
                            </td>
                            <td>
                                 &nbsp;<input  type="image" value="<fmt:message key="global.buttons.SUBMIT"/>"  src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/move.gif" width="25" height="25" alt="<fmt:message key="global.buttons.SUBMIT"/>" title="<fmt:message key="global.buttons.SUBMIT"/>" border=0 align="baseline">                              
                            </td>
                       </tr>
                    </table>
                  </td>
                  </html:form>
                  <td class="lrt_g_txt_b" width="20%">
                    <a href="<c:url value="DeleteRank.do">
                    <c:param name="id" value="${rank.id}" />
                     </c:url>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" width="25" height="25" alt="<fmt:message key="forum.DELETE"/>" title="<fmt:message key="forum.DELETE"/>" align="baseline" border=0></a>
                  </td>
                  </tr>
                </c:forEach>
				 <tr class="lght">
                        <td class="t_g" colspan="4">&nbsp;</td>
                    </tr>
              </c:when>
              <c:otherwise>
                <tr class="drk">
                  <td class="lght">
				&nbsp;
			       </td>
                  <td colspan="3" class="lrt_g_txt_b" style="border-bottom-width: 1px;">
                    <fmt:message key="global.NO_RECORDS"/>
                  </td>
                </tr>
              </c:otherwise>
            </c:choose>
            </table>
<br>






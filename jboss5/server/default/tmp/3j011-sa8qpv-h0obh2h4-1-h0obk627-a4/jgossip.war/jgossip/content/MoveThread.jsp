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

<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="threads.M_THREAD"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

   <html:form action="/MoveThread" >
   <html:hidden property="tid" value='<%=request.getParameter("tid")%>'/>
        
        <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="120">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="threads.M_THREAD"/></span>&nbsp;&nbsp;
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
			<td class="lt_g_txt_b" align="center"><fmt:message key="threads.M_THREAD2"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				  <select name="fid" style="width: 220px;">
                    	<gossip:jumpoptions addUrl="false"/>
                  </select>
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
				&nbsp;<input class="but_b" type="button" value="<fmt:message key="global.buttons.CANCEL"/>" 
				            onclick="top.location.href='<c:url value="ShowForum.do" >
				                                            <c:param name="fid" value="${param.fid}"/>
				                                        </c:url>'">
			</td>
			<td class="t_g" colspan="2">&nbsp;	
			</td>

		</tr>
	</table>
      </html:form>





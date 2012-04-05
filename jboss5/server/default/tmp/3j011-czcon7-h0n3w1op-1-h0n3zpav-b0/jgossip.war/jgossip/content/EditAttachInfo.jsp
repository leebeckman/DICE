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
--%><%@ include file="../jspf/jsp_header.jspf"%>

<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:link>
			ShowForum.do?fid=<%=request.getParameter("fid")%>
		</gossip:link>
		<gossip:title>
			<c:out value="${sessionScope.JRF_CURR_FORUM.title}"/>
		</gossip:title>
	</gossip:navElement>
	<gossip:navElement>
		<gossip:link>
			ShowThread.do?fid=<%=request.getParameter("fid")%>&tid=<%=request.getParameter("tid")%>&block=<%=request.getParameter("block")!=null?request.getParameter("block"):"0"%>
		</gossip:link>
		<gossip:title>
			<gossip:codec><c:out value="${sessionScope.JRF_CURR_THREAD.subject}" escapeXml="false"/></gossip:codec>
		</gossip:title>
	</gossip:navElement>
	<gossip:navElement>
		<gossip:title>
			<fmt:message key='messages.attach.EDIT'/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>



<html:form action="/UpdateAttachInfo" onsubmit="hideErrors();return validateAttachmentInfoForm(this);">
        <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="120">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="messages.attach.EDIT"/></span>&nbsp;&nbsp;
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
			<td class="lt_g_txt_b" align="center"><fmt:message key="messages.attach.FILE_NAME"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;<html:text property="name"  size="40" maxlength="80"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="center"><fmt:message key="messages.FILE_DESC"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;<html:textarea  property='description' cols="40" rows="3" />
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
			<html:hidden property="tid" value='<%=request.getParameter("tid")%>'/>
            <html:hidden property="fid" value='<%=request.getParameter("fid")%>'/>
            <html:hidden property="block" value='<%=request.getParameter("block")%>'/>
            <html:hidden property="mid" value='<%=request.getParameter("mid")%>'/>
            <html:hidden property="id" value='<%=request.getParameter("id")%>'/>
			<input class="but_b" type="submit" value="<fmt:message key="global.buttons.SUBMIT"/>">
			          <c:url var="jrf_href" value="ShowThread.do">
		                    <c:param name="fid" value="${param.fid}"/>
		                    <c:param name="tid" value="${param.tid}"/>
		                    <c:if test="${!empty param.block}">
		                        <c:param name="block" value="${param.block}"/>
		                    </c:if>
		                </c:url>
		  &nbsp;<input class="but_b" type="button" value="<fmt:message key="global.buttons.CANCEL"/>" onclick="top.location.href='<c:url value="${pageScope.jrf_href}" />'">
			</td>
			<td class="t_g" colspan="2">&nbsp;	
			</td>

		</tr>
	</table>
      </html:form>
      <html:javascript formName="attachmentInfoForm" staticJavascript="false"/>




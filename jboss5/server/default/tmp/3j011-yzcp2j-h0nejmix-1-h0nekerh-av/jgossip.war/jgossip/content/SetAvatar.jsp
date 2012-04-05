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
		<gossip:title>
			<fmt:message key="user.SET_AVATAR"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>


&nbsp;*<fmt:message key="user.avatar.COMMENT"/>
<html:form action="/SaveAvatar"  enctype="multipart/form-data">
        <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="200" height="1" border="0">
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="user.avatar.UPLOAD"/></span>&nbsp;&nbsp;
			</td>
			<td width="70%">&nbsp;
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
			<td >&nbsp;
			</td>
			<td class="r_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="user.avatar.UPLOAD"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;<html:file styleClass="file" size="30" property='file'/>
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
				<%@ include file="../jspf/defaultSubmit.jspf"%>
			</td>
			<td class="t_g" colspan="2">&nbsp;	
			</td>

		</tr>
	</table>
      </html:form>
      <html:form action="/SaveRemoteAvatar"   onsubmit="hideErrors();return validateLoadAvatarForm(this);">
        <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="200" height="1" border="0">
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="user.avatar.ADD_REMOTE"/></span>&nbsp;&nbsp;
			</td>
			<td width="70%">&nbsp;
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
			<td >&nbsp;
			</td>
			<td class="r_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="user.avatar.URL"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;<html:text  size="80" property='url'/>
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
				<%@ include file="../jspf/defaultSubmit.jspf"%>
			</td>
			<td class="t_g" colspan="2">&nbsp;	
			</td>

		</tr>
	</table>
      </html:form>
 <html:javascript formName="loadAvatarForm" staticJavascript="false"/>




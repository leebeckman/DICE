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
			<fmt:message key="user.ADD_USER"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

<c:choose>
    <c:when test="${sessionScope.JRF_USER.status==0}">
        <c:set var="reg_action" value="/Registration"/>
	</c:when>
	<c:otherwise>
	    <c:set var="reg_action" value="/AddUser"/>
	</c:otherwise>	
</c:choose>		
<html:form action='<%=(String)pageContext.getAttribute("reg_action")%>' onsubmit="hideErrors();return validateRegistrationForm(this);">
	<input type="hidden" value="direct" name="dispatch">
	<table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="user.ADD_USER"/></span>&nbsp;&nbsp;
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
			<td  align="right">
				<span class="caption_l"><fmt:message key="user.FTMBFO"/>:</span>
			</td>
			<td class="l_g">&nbsp;
			</td>
			<td width="70%">&nbsp;<fmt:message key="user.PASSWORD_RULES"/>
			</td>
			<td class="r_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>

		<tr class="drk">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.CHOOSE_LOGIN"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2"><html:text property="login" size="33" maxlength="32"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
<c:choose>
    <c:when test="${sessionScope.JRF_USER.status==0}">
		<tr class="lght">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.PASS1"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">	<html:password property="password" size="18" maxlength="16"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.PASS2"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2"><html:password property="password2" size="18" maxlength="16"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
	</c:when>
	<c:otherwise>
	    <html:hidden property="password2" value="password"/>
	    <html:hidden property="password" value="password"/>
	</c:otherwise>	
</c:choose>	
       <tr class="lght">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.CONFIRM_CODE"/>:
			<br><fmt:message key="user.CONFIRM_CODE_DESC"/>
			</td>
			<td class="lt_g_txt_b" colspan="2">	<img src="<c:url value="ConfirmCode.do">
                  					  <c:param name="GZIP_NOT_ALLOWED" value="Y"/>
                                   </c:url>"/><br><br>
			<html:text property="confirmCode" size="12" maxlength="10"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>	
              <%@ include file="../jspf/profileForm.jspf" %>
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
			<td class="bot_tab"><%@ include file="../jspf/defaultSubmit.jspf"%>
			</td>
			<td class="t_g" colspan="3">&nbsp;	
			</td>
		</tr>
	</table>

      </html:form>
<html:javascript formName="registrationForm" staticJavascript="false"/>





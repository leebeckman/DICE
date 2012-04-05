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

<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="global.CONSTANTS"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

   <html:form action="/EditConstants" onsubmit="hideErrors();return validateEditConstantsForm(this);">
   <%-- general configuration parameters--%>
         <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="180">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="global.CONSTANTS"/></span>&nbsp;&nbsp;
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
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.MAILHOST"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
			<html:text property="mailhost" size="50"   maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.MAILHOST)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.SMTP_SERVER_PORT"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
			<html:text property="smtpServerPort" size="5"   maxlength="4" value="<%=Configurator.getInstance().get(IConst.CONFIG.SMTP_SERVER_PORT)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.MAILUSER"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="mailuser" size="50"  maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.MAILUSER)%>"/>
				&nbsp;<html:link action="showSetMailPassword"><fmt:message key="constants.MAILPASSWORD"/></html:link><br>
				<fmt:message key="constants.ANONYMOUS"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ADMINMAIL"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="adminmail" size="50"  maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.ADMINMAIL)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.SITE_NAME"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="sitename" size="50"  maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.SITE_NAME)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.DOMAIN_NAME"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="domainname" size="50"   maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.DOMAIN_NAME)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.DOMAIN_URL"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="domainurl" size="50"   maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.DOMAIN_URL)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.SESSION_LENGTH"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="sessionlength" size="11"   maxlength="10" value="<%=Configurator.getInstance().get(IConst.CONFIG.SESSION_LENGTH)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.GZIP_COMPRESS"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="gzipCompress" value="<%=Configurator.getInstance().get(IConst.CONFIG.GZIP_COMPRESS)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="right"><fmt:message key="constants.MOTTO"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="motto" size="100"  maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.MOTTO)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.DEFAULT_LOCALE"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="defaultLocale" value="<%=Configurator.getInstance().get(IConst.CONFIG.DEFAULT_LOCALE)%>">
                    <c:forEach items="${applicationScope.JRF_AVAIBLE_TRANSLATIONS}" var="lang">
                    	<fmt:setLocale value="${lang}"/> 
                    	<fmt:bundle basename="org.jresearch.gossip.resources.lang.lang">
                    		<html:option value='<%=(String)pageContext.getAttribute("lang")%>' ><fmt:message key="locale.display.name"/></html:option>
                    	</fmt:bundle>
                    </c:forEach> 
                </html:select>
                <fmt:setLocale value="${sessionScope[localeKey]}"/> 
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
	<br>
	<%-- sign-on configuration parameters--%>
	
	<table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="180">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="global.SIGNON_CONSTANTS"/></span>&nbsp;&nbsp;
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
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.INVADER1"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="invader" value="<%=Configurator.getInstance().get(IConst.CONFIG.INVADER1)%>">
                    <html:option value="1" key="user.STAT1"/> 
                    <html:option value="7" key="user.STAT7"/> 
                    <html:option value="8" key="user.STAT8"/>
                    <html:option value="9" key="user.STAT9"/>
                    <html:option value="10" key="user.STAT10"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ENABLE_AUTO_LOGIN"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="enableAutoLogin" value="<%=Configurator.getInstance().get(IConst.CONFIG.ENABLE_AUTO_LOGIN)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ENABLE_FORUM_SIGN_ON"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="enableForumSignOn" value="<%=Configurator.getInstance().get(IConst.CONFIG.ENABLE_FORUM_SIGN_ON)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ENABLE_FORUM_REGISTRATION"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="enableForumRegistration" value="<%=Configurator.getInstance().get(IConst.CONFIG.ENABLE_FORUM_REGISTRATION)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ENABLE_EMAIL_CONFIRMATION"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="enableEmailConfiramtion" value="<%=Configurator.getInstance().get(IConst.CONFIG.ENABLE_EMAIL_CONFIRMATION)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.PERIOD_FOR_CONFIRMATION"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="periodForConfirmation" size="3"   maxlength="2" value="<%=Configurator.getInstance().get(IConst.CONFIG.PERIOD_FOR_CONFIRMATION)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ENABLE_EXT_SIGN_ON"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="enableExtSignOn" value="<%=Configurator.getInstance().get(IConst.CONFIG.ENABLE_EXT_SIGN_ON)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.EXT_LOGON_ACTION_URL"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="extLogOnActionUrl" size="50"  maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.EXT_LOGON_ACTION_URL)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.EXT_LOGOUT_ACTION_URL"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="extLogOutActionUrl" size="50"  maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.EXT_LOGOUT_ACTION_URL)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.EXT_REGISTRATION_ACTION_URL"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="extRegistrationActionUrl" size="50"   maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.EXT_REGISTRATION_ACTION_URL)%>"/>
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
	<br>
	<%-- File Uploading configuration parameters--%>
	
	<table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="180">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="global.ATTACHMENT_CONSTANTS"/></span>&nbsp;&nbsp;
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
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ENABLE_AVATAR"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="enableAvatar" value="<%=Configurator.getInstance().get(IConst.CONFIG.ENABLE_AVATAR)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ENABLE_FILE_UPLOAD"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:select property="enableFileUpload" value="<%=Configurator.getInstance().get(IConst.CONFIG.ENABLE_FILE_UPLOAD)%>">
                    <html:option value="<%=IConst.VALUES.TRUE%>" key="global.YES"/>
                    <html:option value="<%=IConst.VALUES.FALSE%>" key="global.NO"/> 
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.MAX_ATTACHMENT_COUNT"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="maxAttachCount" size="3"   maxlength="2" value="<%=Configurator.getInstance().get(IConst.CONFIG.MAX_ATTACHMENT_COUNT)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
       <tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.ATTACH_STORE_PATH"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="attachStorePath" size="64"   maxlength="255" value="<%=Configurator.getInstance().get(IConst.CONFIG.ATTACH_STORE_PATH)%>"/>
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
	<br>
	<%-- RSS configuration parameters--%>
	
	<table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="180">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="global.RSS_CONSTANTS"/></span>&nbsp;&nbsp;
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
		
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.RSS_MAX_ITEM_COUNT"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="rssMaxItemCount" size="3"  maxlength="2" value="<%=Configurator.getInstance().get(IConst.CONFIG.RSS_MAX_ITEM_COUNT)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.RSS_PERIOD"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="rssPeriod" size="3"   maxlength="2" value="<%=Configurator.getInstance().get(IConst.CONFIG.RSS_PERIOD)%>"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="left"><fmt:message key="constants.RSS_TTL"/>
			</td>
			<td class="tl_g" colspan="2">&nbsp;&nbsp;
				<html:text property="rssTtl" size="5"  maxlength="4" value="<%=Configurator.getInstance().get(IConst.CONFIG.RSS_TTL)%>"/>
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
      <html:javascript formName="editConstantsForm" staticJavascript="false"/>



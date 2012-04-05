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
			<fmt:message key="user.SHOW_USER"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>     

<%@ include file="../jspf/topbar.jspf"%>


	<table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="user.SHOW_USER"/></span>&nbsp;&nbsp;
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
				<span class="caption_l"><fmt:message key="user.GENERAL"/>:</span>
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
			<td class="lt_g_txt_b" align="right"><fmt:message key="forum.U_NAME"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2"><c:out value="${requestScope.JRF_USER_TO_SHOW.name}"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
                
                
		<c:if test="${sessionScope.JRF_USER.status>8||requestScope.JRF_USER_TO_SHOW.settings.show_user_mail}">
			<tr class="lght">
				<td class="lt_g_txt_b" align="right"><fmt:message key="messages.E-MAIL"/>:
				</td>
				<td class="lt_g_txt_b" colspan="2"><a href="mailto:<c:out value="${requestScope.JRF_USER_TO_SHOW.info.email}"/>"><c:out value="${requestScope.JRF_USER_TO_SHOW.info.email}"/></a>
				</td>
				<td class="lr_g_w" >&nbsp;	
				</td>
			</tr>
		</c:if>

		<tr class="lght">
			<td class="t_g" align="right">
				<span class="caption_l"><fmt:message key="user.SPECIFIC"/>:&nbsp;</span>
			</td>
			<td class="lt_g_txt_b" colspan="2">	&nbsp;
			</td>
			<td class="r_g" >&nbsp;	
			</td>
		</tr>	
		<tr class="drk">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.U_URL"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">
				<a href="<c:out value="${requestScope.JRF_USER_TO_SHOW.info.homepage}"/>"><c:out value="${requestScope.JRF_USER_TO_SHOW.info.homepage}"/></a>&nbsp;
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.U_ICQ"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">
				<c:out value="${requestScope.JRF_USER_TO_SHOW.info.icq}"/>&nbsp;
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>                
 		<tr class="drk">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.DOB"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">
				<c:out value="${requestScope.JRF_USER_TO_SHOW.info.birthday}"/>&nbsp;
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.OCCUPATION"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">
				<gossip:codec value="${requestScope.JRF_USER_TO_SHOW.info.occupation}"/>&nbsp;
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>                 
 		<tr class="drk">
			<td class="lt_g_txt_b" align="right"><fmt:message key="user.PLACE"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">
				<gossip:codec value="${requestScope.JRF_USER_TO_SHOW.info.city}"/>&nbsp;
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
                

		<c:if test="${sessionScope.JRF_USER.status>8&&requestScope.JRF_USER_TO_SHOW.status<8}">
		<tr class="lght">
			<td class="t_g" align="right">
				<span class="caption_l"><fmt:message key="forum.MODERATES"/>:</span>
			</td>
			<td class="lt_g_txt_b" colspan="2">	&nbsp;
			</td>
			<td class="r_g" >&nbsp;	
			</td>
		</tr>	
		<tr class="drk">
			<td class="lt_g_txt_b" align="right"><fmt:message key="forum.MOD1"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">
			<c:choose>
				<c:when test="${!empty requestScope.JRF_USER_MOD_FORUMS}">	
					<c:forEach var="modForum" items="${requestScope.JRF_USER_MOD_FORUMS}">
						<c:out value="${modForum.title}"/>[ <a href="<c:url value="DropMod.do">
						                                                <c:param name="name" value="${requestScope.JRF_USER_TO_SHOW.name}"/>
						                                                <c:param name="fid" value="${modForum.forumid}"/>
						                                              </c:url>">X</a> ]<br>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<fmt:message key="global.NONE"/>
				</c:otherwise>	
			</c:choose>	
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="right"><fmt:message key="forum.MOD2"/>:
			</td>
			<td class="lt_g_txt_b" colspan="2">
				  <script language="JavaScript">
                   <!--
                   function checkSelected(f){
                   		return f.fid.options[f.fid.selectedIndex].value!="0";
                   }
                   //-->
                   </script>
                   <html:form action="/AddMod" onsubmit="return checkSelected(this)">
                   	  <html:hidden property="name" name="<%=IConst.REQUEST.USER_TO_SHOW%>"/>
                      <html:select property="fid" style="width: 220px;">
              			<html:optionsCollection name="<%=IConst.REQUEST.FORUMS_FOR_MOD%>" value="forumid" label="title"/>
              		  </html:select>&nbsp;<input class="but_b" type="submit" name="Submit" value="<fmt:message key="global.buttons.SUBMIT"/>">
                   </html:form>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr> 
		</c:if>
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
			<td class="bot_tab"><input class="but_b" type="button" name="back" value="<fmt:message key="global.buttons.BACK"/>" onClick="self.history.back();">
			</td>
			<td class="t_g" colspan="3">&nbsp;	
			</td>
		</tr>
    </table>
<br>
  





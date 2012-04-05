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
<script language="JavaScript" 
                 src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>js/picker.js"></script>
<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="skins.UPDATE"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

   <html:form action="UpdateStyles">
   <html:hidden property="skinid" value='<%=""+IConst.CONFIG.DEFAULT_SKIN_ID%>'/>
         <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="200">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l"><fmt:message key="skins.UPDATE"/></span>&nbsp;&nbsp;
			</td>
			<td width="50%">&nbsp;
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
			<td class="l_g" colspan="2">&nbsp;
			<fmt:message key="skins.COLOR_RULE"/>
			</td>
			<td class="r_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<fmt:setBundle basename="org.jresearch.gossip.resources.skins.skin_default" var="skin_desc"/>
		 <c:forEach var="element" items="${sessionScope.JRF_STYLE_SETTINGS}">
		<tr class="drk">
			<td class="lt_g_txt_b" align="left"><fmt:message key="${element.key}" bundle="${skin_desc}"/>
			</td>
			<td class="tl_g" colspan="2" nowrap>&nbsp;&nbsp;
			<c:set var="e_name">value(<c:out value="${element.key}"/>)</c:set>
			<c:choose>
			   <c:when test="${empty jrf_err_mess}">
			   	  <c:set var="e_val" value="${element.value}"/>
				  <html:text property='<%=(String)pageContext.getAttribute("e_name")%>' value='<%=(String)pageContext.getAttribute("e_val")%>' onkeypress="ch_sample(this);" onkeyup="ch_sample(this);"/>
			   </c:when>
			   <c:otherwise>
			      <html:text property='<%=(String)pageContext.getAttribute("e_name")%>' onkeypress="ch_sample(this);" onkeyup="ch_sample(this);"/>
			   </c:otherwise>
			</c:choose>
			<span id="jrf_cp_<c:out value="${e_name}"/>" style="background-color : #<c:out value="${element.value}"/>; border :  1px solid #778899;cursor:pointer;" onClick="cp_popup('stylesForm','<c:out value="${e_name}"/>');">&nbsp;&nbsp;</span>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		</c:forEach>
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




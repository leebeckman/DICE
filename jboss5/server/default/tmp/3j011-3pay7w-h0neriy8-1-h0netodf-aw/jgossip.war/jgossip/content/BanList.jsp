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
--%> <%@ include file="../jspf/jsp_header.jspf"%>
<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="user.BAN_LIST"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>


 <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<fmt:message key="user.BAN_LIST"/>&nbsp;&nbsp;
			</td>
			<td width="50%" colspan="3">
			</td>
		</tr>
		<tr>
			<td class="tb_o" height="2"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g_tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tb_o" colspan="3"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td height="8"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="b_g" colspan="3"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td class="icn">
				&nbsp;
			</td>
			<td class="l_g">	&nbsp;&nbsp;&nbsp;
			</td>
			<td width="50%">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td >	&nbsp;
			</td>
			<td class="r_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
<html:form action="/AddBan" onsubmit="hideErrors();return validateProcessBanForm(this);">
		<tr class="drk">
			<td class="lt_g_txt_b" align="center"><fmt:message key="user.ADD_BAN"/>
			</td>
			<td class="tl_g" colspan="3"><br>&nbsp;&nbsp;
			        <html:select property="type">
                    	<html:option value="1" key="user.ban.1"/>
                    	<html:option value="2" key="user.ban.2"/> 
                    	<html:option value="3" key="user.ban.3"/>
                    </html:select>
                    &nbsp;
					<html:text property="mask" size="40"   maxlength="255" />
                    <br><br>
                    
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
        <tr>
			<tr class="lght">
			<td class="t_g" >	&nbsp;
			</td>
			<td class="tl_g" align="middle">&nbsp;
			</td>
			<td class="t_g" colspan="2">&nbsp;	
			</td>
			<td class="r_g" >&nbsp;	
			</td>
		</tr>
		</tr>
		<tr class="lght">
			<td >	&nbsp;
			</td>
			<td class="bot_tab" nowrap>
				<input class="but_b" type="submit" value="<fmt:message key="global.buttons.SUBMIT"/>">
			</td>
			<td class="t_g" colspan="3">&nbsp;	
			</td>

		</tr>
		</html:form>
<html:javascript formName="processBanForm" staticJavascript="false"/>
   <c:if test="${!empty requestScope.JRF_RECORDS_DATA}">
      	<tr class="lght">
			<td  colspan="5">	&nbsp;
			</td>
		</tr>
	 <c:forEach items="${requestScope.JRF_RECORDS_DATA}" var="sublist"> 
		<tr class="lght">
			<td class="t_g" colspan="5">
			<c:set var="messKey">user.ban.<c:out value="${sublist.key}"/></c:set>
				<br><span class="caption">&nbsp;<fmt:message key="${messKey}"/></span>
			</td>
		
   		<c:choose>		
	      <c:when test="${!empty sublist.value}" >
      		<c:forEach items="${sublist.value}" var="mask" varStatus="status">
           <tr class="strip<c:out value="${status.count%2}"/>">
            	<td class="lt_g_txt_b" align="center">
            		<a href="<c:url value="DeleteBan.do">
                               <c:param name="type" value="${sublist.key}"/>
                               <c:param name="mask" value="${mask}"/>
                              </c:url>"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" width="25" height="25" alt="<fmt:message key="user.REMOVE_BAN"/>" title="<fmt:message key="user.REMOVE_BAN"/>" align="baseline" border=0></a>
                </td>
                <td class="lrt_g_txt_b" colspan="4">
                        <c:out value="${mask}"/>  
				</td>
          </tr>
          </c:forEach>
      </c:when>
	  <c:otherwise>
           <tr class="drk">
               <td colspan="5" class="lrt_g_txt_b" style="border-bottom-width: 1px;">
                  <fmt:message key="global.NO_RECORDS"/>
              </td>
            </tr>
        </c:otherwise>
      </c:choose>
    </c:forEach>
       <tr class="lght">
			<td class="t_g" colspan="5">	&nbsp;
			</td>
		</tr>
</c:if>
            </table>
<br>






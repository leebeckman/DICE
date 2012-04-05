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
<fmt:setTimeZone value="${sessionScope.JRF_TIME_ZONE}" scope="session"/>


<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="log.BROWSE_LOG"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>
<%@ include file="../jspf/timezone.jspf"%>
	<table width="98%" cellspacing="0" cellpadding="0">
		<html:form action="/SearchLog" onsubmit="hideErrors();return validateSearchLogForm(this);">
		<tr>
			<td width="120">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<span class="caption_l">
			<fmt:message key="forum.SEARCH"/>
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
			<td class="lt_g_txt_b" align="center"><fmt:message key="log.search.DATE"/>
			</td>
			<td class="tl_g" colspan="2" nowrap style="padding: 3px;">
				<table>
					<tr>
						<td>
			      			<strong><fmt:message key="log.search.DATE_FROM"/></strong>
			       		</td>
			       		<td>
			               <html:select property="from_d" >
	                           <gossip:n-options startValue="1" endValue="31"/>
	                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
	                      </html:select>
	                      <html:select property="from_M" >
	                           <html:option value="0" key="month.1"/>
	                           <html:option value="1" key="month.2"/>
	                           <html:option value="2" key="month.3"/>
	                           <html:option value="3" key="month.4"/>
	                           <html:option value="4" key="month.5"/>
	                           <html:option value="5" key="month.6"/>
	                           <html:option value="6" key="month.7"/>
	                           <html:option value="7" key="month.8"/>
	                           <html:option value="8" key="month.9"/>
	                           <html:option value="9" key="month.10"/>
	                           <html:option value="10" key="month.11"/>
	                           <html:option value="11" key="month.12"/>
	                        </html:select>
	                        <html:select property="from_y">
	                           <gossip:yearsoptions/>
	                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
	                        </html:select>
	                          &nbsp;
	                        <html:select property="from_H" >
	                           <gossip:n-options startValue="0" endValue="23"/>
	                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
	                        </html:select>
	                        <strong>:</strong>
	                        <html:select property="from_m" >
	                           <gossip:n-options startValue="0" endValue="59"/>
	                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
	                        </html:select>
                       </td>
                     </tr>
                     <tr>
                     	<td>
               				<strong><fmt:message key="log.search.DATE_TO"/></strong>
                        </td>
                        <td>
		                <html:select property="to_d" >
                           <gossip:n-options startValue="1" endValue="31"/>
                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
                        </html:select>
                        <html:select property="to_M" >
                           <html:option value="0" key="month.1"/>
                           <html:option value="1" key="month.2"/>
                           <html:option value="2" key="month.3"/>
                           <html:option value="3" key="month.4"/>
                           <html:option value="4" key="month.5"/>
                           <html:option value="5" key="month.6"/>
                           <html:option value="6" key="month.7"/>
                           <html:option value="7" key="month.8"/>
                           <html:option value="8" key="month.9"/>
                           <html:option value="9" key="month.10"/>
                           <html:option value="10" key="month.11"/>
                           <html:option value="11" key="month.12"/>
                        </html:select>
                        <html:select property="to_y">
                           <gossip:yearsoptions/>
                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
                        </html:select>
                          &nbsp;
                        <html:select property="to_H" >
                           <gossip:n-options startValue="0" endValue="23"/>
                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
                        </html:select>
                        <strong>:</strong>
                        <html:select property="to_m" >
                           <gossip:n-options startValue="0" endValue="59"/>
                           <html:options collection="NumericOptions"  property="property" labelProperty="labelProperty" />
                        </html:select>
                      </td>
                     </tr>
                  </table>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="center"><fmt:message key="log.list.log_level"/>
			</td>
			<td class="tl_g" colspan="2" nowrap style="padding: 3px;">
				<html:select property="log_level" >
                     <html:option value="">ALL</html:option>
                     <html:options collection="log_level"  property="name" labelProperty="name" />
                </html:select>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="center"><fmt:message key="log.list.user_name"/>
			</td>
			<td class="tl_g" colspan="2" nowrap style="padding: 3px;">
				<html:text property="user_name" size="40"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="lght">
			<td class="lt_g_txt_b" align="center"><fmt:message key="log.list.remote_ip"/>
			</td>
			<td class="tl_g" colspan="2" nowrap style="padding: 3px;">
				<html:text property="remote_ip" size="20"/>
			</td>
			<td class="lr_g_w" >&nbsp;	
			</td>
		</tr>
		<tr class="drk">
			<td class="lt_g_txt_b" align="center"><fmt:message key="log.list.session_id"/>
			</td>
			<td class="tl_g" colspan="2" nowrap style="padding: 3px;">
				<html:text property="session_id" size="40"/>
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
        
   </html:form>
<html:javascript formName="searchLogForm" staticJavascript="false"/>
		<tr class="lght">
			<td colspan="4" align="left">	
				&nbsp;<gossip:checkAccess objectId="6" operationId="3">
								<img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" alt="" width="25" height="25" hspace="4" border="0" align="middle">
								<a class="control" href="<c:url value="PurgeLog.do"/>"><fmt:message key="log.PURGE_LOG"/></a>
					  </gossip:checkAccess>
			</td>
		</tr>
      	<tr class="lght">
			<td colspan="4" align="right">	
				<%@ include file="../jspf/pageSplit.jspf"%>
				<br>
			</td>
		</tr>
		<tr class="lght">
			<td class="ltr_g"  colspan="4">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr class="lght">
						<td  align="center">
							<span class="c_title">
								<fmt:message key="log.list.log_date"/>
							</span>
						</td>
						<td class="l_g" align="center">
							<span class="c_title">
								<fmt:message key="log.list.logger"/>
							</span>
						</td>
						<td class="l_g" align="center">
							<span class="c_title">
								<fmt:message key="log.list.log_level"/>
							</span>
						</td>
						<td class="l_g" align="center">
							<span class="c_title">
								<fmt:message key="log.list.message"/>
							</span>
						</td>
						<td class="l_g" align="center">
							<span class="c_title">
								<fmt:message key="log.list.user_name"/>
							</span>
						</td>
						<td class="l_g" align="center">
							<span class="c_title">
								<fmt:message key="log.list.session_id"/>
							</span>
						</td>
						<td class="l_g" align="center">
							<span class="c_title">
								<fmt:message key="log.list.remote_ip"/>
							</span>
						</td>
					</tr>
					<c:forEach var="entry" items="${requestScope.JRF_RECORDS_DATA.records}" varStatus="status">
                       <tr class="strip<c:out value="${status.count%2}"/>">
                            <td  class="t_g" nowrap>
								&nbsp;<fmt:parseDate var="log_date" value="${entry.log_date}" pattern="yyyy-MM-dd HH:mm:ss,SSS" timeZone="GMT"/>&nbsp;
								<fmt:formatDate value="${log_date}" pattern="yyyy-MM-dd HH:mm:ss,SSS"/>
							</td>
							<td class="tl_g" >
								&nbsp;<c:out value="${entry.logger}"/>&nbsp;
							</td>
							<td class="tl_g" >
								&nbsp;<c:out value="${entry.log_level}"/>&nbsp;
							</td>
							<td class="tl_g" >
								&nbsp;<c:out value="${entry.message}"/>&nbsp;
							</td>
							<td class="tl_g" >
								&nbsp;<c:out value="${entry.user_name}"/>&nbsp;
							</td>
							<td class="tl_g" >
								&nbsp;<c:out value="${entry.session_id}"/>&nbsp;
							</td>
							<td class="tl_g" >
								&nbsp;<c:out value="${entry.remote_ip}"/>&nbsp;
							</td>
						</tr>
             </c:forEach>
             </table>
           </td>
		</tr>
      		<c:if test="${requestScope.JRF_RECORDS_DATA.recordsCount==0}">
         		<tr  class="drk">
                   <td class="lrt_g_txt_b"align="center" colspan="4"><br>
                      <fmt:message key="global.NO_RECORDS"/>
                   </td>
            	</tr>
           </c:if>
        <tr class="lght">
			<td class="t_g" colspan="4" align="right">	
			    <br>
				<%@ include file="../jspf/pageSplit.jspf"%>
			</td>
		</tr>
     
     </table>

   		<%@ include file="../jspf/timezone.jspf"%>





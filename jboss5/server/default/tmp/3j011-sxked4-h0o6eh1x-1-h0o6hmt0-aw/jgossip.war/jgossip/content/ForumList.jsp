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
			<fmt:message key="forum.EF"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

<c:choose>		
	<c:when test="${!empty requestScope.JRF_GROUPS}" >
 <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<fmt:message key="forum.EF"/>&nbsp;&nbsp;
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
				<span class="c_title">&nbsp;<fmt:message key="forum.FORUM_NAME"/>&nbsp;</span>
			</td>
			<td width="50%">	&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td >	&nbsp;
			</td>
			<td class="r_g" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>

	 <c:forEach items="${requestScope.JRF_GROUPS}" var="group"> 
		<tr class="lght">
			<td class="t_g" colspan="5">
				<br><span class="caption">&nbsp;<c:out value="${group.name}"/>&nbsp;&nbsp;<a href="<c:url value="showAddForum.do">
			                                                                                                       <c:param name="groupid" value="${group.groupid}"/>
                                                                                                                </c:url>">[<fmt:message key="forum.ADD_FORUM"/>]</a><br></span>
			</td>
   		<c:choose>		
	        <c:when test="${!empty group.forums}" >
      		<c:forEach items="${group.forums}" var="forum" varStatus="status">
           <tr class="strip<c:out value="${status.count%2}"/>">
            	<td class="lt_g_txt_b" align="center">
            		<img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/on.gif" width="25" height="25">
                </td>
                <td class="lt_g_txt_b" colspan="2">
                                       <a href="<c:url value="EditForum.do">
                		                        <c:param name="fid" value="${forum.forumid}"/>
                		                     </c:url>" title="<fmt:message key="forum.EDIT"/>"><c:out value="${forum.title}"/></a>  
				</td>
				<html:form action="/LockForum">
                <td class="lt_g_txt_b" nowrap align="center">
                    <table>
                           <tr>
                               <td>
                                    <html:hidden name="forum" property="forumid"/>
				                    <html:select name="forum" property="locked" >
                    	                <html:option value="0" key="forum.LOCK1"/>
                    	                <html:option value="1" key="forum.LOCK2"/> 
                    	                <html:option value="2" key="forum.LOCK3"/>
                    	                <html:option value="3" key="forum.LOCK4"/>
                                   </html:select>
                               </td>
                		       <td>
                                   <input  type="image" value="<fmt:message key="global.buttons.SUBMIT"/>"  src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/move.gif" width="25" height="25" alt="<fmt:message key="global.buttons.SUBMIT"/>" title="<fmt:message key="global.buttons.SUBMIT"/>" border=0 align="baseline">
                               </td>
				          </tr> 
				    </table>  
                </td>
                </html:form>
                 <td class="lrt_g_txt_b" >
                     <a href="<c:url value="DeleteForum.do">
                		          <c:param name="fid" value="${forum.forumid}"/>
                		      </c:url>" ><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/delete.gif" width="25" height="25" alt="<fmt:message key="forum.DELETE"/>" title="<fmt:message key="forum.DELETE"/>" align="baseline" border=0></a>
				</td>
          </tr>
          </c:forEach>
      </c:when>
	  <c:otherwise>
           <tr class="drk">
               <td colspan="5" class="lrt_g_txt_b" style="border-bottom-width: 1px;">
                  <fmt:message key="forum.NO_FORUMS"/>!
                     [ <a href="<a href="<c:url value="showAddForum.do">
			                       <c:param name="groupid" value="${group.groupid}"/>
                                </c:url>"><fmt:message key="global.CREATE_SOME"/></a> ]
              </td>
            </tr>
        </c:otherwise>
      </c:choose>
    </c:forEach>
       <tr class="lght">
			<td class="t_g" colspan="5">	&nbsp;
			</td>
		</tr>
		<tr class="lght">
			<td  colspan="5">
				<strong><fmt:message key="forum.STATUS"/>:</strong><br/>
				<strong><fmt:message key="forum.LOCK2"/></strong>&nbsp;-&nbsp;<fmt:message key="forum.LOCK2.DESC"/><br/>
				<strong><fmt:message key="forum.LOCK3"/></strong>&nbsp;-&nbsp;<fmt:message key="forum.LOCK3.DESC"/><br/>
				<c:set var="JRF_INVADER_STATUS"><gossip:config key="<%=IConst.CONFIG.INVADER1%>"/></c:set>
				<strong><fmt:message key="forum.LOCK4"/></strong>&nbsp;-&nbsp;<fmt:message key="forum.LOCK4.DESC"/>&nbsp;<gossip:userStatus status="${JRF_INVADER_STATUS}"/><br/>
			</td>
		</tr>
            </table>
<br>
	</c:when>
	<c:otherwise>
 <table width="98%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70">	&nbsp;&nbsp;
			</td>
			<td class="top_tab" nowrap>	&nbsp;&nbsp;<fmt:message key="global.ERROR"/>!&nbsp;&nbsp;
			</td>
			<td width="50%">
			</td>
		</tr>
		<tr>
			<td class="tb_o" height="2"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g_tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="tb_o"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>
		<tr>
			<td height="8"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td class="lr_g"><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
			<td><img src="<gossip:config key="<%=IConst.CONFIG.WEB_ROOT%>"/>images/blank.gif" alt="" width="1" height="1" border="0"></td>
		</tr>

              <tr class="drk">
                <td colspan="3" class="lrt_g_txt_b" style="border-bottom-width: 1px;">
                  <div align="left">
                    <fmt:message key="forum.NFTD"/>!
                     [ <html:link action="showAddGroup"><fmt:message key="global.CREATE_SOME"/></html:link> ]
                  </div>
                </td>
              </tr>
            </table>
	<br>
</c:otherwise>
</c:choose>





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

<c:choose>
  <c:when test="${!empty param.gid}">
   <gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="forum.EDIT_GR2"/>
		</gossip:title>
	</gossip:navElement>
  </gossip:navBar>
 </c:when>
 <c:otherwise>
   <gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="forum.ADD_GROUP"/>
		</gossip:title>
	</gossip:navElement>
   </gossip:navBar>     
 </c:otherwise>
</c:choose>
<%@ include file="../jspf/topbar.jspf"%>

   <html:form action="/AddGroup" onsubmit="hideErrors();return validateGroupForm(this);">
      <%@ include file="../jspf/groupForm.jspf"%>
   </html:form>
   <html:javascript formName="groupForm" staticJavascript="false"/>




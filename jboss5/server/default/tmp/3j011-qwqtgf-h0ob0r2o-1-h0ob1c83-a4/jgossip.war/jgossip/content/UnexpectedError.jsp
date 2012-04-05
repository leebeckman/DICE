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
--%> <%@ include file="../jspf/jsp_header.jspf"%>
<br>
<strong><fmt:message key="errors.ERR77"/></strong>
<br>
<em>
<c:out value="${requestScope['javax.servlet.error.request_uri']}"/>
<br>
<c:out value="${requestScope['javax.servlet.error.exception']}"/>
<br>
</em>
<c:if test="${!empty requestScope.JRF_SYSTEM_EXCEPTION_MESSAGE}">
	<div style="overflow: auto; width: 800px; height: 400px;">
		<pre style="font-family: Arial, Helvetica, sans-serif; font-size: 12px;"><c:out value="${requestScope.JRF_SYSTEM_EXCEPTION_MESSAGE}"/></pre>
	</div>
</c:if>
<br>
<form>&nbsp;<input class="but_b" type="button" value="<fmt:message key="global.buttons.BACK"/>" onclick="self.history.back()"></form>
<br>


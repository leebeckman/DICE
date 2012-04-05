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
<c:if test="${empty sessionScope.JRF_USER_TIME_ZONE&&!empty sessionScope.JRF_USER.info.email}" >
<IFRAME name="_setTimeZone" id="_setTimeZone" width="0" height="0" frameborder="0"></IFRAME>
<form name="jrf_time_zone_form" target="_setTimeZone" action="<c:url value="SetTimeZone.do"/>">
<script>
	var offset=Math.round((new Date()).getTimezoneOffset()/60);
	document.writeln("<input type=\"hidden\" name=\"offset\" value=\""+offset+"\">")
</script>
</form>
<script>
	document.forms.jrf_time_zone_form.submit();
</script>
</c:if>



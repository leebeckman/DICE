<%@ include file="../jspf/jsp_header.jspf"%> 
<c:if test="${empty applicationScope.jrf_jsValidate}"><%--TODO use cache instead application scope--%>
<%@ taglib uri="/WEB-INF/lib/jssplitter.jar" prefix="jr" %>
<c:set var="jrf_jsValidate" scope="application">
<script><jr:jssweeper>   
<html:javascript staticJavascript="true" dynamicJavascript="false"/>
</jr:jssweeper></script>  
</c:set>
</c:if>
<c:out value="${applicationScope.jrf_jsValidate}" escapeXml="false"/> 
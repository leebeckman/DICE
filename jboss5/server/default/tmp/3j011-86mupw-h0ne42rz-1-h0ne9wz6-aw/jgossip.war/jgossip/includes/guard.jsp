<%@ include file="../jspf/jsp_header.jspf"%>
<c:choose>
   <c:when test="${sessionScope.JRF_USER.status<9}">	
	  <logic:redirect forward="jgossip-denied"/>
   </c:when>
   <c:otherwise> 
      <c:set var="showAdminMenu" value="Y" scope="request"/>  
   </c:otherwise>
</c:choose> 
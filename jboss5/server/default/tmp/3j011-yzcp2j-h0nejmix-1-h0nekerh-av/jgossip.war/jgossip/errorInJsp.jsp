<%@ page isErrorPage="true" %>
<h3><font color="red">Error In Jsp :</font></h3>
<strong><%=exception%></strong>
<pre>
<%exception.printStackTrace(new java.io.PrintWriter(out));%>
</pre>

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import org.jboss.jmx.adaptor.control.*;
import org.jboss.jmx.adaptor.model.*;
import java.io.*;

public final class filterView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.InstanceManager _jsp_instancemanager;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>JBoss Object Index</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"style_master.css\" type=\"text/css\">\n");
      out.write("    <meta http-equiv=\"cache-control\" content=\"no-cache\"/>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body leftmargin=\"10\" rightmargin=\"10\" topmargin=\"10\">\n");
      out.write("\n");
      out.write("<table width=\"235\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n");
      out.write("<tr>\n");
      out.write("<td align=\"center\" width=\"235\" height=\"105\"><img src=\"images/logo.gif\" border=\"0\" alt=\"JBoss\"/></td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("&nbsp;\n");
      out.write("\n");
      out.write("<table width=\"235\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n");
      out.write("<tr><td><h2>Object Name Filter</h2></td></tr>\n");
      out.write("<tr><td><h3><a href=\"HtmlAdaptor?action=displayMBeans&filter=\" target=\"ObjectNodeView\">Remove Object Name Filter</a></h3></td></tr>\n");

   Iterator mbeans = (Iterator) Server.getDomainData("");
   int i=0;
   while( mbeans.hasNext() )
   {
      DomainData domainData = (DomainData) mbeans.next();
      out.println(" <tr>");
      out.println("  <td>");
      out.println("   <li><a href=\"HtmlAdaptor?action=displayMBeans&filter="+domainData.getDomainName()+"\" target=\"ObjectNodeView\">"+domainData.getDomainName()+"</a></li>");
      out.println("  </td>");
      out.println(" </tr>");
   }

      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

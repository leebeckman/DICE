package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.*;
import java.util.*;
import org.jboss.jmx.adaptor.model.*;
import java.io.*;

public final class displayMBeans_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<?xml version=\"1.0\"?>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");

	 String bindAddress = "";
         String serverName = "";
         try
         {
            bindAddress = System.getProperty("jboss.bind.address", "");
            serverName = System.getProperty("jboss.server.name", "");
         }
         catch (SecurityException se) {}

         String hostname = "";
         try
         {
            hostname = InetAddress.getLocalHost().getHostName();
         }
         catch(IOException e)  {}

         String hostInfo = hostname;
         if (!bindAddress.equals(""))
         {
            hostInfo = hostInfo + " (" + bindAddress + ")";
         }

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("   <title>JBoss JMX Management Console - ");
      out.print( hostInfo );
      out.write("</title>\n");
      out.write("   <link rel=\"stylesheet\" href=\"style_master.css\" type=\"text/css\"/>\n");
      out.write("   <meta http-equiv=\"cache-control\" content=\"no-cache\"/>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("  <table width='100%' cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n");
      out.write("    <tr>\n");
      out.write("      <td height=\"105\" align=\"center\"><h1>JMX Agent View</h1><h3>");
      out.print( hostInfo);
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print( serverName );
      out.write("</h3></td>\n");
      out.write("      <td height=\"105\" align=\"center\" width=\"300\" nowrap>\n");
      out.write("        <form action=\"HtmlAdaptor?action=displayMBeans\" method=\"post\" name=\"applyFilter\" id=\"applyFilter\">\n");
      out.write("          ObjectName Filter (e.g.: \"jboss:*\", \"*:service=invoker,*\"):<br/>\n");
      out.write("          <input type=\"text\" name=\"filter\" size=\"20\" value=\"");
      out.print( request.getAttribute("filter"));
      out.write("\" />\n");
      out.write("          <input type=\"submit\" name=\"apply\" value=\"Apply Filter\" />\n");
      out.write("          <input type=\"button\" onClick=\"javascript:location='HtmlAdaptor?filter='\" value=\"Clear Filter\" />\n");

	if (request.getAttribute("filterError") != null) {
                out.println("<br/><span class='error'>"+request.getAttribute("filterError")+"</span>");
        }

      out.write("\n");
      out.write("        </form>\n");
      out.write("        ");
      out.print( new java.util.Date() );
      out.write("\n");
      out.write("      </td>\n");
      out.write("    </tr>\n");
      out.write("  </table>\n");
      out.write("\n");
      out.write("  &nbsp;\n");
      out.write("\n");

   out.println("<table width='100%' cellspacing='1' cellpadding='1' border='1'>");
   Iterator mbeans = (Iterator) request.getAttribute("mbeans");
   int i=0;
   while( mbeans.hasNext() )
   {
      DomainData domainData = (DomainData) mbeans.next();
      out.println(" <tr>");
      out.println("  <th style='text-align: left'>");
      out.println("   <h2><a href=\"javascript:document.applyFilter.filter.value='"+domainData.getDomainName()+":*';document.applyFilter.submit()\">"+domainData.getDomainName()+"</a></h2>");
      out.println("  </th>");
      out.println(" </tr>");
      out.println(" <tr>");
      out.println("  <td bgcolor='#D0D0D0'>");
      out.println("    <ul>");
      MBeanData[] data = domainData.getData();
      for(int d = 0; d < data.length; d ++)
      {
         String name = data[d].getObjectName().toString();
         String properties = data[d].getNameProperties();
         out.println("     <li><a href=\"HtmlAdaptor?action=inspectMBean&amp;name="+URLEncoder.encode(name,"UTF-8")+"\">"+URLDecoder.decode(properties,"UTF-8")+"</a></li>");
      }
      out.println("   </ul>");
      out.println("  </td>");
      out.println(" </tr>");
   }
   out.println("</table>");

      out.write("\n");
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

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.management.*;
import javax.management.modelmbean.*;
import org.jboss.jmx.adaptor.control.Server;
import org.jboss.jmx.adaptor.control.AttrResultInfo;
import org.jboss.jmx.adaptor.model.*;
import org.dom4j.io.HTMLWriter;
import org.dom4j.tree.FlyweightCDATA;
import java.lang.reflect.Array;
import java.io.StringWriter;
import java.beans.PropertyEditor;
import org.jboss.util.propertyeditor.PropertyEditors;

public final class inspectMBean_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    private static final Comparator MBEAN_FEATURE_INFO_COMPARATOR = new Comparator()
    {
      public int compare(Object value1, Object value2)
      {
        MBeanFeatureInfo featureInfo1 = (MBeanFeatureInfo) value1;
        MBeanFeatureInfo featureInfo2 = (MBeanFeatureInfo) value2;

        String name1 = featureInfo1.getName();
        String name2 = featureInfo2.getName();

        return name1.compareTo(name2);
      }

      public boolean equals(Object other)
      {
        return this == other;
      }
    };

    String sep = System.getProperty("line.separator","\n");

    public String fixDescription(String desc)
    {
      if (desc == null || desc.equals(""))
      {
        return "(no description)";
      }
      return desc;
    }

    public String fixValue(Object value)
    {
        if (value == null)
            return null;
        String s = String.valueOf(value);
        StringWriter sw = new StringWriter();
        HTMLWriter hw = new HTMLWriter(sw);
        try
        {
           // hw.write(s); // strips whitespace
           hw.write(new FlyweightCDATA(s));
	   s = sw.toString();
        }
        catch(Exception e)
        {
        }
        return s;
    }

    public String fixValueForAttribute(Object value)
    {
        if (value == null)
            return null;
      String s = String.valueOf(value);
       StringWriter sw = new StringWriter();
       HTMLWriter hw = new HTMLWriter(sw);
       try
       {
          hw.write(s);
          s = sw.toString();
       }
       catch(Exception e)
       {
       }
       return s;
    }


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

String hostname = "";
try
{
  hostname = InetAddress.getLocalHost().getHostName();
}
catch(IOException e){}

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html \n");
      out.write("    PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n");
      out.write("    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("   <title>MBean Inspector</title>\n");
      out.write("   <link rel=\"stylesheet\" href=\"style_master.css\" type=\"text/css\" />\n");
      out.write("   <meta http-equiv=\"cache-control\" content=\"no-cache\" />\n");
      out.write("</head>\n");
      out.write("\n");
      org.jboss.jmx.adaptor.model.MBeanData mbeanData = null;
      synchronized (request) {
        mbeanData = (org.jboss.jmx.adaptor.model.MBeanData) _jspx_page_context.getAttribute("mbeanData", PageContext.REQUEST_SCOPE);
        if (mbeanData == null){
          mbeanData = new org.jboss.jmx.adaptor.model.MBeanData();
          _jspx_page_context.setAttribute("mbeanData", mbeanData, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\n');

   if(mbeanData.getObjectName() == null)
   {

      out.write('\n');
      if (true) {
        _jspx_page_context.forward("/");
        return;
      }
      out.write('\n');

   }
   ObjectName objectName = mbeanData.getObjectName();
   String objectNameString = mbeanData.getName();
   String quotedObjectNameString = URLEncoder.encode(mbeanData.getName(), "UTF-8");
   MBeanInfo mbeanInfo = mbeanData.getMetaData();
   MBeanAttributeInfo[] attributeInfo = mbeanInfo.getAttributes();
   MBeanOperationInfo[] operationInfo = mbeanInfo.getOperations();

   //FIXME: Seems to create ArrayIndexOutofBoundsException when uncommented
   /*Arrays.sort(attributeInfo, MBEAN_FEATURE_INFO_COMPARATOR);

   HashMap operationInfoIndexMap = new HashMap();
   for (int a = 0; a < operationInfo.length; a++)
   {
      MBeanOperationInfo opInfo = operationInfo[a];
      operationInfoIndexMap.put(opInfo, String.valueOf(a));
   }

   Arrays.sort(operationInfo, MBEAN_FEATURE_INFO_COMPARATOR);
   */

      out.write("\n");
      out.write("\n");
      out.write("<body leftmargin=\"10\" rightmargin=\"10\" topmargin=\"10\">\n");
      out.write("\n");
      out.write("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n");
      out.write(" <tr>\n");
      out.write("  <td height=\"105\" align=\"center\"><h1>JMX MBean View</h1>");
      out.print( hostname );
      out.write("</td>\n");
      out.write("  <td height=\"105\" align=\"center\" width=\"300\" nowrap>\n");
      out.write("    <p>\n");
      out.write("      <input type=\"button\" value=\"Back to Agent\" onClick=\"javascript:location='HtmlAdaptor?action=displayMBeans'\"/>\n");
      out.write("      <input type=\"button\" value=\"Refresh MBean View\" onClick=\"javascript:location='HtmlAdaptor?action=inspectMBean&amp;name=");
      out.print( URLEncoder.encode(request.getParameter("name"),"UTF-8") );
      out.write("'\"/>\n");
      out.write("    </p>\n");
      out.write("    ");
      out.print( new java.util.Date() );
      out.write("\n");
      out.write("  </td>\n");
      out.write(" </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("&nbsp;\n");
      out.write("\n");

   Hashtable properties = objectName.getKeyPropertyList();
   int size = properties.keySet().size();

      out.write("\n");
      out.write("\n");
      out.write("<!-- 1 -->\n");
      out.write("\n");
      out.write("<table width=\"100%\" cellspacing=\"1\" cellpadding=\"1\" border=\"1\" align=\"center\">\n");
      out.write(" <tr><th rowspan=\"");
      out.print( size + 1 );
      out.write("\">Name</th><td><b>Domain</b></td><td>");
      out.print( objectName.getDomain() );
      out.write("</td></tr>\n");

   Iterator it = properties.keySet().iterator();
   while( it.hasNext() )
   {
     String key=(String)it.next();
     String val=(String)properties.get(key);
     out.println(" <tr><td><b>"+key+"</b></td><td>"+val+"</td></tr>");
   }

      out.write("\n");
      out.write(" <tr><th>Java Class</th><td colspan=\"2\">");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((org.jboss.jmx.adaptor.model.MBeanData)_jspx_page_context.findAttribute("mbeanData")).getClassName())));
      out.write("</td></tr>\n");
      out.write(" <tr><th>Description</th><td colspan=\"2\">");
      out.print( fixDescription(mbeanInfo.getDescription()));
      out.write("</td></tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("<!-- 2 -->\n");
      out.write("<br/>\n");
      out.write("<form method=\"post\" action=\"HtmlAdaptor\">\n");
      out.write(" <input type=\"hidden\" name=\"action\" value=\"updateAttributes\" />\n");
      out.write(" <input type=\"hidden\" name=\"name\" value=\"");
      out.print( objectNameString );
      out.write("\" />\n");
      out.write(" <table width=\"100%\" cellspacing=\"1\" cellpadding=\"1\" border=\"1\" align=\"center\">\n");
      out.write("  <tr>\n");
      out.write("   <th>Attribute Name</th>\n");
      out.write("   <th>Access</th>\n");
      out.write("   <th>Type</th>\n");
      out.write("   <th>Description</th>\n");
      out.write("   <th>Attribute Value</th>\n");
      out.write("  </tr>\n");

  boolean hasWriteableAttribute=false;
  for(int a = 0; a < attributeInfo.length; a ++)
  {
    MBeanAttributeInfo attrInfo = attributeInfo[a];
    String attrName = attrInfo.getName();
    String attrType = attrInfo.getType();
    AttrResultInfo attrResult = Server.getMBeanAttributeResultInfo(objectNameString, attrInfo);
    String attrValue = attrResult.getAsText();
    String access = "";
    if( attrInfo.isReadable() ) access += "R";
    if( attrInfo.isWritable() )
    {
      access += "W";
      hasWriteableAttribute=true;
    }
    String attrDescription = fixDescription(attrInfo.getDescription());
    out.println("  <tr>");
    out.println("   <td class='param'>"+attrName+"</td>");
    out.println("   <td align='center'>"+access+"</td>");
    out.println("   <td>"+attrType+"</td>");
    out.println("   <td>"+attrDescription+"</td>");
    out.println("   <td>");
    out.println("    <pre>");

    if( attrInfo.isWritable() )
    {
      String readonly = attrResult.editor == null ? "class='readonly' readonly" : "class='writable'";
      if( attrType.equals("boolean") || attrType.equals("java.lang.Boolean") )
      {
        Boolean value = attrValue == null || "".equals( attrValue ) ? null : Boolean.valueOf(attrValue);
        String trueChecked = (value == Boolean.TRUE ? "checked" : "");
        String falseChecked = (value == Boolean.FALSE ? "checked" : "");
	String naChecked = value == null ? "checked" : "";
        out.print("<input type='radio' name='"+attrName+"' value='True' "+trueChecked+"/>True");
        out.print("<input type='radio' name='"+attrName+"' value='False' "+falseChecked+"/>False");
	// For wrappers, enable a 'null' selection
	if ( attrType.equals( "java.lang.Boolean" ) && PropertyEditors.isNullHandlingEnabled() )
        {
		out.print("<input type='radio' name='"+attrName+"' value='' "+naChecked+"/>True");
	}

      }
      else if( attrInfo.isReadable() )
      {
	attrValue = fixValueForAttribute(attrValue);
        if (String.valueOf(attrValue).indexOf(sep) == -1)
        {
          out.print("<input type='text' size='80' name='"+attrName+"' value='"+attrValue+"' "+readonly+"/>");
        }
        else
        {
          out.print("<textarea cols='80' rows='10' type='text' name='"+attrName+"' "+readonly+">"+attrValue+"</textarea>");
        }
      }
      else
      {
        out.print("<input type='text' name='"+attrName+"' "+readonly+"/>");
      }
    }
    else
    {
      if( attrType.equals("[Ljavax.management.ObjectName;") )
      {
        ObjectName[] names = (ObjectName[]) Server.getMBeanAttributeObject(objectNameString, attrName);
        if( names != null )
        {
          for( int i = 0; i < names.length; i++ )
          {
            out.print("<p align='center'><a href='HtmlAdaptor?action=inspectMBean&name="+URLEncoder.encode(names[i]+"","UTF-8")+">"+names[i]+"</a></p>");
          }
        }
      }
      else if( attrType.startsWith("["))
      {
        Object arrayObject = Server.getMBeanAttributeObject(objectNameString, attrName);
        if (arrayObject != null)
        {
          for (int i = 0; i < Array.getLength(arrayObject); ++i)
          {
            out.println(fixValue(Array.get(arrayObject,i)));
          }
        }
      }
      else
      {
        out.print(fixValue(attrValue));
      }
    }

    if( attrType.equals("javax.management.ObjectName") )
    {
      if( attrValue != null )
      {
        out.print("<p align='center'><a href='HtmlAdaptor?action=inspectMBean&name="+URLEncoder.encode(attrValue,"UTF-8")+"'>View MBean</a></p>");
      }
    }
    out.println("    </pre>");
    out.println("   </td>");
    out.println("  </tr>");
  }

  if(hasWriteableAttribute)
  {
    out.println(" <tr><td colspan='4'></td><td class='arg'><p align='center'><input type='submit' value='Apply Changes'/></p></td></tr>");
  }

      out.write("\n");
      out.write(" </table>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<!-- 3 -->\n");
      out.write("<br/>\n");

if (operationInfo.length > 0)
{
  out.println(" <table width='100%' cellspacing='1' cellpadding='1' border='1' align='center'>");
  out.println("  <tr>");
  out.println("   <th>Operation</th>");
  out.println("   <th>Return Type</th>");
  out.println("   <th>Description</th>");
  out.println("   <th>Parameters</th>");
  out.println("  </tr>");

  for(int a = 0; a < operationInfo.length; a ++)
  {
    MBeanOperationInfo opInfo = operationInfo[a];
    boolean accept = true;
    if (opInfo instanceof ModelMBeanOperationInfo)
    {
      Descriptor desc = ((ModelMBeanOperationInfo)opInfo).getDescriptor();
      String role = (String)desc.getFieldValue("role");
      if ("getter".equals(role) || "setter".equals(role))
      {
        accept = false;
      }
    }
    if (accept)
    {
      MBeanParameterInfo[] sig = opInfo.getSignature();
      out.println("  <tr>");
      out.println("   <td class='param'>"+opInfo.getName()+"</td>");
      out.println("   <td>"+opInfo.getReturnType()+"</td>");
      out.println("   <td>"+fixDescription(opInfo.getDescription())+"</td>");
      out.println("   <td align='center'>");
      out.println("    <form method='post' action='HtmlAdaptor'>");
      out.println("     <input type='hidden' name='action' value='invokeOp'/>");
      out.println("     <input type='hidden' name='name' value='"+quotedObjectNameString+"'/>");
      out.println("     <input type='hidden' name='methodIndex' value='"+a+"'/>");

      if( sig.length > 0 )
      {
        out.println("     <table width='100%' cellspacing='1' cellpadding='1' border='0'>");
        for(int p = 0; p < sig.length; p ++)
        {
          MBeanParameterInfo paramInfo = sig[p];
          String pname = paramInfo.getName();
          String ptype = paramInfo.getType();
          if( pname == null || pname.length() == 0 || pname.equals(ptype) )
          {
            pname = "arg"+p;
          }
          String pdesc = fixDescription(paramInfo.getDescription());
          out.println("      <tr>");
          out.println("       <td class='arg'>"+pname+"</td>");
          out.println("       <td class='arg'>"+ptype+"</td>");
          out.println("       <td class='arg'>"+pdesc+"</td>");
          out.print("       <td class='arg' width='50'>");
          if(ptype.equals("boolean")||ptype.equals("java.lang.Boolean"))
          {
            out.print("<input type='radio' name='arg"+p+"' value='True' checked/>True");
            out.print("<input type='radio' name='arg"+p+"' value='False'/>False");
          }
          else
          {
            out.print("<input type='text' class='writable' name='arg"+p+"'/>");
          }
          out.println("</td>");
          out.println("      </tr>");
        }
        out.println("     </table>");
      }
      else
      {
        out.println("     [no parameters]<BR>");
      }
      out.println("     <input type='submit' value='Invoke'/>");
      out.println("    </form>");
      out.println("  </td>");
      out.println(" </tr>");
    }
  }
  out.println(" </table>");
}

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

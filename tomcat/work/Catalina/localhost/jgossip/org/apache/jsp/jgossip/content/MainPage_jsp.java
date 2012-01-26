package org.apache.jsp.jgossip.content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;

public final class MainPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(6);
    _jspx_dependants.add("/jgossip/content/../jspf/jsp_header.jspf");
    _jspx_dependants.add("/jgossip/content/../jspf/topbar.jspf");
    _jspx_dependants.add("/jgossip/content/../jspf/status.jspf");
    _jspx_dependants.add("/WEB-INF/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/gossip.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fsetTimeZone_0026_005fvalue_005fscope_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgossip_005fjumpoptions_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fmessagesNotPresent;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fmessagesPresent;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005flink_0026_005ftitleKey_005ftarget_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgossip_005fhasNewMess_0026_005ftid_005fsender_005fintime;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgossip_005fprocess_0026_005fvalue_005fcutToLength_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005fvar_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005ftimeStyle_005fdateStyle_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fsetTimeZone_0026_005fvalue_005fscope_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgossip_005fjumpoptions_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flogic_005fmessagesNotPresent = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flogic_005fmessagesPresent = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005ftitleKey_005ftarget_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgossip_005fhasNewMess_0026_005ftid_005fsender_005fintime = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgossip_005fprocess_0026_005fvalue_005fcutToLength_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005ftimeStyle_005fdateStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.release();
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody.release();
    _005fjspx_005ftagPool_005ffmt_005fsetTimeZone_0026_005fvalue_005fscope_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.release();
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.release();
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fgossip_005fjumpoptions_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.release();
    _005fjspx_005ftagPool_005flogic_005fmessagesNotPresent.release();
    _005fjspx_005ftagPool_005flogic_005fmessagesPresent.release();
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.release();
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005ftitleKey_005ftarget_005faction.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fgossip_005fhasNewMess_0026_005ftid_005fsender_005fintime.release();
    _005fjspx_005ftagPool_005fgossip_005fprocess_0026_005fvalue_005fcutToLength_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005fvar_005ftest.release();
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005ftimeStyle_005fdateStyle_005fnobody.release();
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
      response.setContentType("text/html;charset=ISO-8859-5");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"../errorInJsp.jsp", true, 32768, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(' ');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      //  c:set
      org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.el.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
      _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fset_005f0.setParent(null);
      // /jgossip/content/../jspf/jsp_header.jspf(35,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setVar("localeKey");
      int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
      if (_jspx_eval_c_005fset_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_c_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_c_005fset_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_c_005fset_005f0.doInitBody();
        }
        do {
          out.print(org.apache.struts.Globals.LOCALE_KEY );
          int evalDoAfterBody = _jspx_th_c_005fset_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_c_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f0);
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_fmt_005fsetLocale_005f0(_jspx_page_context))
        return;
      out.write(' ');
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_fmt_005fsetBundle_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_fmt_005fsetTimeZone_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("function jumpMenu(selObj,restore){\r\n");
      out.write("    var nurl=selObj.options[selObj.selectedIndex].value; \r\n");
      out.write("\tif(nurl){\r\n");
      out.write("        self.location.href=nurl;\r\n");
      out.write("\t}                       \t\r\n");
      out.write("\tif (restore) \r\n");
      out.write("\t    selObj.selectedIndex=0;\r\n");
      out.write("}  \r\n");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("         \r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div style=\"position: absolute; top:4px; left: 25px; z-index: 3;\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f0 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f0.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f0.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(45,76) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f0.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f0 = _jspx_th_gossip_005fconfig_005f0.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f0);
      out.write("images/logo_top.gif\" alt=\"\" width=\"90\" height=\"91\" border=\"0\"></div>\r\n");
      out.write("\r\n");
      out.write("<table width=\"98%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n");
      out.write("<tr>\r\n");
      out.write("    <td class=\"r_o\" height=\"25\" >&nbsp;</td>\r\n");
      out.write("    <td>&nbsp;</td>\r\n");
      out.write("    <td width=\"100\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f1 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f1.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f1.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(51,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f1.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f1 = _jspx_th_gossip_005fconfig_005f1.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f1);
      out.write("images/blank.gif\" alt=\"\" width=\"100\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td >&nbsp;</td>\r\n");
      out.write("    <td class=\"top_tab\" nowrap >&nbsp;<span class=\"caption\">&nbsp;&nbsp;<a class=\"header\" href=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f2 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f2.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f2.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(53,96) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f2.setKey(IConst.CONFIG.DOMAIN_URL);
      int _jspx_eval_gossip_005fconfig_005f2 = _jspx_th_gossip_005fconfig_005f2.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f2);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f2);
      out.write('"');
      out.write('>');
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f3 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f3.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f3.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(53,150) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f3.setKey(IConst.CONFIG.DOMAIN_NAME);
      int _jspx_eval_gossip_005fconfig_005f3 = _jspx_th_gossip_005fconfig_005f3.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f3);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f3);
      out.write("</a>&nbsp;&nbsp;</span>&nbsp;\r\n");
      out.write("\t</td>\r\n");
      out.write("    <td >&nbsp;&nbsp;</td>\r\n");
      out.write("    <td nowrap width=\"100%\" align=\"right\" class=\"nav\" nowrap>\r\n");
      out.write("    ");
      //  html:link
      org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f0 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
      _jspx_th_html_005flink_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005flink_005f0.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(57,4) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005flink_005f0.setAction("Main");
      int _jspx_eval_html_005flink_005f0 = _jspx_th_html_005flink_005f0.doStartTag();
      if (_jspx_eval_html_005flink_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_html_005flink_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_html_005flink_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_html_005flink_005f0.doInitBody();
        }
        do {
          //  gossip:config
          org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f4 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
          _jspx_th_gossip_005fconfig_005f4.setPageContext(_jspx_page_context);
          _jspx_th_gossip_005fconfig_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f0);
          // /jgossip/content/../jspf/topbar.jspf(57,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_gossip_005fconfig_005f4.setKey(IConst.CONFIG.SITE_NAME);
          int _jspx_eval_gossip_005fconfig_005f4 = _jspx_th_gossip_005fconfig_005f4.doStartTag();
          if (_jspx_th_gossip_005fconfig_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f4);
            return;
          }
          _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f4);
          int evalDoAfterBody = _jspx_th_html_005flink_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_html_005flink_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_html_005flink_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f0);
      out.write("\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("&nbsp;&nbsp;\r\n");
      out.write("    </td>\r\n");
      out.write("    <td>&nbsp;</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("    <td height=\"5\" colspan=\"2\">\r\n");
      out.write("\t\t<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td height=\"5\" class=\"b_g\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f5 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f5.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f5.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(80,42) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f5.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f5 = _jspx_th_gossip_005fconfig_005f5.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f5);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f5);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</td>\r\n");
      out.write("    <td><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f6 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f6.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f6.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(84,18) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f6.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f6 = _jspx_th_gossip_005fconfig_005f6.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f6);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f6);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t<td class=\"t_o\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f7 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f7.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f7.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(85,27) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f7.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f7 = _jspx_th_gossip_005fconfig_005f7.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f7);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f7);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td class=\"lr_g_t_o\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f8 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f8.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f8.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(86,35) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f8.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f8 = _jspx_th_gossip_005fconfig_005f8.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f8);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f8);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td class=\"b_g_t_o\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f9 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f9.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f9.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(87,34) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f9.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f9 = _jspx_th_gossip_005fconfig_005f9.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f9);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f9);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td class=\"b_g\" colspan=\"2\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f10 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f10.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f10.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(88,42) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f10.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f10 = _jspx_th_gossip_005fconfig_005f10.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f10);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f10);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("    <td height=\"5\" colspan=\"2\">\r\n");
      out.write("\t\t<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td height=\"5\" class=\"l_g\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f11 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f11.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f11.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(95,42) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f11.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f11 = _jspx_th_gossip_005fconfig_005f11.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f11);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f11);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f12 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f12.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f12.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(99,15) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f12.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f12 = _jspx_th_gossip_005fconfig_005f12.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f12);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f12);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f13 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f13.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f13.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(100,18) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f13.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f13 = _jspx_th_gossip_005fconfig_005f13.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f13);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f13);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td class=\"l_g\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f14 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f14.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f14.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(101,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f14.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f14 = _jspx_th_gossip_005fconfig_005f14.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f14);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f14);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td colspan=\"2\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f15 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f15.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f15.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(102,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f15.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f15 = _jspx_th_gossip_005fconfig_005f15.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f15);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f15);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("    <td class=\"r_g\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f16 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f16.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f16.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(103,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f16.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f16 = _jspx_th_gossip_005fconfig_005f16.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f16);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f16);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td valign=\"top\">\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td valign=\"top\"  height=\"38\"  class=\"l_g_r_o\">&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td colspan=\"2\">&nbsp;</td>\r\n");
      out.write("    <td colspan=\"4\" rowspan=\"2\" valign=\"bottom\">\r\n");
      out.write("\t\t<table width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" >\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t    <td colspan=\"2\" valign=\"top\" >&nbsp;<span class=\"motto\">");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f17 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f17.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f17.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(117,63) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f17.setKey(IConst.CONFIG.MOTTO);
      int _jspx_eval_gossip_005fconfig_005f17 = _jspx_th_gossip_005fconfig_005f17.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f17);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f17);
      out.write("</span></td>\r\n");
      out.write("\t\t\t    <td rowspan=\"2\" class=\"b_lg\" nowrap valign=\"top\" align=\"right\">\r\n");
      out.write("\t\t\t    \t<span class=\"nav\" id=\"forum_menu\" ");
      if (_jspx_meth_c_005fif_005f4(_jspx_page_context))
        return;
      out.write(" ><!--forum menu-->\r\n");
      out.write("\t\t\t    \t    ");
      if (_jspx_meth_html_005flink_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                  \t\t<b> | </b>");
      if (_jspx_meth_html_005flink_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                  \t\t<b> | </b> \r\n");
      out.write("\t\t\t    \t    ");
      if (_jspx_meth_c_005fchoose_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t    \t</span>\r\n");
      out.write("\t\t\t    \t");
      if (_jspx_meth_c_005fif_005f8(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t    </td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=\"400\"  align=\"right\" valign=\"bottom\">\r\n");
      out.write("\t\t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"130\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td  class=\"b_o\" colspan=\"2\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td  class=\"b_g\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f18 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f18.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f18.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(181,34) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f18.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f18 = _jspx_th_gossip_005fconfig_005f18.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f18);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f18);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td  class=\"l_o_b_g\" height=\"4\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f19 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f19.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f19.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(182,49) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f19.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f19 = _jspx_th_gossip_005fconfig_005f19.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f19);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f19);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"6\" class=\"r_o\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f20 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f20.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f20.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(183,43) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f20.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f20 = _jspx_th_gossip_005fconfig_005f20.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f20);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f20);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td  height=\"2\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f21 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f21.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f21.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(186,33) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f21.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f21 = _jspx_th_gossip_005fconfig_005f21.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f21);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f21);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td  class=\"l_o\"  ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f22 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f22.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f22.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(187,36) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f22.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f22 = _jspx_th_gossip_005fconfig_005f22.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f22);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f22);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td  class=\"r_o\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f23 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f23.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f23.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(188,35) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f23.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f23 = _jspx_th_gossip_005fconfig_005f23.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f23);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f23);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t    <td class=\"tr_o\" width=\"150\" height=\"27\" nowrap valign=\"middle\">\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<table cellspacing=\"0\" cellpadding=\"4\" border=\"0\">\r\n");
      out.write("\t\t\t\t\t\t<form name=\"form1\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td><span class=\"caption\">");
      if (_jspx_meth_fmt_005fmessage_005f23(_jspx_page_context))
        return;
      out.write("</span></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td> \r\n");
      out.write("                         <select name=\"Quick\" onChange=\"jumpMenu(this,0)\" style=\"width: 198px;\">\r\n");
      out.write("                        \t<option value=\"Main.do\" title=\"");
      if (_jspx_meth_fmt_005fmessage_005f24(_jspx_page_context))
        return;
      out.write('"');
      out.write('>');
      if (_jspx_meth_fmt_005fmessage_005f25(_jspx_page_context))
        return;
      out.write("</option>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f10(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                         </select>\r\n");
      out.write("                         </td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input class=\"but\" type=\"button\" name=\"go\" onclick=\"jumpMenu(document.form1.Quick,0)\" value=\"");
      if (_jspx_meth_fmt_005fmessage_005f26(_jspx_page_context))
        return;
      out.write("\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("    \t\t\t<td  width=\"400\"  height=\"5\" align=\"right\">\r\n");
      out.write("\t\t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t<tr><td  width=\"130\" height=\"6\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f24 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f24.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f24.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(219,48) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f24.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f24 = _jspx_th_gossip_005fconfig_005f24.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f24);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f24);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td  class=\"l_o\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f25 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f25.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f25.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(220,35) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f25.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f25 = _jspx_th_gossip_005fconfig_005f25.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f25);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f25);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"6\" class=\"r_o\" height=\"3\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f26 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f26.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f26.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(221,54) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f26.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f26 = _jspx_th_gossip_005fconfig_005f26.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f26);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f26);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td class=\"b_o\" colspan=\"3\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f27 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f27.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f27.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(225,42) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f27.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f27 = _jspx_th_gossip_005fconfig_005f27.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f27);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f27);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</td>\r\n");
      out.write("    <td rowspan=\"2\" class=\"l_o_r_g\">&nbsp;</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td colspan=\"3\"  valign=\"bottom\">\r\n");
      out.write("\t\t<table cellspacing=\"0\"  cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n");
      out.write("\t\t    <tr>\r\n");
      out.write("\t\t\t\t<td  class=\"l_g_r_o\" >&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td></tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td  class=\"lb_g_r_o\" height=\"4\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f28 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f28.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f28.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(238,47) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f28.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f28 = _jspx_th_gossip_005fconfig_005f28.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f28);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f28);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t<td width=\"100%\" class=\"b_g\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f29 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f29.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f29.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(239,44) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f29.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f29 = _jspx_th_gossip_005fconfig_005f29.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f29);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f29);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td  class=\"r_o\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f30 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f30.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f30.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(242,32) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f30.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f30 = _jspx_th_gossip_005fconfig_005f30.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f30);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f30);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t<td width=\"100%\"  height=\"8\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f31 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f31.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f31.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(243,44) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f31.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f31 = _jspx_th_gossip_005fconfig_005f31.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f31);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f31);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td   valign=\"top\">\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td  height=\"5\" class=\"r_o\">&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td colspan=\"2\" nowrap align=\"right\">&nbsp;<span class=\"txt\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fchoose_005f1(_jspx_page_context))
        return;
      out.write("</span>&nbsp;</td>\r\n");
      out.write("\t<td colspan=\"5\" valign=\"top\">\r\n");
      out.write("\t\t<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("    \t\t\t<td  width=\"400\"  height=\"6\" align=\"right\">\r\n");
      out.write("    \t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"130\" height=\"6\" align=\"center\" nowrap>&nbsp;<span class=\"caption_t\">\r\n");
      out.write("\t\t\t\t        ");
      //  c:choose
      org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f2 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
      _jspx_th_c_005fchoose_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fchoose_005f2.setParent(null);
      int _jspx_eval_c_005fchoose_005f2 = _jspx_th_c_005fchoose_005f2.doStartTag();
      if (_jspx_eval_c_005fchoose_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\t\t\r\n");
          out.write("\t                       ");
          //  c:when
          org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_005fwhen_005f2 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
          _jspx_th_c_005fwhen_005f2.setPageContext(_jspx_page_context);
          _jspx_th_c_005fwhen_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
          // /jgossip/content/../jspf/topbar.jspf(274,24) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fwhen_005f2.setTest("${sessionScope.JRF_USER.status==0}");
          int _jspx_eval_c_005fwhen_005f2 = _jspx_th_c_005fwhen_005f2.doStartTag();
          if (_jspx_eval_c_005fwhen_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write(" \r\n");
              out.write("\t\t\t\t\t\t\t    ");
if (IConst.VALUES.FALSE.equals(Configurator.getInstance().get(IConst.CONFIG.ENABLE_FORUM_SIGN_ON))) {
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t    \t");
              //  c:set
              org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.el.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
              _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
              _jspx_th_c_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
              // /jgossip/content/../jspf/topbar.jspf(276,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fset_005f1.setVar("jrf_url");
              int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
              if (_jspx_eval_c_005fset_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_c_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_c_005fset_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_c_005fset_005f1.doInitBody();
                }
                do {
                  //  gossip:config
                  org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f32 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
                  _jspx_th_gossip_005fconfig_005f32.setPageContext(_jspx_page_context);
                  _jspx_th_gossip_005fconfig_005f32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fset_005f1);
                  // /jgossip/content/../jspf/topbar.jspf(276,33) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_gossip_005fconfig_005f32.setKey(IConst.CONFIG.EXT_LOGON_ACTION_URL);
                  int _jspx_eval_gossip_005fconfig_005f32 = _jspx_th_gossip_005fconfig_005f32.doStartTag();
                  if (_jspx_th_gossip_005fconfig_005f32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f32);
                    return;
                  }
                  _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f32);
                  int evalDoAfterBody = _jspx_th_c_005fset_005f1.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_c_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.popBody();
                }
              }
              if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f1);
                return;
              }
              _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f1);
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t        <a class=\"control\" href=\"");
              if (_jspx_meth_c_005furl_005f1(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write('"');
              out.write('>');
              if (_jspx_meth_fmt_005fmessage_005f29(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write("</a>&nbsp;");
              if (_jspx_meth_fmt_005fmessage_005f30(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write("&nbsp;");
              //  c:set
              org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.el.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
              _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
              _jspx_th_c_005fset_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
              // /jgossip/content/../jspf/topbar.jspf(277,148) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fset_005f2.setVar("jrf_url");
              int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
              if (_jspx_eval_c_005fset_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_c_005fset_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_c_005fset_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_c_005fset_005f2.doInitBody();
                }
                do {
                  //  gossip:config
                  org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f33 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
                  _jspx_th_gossip_005fconfig_005f33.setPageContext(_jspx_page_context);
                  _jspx_th_gossip_005fconfig_005f33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fset_005f2);
                  // /jgossip/content/../jspf/topbar.jspf(277,169) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_gossip_005fconfig_005f33.setKey(IConst.CONFIG.EXT_REGISTRATION_ACTION_URL);
                  int _jspx_eval_gossip_005fconfig_005f33 = _jspx_th_gossip_005fconfig_005f33.doStartTag();
                  if (_jspx_th_gossip_005fconfig_005f33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f33);
                    return;
                  }
                  _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f33);
                  int evalDoAfterBody = _jspx_th_c_005fset_005f2.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_c_005fset_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.popBody();
                }
              }
              if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f2);
                return;
              }
              _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f2);
              out.write("<a class=\"control\" href\"");
              if (_jspx_meth_c_005furl_005f2(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write('"');
              out.write('>');
              if (_jspx_meth_fmt_005fmessage_005f31(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write("</a>\r\n");
              out.write("\t\t\t\t\t\t\t    ");
}else{
              out.write("\r\n");
              out.write("              \t\t\t\t\t    ");
              if (_jspx_meth_html_005flink_005f21(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("              \t\t\t\t\t    ");
if (IConst.VALUES.TRUE.equals(Configurator.getInstance().get(IConst.CONFIG.ENABLE_FORUM_REGISTRATION))) {
              out.write("\r\n");
              out.write("              \t\t\t\t\t        &nbsp;");
              if (_jspx_meth_fmt_005fmessage_005f33(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write("&nbsp;");

              					        if(IConst.VALUES.FALSE.equals(Configurator.getInstance().get(IConst.CONFIG.ENABLE_EMAIL_CONFIRMATION))){
              					        	pageContext.setAttribute("jrf_registration_mode","direct");
              					        }else{
              					        	pageContext.setAttribute("jrf_registration_mode","pending");
              					        }
              					        
              out.write("<a class=\"control\" href=\"");
              if (_jspx_meth_c_005furl_005f3(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write('"');
              out.write(' ');
              out.write('>');
              if (_jspx_meth_fmt_005fmessage_005f34(_jspx_th_c_005fwhen_005f2, _jspx_page_context))
                return;
              out.write("</a>\r\n");
              out.write("              \t\t\t\t\t    ");
}
              out.write(" \r\n");
              out.write("              \t\t\t\t    ");
}
              out.write("\r\n");
              out.write("              \t\t        ");
              int evalDoAfterBody = _jspx_th_c_005fwhen_005f2.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fwhen_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
            return;
          }
          _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
          out.write("\r\n");
          out.write("\t\t                    ");
          //  c:otherwise
          org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f2 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
          _jspx_th_c_005fotherwise_005f2.setPageContext(_jspx_page_context);
          _jspx_th_c_005fotherwise_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
          int _jspx_eval_c_005fotherwise_005f2 = _jspx_th_c_005fotherwise_005f2.doStartTag();
          if (_jspx_eval_c_005fotherwise_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("              \t\t\t\t     ");
if (IConst.VALUES.FALSE.equals(Configurator.getInstance().get(IConst.CONFIG.ENABLE_FORUM_SIGN_ON))) {
              out.write("\r\n");
              out.write("              \t\t\t\t     \t");
              //  c:set
              org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_005fset_005f3 = (org.apache.taglibs.standard.tag.el.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
              _jspx_th_c_005fset_005f3.setPageContext(_jspx_page_context);
              _jspx_th_c_005fset_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f2);
              // /jgossip/content/../jspf/topbar.jspf(295,24) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fset_005f3.setVar("jrf_url");
              int _jspx_eval_c_005fset_005f3 = _jspx_th_c_005fset_005f3.doStartTag();
              if (_jspx_eval_c_005fset_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_c_005fset_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_c_005fset_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_c_005fset_005f3.doInitBody();
                }
                do {
                  //  gossip:config
                  org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f34 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
                  _jspx_th_gossip_005fconfig_005f34.setPageContext(_jspx_page_context);
                  _jspx_th_gossip_005fconfig_005f34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fset_005f3);
                  // /jgossip/content/../jspf/topbar.jspf(295,45) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_gossip_005fconfig_005f34.setKey(IConst.CONFIG.EXT_LOGOUT_ACTION_URL);
                  int _jspx_eval_gossip_005fconfig_005f34 = _jspx_th_gossip_005fconfig_005f34.doStartTag();
                  if (_jspx_th_gossip_005fconfig_005f34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f34);
                    return;
                  }
                  _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f34);
                  int evalDoAfterBody = _jspx_th_c_005fset_005f3.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_c_005fset_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.popBody();
                }
              }
              if (_jspx_th_c_005fset_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f3);
                return;
              }
              _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.reuse(_jspx_th_c_005fset_005f3);
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t        <a class=\"control\" href=\"");
              if (_jspx_meth_c_005furl_005f4(_jspx_th_c_005fotherwise_005f2, _jspx_page_context))
                return;
              out.write('"');
              out.write('>');
              if (_jspx_meth_fmt_005fmessage_005f35(_jspx_th_c_005fotherwise_005f2, _jspx_page_context))
                return;
              out.write("</a>\r\n");
              out.write("\t\t\t\t\t\t\t    ");
}else{
              out.write("\r\n");
              out.write("              \t\t\t\t        ");
              if (_jspx_meth_html_005flink_005f22(_jspx_th_c_005fotherwise_005f2, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("              \t\t\t\t    ");
}
              out.write("\r\n");
              out.write("              \t\t\t\t ");
              int evalDoAfterBody = _jspx_th_c_005fotherwise_005f2.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fotherwise_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
            return;
          }
          _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
          out.write("\r\n");
          out.write("\t\t                ");
          int evalDoAfterBody = _jspx_th_c_005fchoose_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fchoose_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
      out.write("\r\n");
      out.write("              \t\t\t\t</span>\r\n");
      out.write("              \t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td  class=\"l_o\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f35 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f35.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f35.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(304,35) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f35.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f35 = _jspx_th_gossip_005fconfig_005f35.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f35);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f35);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"6\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f36 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f36.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f36.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(305,31) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f36.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f36 = _jspx_th_gossip_005fconfig_005f36.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f36);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f36);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table></td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td colspan=\"8\">\r\n");
      out.write("\t<img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f37 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f37.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f37.setParent(null);
      // /jgossip/content/../jspf/topbar.jspf(315,11) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f37.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f37 = _jspx_th_gossip_005fconfig_005f37.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f37);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f37);
      out.write("images/blank.gif\" alt=\"\" width=\"800\" height=\"1\" border=\"0\">\r\n");
      out.write("\t</td>\r\n");
      out.write("</tr>\t\t\r\n");
      out.write("</table>\r\n");
      out.write("<br><br>\r\n");
      out.write('\r');
      out.write('\n');
      //  c:if
      org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f11 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
      _jspx_th_c_005fif_005f11.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f11.setParent(null);
      // /jgossip/content/../jspf/status.jspf(24,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f11.setTest("${!empty sessionScope.JRF_STATUS_MESSAGE}");
      int _jspx_eval_c_005fif_005f11 = _jspx_th_c_005fif_005f11.doStartTag();
      if (_jspx_eval_c_005fif_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t<table cellspacing=\"0\" cellpadding=\"0\">\r\n");
          out.write("\t\t<tr>\r\n");
          out.write("\t\t\t<td >\t&nbsp;&nbsp;\r\n");
          out.write("\t\t\t</td>\r\n");
          out.write("\t\t\t<td class=\"top_tab\" nowrap>\t&nbsp;&nbsp;");
          if (_jspx_meth_fmt_005fmessage_005f37(_jspx_th_c_005fif_005f11, _jspx_page_context))
            return;
          out.write("&nbsp;&nbsp;\r\n");
          out.write("\t\t\t</td>\r\n");
          out.write("\t\t\t<td width=\"70%\" >&nbsp;&nbsp;<strong>");
          if (_jspx_meth_c_005fout_005f4(_jspx_th_c_005fif_005f11, _jspx_page_context))
            return;
          out.write("</strong>\r\n");
          out.write("\t\t\t");
session.removeAttribute(IConst.SESSION.STATUS_MESSAGE);
          out.write("\r\n");
          out.write("\t\t\t</td>\r\n");
          out.write("\t\t</tr>\r\n");
          out.write("\t\t<tr>\r\n");
          out.write("\t\t\t<td class=\"t_o\" height=\"8\"><img src=\"");
          //  gossip:config
          org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f38 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
          _jspx_th_gossip_005fconfig_005f38.setPageContext(_jspx_page_context);
          _jspx_th_gossip_005fconfig_005f38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f11);
          // /jgossip/content/../jspf/status.jspf(37,40) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_gossip_005fconfig_005f38.setKey(IConst.CONFIG.WEB_ROOT);
          int _jspx_eval_gossip_005fconfig_005f38 = _jspx_th_gossip_005fconfig_005f38.doStartTag();
          if (_jspx_th_gossip_005fconfig_005f38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f38);
            return;
          }
          _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f38);
          out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
          out.write("\t\t\t<td class=\"lr_g_t_o\"><img src=\"");
          //  gossip:config
          org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f39 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
          _jspx_th_gossip_005fconfig_005f39.setPageContext(_jspx_page_context);
          _jspx_th_gossip_005fconfig_005f39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f11);
          // /jgossip/content/../jspf/status.jspf(38,34) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_gossip_005fconfig_005f39.setKey(IConst.CONFIG.WEB_ROOT);
          int _jspx_eval_gossip_005fconfig_005f39 = _jspx_th_gossip_005fconfig_005f39.doStartTag();
          if (_jspx_th_gossip_005fconfig_005f39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f39);
            return;
          }
          _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f39);
          out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
          out.write("\t\t\t<td class=\"t_o\" ><img src=\"");
          //  gossip:config
          org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f40 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
          _jspx_th_gossip_005fconfig_005f40.setPageContext(_jspx_page_context);
          _jspx_th_gossip_005fconfig_005f40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f11);
          // /jgossip/content/../jspf/status.jspf(39,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_gossip_005fconfig_005f40.setKey(IConst.CONFIG.WEB_ROOT);
          int _jspx_eval_gossip_005fconfig_005f40 = _jspx_th_gossip_005fconfig_005f40.doStartTag();
          if (_jspx_th_gossip_005fconfig_005f40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f40);
            return;
          }
          _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f40);
          out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
          out.write("\t\t</tr>\r\n");
          out.write("\t</table>\r\n");
          out.write("\t<br>\r\n");
          out.write("\t<br>\r\n");
          int evalDoAfterBody = _jspx_th_c_005fif_005f11.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f11);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f11);
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"jrf_err\" style=\"position:relative;");
      if (_jspx_meth_logic_005fmessagesNotPresent_005f0(_jspx_page_context))
        return;
      out.write("\">\r\n");
      out.write("\t<table cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td >\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"top_tab\" nowrap>\t&nbsp;&nbsp;");
      if (_jspx_meth_fmt_005fmessage_005f38(_jspx_page_context))
        return;
      out.write("&nbsp;&nbsp;\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td width=\"70%\" >&nbsp;&nbsp;<strong>");
      if (_jspx_meth_fmt_005fmessage_005f39(_jspx_page_context))
        return;
      out.write("</strong>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"t_o\" height=\"8\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f41 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f41.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f41.setParent(null);
      // /jgossip/content/../jspf/status.jspf(57,40) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f41.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f41 = _jspx_th_gossip_005fconfig_005f41.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f41);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f41);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t<td class=\"lr_g_t_o\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f42 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f42.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f42.setParent(null);
      // /jgossip/content/../jspf/status.jspf(58,34) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f42.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f42 = _jspx_th_gossip_005fconfig_005f42.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f42);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f42);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t<td class=\"t_o\" ><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f43 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f43.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f43.setParent(null);
      // /jgossip/content/../jspf/status.jspf(59,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f43.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f43 = _jspx_th_gossip_005fconfig_005f43.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f43);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f43);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=\"2\" align=\"center\" valign=\"middle\">\t\r\n");
      out.write("\t\t\t\t<img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f44 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f44.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f44.setParent(null);
      // /jgossip/content/../jspf/status.jspf(63,14) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f44.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f44 = _jspx_th_gossip_005fconfig_005f44.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f44);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f44);
      out.write("images/excl.gif\" alt=\"\" width=\"24\" height=\"35\" border=\"0\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td width=\"70%\" nowrap>\r\n");
      out.write("\t\t\t<span id=\"jrf_err_mess\">\r\n");
      out.write("\t\t\t");
      //  logic:messagesPresent
      org.apache.struts.taglib.logic.MessagesPresentTag _jspx_th_logic_005fmessagesPresent_005f0 = (org.apache.struts.taglib.logic.MessagesPresentTag) _005fjspx_005ftagPool_005flogic_005fmessagesPresent.get(org.apache.struts.taglib.logic.MessagesPresentTag.class);
      _jspx_th_logic_005fmessagesPresent_005f0.setPageContext(_jspx_page_context);
      _jspx_th_logic_005fmessagesPresent_005f0.setParent(null);
      int _jspx_eval_logic_005fmessagesPresent_005f0 = _jspx_th_logic_005fmessagesPresent_005f0.doStartTag();
      if (_jspx_eval_logic_005fmessagesPresent_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t    ");
          if (_jspx_meth_c_005fset_005f4(_jspx_th_logic_005fmessagesPresent_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t");
          //  html:messages
          org.apache.struts.taglib.html.MessagesTag _jspx_th_html_005fmessages_005f0 = (org.apache.struts.taglib.html.MessagesTag) _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.get(org.apache.struts.taglib.html.MessagesTag.class);
          _jspx_th_html_005fmessages_005f0.setPageContext(_jspx_page_context);
          _jspx_th_html_005fmessages_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_005fmessagesPresent_005f0);
          // /jgossip/content/../jspf/status.jspf(69,4) name = id type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_html_005fmessages_005f0.setId("msg");
          int _jspx_eval_html_005fmessages_005f0 = _jspx_th_html_005fmessages_005f0.doStartTag();
          if (_jspx_eval_html_005fmessages_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.String msg = null;
            if (_jspx_eval_html_005fmessages_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_html_005fmessages_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_html_005fmessages_005f0.doInitBody();
            }
            msg = (java.lang.String) _jspx_page_context.findAttribute("msg");
            do {
              out.write("\r\n");
              out.write("      \t\t\t\t&middot; ");
              if (_jspx_meth_c_005fout_005f5(_jspx_th_html_005fmessages_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("    \t\t\t<br/>\r\n");
              out.write("  \t\t\t\t");
              int evalDoAfterBody = _jspx_th_html_005fmessages_005f0.doAfterBody();
              msg = (java.lang.String) _jspx_page_context.findAttribute("msg");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_html_005fmessages_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_html_005fmessages_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.reuse(_jspx_th_html_005fmessages_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fhtml_005fmessages_0026_005fid.reuse(_jspx_th_html_005fmessages_005f0);
          out.write("\r\n");
          out.write("  \t\t\t");
          int evalDoAfterBody = _jspx_th_logic_005fmessagesPresent_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_logic_005fmessagesPresent_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005flogic_005fmessagesPresent.reuse(_jspx_th_logic_005fmessagesPresent_005f0);
        return;
      }
      _005fjspx_005ftagPool_005flogic_005fmessagesPresent.reuse(_jspx_th_logic_005fmessagesPresent_005f0);
      out.write("\r\n");
      out.write("  \t\t\t</span>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("\t<br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write(" <table width=\"98%\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td width=\"70\">\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"top_tab\" nowrap>\t&nbsp;&nbsp;<span class=\"caption\">");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f45 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f45.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f45.setParent(null);
      // /jgossip/content/MainPage.jsp(34,65) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f45.setKey(IConst.CONFIG.SITE_NAME);
      int _jspx_eval_gossip_005fconfig_005f45 = _jspx_th_gossip_005fconfig_005f45.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f45);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f45);
      out.write("</span>&nbsp;&nbsp;\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td width=\"50%\" colspan=\"3\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td align=\"right\">\r\n");
      out.write("\t\t\t\t");
      //  html:link
      org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f23 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005ftitleKey_005ftarget_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
      _jspx_th_html_005flink_005f23.setPageContext(_jspx_page_context);
      _jspx_th_html_005flink_005f23.setParent(null);
      // /jgossip/content/MainPage.jsp(39,4) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005flink_005f23.setAction("RSSLastTopics");
      // /jgossip/content/MainPage.jsp(39,4) name = titleKey type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005flink_005f23.setTitleKey("global.RSS");
      // /jgossip/content/MainPage.jsp(39,4) name = target type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_html_005flink_005f23.setTarget("_blank");
      int _jspx_eval_html_005flink_005f23 = _jspx_th_html_005flink_005f23.doStartTag();
      if (_jspx_eval_html_005flink_005f23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_html_005flink_005f23 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_html_005flink_005f23.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_html_005flink_005f23.doInitBody();
        }
        do {
          out.write("<img src=\"");
          //  gossip:config
          org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f46 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
          _jspx_th_gossip_005fconfig_005f46.setPageContext(_jspx_page_context);
          _jspx_th_gossip_005fconfig_005f46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f23);
          // /jgossip/content/MainPage.jsp(39,86) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_gossip_005fconfig_005f46.setKey(IConst.CONFIG.WEB_ROOT);
          int _jspx_eval_gossip_005fconfig_005f46 = _jspx_th_gossip_005fconfig_005f46.doStartTag();
          if (_jspx_th_gossip_005fconfig_005f46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f46);
            return;
          }
          _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f46);
          out.write("images/xml.gif\" alt=\"\" width=\"36\" height=\"14\" border=\"0\">");
          int evalDoAfterBody = _jspx_th_html_005flink_005f23.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_html_005flink_005f23 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_html_005flink_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fhtml_005flink_0026_005ftitleKey_005ftarget_005faction.reuse(_jspx_th_html_005flink_005f23);
        return;
      }
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005ftitleKey_005ftarget_005faction.reuse(_jspx_th_html_005flink_005f23);
      out.write("\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"tb_o\" height=\"4\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f47 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f47.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f47.setParent(null);
      // /jgossip/content/MainPage.jsp(43,41) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f47.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f47 = _jspx_th_gossip_005fconfig_005f47.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f47);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f47);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t<td class=\"lr_g_tb_o\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f48 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f48.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f48.setParent(null);
      // /jgossip/content/MainPage.jsp(44,35) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f48.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f48 = _jspx_th_gossip_005fconfig_005f48.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f48);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f48);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t<td class=\"tb_o\" colspan=\"4\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f49 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f49.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f49.setParent(null);
      // /jgossip/content/MainPage.jsp(45,42) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f49.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f49 = _jspx_th_gossip_005fconfig_005f49.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f49);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f49);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td height=\"8\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f50 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f50.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f50.setParent(null);
      // /jgossip/content/MainPage.jsp(48,28) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f50.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f50 = _jspx_th_gossip_005fconfig_005f50.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f50);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f50);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t<td class=\"lr_g\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f51 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f51.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f51.setParent(null);
      // /jgossip/content/MainPage.jsp(49,30) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f51.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f51 = _jspx_th_gossip_005fconfig_005f51.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f51);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f51);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t\t<td class=\"b_g\" colspan=\"4\"><img src=\"");
      //  gossip:config
      org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f52 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
      _jspx_th_gossip_005fconfig_005f52.setPageContext(_jspx_page_context);
      _jspx_th_gossip_005fconfig_005f52.setParent(null);
      // /jgossip/content/MainPage.jsp(50,41) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_gossip_005fconfig_005f52.setKey(IConst.CONFIG.WEB_ROOT);
      int _jspx_eval_gossip_005fconfig_005f52 = _jspx_th_gossip_005fconfig_005f52.doStartTag();
      if (_jspx_th_gossip_005fconfig_005f52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f52);
        return;
      }
      _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f52);
      out.write("images/blank.gif\" alt=\"\" width=\"1\" height=\"1\" border=\"0\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"icn\">\r\n");
      out.write("\t\t\t\t<span class=\"c_title\">&nbsp;");
      if (_jspx_meth_fmt_005fmessage_005f40(_jspx_page_context))
        return;
      out.write("&nbsp;</span>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"l_g\">\t\r\n");
      out.write("\t\t\t\t<span class=\"c_title\">&nbsp;&nbsp;&nbsp;&nbsp;");
      if (_jspx_meth_fmt_005fmessage_005f41(_jspx_page_context))
        return;
      out.write("&nbsp;</span>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td width=\"60%\">\t&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"l_g\" align=\"center\">\r\n");
      out.write("\t\t\t\t<span class=\"c_title\">&nbsp;");
      if (_jspx_meth_fmt_005fmessage_005f42(_jspx_page_context))
        return;
      out.write("&nbsp;</span>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"l_g\" align=\"center\">\r\n");
      out.write("\t\t\t\t<span class=\"c_title\">&nbsp;");
      if (_jspx_meth_fmt_005fmessage_005f43(_jspx_page_context))
        return;
      out.write("&nbsp;</span>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"lr_g\" align=\"center\">\r\n");
      out.write("\t\t\t\t<span class=\"c_title\">&nbsp;");
      if (_jspx_meth_fmt_005fmessage_005f44(_jspx_page_context))
        return;
      out.write("&nbsp;</span>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("  ");
      //  c:choose
      org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f3 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
      _jspx_th_c_005fchoose_005f3.setPageContext(_jspx_page_context);
      _jspx_th_c_005fchoose_005f3.setParent(null);
      int _jspx_eval_c_005fchoose_005f3 = _jspx_th_c_005fchoose_005f3.doStartTag();
      if (_jspx_eval_c_005fchoose_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\t\t\r\n");
          out.write("\t");
          //  c:when
          org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_005fwhen_005f3 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
          _jspx_th_c_005fwhen_005f3.setPageContext(_jspx_page_context);
          _jspx_th_c_005fwhen_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f3);
          // /jgossip/content/MainPage.jsp(72,1) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fwhen_005f3.setTest("${!empty requestScope.JRF_GROUPS}");
          int _jspx_eval_c_005fwhen_005f3 = _jspx_th_c_005fwhen_005f3.doStartTag();
          if (_jspx_eval_c_005fwhen_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("\t\t");
              //  c:forEach
              org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
              _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
              _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f3);
              // /jgossip/content/MainPage.jsp(73,2) name = items type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fforEach_005f1.setItems("${requestScope.JRF_GROUPS}");
              // /jgossip/content/MainPage.jsp(73,2) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fforEach_005f1.setVar("group");
              int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
              try {
                int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
                if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write(" \r\n");
                    out.write("\t\t<tr class=\"lght\">\r\n");
                    out.write("\t\t\t<td class=\"t_g\" colspan=\"6\">\r\n");
                    out.write("\t\t\t\t<br><span class=\"caption\">&nbsp;");
                    if (_jspx_meth_c_005fout_005f6(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
                      return;
                    out.write("<br></span>\r\n");
                    out.write("\t\t\t</td>\r\n");
                    out.write("\t\t</tr>\r\n");
                    out.write("\t\t   ");
                    //  c:choose
                    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f4 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
                    _jspx_th_c_005fchoose_005f4.setPageContext(_jspx_page_context);
                    _jspx_th_c_005fchoose_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
                    int _jspx_eval_c_005fchoose_005f4 = _jspx_th_c_005fchoose_005f4.doStartTag();
                    if (_jspx_eval_c_005fchoose_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      do {
                        out.write("\t\t\r\n");
                        out.write("\t        ");
                        //  c:when
                        org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_005fwhen_005f4 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
                        _jspx_th_c_005fwhen_005f4.setPageContext(_jspx_page_context);
                        _jspx_th_c_005fwhen_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f4);
                        // /jgossip/content/MainPage.jsp(80,9) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                        _jspx_th_c_005fwhen_005f4.setTest("${!empty group.forums}");
                        int _jspx_eval_c_005fwhen_005f4 = _jspx_th_c_005fwhen_005f4.doStartTag();
                        if (_jspx_eval_c_005fwhen_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          do {
                            out.write("\r\n");
                            out.write("      \t\t");
                            //  c:forEach
                            org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
                            _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
                            _jspx_th_c_005fforEach_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f4);
                            // /jgossip/content/MainPage.jsp(81,8) name = items type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                            _jspx_th_c_005fforEach_005f2.setItems("${group.forums}");
                            // /jgossip/content/MainPage.jsp(81,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                            _jspx_th_c_005fforEach_005f2.setVar("forum");
                            // /jgossip/content/MainPage.jsp(81,8) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                            _jspx_th_c_005fforEach_005f2.setVarStatus("status");
                            int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
                            try {
                              int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
                              if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              do {
                              out.write("\r\n");
                              out.write("\t\t\t\t");
                              if (_jspx_meth_c_005fset_005f5(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("\r\n");
                              out.write("\t\t<tr class=\"strip");
                              if (_jspx_meth_c_005fout_005f7(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("\">\r\n");
                              out.write("\t\t\t<td class=\"lt_g_txt_b\" align=\"center\">\r\n");
                              out.write("\t\t\t");
                              out.write("\r\n");
                              out.write("                \t");
String icon="off";
                              out.write("\r\n");
                              out.write("                \t");
                              //  c:if
                              org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f12 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
                              _jspx_th_c_005fif_005f12.setPageContext(_jspx_page_context);
                              _jspx_th_c_005fif_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
                              // /jgossip/content/MainPage.jsp(87,17) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                              _jspx_th_c_005fif_005f12.setTest("${forum.threadsCount>0}");
                              int _jspx_eval_c_005fif_005f12 = _jspx_th_c_005fif_005f12.doStartTag();
                              if (_jspx_eval_c_005fif_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              do {
                              out.write("    \t\t\r\n");
                              out.write("                \t\t");
                              //  gossip:hasNewMess
                              org.jresearch.gossip.tags.LastVisitTag _jspx_th_gossip_005fhasNewMess_005f0 = (org.jresearch.gossip.tags.LastVisitTag) _005fjspx_005ftagPool_005fgossip_005fhasNewMess_0026_005ftid_005fsender_005fintime.get(org.jresearch.gossip.tags.LastVisitTag.class);
                              _jspx_th_gossip_005fhasNewMess_005f0.setPageContext(_jspx_page_context);
                              _jspx_th_gossip_005fhasNewMess_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f12);
                              // /jgossip/content/MainPage.jsp(88,18) name = intime type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                              _jspx_th_gossip_005fhasNewMess_005f0.setIntime("${lastmessage.intime}");
                              // /jgossip/content/MainPage.jsp(88,18) name = tid type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                              _jspx_th_gossip_005fhasNewMess_005f0.setTid("${forum.lastMessThreadId}");
                              // /jgossip/content/MainPage.jsp(88,18) name = sender type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                              _jspx_th_gossip_005fhasNewMess_005f0.setSender("${lastmessage.sender}");
                              int _jspx_eval_gossip_005fhasNewMess_005f0 = _jspx_th_gossip_005fhasNewMess_005f0.doStartTag();
                              if (_jspx_eval_gossip_005fhasNewMess_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              do {
                              out.write("\r\n");
                              out.write("                \t\t\t");
icon="on";
                              out.write("\r\n");
                              out.write("                \t\t");
                              int evalDoAfterBody = _jspx_th_gossip_005fhasNewMess_005f0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              }
                              if (_jspx_th_gossip_005fhasNewMess_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _005fjspx_005ftagPool_005fgossip_005fhasNewMess_0026_005ftid_005fsender_005fintime.reuse(_jspx_th_gossip_005fhasNewMess_005f0);
                              return;
                              }
                              _005fjspx_005ftagPool_005fgossip_005fhasNewMess_0026_005ftid_005fsender_005fintime.reuse(_jspx_th_gossip_005fhasNewMess_005f0);
                              out.write("\r\n");
                              out.write("                \t");
                              int evalDoAfterBody = _jspx_th_c_005fif_005f12.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              }
                              if (_jspx_th_c_005fif_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f12);
                              return;
                              }
                              _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f12);
                              out.write("\r\n");
                              out.write("                 <img src=\"");
                              //  gossip:config
                              org.jresearch.gossip.tags.ConfiguratorTag _jspx_th_gossip_005fconfig_005f53 = (org.jresearch.gossip.tags.ConfiguratorTag) _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.get(org.jresearch.gossip.tags.ConfiguratorTag.class);
                              _jspx_th_gossip_005fconfig_005f53.setPageContext(_jspx_page_context);
                              _jspx_th_gossip_005fconfig_005f53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
                              // /jgossip/content/MainPage.jsp(92,27) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                              _jspx_th_gossip_005fconfig_005f53.setKey(IConst.CONFIG.WEB_ROOT);
                              int _jspx_eval_gossip_005fconfig_005f53 = _jspx_th_gossip_005fconfig_005f53.doStartTag();
                              if (_jspx_th_gossip_005fconfig_005f53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f53);
                              return;
                              }
                              _005fjspx_005ftagPool_005fgossip_005fconfig_0026_005fkey_005fnobody.reuse(_jspx_th_gossip_005fconfig_005f53);
                              out.write("images/");
                              out.print(icon);
                              out.write(".gif\" width=\"25\" height=\"25\" border=\"0\">\r\n");
                              out.write("\t\t\t</td>\r\n");
                              out.write("\t\t\t<td class=\"lt_g_txt_b\" colspan=\"2\"><a href=\"");
                              if (_jspx_meth_c_005furl_005f5(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("\" class=\"forum_name\">");
                              if (_jspx_meth_c_005fout_005f8(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("</a><br>\r\n");
                              out.write("                  <span class=\"txt\">");
                              if (_jspx_meth_c_005fout_005f9(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("</span>\r\n");
                              out.write("\t\t\t</td>\r\n");
                              out.write("\t\t\t<td class=\"lt_g_txt_b\" align=\"center\">");
                              if (_jspx_meth_c_005fout_005f10(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("\r\n");
                              out.write("\t\t\t</td>\r\n");
                              out.write("\t\t\t<td class=\"lt_g_txt_b\" align=\"center\">");
                              if (_jspx_meth_c_005fout_005f11(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("\r\n");
                              out.write("\t\t\t</td>\r\n");
                              out.write("\t\t\t<td class=\"lrt_g_txt_b\" nowrap> \r\n");
                              out.write("              ");
                              if (_jspx_meth_c_005fchoose_005f5(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                              return;
                              out.write("\r\n");
                              out.write("                 \r\n");
                              out.write("\t\t\t</td>\r\n");
                              out.write("\t\t</tr>\r\n");
                              out.write("\t\t   ");
                              int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                              } while (true);
                              }
                              if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              return;
                              }
                            } catch (Throwable _jspx_exception) {
                              while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
                              out = _jspx_page_context.popBody();
                              _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
                            } finally {
                              _jspx_th_c_005fforEach_005f2.doFinally();
                              _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f2);
                            }
                            out.write("\r\n");
                            out.write("\t\t   ");
                            int evalDoAfterBody = _jspx_th_c_005fwhen_005f4.doAfterBody();
                            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                          } while (true);
                        }
                        if (_jspx_th_c_005fwhen_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f4);
                          return;
                        }
                        _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f4);
                        out.write("\r\n");
                        out.write("\t       ");
                        if (_jspx_meth_c_005fotherwise_005f4(_jspx_th_c_005fchoose_005f4, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
                          return;
                        out.write("\r\n");
                        out.write("        ");
                        int evalDoAfterBody = _jspx_th_c_005fchoose_005f4.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                    }
                    if (_jspx_th_c_005fchoose_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f4);
                      return;
                    }
                    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f4);
                    out.write("\r\n");
                    out.write("\t\t");
                    int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  return;
                }
              } catch (Throwable _jspx_exception) {
                while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
                  out = _jspx_page_context.popBody();
                _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
              } finally {
                _jspx_th_c_005fforEach_005f1.doFinally();
                _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
              }
              out.write('\r');
              out.write('\n');
              out.write('	');
              int evalDoAfterBody = _jspx_th_c_005fwhen_005f3.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fwhen_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
            return;
          }
          _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
          out.write('\r');
          out.write('\n');
          out.write('	');
          if (_jspx_meth_c_005fotherwise_005f5(_jspx_th_c_005fchoose_005f3, _jspx_page_context))
            return;
          out.write('\r');
          out.write('\n');
          int evalDoAfterBody = _jspx_th_c_005fchoose_005f3.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fchoose_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f3);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f3);
      out.write("\r\n");
      out.write("\t\t<tr class=\"lght\">\r\n");
      out.write("\t\t\t<td class=\"t_g\" colspan=\"6\">\t&nbsp;\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table> \r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("<table width=\"98%\">\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t    ");
      if (_jspx_meth_c_005fif_005f17(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t<td align=\"right\">\r\n");
      out.write("\t\t");
      if (_jspx_meth_c_005fchoose_005f6(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_fmt_005fsetLocale_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:setLocale
    org.apache.taglibs.standard.tag.el.fmt.SetLocaleTag _jspx_th_fmt_005fsetLocale_005f0 = (org.apache.taglibs.standard.tag.el.fmt.SetLocaleTag) _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.SetLocaleTag.class);
    _jspx_th_fmt_005fsetLocale_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fsetLocale_005f0.setParent(null);
    // /jgossip/content/../jspf/jsp_header.jspf(36,0) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetLocale_005f0.setValue("${sessionScope[localeKey]}");
    int _jspx_eval_fmt_005fsetLocale_005f0 = _jspx_th_fmt_005fsetLocale_005f0.doStartTag();
    if (_jspx_th_fmt_005fsetLocale_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody.reuse(_jspx_th_fmt_005fsetLocale_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody.reuse(_jspx_th_fmt_005fsetLocale_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fsetBundle_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:setBundle
    org.apache.taglibs.standard.tag.el.fmt.SetBundleTag _jspx_th_fmt_005fsetBundle_005f0 = (org.apache.taglibs.standard.tag.el.fmt.SetBundleTag) _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.SetBundleTag.class);
    _jspx_th_fmt_005fsetBundle_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fsetBundle_005f0.setParent(null);
    // /jgossip/content/../jspf/jsp_header.jspf(37,0) name = basename type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetBundle_005f0.setBasename("org.jresearch.gossip.resources.lang.lang");
    // /jgossip/content/../jspf/jsp_header.jspf(37,0) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetBundle_005f0.setScope("session");
    int _jspx_eval_fmt_005fsetBundle_005f0 = _jspx_th_fmt_005fsetBundle_005f0.doStartTag();
    if (_jspx_th_fmt_005fsetBundle_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody.reuse(_jspx_th_fmt_005fsetBundle_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody.reuse(_jspx_th_fmt_005fsetBundle_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fsetTimeZone_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:setTimeZone
    org.apache.taglibs.standard.tag.el.fmt.SetTimeZoneTag _jspx_th_fmt_005fsetTimeZone_005f0 = (org.apache.taglibs.standard.tag.el.fmt.SetTimeZoneTag) _005fjspx_005ftagPool_005ffmt_005fsetTimeZone_0026_005fvalue_005fscope_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.SetTimeZoneTag.class);
    _jspx_th_fmt_005fsetTimeZone_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fsetTimeZone_005f0.setParent(null);
    // /jgossip/content/MainPage.jsp(24,0) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetTimeZone_005f0.setValue("${sessionScope.JRF_TIME_ZONE}");
    // /jgossip/content/MainPage.jsp(24,0) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetTimeZone_005f0.setScope("session");
    int _jspx_eval_fmt_005fsetTimeZone_005f0 = _jspx_th_fmt_005fsetTimeZone_005f0.doStartTag();
    if (_jspx_th_fmt_005fsetTimeZone_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fsetTimeZone_0026_005fvalue_005fscope_005fnobody.reuse(_jspx_th_fmt_005fsetTimeZone_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fsetTimeZone_0026_005fvalue_005fscope_005fnobody.reuse(_jspx_th_fmt_005fsetTimeZone_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(33,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest("${sessionScope.JRF_USER.status>8}");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("function changeMenu(){\r\n");
        out.write("    var amObj=document.getElementById(\"admin_menu\");\r\n");
        out.write("    var fmObj=document.getElementById(\"forum_menu\");\r\n");
        out.write("    var amDsp=amObj.style.display;\r\n");
        out.write("    var fmDsp=fmObj.style.display;\r\n");
        out.write("    amObj.style.display=fmDsp;\r\n");
        out.write("    fmObj.style.display=amDsp;\r\n");
        out.write("}      \r\n");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(58,5) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest("${!empty JRF_TITLE_NAV_BAR}");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_c_005fforEach_005f0(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write(" \r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.el.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.el.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.el.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /jgossip/content/../jspf/topbar.jspf(59,6) name = items type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems("${JRF_TITLE_NAV_BAR}");
    // /jgossip/content/../jspf/topbar.jspf(59,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("currNavEl");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t&nbsp;&raquo;&nbsp;\r\n");
          out.write("\t\t\t\t\t\t\t");
          if (_jspx_meth_c_005fif_005f2(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t");
          if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t");
          if (_jspx_meth_c_005fif_005f3(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t<script>\r\n");
          out.write("\t\t\t\t\t\t\tself.document.title+=unQuote(\" > ");
          if (_jspx_meth_c_005fout_005f2(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\");\r\n");
          out.write("\t\t\t\t\t\t\t</script>\r\n");
          out.write("\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /jgossip/content/../jspf/topbar.jspf(61,7) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest("${currNavEl.link!='N'}");
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t<a href=\"");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    // /jgossip/content/../jspf/topbar.jspf(62,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue("${currNavEl.link}");
    // /jgossip/content/../jspf/topbar.jspf(62,17) name = escapeXml type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setEscapeXml("false");
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /jgossip/content/../jspf/topbar.jspf(64,7) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue("${currNavEl.title}");
    // /jgossip/content/../jspf/topbar.jspf(64,7) name = escapeXml type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setEscapeXml("false");
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /jgossip/content/../jspf/topbar.jspf(65,7) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest("${currNavEl.link!='N'}");
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t</a>\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /jgossip/content/../jspf/topbar.jspf(69,40) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue("${currNavEl.title}");
    // /jgossip/content/../jspf/topbar.jspf(69,40) name = escapeXml type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setEscapeXml("false");
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(119,42) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest("${!empty requestScope.showAdminMenu}");
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t    \t                                     style=\"display:none;\"\r\n");
        out.write("\t\t\t    \t                                  ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f1 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f1.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(122,12) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f1.setAction("showSearch");
    int _jspx_eval_html_005flink_005f1 = _jspx_th_html_005flink_005f1.doStartTag();
    if (_jspx_eval_html_005flink_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f1.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f0(_jspx_th_html_005flink_005f1, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f0 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f1);
    // /jgossip/content/../jspf/topbar.jspf(122,43) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f0.setKey("forum.SEARCH");
    int _jspx_eval_fmt_005fmessage_005f0 = _jspx_th_fmt_005fmessage_005f0.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f2 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f2.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(123,30) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f2.setAction("showTipsAndTricks");
    int _jspx_eval_html_005flink_005f2 = _jspx_th_html_005flink_005f2.doStartTag();
    if (_jspx_eval_html_005flink_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f2.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f1(_jspx_th_html_005flink_005f2, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f2);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f1 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f2);
    // /jgossip/content/../jspf/topbar.jspf(123,68) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f1.setKey("global.TRICKS");
    int _jspx_eval_fmt_005fmessage_005f1 = _jspx_th_fmt_005fmessage_005f1.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent(null);
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t\t\r\n");
        out.write("\t                      ");
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                  ");
        if (_jspx_meth_c_005fotherwise_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                ");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /jgossip/content/../jspf/topbar.jspf(126,23) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest("${sessionScope.JRF_USER.status==0}");
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("            \r\n");
        out.write("              \t\t\t        ");
        if (_jspx_meth_html_005flink_005f3(_jspx_th_c_005fwhen_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("              \t\t\t        <br>\r\n");
        out.write("              \t\t      ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f3 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f3.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f0);
    // /jgossip/content/../jspf/topbar.jspf(127,25) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f3.setAction("showUnsubscribe");
    int _jspx_eval_html_005flink_005f3 = _jspx_th_html_005flink_005f3.doStartTag();
    if (_jspx_eval_html_005flink_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f3.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f2(_jspx_th_html_005flink_005f3, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f3);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f2 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f3);
    // /jgossip/content/../jspf/topbar.jspf(127,61) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f2.setKey("mails.UNSUBSCR");
    int _jspx_eval_fmt_005fmessage_005f2 = _jspx_th_fmt_005fmessage_005f2.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("              \t\t        ");
        if (_jspx_meth_c_005fif_005f5(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("              \t\t        ");
        if (_jspx_meth_html_005flink_005f4(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                            <br>\r\n");
        out.write("                            ");
        if (_jspx_meth_html_005flink_005f5(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("<b> | </b>");
        if (_jspx_meth_html_005flink_005f6(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                            <br><b>");
        if (_jspx_meth_html_005flink_005f7(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("</b>\r\n");
        out.write("\t\t\t                ");
        if (_jspx_meth_c_005fif_005f7(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                          ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /jgossip/content/../jspf/topbar.jspf(131,24) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f5.setTest("${!empty requestScope.JRF_CURR_THREAD}");
    int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
    if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t\r\n");
        out.write("\t\t\t\t\t            <a href=\"");
        if (_jspx_meth_c_005furl_005f0(_jspx_th_c_005fif_005f5, _jspx_page_context))
          return true;
        out.write('"');
        out.write('>');
        if (_jspx_meth_fmt_005fmessage_005f3(_jspx_th_c_005fif_005f5, _jspx_page_context))
          return true;
        out.write("</a><b> | </b>\r\n");
        out.write("\t\t\t\t            ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f5);
    // /jgossip/content/../jspf/topbar.jspf(132,26) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("Subscribe.do");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_eval_c_005furl_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_c_005furl_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_c_005furl_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t                       ");
        if (_jspx_meth_c_005fparam_005f0(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                       ");
        if (_jspx_meth_c_005fparam_005f1(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                       ");
        if (_jspx_meth_c_005fif_005f6(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                                  ");
        int evalDoAfterBody = _jspx_th_c_005furl_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f0 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /jgossip/content/../jspf/topbar.jspf(133,26) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f0.setName("tid");
    // /jgossip/content/../jspf/topbar.jspf(133,26) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f0.setValue("${param.tid}");
    int _jspx_eval_c_005fparam_005f0 = _jspx_th_c_005fparam_005f0.doStartTag();
    if (_jspx_th_c_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f1 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /jgossip/content/../jspf/topbar.jspf(134,26) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f1.setName("fid");
    // /jgossip/content/../jspf/topbar.jspf(134,26) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f1.setValue("${param.fid}");
    int _jspx_eval_c_005fparam_005f1 = _jspx_th_c_005fparam_005f1.doStartTag();
    if (_jspx_th_c_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f6 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /jgossip/content/../jspf/topbar.jspf(135,26) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f6.setTest("${!empty param.block}");
    int _jspx_eval_c_005fif_005f6 = _jspx_th_c_005fif_005f6.doStartTag();
    if (_jspx_eval_c_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t                           ");
        if (_jspx_meth_c_005fparam_005f2(_jspx_th_c_005fif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                       ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f2 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f6);
    // /jgossip/content/../jspf/topbar.jspf(136,30) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f2.setName("block");
    // /jgossip/content/../jspf/topbar.jspf(136,30) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f2.setValue("${param.block}");
    int _jspx_eval_c_005fparam_005f2 = _jspx_th_c_005fparam_005f2.doStartTag();
    if (_jspx_th_c_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f2);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f3 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f5);
    // /jgossip/content/../jspf/topbar.jspf(138,44) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f3.setKey("forum.NEW_SUBSC");
    int _jspx_eval_fmt_005fmessage_005f3 = _jspx_th_fmt_005fmessage_005f3.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f3);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f4 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f4.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /jgossip/content/../jspf/topbar.jspf(140,24) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f4.setAction("ShowSubscriptions");
    int _jspx_eval_html_005flink_005f4 = _jspx_th_html_005flink_005f4.doStartTag();
    if (_jspx_eval_html_005flink_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f4.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f4(_jspx_th_html_005flink_005f4, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f4);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f4 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f4.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f4);
    // /jgossip/content/../jspf/topbar.jspf(140,62) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f4.setKey("mails.SUBSCR");
    int _jspx_eval_fmt_005fmessage_005f4 = _jspx_th_fmt_005fmessage_005f4.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f4);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f5 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f5.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /jgossip/content/../jspf/topbar.jspf(142,28) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f5.setAction("EditProfile");
    int _jspx_eval_html_005flink_005f5 = _jspx_th_html_005flink_005f5.doStartTag();
    if (_jspx_eval_html_005flink_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f5.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f5(_jspx_th_html_005flink_005f5, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f5);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f5 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f5.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f5);
    // /jgossip/content/../jspf/topbar.jspf(142,60) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f5.setKey("forum.Y_INFO");
    int _jspx_eval_fmt_005fmessage_005f5 = _jspx_th_fmt_005fmessage_005f5.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f5);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f6 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f6.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /jgossip/content/../jspf/topbar.jspf(142,115) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f6.setAction("showChangePassword");
    int _jspx_eval_html_005flink_005f6 = _jspx_th_html_005flink_005f6.doStartTag();
    if (_jspx_eval_html_005flink_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f6.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f6(_jspx_th_html_005flink_005f6, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f6);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f6 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f6.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f6);
    // /jgossip/content/../jspf/topbar.jspf(142,154) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f6.setKey("forum.Y_PASS");
    int _jspx_eval_fmt_005fmessage_005f6 = _jspx_th_fmt_005fmessage_005f6.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f6);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f7 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f7.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /jgossip/content/../jspf/topbar.jspf(143,35) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f7.setAction("ShowNewTopics");
    int _jspx_eval_html_005flink_005f7 = _jspx_th_html_005flink_005f7.doStartTag();
    if (_jspx_eval_html_005flink_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f7.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f7(_jspx_th_html_005flink_005f7, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f7);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f7 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f7.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f7);
    // /jgossip/content/../jspf/topbar.jspf(143,69) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f7.setKey("forum.NEW_TOP");
    int _jspx_eval_fmt_005fmessage_005f7 = _jspx_th_fmt_005fmessage_005f7.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f7 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /jgossip/content/../jspf/topbar.jspf(144,19) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f7.setTest("${sessionScope.JRF_USER.status>8}");
    int _jspx_eval_c_005fif_005f7 = _jspx_th_c_005fif_005f7.doStartTag();
    if (_jspx_eval_c_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                <b> | </b> <a style=\"cursor:pointer;text-decoration:underline;\" onClick=\"changeMenu()\">");
        if (_jspx_meth_fmt_005fmessage_005f8(_jspx_th_c_005fif_005f7, _jspx_page_context))
          return true;
        out.write("</a>\r\n");
        out.write("\t\t\t\t            ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f8 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f8.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f7);
    // /jgossip/content/../jspf/topbar.jspf(145,107) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f8.setKey("global.menu.ADMIN");
    int _jspx_eval_fmt_005fmessage_005f8 = _jspx_th_fmt_005fmessage_005f8.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f8 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f8.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(150,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f8.setTest("${sessionScope.JRF_USER.status>8}");
    int _jspx_eval_c_005fif_005f8 = _jspx_th_c_005fif_005f8.doStartTag();
    if (_jspx_eval_c_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t    \t    <span class=\"nav\"  id=\"admin_menu\" ");
        if (_jspx_meth_c_005fif_005f9(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("><!-- admin's menu-->\r\n");
        out.write("\t\t\t    \t\t   ");
        if (_jspx_meth_html_005flink_005f8(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write(" <b>|</b> ");
        if (_jspx_meth_html_005flink_005f9(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write(" <b>|</b>\r\n");
        out.write("                           ");
        if (_jspx_meth_html_005flink_005f10(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write(" <b>|</b> ");
        if (_jspx_meth_html_005flink_005f11(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                           <br>");
        if (_jspx_meth_html_005flink_005f12(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("    \r\n");
        out.write("                           <b> | </b>");
        if (_jspx_meth_html_005flink_005f13(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                           <b> | </b>");
        if (_jspx_meth_html_005flink_005f14(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                           <br>");
        if (_jspx_meth_html_005flink_005f15(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                           <b> | </b>");
        if (_jspx_meth_html_005flink_005f16(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                           <b> | </b>");
        if (_jspx_meth_html_005flink_005f17(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                           <b> | </b>");
        if (_jspx_meth_html_005flink_005f18(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                           <br><a style=\"cursor:pointer;text-decoration:underline;\" onClick=\"changeMenu()\">");
        if (_jspx_meth_fmt_005fmessage_005f20(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("</a>\r\n");
        out.write("                            <b> | </b>");
        if (_jspx_meth_html_005flink_005f19(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                            <b> | </b>");
        if (_jspx_meth_html_005flink_005f20(_jspx_th_c_005fif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        </span>\r\n");
        out.write("\t\t\t    \t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f9 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(151,47) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f9.setTest("${empty requestScope.showAdminMenu}");
    int _jspx_eval_c_005fif_005f9 = _jspx_th_c_005fif_005f9.doStartTag();
    if (_jspx_eval_c_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t    \t                                          style=\"display:none;\"\r\n");
        out.write("\t\t\t    \t                                       ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f8 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f8.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(154,12) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f8.setAction("showAddGroup");
    int _jspx_eval_html_005flink_005f8 = _jspx_th_html_005flink_005f8.doStartTag();
    if (_jspx_eval_html_005flink_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f8.doInitBody();
      }
      do {
        out.write("<nobr>");
        if (_jspx_meth_fmt_005fmessage_005f9(_jspx_th_html_005flink_005f8, _jspx_page_context))
          return true;
        out.write("</nobr>");
        int evalDoAfterBody = _jspx_th_html_005flink_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f8);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f9 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f9.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f8);
    // /jgossip/content/../jspf/topbar.jspf(154,51) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f9.setKey("forum.ADD_GROUP");
    int _jspx_eval_fmt_005fmessage_005f9 = _jspx_th_fmt_005fmessage_005f9.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f9);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f9 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f9.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(154,116) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f9.setAction("showAddForum");
    int _jspx_eval_html_005flink_005f9 = _jspx_th_html_005flink_005f9.doStartTag();
    if (_jspx_eval_html_005flink_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f9.doInitBody();
      }
      do {
        out.write("<nobr>");
        if (_jspx_meth_fmt_005fmessage_005f10(_jspx_th_html_005flink_005f9, _jspx_page_context))
          return true;
        out.write("</nobr>");
        int evalDoAfterBody = _jspx_th_html_005flink_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f9);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f10 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f10.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f9);
    // /jgossip/content/../jspf/topbar.jspf(154,155) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f10.setKey("forum.AF");
    int _jspx_eval_fmt_005fmessage_005f10 = _jspx_th_fmt_005fmessage_005f10.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f10);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f10 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f10.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(155,27) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f10.setAction("ShowGroupList");
    int _jspx_eval_html_005flink_005f10 = _jspx_th_html_005flink_005f10.doStartTag();
    if (_jspx_eval_html_005flink_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f10.doInitBody();
      }
      do {
        out.write("<nobr>");
        if (_jspx_meth_fmt_005fmessage_005f11(_jspx_th_html_005flink_005f10, _jspx_page_context))
          return true;
        out.write("</nobr>");
        int evalDoAfterBody = _jspx_th_html_005flink_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f10);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f11 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f11.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f10);
    // /jgossip/content/../jspf/topbar.jspf(155,67) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f11.setKey("forum.EDIT_GR");
    int _jspx_eval_fmt_005fmessage_005f11 = _jspx_th_fmt_005fmessage_005f11.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f11);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f11 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f11.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(155,130) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f11.setAction("ShowForumList");
    int _jspx_eval_html_005flink_005f11 = _jspx_th_html_005flink_005f11.doStartTag();
    if (_jspx_eval_html_005flink_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f11.doInitBody();
      }
      do {
        out.write("<nobr>");
        if (_jspx_meth_fmt_005fmessage_005f12(_jspx_th_html_005flink_005f11, _jspx_page_context))
          return true;
        out.write("</nobr>");
        int evalDoAfterBody = _jspx_th_html_005flink_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f11);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f12 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f12.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f11);
    // /jgossip/content/../jspf/topbar.jspf(155,170) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f12.setKey("forum.EF");
    int _jspx_eval_fmt_005fmessage_005f12 = _jspx_th_fmt_005fmessage_005f12.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f12);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f12 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f12.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(156,31) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f12.setAction("showDropOldMess");
    int _jspx_eval_html_005flink_005f12 = _jspx_th_html_005flink_005f12.doStartTag();
    if (_jspx_eval_html_005flink_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f12.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f12.doInitBody();
      }
      do {
        out.write("<nobr>");
        if (_jspx_meth_fmt_005fmessage_005f13(_jspx_th_html_005flink_005f12, _jspx_page_context))
          return true;
        out.write("</nobr>");
        int evalDoAfterBody = _jspx_th_html_005flink_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f12);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f13 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f13.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f12);
    // /jgossip/content/../jspf/topbar.jspf(156,73) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f13.setKey("mails.DROPOLD");
    int _jspx_eval_fmt_005fmessage_005f13 = _jspx_th_fmt_005fmessage_005f13.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f13);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f13 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f13.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(157,37) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f13.setAction("ShowUserList");
    int _jspx_eval_html_005flink_005f13 = _jspx_th_html_005flink_005f13.doStartTag();
    if (_jspx_eval_html_005flink_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f13 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f13.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f13.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f14(_jspx_th_html_005flink_005f13, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f13 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f13);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f14 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f14.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f13);
    // /jgossip/content/../jspf/topbar.jspf(157,70) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f14.setKey("forum.EU");
    int _jspx_eval_fmt_005fmessage_005f14 = _jspx_th_fmt_005fmessage_005f14.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f14);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f14 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f14.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(158,37) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f14.setAction("WhoIs");
    int _jspx_eval_html_005flink_005f14 = _jspx_th_html_005flink_005f14.doStartTag();
    if (_jspx_eval_html_005flink_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f14 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f14.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f14.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f15(_jspx_th_html_005flink_005f14, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f14 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f14);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f15 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f15.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f14);
    // /jgossip/content/../jspf/topbar.jspf(158,63) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f15.setKey("forum.ONLINE");
    int _jspx_eval_fmt_005fmessage_005f15 = _jspx_th_fmt_005fmessage_005f15.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f15);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f15 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f15.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(159,31) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f15.setAction("BanList");
    int _jspx_eval_html_005flink_005f15 = _jspx_th_html_005flink_005f15.doStartTag();
    if (_jspx_eval_html_005flink_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f15 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f15.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f15.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f16(_jspx_th_html_005flink_005f15, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f15 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f15);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f16 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f16.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f15);
    // /jgossip/content/../jspf/topbar.jspf(159,59) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f16.setKey("user.BAN_LIST");
    int _jspx_eval_fmt_005fmessage_005f16 = _jspx_th_fmt_005fmessage_005f16.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f16);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f16 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f16.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(160,37) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f16.setAction("showEditStyles");
    int _jspx_eval_html_005flink_005f16 = _jspx_th_html_005flink_005f16.doStartTag();
    if (_jspx_eval_html_005flink_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f16 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f16.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f16.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f17(_jspx_th_html_005flink_005f16, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f16.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f16 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f16);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f17 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f17.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f16);
    // /jgossip/content/../jspf/topbar.jspf(160,72) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f17.setKey("skins.UPDATE");
    int _jspx_eval_fmt_005fmessage_005f17 = _jspx_th_fmt_005fmessage_005f17.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f17);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f17 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f17.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(161,37) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f17.setAction("showEditConstants");
    int _jspx_eval_html_005flink_005f17 = _jspx_th_html_005flink_005f17.doStartTag();
    if (_jspx_eval_html_005flink_005f17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f17 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f17.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f17.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f18(_jspx_th_html_005flink_005f17, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f17.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f17 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f17);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f18(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f18 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f18.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f17);
    // /jgossip/content/../jspf/topbar.jspf(161,75) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f18.setKey("global.CONSTANTS");
    int _jspx_eval_fmt_005fmessage_005f18 = _jspx_th_fmt_005fmessage_005f18.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f18);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f18(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f18 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f18.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(162,37) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f18.setAction("Ranks");
    int _jspx_eval_html_005flink_005f18 = _jspx_th_html_005flink_005f18.doStartTag();
    if (_jspx_eval_html_005flink_005f18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f18 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f18.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f18.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f19(_jspx_th_html_005flink_005f18, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f18.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f18 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f18);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f18, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f19 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f19.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f18);
    // /jgossip/content/../jspf/topbar.jspf(162,63) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f19.setKey("user.EDIT_RANKS");
    int _jspx_eval_fmt_005fmessage_005f19 = _jspx_th_fmt_005fmessage_005f19.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f19);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f20(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f20 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f20.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(163,107) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f20.setKey("global.menu.FORUM");
    int _jspx_eval_fmt_005fmessage_005f20 = _jspx_th_fmt_005fmessage_005f20.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f20);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f19 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f19.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(164,38) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f19.setAction("showSendAdminMail");
    int _jspx_eval_html_005flink_005f19 = _jspx_th_html_005flink_005f19.doStartTag();
    if (_jspx_eval_html_005flink_005f19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f19 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f19.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f19.doInitBody();
      }
      do {
        out.write("<nobr>");
        if (_jspx_meth_fmt_005fmessage_005f21(_jspx_th_html_005flink_005f19, _jspx_page_context))
          return true;
        out.write("</nobr>");
        int evalDoAfterBody = _jspx_th_html_005flink_005f19.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f19 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f19);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f21(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f19, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f21 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f21.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f19);
    // /jgossip/content/../jspf/topbar.jspf(164,82) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f21.setKey("mails.SEND_ADMIN_MAIL");
    int _jspx_eval_fmt_005fmessage_005f21 = _jspx_th_fmt_005fmessage_005f21.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f21);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f20(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f20 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f20.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f8);
    // /jgossip/content/../jspf/topbar.jspf(165,38) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f20.setAction("BrowseLog");
    int _jspx_eval_html_005flink_005f20 = _jspx_th_html_005flink_005f20.doStartTag();
    if (_jspx_eval_html_005flink_005f20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f20 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f20.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f20.doInitBody();
      }
      do {
        out.write("<nobr>");
        if (_jspx_meth_fmt_005fmessage_005f22(_jspx_th_html_005flink_005f20, _jspx_page_context))
          return true;
        out.write("</nobr>");
        int evalDoAfterBody = _jspx_th_html_005flink_005f20.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f20 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f20);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f22(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f20, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f22 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f22.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f20);
    // /jgossip/content/../jspf/topbar.jspf(165,74) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f22.setKey("log.BROWSE_LOG");
    int _jspx_eval_fmt_005fmessage_005f22 = _jspx_th_fmt_005fmessage_005f22.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f22);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f23(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f23 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f23.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f23.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(197,33) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f23.setKey("global.JUMPTO");
    int _jspx_eval_fmt_005fmessage_005f23 = _jspx_th_fmt_005fmessage_005f23.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f23);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f23);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f24(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f24 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f24.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f24.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(200,56) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f24.setKey("forum.ROOT");
    int _jspx_eval_fmt_005fmessage_005f24 = _jspx_th_fmt_005fmessage_005f24.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f24);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f24);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f25(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f25 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f25.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f25.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(200,89) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f25.setKey("forum.ROOT");
    int _jspx_eval_fmt_005fmessage_005f25 = _jspx_th_fmt_005fmessage_005f25.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f25);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f25);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f10 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f10.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(201,7) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f10.setTest("${!empty sessionScope.JRF_GROUPS}");
    int _jspx_eval_c_005fif_005f10 = _jspx_th_c_005fif_005f10.doStartTag();
    if (_jspx_eval_c_005fif_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t    ");
        if (_jspx_meth_gossip_005fjumpoptions_005f0(_jspx_th_c_005fif_005f10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f10);
    return false;
  }

  private boolean _jspx_meth_gossip_005fjumpoptions_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gossip:jumpoptions
    org.jresearch.gossip.tags.JumpToOptionsTag _jspx_th_gossip_005fjumpoptions_005f0 = (org.jresearch.gossip.tags.JumpToOptionsTag) _005fjspx_005ftagPool_005fgossip_005fjumpoptions_005fnobody.get(org.jresearch.gossip.tags.JumpToOptionsTag.class);
    _jspx_th_gossip_005fjumpoptions_005f0.setPageContext(_jspx_page_context);
    _jspx_th_gossip_005fjumpoptions_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f10);
    int _jspx_eval_gossip_005fjumpoptions_005f0 = _jspx_th_gossip_005fjumpoptions_005f0.doStartTag();
    if (_jspx_th_gossip_005fjumpoptions_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgossip_005fjumpoptions_005fnobody.reuse(_jspx_th_gossip_005fjumpoptions_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fgossip_005fjumpoptions_005fnobody.reuse(_jspx_th_gossip_005fjumpoptions_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f26(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f26 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f26.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f26.setParent(null);
    // /jgossip/content/../jspf/topbar.jspf(206,104) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f26.setKey("global.GO");
    int _jspx_eval_fmt_005fmessage_005f26 = _jspx_th_fmt_005fmessage_005f26.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f26);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f26);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f1 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f1.setParent(null);
    int _jspx_eval_c_005fchoose_005f1 = _jspx_th_c_005fchoose_005f1.doStartTag();
    if (_jspx_eval_c_005fchoose_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t\t\r\n");
        out.write("\t          ");
        if (_jspx_meth_c_005fwhen_005f1(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t      ");
        if (_jspx_meth_c_005fotherwise_005f1(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    ");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_005fwhen_005f1 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    // /jgossip/content/../jspf/topbar.jspf(258,11) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f1.setTest("${sessionScope.JRF_USER.status==0}");
    int _jspx_eval_c_005fwhen_005f1 = _jspx_th_c_005fwhen_005f1.doStartTag();
    if (_jspx_eval_c_005fwhen_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("             \t  ");
        if (_jspx_meth_fmt_005fmessage_005f27(_jspx_th_c_005fwhen_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("              ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f27(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f27 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f27.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f1);
    // /jgossip/content/../jspf/topbar.jspf(259,16) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f27.setKey("global.NOT_LOGGED");
    int _jspx_eval_fmt_005fmessage_005f27 = _jspx_th_fmt_005fmessage_005f27.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f27);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f27);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f1 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    int _jspx_eval_c_005fotherwise_005f1 = _jspx_th_c_005fotherwise_005f1.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                  ");
        if (_jspx_meth_fmt_005fmessage_005f28(_jspx_th_c_005fotherwise_005f1, _jspx_page_context))
          return true;
        out.write(",&nbsp;");
        if (_jspx_meth_c_005fout_005f3(_jspx_th_c_005fotherwise_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("              ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f28(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f28 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f28.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f1);
    // /jgossip/content/../jspf/topbar.jspf(262,18) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f28.setKey("forum.HELLO");
    int _jspx_eval_fmt_005fmessage_005f28 = _jspx_th_fmt_005fmessage_005f28.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f28);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f28);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f1);
    // /jgossip/content/../jspf/topbar.jspf(262,57) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue("${sessionScope.JRF_USER.name}");
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(277,40) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("${jrf_url}");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f29(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f29 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f29.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(277,69) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f29.setKey("global.LOGIN");
    int _jspx_eval_fmt_005fmessage_005f29 = _jspx_th_fmt_005fmessage_005f29.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f29);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f29);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f30(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f30 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f30.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(277,112) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f30.setKey("global.OR");
    int _jspx_eval_fmt_005fmessage_005f30 = _jspx_th_fmt_005fmessage_005f30.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f30);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f30);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(277,270) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("${jrf_url}");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f31(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f31 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f31.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(277,299) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f31.setKey("global.REGISTER");
    int _jspx_eval_fmt_005fmessage_005f31 = _jspx_th_fmt_005fmessage_005f31.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f31);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f31);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f21 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f21.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(279,23) name = styleClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f21.setStyleClass("control");
    // /jgossip/content/../jspf/topbar.jspf(279,23) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f21.setAction("showLogon");
    int _jspx_eval_html_005flink_005f21 = _jspx_th_html_005flink_005f21.doStartTag();
    if (_jspx_eval_html_005flink_005f21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f21 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f21.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f21.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f32(_jspx_th_html_005flink_005f21, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f21.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f21 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.reuse(_jspx_th_html_005flink_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.reuse(_jspx_th_html_005flink_005f21);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f32(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f21, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f32 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f32.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f21);
    // /jgossip/content/../jspf/topbar.jspf(279,74) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f32.setKey("global.LOGIN");
    int _jspx_eval_fmt_005fmessage_005f32 = _jspx_th_fmt_005fmessage_005f32.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f32);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f32);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f33(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f33 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f33.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(281,33) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f33.setKey("global.OR");
    int _jspx_eval_fmt_005fmessage_005f33 = _jspx_th_fmt_005fmessage_005f33.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f33);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f33);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f3 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(287,54) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setValue("ShowRegistration.do");
    int _jspx_eval_c_005furl_005f3 = _jspx_th_c_005furl_005f3.doStartTag();
    if (_jspx_eval_c_005furl_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_005furl_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_c_005furl_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_c_005furl_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t                       \t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fparam_005f3(_jspx_th_c_005furl_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                       \t\t\t\t\t\t\t\t ");
        int evalDoAfterBody = _jspx_th_c_005furl_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_005furl_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_c_005furl_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f3 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f3);
    // /jgossip/content/../jspf/topbar.jspf(288,35) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f3.setName("dispatch");
    // /jgossip/content/../jspf/topbar.jspf(288,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f3.setValue("${pageScope.jrf_registration_mode}");
    int _jspx_eval_c_005fparam_005f3 = _jspx_th_c_005fparam_005f3.doStartTag();
    if (_jspx_th_c_005fparam_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f3);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f34(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f34 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f34.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f2);
    // /jgossip/content/../jspf/topbar.jspf(289,46) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f34.setKey("global.REGISTER");
    int _jspx_eval_fmt_005fmessage_005f34 = _jspx_th_fmt_005fmessage_005f34.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f34);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f34);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f4 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f2);
    // /jgossip/content/../jspf/topbar.jspf(296,40) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setValue("${jrf_url}");
    int _jspx_eval_c_005furl_005f4 = _jspx_th_c_005furl_005f4.doStartTag();
    if (_jspx_th_c_005furl_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f35(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f35 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f35.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f2);
    // /jgossip/content/../jspf/topbar.jspf(296,69) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f35.setKey("forum.LOGOUT");
    int _jspx_eval_fmt_005fmessage_005f35 = _jspx_th_fmt_005fmessage_005f35.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f35);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f35);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f22(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f22 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f22.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f2);
    // /jgossip/content/../jspf/topbar.jspf(298,26) name = styleClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f22.setStyleClass("control");
    // /jgossip/content/../jspf/topbar.jspf(298,26) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f22.setAction("Logout");
    int _jspx_eval_html_005flink_005f22 = _jspx_th_html_005flink_005f22.doStartTag();
    if (_jspx_eval_html_005flink_005f22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f22 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f22.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f22.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f36(_jspx_th_html_005flink_005f22, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f22.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f22 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.reuse(_jspx_th_html_005flink_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.reuse(_jspx_th_html_005flink_005f22);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f36(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f22, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f36 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f36.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f22);
    // /jgossip/content/../jspf/topbar.jspf(298,74) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f36.setKey("forum.LOGOUT");
    int _jspx_eval_fmt_005fmessage_005f36 = _jspx_th_fmt_005fmessage_005f36.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f36);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f36);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f37(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f37 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f37.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f11);
    // /jgossip/content/../jspf/status.jspf(30,43) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f37.setKey("status.STATUS");
    int _jspx_eval_fmt_005fmessage_005f37 = _jspx_th_fmt_005fmessage_005f37.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f37);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f37);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f4 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f11);
    // /jgossip/content/../jspf/status.jspf(32,40) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f4.setValue("${sessionScope.JRF_STATUS_MESSAGE}");
    // /jgossip/content/../jspf/status.jspf(32,40) name = escapeXml type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f4.setEscapeXml("false");
    int _jspx_eval_c_005fout_005f4 = _jspx_th_c_005fout_005f4.doStartTag();
    if (_jspx_th_c_005fout_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f4);
    return false;
  }

  private boolean _jspx_meth_logic_005fmessagesNotPresent_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  logic:messagesNotPresent
    org.apache.struts.taglib.logic.MessagesNotPresentTag _jspx_th_logic_005fmessagesNotPresent_005f0 = (org.apache.struts.taglib.logic.MessagesNotPresentTag) _005fjspx_005ftagPool_005flogic_005fmessagesNotPresent.get(org.apache.struts.taglib.logic.MessagesNotPresentTag.class);
    _jspx_th_logic_005fmessagesNotPresent_005f0.setPageContext(_jspx_page_context);
    _jspx_th_logic_005fmessagesNotPresent_005f0.setParent(null);
    int _jspx_eval_logic_005fmessagesNotPresent_005f0 = _jspx_th_logic_005fmessagesNotPresent_005f0.doStartTag();
    if (_jspx_eval_logic_005fmessagesNotPresent_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("display:none;");
        int evalDoAfterBody = _jspx_th_logic_005fmessagesNotPresent_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_logic_005fmessagesNotPresent_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flogic_005fmessagesNotPresent.reuse(_jspx_th_logic_005fmessagesNotPresent_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flogic_005fmessagesNotPresent.reuse(_jspx_th_logic_005fmessagesNotPresent_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f38(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f38 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f38.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f38.setParent(null);
    // /jgossip/content/../jspf/status.jspf(51,43) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f38.setKey("errors.TITLE");
    int _jspx_eval_fmt_005fmessage_005f38 = _jspx_th_fmt_005fmessage_005f38.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f38);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f38);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f39(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f39 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f39.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f39.setParent(null);
    // /jgossip/content/../jspf/status.jspf(53,40) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f39.setKey("errors.DESCRIPTION");
    int _jspx_eval_fmt_005fmessage_005f39 = _jspx_th_fmt_005fmessage_005f39.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f39);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f39);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_005fmessagesPresent_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_005fset_005f4 = (org.apache.taglibs.standard.tag.el.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
    _jspx_th_c_005fset_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_005fmessagesPresent_005f0);
    // /jgossip/content/../jspf/status.jspf(68,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setVar("jrf_err_mess");
    // /jgossip/content/../jspf/status.jspf(68,7) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setValue("Y");
    int _jspx_eval_c_005fset_005f4 = _jspx_th_c_005fset_005f4.doStartTag();
    if (_jspx_th_c_005fset_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fmessages_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f5 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fmessages_005f0);
    // /jgossip/content/../jspf/status.jspf(70,19) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f5.setValue("${msg}");
    // /jgossip/content/../jspf/status.jspf(70,19) name = escapeXml type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f5.setEscapeXml("false");
    int _jspx_eval_c_005fout_005f5 = _jspx_th_c_005fout_005f5.doStartTag();
    if (_jspx_th_c_005fout_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f5);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f40(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f40 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f40.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f40.setParent(null);
    // /jgossip/content/MainPage.jsp(54,32) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f40.setKey("forum.STATUS");
    int _jspx_eval_fmt_005fmessage_005f40 = _jspx_th_fmt_005fmessage_005f40.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f40);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f40);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f41(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f41 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f41.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f41.setParent(null);
    // /jgossip/content/MainPage.jsp(57,50) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f41.setKey("forum.FORUM_NAME");
    int _jspx_eval_fmt_005fmessage_005f41 = _jspx_th_fmt_005fmessage_005f41.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f41);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f41);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f42(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f42 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f42.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f42.setParent(null);
    // /jgossip/content/MainPage.jsp(62,32) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f42.setKey("forum.MESGS");
    int _jspx_eval_fmt_005fmessage_005f42 = _jspx_th_fmt_005fmessage_005f42.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f42);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f42);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f43(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f43 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f43.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f43.setParent(null);
    // /jgossip/content/MainPage.jsp(65,32) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f43.setKey("forum.TOPICS");
    int _jspx_eval_fmt_005fmessage_005f43 = _jspx_th_fmt_005fmessage_005f43.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f43);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f43);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f44(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f44 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f44.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f44.setParent(null);
    // /jgossip/content/MainPage.jsp(68,32) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f44.setKey("forum.L_MSG");
    int _jspx_eval_fmt_005fmessage_005f44 = _jspx_th_fmt_005fmessage_005f44.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f44);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f44);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f6 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /jgossip/content/MainPage.jsp(76,36) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f6.setValue("${group.name}");
    int _jspx_eval_c_005fout_005f6 = _jspx_th_c_005fout_005f6.doStartTag();
    if (_jspx_th_c_005fout_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_005fset_005f5 = (org.apache.taglibs.standard.tag.el.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
    _jspx_th_c_005fset_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /jgossip/content/MainPage.jsp(82,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setVar("lastmessage");
    // /jgossip/content/MainPage.jsp(82,4) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setValue("${forum.lastMessage}");
    int _jspx_eval_c_005fset_005f5 = _jspx_th_c_005fset_005f5.doStartTag();
    if (_jspx_th_c_005fset_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f7 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /jgossip/content/MainPage.jsp(83,18) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f7.setValue("${status.count%2}");
    int _jspx_eval_c_005fout_005f7 = _jspx_th_c_005fout_005f7.doStartTag();
    if (_jspx_th_c_005fout_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f5 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /jgossip/content/MainPage.jsp(94,47) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f5.setValue("ShowForum.do");
    int _jspx_eval_c_005furl_005f5 = _jspx_th_c_005furl_005f5.doStartTag();
    if (_jspx_eval_c_005furl_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_005furl_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_push_body_count_c_005fforEach_005f2[0]++;
        _jspx_th_c_005furl_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_c_005furl_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t                                                ");
        if (_jspx_meth_c_005fparam_005f4(_jspx_th_c_005furl_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                                                       ");
        int evalDoAfterBody = _jspx_th_c_005furl_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_005furl_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
        _jspx_push_body_count_c_005fforEach_005f2[0]--;
      }
    }
    if (_jspx_th_c_005furl_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f4 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f5);
    // /jgossip/content/MainPage.jsp(95,51) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f4.setName("fid");
    // /jgossip/content/MainPage.jsp(95,51) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f4.setValue("${forum.forumid}");
    int _jspx_eval_c_005fparam_005f4 = _jspx_th_c_005fparam_005f4.doStartTag();
    if (_jspx_th_c_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f8 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /jgossip/content/MainPage.jsp(96,84) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f8.setValue("${forum.title}");
    int _jspx_eval_c_005fout_005f8 = _jspx_th_c_005fout_005f8.doStartTag();
    if (_jspx_th_c_005fout_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f9 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /jgossip/content/MainPage.jsp(97,36) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f9.setValue("${forum.description}");
    int _jspx_eval_c_005fout_005f9 = _jspx_th_c_005fout_005f9.doStartTag();
    if (_jspx_th_c_005fout_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f10 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /jgossip/content/MainPage.jsp(99,41) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f10.setValue("${forum.messCount}");
    int _jspx_eval_c_005fout_005f10 = _jspx_th_c_005fout_005f10.doStartTag();
    if (_jspx_th_c_005fout_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f11 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /jgossip/content/MainPage.jsp(101,41) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f11.setValue("${forum.threadsCount}");
    int _jspx_eval_c_005fout_005f11 = _jspx_th_c_005fout_005f11.doStartTag();
    if (_jspx_th_c_005fout_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f11);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f5 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    int _jspx_eval_c_005fchoose_005f5 = _jspx_th_c_005fchoose_005f5.doStartTag();
    if (_jspx_eval_c_005fchoose_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t\t\r\n");
        out.write("\t            ");
        if (_jspx_meth_c_005fwhen_005f5(_jspx_th_c_005fchoose_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("\t             ");
        if (_jspx_meth_c_005fotherwise_005f3(_jspx_th_c_005fchoose_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("              ");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_005fwhen_005f5 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f5);
    // /jgossip/content/MainPage.jsp(105,13) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f5.setTest("${forum.threadsCount>0}");
    int _jspx_eval_c_005fwhen_005f5 = _jspx_th_c_005fwhen_005f5.doStartTag();
    if (_jspx_eval_c_005fwhen_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                ");
        if (_jspx_meth_c_005fset_005f6(_jspx_th_c_005fwhen_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                  <span class=\"txt\"><a href=\"");
        if (_jspx_meth_c_005furl_005f6(_jspx_th_c_005fwhen_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write('"');
        out.write('>');
        if (_jspx_meth_gossip_005fprocess_005f0(_jspx_th_c_005fwhen_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("</a> (\r\n");
        out.write("                \r\n");
        out.write("                      ");
        if (_jspx_meth_c_005fif_005f13(_jspx_th_c_005fwhen_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("       \t\r\n");
        out.write("                          \t\t");
        if (_jspx_meth_c_005fout_005f12(_jspx_th_c_005fwhen_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                      ");
        if (_jspx_meth_c_005fif_005f14(_jspx_th_c_005fwhen_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                  )\r\n");
        out.write("                  <br>\r\n");
        out.write("                 \t");
        if (_jspx_meth_fmt_005fformatDate_005f0(_jspx_th_c_005fwhen_005f5, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                  </span>\r\n");
        out.write("                 ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_005fset_005f6 = (org.apache.taglibs.standard.tag.el.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
    _jspx_th_c_005fset_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f5);
    // /jgossip/content/MainPage.jsp(106,16) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f6.setVar("senderInfo");
    // /jgossip/content/MainPage.jsp(106,16) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f6.setValue("${lastmessage.senderInfo}");
    int _jspx_eval_c_005fset_005f6 = _jspx_th_c_005fset_005f6.doStartTag();
    if (_jspx_th_c_005fset_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f6 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f5);
    // /jgossip/content/MainPage.jsp(107,45) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f6.setValue("ShowMessage.do");
    int _jspx_eval_c_005furl_005f6 = _jspx_th_c_005furl_005f6.doStartTag();
    if (_jspx_eval_c_005furl_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_005furl_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_push_body_count_c_005fforEach_005f2[0]++;
        _jspx_th_c_005furl_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_c_005furl_005f6.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                                       ");
        if (_jspx_meth_c_005fparam_005f5(_jspx_th_c_005furl_005f6, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                                       ");
        if (_jspx_meth_c_005fparam_005f6(_jspx_th_c_005furl_005f6, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                                       ");
        if (_jspx_meth_c_005fparam_005f7(_jspx_th_c_005furl_005f6, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                                   ");
        int evalDoAfterBody = _jspx_th_c_005furl_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_005furl_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
        _jspx_push_body_count_c_005fforEach_005f2[0]--;
      }
    }
    if (_jspx_th_c_005furl_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f6, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f5 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f6);
    // /jgossip/content/MainPage.jsp(108,39) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f5.setName("fid");
    // /jgossip/content/MainPage.jsp(108,39) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f5.setValue("${forum.forumid}");
    int _jspx_eval_c_005fparam_005f5 = _jspx_th_c_005fparam_005f5.doStartTag();
    if (_jspx_th_c_005fparam_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f6, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f6 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f6);
    // /jgossip/content/MainPage.jsp(109,39) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f6.setName("tid");
    // /jgossip/content/MainPage.jsp(109,39) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f6.setValue("${forum.lastMessThreadId}");
    int _jspx_eval_c_005fparam_005f6 = _jspx_th_c_005fparam_005f6.doStartTag();
    if (_jspx_th_c_005fparam_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f6, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f7 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f6);
    // /jgossip/content/MainPage.jsp(110,39) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f7.setName("mid");
    // /jgossip/content/MainPage.jsp(110,39) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f7.setValue("${lastmessage.id}");
    int _jspx_eval_c_005fparam_005f7 = _jspx_th_c_005fparam_005f7.doStartTag();
    if (_jspx_th_c_005fparam_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f7);
    return false;
  }

  private boolean _jspx_meth_gossip_005fprocess_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gossip:process
    org.jresearch.gossip.tags.ProcessMessageTag _jspx_th_gossip_005fprocess_005f0 = (org.jresearch.gossip.tags.ProcessMessageTag) _005fjspx_005ftagPool_005fgossip_005fprocess_0026_005fvalue_005fcutToLength_005fnobody.get(org.jresearch.gossip.tags.ProcessMessageTag.class);
    _jspx_th_gossip_005fprocess_005f0.setPageContext(_jspx_page_context);
    _jspx_th_gossip_005fprocess_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f5);
    // /jgossip/content/MainPage.jsp(111,45) name = cutToLength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gossip_005fprocess_005f0.setCutToLength(26);
    // /jgossip/content/MainPage.jsp(111,45) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gossip_005fprocess_005f0.setValue("${lastmessage.centents}");
    int _jspx_eval_gossip_005fprocess_005f0 = _jspx_th_gossip_005fprocess_005f0.doStartTag();
    if (_jspx_th_gossip_005fprocess_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgossip_005fprocess_0026_005fvalue_005fcutToLength_005fnobody.reuse(_jspx_th_gossip_005fprocess_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fgossip_005fprocess_0026_005fvalue_005fcutToLength_005fnobody.reuse(_jspx_th_gossip_005fprocess_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f13 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005fvar_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f5);
    // /jgossip/content/MainPage.jsp(113,22) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f13.setTest("${senderInfo.status>0}");
    // /jgossip/content/MainPage.jsp(113,22) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f13.setVar("isReg");
    int _jspx_eval_c_005fif_005f13 = _jspx_th_c_005fif_005f13.doStartTag();
    if (_jspx_eval_c_005fif_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                          <a href=\"");
        if (_jspx_meth_c_005furl_005f7(_jspx_th_c_005fif_005f13, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\">\r\n");
        out.write("                      ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f13);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f13, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f7 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f13);
    // /jgossip/content/MainPage.jsp(114,35) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f7.setValue("ShowUser.do");
    int _jspx_eval_c_005furl_005f7 = _jspx_th_c_005furl_005f7.doStartTag();
    if (_jspx_eval_c_005furl_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_005furl_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_push_body_count_c_005fforEach_005f2[0]++;
        _jspx_th_c_005furl_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_c_005furl_005f7.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                                       ");
        if (_jspx_meth_c_005fparam_005f8(_jspx_th_c_005furl_005f7, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                                   ");
        int evalDoAfterBody = _jspx_th_c_005furl_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_005furl_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
        _jspx_push_body_count_c_005fforEach_005f2[0]--;
      }
    }
    if (_jspx_th_c_005furl_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f7, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f8 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f7);
    // /jgossip/content/MainPage.jsp(115,39) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f8.setName("uid");
    // /jgossip/content/MainPage.jsp(115,39) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f8.setValue("${lastmessage.sender}");
    int _jspx_eval_c_005fparam_005f8 = _jspx_th_c_005fparam_005f8.doStartTag();
    if (_jspx_th_c_005fparam_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f12 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f12.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f5);
    // /jgossip/content/MainPage.jsp(118,28) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f12.setValue("${lastmessage.sender}");
    int _jspx_eval_c_005fout_005f12 = _jspx_th_c_005fout_005f12.doStartTag();
    if (_jspx_th_c_005fout_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f12);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f14 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f14.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f5);
    // /jgossip/content/MainPage.jsp(119,22) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f14.setTest("${isReg}");
    int _jspx_eval_c_005fif_005f14 = _jspx_th_c_005fif_005f14.doStartTag();
    if (_jspx_eval_c_005fif_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                          \t</a>\r\n");
        out.write("                      ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f14);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatDate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatDate
    org.apache.taglibs.standard.tag.el.fmt.FormatDateTag _jspx_th_fmt_005fformatDate_005f0 = (org.apache.taglibs.standard.tag.el.fmt.FormatDateTag) _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005ftimeStyle_005fdateStyle_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.FormatDateTag.class);
    _jspx_th_fmt_005fformatDate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatDate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f5);
    // /jgossip/content/MainPage.jsp(124,18) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setValue("${lastmessage.intime}");
    // /jgossip/content/MainPage.jsp(124,18) name = type type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setType("both");
    // /jgossip/content/MainPage.jsp(124,18) name = dateStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setDateStyle("short");
    // /jgossip/content/MainPage.jsp(124,18) name = timeStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setTimeStyle("short");
    int _jspx_eval_fmt_005fformatDate_005f0 = _jspx_th_fmt_005fformatDate_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatDate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005ftimeStyle_005fdateStyle_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005ftimeStyle_005fdateStyle_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f5, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f3 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f5);
    int _jspx_eval_c_005fotherwise_005f3 = _jspx_th_c_005fotherwise_005f3.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                     ");
        if (_jspx_meth_fmt_005fmessage_005f45(_jspx_th_c_005fotherwise_005f3, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
          return true;
        out.write("\r\n");
        out.write("                 ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f3);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f45(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f3, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f45 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f45.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f3);
    // /jgossip/content/MainPage.jsp(128,21) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f45.setKey("global.NONE");
    int _jspx_eval_fmt_005fmessage_005f45 = _jspx_th_fmt_005fmessage_005f45.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f45);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f45);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f4, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f4 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f4);
    int _jspx_eval_c_005fotherwise_005f4 = _jspx_th_c_005fotherwise_005f4.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            <tr class=\"drk\">\r\n");
        out.write("              <td class=\"lrt_g_txt_b\" colspan=\"6\">\r\n");
        out.write("                  ");
        if (_jspx_meth_fmt_005fmessage_005f46(_jspx_th_c_005fotherwise_005f4, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("!\r\n");
        out.write("                  \t");
        if (_jspx_meth_c_005fif_005f15(_jspx_th_c_005fotherwise_005f4, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("\r\n");
        out.write("              </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("           ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f4);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f46(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f4, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f46 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f46.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f4);
    // /jgossip/content/MainPage.jsp(139,18) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f46.setKey("forum.NO_FORUMS");
    int _jspx_eval_fmt_005fmessage_005f46 = _jspx_th_fmt_005fmessage_005f46.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f46);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f46);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f4, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f15 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f15.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f4);
    // /jgossip/content/MainPage.jsp(140,19) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f15.setTest("${sessionScope.JRF_USER.status==10}");
    int _jspx_eval_c_005fif_005f15 = _jspx_th_c_005fif_005f15.doStartTag();
    if (_jspx_eval_c_005fif_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                     [ <a href=\"");
        if (_jspx_meth_c_005furl_005f8(_jspx_th_c_005fif_005f15, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write('"');
        out.write('>');
        if (_jspx_meth_fmt_005fmessage_005f47(_jspx_th_c_005fif_005f15, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("</a> ]\r\n");
        out.write("                \t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f15);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f15, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.el.core.UrlTag _jspx_th_c_005furl_005f8 = (org.apache.taglibs.standard.tag.el.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.get(org.apache.taglibs.standard.tag.el.core.UrlTag.class);
    _jspx_th_c_005furl_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f15);
    // /jgossip/content/MainPage.jsp(141,32) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f8.setValue("showAddForum.do");
    int _jspx_eval_c_005furl_005f8 = _jspx_th_c_005furl_005f8.doStartTag();
    if (_jspx_eval_c_005furl_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_005furl_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_push_body_count_c_005fforEach_005f1[0]++;
        _jspx_th_c_005furl_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_c_005furl_005f8.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t                       ");
        if (_jspx_meth_c_005fparam_005f9(_jspx_th_c_005furl_005f8, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("\r\n");
        out.write("                                ");
        int evalDoAfterBody = _jspx_th_c_005furl_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_005furl_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
        _jspx_push_body_count_c_005fforEach_005f1[0]--;
      }
    }
    if (_jspx_th_c_005furl_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue.reuse(_jspx_th_c_005furl_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f8, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.el.core.ParamTag _jspx_th_c_005fparam_005f9 = (org.apache.taglibs.standard.tag.el.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.el.core.ParamTag.class);
    _jspx_th_c_005fparam_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f8);
    // /jgossip/content/MainPage.jsp(142,26) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f9.setName("groupid");
    // /jgossip/content/MainPage.jsp(142,26) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f9.setValue("${group.groupid}");
    int _jspx_eval_c_005fparam_005f9 = _jspx_th_c_005fparam_005f9.doStartTag();
    if (_jspx_th_c_005fparam_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f9);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f47(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f15, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f47 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f47.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f15);
    // /jgossip/content/MainPage.jsp(143,42) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f47.setKey("global.CREATE_SOME");
    int _jspx_eval_fmt_005fmessage_005f47 = _jspx_th_fmt_005fmessage_005f47.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f47);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f47);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f5 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f3);
    int _jspx_eval_c_005fotherwise_005f5 = _jspx_th_c_005fotherwise_005f5.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t <tr class=\"lght\">\r\n");
        out.write("\t\t\t<td class=\"t_g\" colspan=\"6\">\r\n");
        out.write("      \t\t\t");
        if (_jspx_meth_fmt_005fmessage_005f48(_jspx_th_c_005fotherwise_005f5, _jspx_page_context))
          return true;
        out.write("!<br>\r\n");
        out.write("                    ");
        if (_jspx_meth_fmt_005fmessage_005f49(_jspx_th_c_005fotherwise_005f5, _jspx_page_context))
          return true;
        out.write("!\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fif_005f16(_jspx_th_c_005fotherwise_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("              <br>\r\n");
        out.write("              \t</td>\r\n");
        out.write("\t\t   </tr>\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f5);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f48(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f48 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f48.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f5);
    // /jgossip/content/MainPage.jsp(154,9) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f48.setKey("global.ERROR");
    int _jspx_eval_fmt_005fmessage_005f48 = _jspx_th_fmt_005fmessage_005f48.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f48);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f48);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f49(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f49 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f49.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f5);
    // /jgossip/content/MainPage.jsp(155,20) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f49.setKey("forum.NFTD");
    int _jspx_eval_fmt_005fmessage_005f49 = _jspx_th_fmt_005fmessage_005f49.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f49);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f49);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f16 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f16.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f5);
    // /jgossip/content/MainPage.jsp(156,20) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f16.setTest("${sessionScope.JRF_USER.status==10}");
    int _jspx_eval_c_005fif_005f16 = _jspx_th_c_005fif_005f16.doStartTag();
    if (_jspx_eval_c_005fif_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                      ");
        if (_jspx_meth_html_005flink_005f24(_jspx_th_c_005fif_005f16, _jspx_page_context))
          return true;
        out.write(" \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f16.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f16);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f24(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f24 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f24.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f16);
    // /jgossip/content/MainPage.jsp(157,22) name = styleClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f24.setStyleClass("control");
    // /jgossip/content/MainPage.jsp(157,22) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f24.setAction("showAddGroup");
    int _jspx_eval_html_005flink_005f24 = _jspx_th_html_005flink_005f24.doStartTag();
    if (_jspx_eval_html_005flink_005f24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f24 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f24.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f24.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f50(_jspx_th_html_005flink_005f24, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f24.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f24 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.reuse(_jspx_th_html_005flink_005f24);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005fstyleClass_005faction.reuse(_jspx_th_html_005flink_005f24);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f50(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f24, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f50 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f50.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f24);
    // /jgossip/content/MainPage.jsp(157,76) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f50.setKey("global.CREATE_SOME");
    int _jspx_eval_fmt_005fmessage_005f50 = _jspx_th_fmt_005fmessage_005f50.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f50);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f50);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f17(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.el.core.IfTag _jspx_th_c_005fif_005f17 = (org.apache.taglibs.standard.tag.el.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.IfTag.class);
    _jspx_th_c_005fif_005f17.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f17.setParent(null);
    // /jgossip/content/MainPage.jsp(173,5) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f17.setTest("${!empty pageScope.JRF_HAVE_AN_UPDATED_TOPICS}");
    int _jspx_eval_c_005fif_005f17 = _jspx_th_c_005fif_005f17.doStartTag();
    if (_jspx_eval_c_005fif_005f17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t    <td align=\"left\">\r\n");
        out.write("\t        ");
        if (_jspx_meth_html_005flink_005f25(_jspx_th_c_005fif_005f17, _jspx_page_context))
          return true;
        out.write("\t\r\n");
        out.write("\t    </td>\r\n");
        out.write("\t    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f17.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f17);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f25(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f25 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f25.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f17);
    // /jgossip/content/MainPage.jsp(175,9) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005flink_005f25.setAction("MarkRead");
    int _jspx_eval_html_005flink_005f25 = _jspx_th_html_005flink_005f25.doStartTag();
    if (_jspx_eval_html_005flink_005f25 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f25 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f25.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f25.doInitBody();
      }
      do {
        if (_jspx_meth_fmt_005fmessage_005f51(_jspx_th_html_005flink_005f25, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_html_005flink_005f25.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f25 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f25);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_0026_005faction.reuse(_jspx_th_html_005flink_005f25);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f51(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f25, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f51 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f51.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f25);
    // /jgossip/content/MainPage.jsp(175,38) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f51.setKey("global.MARK_READ");
    int _jspx_eval_fmt_005fmessage_005f51 = _jspx_th_fmt_005fmessage_005f51.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f51);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f51);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f6 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f6.setParent(null);
    int _jspx_eval_c_005fchoose_005f6 = _jspx_th_c_005fchoose_005f6.doStartTag();
    if (_jspx_eval_c_005fchoose_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t\t\r\n");
        out.write("\t       ");
        if (_jspx_meth_c_005fwhen_005f6(_jspx_th_c_005fchoose_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t   ");
        if (_jspx_meth_c_005fotherwise_005f6(_jspx_th_c_005fchoose_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.el.core.WhenTag _jspx_th_c_005fwhen_005f6 = (org.apache.taglibs.standard.tag.el.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.el.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f6);
    // /jgossip/content/MainPage.jsp(180,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f6.setTest("${empty sessionScope.JRF_USER_TIME_ZONE}");
    int _jspx_eval_c_005fwhen_005f6 = _jspx_th_c_005fwhen_005f6.doStartTag();
    if (_jspx_eval_c_005fwhen_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t    ");
        if (_jspx_meth_fmt_005fmessage_005f52(_jspx_th_c_005fwhen_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t   ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f6);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f52(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f52 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f52.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f52.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f6);
    // /jgossip/content/MainPage.jsp(181,7) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f52.setKey("global.TIME_GMT");
    int _jspx_eval_fmt_005fmessage_005f52 = _jspx_th_fmt_005fmessage_005f52.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f52);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f52);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f6 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f6);
    int _jspx_eval_c_005fotherwise_005f6 = _jspx_th_c_005fotherwise_005f6.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t    ");
        if (_jspx_meth_fmt_005fmessage_005f53(_jspx_th_c_005fotherwise_005f6, _jspx_page_context))
          return true;
        out.write(' ');
        out.write('(');
        if (_jspx_meth_c_005fout_005f13(_jspx_th_c_005fotherwise_005f6, _jspx_page_context))
          return true;
        out.write(")\r\n");
        out.write("\t\t   ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f6);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f53(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.el.fmt.MessageTag _jspx_th_fmt_005fmessage_005f53 = (org.apache.taglibs.standard.tag.el.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.el.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f53.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f6);
    // /jgossip/content/MainPage.jsp(184,7) name = key type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f53.setKey("global.TIME_LOCAL");
    int _jspx_eval_fmt_005fmessage_005f53 = _jspx_th_fmt_005fmessage_005f53.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f53);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f53);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f13 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f6);
    // /jgossip/content/MainPage.jsp(184,47) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f13.setValue("${sessionScope.JRF_TIME_ZONE}");
    int _jspx_eval_c_005fout_005f13 = _jspx_th_c_005fout_005f13.doStartTag();
    if (_jspx_th_c_005fout_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f13);
    return false;
  }
}

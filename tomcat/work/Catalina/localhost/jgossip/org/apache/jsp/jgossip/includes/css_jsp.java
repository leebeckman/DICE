package org.apache.jsp.jgossip.includes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;

public final class css_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/jgossip/includes/../jspf/jsp_header.jspf");
    _jspx_dependants.add("/WEB-INF/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/gossip.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar.release();
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
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

      out.write('\r');
      out.write('\n');
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
      // /jgossip/includes/../jspf/jsp_header.jspf(35,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
      out.write("\r\n");
      out.write("<style type=\"text/css\">\t");
      out.write("\r\n");
      out.write("\t*{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 10px;\r\n");
      out.write("\t}\r\n");
      out.write("\tbody{\r\n");
      out.write("\t\tbackground-color: #");
      if (_jspx_meth_c_005fout_005f1(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t}\r\n");
      out.write("\tblockquote{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tbackground-color:#FFFFE0;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f2(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tpadding:3px;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\ta:link,a:visited{ \r\n");
      out.write("\t\tcolor:#");
      if (_jspx_meth_c_005fout_005f3(_jspx_page_context))
        return;
      out.write("; \r\n");
      out.write("\t\ttext-decoration:underline\r\n");
      out.write("\t}\r\n");
      out.write("\ta:active,a:hover{ \r\n");
      out.write("\t\tcolor:#");
      if (_jspx_meth_c_005fout_005f4(_jspx_page_context))
        return;
      out.write("; \r\n");
      out.write("\t\ttext-decoration:underline\r\n");
      out.write("\t}\r\n");
      out.write("\ta.control:link,a.control:visited,a.control:active,a.control:hover{ \r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f5(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\tfont-weight: 600; \r\n");
      out.write("\t\ttext-decoration:none;\r\n");
      out.write("\t\ttext-transform: uppercase;\r\n");
      out.write("\t}\r\n");
      out.write("\ta.header:link,a.header:visited,a.header:active,a.header:hover{ \r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f6(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\tfont-weight: 600; \r\n");
      out.write("\t\ttext-decoration:none;\r\n");
      out.write("\t\ttext-transform: uppercase;\r\n");
      out.write("\t}\r\n");
      out.write("\ttable,tr,td{\r\n");
      out.write("\t\tempty-cells: show;\r\n");
      out.write("\t}\r\n");
      out.write("    table.tblr_g{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f7(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.t_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f8(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.tb_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f9(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lb_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f10(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.b_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f11(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.r_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f12(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.l_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f13(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.tr_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f14(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lr_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f15(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lrb_o{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f16(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.tb_g{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f17(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lrb_g{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f18(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.tr_g{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f19(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.ltr_g{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f20(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.tl_g{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f21(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.t_g{\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f22(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.top_tab {\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f23(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f24(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\tfont-weight: 600;\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\ttext-align: center;\r\n");
      out.write("\t\tpadding:5px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.bot_tab {\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f25(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f26(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\tfont-weight: 600;\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\ttext-align: center;\r\n");
      out.write("\t\tvertical-align: middle;\r\n");
      out.write("\t\tpadding: 5px;\r\n");
      out.write("\t\twhite-space: nowrap;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.bot_tab_nav {\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f27(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f28(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\tfont-weight: 600;\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\ttext-align: center;\r\n");
      out.write("\t\tvertical-align: middle;\r\n");
      out.write("\t\tpadding: 5px;\r\n");
      out.write("\t\twhite-space: nowrap;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lr_g{\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f29(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lr_g_tb_o{\r\n");
      out.write("\t\tborder-left-color: #");
      if (_jspx_meth_c_005fout_005f30(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f31(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-bottom-color: #");
      if (_jspx_meth_c_005fout_005f32(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f33(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lr_g_t_o{\r\n");
      out.write("\t\tborder-left-color: #");
      if (_jspx_meth_c_005fout_005f34(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f35(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-bottom-color: #");
      if (_jspx_meth_c_005fout_005f36(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f37(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.b_g_t_o{\r\n");
      out.write("\t\tborder-left-color: #");
      if (_jspx_meth_c_005fout_005f38(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f39(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t\tborder-bottom-color: #");
      if (_jspx_meth_c_005fout_005f40(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f41(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.l_g_r_o{\r\n");
      out.write("\t\tborder-left-color: #");
      if (_jspx_meth_c_005fout_005f42(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f43(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-bottom-color: #");
      if (_jspx_meth_c_005fout_005f44(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f45(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lb_g_r_o{\r\n");
      out.write("\t\tborder-left-color: #");
      if (_jspx_meth_c_005fout_005f46(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f47(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-bottom-color: #");
      if (_jspx_meth_c_005fout_005f48(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f49(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.l_o_r_g{\r\n");
      out.write("\t\tborder-left-color: #");
      if (_jspx_meth_c_005fout_005f50(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f51(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-bottom-color: #");
      if (_jspx_meth_c_005fout_005f52(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f53(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.l_o_b_g{\r\n");
      out.write("\t\tborder-left-color: #");
      if (_jspx_meth_c_005fout_005f54(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f55(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t\tborder-bottom-color: #");
      if (_jspx_meth_c_005fout_005f56(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f57(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.nav{\r\n");
      out.write("\t\tborder-right-color: #");
      if (_jspx_meth_c_005fout_005f58(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-top-color: #");
      if (_jspx_meth_c_005fout_005f59(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-style: italic;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\ttext-transform: lowercase;\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f60(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lr_g,td.lr_g_w{\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f61(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\ttd.l_g{\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f62(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.r_g{\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f63(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.b_g{\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f64(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.b_lg{\r\n");
      out.write("\t\tborder-bottom-width: 1px;\r\n");
      out.write("\t\tborder-top-width: 0px;\r\n");
      out.write("\t\tborder-left-width: 0px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f65(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lght,tr.lght,tr.strip1{\r\n");
      out.write("\t\tbackground-color: #");
      if (_jspx_meth_c_005fout_005f66(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.drk,tr.drk,tr.strip0{\r\n");
      out.write("\t\tbackground-color: #");
      if (_jspx_meth_c_005fout_005f67(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.icn{\r\n");
      out.write("\t\ttext-align: center;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lr_g_w{\r\n");
      out.write("\t\tbackground-color: #");
      if (_jspx_meth_c_005fout_005f68(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.txt_b{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f69(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 12px;\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\tpadding: 5px;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lt_g_txt_b{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f70(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 12px;\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\tpadding: 5px;\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 0px;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f71(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd.lrt_g_txt_b{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f72(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 12px;\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\tpadding: 5px;\r\n");
      out.write("\t\tborder-bottom-width: 0px;\r\n");
      out.write("\t\tborder-top-width: 1px;\r\n");
      out.write("\t\tborder-left-width: 1px;\r\n");
      out.write("\t\tborder-right-width: 1px;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f73(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.c_title{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f74(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 12px;\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\ttext-transform: lowercase;\r\n");
      out.write("\t\twhite-space: nowrap;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.caption,input.but,input.but_b,span.caption_t,span.caption_l{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f75(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\ttext-transform: uppercase;\r\n");
      out.write("\t\twhite-space: nowrap;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.caption,span.caption_l{\r\n");
      out.write("\t\tfont-weight: 600;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.caption_l{\r\n");
      out.write("\t\ttext-transform: lowercase;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.caption_t{\r\n");
      out.write("\t\ttext-transform: none;\r\n");
      out.write("\t\tfont-weight: normal;\r\n");
      out.write("\t}\r\n");
      out.write("\ttd,span.txt,span.txt_b,span.nav,span.txt_caption{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f76(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 10px;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.nav{\t\r\n");
      out.write("\t\tpadding-bottom: 2px;\r\n");
      out.write("\t\tpadding-left: 2px;\r\n");
      out.write("\t\tpadding-right: 2px;\r\n");
      out.write("\t\tpadding-top: 3px;\r\n");
      out.write("    }\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\tspan.txt_caption{\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\ttext-transform: lowercase;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.powered_b,span.powered{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f77(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 8px;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.powered_b,span.txt_b{\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t}\r\n");
      out.write("\tspan.motto{\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\tfont-family: \"Times New Roman\", Times, serif;\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f78(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tinput.but{\r\n");
      out.write("\t\tbackground: White;\r\n");
      out.write("\t\tbackground-color: White;\r\n");
      out.write("\t\tborder-width: 0px;\r\n");
      out.write("\t\tborder-style: hidden;\r\n");
      out.write("\t\tcursor: pointer;\r\n");
      out.write("\t}\r\n");
      out.write("\tinput.but_b,input.but_b_cntrl{\r\n");
      out.write("\t\tbackground: White;\r\n");
      out.write("\t\tbackground-color: White;\r\n");
      out.write("\t\tborder-width: 1px;\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f79(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-weight: bold;\r\n");
      out.write("\t\tpadding-left: 15px;\r\n");
      out.write("\t\tpadding-right: 15px;\r\n");
      out.write("\t\tcursor: pointer;\r\n");
      out.write("\t}\r\n");
      out.write("\tinput.file{\r\n");
      out.write("\t\tbackground: White;\r\n");
      out.write("\t\tbackground-color: White;\r\n");
      out.write("\t\tborder-width: 1px;\r\n");
      out.write("\t\tborder-style: solid;\r\n");
      out.write("\t\tborder-color: #");
      if (_jspx_meth_c_005fout_005f80(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-style: italic;\r\n");
      out.write("\t}\r\n");
      out.write("\tinput.but_b_cntrl{\r\n");
      out.write("\t\tpadding-left: 3px;\r\n");
      out.write("\t\tpadding-right: 3px;\r\n");
      out.write("\t\tmargin:3px;\r\n");
      out.write("\t\tfont-style: italic;\r\n");
      out.write("\t}\r\n");
      out.write("\tselect,input,textarea{\r\n");
      out.write("\t\tcolor: #");
      if (_jspx_meth_c_005fout_005f81(_jspx_page_context))
        return;
      out.write(";\r\n");
      out.write("\t\tfont-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("\t\tfont-size: 11px;\r\n");
      out.write("\t\ttext-transform: none;\r\n");
      out.write("\t}\r\n");
      out.write("</style>");
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
    // /jgossip/includes/../jspf/jsp_header.jspf(36,0) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /jgossip/includes/../jspf/jsp_header.jspf(37,0) name = basename type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetBundle_005f0.setBasename("org.jresearch.gossip.resources.lang.lang");
    // /jgossip/includes/../jspf/jsp_header.jspf(37,0) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetBundle_005f0.setScope("session");
    int _jspx_eval_fmt_005fsetBundle_005f0 = _jspx_th_fmt_005fsetBundle_005f0.doStartTag();
    if (_jspx_th_fmt_005fsetBundle_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody.reuse(_jspx_th_fmt_005fsetBundle_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fsetBundle_0026_005fscope_005fbasename_005fnobody.reuse(_jspx_th_fmt_005fsetBundle_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(null);
    // /jgossip/includes/css.jsp(28,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent(null);
    // /jgossip/includes/css.jsp(33,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue("${sessionScope.JRF_STYLE_SETTINGS.bgColor}");
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f2.setParent(null);
    // /jgossip/includes/css.jsp(38,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent(null);
    // /jgossip/includes/css.jsp(47,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f4 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f4.setParent(null);
    // /jgossip/includes/css.jsp(51,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f4.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f4 = _jspx_th_c_005fout_005f4.doStartTag();
    if (_jspx_th_c_005fout_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f5 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f5.setParent(null);
    // /jgossip/includes/css.jsp(55,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f5.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f5 = _jspx_th_c_005fout_005f5.doStartTag();
    if (_jspx_th_c_005fout_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f6 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f6.setParent(null);
    // /jgossip/includes/css.jsp(63,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f6.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f6 = _jspx_th_c_005fout_005f6.doStartTag();
    if (_jspx_th_c_005fout_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f7 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f7.setParent(null);
    // /jgossip/includes/css.jsp(75,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f7.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f7 = _jspx_th_c_005fout_005f7.doStartTag();
    if (_jspx_th_c_005fout_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f8 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f8.setParent(null);
    // /jgossip/includes/css.jsp(83,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f8.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f8 = _jspx_th_c_005fout_005f8.doStartTag();
    if (_jspx_th_c_005fout_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f9 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f9.setParent(null);
    // /jgossip/includes/css.jsp(91,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f9.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f9 = _jspx_th_c_005fout_005f9.doStartTag();
    if (_jspx_th_c_005fout_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f10 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f10.setParent(null);
    // /jgossip/includes/css.jsp(99,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f10.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f10 = _jspx_th_c_005fout_005f10.doStartTag();
    if (_jspx_th_c_005fout_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f11(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f11 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f11.setParent(null);
    // /jgossip/includes/css.jsp(107,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f11.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f11 = _jspx_th_c_005fout_005f11.doStartTag();
    if (_jspx_th_c_005fout_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f11);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f12(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f12 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f12.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f12.setParent(null);
    // /jgossip/includes/css.jsp(115,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f12.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f12 = _jspx_th_c_005fout_005f12.doStartTag();
    if (_jspx_th_c_005fout_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f12);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f13(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f13 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f13.setParent(null);
    // /jgossip/includes/css.jsp(123,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f13.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f13 = _jspx_th_c_005fout_005f13.doStartTag();
    if (_jspx_th_c_005fout_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f13);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f14(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f14 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f14.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f14.setParent(null);
    // /jgossip/includes/css.jsp(131,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f14.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f14 = _jspx_th_c_005fout_005f14.doStartTag();
    if (_jspx_th_c_005fout_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f14);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f15(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f15 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f15.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f15.setParent(null);
    // /jgossip/includes/css.jsp(139,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f15.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f15 = _jspx_th_c_005fout_005f15.doStartTag();
    if (_jspx_th_c_005fout_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f15);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f16(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f16 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f16.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f16.setParent(null);
    // /jgossip/includes/css.jsp(147,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f16.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f16 = _jspx_th_c_005fout_005f16.doStartTag();
    if (_jspx_th_c_005fout_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f16);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f17(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f17 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f17.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f17.setParent(null);
    // /jgossip/includes/css.jsp(155,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f17.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f17 = _jspx_th_c_005fout_005f17.doStartTag();
    if (_jspx_th_c_005fout_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f17);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f18(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f18 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f18.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f18.setParent(null);
    // /jgossip/includes/css.jsp(163,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f18.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f18 = _jspx_th_c_005fout_005f18.doStartTag();
    if (_jspx_th_c_005fout_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f18);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f19(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f19 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f19.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f19.setParent(null);
    // /jgossip/includes/css.jsp(171,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f19.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f19 = _jspx_th_c_005fout_005f19.doStartTag();
    if (_jspx_th_c_005fout_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f19);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f20(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f20 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f20.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f20.setParent(null);
    // /jgossip/includes/css.jsp(179,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f20.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f20 = _jspx_th_c_005fout_005f20.doStartTag();
    if (_jspx_th_c_005fout_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f20);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f21(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f21 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f21.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f21.setParent(null);
    // /jgossip/includes/css.jsp(187,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f21.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f21 = _jspx_th_c_005fout_005f21.doStartTag();
    if (_jspx_th_c_005fout_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f21);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f22(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f22 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f22.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f22.setParent(null);
    // /jgossip/includes/css.jsp(195,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f22.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f22 = _jspx_th_c_005fout_005f22.doStartTag();
    if (_jspx_th_c_005fout_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f22);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f23(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f23 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f23.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f23.setParent(null);
    // /jgossip/includes/css.jsp(202,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f23.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f23 = _jspx_th_c_005fout_005f23.doStartTag();
    if (_jspx_th_c_005fout_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f23);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f23);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f24(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f24 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f24.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f24.setParent(null);
    // /jgossip/includes/css.jsp(204,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f24.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f24 = _jspx_th_c_005fout_005f24.doStartTag();
    if (_jspx_th_c_005fout_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f24);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f24);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f25(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f25 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f25.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f25.setParent(null);
    // /jgossip/includes/css.jsp(216,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f25.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f25 = _jspx_th_c_005fout_005f25.doStartTag();
    if (_jspx_th_c_005fout_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f25);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f25);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f26(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f26 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f26.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f26.setParent(null);
    // /jgossip/includes/css.jsp(218,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f26.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f26 = _jspx_th_c_005fout_005f26.doStartTag();
    if (_jspx_th_c_005fout_005f26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f26);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f26);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f27(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f27 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f27.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f27.setParent(null);
    // /jgossip/includes/css.jsp(232,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f27.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f27 = _jspx_th_c_005fout_005f27.doStartTag();
    if (_jspx_th_c_005fout_005f27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f27);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f27);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f28(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f28 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f28.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f28.setParent(null);
    // /jgossip/includes/css.jsp(234,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f28.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f28 = _jspx_th_c_005fout_005f28.doStartTag();
    if (_jspx_th_c_005fout_005f28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f28);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f28);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f29(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f29 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f29.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f29.setParent(null);
    // /jgossip/includes/css.jsp(248,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f29.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f29 = _jspx_th_c_005fout_005f29.doStartTag();
    if (_jspx_th_c_005fout_005f29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f29);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f29);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f30(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f30 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f30.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f30.setParent(null);
    // /jgossip/includes/css.jsp(256,22) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f30.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f30 = _jspx_th_c_005fout_005f30.doStartTag();
    if (_jspx_th_c_005fout_005f30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f30);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f30);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f31(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f31 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f31.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f31.setParent(null);
    // /jgossip/includes/css.jsp(257,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f31.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f31 = _jspx_th_c_005fout_005f31.doStartTag();
    if (_jspx_th_c_005fout_005f31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f31);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f31);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f32(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f32 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f32.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f32.setParent(null);
    // /jgossip/includes/css.jsp(261,24) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f32.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f32 = _jspx_th_c_005fout_005f32.doStartTag();
    if (_jspx_th_c_005fout_005f32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f32);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f32);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f33(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f33 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f33.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f33.setParent(null);
    // /jgossip/includes/css.jsp(262,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f33.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f33 = _jspx_th_c_005fout_005f33.doStartTag();
    if (_jspx_th_c_005fout_005f33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f33);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f33);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f34(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f34 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f34.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f34.setParent(null);
    // /jgossip/includes/css.jsp(267,22) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f34.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f34 = _jspx_th_c_005fout_005f34.doStartTag();
    if (_jspx_th_c_005fout_005f34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f34);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f34);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f35(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f35 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f35.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f35.setParent(null);
    // /jgossip/includes/css.jsp(268,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f35.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f35 = _jspx_th_c_005fout_005f35.doStartTag();
    if (_jspx_th_c_005fout_005f35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f35);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f35);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f36(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f36 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f36.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f36.setParent(null);
    // /jgossip/includes/css.jsp(272,24) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f36.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f36 = _jspx_th_c_005fout_005f36.doStartTag();
    if (_jspx_th_c_005fout_005f36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f36);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f36);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f37(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f37 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f37.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f37.setParent(null);
    // /jgossip/includes/css.jsp(273,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f37.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f37 = _jspx_th_c_005fout_005f37.doStartTag();
    if (_jspx_th_c_005fout_005f37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f37);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f37);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f38(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f38 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f38.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f38.setParent(null);
    // /jgossip/includes/css.jsp(278,22) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f38.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f38 = _jspx_th_c_005fout_005f38.doStartTag();
    if (_jspx_th_c_005fout_005f38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f38);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f38);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f39(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f39 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f39.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f39.setParent(null);
    // /jgossip/includes/css.jsp(279,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f39.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f39 = _jspx_th_c_005fout_005f39.doStartTag();
    if (_jspx_th_c_005fout_005f39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f39);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f39);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f40(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f40 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f40.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f40.setParent(null);
    // /jgossip/includes/css.jsp(283,24) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f40.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f40 = _jspx_th_c_005fout_005f40.doStartTag();
    if (_jspx_th_c_005fout_005f40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f40);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f40);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f41(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f41 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f41.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f41.setParent(null);
    // /jgossip/includes/css.jsp(284,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f41.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f41 = _jspx_th_c_005fout_005f41.doStartTag();
    if (_jspx_th_c_005fout_005f41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f41);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f41);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f42(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f42 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f42.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f42.setParent(null);
    // /jgossip/includes/css.jsp(289,22) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f42.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f42 = _jspx_th_c_005fout_005f42.doStartTag();
    if (_jspx_th_c_005fout_005f42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f42);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f42);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f43(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f43 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f43.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f43.setParent(null);
    // /jgossip/includes/css.jsp(290,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f43.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f43 = _jspx_th_c_005fout_005f43.doStartTag();
    if (_jspx_th_c_005fout_005f43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f43);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f43);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f44(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f44 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f44.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f44.setParent(null);
    // /jgossip/includes/css.jsp(294,24) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f44.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f44 = _jspx_th_c_005fout_005f44.doStartTag();
    if (_jspx_th_c_005fout_005f44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f44);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f44);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f45(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f45 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f45.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f45.setParent(null);
    // /jgossip/includes/css.jsp(295,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f45.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f45 = _jspx_th_c_005fout_005f45.doStartTag();
    if (_jspx_th_c_005fout_005f45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f45);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f45);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f46(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f46 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f46.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f46.setParent(null);
    // /jgossip/includes/css.jsp(300,22) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f46.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f46 = _jspx_th_c_005fout_005f46.doStartTag();
    if (_jspx_th_c_005fout_005f46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f46);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f46);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f47(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f47 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f47.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f47.setParent(null);
    // /jgossip/includes/css.jsp(301,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f47.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f47 = _jspx_th_c_005fout_005f47.doStartTag();
    if (_jspx_th_c_005fout_005f47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f47);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f47);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f48(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f48 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f48.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f48.setParent(null);
    // /jgossip/includes/css.jsp(305,24) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f48.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f48 = _jspx_th_c_005fout_005f48.doStartTag();
    if (_jspx_th_c_005fout_005f48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f48);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f48);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f49(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f49 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f49.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f49.setParent(null);
    // /jgossip/includes/css.jsp(306,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f49.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f49 = _jspx_th_c_005fout_005f49.doStartTag();
    if (_jspx_th_c_005fout_005f49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f49);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f49);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f50(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f50 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f50.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f50.setParent(null);
    // /jgossip/includes/css.jsp(311,22) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f50.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f50 = _jspx_th_c_005fout_005f50.doStartTag();
    if (_jspx_th_c_005fout_005f50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f50);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f50);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f51(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f51 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f51.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f51.setParent(null);
    // /jgossip/includes/css.jsp(312,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f51.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f51 = _jspx_th_c_005fout_005f51.doStartTag();
    if (_jspx_th_c_005fout_005f51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f51);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f51);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f52(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f52 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f52.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f52.setParent(null);
    // /jgossip/includes/css.jsp(316,24) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f52.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f52 = _jspx_th_c_005fout_005f52.doStartTag();
    if (_jspx_th_c_005fout_005f52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f52);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f52);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f53(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f53 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f53.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f53.setParent(null);
    // /jgossip/includes/css.jsp(317,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f53.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f53 = _jspx_th_c_005fout_005f53.doStartTag();
    if (_jspx_th_c_005fout_005f53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f53);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f53);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f54(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f54 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f54.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f54.setParent(null);
    // /jgossip/includes/css.jsp(322,22) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f54.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f54 = _jspx_th_c_005fout_005f54.doStartTag();
    if (_jspx_th_c_005fout_005f54.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f54);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f54);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f55(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f55 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f55.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f55.setParent(null);
    // /jgossip/includes/css.jsp(323,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f55.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f55 = _jspx_th_c_005fout_005f55.doStartTag();
    if (_jspx_th_c_005fout_005f55.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f55);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f55);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f56(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f56 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f56.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f56.setParent(null);
    // /jgossip/includes/css.jsp(327,24) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f56.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f56 = _jspx_th_c_005fout_005f56.doStartTag();
    if (_jspx_th_c_005fout_005f56.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f56);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f56);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f57(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f57 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f57.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f57.setParent(null);
    // /jgossip/includes/css.jsp(328,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f57.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f57 = _jspx_th_c_005fout_005f57.doStartTag();
    if (_jspx_th_c_005fout_005f57.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f57);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f57);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f58(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f58 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f58.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f58.setParent(null);
    // /jgossip/includes/css.jsp(333,23) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f58.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f58 = _jspx_th_c_005fout_005f58.doStartTag();
    if (_jspx_th_c_005fout_005f58.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f58);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f58);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f59(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f59 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f59.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f59.setParent(null);
    // /jgossip/includes/css.jsp(337,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f59.setValue("${sessionScope.JRF_STYLE_SETTINGS.rowBgDrk}");
    int _jspx_eval_c_005fout_005f59 = _jspx_th_c_005fout_005f59.doStartTag();
    if (_jspx_th_c_005fout_005f59.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f59);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f59);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f60(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f60 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f60.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f60.setParent(null);
    // /jgossip/includes/css.jsp(345,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f60.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f60 = _jspx_th_c_005fout_005f60.doStartTag();
    if (_jspx_th_c_005fout_005f60.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f60);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f60);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f61(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f61 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f61.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f61.setParent(null);
    // /jgossip/includes/css.jsp(352,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f61.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f61 = _jspx_th_c_005fout_005f61.doStartTag();
    if (_jspx_th_c_005fout_005f61.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f61);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f61);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f62(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f62 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f62.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f62.setParent(null);
    // /jgossip/includes/css.jsp(361,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f62.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f62 = _jspx_th_c_005fout_005f62.doStartTag();
    if (_jspx_th_c_005fout_005f62.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f62);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f62);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f63(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f63 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f63.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f63.setParent(null);
    // /jgossip/includes/css.jsp(369,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f63.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f63 = _jspx_th_c_005fout_005f63.doStartTag();
    if (_jspx_th_c_005fout_005f63.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f63);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f63);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f64(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f64 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f64.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f64.setParent(null);
    // /jgossip/includes/css.jsp(377,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f64.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f64 = _jspx_th_c_005fout_005f64.doStartTag();
    if (_jspx_th_c_005fout_005f64.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f64);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f64);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f65(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f65 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f65.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f65.setParent(null);
    // /jgossip/includes/css.jsp(385,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f65.setValue("${sessionScope.JRF_STYLE_SETTINGS.rowBgDrk}");
    int _jspx_eval_c_005fout_005f65 = _jspx_th_c_005fout_005f65.doStartTag();
    if (_jspx_th_c_005fout_005f65.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f65);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f65);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f66(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f66 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f66.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f66.setParent(null);
    // /jgossip/includes/css.jsp(389,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f66.setValue("${sessionScope.JRF_STYLE_SETTINGS.rowBgLght}");
    int _jspx_eval_c_005fout_005f66 = _jspx_th_c_005fout_005f66.doStartTag();
    if (_jspx_th_c_005fout_005f66.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f66);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f66);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f67(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f67 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f67.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f67.setParent(null);
    // /jgossip/includes/css.jsp(392,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f67.setValue("${sessionScope.JRF_STYLE_SETTINGS.rowBgDrk}");
    int _jspx_eval_c_005fout_005f67 = _jspx_th_c_005fout_005f67.doStartTag();
    if (_jspx_th_c_005fout_005f67.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f67);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f67);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f68(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f68 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f68.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f68.setParent(null);
    // /jgossip/includes/css.jsp(398,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f68.setValue("${sessionScope.JRF_STYLE_SETTINGS.bgColor}");
    int _jspx_eval_c_005fout_005f68 = _jspx_th_c_005fout_005f68.doStartTag();
    if (_jspx_th_c_005fout_005f68.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f68);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f68);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f69(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f69 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f69.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f69.setParent(null);
    // /jgossip/includes/css.jsp(401,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f69.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f69 = _jspx_th_c_005fout_005f69.doStartTag();
    if (_jspx_th_c_005fout_005f69.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f69);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f69);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f70(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f70 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f70.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f70.setParent(null);
    // /jgossip/includes/css.jsp(408,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f70.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f70 = _jspx_th_c_005fout_005f70.doStartTag();
    if (_jspx_th_c_005fout_005f70.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f70);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f70);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f71(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f71 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f71.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f71.setParent(null);
    // /jgossip/includes/css.jsp(417,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f71.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f71 = _jspx_th_c_005fout_005f71.doStartTag();
    if (_jspx_th_c_005fout_005f71.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f71);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f71);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f72(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f72 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f72.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f72.setParent(null);
    // /jgossip/includes/css.jsp(421,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f72.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f72 = _jspx_th_c_005fout_005f72.doStartTag();
    if (_jspx_th_c_005fout_005f72.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f72);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f72);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f73(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f73 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f73.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f73.setParent(null);
    // /jgossip/includes/css.jsp(430,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f73.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f73 = _jspx_th_c_005fout_005f73.doStartTag();
    if (_jspx_th_c_005fout_005f73.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f73);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f73);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f74(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f74 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f74.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f74.setParent(null);
    // /jgossip/includes/css.jsp(434,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f74.setValue("${sessionScope.JRF_STYLE_SETTINGS.clmnTitleColor}");
    int _jspx_eval_c_005fout_005f74 = _jspx_th_c_005fout_005f74.doStartTag();
    if (_jspx_th_c_005fout_005f74.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f74);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f74);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f75(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f75 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f75.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f75.setParent(null);
    // /jgossip/includes/css.jsp(442,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f75.setValue("${sessionScope.JRF_STYLE_SETTINGS.border2}");
    int _jspx_eval_c_005fout_005f75 = _jspx_th_c_005fout_005f75.doStartTag();
    if (_jspx_th_c_005fout_005f75.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f75);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f75);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f76(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f76 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f76.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f76.setParent(null);
    // /jgossip/includes/css.jsp(459,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f76.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f76 = _jspx_th_c_005fout_005f76.doStartTag();
    if (_jspx_th_c_005fout_005f76.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f76);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f76);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f77(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f77 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f77.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f77.setParent(null);
    // /jgossip/includes/css.jsp(476,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f77.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f77 = _jspx_th_c_005fout_005f77.doStartTag();
    if (_jspx_th_c_005fout_005f77.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f77);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f77);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f78(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f78 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f78.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f78.setParent(null);
    // /jgossip/includes/css.jsp(487,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f78.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f78 = _jspx_th_c_005fout_005f78.doStartTag();
    if (_jspx_th_c_005fout_005f78.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f78);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f78);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f79(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f79 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f79.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f79.setParent(null);
    // /jgossip/includes/css.jsp(502,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f79.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f79 = _jspx_th_c_005fout_005f79.doStartTag();
    if (_jspx_th_c_005fout_005f79.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f79);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f79);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f80(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f80 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f80.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f80.setParent(null);
    // /jgossip/includes/css.jsp(513,17) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f80.setValue("${sessionScope.JRF_STYLE_SETTINGS.border1}");
    int _jspx_eval_c_005fout_005f80 = _jspx_th_c_005fout_005f80.doStartTag();
    if (_jspx_th_c_005fout_005f80.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f80);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f80);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f81(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_005fout_005f81 = (org.apache.taglibs.standard.tag.el.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_005fout_005f81.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f81.setParent(null);
    // /jgossip/includes/css.jsp(523,10) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f81.setValue("${sessionScope.JRF_STYLE_SETTINGS.textColor}");
    int _jspx_eval_c_005fout_005f81 = _jspx_th_c_005fout_005f81.doStartTag();
    if (_jspx_th_c_005fout_005f81.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f81);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f81);
    return false;
  }
}

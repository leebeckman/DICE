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
<style type="text/css">	<%--TODO use cache --%>
	*{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 10px;
	}
	body{
		background-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.bgColor}"/>;
	}
	blockquote{
		border-style: solid;
		background-color:#FFFFE0;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 1px;
		padding:3px;
	}
	
	a:link,a:visited{ 
		color:#<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>; 
		text-decoration:underline
	}
	a:active,a:hover{ 
		color:#<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>; 
		text-decoration:underline
	}
	a.control:link,a.control:visited,a.control:active,a.control:hover{ 
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 11px;
		font-weight: 600; 
		text-decoration:none;
		text-transform: uppercase;
	}
	a.header:link,a.header:visited,a.header:active,a.header:hover{ 
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 11px;
		font-weight: 600; 
		text-decoration:none;
		text-transform: uppercase;
	}
	table,tr,td{
		empty-cells: show;
	}
    table.tblr_g{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 1px;
	}
	td.t_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 0px;
		border-right-width: 0px;
	}
	td.tb_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-left-width: 0px;
		border-right-width: 0px;
	}
	td.lb_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 0px;
	}
	td.b_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
	}
	td.r_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 1px;
	}
	td.l_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 0px;
	}
	td.tr_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 0px;
		border-right-width: 1px;
	}
	td.lr_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 1px;
	}
	td.lrb_o{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 1px;
	}
	td.tb_g{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 1px;
		border-top-width: 1px;
		border-left-width: 0px;
		border-right-width: 0px;
	}
	td.lrb_g{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 1px;
	}
	td.tr_g{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 0px;
		border-right-width: 1px;
	}
	td.ltr_g{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 1px;
	}
	td.tl_g{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 0px;
	}
	td.t_g{
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 0px;
		border-right-width: 0px;
	}
	td.top_tab {
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 11px;
		font-weight: 600;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 1px;
		text-align: center;
		padding:5px;
	}
	td.bot_tab {
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 11px;
		font-weight: 600;
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 1px;
		text-align: center;
		vertical-align: middle;
		padding: 5px;
		white-space: nowrap;
	}
	td.bot_tab_nav {
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 11px;
		font-weight: 600;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 1px;
		text-align: center;
		vertical-align: middle;
		padding: 5px;
		white-space: nowrap;
	}
	td.lr_g{
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		border-bottom-width: 0px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 1px;
	}
	td.lr_g_tb_o{
		border-left-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		border-left-width: 1px;
		border-right-width: 1px;
		border-bottom-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 1px;
	}
	td.lr_g_t_o{
		border-left-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		border-left-width: 1px;
		border-right-width: 1px;
		border-bottom-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
	}
	td.b_g_t_o{
		border-left-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 1px;
	}
	td.l_g_r_o{
		border-left-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-style: solid;
		border-left-width: 1px;
		border-right-width: 1px;
		border-bottom-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 0px;
	}
	td.lb_g_r_o{
		border-left-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-style: solid;
		border-left-width: 1px;
		border-right-width: 1px;
		border-bottom-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 0px;
	}
	td.l_o_r_g{
		border-left-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		border-left-width: 1px;
		border-right-width: 1px;
		border-bottom-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 0px;
		border-top-width: 0px;
	}
	td.l_o_b_g{
		border-left-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
		border-left-width: 1px;
		border-right-width: 0px;
		border-bottom-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-bottom-width: 1px;
		border-top-width: 0px;
	}
	td.nav{
		border-right-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		border-style: solid;
		border-left-width: 0px;
		border-right-width: 1px;
		border-top-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.rowBgDrk}"/>;
		border-bottom-width: 0px;
		border-top-width: 1px;
		font-family: Arial, Helvetica, sans-serif;
		font-style: italic;
		font-size: 11px;
		font-weight: bold;
		text-transform: lowercase;
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
	}
	td.lr_g,td.lr_g_w{
		border-bottom-width: 0px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 1px;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
	}

	td.l_g{
		border-bottom-width: 0px;
		border-top-width: 0px;
		border-left-width: 1px;
		border-right-width: 0px;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
	}
	td.r_g{
		border-bottom-width: 0px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 1px;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
	}
	td.b_g{
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
	}
	td.b_lg{
		border-bottom-width: 1px;
		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.rowBgDrk}"/>;
		border-style: solid;
	}
	td.lght,tr.lght,tr.strip1{
		background-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.rowBgLght}"/>;
	}
	td.drk,tr.drk,tr.strip0{
		background-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.rowBgDrk}"/>;
	}
	td.icn{
		text-align: center;
	}
	td.lr_g_w{
		background-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.bgColor}"/>;
	}
	td.txt_b{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		font-weight: bold;
		padding: 5px;
	}
	td.lt_g_txt_b{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		font-weight: bold;
		padding: 5px;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 0px;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
	}
	td.lrt_g_txt_b{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		font-weight: bold;
		padding: 5px;
		border-bottom-width: 0px;
		border-top-width: 1px;
		border-left-width: 1px;
		border-right-width: 1px;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		border-style: solid;
	}
	span.c_title{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.clmnTitleColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		font-weight: bold;
		text-transform: lowercase;
		white-space: nowrap;
	}
	span.caption,input.but,input.but_b,span.caption_t,span.caption_l{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border2}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 11px;
		text-transform: uppercase;
		white-space: nowrap;
	}
	span.caption,span.caption_l{
		font-weight: 600;
	}
	span.caption_l{
		text-transform: lowercase;
	}
	span.caption_t{
		text-transform: none;
		font-weight: normal;
	}
	td,span.txt,span.txt_b,span.nav,span.txt_caption{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 10px;
	}
	span.nav{	
		padding-bottom: 2px;
		padding-left: 2px;
		padding-right: 2px;
		padding-top: 3px;
    }
	

	span.txt_caption{
		font-weight: bold;
		text-transform: lowercase;
	}
	span.powered_b,span.powered{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 8px;
	}
	span.powered_b,span.txt_b{
		font-weight: bold;
	}
	span.motto{
		font-size: 11px;
		font-weight: bold;
		font-family: "Times New Roman", Times, serif;
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
	}

	input.but{
		background: White;
		background-color: White;
		border-width: 0px;
		border-style: hidden;
		cursor: pointer;
	}
	input.but_b,input.but_b_cntrl{
		background: White;
		background-color: White;
		border-width: 1px;
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		font-weight: bold;
		padding-left: 15px;
		padding-right: 15px;
		cursor: pointer;
	}
	input.file{
		background: White;
		background-color: White;
		border-width: 1px;
		border-style: solid;
		border-color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.border1}"/>;
		font-style: italic;
	}
	input.but_b_cntrl{
		padding-left: 3px;
		padding-right: 3px;
		margin:3px;
		font-style: italic;
	}
	select,input,textarea{
		color: #<c:out value="${sessionScope.JRF_STYLE_SETTINGS.textColor}"/>;
		font-family: Arial, Helvetica, sans-serif;
		font-size: 11px;
		text-transform: none;
	}
</style>
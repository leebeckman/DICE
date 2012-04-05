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
--%>  <%@ include file="../jspf/jsp_header.jspf"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="org.jresearch.gossip.util.MessageProcessor,
                org.jresearch.gossip.util.HtmlCodec,
                java.util.Iterator,
                java.util.HashMap" %>
<gossip:navBar id="<%=IConst.PAGE.TITLE_NAV_BAR%>" >
	<gossip:navElement>
		<gossip:title>
			<fmt:message key="global.TRICKS"/>
		</gossip:title>
	</gossip:navElement>
</gossip:navBar>
<%@ include file="../jspf/topbar.jspf"%>

<tiles:definition id="sampleTags" page='<%=Configurator.getInstance().get(IConst.CONFIG.MODULE_PREFIX)+"/content/sampleTags.jsp"%>'>
   <tiles:putList name="items">
		<tiles:add>
			[b] <fmt:message key="tags.HTEXT"/>[/b]
		</tiles:add>
		<tiles:add>
			[i] <fmt:message key="tags.HTEXT"/> [/i]
		</tiles:add>
		<tiles:add>
			[i][b] <fmt:message key="tags.HTEXT"/> [/b][/i]
		</tiles:add>
		<tiles:add>
			[url]http://jakarta.apache.org/struts[/url]
		</tiles:add>
		<tiles:add>
			[url=http://jakarta.apache.org/struts]<fmt:message key="tags.HTEXT"/>[/url]
		</tiles:add>
		<tiles:add>
			[img]http://jakarta.apache.org/struts/images/struts-power.gif[/img]
		</tiles:add>
		<tiles:add>
			[hr] <fmt:message key="tags.HTEXT"/> [hr]
		</tiles:add>
		<tiles:add>
			[quote] <fmt:message key="tags.HTEXT"/> [/quote]
		</tiles:add>
		<tiles:add>
			[code] <fmt:message key="tags.HTEXT"/> [/code]
		</tiles:add>
		<tiles:add>
		   [nosmile]
           <%HashMap hm=MessageProcessor.getEmoticonsMap();
	        Iterator it=hm.keySet().iterator();%>
	        <%while(it.hasNext()){
			               String key=(String)it.next();%> 
			          <%=(String)hm.get(key)%>
	         <%}%>
		   [/nosmile]
			<%it=hm.keySet().iterator();%>
	        <%while(it.hasNext()){
			               String key=(String)it.next();%> 
			          <%=(String)hm.get(key)%>
	         <%}%>
	     
		</tiles:add>
	</tiles:putList>
</tiles:definition>

<tiles:insert beanName="sampleTags" flush="true"/>







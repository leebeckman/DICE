<%@ page import="org.apache.log4j.*" %>
<%@ page import="com.ecyrd.jspwiki.*" %>
<%@ page import="com.ecyrd.jspwiki.auth.NoSuchPrincipalException" %>
<%@ page import="com.ecyrd.jspwiki.auth.WikiSecurityException" %>
<%@ page import="com.ecyrd.jspwiki.auth.authorize.Group" %>
<%@ page import="com.ecyrd.jspwiki.auth.authorize.GroupManager" %>
<%@ page errorPage="/Error.jsp" %>
<%@ taglib uri="/WEB-INF/jspwiki.tld" prefix="wiki" %>
<%! 
    public void jspInit()
    {
        wiki = WikiEngine.getInstance( getServletConfig() );
    }
    Logger log = Logger.getLogger("JSPWiki"); 
    WikiEngine wiki;
%>

<%
    // Create wiki context and check for authorization
    WikiContext wikiContext = wiki.createContext( request, WikiContext.CREATE_GROUP );
    if(!wikiContext.hasAccess( response )) return;
    
    // Extract the current user, group name, members and action attributes
    WikiSession wikiSession = wikiContext.getWikiSession();
    GroupManager groupMgr = wiki.getGroupManager();
    Group group = null;
    try 
    {
        group = groupMgr.parseGroup( wikiContext, true );
        pageContext.setAttribute ( "Group", group, PageContext.REQUEST_SCOPE );
    }
    catch ( WikiSecurityException e )
    {
        wikiSession.addMessage( GroupManager.MESSAGES_KEY, e.getMessage() );
        response.sendRedirect( "Group.jsp" );
    }
    
    // Are we saving the group?
    if( "save".equals(request.getParameter("action")) )
    {
        // Validate the group
        groupMgr.validateGroup( wikiContext, group );
        
        try 
        {
            groupMgr.getGroup( group.getName() );
            // Oops! The group already exists. This is mischief!
            wikiSession.addMessage( GroupManager.MESSAGES_KEY, "Group '" + 
                group.getName() + "' already exists. Try another name." );
        }
        catch ( NoSuchPrincipalException e )
        {
            // Group not found; this is good!
        }

        // If no errors, save the group now
        if ( wikiSession.getMessages( GroupManager.MESSAGES_KEY ).length == 0 )
        {
            try
            {
                groupMgr.setGroup( wikiSession, group );
            }
            catch( WikiSecurityException e )
            {
                // Something went horribly wrong! Maybe it's an I/O error...
                wikiSession.addMessage( GroupManager.MESSAGES_KEY, e.getMessage() );
            }
        }
        if ( wikiSession.getMessages( GroupManager.MESSAGES_KEY ).length == 0 )
        {
            response.sendRedirect( "Group.jsp?group=" + group.getName() );
            return;
        }
    }

    // Set the content type and include the response content
    response.setContentType("text/html; charset="+wiki.getContentEncoding() );
    String contentPage = wiki.getTemplateManager().findJSP( pageContext,
                                                            wikiContext.getTemplate(),
                                                            "ViewTemplate.jsp" );

%><wiki:Include page="<%=contentPage%>" />


package edu.rice.rubis.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

public class SubmitChat extends RubisHttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    String chatMessage = request.getParameter("chatMessage");;
    ServletPrinter.addChatMessage(chatMessage);

    String referrer = ((HttpServletRequest) request).getHeader("referer");
    ((HttpServletResponse) response).sendRedirect(referrer);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    doGet(request, response);
  }

  /**
   * Clean up the connection pool.
   */
  public void destroy()
  {
    super.destroy();
  }
  
  public int getPoolSize()
  {
    return Config.SubmitChatPoolSize;
  }
}

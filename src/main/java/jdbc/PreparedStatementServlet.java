/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

@WebServlet("/HelloServlet") 
public class PreparedStatementServlet extends HttpServlet {
	  public void init() throws ServletException
	  {
	  }
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Hello");
        
        try {
          Connection cn = null;
          try{
        	  InitialContext ic = new InitialContext();
        	  Context initialContext = (Context) ic.lookup("java:comp/env");
        	  DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
        	  cn = datasource.getConnection();
        	    
              //cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DrVera","bodnar","6215891");
              PreparedStatement ps = null;
              String sql = "SELECT * FROM Users";
              ps = cn.prepareStatement(sql);
              ResultSet rs = ps.executeQuery();
              out.println("<table>");
              while (rs.next()) {
            	  
            	  out.println("<tr>");
            	  out.println("<td>"+rs.getInt("id")+"</td>");
            	  out.println("<td>"+rs.getString("FirstName")+"</td>");
            	  out.println("</tr>");
              }
              out.println("</table>");

              out.println("COMPLETE");
          }finally {
              if (cn != null)
                  cn.close();
          }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>" + e.toString() + "</p>");
            out.println("<p>" + e.getLocalizedMessage() + "</p>");
        }
        
            out.close();
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public void destroy()
    {
        // do nothing.
    }

}


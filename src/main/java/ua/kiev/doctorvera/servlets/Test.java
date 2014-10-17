/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kiev.doctorvera.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kiev.doctorvera.entity.UserTypes;
import ua.kiev.doctorvera.entity.Users;
import ua.kiev.doctorvera.mysql.MySqlDaoFactory;
import ua.kiev.doctorvera.mysql.UserTypesMySql;

@WebServlet("/HelloServlet") 
public class Test extends HttpServlet {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private final String TABLE_NAME = "UserTypes";
	//private final String SCHEMA = "DrVera";
	
	
	public void init() throws ServletException{};
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
        	  Connection connection = new MySqlDaoFactory().getConnection();
        	  out.println("<p> connection created</p>");
        	  UserTypesMySql  userTypesDao = (UserTypesMySql)new MySqlDaoFactory().getDao(connection, UserTypes.class);
        	  if (userTypesDao == null)out.println("<p>userTypesDao = NULL</p>");
        	  out.println("<p> Dao created</p>");
        	  UserTypes userType = userTypesDao.getByPK(1);
        	  if (userType == null)out.println("<p>userType = NULL</p>");
        	  out.println("<p>" + userType.getId()+ " " + userType.getName() + "</p>");
        	  Collection<Users> usersCurrentType = userType.getUserCollection(); 
        	  for(Users user : usersCurrentType){
        		  out.println("<p>" + user.getId()+ " " + user.getUsername() + "" + user.getFirstName() + "</p>");
        	  }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<p>" + e.getLocalizedMessage() + "</p>");
            out.println("<p>" + e.getStackTrace().toString() + "</p>");
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


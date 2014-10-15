/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kiev.doctorvera.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

@WebServlet("/HelloServlet") 
public class PreparedStatementServlet extends HttpServlet {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String TABLE_NAME = "DrVera.UserTypes";
	private Connection connection;
	
	public void init() throws ServletException{};
	
	private String getTableName(){
		return TABLE_NAME;
	}
	
    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    private String getCreateQuery() {
		DatabaseMetaData meta;
		ResultSet rs;
		String query="INSERT INTO " + getTableName() +" (" ;
		try {
			meta = connection.getMetaData();
			rs = meta.getColumns(null,"DrVera",getTableName(),null);
			int i=0;
			while (rs.next()){
				if(rs.getString("PKCOLUMN_NAME").equals(rs.getString("COLUMN_NAME"))) continue;
				query += rs.getString("COLUMN_NAME") + ",";
				i++;
			}
			query = query.substring(0,(query.length()-1)) + ") VALUES (";
			while(i!=0){
				query += "?,";
				i--;
			}
			query = query.substring(0,(query.length()-1)) + ");";
			return query;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return e.getLocalizedMessage();
		}
    };
    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    private String getUpdateQuery() {
		DatabaseMetaData meta;
		ResultSet rs;
		String query="UPDATE " + getTableName() +" SET " ;
		try {
			meta = connection.getMetaData();
			rs = meta.getColumns(null,"DrVera",getTableName(),null);
			while (rs.next()){
				if(rs.getString("PKCOLUMN_NAME").equals(rs.getString("COLUMN_NAME"))) continue;
				query += rs.getString("COLUMN_NAME") + " = ?, ";
			}
			query = query.substring(0,(query.length()-2)) + "WHERE " + rs.getString("COLUMN_NAME") + " = ?;";

			return query;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			return e.getLocalizedMessage();
		}
    };
   
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
        
        try {
          try{
        	  InitialContext ic = new InitialContext();
        	  Context initialContext = (Context) ic.lookup("java:comp/env");
        	  DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
        	  connection = datasource.getConnection();

            	  out.println(getCreateQuery());
            	  out.println(getUpdateQuery());
          }finally {
              if (connection != null)
            	  connection.close();
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


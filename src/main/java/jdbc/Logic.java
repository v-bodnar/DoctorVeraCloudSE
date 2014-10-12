/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;
import java.sql.*;
import java.util.ResourceBundle;

public class Logic {
     public static Connection getConnection() throws SQLException{
         ResourceBundle resource = ResourceBundle.getBundle("database");
         String url = resource.getString("url");
         String driver = resource.getString("driver");
         String user = resource.getString("user");
         String pass = resource.getString("password");
         try{
             Class.forName(driver).newInstance();
         }catch (ClassNotFoundException e){
             throw new SQLException("Драйвер не загружен");
         }catch (InstantiationException e){
             e.printStackTrace();
         }catch (IllegalAccessException e){
             e.printStackTrace();
         }
         return DriverManager.getConnection(url, user, pass);
     }
}

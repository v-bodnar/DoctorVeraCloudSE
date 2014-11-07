package ua.kiev.doctorvera.filter;

import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// Implements Filter class
public class LogFilter implements Filter  {
   public void  init(FilterConfig config) 
                         throws ServletException{
   }
   public void  doFilter(ServletRequest request, 
                 ServletResponse response,
                 FilterChain chain) 
                 throws java.io.IOException, ServletException {
	   request.setCharacterEncoding("UTF-8");
      // Get the IP address of client machine.   
      String ipAddress = request.getRemoteAddr();

      // Log the IP address and current timestamp.
      System.out.println("IP "+ ipAddress + ", Time "+ new Date().toString());

      // Pass request back down the filter chain
      chain.doFilter(request,response);
   }
   public void destroy( ){
      /* Called before the Filter instance is removed 
      from service by the web container*/
   }
}
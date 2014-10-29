package ua.kiev.doctorvera.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenFilter  implements Filter  {
	   public void  init(FilterConfig config) 
               throws ServletException{
}
public void  doFilter(ServletRequest req, 
       ServletResponse res,
       FilterChain chain) 
       throws java.io.IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
	HttpSession session = request.getSession(false);
	if ((null == session || session.getAttribute("user") == null) && (request.getParameter("login") == null || request.getParameter("password") == null)) {
        System.out.println("redirecting");
		response.sendRedirect("/test/jsp/login.jspx");
    }else{
    	System.out.println("next chain");
    	// Pass request back down the filter chain
    	chain.doFilter(request,response);
    }
	


}
public void destroy( ){
/* Called before the Filter instance is removed 
from service by the web container*/
}
}
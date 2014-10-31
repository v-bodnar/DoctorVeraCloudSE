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

import ua.kiev.doctorvera.manager.Config;
import ua.kiev.doctorvera.manager.Mapping;

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
	final String url = request.getRequestURL().toString();
	final String PROJECT_NAME = Config.getInstance().getProperty(Config.Key.PROJECT_NAME);
	final String LOGIN_PAGE = Mapping.getInstance().getProperty(Mapping.Key.LOGIN_PAGE);
	
	if(url.contains(LOGIN_PAGE)){
    	// Pass request back down the filter chain
		System.out.println("next chain");
    	chain.doFilter(request,response);
	} else if (((null == session || session.getAttribute("user") == null) && 
			(request.getParameter("login") == null || request.getParameter("password") == null))) {
        System.out.println("redirecting to " + LOGIN_PAGE);
		response.sendRedirect("/" + PROJECT_NAME + LOGIN_PAGE);
    }else{
    	// Pass request back down the filter chain
    	System.out.println("next chain");
    	chain.doFilter(request,response);
    }

}
public void destroy( ){
/* Called before the Filter instance is removed 
from service by the web container*/
}
}
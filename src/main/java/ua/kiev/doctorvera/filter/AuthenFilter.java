package ua.kiev.doctorvera.filter;

import java.util.Date;

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
		private static Date lastCall;
		private static String lastIp;
public void  doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
       throws java.io.IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
	HttpSession session = request.getSession(false);
	final String url = request.getRequestURL().toString();
	final String PROJECT_NAME = Config.getInstance().getProperty(Config.Key.PROJECT_NAME);
	final String LOGIN_PAGE = Mapping.getInstance().getProperty(Mapping.Page.LOGIN_PAGE);
	
	if(url.contains(LOGIN_PAGE)){
		bruteReveal(request);
    	// Pass request back down the filter chain
		System.out.println("next chain");
    	chain.doFilter(request,response);
	} else if (((null == session || session.getAttribute("authorizedUserId") == null) && 
			(request.getParameter("login") == null || request.getParameter("password") == null))) {
        System.out.println("redirecting to " + LOGIN_PAGE);
        bruteReveal(request);
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

private void bruteReveal(HttpServletRequest request){
    try {
    	if(lastCall != null && (lastCall.getTime()-new Date().getTime()) <= 30000 && lastIp.equals(getClientIpAddr(request)))
		Thread.sleep(5000);
    	Thread.sleep(1000);
    	lastCall = new Date();
    	lastIp = getClientIpAddr(request);
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	} 
}

private static String getClientIpAddr(HttpServletRequest request) {  
    String ip = request.getHeader("X-Forwarded-For");  
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        ip = request.getHeader("Proxy-Client-IP");  
    }  
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        ip = request.getHeader("WL-Proxy-Client-IP");  
    }  
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        ip = request.getHeader("HTTP_CLIENT_IP");  
    }  
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
    }  
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        ip = request.getRemoteAddr();  
    }  
    return ip;  
}  
}
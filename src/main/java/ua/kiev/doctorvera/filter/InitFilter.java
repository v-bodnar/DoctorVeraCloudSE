package ua.kiev.doctorvera.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// Implements Filter class
public class InitFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {

		//Message message = Message.getInstance();
		//String welcome = message.getMessage(Message.Messages.HEADER_WELCOME);
		//System.out.println("Class Message initialized! " + welcome);

		// Pass request back down the filter chain
		chain.doFilter(request, response);
	}

	public void destroy() {
		/*
		 * Called before the Filter instance is removed from service by the web
		 * container
		 */
	}
}
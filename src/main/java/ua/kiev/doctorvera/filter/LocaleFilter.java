package ua.kiev.doctorvera.filter;

import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ua.kiev.doctorvera.manager.Config;

// Implements Filter class
public class LocaleFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {

		Locale.setDefault(new Locale(Config.getInstance().getProperty(Config.Key.LANGUAGE),Config.getInstance().getProperty(Config.Key.COUNTRY)));

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
package com.example.web.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IEFilter implements Filter {
	protected FilterConfig filterconfig;
	private static Log log = LogFactory.getLog(IEFilter.class);
	public String param;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
	    request.setCharacterEncoding("UTF-8");
		response.setHeader("Expires ", "0s");
		response.setHeader("Cache-Control", "no-store, no-cache, max-age=0, post-check=0, pre-check=0, public, must-revalidate, proxy-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Type", "text/html;charset=EUC-KR");
		response.setHeader("X-UA-Compatible", "IE=Edge,chrome=1");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
		this.param = filterconfig.getInitParameter("param");
		log.debug(param);
	}

}

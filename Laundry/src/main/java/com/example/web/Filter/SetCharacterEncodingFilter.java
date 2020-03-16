package com.example.web.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class SetCharacterEncodingFilter implements Filter {
	protected FilterConfig filterconfig = null;
	protected boolean ignore = true;
	protected String encoding = null;

	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding = null;
		this.filterconfig = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (ignore || (req.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(req);
			if (encoding != null) {

				req.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterconfig = filterconfig;
		String value = filterconfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("yes")) {
			this.ignore = true;
		} else {
			this.ignore = false;
		}
	}

	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}
}

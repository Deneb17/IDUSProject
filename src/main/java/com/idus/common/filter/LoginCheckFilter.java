package com.idus.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.idus.auth.dto.Authorization;

@WebFilter(urlPatterns= {"/myPage/*", "/blog/addPiece", "/blog/wirtePost"})
public class LoginCheckFilter implements Filter {
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		Authorization auth = (Authorization) session.getAttribute("auth");
		
		if (auth == null) {
			res.sendRedirect("/idus/login");
		} else {
			chain.doFilter(request, response);
		}
		
	}
	
}

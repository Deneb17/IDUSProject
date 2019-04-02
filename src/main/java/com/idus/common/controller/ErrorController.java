package com.idus.common.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	// TODO 로그 작업 필요!
	
	private String error404View = "commons/exception/404";
	
	@RequestMapping("/404")
	public String error404RedirectHandler(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_OK);
		return "redirect:/error/404error";
	}
	
	@RequestMapping("/404error")
	public String error404ViewHandler(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_OK);
		return error404View;
	}
	
}

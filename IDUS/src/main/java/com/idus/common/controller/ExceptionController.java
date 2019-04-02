package com.idus.common.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.idus.common.exception.IllegalAccessException;
import com.idus.myPage.exception.GetOrderInformationFailException;


@ControllerAdvice("com.idus")
public class ExceptionController {
	
	@ExceptionHandler(IllegalAccessException.class)
	public String illegalAccessExceptionHandler() {
		return "commons/exception/illegalAccess";
	}
	
	@ExceptionHandler(GetOrderInformationFailException.class)
	public String myOrderNotFoundException() {
		return "myPage/myOrders/myOrdersFail";
	}
	
}

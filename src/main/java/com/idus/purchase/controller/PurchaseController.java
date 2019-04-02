package com.idus.purchase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/purchase")
public class PurchaseController {
	
//	private PurchaseService service;
	
	// 구매 페이지로 이동
	@RequestMapping(method=RequestMethod.GET)
	public String purchaseViewHandler() {
		
		return "";
	}
	
	
	// 실제 결제 수행하는 핸들러
	@RequestMapping(method=RequestMethod.POST)
	public String purchaseHandler() {
		return "";
	}
	
}
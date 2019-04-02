package com.idus.piece.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idus.piece.dto.RecentItems;
import com.idus.piece.service.PieceService;


@Controller
@RequestMapping("/list")
public class PieceController {
	
	@Autowired
	private PieceService service;
	
	// 최근 등록 상품 목록
	@RequestMapping(value="/recent")
	public String recentHandler(Model model) {
		//데이터를 받아서 해당 페이지로 데이터를 보낸다
		List<RecentItems> result = service.selectItems();
		model.addAttribute("result", result);
		return "/list/recentList";
	}
	
	// 최근 등록 상품 무한스크롤 
	@RequestMapping(value="/recentMore")
	@ResponseBody
	public List<RecentItems> recentMoreHandler(HttpServletRequest req) {
		//System.out.println("req.header >>>>>>>>>>>>>>>> "+ req.getHeader());
		int bno = Integer.parseInt(req.getParameter("bno"));
		List<RecentItems> result = service.selectMoreItems(bno);
		return result;
	}
	
	// 인기 상품 목록(나중에)
	@RequestMapping(value="/popular", method=RequestMethod.GET)
	public String popularHandler() {
		
		return "";
	}
	
	
	// 상품 상세 페이지
	@RequestMapping(value="/{pieceNo}", method=RequestMethod.GET)
	public String pieceHandler() {
		
		return "";
	}
	
	
	// 바로구매 페이지로 이동
	@RequestMapping(value="/direct", method=RequestMethod.POST) // /piece/direct
	public String directPurchase() {
		
		return "";
	}
	
}
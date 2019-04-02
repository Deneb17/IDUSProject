package com.idus.blog.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.idus.auth.dto.Authorization;
import com.idus.blog.dto.AddPieceRequest;
import com.idus.blog.dto.PostWritingRequest;
import com.idus.blog.service.BlogService;
import com.idus.common.util.JsonStringBuilder;
import com.idus.common.util.ResponseManager;


@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService service;
	private String addPieceView = "blog/addPiece";
	private String blogView = "blog/blogHome";
	private String writePostView = "blog/writePost";
	private String pieceListView = "blog/pieceList";
	private String postListView = "blog/postList";
	
	
	// 블로그 뷰
	@RequestMapping(value="/{memberNo}", method=RequestMethod.GET)
	public String blogViewHandler(@PathVariable("memberNo") int memberNo, HttpSession session, Model model) {
		
		service.getBlogModel(memberNo, session, model);
		
		return blogView;
	}
	
	
	// 상품 추가 뷰
	@RequestMapping(value="/addPiece", method=RequestMethod.GET)
	public String newPieceViewHandler() {
		return addPieceView;
	}
	
	
	// 상품 추가 처리 핸들러
	@RequestMapping(value="/addPiece", method=RequestMethod.POST)
	public String newPieceHandler(AddPieceRequest addPieceRequest, HttpSession session, HttpServletRequest request) {
		
		boolean isInsertSuccess = false;
		int memberNo = ((Authorization) session.getAttribute("auth")).getMemberNo();
		
		if(addPieceRequest != null) {
			isInsertSuccess = service.addNewPiece(addPieceRequest, session, request);
		}
		
		// TODO 나중에 리다이렉트 페이지 수정 필요.
		if(isInsertSuccess) {
			return "redirect:/blog/" + memberNo;
		} else {
			return "redirect:/error/404error";
		}
		
	}
	
	
	// ajax 이미지 저장 핸들러
	@RequestMapping(value="/saveImage", method=RequestMethod.POST)
	public void pieceImageHandler(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
		
		String imageUrl = null;
		
		if(multipartFile != null) {
			imageUrl = service.saveImage(multipartFile, request);
		}
		
		JsonStringBuilder json = new JsonStringBuilder();
		PrintWriter out = ResponseManager.getJSONWriter(response);
		
		if(imageUrl != null) {
			json.addEntry("isSaved", true);
			json.addEntry("url", imageUrl);
		} else {
			json.addEntry("isSaved", false);
		}
		
		out.write(json.toString());
	}
	
	
	
	// 포스트 작성 페이지 뷰 핸들러
	@RequestMapping(value="/writePost", method=RequestMethod.GET)
	public String writePostViewHandler() {
		return writePostView;
	}
	
	
	
	// 포스트 작성 핸들러
	@RequestMapping(value = "/writePost", method = RequestMethod.POST)
	public String writePostHandler(PostWritingRequest postWritingRequest, HttpSession session) {
		
		boolean isWriteSuccess = service.writePost(postWritingRequest, session);
		int memberNo = ((Authorization) session.getAttribute("auth")).getMemberNo();
		
		// TODO 리다이렉트 주소 지정 필요!
		if(isWriteSuccess) {
			return "redirect:/blog/" + memberNo;
		} else {
			return "";
		}
	}
	
	
	// 상품 리스트 뷰
	@RequestMapping(value="{memberNo}/pieceList", method=RequestMethod.GET)
	public String pieceListViewHandler(
			@PathVariable("memberNo") int memberNo, 
			@RequestParam(value="pieceName", required=false) String pieceName,
			HttpSession session, Model model) {
		
		service.setPieceListModel(memberNo, pieceName, session, model);
		
		return pieceListView;
	}
	
	
	// 상품 게시판 AJAX 핸들러
	@RequestMapping(value="/morePieces", method=RequestMethod.POST)
	public void morePieceHandler(
			int pageNo, 
			int artistNo, 
			@RequestParam(value="pieceName", required=false) String pieceName, HttpServletResponse response) {
		
		JsonStringBuilder json = service.getMorePieces(pageNo, artistNo, pieceName);
		PrintWriter out = ResponseManager.getJSONWriter(response);
		
		if(json != null) {
			out.write(json.toString());
		}
		
	}
	
	
	// 게시판 뷰 핸들러
	@RequestMapping(value="{artistNo}/postList", method=RequestMethod.GET)
	public String postListViewHandler(
			@PathVariable("artistNo") int artistNo,
			@RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo, 
			@RequestParam(value="title", required=false) String title, 
			HttpSession session, Model model) {
		
		service.getPostListModel(artistNo, pageNo, title, session, model);
		
		return postListView;
	}
	
	
}
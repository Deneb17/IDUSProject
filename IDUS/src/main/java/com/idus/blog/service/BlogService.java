package com.idus.blog.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.idus.auth.dto.Authorization;
import com.idus.auth.exceptions.FileSaveFailException;
import com.idus.blog.dao.BlogDao;
import com.idus.blog.dto.AddPieceRequest;
import com.idus.blog.dto.ArtistInfo;
import com.idus.blog.dto.Options;
import com.idus.blog.dto.Piece;
import com.idus.blog.dto.PieceImage;
import com.idus.blog.dto.PieceInfo;
import com.idus.blog.dto.Post;
import com.idus.blog.dto.PostInfo;
import com.idus.blog.dto.PostWritingRequest;
import com.idus.common.exception.IllegalAccessException;
import com.idus.common.util.JsonStringBuilder;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao dao;

	public String saveImage(MultipartFile multipartFile, HttpServletRequest request) {
		
		String imagePath = null;
		
		LocalDateTime time = LocalDateTime.now();
		String today = time.getYear() + "_" + time.getMonthValue() + "_" + time.getDayOfMonth();
		String dirPath = request.getServletContext().getRealPath("/resources/user/image/" + today);
		System.out.println(dirPath);
		
		UUID uuid = UUID.randomUUID();
		String fileName = "";
		
		if(!multipartFile.isEmpty()) {
			
			fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();
			File file = new File(dirPath, fileName);
			
			if(!file.exists()) {
				file.mkdirs();
			}
			
			try {
				multipartFile.transferTo(file);
			} catch (IOException e) {
				throw new FileSaveFailException("게시판 이미지 저장 실패");
			}
			
			imagePath = request.getContextPath() + "/resources/user/image/" + today + "/" + fileName;
			System.out.println(imagePath);
		}
		
		return imagePath;
	}

	
	@Transactional
	public boolean addNewPiece(AddPieceRequest addPieceRequest, HttpSession session, HttpServletRequest request) {
		
		boolean isInsertSuccess = false;
		MultipartFile thumbnailFile = addPieceRequest.getThumbnail();
		List<MultipartFile> imagesFile = addPieceRequest.getImages();
		Piece piece = new Piece();
		piece.setArtistNo(((Authorization)session.getAttribute("auth")).getMemberNo());
		piece.setTitle(addPieceRequest.getTitle());
		piece.setPieceName(addPieceRequest.getPieceName());
		piece.setPrice(addPieceRequest.getPrice());
		piece.setDeliveryCharge(addPieceRequest.getDeliveryCharge());
		piece.setDiscount(addPieceRequest.getDiscount());
		piece.setDescription(addPieceRequest.getDescription());
		piece.setCreatedDate(LocalDateTime.now());
		
		// 상품 데이터 저장
		int pieceInsertNum = dao.insertPiece(piece);
		int pieceNo = piece.getPieceNo();
		
		
		// 상품 추가 요청으로 부터 옵션 목록을 받아와 데이터베이스에 저장
		
		// 옵션이 들어갈 리스트
		List<Options> options = new ArrayList<Options>();
		
		// 요청의 옵션을 리스트에 추가
		for(String o : addPieceRequest.getOptions()) {
			Options option = new Options();
			option.setPieceNo(pieceNo);
			option.setOptions(o);
			options.add(option);
		}
		
		// 옵션을 데이터베이스에 저장
		int optionsInsertNum = 0;
		for(Options option : options) {
			optionsInsertNum += dao.insertOptions(option);
		}
		
		
		// 상품 이미지 저장
		
		// 썸네일 이미지
		PieceImage thumbnailImage = new PieceImage();
		thumbnailImage.setPieceNo(pieceNo);
		thumbnailImage.setUrl(saveImage(thumbnailFile, request));
		thumbnailImage.setThumbnail(true);
		
		// 일반 이미지 배열
		List<PieceImage> images = new ArrayList<PieceImage>();
		
		for(MultipartFile file : imagesFile) {
			PieceImage image = new PieceImage();
			image.setPieceNo(pieceNo);
			image.setUrl(saveImage(file, request));
			image.setThumbnail(false);
			images.add(image);
		}
		
		int imageInsertNum = dao.insertPieceImage(thumbnailImage);
		
		for(PieceImage i : images) {
			imageInsertNum += dao.insertPieceImage(i);
		}
		
		if(pieceInsertNum > 0 && imageInsertNum > 0 && imageInsertNum == 1 + imagesFile.size() && optionsInsertNum > 0) {
			isInsertSuccess = true;
		}		
		
		return isInsertSuccess;
	}

	
	// 블로그 모델 서비스
	public void getBlogModel(int memberNo, HttpSession session, Model model) {
		
		// 데이터베이스에서 작가 검색
		ArtistInfo artist = dao.selectArtistByMemberNo(memberNo);
		
		// 해당 회원 번호가 존재하지 않는경우
		if(artist == null) {
			throw new IllegalAccessException();  // 존재하지 않는 회원 번호로 잘못된 접근
		}
		
		Authorization auth = (Authorization) session.getAttribute("auth");
		
		// 현재 로그인한 사용자가 블로그 관리자인지 확인
		if(auth == null) {
			artist.setManager(false);
		} else if(auth != null && auth.getMemberNo() == memberNo) {
			artist.setManager(true);
		}
		
		// 최근 등록된 상품 12개를 데이터 베이스에서 검색
		List<PieceInfo> pieceList = dao.selectRecentPieces(memberNo);
		
		// 최근 등록된 포스트 10개를 데이터베이스에서 검색
		List<PostInfo> postList = dao.selectRecentPosts(memberNo);
		
		model.addAttribute("artist", artist);
		model.addAttribute("pieceList", pieceList);
		model.addAttribute("postList", postList);
		
	}


	@Transactional
	public boolean writePost(PostWritingRequest postWritingRequest, HttpSession session) {
		
		boolean isWriteSuccess = false;
		
		if(postWritingRequest == null) {
			return isWriteSuccess;
		}
		
		Authorization auth = (Authorization) session.getAttribute("auth");
		
		Post post = new Post();
		post.setMemberNo(auth.getMemberNo());
		post.setTitle(postWritingRequest.getTitle());
		post.setContent(postWritingRequest.getContent());
		post.setCreatedDate(LocalDateTime.now());
		
		int postNo = dao.insertPost(post);
		
		if(postNo > 0) {
			isWriteSuccess = true;
		}
		
		return isWriteSuccess;
	}

	
	
	public void setPieceListModel(int memberNo, HttpSession session, Model model) {
		
		// 데이터베이스에서 작가 검색
		ArtistInfo artist = dao.selectArtistByMemberNo(memberNo);
		
		// 해당 회원 번호가 존재하지 않는경우
		if(artist == null) {
			throw new IllegalAccessException();  // 존재하지 않는 회원 번호로 잘못된 접근
		}
		
		Authorization auth = (Authorization) session.getAttribute("auth");
		
		// 현재 로그인한 사용자가 블로그 관리자인지 확인
		if(auth == null) {
			artist.setManager(false);
		} else if(auth != null && auth.getMemberNo() == memberNo) {
			artist.setManager(true);
		}
		
		// 상품 리스트 12개 검색
		List<PieceInfo> pieceList = dao.selectRecentPieces(memberNo);
		
		model.addAttribute("artist", artist);
		model.addAttribute("pieceList", pieceList);
	}


	public JsonStringBuilder getMorePieces(int pageNo, int artistNo) {
		
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("artistNo", artistNo);
		param.put("pageNo", pageNo);
		
		List<PieceInfo> pieceList = dao.selectMorePieces(param);
		JsonStringBuilder json = new JsonStringBuilder();
		
		json.addEntry("pieceList", pieceList);
		
		return json;
	}
	
}

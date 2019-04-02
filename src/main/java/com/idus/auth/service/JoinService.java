package com.idus.auth.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.idus.auth.dao.JoinDao;
import com.idus.auth.dto.JoinRequest;
import com.idus.auth.dto.Member;
import com.idus.auth.exceptions.FailToJoinException;
import com.idus.auth.exceptions.FileSaveFailException;

@Service
public class JoinService {
	
	@Autowired
	private JoinDao joinDao;
	
	
	
	
	// 회원가입 서비스
	@Transactional
	public boolean doJoin(JoinRequest joinRequest, HttpServletRequest request) {
		
		boolean isInsertSuccess = false;
		
		// 썸네일 이미지 업로드
		String thumbnailPath = uploadImage(joinRequest, request);
		
		Member member = new Member();
		
		// joinrequest객체의 정보를 member 객체로 이동
		member.getInformation(joinRequest);
		if(thumbnailPath != null) {
			member.setThumbnail(thumbnailPath);
		}
		
		// 회원가입 양식에 작성된 회원 정보를 데이터베이스에 저장
		int insertedNum = joinDao.insertNewMember(member);
		
		if(insertedNum > 0) {
			// 회원 정보 저장에 성공 시
			isInsertSuccess = true;
		} else {
			// 회원 정보 저장에 실패 시
			throw new FailToJoinException("회원 가입에 실패 하였습니다.");
		}
		
		return isInsertSuccess;
	}
	
	
	
	
	// 썸네일 이미지 저장 서비스
	private String uploadImage(JoinRequest joinRequest, HttpServletRequest request) {
		
		// 썸네일 파일이 저장될 상대경로
		String thumbnailPath = null;
		
		// 오늘 날짜를 이용하여 파일이 업로드 될 디렉토리 경로 생성
		LocalDateTime time = LocalDateTime.now();
		String today = time.getYear() + "_" + time.getMonthValue() + "_" + time.getDayOfMonth();
		String dirPath = request.getServletContext().getRealPath("/resources/user/image/userThumbnail/" + today);  // 절대경로
		
		// UUID를 이용하여 중복되지 않는 파일명 생성
		MultipartFile multipartFile = joinRequest.getThumbnail();
		UUID uuid = UUID.randomUUID();
		String fileName = "";
		
		if(multipartFile != null && !multipartFile.isEmpty()) {
			
			// UUID를 이용하여 중복되지 않는 파일명 생성
			fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();
			
			// 파일객체 생성
			File file = new File(dirPath, fileName);
			
			if(!file.exists()) {
				file.mkdirs();
			}
			
			try {
				// 저장
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
				throw new FileSaveFailException("사용자 썸네일 저장에 실패 하였습니다.");
			}
			
			thumbnailPath = request.getContextPath() + "/resources/user/image/userThumbnail/" + today + "/" + fileName;
			
		} else {
			thumbnailPath = request.getContextPath() + "/resources/user/image/userThumbnail/default_thumbnail.png";
		}
		
		return thumbnailPath;
		
	}
	
	
	
	
	// 이메일 중복 체크 서비스
	public boolean emailDuplicationCheck(String email) {
		
		boolean isDuplicated = false;
		
		// 이메일로 회원 정보 검색
		Member member = joinDao.selectMemberByEmail(email);
		
		// 일치하는 회원이 존재 할 시
		if(member != null) {
			isDuplicated = true;
		}
		return isDuplicated;
	}
	
	
	
	// 닉네임 중복 체크 서비스
	public boolean nickNameDuplicationCheck(String nickName) {

		boolean isDuplicated = false;

		// 닉네임으로 회원 정보 검색
		Member member = joinDao.selectMemberByNickName(nickName);

		// 일치하는 회원이 존재 할 시
		if (member != null) {
			isDuplicated = true;
		}

		return isDuplicated;
	}
	
}

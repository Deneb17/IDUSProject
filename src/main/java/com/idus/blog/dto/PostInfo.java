package com.idus.blog.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostInfo {
	
	private static DateTimeFormatter todayFormat = DateTimeFormatter.ofPattern("HH:mm");
	private static DateTimeFormatter notTodayFormat = DateTimeFormatter.ofPattern("yy/MM/dd");
	private int postNo;
	private String title;
	private int commentCount;
	private String nickName;
	private LocalDateTime createdDate;
	
	public int getPostNo() {
		return postNo;
	}
	
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getCreatedDate() {
		if(isToday(createdDate)) return todayFormat.format(createdDate);
		return notTodayFormat.format(createdDate);
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	private static boolean isToday(LocalDateTime localDateTime) {
		LocalDate today = LocalDate.now();
		if(today.getMonth() == localDateTime.getMonth() && today.getYear() == localDateTime.getYear() && today.getDayOfMonth() == localDateTime.getDayOfMonth()) {
			return true;
		}
		return false;
	}
	
}
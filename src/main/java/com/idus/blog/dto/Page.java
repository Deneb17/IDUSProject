package com.idus.blog.dto;

import java.util.List;

// 블로그 페이지 정보를 담는 객체
public class Page {
	
	private boolean hasBefore;
	private boolean hasNext;
	private List<Integer> paging;
	private int currentBundle;
	
	public boolean isHasBefore() {
		return hasBefore;
	}
	
	public void setHasBefore(boolean hasBefore) {
		this.hasBefore = hasBefore;
	}
	
	public boolean isHasNext() {
		return hasNext;
	}
	
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	public List<Integer> getPaging() {
		return paging;
	}

	public void setPaging(List<Integer> paging) {
		this.paging = paging;
	}

	public int getCurrentBundle() {
		return currentBundle;
	}
	
	public void setCurrentBundle(int currentBundle) {
		this.currentBundle = currentBundle;
	}

}
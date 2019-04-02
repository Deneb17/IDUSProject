package com.idus.blog.dto;

public class PieceImage {
	
	private int imageNo;
	private int pieceNo;
	private String url;
	private boolean isThumbnail;
	
	public int getImageNo() {
		return imageNo;
	}
	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}
	public int getPieceNo() {
		return pieceNo;
	}
	public void setPieceNo(int pieceNo) {
		this.pieceNo = pieceNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isThumbnail() {
		return isThumbnail;
	}
	public void setThumbnail(boolean isThumbnail) {
		this.isThumbnail = isThumbnail;
	}
	
}

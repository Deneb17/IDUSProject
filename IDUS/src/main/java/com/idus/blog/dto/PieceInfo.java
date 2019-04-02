package com.idus.blog.dto;

public class PieceInfo {
	
	private int pieceNo;
	private String thumbnail;
	private String artist;
	private String title;
	private int discount;
	private int price;
	private int star;
	
	public int getPieceNo() {
		return pieceNo;
	}
	
	public void setPieceNo(int pieceNo) {
		this.pieceNo = pieceNo;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
	
}

package com.idus.blog.dto;

import java.time.LocalDateTime;

public class Piece {
	
	private int pieceNo;
	private int artistNo;
	private String title;
	private String pieceName;
	private long price;
	private long deliveryCharge;
	private int discount;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private long likeIt;
	private int star;
	
	public int getPieceNo() {
		return pieceNo;
	}
	public void setPieceNo(int pieceNo) {
		this.pieceNo = pieceNo;
	}
	public int getArtistNo() {
		return artistNo;
	}
	public void setArtistNo(int artistNo) {
		this.artistNo = artistNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPieceName() {
		return pieceName;
	}
	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(long deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public long getLikeIt() {
		return likeIt;
	}
	public void setLikeIt(long likeIt) {
		this.likeIt = likeIt;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
}
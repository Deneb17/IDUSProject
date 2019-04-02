package com.idus.myPage.dto;

public class Order {

	private int orderNo; // 주문 번호
	private int artistNo; // 작가 번호
	private int customerNo;// 구매자 번호
	private int pieceNo;// 작품 번호
	private int optionNo;//옵션 번호
	private int statusNo;// 상태 번호
	private int amount;// 수량
	private int price;// 전체 가격
	private String orderDate;// 주문일
	private boolean refundRequest;// 환불신청
	private boolean confirm;// 처리완료
	private String confirmDate;// 처리완료 일자
	
	
	public int getOrderNo() {
		return orderNo;
	}
	public int getArtistNo() {
		return artistNo;
	}
	public void setArtistNo(int artistNo) {
		this.artistNo = artistNo;
	}
	public int getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}
	public int getPieceNo() {
		return pieceNo;
	}
	public void setPieceNo(int pieceNo) {
		this.pieceNo = pieceNo;
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public int getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public boolean isRefundRequest() {
		return refundRequest;
	}
	public void setRefundRequest(boolean refundRequest) {
		this.refundRequest = refundRequest;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	
	
	
}

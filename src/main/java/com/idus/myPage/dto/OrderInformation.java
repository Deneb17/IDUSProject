package com.idus.myPage.dto;

import java.util.List;

public class OrderInformation {

	private String orderDate; // 주문 일자
	private int orderNo; // 주문 번호
	private int customerNo; // 구매자 번호
	private int pieceNo; // 작품 번호
	private int amount; // 수량
	private int price; // 전체 가격

	public void getOrderInformation(List<Order> order) {
		
		this.orderDate = ((Order) order).getOrderDate();
		this.orderNo = ((Order) order).getOrderNo();
		this.customerNo = ((Order) order).getCustomerNo();
		this.pieceNo = ((Order) order).getPieceNo();
		this.amount = ((Order) order).getAmount();
		this.price = ((Order) order).getPrice();
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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

}

package com.model2.mvc.service.domain;

import java.sql.Date;

public class Purchase {
	
	private User buyer; //구매자정보
	private String divyAddr; // 배달주소
	private String divyDate; // 희망날짜
	private String divyRequest; // 배송요청
	private Date orderDate; // 구매일자
	private String paymentOption; // 지불방식
	private Product purchaseProd; // 구매정보
	private String receiverName; // 구매자이름
	private String receiverPhone; // 구매자전화번호
	private String tranCode; // 구매상태코드
	private int tranNo; // 구매번호
	
	public Purchase(){
	}
	
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public String getDivyAddr() {
		return divyAddr;
	}
	public void setDivyAddr(String divyAddr) {
		this.divyAddr = divyAddr;
	}
	public String getDivyDate() {
		return divyDate;
	}
	public void setDivyDate(String divyDate) {
		this.divyDate = divyDate;
	}
	public String getDivyRequest() {
		return divyRequest;
	}
	public void setDivyRequest(String divyRequest) {
		this.divyRequest = divyRequest;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public Product getPurchaseProd() {
		return purchaseProd;
	}
	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public int getTranNo() {
		return tranNo;
	}
	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	
	@Override
	public String toString() {
		return "Purchase [buyer=" + buyer + ", divyAddr=" + divyAddr
				+ ", divyDate=" + divyDate + ", divyRequest=" + divyRequest
				+ ", orderDate=" + orderDate + ", paymentOption="
				+ paymentOption + ", purchaseProd=" + purchaseProd
				+ ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", tranCode=" + tranCode + ", tranNo="
				+ tranNo + "]";
	}
}
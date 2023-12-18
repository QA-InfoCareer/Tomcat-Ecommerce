package com.myshopping.myshopping.dto;

public class BillCopyDto {
	private String id;
	private double totalPrice;
	private double gst;
	private double deleveryCharge;
	private double productPrice;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	public double getDeleveryCharge() {
		return deleveryCharge;
	}
	public void setDeleveryCharge(double deleveryCharge) {
		this.deleveryCharge = deleveryCharge;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
}

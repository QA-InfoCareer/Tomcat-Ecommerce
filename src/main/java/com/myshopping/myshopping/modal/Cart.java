package com.myshopping.myshopping.modal;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart")
public class Cart {
	@Id
	private String id;
	private String name;
	private String frentCamara;
	private String backCamara;
	private String battery;
	private String color;
	private String display;
	private Double rate;
	private String ram;
	private String storage;
	private String message;
	private String productPic;
	private String processer;
	private String brand;
	private String product;
	private int productBuyId;	
	@DBRef
	private User user;
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	@DBRef	
	private Bill bill;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrentCamara() {
		return frentCamara;
	}
	public void setFrentCamara(String frentCamara) {
		this.frentCamara = frentCamara;
	}
	public String getBackCamara() {
		return backCamara;
	}
	public void setBackCamara(String backCamara) {
		this.backCamara = backCamara;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getProductPic() {
		return productPic;
	}
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	public String getProcesser() {
		return processer;
	}
	public void setProcesser(String processer) {
		this.processer = processer;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getProductBuyId() {
		return productBuyId;
	}
	public void setProductBuyId(int productBuyId) {
		this.productBuyId = productBuyId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
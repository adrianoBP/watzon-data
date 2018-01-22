package it.sorintlab.watzondata.frontend;

import org.springframework.beans.BeanUtils;

public class APICustomerPrice {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getProvinceState() {
		return provinceState;
	}
	public void setProvinceState(String provinceState) {
		this.provinceState = provinceState;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	private String name;
	private String street;
	private String city;
	private String zip;
	private String provinceState;
	private String country;
	private String note;
	private String website;
	private String taxCode;
	
	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	private String currency;
	
	public static APICustomerPrice fromApiCustomer(ApiCustomer customer, ApiCustomerProduct product) {
		APICustomerPrice customerPrice = new APICustomerPrice();
		BeanUtils.copyProperties(customer, customerPrice);
		customerPrice.setCurrency(product.getCurrency());
		customerPrice.setPrice(product.getPrice());
		
		return customerPrice;
	}
	
}

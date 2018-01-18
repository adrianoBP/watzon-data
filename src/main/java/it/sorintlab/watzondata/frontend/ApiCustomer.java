package it.sorintlab.watzondata.frontend;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import it.sorintlab.watzondata.domain.Customer;


public class ApiCustomer {

	
	
	private int id;
	private String name;
	private String street;
	private String city;
	private String zip;
	private String provinceState;
	private String country;
	private String note;
	private String website;
	private String taxCode;
	private List<Integer> products;
	
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
	public List<Integer> getProducts() {
		return products;
	}
	public void setProducts(List<Integer> products) {
		this.products = products;
	}
	
	public static ApiCustomer fromDomain(Customer domain){
		ApiCustomer frontend = new ApiCustomer();
		BeanUtils.copyProperties(domain, frontend,"products","city", "version");
		frontend.city = domain.getCity().getName();
		frontend.country = domain.getCity().getCountry();
		frontend.provinceState= domain.getCity().getProvince_state();
		frontend.zip = domain.getCity().getZip();
		frontend.products = domain.getProducts().stream().map(prod -> prod.getId().getProduct().getId()).collect(Collectors.toList());
		return frontend;
		
	}
	
	//public static String toUrl(Customer customer)

}

package it.sorintlab.watzondata.frontend;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import it.sorintlab.watzondata.domain.Customer;
import it.sorintlab.watzondata.domain.CustomerProduct;
import it.sorintlab.watzondata.domain.CustomerProductPk;
import it.sorintlab.watzondata.domain.Product;

public class ApiCustomerProduct {
	private int customerId;
	private int productId;
	private double price;
	private String currency;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
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
	
	public static ApiCustomerProduct fromDomain(CustomerProduct domain){
		ApiCustomerProduct frontend = new ApiCustomerProduct();
		frontend.price  = domain.getPrice();
		frontend.currency = domain.getCurrency();
		frontend.customerId = domain.getId().getCustomer().getId();
		frontend.productId = domain.getId().getProduct().getId();
		return frontend;
	}


}

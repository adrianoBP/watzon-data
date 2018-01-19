package it.sorintlab.watzondata.frontend;

import it.sorintlab.watzondata.domain.Product;

public class APIProduct {

	private int id;
	private String code;
	private String name;
	private String description;
	
	public APIProduct() {}
	
	public APIProduct(int id, String code, String name, String description) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public static APIProduct fromBackend(Product domain) {
		APIProduct product = new APIProduct();
		
		product.setId(domain.getId());
		product.setCode(domain.getCode());
		product.setName(domain.getName());
		product.setDescription(domain.getDescription());
		
		return product;
	}
	
	public Product toBackend() {
		Product domain = new Product();
		
		domain.setId(this.getId());
		domain.setCode(this.getCode());
		domain.setName(this.getName());
		domain.setDescription(this.getDescription());
		
		return domain;
	}
}

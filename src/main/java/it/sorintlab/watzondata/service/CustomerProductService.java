package it.sorintlab.watzondata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sorintlab.watzondata.backend.CustomerProduct;
import it.sorintlab.watzondata.backend.CustomerProductPk;
import it.sorintlab.watzondata.frontend.ApiCustomerProduct;
import it.sorintlab.watzondata.repository.CustomerProductRepository;
import it.sorintlab.watzondata.repository.CustomerRepository;
import it.sorintlab.watzondata.repository.ProductsRepository;

@Service
public class CustomerProductService {

	//Repository for toBackend
	@Autowired
	CustomerRepository customersRepo;
	@Autowired
	CustomerProductRepository customersProductRepo;
	@Autowired
	ProductsRepository productsRepo;	
		
	public CustomerProduct toBackend(ApiCustomerProduct frontend) {
		CustomerProduct backend = new CustomerProduct();
		backend.setCurrency(frontend.getCurrency());
		backend.setPrice(frontend.getPrice());
		//FAKE VERSION NUMBER
		backend.setVersion(-87654321);
		backend.setId(new CustomerProductPk(customersRepo.findOne(frontend.getCustomerId()),productsRepo.findOne(frontend.getProductId())));
		
		return backend;
	}
}

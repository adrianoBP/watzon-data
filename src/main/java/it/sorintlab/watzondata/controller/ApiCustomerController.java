package it.sorintlab.watzondata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sorintlab.watzondata.domain.Customer;
import it.sorintlab.watzondata.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ApiCustomerController {

	@GetMapping("/customers")
	public List<Customer> list() {
		return java.util.Collections.emptyList();
	}
	
	

}

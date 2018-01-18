package it.sorintlab.watzondata.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import it.sorintlab.watzondata.domain.Customer;
import it.sorintlab.watzondata.frontend.ApiCustomer;
import it.sorintlab.watzondata.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class ApiCustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping(value = "/customers", params = "full")
	public List<ApiCustomer> listFull() {
		ArrayList<Customer> temp = new ArrayList<>();
		customerRepository.findAll().forEach(temp::add);
		return temp.stream().map(c -> ApiCustomer.fromDomain(c)).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/customers")
	public List<String> list() {
		ArrayList<Customer> temp = new ArrayList<>();
		customerRepository.findAll().forEach(temp::add);
		return temp.stream().map(c -> new String("/api/customers/" + c.getId())).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/customers/{id}")
	public ApiCustomer getCustomer(@PathVariable("id") int id){
		return ApiCustomer.fromDomain(customerRepository.findOne(id));
	}
	

}

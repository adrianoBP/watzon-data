package it.sorintlab.watzondata.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.backend.Customer;
import it.sorintlab.watzondata.frontend.APIContact;
import it.sorintlab.watzondata.frontend.ApiCustomer;
import it.sorintlab.watzondata.repository.ContactRepository;
import it.sorintlab.watzondata.repository.CustomerRepository;
import it.sorintlab.watzondata.service.ContactService;

@RestController
@RequestMapping("/api")
public class ApiCustomerController {

	@Autowired
	private CustomerRepository customerRepository;	
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private ContactService contactService;
	/*
	@GetMapping(value = "/contacts")
	public List<APIContact> listAllcontacts() {
		ArrayList<Contact> temp = new ArrayList<>();
		contactRepository.findAll().forEach(temp::add);
		return temp.stream().map(c -> APIContact.fromBackend(c)).collect(Collectors.toList());
	}*/
	
	@GetMapping(value = "/customers", params = "full")
	public List<ApiCustomer> listFull() {
		ArrayList<Customer> temp = new ArrayList<>();
		customerRepository.findAll().forEach(temp::add);
		return temp.stream().map(c -> ApiCustomer.fromBackend(c)).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/customers")
	public List<String> list() {
		ArrayList<Customer> temp = new ArrayList<>();
		customerRepository.findAll().forEach(temp::add);
		return temp.stream().map(c -> new String("/api/customers/" + c.getId())).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/customers/{id}")
	public ApiCustomer getCustomer(@PathVariable("id") int id){
		return ApiCustomer.fromBackend(customerRepository.findOne(id));
	}
		
	@GetMapping(value = "/customers/{id}/products")
	public List<String> listProductsByCustomer(@PathVariable("id") int id) {
		Customer temp = customerRepository.findOne(id);
		//return "/api/customers/{id}/products/{productsId}
		return customerRepository.findOne(id).getProducts().stream().map(c -> new String("/api/customers/" + temp.getId() + "/products/" + c.getId())).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/customers/{id}/contacts")
	public List<String> listContactByCustomer(@PathVariable("id") int id) {
		Customer temp = customerRepository.findOne(id);
		return customerRepository.findOne(id).getContacts().stream().map(c -> new String("/api/customers/" + temp.getId() + "/contacts/" + c.getId())).collect(Collectors.toList());
	}

	@GetMapping(value = "/customers/{id}/contacts", params = "full")
	public List<APIContact> listContactByCustomer2(@PathVariable("id") int id) {
		return ApiCustomer.fromBackend(customerRepository.findOne(id)).getContacts();
	}
	
	@GetMapping(value = "/customers/{id}/contacts/{id2}")
	public APIContact getContactByCustomer(@PathVariable("id") int id, @PathVariable("id2") int idC) {
		Customer temp = customerRepository.findOne(id);
		for (Contact tempC : temp.getContacts())
			if (tempC.getId() == idC)
				return APIContact.fromBackend(tempC);
		return null;
	}
	
	@PostMapping(value = "/customers/{id}/contacts")
	public boolean postContact(@PathVariable("id") int id, @RequestBody APIContact Acontact) {
		Contact c = contactService.toBackend(Acontact);
		c.setCustomer(customerRepository.findOne(id));
		c.setBirthDate(LocalDate.now());
		contactRepository.save(c);
		return true;
	}

}
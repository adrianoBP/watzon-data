package it.sorintlab.watzondata.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sorintlab.watzondata.domain.Customer;

@RestController
@RequestMapping("/api")
public class ApiCustomerController {

	@GetMapping("/customers")
	public List<Customer> list() {
		return java.util.Collections.emptyList();
	}
}

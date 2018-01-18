package it.sorintlab.watzondata.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.sorintlab.watzondata.domain.Customer;

@Controller
@RequestMapping("/api")
public class ApiCustomerController {

	@GetMapping("/customers")
	public List<Customer> list() {
		return null;
	}
}

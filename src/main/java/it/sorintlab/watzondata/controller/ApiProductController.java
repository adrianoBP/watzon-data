package it.sorintlab.watzondata.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sorintlab.watzondata.domain.Product;
import it.sorintlab.watzondata.repository.ProductsRepository;

@RestController
@RequestMapping("/api")
public class ApiProductController {

	@Autowired
	private ProductsRepository productRepository;
	
	@GetMapping(value = "/products", params="full")
	public Iterable<Product> getProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping("products")
	public Iterable<String> getProduct(){
		return StreamSupport.stream(productRepository.findAll().spliterator(), false)
				.map(p -> ""+p.getId())
				.collect(Collectors.toList());
	}

}

package it.sorintlab.watzondata.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections.IteratorUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sorintlab.watzondata.backend.Product;
import it.sorintlab.watzondata.repository.ProductsRepository;

@RestController
@RequestMapping("/api")
public class ApiProductController {

//	private final APIPassThroughtService passThrought;
//	
//	@Autowired
//	public ApiProductController(APIPassThroughtService passThrought) {
//		this.passThrought = passThrought;
//	}
	
	@Autowired
	private ProductsRepository productRepository;
	
	@GetMapping(value = "/products", params="full")
	public Iterable<Product> getProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping(value = "/products", params="desc")
	public Iterable<Product> getProductsDesc(){
		Iterable<Product> tmpIter = productRepository.findAll();
		List<Product> tmpList = Lists.newArrayList(tmpIter);
		tmpList.sort(Comparator.comparingInt(Product::getId).reversed());
		tmpIter = tmpList;
		return tmpList;
	}
	
	@GetMapping(value = "/products", params="asc")
	public Iterable<Product> getProductsAsc(){
		Iterable<Product> tmpIter = productRepository.findAll();
		List<Product> tmpList = Lists.newArrayList(tmpIter);
		tmpList.sort(Comparator.comparingInt(Product::getId));
		tmpIter = tmpList;
		return tmpList;
	}
	
	@GetMapping("/products")
	public Iterable<String> getProduct(){
		return StreamSupport.stream(productRepository.findAll().spliterator(), false)
				.map(p -> ""+p.getId())
				.collect(Collectors.toList());
	}
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

//	@GetMapping("/products/{id}")
//	public Product updateProduct(@PathVariable("id") Integer id) {
//		return Product.
//	}
	
}

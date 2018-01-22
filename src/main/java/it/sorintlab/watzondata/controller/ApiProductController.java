package it.sorintlab.watzondata.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sorintlab.watzondata.backend.CustomerProduct;
import it.sorintlab.watzondata.backend.Product;
import it.sorintlab.watzondata.frontend.APICustomerPrice;
import it.sorintlab.watzondata.frontend.ApiCustomer;
import it.sorintlab.watzondata.frontend.ApiCustomerProduct;
import it.sorintlab.watzondata.repository.CustomerRepository;
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
		List<Product> tmpList = IterableUtils.toList(tmpIter);
		tmpList.sort(Comparator.comparingInt(Product::getId).reversed());
		tmpIter = tmpList;
		return tmpList;
	}
	
	@GetMapping(value = "/products", params="asc")
	public Iterable<Product> getProductsAsc(){
		Iterable<Product> tmpIter = productRepository.findAll();
		List<Product> tmpList = IterableUtils.toList(tmpIter);
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
	
//	@GetMapping("/products/searchlike/{val}")
//	public Iterable<String> getProductSearchlike(@PathVariable("val") String val){
//		List<Product> products = new ArrayList<Product>();
//		productRepository.findAll().forEach(prod -> {
//			if(prod.getName().contains(val))
//				products.add(prod);
//		});
//		return producst
//		});
//		
//	}
//	
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping(value = "/products/{id}/customers", params= "asc")
	public List<APICustomerPrice> getCustomersByProductAsc(@PathVariable("id") int id){
		List<APICustomerPrice> customerList = new ArrayList<APICustomerPrice>();
		customerRepository.findAll().forEach(cust -> { 
			
				ApiCustomerProduct customerProduct = null;
				for(CustomerProduct cp : cust.getProducts())
				{
					if(cp.getId().getProduct().getId() == id)
					{
						customerProduct = ApiCustomerProduct.fromBackend(cp);
						break;
					}
				}
				if(customerProduct!=null)
					customerList.add(APICustomerPrice.fromApiCustomer(ApiCustomer.fromBackend(cust), customerProduct ));
			});
		customerList.sort(Comparator.comparingDouble(APICustomerPrice::getPrice));
		return customerList;
	}
	/*
	 @GetMapping(value = "/products/{id}/customers", params= "asc")
	public Page<APICustomerPrice> getCustomersByProductAsc(@PathVariable("id") int id, @RequestParam("name") String name, Pageable paging){
		List<APICustomerPrice> customerList = new ArrayList<APICustomerPrice>();
		
		Sort sort = new Sort(new Order(Direction.ASC, "price"));
		Page<CustomerProduct> products = customerProductRepository.findByProduct/*Id(id, paging);
		products.map(prod -> prod.getCustomer());
		/*customerRepository.findAll(sort);/*.forEach(cust -> { 
			
				ApiCustomerProduct customerProduct = null;
				for(CustomerProduct cp : cust.getProducts())
				{
					if(cp.getId().getProduct().getId() == id)
					{
						customerProduct = ApiCustomerProduct.fromBackend(cp);
						break;
					}
				}
				if(customerProduct!=null)
					customerList.add(APICustomerPrice.fromApiCustomer(ApiCustomer.fromBackend(cust), customerProduct ));
			});
		//customerList.sort(Comparator.comparingDouble(APICustomerPrice::getPrice));
		return customerList;
	}
	 */
	
	@GetMapping(value = "/products/{id}/customers", params= "desc")
	public List<APICustomerPrice> getCustomersByProductDesc(@PathVariable("id") int id){
		List<APICustomerPrice> customerList = new ArrayList<APICustomerPrice>();
		customerRepository.findAll().forEach(cust -> { 
			
				ApiCustomerProduct customerProduct = null;
				for(CustomerProduct cp : cust.getProducts())
				{
					if(cp.getId().getProduct().getId() == id)
					{
						customerProduct = ApiCustomerProduct.fromBackend(cp);
						break;
					}
				}
				if(customerProduct!=null)
					customerList.add(APICustomerPrice.fromApiCustomer(ApiCustomer.fromBackend(cust), customerProduct ));
			});
		customerList.sort(Comparator.comparingDouble(APICustomerPrice::getPrice).reversed());
		return customerList;
	}
	
//	@GetMapping("/products/{id}")
//	public Product updateProduct(@PathVariable("id") Integer id) {
//		return Product.
//	}
	
}


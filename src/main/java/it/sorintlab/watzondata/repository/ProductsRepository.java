package it.sorintlab.watzondata.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import it.sorintlab.watzondata.backend.Customer;
import it.sorintlab.watzondata.backend.Product;

@RepositoryRestResource
public interface ProductsRepository extends PagingAndSortingRepository<Product, Integer> {

	@RestResource(path="search", rel="search")
	public Page<Customer> findByName(@Param("name") String name, Pageable paging);
	@RestResource(path="searchlike", rel="searchlike")
	public Page<Customer> findByNameContaining(@Param("name") String name, Pageable paging);
}

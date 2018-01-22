package it.sorintlab.watzondata.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import it.sorintlab.watzondata.backend.Product;

@RepositoryRestResource
public interface ProductsRepository extends PagingAndSortingRepository<Product, Integer> {

	@RestResource(path="search", rel="search")
	public List<Product> findByName(@Param("name") String name);
	@RestResource(path="searchlike", rel="searchlike")
	public List<Product> findByNameContaining(@Param("name") String name);
}

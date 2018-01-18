package it.sorintlab.watzondata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.sorintlab.watzondata.domain.Product;

@RepositoryRestResource
public interface ProductsRepository extends PagingAndSortingRepository<Product, Integer> {

}

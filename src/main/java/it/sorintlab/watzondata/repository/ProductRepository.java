package it.sorintlab.watzondata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.sorintlab.watzondata.backend.CustomerProduct;
import it.sorintlab.watzondata.backend.CustomerProductPk;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<CustomerProduct, CustomerProductPk> {

	
}

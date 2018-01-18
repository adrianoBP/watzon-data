package it.sorintlab.watzondata.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import it.sorintlab.watzondata.domain.Customer;
import it.sorintlab.watzondata.domain.CustomerProduct;
import it.sorintlab.watzondata.domain.CustomerProductPk;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<CustomerProduct, CustomerProductPk> {

	
}

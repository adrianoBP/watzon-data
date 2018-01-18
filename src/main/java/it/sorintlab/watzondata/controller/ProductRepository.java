package it.sorintlab.watzondata.controller;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.sorintlab.watzondata.domain.CustomerProduct;
import it.sorintlab.watzondata.domain.CustomerProductPk;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<CustomerProduct, CustomerProductPk> {

}

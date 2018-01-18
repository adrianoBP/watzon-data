package it.sorintlab.watzondata.controller;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.sorintlab.watzondata.domain.Customer;

@RepositoryRestResource
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

}

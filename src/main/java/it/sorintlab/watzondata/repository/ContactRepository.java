package it.sorintlab.watzondata.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.backend.Customer;

@RepositoryRestResource
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer>{

//	@RestResource(path="search", rel="search")
//	public Page<Contact> findByName(@Param("name") String name, Pageable paging);
//	@RestResource(path="searchlike", rel="searchlike")
//	public Page<Contact> findByNameContaining(@Param("name") String name, Pageable paging);
	
}
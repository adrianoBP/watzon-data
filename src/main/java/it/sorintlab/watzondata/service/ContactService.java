package it.sorintlab.watzondata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.frontend.APIContact;
import it.sorintlab.watzondata.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository repo;
	
	public Contact toBackend(APIContact frontend, int id) {
		Contact backend= toBackend(frontend);
		Contact oldContact = repo.findOne(id);
		if (backend.getBirthDate() == null)
			backend.setBirthDate(oldContact.getBirthDate());
		if (backend.getName() == null)
			backend.setName(oldContact.getName());
		if (backend.getRole() == null)
			backend.setRole(oldContact.getRole());
		if (backend.getSurname() == null)
			backend.setSurname(oldContact.getSurname());
		backend.setCustomer(oldContact.getCustomer());
		backend.setId(id);
		backend.setInfoReferences(oldContact.getInfoReferences());
		backend.setVersion(oldContact.getVersion());
		
		return backend;
	}
	
	public Contact toBackend(APIContact frontend) {
		Contact backend= new Contact();
		backend.setBirthDate(frontend.getBirthDate());
		backend.setName(frontend.getName());
		backend.setRole(frontend.getRole());
		backend.setSurname(frontend.getSurname());
		return backend;
	}
}

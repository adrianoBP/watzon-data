package it.sorintlab.watzondata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.backend.InfoReference;
import it.sorintlab.watzondata.backend.InfoReference.Type;
import it.sorintlab.watzondata.frontend.APIContact;
import it.sorintlab.watzondata.repository.ContactRepository;

@Service
public class ContactService {

	final private ContactRepository repository;
	
	@Autowired
	public ContactService(ContactRepository repository) {
		this.repository = repository;
	}
	
	public Contact toBackend(APIContact frontend, int id) {
		Contact oldContact = repository.findOne(id);
		if (oldContact == null) {
			throw new ContactNotFoundException(id);
		}
		
		Contact backend = toBackend(frontend);
		if (backend.getBirthDate() == null) {
			backend.setBirthDate(oldContact.getBirthDate());
		}
		if (backend.getName() == null) {
			backend.setName(oldContact.getName());
		}
		if (backend.getRole() == null) {
			backend.setRole(oldContact.getRole());
		}
		if (backend.getSurname() == null) {
			backend.setSurname(oldContact.getSurname());
		}
		backend.setCustomer(oldContact.getCustomer());
		backend.setId(id);
		backend.setInfoReferences(oldContact.getInfoReferences());
		backend.setVersion(oldContact.getVersion());

		return backend;
	}

	public Contact toBackend(APIContact frontend) {
		Assert.notNull(frontend, "Input APIContact should not be null.");

		Contact backend = new Contact();
		backend.setId(frontend.getId());
		backend.setBirthDate(frontend.getBirthDate());
		backend.setName(frontend.getName());
		backend.setRole(frontend.getRole());
		backend.setSurname(frontend.getSurname());

		List<InfoReference> infoReferences = new ArrayList<>();
		backend.setInfoReferences(infoReferences);

		final List<String> phoneNumbers = frontend.getPhoneNumbers();
		if (phoneNumbers != null) {
			for (String phoneNumber : phoneNumbers) {
				infoReferences.add(InfoReference.of(Type.phone_number, phoneNumber));
			}
		}

		final List<String> emails = frontend.getEmails();
		if (emails != null) {
			for (String email : emails) {
				infoReferences.add(InfoReference.of(Type.email, email));
			}
		}

		return backend;
	}
}

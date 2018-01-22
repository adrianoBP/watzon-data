package it.sorintlab.watzondata.service;

import org.springframework.dao.DataAccessException;

@SuppressWarnings("serial")
public class ContactNotFoundException extends DataAccessException {

	public ContactNotFoundException(Integer id) {
		super("No contact associated with id : " + id);
	}
}

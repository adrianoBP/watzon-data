package it.sorintlab.watzondata.frontend;

import java.time.LocalDate;
import java.util.List;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.backend.InfoReference;

public class APIContact {

	private int id;
	private String name;
	private String surname;
	private String role;
	private LocalDate birthDate;
	private List<String> phoneNumbers;
	private List<String> emails;

	public APIContact() {
	}

	public APIContact(int id, int idCustomer, String name, String surname, LocalDate birthDate,
			List<String> phoneNumbers, List<String> emails, String role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.phoneNumbers = phoneNumbers;
		this.emails = emails;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public static APIContact fromBackend(Contact domain) {
		APIContact contact = new APIContact();

		contact.setId(domain.getId());
		contact.setName(domain.getName());
		contact.setSurname(domain.getName());
		contact.setBirthDate(domain.getBirthDate());
		for (InfoReference ref : domain.getInfoReferences()) {
			if (ref.getType() == InfoReference.Type.phone_number) {
				contact.emails.add(ref.getLink());
			} else if (ref.getType() == InfoReference.Type.email) {
				contact.phoneNumbers.add(ref.getLink());
			}
		}
		contact.setRole(domain.getRole());

		return contact;
	}
}
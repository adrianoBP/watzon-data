package it.sorintlab.watzondata.frontend;

import java.time.LocalDate;
import java.util.List;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.backend.InfoReference;

public class APIContact {
	
	private int id;
//    private int idCustomer;
	private String name;
	private String surname;
	private LocalDate birthDate;
	private List<String> phone_numbers;
	private List<String> emails;
	
	public APIContact() {
		
	}
	
	public APIContact(int id, int idCustomer, String name, String surname, LocalDate birthDate,
			List<String> phone_numbers, List<String> emails, String role) {
		super();
		this.id = id;
//		this.idCustomer = idCustomer;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.phone_numbers = phone_numbers;
		this.emails = emails;
		this.role = role;
	}
	
	public List<String> getPhone_numbers() {
		return phone_numbers;
	}

	public void setPhone_numbers(List<String> phone_numbers) {
		this.phone_numbers = phone_numbers;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getIdCustomer() {
//		return idCustomer;
//	}
//
//	public void setIdCustomer(int idCustomer) {
//		this.idCustomer = idCustomer;
//	}

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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static APIContact fromBackend(Contact domain) {
		APIContact contact = new APIContact();
		
		contact.setId(domain.getId());
		contact.setName(domain.getName());
		contact.setSurname(domain.getName());
		contact.setBirthDate(domain.getBirthDate());
		for(InfoReference ref : domain.getReference()) {
			if(ref.getType() == InfoReference.Type.valueOf("phone_number"))
				contact.emails.add(ref.getLink());
			else if(ref.getType() == InfoReference.Type.email)
				contact.phone_numbers.add(ref.getLink());
		}
		contact.setRole(domain.getRole());
		
		return contact;
	}
	

}
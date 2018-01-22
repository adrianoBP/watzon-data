package it.sorintlab.watzondata.service;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.backend.InfoReference;
import it.sorintlab.watzondata.backend.InfoReference.Type;
import it.sorintlab.watzondata.frontend.APIContact;
import it.sorintlab.watzondata.repository.ContactRepository;

public class ContactServiceTests {

	private static final String TEST_ROLE = "test role";
	private static final Integer TEST_ID = Integer.valueOf(1);
	private static final String TEST_NAME = "test name";
	private static final String TEST_SURNAME = "test surname";
	private static final LocalDate TEST_BIRTH_DATE = LocalDate.of(2018, 1, 22);
	
	private static final String TEST_PHONE_1 = "phone 1";
	private static final String TEST_PHONE_2 = "phone 2";
	private static final String TEST_EMAIL_1 = "email 1";
	private static final String TEST_EMAIL_2 = "email 2";

	private ContactService serviceToTest;
	
	private ContactRepository mockRepository;
	
	@Before
	public void setUp() {
		mockRepository = Mockito.mock(ContactRepository.class);
		serviceToTest = new ContactService(mockRepository);
	}
	
	@Test
	public void shouldCallRepositoryWithId_whenInputPlusId() {
		when(mockRepository.findOne(TEST_ID)).thenReturn(new Contact());
		serviceToTest.toBackend(new APIContact(), TEST_ID);
		verify(mockRepository).findOne(TEST_ID);
	}
	
	@Test(expected = ContactNotFoundException.class)
	public void shouldThrowContactNotFoundExecption_whenIdNotFound() {
		serviceToTest.toBackend(new APIContact(), TEST_ID);
	}
	
	// passiamo un oggetto vuoto in input, ma troviamo dati sul db
	
	// passiamo un oggetto con proprietà impostate, sovrascrive i dati dal db
	
	// passiamo un riferimento null in input -> IllegalArgumentException
	
	@Test
	public void shouldReturnNotNull_whenAnyInput() {
		Contact result = serviceToTest.toBackend(new APIContact());
		assertNotNull(result);
	}

	@Ignore
	@Test
	public void shouldReturnNull_whenNullInput() {
		Contact result = serviceToTest.toBackend(null);
		assertNull(result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIlegalArgumentException_whenNullInput() {
		serviceToTest.toBackend(null);
	}

	@Test
	public void shouldReturnPopulatedContact_whenFullInput() {
		APIContact input = new APIContact();
		input.setId(TEST_ID);
		input.setName(TEST_NAME);
		input.setSurname(TEST_SURNAME);
		input.setBirthDate(TEST_BIRTH_DATE);
		input.setRole(TEST_ROLE);
		input.setPhoneNumbers(asList(TEST_PHONE_1, TEST_PHONE_2));
		input.setEmails(asList(TEST_EMAIL_1, TEST_EMAIL_2));

		Contact result = serviceToTest.toBackend(input);
		assertEquals(TEST_ID, result.getId());
		assertEquals(TEST_NAME, result.getName());
		assertEquals(TEST_SURNAME, result.getSurname());
		assertEquals(TEST_BIRTH_DATE, result.getBirthDate());
		assertEquals(TEST_ROLE, result.getRole());

		List<InfoReference> infoReferences = result.getInfoReferences();
		assertNotNull(infoReferences);
		assertEquals(4, infoReferences.size());
		assertThat(infoReferences, hasItem(InfoReference.of(Type.phone_number, TEST_PHONE_1)));
		assertThat(infoReferences, hasItem(InfoReference.of(Type.phone_number, TEST_PHONE_2)));
		assertThat(infoReferences, hasItem(InfoReference.of(Type.email, TEST_EMAIL_1)));
		assertThat(infoReferences, hasItem(InfoReference.of(Type.email, TEST_EMAIL_2)));
	}
}

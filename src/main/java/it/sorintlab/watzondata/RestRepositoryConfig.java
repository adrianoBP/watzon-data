package it.sorintlab.watzondata;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import it.sorintlab.watzondata.backend.Contact;
import it.sorintlab.watzondata.backend.Customer;
import it.sorintlab.watzondata.backend.Product;

@Configuration
public class RestRepositoryConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Customer.class, Product.class, Contact.class);
	}
}

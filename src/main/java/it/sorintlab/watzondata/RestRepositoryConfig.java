package it.sorintlab.watzondata;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import it.sorintlab.watzondata.domain.Contact;
import it.sorintlab.watzondata.domain.Customer;
import it.sorintlab.watzondata.domain.Product;

@Configuration
public class RestRepositoryConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Customer.class, Product.class, Contact.class);
	}
}

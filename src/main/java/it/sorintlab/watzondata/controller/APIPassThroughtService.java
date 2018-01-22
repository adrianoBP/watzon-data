package it.sorintlab.watzondata.controller;

import it.sorintlab.watzondata.backend.Product;

public interface APIPassThroughtService {
	
	Product update(Integer id, Product backend);

	
}

package it.sorintlab.watzondata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sorintlab.watzondata.backend.Product;

public interface APIProductRepository extends JpaRepository<Product, Integer> {

}

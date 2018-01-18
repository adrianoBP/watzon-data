package it.sorintlab.watzondata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.sorintlab.watzondata.domain.Product;

public interface APIProductRepository extends JpaRepository<Product, Integer> {

}

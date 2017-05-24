package com.gk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gk.entities.Product;

/**
 * 
 * @author gauravkhandave
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}

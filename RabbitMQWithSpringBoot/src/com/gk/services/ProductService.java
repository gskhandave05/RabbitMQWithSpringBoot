/**
 * 
 */
package com.gk.services;

import java.util.List;

import com.gk.entities.Product;
import com.gk.model.ProductBean;

/**
 * @author gauravkhandave
 *
 */
public interface ProductService {
	List<Product> listAll();

    Product getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductForm(ProductBean productBean);

    void sendProductMessage(String id);
}

/**
 * 
 */
package com.gk.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.gk.entities.Product;
import com.gk.model.ProductBean;

/**
 * @author gauravkhandave
 *
 */
@Component
public class ProductToProductBean implements Converter<Product,ProductBean>{

	@Override
	public ProductBean convert(Product product) {
		ProductBean bean = new ProductBean();
		bean.setDescription(product.getDescription());
		bean.setId(product.getId());
		bean.setImageUrl(product.getImageUrl());
		bean.setPrice(product.getPrice());
		return bean;
	}

}

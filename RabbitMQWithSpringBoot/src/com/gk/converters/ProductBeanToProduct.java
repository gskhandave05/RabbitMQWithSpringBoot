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
public class ProductBeanToProduct implements Converter<ProductBean,Product>{

	@Override
	public Product convert(ProductBean bean) {

		Product product = new Product();
		product.setId(bean.getId());
		product.setDescription(bean.getDescription());
		product.setImageUrl(bean.getImageUrl());
		product.setPrice(bean.getPrice());
		return product;
	}

}

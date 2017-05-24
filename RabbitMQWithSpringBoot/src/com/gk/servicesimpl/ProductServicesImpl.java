/**
 * 
 */
package com.gk.servicesimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.converters.ProductBeanToProduct;
import com.gk.entities.Product;
import com.gk.model.ProductBean;
import com.gk.repository.ProductRepository;
import com.gk.services.ProductService;
import com.gk.maindriver.RabbitMQSpringBootApplication;

/**
 * @author gauravkhandave
 *
 */
@Service
public class ProductServicesImpl implements ProductService{

	ProductRepository productRepository;
	RabbitTemplate rabbitTemplate;
	ProductBeanToProduct productBeanToProduct;
	
	 @Autowired
	    public ProductServicesImpl(ProductRepository productRepository, ProductBeanToProduct productBeanToProduct,
	                              RabbitTemplate rabbitTemplate) {
	        this.productRepository = productRepository;
	        this.productBeanToProduct = productBeanToProduct;
	        this.rabbitTemplate = rabbitTemplate;
	    }
	
	@Override
	public List<Product> listAll() {
		List<Product> list = new ArrayList<Product>();
		for(Product product: productRepository.findAll()){
			list.add(product);
		}
		return list;		
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product saveOrUpdate(Product product) {
		productRepository.save(product);
		return product;
	}

	@Override
	public void delete(Long id) {
		productRepository.delete(id);
		
	}

	@Override
	public Product saveOrUpdateProductForm(ProductBean productBean) {
		Product savedProduct = saveOrUpdate(productBeanToProduct.convert(productBean));

        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
	}

	@Override
	public void sendProductMessage(String id) {
		Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", id);
        rabbitTemplate.convertAndSend(RabbitMQSpringBootApplication.GSK_MESSAGE_QUEUE, actionmap);
		
	}

}

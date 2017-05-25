package com.gk.listener;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.gk.entities.Product;
import com.gk.repository.ProductRepository;

@Component
public class ProductMessageListener {
	private ProductRepository productRepository;


    public ProductMessageListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method is invoked whenever any new message is put in the queue.
     * 
     * @param message
     */
    public void receiveMessage(Map<String, String> message) {
        System.out.println("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));
        Product product = productRepository.findOne(id);
        product.setMessageReceived(true);
        product.setMessageCount(product.getMessageCount() + 1);

        productRepository.save(product);
        System.out.println("Message processed...");
    }
}

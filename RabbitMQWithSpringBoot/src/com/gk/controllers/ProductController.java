/**
 * 
 */
package com.gk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gk.converters.ProductBeanToProduct;
import com.gk.converters.ProductToProductBean;
import com.gk.entities.Product;
import com.gk.model.ProductBean;
import com.gk.services.ProductService;

/**
 * @author gauravkhandave
 *
 */

@RestController
@RequestMapping("/")
public class ProductController {

	private ProductService productService;
    private ProductToProductBean productToProductBean;
    private ProductBeanToProduct productBeanToProduct;



    @Autowired
    public void setProductToProductForm(ProductToProductBean productToProductForm) {
        this.productToProductBean = productToProductForm;
    }
    
    @Autowired
    public void setProductBeanToProduct(ProductBeanToProduct productBeanToProduct) {
        this.productBeanToProduct = productBeanToProduct;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
    @RequestMapping("/listProduct")
    public List<Product> listProducts(){
    	return productService.listAll();
    }
    
    @RequestMapping(value="/addProduct",method = RequestMethod.POST)
    public Product saveProduct(@RequestBody ProductBean productBean){
    	return productService.saveOrUpdate(productBeanToProduct.convert(productBean));
    }
   
    @RequestMapping("/product/sendProductMsg/{id}")
    public String indexProduct(@PathVariable String id){
    	productService.sendProductMessage(id);
    	return id;
    }
}

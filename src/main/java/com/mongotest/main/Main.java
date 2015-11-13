package com.mongotest.main;

import com.mongotest.product.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by diegoamaya on 12/11/15.
 */
public class Main {

    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        ProductService productService = (ProductService) applicationContext.getBean("productService");

        /**
         * Retrieve All Products
         */
        productService.retrieveAllProducts();

    }

}

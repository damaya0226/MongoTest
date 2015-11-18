package com.mongotest.main;

import com.mongotest.product.entities.ProductCategory;
import com.mongotest.product.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by diegoamaya on 12/11/15.
 * Please enjoy this application.
 */
public class Main {

    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        ProductService productService = (ProductService) applicationContext.getBean("productService");

        /**
         * Retrieve All Products
         */
        System.out.println("Retrieve All Products");
        productService.retrieveAllProducts();

        /**
         * Retrieve All Products With Price Less Than 5000
         */
        System.out.println("Retrieve All Products With Price Less Than 5000");
        productService.retrieveAllProductsWithPriceLessThan(5000);

        /**
         * Retrieve All Products From Special Category:  Beer
         */
        System.out.println("Retrieve All Products From Special Category:  Beer");
        productService.retrieveAllProductsFromSpecialCategory(ProductCategory.Beer);

        /**
         * Retrieve All Products From Special Category:  Vehicle
         */
        System.out.println("Retrieve All Products From Special Category:  Vehicle");
        productService.retrieveAllProductsFromSpecialCategory(ProductCategory.Vehicle);
    }

}

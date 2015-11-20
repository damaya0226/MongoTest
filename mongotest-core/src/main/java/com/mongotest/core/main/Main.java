package com.mongotest.core.main;

import com.mongotest.product.entities.ProductCategory;
import com.mongotest.product.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by diegoamaya on 12/11/15.
 * Please enjoy this application.
 */
public class Main {

    static{
        //Mongo
        //System.setProperty("spring.profiles.active", "mongodb");

        // Postgres
        //System.setProperty("spring.profiles.active", "postgres-concrete-table");
        //System.setProperty("spring.profiles.active", "postgres-single-table");
        System.setProperty("spring.profiles.active", "postgres-multi-table");

        //...
    }

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

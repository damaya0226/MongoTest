package com.mongotest.server.main;

import com.mongotest.commons.product.entities.ProductCategory;
import com.mongotest.server.product.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

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
        Date startDate = new Date();
        productService.retrieveAllProducts();
        Date endDate = new Date();
        System.out.println("Retrieve All Products with profile " + System.getProperty("spring.profiles.active") + " last " + (endDate.getTime() - startDate.getTime()) + " milliseconds");

        /**
         * Retrieve All Products With Price Less Than 5000
         */
        System.out.println("Retrieve All Products With Price Less Than 5000");
        startDate = new Date();
        productService.retrieveAllProductsWithPriceLessThan(5000D);
        endDate = new Date();
        System.out.println("Retrieve All Products With Price Less Than 5000 with profile " + System.getProperty("spring.profiles.active") + " last " + (endDate.getTime() - startDate.getTime()) + " milliseconds");

        /**
         * Retrieve All Products From Special Category:  Beer
         */
        System.out.println("Retrieve All Products From Special Category:  Beer");
        startDate = new Date();
        productService.retrieveAllProductsFromSpecialCategory(ProductCategory.Beer);
        endDate = new Date();
        System.out.println("Retrieve All Products From Special Category:  Beer with profile " + System.getProperty("spring.profiles.active") + " last " + (endDate.getTime() - startDate.getTime()) + " milliseconds");

        /**
         * Retrieve All Products From Special Category:  Vehicle
         */
        System.out.println("Retrieve All Products From Special Category:  Vehicle");
        startDate = new Date();
        productService.retrieveAllProductsFromSpecialCategory(ProductCategory.Vehicle);
        endDate = new Date();
        System.out.println("Retrieve All Products From Special Category: Vehicle  with profile " + System.getProperty("spring.profiles.active") + " last " + (endDate.getTime() - startDate.getTime()) + " milliseconds");
    }


}

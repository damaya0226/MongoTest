package com.mongotest.populator.main;

import com.mongotest.populator.product.ProductPopulator;
import com.mongotest.server.product.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by diegoamaya on 20/11/15.
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-populator-context.xml");
        ProductPopulator productPopulator = (ProductPopulator) applicationContext.getBean("productPopulator");
        Date startDate = new Date();
        productPopulator.populate();
        Date endDate = new Date();
        System.out.println("The insertion with profile: " + System.getProperty("spring.profiles.active") + " last " + (endDate.getTime() - startDate.getTime())+ " milliseconds");
    }
}

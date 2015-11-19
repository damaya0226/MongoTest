package com.mongotest.product.dao;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by diegoamaya on 19/11/15.
 */
public class DaoTest extends AbstractTestNGSpringContextTests {

    static {
        System.setProperty("spring.profiles.active", "mongodb-test");

        //System.setProperty("spring.profiles.active", "postgresConcreteTable-test");
        //System.setProperty("spring.profiles.active", "postgresSingleTable-test");
    }

}

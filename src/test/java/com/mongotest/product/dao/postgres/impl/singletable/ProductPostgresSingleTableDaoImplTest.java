package com.mongotest.product.dao.postgres.impl.singletable;

import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.dao.postgres.impl.concretetable.ProductPostgresConcreteTableDaoImpl;
import com.mongotest.product.entities.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Integration test with Postgres using the ProductDao
 * Created by diegoamaya on 12/11/15.
 */
@Test
@ActiveProfiles(profiles = "postgresSingleTable-test")
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class ProductPostgresSingleTableDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductDao productDao;

    private final static String INSERT_VEHICLE = "INSERT INTO PRODUCT(CATEGORY, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, null, null, null, ?)";

    private final static String INSERT_BEER = "INSERT INTO PRODUCT(CATEGORY, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK)\n" +
            "VALUES (?, ?, ?, ?, null, null, null, ?, ?, ?, ?)";

    private final static String DELETE_VEHICLES = "DELETE FROM PRODUCT WHERE CATEGORY = '" + ProductCategory.Vehicle.getCategory() + "'";

    private final static String DELETE_BEERS = "DELETE FROM PRODUCT WHERE CATEGORY = '" + ProductCategory.Beer.getCategory() + "'";

    @BeforeMethod(groups = "requireData")
    public void insertProducts(){
        Double price = 1500D;
        int units = 1;
        for(int i = 1 ; i <= 3 ; i++){
            ((ProductPostgresConcreteTableDaoImpl) productDao).getJdbcTemplate().update(INSERT_VEHICLE, ProductCategory.Vehicle.getCategory(), price, ("Vehicle " + i), units, "Blue, Red, Yellow", ("engine" + i), ("soundType" + i), i);
            price += 20;
            units += 1;
        }
        price = 1500D;
        units = 2;

        Double alcoholContent = 0.1D;
        int block = 101;
        for(int i = 1 ; i <= 5 ; i++){
            ((ProductPostgresConcreteTableDaoImpl) productDao).getJdbcTemplate().update(INSERT_BEER, ProductCategory.Beer.getCategory(), price, ("Beer " + i), units, "Tomato, Potato, Banana", ("BeerCountry" + i), alcoholContent, block);
            price += 500;
            units += 2;
            alcoholContent += 0.01;
            block++;
        }

    }

    @BeforeMethod(groups = "requireDeleteAllProducts")
    public void deleteAllProducts(){
        ((ProductPostgresConcreteTableDaoImpl) productDao).getJdbcTemplate().update(DELETE_VEHICLES);
        ((ProductPostgresConcreteTableDaoImpl) productDao).getJdbcTemplate().update(DELETE_BEERS);
    }

    @Test(groups = {"requireDeleteAllProducts" , "requireData"})
    public void testRetrieveAllProducts(){
        Assert.assertEquals(productDao.retrieveAllProducts(), 8);
    }

    @Test(groups = {"requireDeleteAllProducts" , "requireData"})
    public void testRetrieveAllProductsWithPriceLessThan(){
        Assert.assertEquals(productDao.retrieveAllProductsWithPriceLessThan(2501), 6);
        Assert.assertEquals(productDao.retrieveAllProductsWithPriceLessThan(3000), 6);
    }

    @Test(groups = {"requireDeleteAllProducts" , "requireData"})
    public void testRetrieveAllProductsFromSpecialCategory(){
        Assert.assertEquals(productDao.retrieveAllProductsFromSpecialCategory(ProductCategory.Beer), 5);
        Assert.assertEquals(productDao.retrieveAllProductsFromSpecialCategory(ProductCategory.Vehicle), 3);
    }

}

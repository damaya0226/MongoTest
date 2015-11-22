package com.mongotest.server.product.dao.rdb.postgres.impl.multitable;

import com.mongotest.server.product.dao.ProductDao;
import com.mongotest.commons.product.entities.ProductCategory;
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
@ActiveProfiles(profiles = "postgres-multi-table-test")
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class ProductPostgresMultiTableDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductDao productDao;

    private final static String INSERT = "INSERT INTO PRODUCT(ID, PRICE, DESCRIPTION, UNITS, CATEGORY, BLOCK)\n" +
            "VALUES(?, ?, ?, ?,?, ?)";

    private final static String INSERT_VEHICLE = "INSERT INTO PRODUCT_VEHICLE(ID, COLORS, ENGINE_TYPE, SOUND_TYPE)\n" +
            "VALUES (?, ?, ?, ?)";

    private final static String INSERT_BEER = "INSERT INTO PRODUCT_BEER(ID, FLAVORS, MADEIN, ALCOHOL_CONTENT)\n" +
            "VALUES (?, ?, ?, ?);";

    private final static String DELETE = "DELETE FROM PRODUCT";

    private final static String DELETE_VEHICLES = "DELETE FROM PRODUCT_VEHICLE";

    private final static String DELETE_BEERS = "DELETE FROM PRODUCT_BEER";

    @BeforeMethod(groups = "requireData")
    public void insertProducts(){
        Double price = 1500D;
        int units = 1;
        int i = 1;
        for( ; i <= 3 ; i++){
            ((ProductPostgresMultipleTableDaoImpl) productDao).getJdbcTemplate().update(INSERT, i, price, ("Vehicle " + i), units, ProductCategory.Vehicle.getCategory(), i);
            ((ProductPostgresMultipleTableDaoImpl) productDao).getJdbcTemplate().update(INSERT_VEHICLE, i, "Blue, Red, Yellow", ("engine" + i), ("soundType" + i));
            price += 20;
            units += 1;
        }
        price = 1500D;
        units = 2;

        Double alcoholContent = 0.1D;
        int block = 101;
        for(int j = 1 ; j <= 5 ; j++){
            ((ProductPostgresMultipleTableDaoImpl) productDao).getJdbcTemplate().update(INSERT, i, price, ("Beer " + i), units, ProductCategory.Beer.getCategory(),block);
            ((ProductPostgresMultipleTableDaoImpl) productDao).getJdbcTemplate().update(INSERT_BEER, i, "Tomato, Potato, Banana", ("BeerCountry" + i), alcoholContent);
            price += 500;
            units += 2;
            alcoholContent += 0.01;
            block++;
            i++;
        }

    }

    @BeforeMethod(groups = "requireDeleteAllProducts")
    public void deleteAllProducts(){
        ((ProductPostgresMultipleTableDaoImpl) productDao).getJdbcTemplate().update(DELETE_VEHICLES);
        ((ProductPostgresMultipleTableDaoImpl) productDao).getJdbcTemplate().update(DELETE_BEERS);
        ((ProductPostgresMultipleTableDaoImpl) productDao).getJdbcTemplate().update(DELETE);
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

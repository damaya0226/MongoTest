package com.mongotest.server.product.dao.rdb.postgres.impl.singletable;

import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductBeer;
import com.mongotest.commons.product.entities.ProductVehicle;
import com.mongotest.server.product.dao.ProductDao;
import com.mongotest.server.product.dao.rdb.postgres.impl.concretetable.ProductPostgresConcreteTableDaoImpl;
import com.mongotest.commons.product.entities.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Integration test with Postgres using the ProductDao
 * Created by diegoamaya on 12/11/15.
 */
@Test
@ActiveProfiles(profiles = "postgres-single-table-test")
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class ProductPostgresSingleTableDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductDao productDao;

    private final static String DELETE_VEHICLES = "DELETE FROM PRODUCT WHERE CATEGORY = '" + ProductCategory.Vehicle.getCategory() + "'";

    private final static String DELETE_BEERS = "DELETE FROM PRODUCT WHERE CATEGORY = '" + ProductCategory.Beer.getCategory() + "'";

    @BeforeMethod(groups = "requireData")
    public void insertProducts(){
        Double price = 1500D;
        int units = 1;
        Product productVehicle = new ProductVehicle();
        for(int i = 1 ; i <= 3 ; i++){
            productVehicle.setId((long) i);
            productVehicle.setPrice(price);
            productVehicle.setDescription(ProductCategory.Vehicle.getCategory() + i);
            productVehicle.setUnits(units);
            productVehicle.setBlock(i);
            ((ProductVehicle)productVehicle).setColors(Arrays.asList("Blue", "Red", "Yellow"));
            ((ProductVehicle)productVehicle).setEngineType("engine" + i);
            ((ProductVehicle)productVehicle).setSoundType("soundType" + i);
            productDao.insertProduct(productVehicle);
            price += 20;
            units += 1;
        }
        price = 1500D;
        units = 2;

        Double alcoholContent = 0.1D;
        int block = 101;
        Product productBeer = new ProductBeer();
        for(int i = 1 ; i <= 5 ; i++){
            productBeer.setId((long) i);
            productBeer.setPrice(price);
            productBeer.setDescription(ProductCategory.Beer.getCategory() + i);
            productBeer.setUnits(units);
            productBeer.setBlock(block);
            ((ProductBeer)productBeer).setFlavors(Arrays.asList("Tomato", "Potato", "Banana"));
            ((ProductBeer)productBeer).setMadeIn("BeerCountry" + i);
            ((ProductBeer)productBeer).setAlcoholContent(alcoholContent);
            productDao.insertProduct(productBeer);
            price += 500;
            units += 2;
            alcoholContent += 0.01;
            block++;
        }

    }

    @BeforeMethod(groups = "requireDeleteAllProducts")
    public void deleteAllProducts(){
        ((ProductPostgresSingleTableDaoImpl) productDao).getJdbcTemplate().update(DELETE_VEHICLES);
        ((ProductPostgresSingleTableDaoImpl) productDao).getJdbcTemplate().update(DELETE_BEERS);
    }

    @Test(groups = {"requireDeleteAllProducts" , "requireData"})
    public void testRetrieveAllProducts(){
        Assert.assertEquals(productDao.retrieveAllProducts(), 8);
    }

    @Test(groups = {"requireDeleteAllProducts" , "requireData"})
    public void testRetrieveAllProductsWithPriceLessThan(){
        Assert.assertEquals(productDao.retrieveAllProductsWithPriceLessThan(2501D), 6);
        Assert.assertEquals(productDao.retrieveAllProductsWithPriceLessThan(3000D), 6);
    }

    @Test(groups = {"requireDeleteAllProducts" , "requireData"})
    public void testRetrieveAllProductsFromSpecialCategory(){
        Assert.assertEquals(productDao.retrieveAllProductsFromSpecialCategory(ProductCategory.Beer), 5);
        Assert.assertEquals(productDao.retrieveAllProductsFromSpecialCategory(ProductCategory.Vehicle), 3);
    }

}

package com.mongotest.server.product.dao.mongodb.impl;

import com.mongodb.client.MongoCollection;
import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductBeer;
import com.mongotest.commons.product.entities.ProductVehicle;
import com.mongotest.server.product.dao.ProductDao;
import com.mongotest.commons.product.entities.ProductCategory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Integration test with MongoDB using the ProductDao
 * Created by diegoamaya on 12/11/15.
 */
@Test
@ActiveProfiles(profiles = "mongodb-test")
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class ProductMongoDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private MongoCollection<Document> productCollection;

    @BeforeMethod(groups = "requireData")
    public void insertProducts(){
        for(Product product : getBeerDocuments()){
            productDao.insertProduct(product);
        }
        for(Product product : getVehicleDocuments()){
            productDao.insertProduct(product);
        }
    }
    @BeforeMethod(groups = "requireDropCollection")
    public void dropCollection(){
        productCollection.drop();
    }

    @Test(groups = {"requireDropCollection" , "requireData"})
    public void testRetrieveAllProducts(){
        Assert.assertEquals(productDao.retrieveAllProducts(), 8);
    }

    @Test(groups = {"requireDropCollection" , "requireData"})
    public void testRetrieveAllProductsWithPriceLessThan(){
        Assert.assertEquals(productDao.retrieveAllProductsWithPriceLessThan(2501), 6);
        Assert.assertEquals(productDao.retrieveAllProductsWithPriceLessThan(3000), 6);
    }

    @Test(groups = {"requireDropCollection" , "requireData"})
    public void testRetrieveAllProductsFromSpecialCategory(){
        Assert.assertEquals(productDao.retrieveAllProductsFromSpecialCategory(ProductCategory.Beer), 5);
        Assert.assertEquals(productDao.retrieveAllProductsFromSpecialCategory(ProductCategory.Vehicle), 3);
    }

    private List<Product> getBeerDocuments(){
        return Arrays.asList(
                (Product) new ProductBeer(1500D, "German Beer", 10, 111, Arrays.asList("Tomato", "Potato", "Bacon"), "Germany", 0.1D),
                (Product) new ProductBeer(2000D, "Brazilian Beer", 20, 222, Arrays.asList("Farofa", "Jurupinga", "Arroz"), "Brazil", 0.2D),
                (Product) new ProductBeer(2500D, "Colombian Beer", 30, 333, Arrays.asList("Mazamorra", "Chicha", "Arepa"), "Colombia", 0.3D),
                (Product) new ProductBeer(3000D, "Argentinian Beer", 40, 444, Arrays.asList("Mate", "Pasto", "Carne"), "Argentina", 0.4D),
                (Product) new ProductBeer(3500D, "Ast Beer", 50, 555, Arrays.asList("Tomato", "Potato", "Bacon"), "Holand", 0.5D)
        );
    }

    private List<Product> getVehicleDocuments(){
        return Arrays.asList(
                (Product) new ProductVehicle(1500D, "German Vehicle", 10, 111, Arrays.asList("Blue", "Red", "Yellow"), "XXX", "XXX"),
                (Product) new ProductVehicle(2000D, "Brazilian Vehicle", 20, 222, Arrays.asList("Green", "Red", "Black"), "YYY", "YYY"),
                (Product) new ProductVehicle(2500D, "Colombian Vehicle", 30, 333, Arrays.asList("Green", "Yellow", "Red"), "ZZZ", "ZZZ")
        );
    }
}

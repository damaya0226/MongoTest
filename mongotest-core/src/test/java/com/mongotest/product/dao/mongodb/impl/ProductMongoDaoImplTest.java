package com.mongotest.product.dao.mongodb.impl;

import com.mongodb.client.MongoCollection;
import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.entities.ProductCategory;
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
        productCollection.insertMany(getBeerDocuments());
        productCollection.insertMany(getVehicleDocuments());
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

    private List<Document> getBeerDocuments(){
        return Arrays.asList(
                new Document("category", "Beer")
                        .append("price", 1500)
                        .append("description", "German Beer")
                        .append("units", 10)
                        .append("flavors", Arrays.asList("Tomato", "Potato", "Bacon"))
                        .append("madeIn", "Germany")
                        .append("alcoholContent", 0.1)
                        .append("block", 111),

                new Document("category", "Beer")
                        .append("price", 2000)
                        .append("description", "Brazilian Beer")
                        .append("units", 20)
                        .append("flavors", Arrays.asList("Farofa", "Jurupinga", "Arroz"))
                        .append("madeIn", "Brazil")
                        .append("alcoholContent", 0.2)
                        .append("block", 222),

                new Document("category", "Beer")
                        .append("price", 2500)
                        .append("description", "Colombian Beer")
                        .append("units", 30)
                        .append("flavors", Arrays.asList("Mazamorra", "Chicha", "Arepa"))
                        .append("madeIn", "Colombia")
                        .append("alcoholContent", 0.3)
                        .append("block", 333),

                new Document("category", "Beer")
                        .append("price", 3000)
                        .append("description", "Argentinian Beer")
                        .append("units", 40)
                        .append("flavors", Arrays.asList("Mate", "Pasto", "Carne"))
                        .append("madeIn", "Argentina")
                        .append("alcoholContent", 0.4)
                        .append("block", 444),

                new Document("category", "Beer")
                        .append("price", 3500)
                        .append("description", "Ast Beer")
                        .append("units", 50)
                        .append("flavors", Arrays.asList("Tomato", "Potato", "Bacon"))
                        .append("madeIn", "Holand")
                        .append("alcoholContent", 0.5)
                        .append("block", 555)

        );
    }

    private List<Document> getVehicleDocuments(){
        return Arrays.asList(
                new Document("category", "Vehicle")
                        .append("price", 1500)
                        .append("description", "German Vehicle")
                        .append("units", 10)
                        .append("colors", Arrays.asList("Blue", "Red", "Yellow"))
                        .append("engineType", "XXX")
                        .append("soundType", "XXX")
                        .append("block", 111),

                new Document("category", "Vehicle")
                        .append("price", 2000)
                        .append("description", "Brazilian Vehicle")
                        .append("units", 20)
                        .append("colors", Arrays.asList("Green", "Red", "Black"))
                        .append("engineType", "YYY")
                        .append("soundType", "YYY")
                        .append("block", 222),

                new Document("category", "Vehicle")
                        .append("price", 2500)
                        .append("description", "Colombian Vehicle")
                        .append("units", 30)
                        .append("colors", Arrays.asList("Green", "Yellow", "Red"))
                        .append("engineType", "ZZZ")
                        .append("soundType", "ZZZ")
                        .append("block", 333)

        );
    }
}

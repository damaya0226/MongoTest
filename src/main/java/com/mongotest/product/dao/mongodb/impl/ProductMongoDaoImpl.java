package com.mongotest.product.dao.mongodb.impl;

import com.mongodb.client.MongoCollection;
import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.entities.ProductCategory;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by diegoamaya on 12/11/15.
 */
public class ProductMongoDaoImpl implements ProductDao{

    private MongoCollection<Document> productCollection;

    public int retrieveAllProducts() {
        List<Document> products = productCollection.find().into(new ArrayList<Document>());
        printProducts(products);
        return products.size();
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        List<Document> products = productCollection.find(lt("price", value)).into(new ArrayList<Document>());
        printProducts(products);
        return products.size();
    }

    public int retrieveAllProductsFromSpecialCategory(ProductCategory category) {
        List<Document> products = productCollection.find(eq("category", category.getCategory())).into(new ArrayList<Document>());
        printProducts(products);
        return products.size();
    }

    public void setProductCollection(MongoCollection<Document> productCollection) {
        this.productCollection = productCollection;
    }

    private void printProducts(List<Document> products){
        for(Document product : products){
            System.out.println(product);
        }
    }
}

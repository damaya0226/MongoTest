package com.mongotest.product.dao.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongotest.product.dao.ProductDao;
import org.bson.Document;

import java.math.BigDecimal;
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
        for(Document product : products){
            System.out.println(product);
        }
        return products.size();
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        List<Document> products = productCollection.find(lt("price", value)).into(new ArrayList<Document>());
        for(Document product : products){
            System.out.println(product);
        }
        return products.size();
    }

    public int retrieveAllProductsFromSpecialCategory(String category) {
        List<Document> products = productCollection.find(eq("category", category)).into(new ArrayList<Document>());
        for(Document product : products){
            System.out.println(product);
        }
        return products.size();
    }

    public void setProductCollection(MongoCollection<Document> productCollection) {
        this.productCollection = productCollection;
    }
}

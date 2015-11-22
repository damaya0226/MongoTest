package com.mongotest.server.product.dao.mongodb.impl;

import com.mongodb.client.MongoCollection;
import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductBeer;
import com.mongotest.commons.product.entities.ProductVehicle;
import com.mongotest.server.product.dao.ProductDao;
import com.mongotest.commons.product.entities.ProductCategory;
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

    public void insertProduct(Product product) {
        productCollection.insertOne(fromProductToDocument(product));
    }

    public void setProductCollection(MongoCollection<Document> productCollection) {
        this.productCollection = productCollection;
    }

    private void printProducts(List<Document> products){
        for(Document product : products){
            System.out.println(product);
        }
    }

    private Document fromProductToDocument(Product product){
        Document result = new Document();
        result.append("price", product.getPrice())
              .append("description", product.getDescription())
              .append("units", product.getUnits())
              .append("block", product.getBlock());
        if(product instanceof ProductVehicle){
            result = new Document("category", ProductCategory.Vehicle.getCategory())
                    .append("colors", ((ProductVehicle) product).getColors())
                    .append("engineType", ((ProductVehicle) product).getEngineType())
                    .append("soundType", ((ProductVehicle) product).getSoundType());
        }else if(product instanceof ProductBeer){
            result = new Document("category", ProductCategory.Beer.getCategory())
                    .append("flavors", ((ProductBeer) product).getFlavors())
                    .append("madeIn", ((ProductBeer) product).getMadeIn())
                    .append("alcoholContent", ((ProductBeer) product).getAlcoholContent());
        }
        return result;
    }

}

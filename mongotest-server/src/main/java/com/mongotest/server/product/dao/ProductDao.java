package com.mongotest.server.product.dao;

import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductCategory;


/**
 * Created by diegoamaya on 12/11/15.
 */
public interface ProductDao{

    int retrieveAllProducts();

    int retrieveAllProductsWithPriceLessThan(Integer value);

    int retrieveAllProductsFromSpecialCategory(ProductCategory category);

    void insertProduct(Product product);

}

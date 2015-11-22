package com.mongotest.server.product.service;

import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductCategory;

/**
 * Created by diegoamaya on 12/11/15.
 */
public interface ProductService {

    void retrieveAllProducts();

    void retrieveAllProductsWithPriceLessThan(Double value);

    void retrieveAllProductsFromSpecialCategory(ProductCategory category);

    void insertProduct(Product product);

}

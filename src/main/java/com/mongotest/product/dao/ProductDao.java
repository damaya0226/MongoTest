package com.mongotest.product.dao;

import com.mongotest.product.entities.ProductCategory;

import java.math.BigDecimal;

/**
 * Created by diegoamaya on 12/11/15.
 */
public interface ProductDao {

    int retrieveAllProducts();

    int retrieveAllProductsWithPriceLessThan(Integer value);

    int retrieveAllProductsFromSpecialCategory(ProductCategory category);

}

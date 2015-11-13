package com.mongotest.product.service;

import java.math.BigDecimal;

/**
 * Created by diegoamaya on 12/11/15.
 */
public interface ProductService {

    void retrieveAllProducts();

    void retrieveAllProductsWithPriceLessThan(Integer value);

    void retrieveAllProductsFromSpecialCategory(String category);

}

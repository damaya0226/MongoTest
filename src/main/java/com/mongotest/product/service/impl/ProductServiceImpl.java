package com.mongotest.product.service.impl;

import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.service.ProductService;

import java.math.BigDecimal;

/**
 * Created by diegoamaya on 12/11/15.
 */
public class ProductServiceImpl implements ProductService {

    ProductDao productDao;


    public void retrieveAllProducts() {
        productDao.retrieveAllProducts();
    }

    public void retrieveAllProductsWithPriceLessThan(Integer value) {
        productDao.retrieveAllProductsWithPriceLessThan(value);
    }

    public void retrieveAllProductsFromSpecialCategory(String category) {
        productDao.retrieveAllProductsFromSpecialCategory(category);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}

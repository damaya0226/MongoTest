package com.mongotest.server.product.service.impl;

import com.mongotest.server.product.dao.ProductDao;
import com.mongotest.commons.product.entities.ProductCategory;
import com.mongotest.server.product.service.ProductService;

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

    public void retrieveAllProductsFromSpecialCategory(ProductCategory category) {
        productDao.retrieveAllProductsFromSpecialCategory(category);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}

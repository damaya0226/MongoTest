package com.mongotest.product.dao.mssql.impl;

import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.entities.ProductCategory;

import java.math.BigDecimal;

/**
 * Created by diegoamaya on 12/11/15.
 */
public class ProductMssqlDaoImpl implements ProductDao{

    public int retrieveAllProducts() {
        return 0;
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        return 0;
    }

    public int retrieveAllProductsFromSpecialCategory(ProductCategory category) {
        return 0;
    }
}

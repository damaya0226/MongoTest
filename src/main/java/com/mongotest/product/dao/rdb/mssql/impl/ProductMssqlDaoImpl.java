package com.mongotest.product.dao.rdb.mssql.impl;

import com.mongotest.product.dao.rdb.ProductRelationalDao;
import com.mongotest.product.entities.ProductCategory;

/**
 * Created by diegoamaya on 12/11/15.
 */
public class ProductMssqlDaoImpl extends ProductRelationalDao {

    public int retrieveAllProducts() {
        return 0;
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        return 0;
    }

    public int retrieveAllProductsFromSpecialCategory(ProductCategory category) {
        return 0;
    }

    @Override
    protected String getAllProductsQuery() {
        return null;
    }

    @Override
    protected String getAllProductsLessThanQuery() {
        return null;
    }

    @Override
    protected String getAllProductsFromCategoryQuery(ProductCategory category) {
        return null;
    }
}

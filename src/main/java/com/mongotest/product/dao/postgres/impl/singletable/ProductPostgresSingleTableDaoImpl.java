package com.mongotest.product.dao.postgres.impl.singletable;

import com.mongotest.product.dao.postgres.impl.concretetable.ProductPostgresConcreteTableDaoImpl;
import com.mongotest.product.entities.ProductCategory;

/**
 * Created by diegoamaya on 18/11/15.
 */
public class ProductPostgresSingleTableDaoImpl extends ProductPostgresConcreteTableDaoImpl {

    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM PRODUCT";

    private final static String ALL_PRODUCTS_LESS_THAN_QUERY = "SELECT * FROM PRODUCT WHERE PRICE < ?";

    private final static String ALL_PRODUCTS_FROM_CATEGORY_QUERY = "SELECT * FROM PRODUCT WHERE CATEGORY = ?";

    @Override
    protected String getAllProductsQuery(){
        return ALL_PRODUCTS_QUERY;
    }

    @Override
    protected String getAllProductsLessThanQuery() {
        return ALL_PRODUCTS_LESS_THAN_QUERY;
    }

    @Override
    protected String getAllProductsFromCategoryQuery(ProductCategory category) {
        return ALL_PRODUCTS_FROM_CATEGORY_QUERY.replaceFirst("\\?", "'"+category.getCategory()+"'");
    }
}

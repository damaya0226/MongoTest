package com.mongotest.product.dao.rdb.postgres.impl.multitable;

import com.mongotest.product.dao.rdb.ProductRelationalDao;
import com.mongotest.product.entities.ProductCategory;

/**
 * Created by diegoamaya on 18/11/15.
 */
public class ProductPostgresMultipleTableDaoImpl extends ProductRelationalDao {

    private final static String ALL_PRODUCTS_QUERY = "SELECT P.ID AS ID, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, null AS FLAVORS, null AS MADEIN, \n" +
            "null AS ALCOHOL_CONTENT, BLOCK, CATEGORY FROM PRODUCT P \n" +
            "INNER JOIN PRODUCT_VEHICLE PV \n" +
            "ON P.ID = PV.ID \n" +
            "UNION ALL\n" +
            "SELECT P.ID AS ID, PRICE, DESCRIPTION, UNITS, null AS COLORS, null AS ENGINE_TYPE, null AS SOUND_TYPE,\n" +
            "FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK, CATEGORY FROM PRODUCT P \n" +
            "INNER JOIN PRODUCT_BEER PB \n" +
            "ON P.ID = PB.ID";

    private final static String ALL_PRODUCTS_LESS_THAN_QUERY = "SELECT * FROM (SELECT P.ID AS ID, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, null AS FLAVORS, null AS MADEIN, \n" +
            "null AS ALCOHOL_CONTENT, BLOCK, CATEGORY FROM PRODUCT P \n" +
            "INNER JOIN PRODUCT_VEHICLE PV \n" +
            "ON P.ID = PV.ID \n" +
            "UNION ALL\n" +
            "SELECT P.ID AS ID, PRICE, DESCRIPTION, UNITS, null AS COLORS, null AS ENGINE_TYPE, null AS SOUND_TYPE,\n" +
            "FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK, CATEGORY FROM PRODUCT P \n" +
            "INNER JOIN PRODUCT_BEER PB \n" +
            "ON P.ID = PB.ID) PRODUCTS WHERE PRICE < ?";

    private final static String ALL_PRODUCTS_FROM_CATEGORY_QUERY = "SELECT * FROM (SELECT P.ID AS ID, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, null AS FLAVORS, null AS MADEIN, \n" +
            "null AS ALCOHOL_CONTENT, BLOCK, CATEGORY FROM PRODUCT P \n" +
            "INNER JOIN PRODUCT_VEHICLE PV \n" +
            "ON P.ID = PV.ID \n" +
            "UNION ALL\n" +
            "SELECT P.ID AS ID, PRICE, DESCRIPTION, UNITS, null AS COLORS, null AS ENGINE_TYPE, null AS SOUND_TYPE,\n" +
            "FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK, CATEGORY FROM PRODUCT P \n" +
            "INNER JOIN PRODUCT_BEER PB \n" +
            "ON P.ID = PB.ID) PRODUCTS WHERE CATEGORY = ?";

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

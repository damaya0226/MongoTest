package com.mongotest.product.dao.postgres.impl;

import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.entities.ProductCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the DAO for the product concept with
 * Table per concrete class strategy (Herencia Por Tabla Concreta)
 * Created by diegoamaya on 12/11/15.
 */
public class ProductPostgresDaoImpl extends JdbcDaoSupport implements ProductDao{

    private final static String ALL_PRODUCTS_QUERY = "SELECT ID, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, \n" +
            "null AS FLAVORS, null AS MADEIN, null AS ALCOHOL_CONTENT, BLOCK, 'Vehicle' AS CATEGORY \n" +
            "FROM PRODUCT_VEHICLE\n" +
            "UNION ALL \n" +
            "SELECT ID, PRICE, DESCRIPTION, UNITS, null AS COLORS, null AS ENGINE_TYPE, null AS SOUND_TYPE,\n" +
            "FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK, 'Beer' AS CATEGORY\n" +
            "FROM PRODUCT_BEER";

    private final static String ALL_PRODUCTS_LESS_THAN_QUERY = "SELECT * FROM (SELECT ID, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, \n" +
            "null AS FLAVORS, null AS MADEIN, null AS ALCOHOL_CONTENT, BLOCK, 'Vehicle' AS CATEGORY \n" +
            "FROM PRODUCT_VEHICLE\n" +
            "UNION ALL \n" +
            "SELECT ID, PRICE, DESCRIPTION, UNITS, null AS COLORS, null AS ENGINE_TYPE, null AS SOUND_TYPE,\n" +
            "FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK, 'Beer' AS CATEGORY\n" +
            "FROM PRODUCT_BEER) PRODUCTS WHERE PRICE < ?";

    private final static String ALL_BEERS_QUERY = "SELECT * FROM PRODUCT_BEER";

    private final static String ALL_VEHICLES_QUERY = "SELECT * FROM PRODUCT_VEHICLE";

    public int retrieveAllProducts() {
        List<Map<String, Object>> products = getJdbcTemplate().queryForList(ALL_PRODUCTS_QUERY);
        printProducts(products);
        return products.size();
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        List<Map<String, Object>> products = getJdbcTemplate().queryForList(ALL_PRODUCTS_LESS_THAN_QUERY, value);
        printProducts(products);
        return products.size();
    }

    public int retrieveAllProductsFromSpecialCategory(ProductCategory category) {
        List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
        if(category.equals(ProductCategory.Beer)){
            products = getJdbcTemplate().queryForList(ALL_BEERS_QUERY);
        }else if(category.equals(ProductCategory.Vehicle)){
            products = getJdbcTemplate().queryForList(ALL_VEHICLES_QUERY);
        }
        printProducts(products);
        return products.size();
    }

    private void printProducts(List<Map<String, Object>> products){
        for(Map<String, Object> product : products){
            System.out.println(product);
        }
    }

}

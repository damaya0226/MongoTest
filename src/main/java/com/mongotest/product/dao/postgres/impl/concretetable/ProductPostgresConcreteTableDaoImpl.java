package com.mongotest.product.dao.postgres.impl.concretetable;

import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.entities.ProductCategory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;
import java.util.Map;

/**
 * This class represents the DAO for the product concept with
 * Table per concrete class strategy (Herencia Por Tabla Concreta)
 * Created by diegoamaya on 12/11/15.
 */
public class ProductPostgresConcreteTableDaoImpl extends JdbcDaoSupport implements ProductDao{

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
        List<Map<String, Object>> products = getJdbcTemplate().queryForList(getAllProductsQuery());
        printProducts(products);
        return products.size();
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        List<Map<String, Object>> products = getJdbcTemplate().queryForList(getAllProductsLessThanQuery(), value);
        printProducts(products);
        return products.size();
    }

    public int retrieveAllProductsFromSpecialCategory(ProductCategory category) {
        List<Map<String, Object>> products = getJdbcTemplate().queryForList(getAllProductsFromCategoryQuery(category));
        printProducts(products);
        return products.size();
    }

    private void printProducts(List<Map<String, Object>> products){
        for(Map<String, Object> product : products){
            System.out.println(product);
        }
    }

    protected String getAllProductsQuery(){
        return ALL_PRODUCTS_QUERY;
    }

    protected String getAllProductsLessThanQuery() {
        return ALL_PRODUCTS_LESS_THAN_QUERY;
    }

    protected String getAllProductsFromCategoryQuery(ProductCategory category) {
        String result = "";
        if(category.equals(ProductCategory.Beer)){
            result = ALL_BEERS_QUERY;
        }else if(category.equals(ProductCategory.Vehicle)){
            result = ALL_VEHICLES_QUERY;
        }
        return result;
    }
}

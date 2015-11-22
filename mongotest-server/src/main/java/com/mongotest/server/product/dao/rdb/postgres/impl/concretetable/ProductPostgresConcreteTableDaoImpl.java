package com.mongotest.server.product.dao.rdb.postgres.impl.concretetable;

import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductBeer;
import com.mongotest.commons.product.entities.ProductVehicle;
import com.mongotest.server.product.dao.rdb.ProductRelationalDao;
import com.mongotest.commons.product.entities.ProductCategory;

/**
 * This class represents the DAO for the product concept with
 * Table per concrete class strategy (Herencia Por Tabla Concreta)
 * Created by diegoamaya on 12/11/15.
 */
public class ProductPostgresConcreteTableDaoImpl extends ProductRelationalDao {

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

    private final static String INSERT_VEHICLE = "INSERT INTO PRODUCT_VEHICLE(PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, BLOCK)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final static String INSERT_BEER = "INSERT INTO PRODUCT_BEER(PRICE, DESCRIPTION, UNITS, FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK)\n" +
            "VALUES (?, ?, ?, ?, ?, ? , ?)";

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

    public void insertProduct(Product product) {
        if(product instanceof ProductVehicle){
            getJdbcTemplate().update(INSERT_VEHICLE, product.getPrice(), product.getDescription(), product.getUnits(), String.join(", ", ((ProductVehicle) product).getColors()), ((ProductVehicle) product).getEngineType(), ((ProductVehicle) product).getSoundType(), product.getBlock());
        }else {
            getJdbcTemplate().update(INSERT_BEER, product.getPrice(), product.getDescription(), product.getUnits(), String.join(", ", ((ProductBeer) product).getFlavors()), ((ProductBeer) product).getMadeIn(), ((ProductBeer) product).getAlcoholContent(), product.getBlock());
        }

    }
}

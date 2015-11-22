package com.mongotest.server.product.dao.rdb.postgres.impl.singletable;

import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductBeer;
import com.mongotest.commons.product.entities.ProductVehicle;
import com.mongotest.server.product.dao.rdb.ProductRelationalDao;
import com.mongotest.commons.product.entities.ProductCategory;

/**
 * Created by diegoamaya on 18/11/15.
 */
public class ProductPostgresSingleTableDaoImpl extends ProductRelationalDao {

    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM PRODUCT";

    private final static String ALL_PRODUCTS_LESS_THAN_QUERY = "SELECT * FROM PRODUCT WHERE PRICE < ?";

    private final static String ALL_PRODUCTS_FROM_CATEGORY_QUERY = "SELECT * FROM PRODUCT WHERE CATEGORY = ?";

    private final static String INSERT_VEHICLE = "INSERT INTO PRODUCT(CATEGORY, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, null, null, null, ?)";

    private final static String INSERT_BEER = "INSERT INTO PRODUCT(CATEGORY, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK)\n" +
            "VALUES (?, ?, ?, ?, null, null, null, ?, ?, ?, ?)";

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

    public void insertProduct(Product product) {
        if(product instanceof ProductVehicle) {
            getJdbcTemplate().update(INSERT_VEHICLE, ProductCategory.Vehicle.getCategory(), product.getPrice(), product.getDescription(), product.getUnits(), String.join(", ", ((ProductVehicle) product).getColors()), ((ProductVehicle) product).getEngineType(), ((ProductVehicle) product).getSoundType(), product.getBlock());
        }else if(product instanceof ProductBeer) {
            getJdbcTemplate().update(INSERT_BEER, ProductCategory.Beer.getCategory(), product.getPrice(), product.getDescription(), product.getUnits(), String.join(", ", ((ProductBeer) product).getFlavors()), ((ProductBeer) product).getMadeIn(), ((ProductBeer) product).getAlcoholContent(), product.getBlock());
        }
    }
}

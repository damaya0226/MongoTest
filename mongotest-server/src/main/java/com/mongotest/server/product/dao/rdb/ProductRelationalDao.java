package com.mongotest.server.product.dao.rdb;

import com.mongotest.server.product.dao.ProductDao;
import com.mongotest.commons.product.entities.ProductCategory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by diegoamaya on 20/11/15.
 */
public abstract class ProductRelationalDao extends JdbcDaoSupport implements ProductDao{

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

    protected void printProducts(List<Map<String, Object>> products){
        for(Map<String, Object> product : products){
            System.out.println(product);
        }
    }

    protected abstract String getAllProductsQuery();

    protected abstract String getAllProductsLessThanQuery();

    protected abstract String getAllProductsFromCategoryQuery(ProductCategory category);
}

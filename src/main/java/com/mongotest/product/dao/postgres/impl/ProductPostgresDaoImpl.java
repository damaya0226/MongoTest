package com.mongotest.product.dao.postgres.impl;

import com.mongotest.product.dao.ProductDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.math.BigDecimal;

/**
 * This class represents the DAO for the product concept with
 * Table per concrete class strategy (Herencia Por Tabla Concreta)
 * Created by diegoamaya on 12/11/15.
 */
public class ProductPostgresDaoImpl extends JdbcDaoSupport implements ProductDao{

    private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM PRODUCTS";


    public int retrieveAllProducts() {
        return getJdbcTemplate().queryForList(ALL_PRODUCTS_QUERY).size();
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        return 0;
    }

    public int retrieveAllProductsFromSpecialCategory(String category) {
        return 0;
    }

}

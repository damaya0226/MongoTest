package com.mongotest.product.dao.postgres.impl;

import com.mongotest.product.dao.ProductDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

/**
 * Created by diegoamaya on 12/11/15.
 */
public class ProductPostgresDaoImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    public int retrieveAllProducts() {
        jdbcTemplate.queryForList("select * from products");
        return 0;
    }

    public int retrieveAllProductsWithPriceLessThan(Integer value) {
        return 0;
    }

    public int retrieveAllProductsFromSpecialCategory(String category) {
        return 0;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

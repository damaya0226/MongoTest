package com.mongotest.product.dao;

import com.mongotest.product.entities.ProductCategory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by diegoamaya on 12/11/15.
 */
public interface ProductDao{

    int retrieveAllProducts();

    int retrieveAllProductsWithPriceLessThan(Integer value);

    int retrieveAllProductsFromSpecialCategory(ProductCategory category);

}

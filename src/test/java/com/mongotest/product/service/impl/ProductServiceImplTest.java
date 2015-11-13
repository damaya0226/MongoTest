package com.mongotest.product.service.impl;

import com.mongotest.product.dao.ProductDao;
import com.mongotest.product.service.ProductService;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by diegoamaya on 13/11/15.
 */
@Test
public class ProductServiceImplTest {

    //Class to test
    private ProductService productService;

    //Mocks
    private ProductDao mockProductDao;

    @BeforeClass
    public void setup(){
        productService = new ProductServiceImpl();
        mockProductDao = Mockito.mock(ProductDao.class);
        ((ProductServiceImpl)productService).setProductDao(mockProductDao);
    }

    @Test
    public void testRetrieveAllProducts(){
        productService.retrieveAllProducts();
        Mockito.verify(mockProductDao, Mockito.times(1)).retrieveAllProducts();
    }

    @Test
    public void testRetrieveAllProductsFromSpecialCategory(){
        productService.retrieveAllProductsFromSpecialCategory("Beer");
        Mockito.verify(mockProductDao, Mockito.times(1)).retrieveAllProductsFromSpecialCategory("Beer");
    }

    @Test
    public void testRetrieveAllProductsWithPriceLessThan(){
        productService.retrieveAllProductsWithPriceLessThan(1200);
        Mockito.verify(mockProductDao, Mockito.times(1)).retrieveAllProductsWithPriceLessThan(1200);
    }
}

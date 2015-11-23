package com.mongotest.populator.product.impl;

import com.mongotest.commons.product.entities.Product;
import com.mongotest.commons.product.entities.ProductBeer;
import com.mongotest.commons.product.entities.ProductCategory;
import com.mongotest.commons.product.entities.ProductVehicle;
import com.mongotest.populator.product.ProductPopulator;
import com.mongotest.server.product.service.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by diegoamaya on 22/11/15.
 */
public class ProductPopulatorImpl implements ProductPopulator {

    private ProductService productService;

    public void populate() {
        Double price = 1500D;
        int units = 1;
        int i = 1;
        Product productVehicle = new ProductVehicle();
        for( ; i <= NUMBER_OF_VEHICLES ; i++){
            productVehicle.setId((long) i);
            productVehicle.setPrice(new BigDecimal(price ).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
            productVehicle.setDescription(ProductCategory.Vehicle.getCategory() + i);
            productVehicle.setUnits(units);
            productVehicle.setBlock(i);
            ((ProductVehicle)productVehicle).setColors(Arrays.asList("Blue", "Red", "Yellow"));
            ((ProductVehicle)productVehicle).setEngineType("engine" + i);
            ((ProductVehicle)productVehicle).setSoundType("soundType" + i);
            productService.insertProduct(productVehicle);
            price += 2;
            units += 1;
        }
        price = 1500D;
        units = 2;

        Double alcoholContent = 0.1D;
        int block = 101;
        Product productBeer = new ProductBeer();
        for( ; i <= NUMBER_OF_BEERS ; i++){
            productBeer.setId((long) i);
            productBeer.setPrice(new BigDecimal(price ).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
            productBeer.setDescription(ProductCategory.Beer.getCategory() + i);
            productBeer.setUnits(units);
            productBeer.setBlock(block);
            ((ProductBeer)productBeer).setFlavors(Arrays.asList("Tomato", "Potato", "Banana"));
            ((ProductBeer)productBeer).setMadeIn("BeerCountry" + i);
            ((ProductBeer)productBeer).setAlcoholContent(new BigDecimal(alcoholContent ).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
            productService.insertProduct(productBeer);
            price += 5;
            units += 2;
            alcoholContent += 0.01;
            block++;
        }
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
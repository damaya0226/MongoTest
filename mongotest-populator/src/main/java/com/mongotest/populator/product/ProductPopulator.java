package com.mongotest.populator.product;

/**
 * Created by diegoamaya on 22/11/15.
 */
public interface ProductPopulator {

    Integer NUMBER_OF_BEERS = 100000;
    Integer NUMBER_OF_VEHICLES = 100000;

    void populate();
}

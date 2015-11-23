package com.mongotest.commons.product.entities;

import java.util.List;

/**
 * Represents the Beer Product DTO
 * Created by diegoamaya on 22/11/15.
 */
public class ProductBeer extends Product {

    private List<String> flavors;
    private String madeIn;
    private Double alcoholContent;

    public ProductBeer(Long id, Double price, String description, Integer units, Integer block, List<String> flavors, String madeIn, Double alcoholContent) {
        super(id, price, description, units, block);
        this.flavors = flavors;
        this.madeIn = madeIn;
        this.alcoholContent = alcoholContent;
    }

    public ProductBeer() {
        super();
    }

    public ProductBeer(Double price, String description, Integer units, Integer block, List<String> flavors, String madeIn, Double alcoholContent) {
        super(price, description, units, block);
        this.flavors = flavors;
        this.madeIn = madeIn;
        this.alcoholContent = alcoholContent;
    }

    public List<String> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<String> flavors) {
        this.flavors = flavors;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(Double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }
}



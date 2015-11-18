package com.mongotest.product.entities;

/**
 * Created by diegoamaya on 17/11/15.
 */
public enum ProductCategory {
    Vehicle("Vehicle"),
    Beer("Beer");

    private String category;

    private ProductCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }
}

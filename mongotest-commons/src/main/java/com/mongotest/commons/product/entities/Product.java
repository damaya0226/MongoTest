package com.mongotest.commons.product.entities;

/**
 * Represents the Product DTO
 * Created by diegoamaya on 22/11/15.
 */
public class Product {

    private Long id;
    private Double price;
    private String description;
    private Integer units;
    private Integer block;

    public Product() {}

    public Product(Long id, Double price, String description, Integer units, Integer block){
        this.id = id;
        this.price = price;
        this.description = description;
        this.units = units;
        this.block = block;
    }

    public Product(Double price, String description, Integer units, Integer block){
        this.price = price;
        this.description = description;
        this.units = units;
        this.block = block;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }
}

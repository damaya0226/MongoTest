package com.mongotest.commons.product.entities;

import java.util.List;

/**
 * Created by diegoamaya on 22/11/15.
 */
public class ProductVehicle extends Product{

    private List<String> colors;
    private String engineType;
    private String soundType;

    public ProductVehicle(Long id, Double price, String description, Integer units, Integer block, List<String> colors, String engineType, String soundType) {
        super(id, price, description, units, block);
        this.colors = colors;
        this.engineType = engineType;
        this.soundType = soundType;
    }

    public ProductVehicle(Double price, String description, Integer units, Integer block, List<String> colors, String engineType, String soundType) {
        super(price, description, units, block);
        this.colors = colors;
        this.engineType = engineType;
        this.soundType = soundType;
    }

    public ProductVehicle() {
        super();
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getSoundType() {
        return soundType;
    }

    public void setSoundType(String soundType) {
        this.soundType = soundType;
    }
}

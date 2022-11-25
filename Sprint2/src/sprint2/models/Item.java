/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.models;

import sprint2.utils.FoodType;

/**
 *
 * @author warre
 */
public class Item {
    
    String name;
    String id;
    Double Price;
    FoodType type;

    public Item(String name, String id, Double Price, FoodType type) {
        this.name = name;
        this.id = id;
        this.Price = Price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public Item() {
    }
    
    
}

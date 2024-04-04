package model;

import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private float price;
    private String type;
    private String name;
    private static int sId = 10000000;

    public Item() {
        this.id = sId++;
    }

    public Item(float price, String type, String name) {
        this.id = sId++;
        this.price = price;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

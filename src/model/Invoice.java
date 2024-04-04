package model;

import java.util.Date;
import java.util.List;

public class Invoice {

    private int id;
    private static int sId = 1000000;
    private Date date;
    private Customer customer;
    private List<Pair<Item, Integer>> items;
    private float totalPrice;
    private int totalItems;

    public Invoice(Customer selectedCustomer, List<Pair<Item, Integer>> purchaseItems, float totalPrice1, int totalItems1) {
        this.id = sId++;
    }

    public Invoice(Customer customer, List<Pair<Item, Integer>> items, float totalPrice, int totalItems, Date date) {
        this.id = sId++;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getsId() {
        return sId;
    }

    public static void setsId(int sId) {
        Invoice.sId = sId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pair<Item, Integer>> getItems() {
        return items;
    }

    public void setItems(List<Pair<Item, Integer>> items) {
        this.items = items;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

}

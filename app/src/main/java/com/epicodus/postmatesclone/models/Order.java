package com.epicodus.postmatesclone.models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Guest on 10/28/15.
 */
@Table (name = "Orders", id = "_id")
public class Order {
    @Column (name = "Item")
    private String item;

    @Column (name = "Quantity")
    private int quantity;

    @Column (name = "Price")
    private int price;

    @Column (name = "Total")
    private int total;

    public Order() {
        super();
    }

    public Order(String item, int quantity, int price, int total) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

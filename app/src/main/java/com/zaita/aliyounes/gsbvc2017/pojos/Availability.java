package com.zaita.aliyounes.gsbvc2017.pojos;

/**
 * Created by Lenovo on 9/4/2017.
 */

public class Availability {
    public String brand;
    public String product;
    public int quantity;
    public boolean isRemoving = false;

    public Availability(String brand, String product, int quantity) {
        this.brand = brand;
        this.product = product;
        this.quantity = quantity;
    }

    public Availability() {
    }
}

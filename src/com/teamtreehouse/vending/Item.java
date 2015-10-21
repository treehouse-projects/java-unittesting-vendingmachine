package com.teamtreehouse.vending;

public class Item {
    private final String name;
    private final int wholesalePrice;
    private final int retailPrice;

    public Item(String name, int wholesalePrice, int retailPrice) {
        this.name = name;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
    }

    public String getName() {
        return name;
    }

    public int getWholesalePrice() {
        return wholesalePrice;
    }

    public int getRetailPrice() {
        return retailPrice;
    }
}

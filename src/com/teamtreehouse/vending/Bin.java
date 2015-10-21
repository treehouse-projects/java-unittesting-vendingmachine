package com.teamtreehouse.vending;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Bin {
    private final BlockingQueue<Item> items;

    public Bin(int maxItems) {
        items = new ArrayBlockingQueue<>(maxItems);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getAvailableSlots() {
        return items.remainingCapacity();
    }

    public String getItemName() {
        if (isEmpty()) return null;
        return items.peek().getName();
    }

    public int getItemPrice() {
        return items.peek().getRetailPrice();
    }

    public Item release() {
        return items.poll();
    }

    public void restock(String name, int amount, int wholesalePrice, int retailPrice) {
        if (!isEmpty() && !name.equalsIgnoreCase(getItemName())) {
            throw new IllegalArgumentException(String.format("Cannot restock %s with %s", getItemName(), name));
        }
        if (amount > getAvailableSlots()) {
            throw new IllegalArgumentException(String.format("There are only %d spots left", getAvailableSlots()));
        }
        for (int i = 0; i < amount; i++) {
            items.add(new Item(name, wholesalePrice, retailPrice));
        }
    }

}
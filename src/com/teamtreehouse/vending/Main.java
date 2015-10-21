package com.teamtreehouse.vending;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Notifier notifier = new Notifier() {

            @Override
            public void onSale(Item item) {
                System.out.printf("Sold item %s for %s",
                        item.getName(),
                        item.getRetailPrice());
            }
        };
        VendingMachine machine = new VendingMachine(notifier, 10, 10, 10);
        try {
            System.out.println("Restocking");
            machine.restock("A1", "Twinkies", 9, 30, 75);

            System.out.println("Adding money");
            machine.addMoney(75);

            System.out.println("Vending");
            machine.vend("A1");
        } catch (InvalidLocationException ile) {
            ile.printStackTrace();
        } catch (NotEnoughFundsException nefe) {
            nefe.printStackTrace();
        }
    }
}

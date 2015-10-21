package com.teamtreehouse.vending;


public class VendingMachine {
    private final Notifier notifier;
    private final AbstractChooser chooser;
    private final Bin[][] bins;
    private int runningSalesTotal;
    private int currentCredit;

    public VendingMachine(Notifier notifier, int rowCount, int columnCount, int maxItemsPerBin) {
        this.notifier = notifier;
        chooser = new AlphaNumericChooser(rowCount, columnCount);
        runningSalesTotal = 0;
        currentCredit = 0;
        bins = new Bin[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                bins[row][col] = new Bin(maxItemsPerBin);
            }
        }
    }

    public int addMoney(int money) {
        currentCredit += money;
        return currentCredit;
    }

    public int refundMoney() {
        int refundAmount = currentCredit;
        currentCredit = 0;
        return refundAmount;
    }

    public Item vend(String input) throws InvalidLocationException, NotEnoughFundsException {
        Bin bin = binByInput(input);
        int price = bin.getItemPrice();
        if (currentCredit < price) {
            throw new NotEnoughFundsException();
        }
        currentCredit -= price;
        runningSalesTotal += price;
        Item item = bin.release();
        notifier.onSale(item);
        return item;
    }

    public int getRunningSalesTotal() {
        return runningSalesTotal;
    }

    public void restock(String input, String name, int amount, int wholesalePrice, int retailPrice) throws InvalidLocationException {
        Bin bin = binByInput(input);
        bin.restock(name, amount, wholesalePrice, retailPrice);
    }

    private Bin binByInput(String input) throws InvalidLocationException {
        AbstractChooser.Location location = chooser.locationFromInput(input);
        return bins[location.getRow()][location.getColumn()];
    }

}

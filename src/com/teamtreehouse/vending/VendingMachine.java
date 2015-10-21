package com.teamtreehouse.vending;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VendingMachine {
    private final Notifier notifier;
    private final Bin[][] bins;
    private int runningSalesTotal;
    private int currentCredit;

    public VendingMachine(Notifier notifier, int rowCount, int columnCount, int maxItemsPerBin) {
        this.notifier = notifier;
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
        int[] location = locationFromInput(input);
        return bins[location[0]][location[1]];
    }

    private int[] locationFromInput(String input) throws InvalidLocationException {
        // FIXME:csd - This only supports Alpha Numeric choosers.
        Pattern pattern = Pattern.compile("^(?<row>[a-zA-Z]{1})(?<column>[0-9]+)$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidLocationException("Invalid buttons");
        }
        return new int[]{inputAsRow(matcher.group("row")), inputAsColumn(matcher.group("column"))};
    }

    private int inputAsColumn(String columnInput) {
        int columnAsInt = Integer.parseInt(columnInput);
        return columnAsInt - 1;
    }

    private int inputAsRow(String rowInput) {
        rowInput = rowInput.toUpperCase();
        // TODO:csd - This is illegible!
        return (int) rowInput.charAt(0) - (int) 'A';
    }


}

package com.teamtreehouse.vending;

public class Creditor {
    private int funds;

    public Creditor() {
        funds = 0;
    }

    public void addFunds(int money) {
        funds += money;
    }

    public void deduct(int money) throws NotEnoughFundsException {
        if (money > funds) {
            throw new NotEnoughFundsException();
        }
        funds -= money;
    }

    public int refund() {
        int refund = funds;
        funds = 0;
        return refund;
    }

    public int getAvailableFunds() {
        return funds;
    }

}

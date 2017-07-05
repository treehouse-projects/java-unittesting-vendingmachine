package com.teamtreehouse.vending;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Chris on 6/29/17.
 */
public class BinTest {
    private Bin bin;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Before
    public void setUp() throws Exception {
        bin = new Bin(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void restockingWithDifferentItemsIsNotAllowed() throws Exception {
        bin.restock("Cheetos", 1, 100, 50);
        bin.restock("Doritos", 1, 100, 50);
    }

    @Test
    public void whenEmptyPrixeIsZero() throws Exception {
        assertEquals(0, bin.getItemPrice());
    }

    @Test
    public void whenEmptyNameIsNull() throws Exception {
        assertNull(bin.getItemName());
    }

    @Test
    public void overstockNotAllowed() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("There are only 10 spots");
        bin.restock("Fritos", 2600, 10,50);
    }
}
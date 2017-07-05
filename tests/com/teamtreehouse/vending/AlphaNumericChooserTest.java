package com.teamtreehouse.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chris on 6/29/17.
 */
public class AlphaNumericChooserTest {
    private AlphaNumericChooser chooser;
    @Before
    public void setUp() throws Exception {
        chooser = new AlphaNumericChooser(26,10);
    }

    @Test
    public void locationFromInput() throws Exception {
        AlphaNumericChooser.Location loc = chooser.locationFromInput("B4");

        assertEquals(1, loc.getRow());
        assertEquals(3, loc.getColumn());
    }

}
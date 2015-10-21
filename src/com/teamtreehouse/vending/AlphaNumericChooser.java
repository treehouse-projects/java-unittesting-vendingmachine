package com.teamtreehouse.vending;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaNumericChooser extends AbstractChooser {
    private int ALPHA_FIRST = (int) 'A';
    private int ALPHA_LAST = (int) 'Z';

    public AlphaNumericChooser(int maxRows, int maxColumns) {
        super(maxRows, maxColumns);
        int maxAllowedAlpha = (ALPHA_LAST - ALPHA_FIRST) + 1;
        if (maxRows > maxAllowedAlpha) {
            throw new IllegalArgumentException("Maximum rows supported is " + maxAllowedAlpha);
        }
    }

    @Override
    public Location locationFromInput(String input) throws InvalidLocationException {
        Pattern pattern = Pattern.compile("^(?<row>[a-zA-Z]{1})(?<column>[0-9]+)$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidLocationException("Invalid buttons");
        }
        int row = inputAsRow(matcher.group("row"));
        int column = inputAsColumn(matcher.group("column"));
        return new Location(row, column);

    }

    private int inputAsRow(String rowInput) {
        rowInput = rowInput.toUpperCase();
        return (int) rowInput.charAt(0) - ALPHA_FIRST;

    }

    private int inputAsColumn(String columnInput) {
        int columnAsInt = Integer.parseInt(columnInput);
        return columnAsInt - 1;
    }
}

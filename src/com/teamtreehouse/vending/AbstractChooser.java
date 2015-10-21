package com.teamtreehouse.vending;

public abstract class AbstractChooser {
    private final int maxRows;
    private final int maxColumns;

    public class Location {
        private final int row;
        private final int column;

        protected Location(int row, int column) throws InvalidLocationException {
            if (row > maxRows || column > maxColumns) {
                throw new InvalidLocationException("Invalid Location");
            }
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }

    public AbstractChooser(int maxRows, int maxColumns) {
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
    }

    public abstract Location locationFromInput(String input) throws InvalidLocationException;
}

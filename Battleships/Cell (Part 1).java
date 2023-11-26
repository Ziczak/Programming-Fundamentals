public class Cell {

    public static final String[] ROW_LABELS = {"A","B","C","D","E","F","G","H"};
    public static final String[] COL_LABELS = {"1","2","3","4","5","6","7","8"};

    public static final int ROW_COUNT = 8 ;
    public static final int COL_COUNT = 8 ;

    private int row;
    private int column;
    
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.column;
    }

    public boolean isInBounds() {
        return row >= 0 && row < ROW_COUNT && column >= 0 && column < COL_COUNT;
    }

    public String toString() {
        boolean isInBounds = isInBounds();
        if (isInBounds) {
            return ROW_LABELS[this.row] + COL_LABELS[this.column];
        } else {
            return "INVALID";
        }
    }

    public static Cell fromString(String position) {
        if (position == null || position.length() != 2) {
            return null;
        }

        int row = Character.toUpperCase(position.charAt(0)) - 'A';
        int column = position.charAt(1) - '1';

        if (row < 0 || row >= 8 || column < 0 || column >= 8) {
            return null;
        }

        return new Cell(row, column);
    }
}

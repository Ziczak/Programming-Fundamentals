public class Renderer {

    private static final char[] COL_LABELS = {'1','2','3','4','5','6','7','8'} ;
    private static final char[] ROW_LABELS = {'A','B','C','D','E','F','G','H'} ;

    private static final char DEFAULT = '·' ;
    private static final char HIT = '◉' ;
    private static final char MISS = '~' ;

    public static final int GRID_WIDTH = 17;
    public static final int GRID_HEIGHT = 9;

    private static int cellCol2GridCol(int cellCol) {
        return 2 + (2*cellCol);
    }

    private static int cellRow2GridRow(int cellRow) {
        return 1 + cellRow ;
    }
    
    public static char[][] renderEmptyGrid() {
        char[][] grid = new char[GRID_HEIGHT][GRID_WIDTH];

        for (int col = 0; col < GRID_WIDTH; col++) {
            grid[0][col] = ' ';
        }

        for (int col = 0; col < COL_LABELS.length; col++) {
            grid[0][cellCol2GridCol(col)] = COL_LABELS[col];
        }

        for (int row = 0; row < ROW_LABELS.length; row++) {
            int gridRow = cellRow2GridRow(row);
            grid[gridRow][0] = ROW_LABELS[row];

            for (int col = 2; col < GRID_WIDTH; col += 2) {
                grid[gridRow][col] = DEFAULT;
            }
        }

        for (int row = 1; row < GRID_HEIGHT; row++) {
            for (int col = 1; col < GRID_WIDTH; col += 2) {
                grid[row][col] = ' ';
            }
        }
        return grid;
    }
    
    public static char[][] renderFleetGrid(Fleet fleet, Guesses guesses) {
        char[][] grid = renderEmptyGrid();

        for (int row = 0; row < ROW_LABELS.length; row++) {
            for (int col = 0; col < COL_LABELS.length; col++) {
                Cell cell = new Cell(row, col);
                Ship ship = fleet.getShipAt(cell);

                if (guesses.isGuessed(cell) && ship != null) {
                    grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = HIT;
                } else if (guesses.isGuessed(cell)) {
                    grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = MISS;
                } else if (ship != null) {
                    grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = ship.getName().charAt(0);
                } else {
                    grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = DEFAULT;
                }
            }
        }
        return grid;
    }
    
    public static char[][] renderTargetingGrid(Fleet fleet, Guesses guesses) {
        char[][] grid = renderEmptyGrid();

        for (int row = 0; row < ROW_LABELS.length; row++) {
            for (int col = 0; col < COL_LABELS.length; col++) {
                Cell cell = new Cell(row, col);

                if (guesses.isGuessed(cell)) {
                    Ship ship = fleet.getShipAt(cell);

                    if (ship != null) {
                        if (ship.getHealth() == 0) {
                            grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = ship.getName().charAt(0);
                        } else {
                            grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = HIT;
                        }
                    } else {
                        grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = MISS;
                    }
                } else if (grid[cellRow2GridRow(row)][cellCol2GridCol(col)] != HIT) {
                    grid[cellRow2GridRow(row)][cellCol2GridCol(col)] = DEFAULT;
                }
            }
        }
        return grid;
    }
}

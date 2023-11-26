public class Guesses {

    private Cell[] guessedCells;
    private int totalGuesses;
    private int currentGuessIndex;

    public Guesses() {
        guessedCells = new Cell[10]; 
        totalGuesses = 0;
        currentGuessIndex = 0;
    }

    public void addGuess(Cell cell) {
        if (cell != null && !isGuessed(cell)) {
            if (currentGuessIndex == guessedCells.length) {
                Cell[] newArray = new Cell[guessedCells.length * 2];
                System.arraycopy(guessedCells, 0, newArray, 0, guessedCells.length);
                guessedCells = newArray;
            }
            guessedCells[currentGuessIndex] = cell;
            currentGuessIndex++;
            totalGuesses++;
        }
    }

    public boolean isGuessed(Cell cell) {
        for (int i = 0; i < currentGuessIndex; i++) {
            Cell guess = guessedCells[i];
            if (guess.getRow() == cell.getRow() && guess.getCol() == cell.getCol()) {
                return true;
            }
        }
        return false;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }
}

import java.util.Random ;
import java.util.Scanner ;

public class Runner {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //load fleets
        Fleet playerFleet = FleetLoader.load("fleet1.txt") ;
        Fleet enemyFleet = FleetLoader.load("fleet2.txt") ;

        //start with empty guesses
        Guesses playerGuesses = new Guesses();
        Guesses enemyGuesses = new Guesses();

        //render initial grids
        char[][] fleetGrid = Renderer.renderFleetGrid(playerFleet, enemyGuesses);
        char[][] targetingGrid = Renderer.renderTargetingGrid(enemyFleet, playerGuesses);
        printGrids(fleetGrid, targetingGrid);
        
        while (true) {
            
            //handle player guess
            Cell playerGuess = getPlayerGuess(input) ;
            enemyFleet.handleAttack(playerGuess);
            playerGuesses.addGuess(playerGuess);

            //handle enemy guess
            Cell enemyGuess = getEnemyGuess();
            playerFleet.handleAttack(enemyGuess);
            enemyGuesses.addGuess(enemyGuess);

            //render updated grids
            fleetGrid = Renderer.renderFleetGrid(playerFleet, enemyGuesses);
            targetingGrid = Renderer.renderTargetingGrid(enemyFleet, playerGuesses);
            printGrids(fleetGrid, targetingGrid);
        }

    }

    //helper method for getting a location from the user
    private static Cell getPlayerGuess(Scanner input) {
        System.out.print("Please guess a location: ");

        Cell playerGuess ;
        do {

            playerGuess = Cell.fromString(input.nextLine());
            if (playerGuess == null) {
                System.out.println("That is not a valid location");
            }

        } while (playerGuess == null) ;

        return playerGuess ;     
    }

    //helper method for getting a location randomly
    private static Cell getEnemyGuess() {
        Random r = new Random() ;

        return new Cell(r.nextInt(Cell.ROW_COUNT), r.nextInt(Cell.COL_COUNT));
    }

    // helper method for printing grids side by side
    private static void printGrids(char[][] grid1, char[][] grid2) {

        for (int r=0 ; r<Renderer.GRID_HEIGHT ; r++) {

            String line = "";

            for (int c=0 ; c<Renderer.GRID_WIDTH ; c++) {
                line += grid1[r][c];
            }
            line += "   ";
            for (int c=0 ; c<Renderer.GRID_WIDTH ; c++) {
                line += grid2[r][c];
            }
            System.out.println(line);
        }
    }


}



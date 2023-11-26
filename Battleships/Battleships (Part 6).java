import java.util.Scanner;

public class Battleships {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        EnemyAI enemyAI = new EnemyAI(args);

        Fleet playerFleet = buildPlayerFleet();
        Fleet enemyFleet = enemyAI.buildFleet();

        Guesses playerGuesses = new Guesses();
        Guesses enemyGuesses = new Guesses();

        Attack playerAttackResult = null;
        Attack enemyAttackResult = null;

        BattleshipsBoard.display(playerFleet, enemyFleet, playerGuesses, enemyGuesses, playerAttackResult, enemyAttackResult);

        while (playerFleet.shipsRemaining() > 0 && enemyFleet.shipsRemaining() > 0) {
            Cell playerGuess = getPlayerGuess(playerGuesses);
            playerGuesses.addGuess(playerGuess);
            Attack playerAttack = enemyFleet.handleAttack(playerGuess); 
            playerAttackResult = playerAttack;

            Cell enemyGuess = enemyAI.getNextGuess();
            enemyGuesses.addGuess(enemyGuess);
            Attack enemyAttack = playerFleet.handleAttack(enemyGuess);
            enemyAttackResult = enemyAttack;
            enemyAI.informAboutResult(enemyAttack);

            BattleshipsBoard.display(playerFleet, enemyFleet, playerGuesses, enemyGuesses, playerAttackResult, enemyAttackResult);
        }

        if (playerFleet.shipsRemaining() > 0) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }
    }

    private static Fleet buildPlayerFleet() {
        Fleet playerFleet = new Fleet();

        String[] shipNames = {"Carrier", "Battleship", "Destroyer", "Submarine", "Patrol Boat"};
        int[] shipLengths = {5, 4, 3, 3, 2};

        int NUM_SHIPS = shipNames.length;

        for (int i = 0; i < NUM_SHIPS; i++) {
            BattleshipsBoard.display(playerFleet);
            Ship ship = new Ship(shipNames[i], shipLengths[i]);
            addShipToFleet(ship, playerFleet);
        }
        return playerFleet;
    }

    private static void addShipToFleet(Ship ship, Fleet fleet) {
        while (!fleet.addShip(ship)) {
            System.out.println("Where would you like to place your " + ship.getName() + "?");
            String response = input.nextLine().toLowerCase();

            if (!response.matches("[a-h][1-8][hv]")) {
                System.out.println("Invalid position");
                continue;
            }

            Position position = Position.fromString(response);

            ship.setPosition(position.getLocation(), position.isHorizontal());

            if (!ship.isInBounds()) {
                System.out.println("Out of bounds");
                continue;
            }

            boolean isOccupied = false;

            for (Ship existingShip : fleet.getShips()) {
                if (existingShip.intersectsWith(ship)) {
                    isOccupied = true;
                    break;
                }
            }

            if (!isOccupied) {
                fleet.addShip(ship);
                break;
            } else {
                System.out.println("Already occupied");
            }
        }
    }

    private static Cell getPlayerGuess(Guesses playerGuesses) {
        while (true) {
            System.out.print("Please enter target: ");
            String response = input.nextLine();
            Cell guess = Cell.fromString(response);

            if (guess == null) {
                System.out.println("Invalid location");
                continue;
            }

            if (playerGuesses.isGuessed(guess)) {
                System.out.println("You guessed that already!");
                continue;
            }
            return guess;
        }
    }
}

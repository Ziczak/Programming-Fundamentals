import java.util.Scanner ;

public class Runner {

    public static void main(String[] args) {

        //Make sure you edit this to try moving the ships around and testing that you get different results
        
        Ship battleship = new Ship("Battleship", 4);
        battleship.setPosition(Cell.fromString("E1"), false);
        System.out.println("Battleship in bounds: " + battleship.isInBounds());

        Ship submarine = new Ship("Submarine", 3);
        submarine.setPosition(Cell.fromString("G2"), true);
        System.out.println("Submarine in bounds: " + submarine.isInBounds());

        System.out.println("Ships intersect: " + battleship.intersectsWith(submarine)) ;

        Scanner input = new Scanner(System.in) ;

        Guesses guesses = new Guesses() ;
        
        while (true) {
            
            System.out.println("Please enter a location:") ;
            Cell guess = Cell.fromString(input.nextLine());

            if (guess == null || guesses.isGuessed(guess)) {
                //make sure the guess is valid and new
                continue ;
            }

            Attack attack = battleship.handleAttack(guess) ;
            if (!attack.isHit()) {
                attack = submarine.handleAttack(guess);
            }

            System.out.println(attack);

            guesses.addGuess(guess) ;
        }
    }

}

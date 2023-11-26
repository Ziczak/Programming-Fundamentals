import java.util.Scanner ;

public class Runner {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in) ;

        Guesses guesses = new Guesses() ;

        //add a valid guess
        guesses.addGuess(new Cell(0,0));
        
        //add the same guess
        guesses.addGuess(Cell.fromString("A1")) ;

        //add an invalid guess
        guesses.addGuess(new Cell(8,0));

        //There should be one valid guess
        System.out.println("You have made " + guesses.getTotalGuesses() + " valid, distinct guesses") ;
        

        while (true) {

            System.out.println("Please enter a guess:") ;
            Cell guess = Cell.fromString(input.nextLine());

            if (guess == null) {
                System.out.println("Invalid guess");
                continue ;
            }

            if (guesses.isGuessed(guess)) {
                System.out.println("You have guessed this already!") ;
            }

            guesses.addGuess(guess) ;

            System.out.println("You have made " + guesses.getTotalGuesses() + " valid, distinct guesses") ;
        }
    }
}

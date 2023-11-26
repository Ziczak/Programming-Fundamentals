import java.util.Scanner ;

public class Runner {

    //You can make any changes you like to this
    public static void main(String[] args) {

        //These should be valid cells
        System.out.println(new Cell(0, 0));
        System.out.println(new Cell(1, 0));
        System.out.println(new Cell(0, 1));

        //These should be invalid cells
        System.out.println(new Cell(8, 0));
        System.out.println(new Cell(0, 8));

        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a grid location: ");
        String location = input.nextLine();

        Cell cell = Cell.fromString(location) ;
        if (cell == null) {
            System.out.println("That location is invalid");
        } else {
            System.out.println(cell + " is a valid location.");
            System.out.println("row: " + cell.getRow() + ", column: " + cell.getCol()) ;
        }
    }
}

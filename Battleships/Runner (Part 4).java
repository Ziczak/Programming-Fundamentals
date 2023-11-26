import java.util.Scanner ;

public class Runner {

    public static void main(String args[]) {
        
        Fleet fleet = new Fleet() ;

        Ship carrier = new Ship("Carrier", 5) ;
        carrier.setPosition(Cell.fromString("B2"), true);
        fleet.addShip(carrier) ;
        System.out.println("Added Carrier at B2h");
        

        Ship battleship = new Ship("Battleship", 4) ;

        //lets try a bunch of positions for battleship
        String[] battleshipLocations = {"A2","A4","A6", "B1", "C1"};
        char[] battleshipOrientations = {'v', 'v', 'v', 'h', 'h'};
        tryPositions(fleet, battleship, battleshipLocations, battleshipOrientations) ;

        Ship destroyer = new Ship("Destroyer", 3);

        //lets try a bunch of positions for destroyer
        String[] destroyerLocations = {"B2","A6","C5"};
        char[] destroyerOrientations = {'h', 'v', 'v'};
        tryPositions(fleet, destroyer, destroyerLocations, destroyerOrientations) ;
        

        Scanner input = new Scanner(System.in);

        while (fleet.shipsRemaining() > 0) {

            System.out.print("Please enter a target: ");
            Cell target = Cell.fromString(input.nextLine());

            if (target == null) {
                System.out.println("Invalid target");
                continue ;
            }

            Attack attack = fleet.handleAttack(target);
            System.out.println(attack);
        }

        
        
    }

    //Helper method to try positions for ships
    private static void tryPositions(Fleet fleet, Ship ship, String[] locations, char[] orientations) {

        for (int i=0 ; i<locations.length ; i++) {
            Cell location = Cell.fromString(locations[i]);
            boolean orientation = orientations[i] == 'h' ;

            ship.setPosition(location, orientation);

            if (!fleet.addShip(ship)) {
                System.out.println("Could not add " + ship.getName() + " at " + locations[i] + orientations[i]);
            } else {
                System.out.println("Added " + ship.getName() + " at " + locations[i] + orientations[i]);
                //We only want to add the ship once, so exit loop as soon as we do.
                break ;
            }
        }
    }

}

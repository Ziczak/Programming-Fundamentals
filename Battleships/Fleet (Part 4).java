import java.util.ArrayList;
import java.util.List;

public class Fleet {
    public static final String[] names = {"Carrier", "Battleship", "Destroyer", "Submarine", "Patrol Boat"};
    public static final int[] lengths = {5,4,3,3,2};
    private List<Ship> ships;
    
    public Fleet() {
        this.ships = new ArrayList<>();
    }
    
    public List<Ship> getShips() {
        return this.ships;
    }
    
    public int shipsRemaining() {
        int notSunkAndPositioned = 0;
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).isPositioned() && ships.get(i).getHealth() > 0) {
                notSunkAndPositioned++;
            }
        }
        return notSunkAndPositioned;
    }
    
    public boolean addShip(Ship ship) {
        if (!ship.isPositioned()) {
            return false;
        }
        
        if (!ship.isInBounds()) {
            return false;
        }
        
        for (Ship otherShip : ships) {
            if (otherShip.intersectsWith(ship)) {
                return false;
            }
        }
        ships.add(ship);
        return true;
    }

    public Ship getShipAt(Cell cell) {
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            if (ship.intersectsWith(cell)) {
                return ship;
            }
        }
        return null;
    }

    public Attack handleAttack(Cell target) {
        boolean hit = false;
        Ship sunkShip = null;
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            Attack result = ship.handleAttack(target);
            if (result.isHit()) {
                hit = true;
                if (result.getSunkShip() != null) {
                    sunkShip = result.getSunkShip();
                }
            }
        }
        return new Attack(target, hit, sunkShip);
    }
}

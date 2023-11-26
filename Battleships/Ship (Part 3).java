import java.util.*;

public class Ship {
    private String name;
    private int length;
    private int health;
    private boolean isHorizontal;
    private List<Cell> cells;
    private boolean isPositioned;

    private static final int ROW_COUNT = 8;
    private static final int COL_COUNT = 8;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.health = length;
        this.isHorizontal = true;
        this.cells = new ArrayList<>();
        this.isPositioned = false;
        for (int i = 0; i < length; i++) {
            cells.add(new Cell(0, i));
        }
    }

    private int getRow() {
        return this.cells.get(0).getRow();
    }

    private int getCol() {
        return this.cells.get(0).getCol();
    }

    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.length;
    }

    public int getHealth() {
        return this.health;
    }

    private boolean reduceHealth() {
        if (this.health > 0) {
            health--;
            return true;
        }
        return false;
    }

    private boolean isSunk() {
        return this.health == 0;
    }

    public void setPosition(Cell cell, boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
        this.cells.clear();

        for (int i = 0; i < length; i++) {
            if (isHorizontal) {
                cells.add(new Cell(cell.getRow(), cell.getCol() + i));
            } else {
                cells.add(new Cell(cell.getRow() + i, cell.getCol()));
            }
        }
        this.isPositioned = true;
    }

    public boolean isPositioned() {
        return (this.cells.size() == this.length && this.isPositioned);
    }

    public boolean isInBounds() {
        int row = this.cells.get(0).getRow();
        int col = this.cells.get(0).getCol();

        if (isHorizontal) {
            return row >= 0 && row < ROW_COUNT && col >= 0 && col + length - 1 < COL_COUNT;
        } else {
            return row >= 0 && row + length - 1 < ROW_COUNT && col >= 0 && col < COL_COUNT;
        }
    }

    public boolean intersectsWith(Cell cell) {
        if (!isPositioned()) {
            return false;
        }

        if (cell == null) {
            return false;
        }
        
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getRow() == cell.getRow() && cells.get(i).getCol() == cell.getCol()) {
                return true;
            }
        }
        return false;
    }
   
    public boolean intersectsWith(Ship otherShip) {
        if (!this.isPositioned() || !otherShip.isPositioned()) {
            return false;
        }
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < otherShip.cells.size(); j++) {
                int thisCellRow = cells.get(i).getRow();
                int thisCellCol = cells.get(i).getCol();
                int otherCellRow = otherShip.cells.get(j).getRow();
                int otherCellCol = otherShip.cells.get(j).getCol();
                if (thisCellRow == otherCellRow && thisCellCol == otherCellCol) {
                    return true;
                }
            }
        }
        return false;
    }

    public Attack handleAttack(Cell target) {
        if (!isPositioned()) {
            return new Attack(target, false, null);
        }

        boolean hit = false;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getRow() == target.getRow() && cells.get(i).getCol() == target.getCol()) {
                hit = true;
                if (!isSunk()) {
                    reduceHealth();
                }
                break;
            }
        }
        boolean sunk = isSunk();
        if (sunk) {
            hit = true;
        }
        Attack attack;
        if (sunk) {
            attack = new Attack(target, hit, this);
        } else {
            attack = new Attack(target, hit, null);
        }
        return attack;  
    }
}

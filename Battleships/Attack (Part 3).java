public class Attack {

    private Cell target;
    private boolean hit;
    private Ship sunkShip;

    public Attack(Cell target, boolean hit, Ship sunkShip) {
        this.target = target;
        this.hit = hit;
        this.sunkShip = sunkShip;
    }

    public Cell getTarget() {
        return this.target;
    }

    public boolean isHit() {
        return this.hit;
    }

    public Ship getSunkShip() {
        return sunkShip;
    }

    public String toString() {
        String result = target.toString();
        if (hit) {
            result += " hits";
            if (sunkShip != null) {
                result += ", and sinks the " + sunkShip.getName() + "!";
            }
            else {
                result += "!";
            }
        }
        else {
            result += " misses!";
        }
        return result;
    }
}

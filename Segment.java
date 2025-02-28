public class Segment /* extends Ship */ {
    
    private Ship ship;
    private boolean isHit;

    public Segment(Ship x) {
        this.ship = x;
        this.isHit = false;
    }

    public boolean hit() {
        return this.isHit;
    }

    public void attack() {
        this.isHit = true;
    }

    public Ship getShip() {
        return ship;
    }

    @Override
    public String toString() {  
        return ship.toString();
    }
}

public class Cell {
    
    private boolean occupied;
    private boolean cellIsHit;
    private Segment segment;
    

    public Cell() {
        this.occupied = false;
    }

    public void placeSegment(Segment s) {
        if (this.occupied == false) {
            this.segment = s;
            this.occupied = true;
        }
        // else if (s == null) {
        //     this.occupied = false;
        // }
    }

    public void attack() {
        if (occupied == true) {
            this.segment.attack();
        }
        this.cellIsHit = true;
    }

    public boolean hasBeenHit() {
        if (occupied == true) {
            return this.segment.hit();
        }
        return this.cellIsHit;
    }

    public boolean isOccupied() {
        if (this.segment != null) {
            return this.occupied;
        }
        return false;
    }

    public String displaySetup() {
        if (occupied == true) {
            return this.segment.toString();
        }
        else {
            return ".";
        }
    }

    @Override
    public String toString() {
        if (hasBeenHit() == false) {
            return ".";
        }
        else if (hasBeenHit() == true && isOccupied() == false) {
            return "O";
        }
        else if (hasBeenHit() == true && isOccupied() == true && this.segment.getShip().sunk() == false) {
            return "X";
        }
        else {
            return displaySetup();
        }
    }
}

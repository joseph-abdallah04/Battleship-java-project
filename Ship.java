import java.io.*;
import java.util.*;
public class Ship {

    private String type;
    private String name;
    private int length;
    List<Segment> segments = new ArrayList<>();

    private Ship(String type) {
        this.type = type;
        for(int i = 0; i < length(); i++) {
            segments.add(new Segment(this)); 
            // This may need to be changed fom add(i) to the representation of the different ships (eg B or battleship: B, B, B, B), instead of numbers 1, 2, 3, 4 etc.
        }
        System.out.println(segments.size());
    }

    private String getShipType() {
        return this.type;
    }

    public int length() { 
        String x = this.type;
        switch (x) {
            case("battleship"):
                this.length = 4;
                break;
            case("carrier"):
                this.length = 5;
                break;
            case("destroyer"):
                this.length = 3;
                break;
            case("submarine"):
                this.length = 3;
                break;
            case("patrol boat"):
                this.length = 2;
                break;
            default:
                this.length = 0;
                break;
        }
        return this.length;
    }


    public String name() {
        String x = this.type;
        switch (x) {
            case("battleship"):
                return "Battleship";
            case("carrier"):
                return "Carrier";
            case("destroyer"):
                return "Destroyer";
            case("submarine"):
                return "Submarine";
            case("patrol boat"):
                return "Patrol Boat";
            default:
                return null;
        }
    }

    /*
    The logic behind this method is that the for loop essentially adds 1 to a variable "adder" for each time a segment is hit 
    (until the length of the segments array). If it is not hit, the loop breaks as that means it will not be sunk so its pointless 
    itterating again. Then it compares this variable to the length of the segments array, and if they are the same, then we know that 
    the ship is sunk.
    */
    public boolean sunk() {
        int adder = 0;
        for (int i = 0; i < segments.size(); i++){
            if (this.segments.get(i).hit() == true) {
                adder++;    
            }
            else {
                break;
            }
        }
        
        if (adder == segments.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        String x = this.type;
        switch (x) {
            case("battleship"):
                return "B";
            case("carrier"):
                return "C";
            case("destroyer"):
                return "D";
            case("submarine"):
                return "S";
            case("patrol boat"):
                return "P";
            default:
                return null;
        }
    }

    public Segment getSegment(int x) {
        if (x >= 1 && x <= segments.size()) {
            return segments.get(x - 1); 
        }
        else {
            return null;
        }
    }

    public static Ship createShip(String s) {
        s = s.toLowerCase();
        switch (s) {
            case("battleship"):
            case("carrier"):
            case("destroyer"):
            case("submarine"):
            case("patrol boat"):
                return new Ship(s);
            default:
                return null;
        }
    }

}

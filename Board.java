public class Board {
    
    private Cell[][] coords;

    public Board() {
        coords = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int e = 0; e < 10; e++) {
                coords[i][e] = new Cell();
            }
        }
    }

    private boolean isOccupiedDown(Ship ship, String startPos) {
        for(int i = getY(startPos); i < (getY(startPos) + ship.length()); i++) { //could possibly be i++
            if (i > 9) {
                return true;
            }    
            if (coords[getX(startPos)][i].isOccupied()) {
                return true;
            }
            
        }
        return false;
    }

    private boolean isOccupiedAcross(Ship ship, String startPos) {
        for (int i = getX(startPos); i < (getX(startPos) + ship.length()); i++) {
            if (i > 9) {
                return true;
            }   
            if (coords[i][getY(startPos)].isOccupied()) {
                return true;
            }
            
        }
        return false;
    }

    public void placeShip(Ship ship, String startPos, String direction) throws 
    InvalidPlacementException, InvalidPositionException, InvalidShipTypeException {
        if (ship == null) {
            throw new InvalidShipTypeException();
        }
        if (!direction.equals("down") && !direction.equals("across")) {
            throw new InvalidPlacementException();
        }
        if (positioner(startPos) == null) {
            throw new InvalidPositionException();
        }
        if(direction.equals("down")) {
            if (isOccupiedAcross(ship, startPos) == true) {
                throw new InvalidPlacementException();
            }
            for (int i = getX(startPos); i < (getX(startPos) + ship.length()); i++) {
                coords[i][getY(startPos)].placeSegment(ship.getSegment(i - getX(startPos) + 1));
            }
        }
        else if (direction.equals("across")) {
            if (isOccupiedDown(ship, startPos) == true) {
                throw new InvalidPlacementException();
            }
            for (int i = getY(startPos); i < (getY(startPos) + ship.length()); i++) {
                coords[getX(startPos)][i].placeSegment(ship.getSegment(i - getY(startPos) + 1));
            }
        }
    }

    public void attack(String position) throws InvalidPositionException {
        Cell cell = positioner(position);
        if(cell != null) {
            cell.attack();
        }
        else {
            throw new InvalidPositionException();
        }
    }

    public boolean hasBeenHit(String position) throws InvalidPositionException {
        Cell cell = positioner(position);
        if(cell != null) {
            return cell.hasBeenHit();
        }
        else {
            throw new InvalidPositionException();
        }
    }

    @Override
    public String toString() {
        String x = "  1 2 3 4 5 6 7 8 9 10\n";
        char c = 'A';
        for (Cell[] cells: coords) {
            x += c++;
            for (Cell cell : cells) {
                x += " " + cell.toString();
            }
            x += "\n";
        } 
        return x;
    }

    public String displaySetup() {
        String x = "  1 2 3 4 5 6 7 8 9 10\n";
        char c = 'A';
        for (Cell[] cells: coords) {
            x += c++;
            for (Cell cell : cells) {
                x += " " + cell.displaySetup();
            }
            x += "\n";
        } 
        return x;
    }

    private int getX(String position) {
        char a = position.toLowerCase().charAt(0);
        int row = a - 97;
        return row;
    }

    private int getY(String position) {
        int column = Integer.parseInt(position.substring(1));
        return column - 1;
    }

    private Cell positioner(String position) {
        try {
            char a = position.toLowerCase().charAt(0);
            int column = Integer.parseInt(position.substring(1)); 
            int row = a;
            return coords[(row - 97)][(column - 1)];
        } 
        catch (IndexOutOfBoundsException e) {
            return null;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
}

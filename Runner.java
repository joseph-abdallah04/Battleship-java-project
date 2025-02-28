public class Runner {
    public static void main(String[] args) {
            Board board = new Board();
            Ship ship = Ship.createShip("battleship");
            try {
                board.placeShip(ship, "A6", "down");
                board.placeShip(ship, "g8", "across");
            }
            catch (Exception e) {
                //...
            }
            System.out.println(board.displaySetup());
    }
}

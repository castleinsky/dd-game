package game.player;

import java.util.Random;

public class RobotPlayer extends AbstractPlayer {

    private static Random r = new Random();
    private final int cols;
    public RobotPlayer(int id, String symbol, int cols) {
        super(id, symbol);
        this.cols = cols;
    }
    @Override
    public String move() {
        String col = String.valueOf(r.nextInt(cols) + 1);
        printMove(col);
        return col;
    }
}

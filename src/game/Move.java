package game;

public class Move {
    public final int playerIndex;
    public final int row;
    public final int col;

    public Move(int playerIndex, int row, int col) {
        this.playerIndex = playerIndex;
        this.row = row;
        this.col = col;
    }
}
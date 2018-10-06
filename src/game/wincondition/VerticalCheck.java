package game.wincondition;

import game.Board;
import game.Move;

import java.util.function.Predicate;

public class VerticalCheck implements Predicate<Move> {

    private final int[][] board;
    private final int streak;
    public VerticalCheck(int[][] board) {
        this.board = board;
        this.streak = 4;
    }
    @Override
    public boolean test(Move lastMove) {

        int count = 1;
        int cols = board[0].length;
        int row = lastMove.row;
        int c = lastMove.col - 1;

        while(c >=0 && board[row][c] == lastMove.playerIndex){
            ++count;
            --c;
        }

        c = lastMove.col + 1;
        while(c < cols && board[row][c] == lastMove.playerIndex) {
            ++count;
            ++c;
        }
        return count == streak;
    }
}

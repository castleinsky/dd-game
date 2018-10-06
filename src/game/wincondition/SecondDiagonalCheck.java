package game.wincondition;

import game.Board;
import game.Move;

import java.util.function.Predicate;

public class SecondDiagonalCheck implements Predicate<Move> {

    private final int[][] board;
    private final int streak;
    public SecondDiagonalCheck(int[][] board) {
        this.board = board;
        this.streak = 4;
    }

    @Override
    public boolean test(Move lastMove) {
        int rows = board.length;
        int cols = board[0].length;
        int r = lastMove.row - 1, c = lastMove.col + 1;
        //towards upper left
        int count = 1;
        while(r >=0 && c < cols && board[r][c] == lastMove.playerIndex) {
            ++count;
            --r;
            ++c;
        }
        //towards bottom right
        r = lastMove.row + 1;
        c = lastMove.col - 1;
        while(r < rows && c >=0 && board[r][c] == lastMove.playerIndex) {
            ++count;
            ++r;
            --c;
        }
        return count == streak;
    }
}

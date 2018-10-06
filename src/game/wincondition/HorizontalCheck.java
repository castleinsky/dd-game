package game.wincondition;

import game.Board;
import game.Move;

import java.util.function.Predicate;

public class HorizontalCheck implements Predicate<Move> {

    private final int[][] board;
    private final int streak;
    public HorizontalCheck(int[][] board) {
        this.board = board;
        this.streak = 4;
    }
    @Override
    public boolean test(Move lastMove) {
        //towards left;
        int count = 1;
        int rows = board.length;
        int col = lastMove.col;
        int row = lastMove.row - 1 ;
        //up
        while(row >=0 && board[row][col] == lastMove.playerIndex) {
            ++count;
            --row;
        }
        row = lastMove.row + 1;
        //down
        while(row < rows && board[row][col] == lastMove.playerIndex) {
            ++count;
            ++row;
        }
        return count == streak;
    }
}

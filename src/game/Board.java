package game;

import game.wincondition.FirstDiagonalCheck;
import game.wincondition.HorizontalCheck;
import game.wincondition.SecondDiagonalCheck;
import game.wincondition.VerticalCheck;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Board {
    private final int[][] board;
    private final int numRows;
    private final int numCols;
    private final String[] playerSymbols;
    private final List<Predicate<Move>> winningConditions;

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public Board(int[][] board, String[] playerSymbols) {
        this.board = board;
        this.numRows = board.length;
        this.numCols = board[0].length;
        this.playerSymbols = playerSymbols;
        this.winningConditions =
                Arrays.asList(
                    new HorizontalCheck(this.board),
                    new VerticalCheck(board),
                    new FirstDiagonalCheck(board),
                    new SecondDiagonalCheck(board)
        );
    }

    public boolean isValidMove(Move move) {
        if (move == null || move.row < 0 || move.col < 0) {
            return false;
        }

        if (move.row >= numRows || move.col >= numCols) {
            return false;
        }

        // can't place over existing piece
        if (board[move.row][move.col] > -1) {
            return false;
        }

        // check that we are stacking if not on bottom row
        if (move.row < numRows - 1 && board[move.row + 1][move.col] < 0) {
            return false;
        }

        return true;
    }

    private int moveCount = 0;
    public void updateBoard(Move move) {
        ++moveCount;
        board[move.row][move.col] = move.playerIndex;
    }

    public String boardTerminalString(int[][] board, String[] playerSymbols) {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int col = 0; col < numCols; col++) {
            sb.append(String.format("%1$" + 3 + "s", col + 1 + ""));
        }
        sb.append("\n");
        for (int row = 0; row < numRows; row++) {
            sb.append(String.format("%1$" + 3 + "s", row + 1 + ""));
            for (int col = 0; col < numCols; col++) {
                int playerIndex = board[row][col];
                String playerSymbol = playerIndex > -1 ? playerSymbols[playerIndex] : ".";
                playerSymbol = String.format("%1$" + 3 + "s", playerSymbol);
                sb.append(playerSymbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void printBoard() {
        System.out.println("\n" + boardTerminalString(board, playerSymbols) + "\n");
    }

    /**
     *
     * This method is called after each move.
     *
     * @param lastMove = Move object representing the last move made
     * @return index of winning player; -1 if no winner detected
     */
    public int winningPlayerIndex(Move lastMove) {
        return winningConditions.stream().anyMatch( x -> x.test(lastMove))
                ? lastMove.playerIndex : -1;
    }

    public boolean isGameOver(Move move) {
        if (winningPlayerIndex(move) != -1) {
            printBoard();
            System.out.println("\n\nPlayer #" + (move.playerIndex + 1) + " wins!\n\n");
            return true;
        }
        if (moveCount == numRows * numCols) {
            printBoard();
            System.out.println("\n\nIt's a draw!");
            return true;
        }
        return false;
    }
}

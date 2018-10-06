package game;


/*
 Connect 4

 Description
 Complete the given terminal-based Connect 4 game:
 - Connect 4 is played on a 6 high x 7 wide grid.
 - There are two players.
 - Each player has different colored pieces.
 - During each round, each player has an opportunity to place a piece.
 - The board is filled from the bottom up: pieces are dropped into a column and stacked.
 - The first player to place 4 pieces in a line wins.
 - Winning lines can be vertical, horizontal, or diagonal.

 A quick video on Connect 4: https://www.youtube.com/watch?v=yDWPi1pZ0Po

 Complete and Enhance the Game:
 - Get as far as you can, but the main goal is to get the basic game working. The initial code is provided only as a basic guide -- feel free to change it as necessary. Good luck!

 Detect When Game Ends:
 - Complete the game so it stops when a player win is detected.

 Better User Interface:
 - Streamline the input so that only a column input is required.

 Enable Robot Players
 - Enhance the game so that players can be robots or humans: implement a “random move” robot.
 - Enhance the game to accommodate both human vs. robot and robot vs. robot.
*/

import game.env.Environment;
import game.player.AbstractPlayer;
import game.player.HumanPlayer;
import game.player.RobotPlayer;

import java.util.Arrays;

public class Game {

    private static final String[] PLAYER_SYMBOLS = new String[]{"X", "O", "A", "B", "C", "D", "E", "Y", "Z", "H"};


    private int players;
    private AbstractPlayer[] playersAr;
    private final int[] colIndex;
    private final Board board;


    public Game(GameParams params) {
        int[][] boardArray = new int[params.getRows()][params.getCols()];
        for (int i = 0; i < params.getRows(); i++) {
            Arrays.fill(boardArray[i], -1);
        }

        initializePlayers(params.getHumans(), params.getRobots(), params.getCols());
        //colIndex for O(1) look up.
        colIndex = new int[params.getCols()];
        //intialized all indexes with appropriate values
        Arrays.fill(colIndex, params.getRows() - 1);

        board = new Board(boardArray, PLAYER_SYMBOLS);
    }

    private void initializePlayers(int humans, int robots, int cols) {
        this.players = humans + robots;
        this.playersAr = new AbstractPlayer[players];
        for(int i = 0; i < humans; ++i) {
            playersAr[i] = new HumanPlayer(i, PLAYER_SYMBOLS[i]);
        }
        for(int i = humans; i < players; ++i) {
            RobotPlayer p = new RobotPlayer(i, PLAYER_SYMBOLS[i], cols);
            playersAr[i] = p;
        }
    }

    /**
     * @param playerIndex
     * @param moveString
     * @return Move object or null if unable to parse
     */
    public Move parseMove(int playerIndex, String moveString) {
        try {
            if (moveString.isEmpty() || moveString == null) {
                return null;
            }
            int col = Integer.parseInt(moveString.trim()) - 1;
            int row = colIndex[col];
            return new Move(playerIndex, row , col);
        } catch (Exception e) {
            return null;
        }
    }


    public void start() {
        while (true) {
            for (int currentPlayerIndex = 0; currentPlayerIndex < players; currentPlayerIndex++) {
                board.printBoard();
                Move move = null;
                while (true) {
                    String moveString = playersAr[currentPlayerIndex].move();
                    move = parseMove(currentPlayerIndex, moveString);
                    if (board.isValidMove(move)) {
                        --colIndex[move.col];
                        board.updateBoard(move);
                        break;
                    } else {
                        System.out.println("INVALID MOVE");
                    }

                }// while
                if(board.isGameOver(move)) {
                    System.out.println("Game Over!");
                    System.exit(0);
                }

            }
        }
    }

    public static void main(String[] args) throws Throwable {
        GameParams gameParams = Environment.getGameParams();
        System.out.println(gameParams);
        Game game = new Game(gameParams);
        game.start();
    }
}
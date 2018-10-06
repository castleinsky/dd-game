package game.player;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(int id, String symbol) {
        super(id, symbol);
    }

    @Override
    public String move() {
        String move = getUserInput(String.format("Player #%d - %s = Move [ col ]:  ", (getId() + 1), getSymbol()));
        printMove(move);
        return move;
    }

    public String getUserInput(String prompt) {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print(prompt);

        // get their input as a String
        String userInput = scanner.next();

        return userInput;
    }
}

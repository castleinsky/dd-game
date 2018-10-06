package game.player;

public abstract class AbstractPlayer {

    private int id;
    private String symbol;

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public AbstractPlayer(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;

    }

    public abstract String move();
    public void printMove(String column) {
        System.out.println(String.format("Player [%d-%s] played in column %s", (id + 1), symbol, column));
    }
}

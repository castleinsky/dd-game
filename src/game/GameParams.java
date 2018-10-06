package game;

public class GameParams {

    private final int rows;
    private final int cols;

    public GameParams(int rows, int cols, int humans, int robots) {
        this.rows = rows;
        this.cols = cols;
        this.humans = humans;
        this.robots = robots;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getHumans() {
        return humans;
    }

    public int getRobots() {
        return robots;
    }

    private final int humans;
    private final int robots;

    public String toString() {
        return String.format("Game Params => Board Rows = %d, Cols = %d, Humans = %d, Robots = %d"
                , getRows(), getCols(), getHumans(), getRobots());
    }
}

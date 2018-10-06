package game.env;

import game.GameParams;

public class Environment {

    public static GameParams getGameParams() {
        int rows = int32OrDefault(System.getProperty("rows"), 6);
        int cols = int32OrDefault(System.getProperty("cols"), 7);
        int humans = int32OrDefault(System.getProperty("humans"), 1);
        int robots = int32OrDefault(System.getProperty("robots"), 1);
        return new GameParams(rows, cols, humans, robots);
    }

    public static int int32OrDefault(String in, int defaultValue) {
        if (in == null || in.trim().length() == 0) {
            return defaultValue;
        }
        return Integer.parseInt(in);
    }
}

package animalchess;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a game.
 * It will mainly have the behaviour of setting the squares on
 * the board and manage winner of games.
 *
 * @author Yoochul Kim
 * @see Square
 * @see Player
 */
public class Game {
    /**
     * The column size of the chess board.
     */
    public static final int HEIGHT = 6;
    /**
     * The row size of the chess board.
     */
    public static final int WIDTH = 5;
    /**
     * The minimum row in which pieces can be placed.
     */
    public static final int MIN_ROW = 0;
    /**
     * The maximum row in which pieces can be placed.
     */
    public static final int MAX_ROW = HEIGHT - 1;
    /**
     * The minimum column in which pieces can be placed.
     */
    public static final int MIN_COL = 0;
    /**
     * The maximum column in which pieces can be placed.
     */
    public static final int MAX_COL = WIDTH - 1;
    /**
     * The max player number can be played in one game.
     */
    public static final int MAX_PLAYER_NUMBER = 2;
    private final Square[][] square2DArray;

    private final List<Player> players;
    private Player winner;

    public enum Side {
        UPPER,
        LOWER
    }

    /**
     * Constructor to create {@code Game} with
     * given two {@code Player}.
     *
     * @param p0 the first player of the game
     * @param p1 the second player of the game
     */
    public Game(Player p0, Player p1) {
        players = new ArrayList<>();
        players.add(p0);
        players.add(p1);
        square2DArray = new Square[HEIGHT][WIDTH];
        setBoard();
    }

    /**
     * Initializes two-dimensional square array and set animals on the board.
     */
    private void setBoard() {
        // Set 30 (6x5) squares on the game
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                square2DArray[i][j] = new Square(this, i, j);
            }
        }

        // Set pieces of the 1st player
        square2DArray[0][0].placePiece(new Cat(players.get(0), square2DArray[0][0]));
        square2DArray[0][1].placePiece(new Dog(players.get(0), square2DArray[0][1]));
        square2DArray[0][2].placePiece(new Lion(players.get(0), square2DArray[0][2]));
        square2DArray[0][3].placePiece(new Dog(players.get(0), square2DArray[0][3]));
        square2DArray[0][4].placePiece(new Cat(players.get(0), square2DArray[0][4]));
        square2DArray[2][1].placePiece(new Chick(players.get(0), square2DArray[2][1]));
        square2DArray[2][2].placePiece(new Chick(players.get(0), square2DArray[2][2]));
        square2DArray[2][3].placePiece(new Chick(players.get(0), square2DArray[2][3]));

        // Set pieces of the 2nd player
        square2DArray[5][0].placePiece(new Cat(players.get(1), square2DArray[5][0]));
        square2DArray[5][1].placePiece(new Dog(players.get(1), square2DArray[5][1]));
        square2DArray[5][2].placePiece(new Lion(players.get(1), square2DArray[5][2]));
        square2DArray[5][3].placePiece(new Dog(players.get(1), square2DArray[5][3]));
        square2DArray[5][4].placePiece(new Cat(players.get(1), square2DArray[5][4]));
        square2DArray[3][1].placePiece(new Chick(players.get(1), square2DArray[3][1]));
        square2DArray[3][2].placePiece(new Chick(players.get(1), square2DArray[3][2]));
        square2DArray[3][3].placePiece(new Chick(players.get(1), square2DArray[3][3]));
    }

    /**
     * Get the {@code Square} located in the row and col.
     *
     * @param row the row in the game
     * @param col the column in the game
     * @return the {@code Square} located in passed row and col
     */
    public Square getSquare(int row, int col) {
        return square2DArray[row][col];
    }

    /**
     * Get one of the players who has the player number.
     *
     * @param playerNumber the number held by one of the players participating in the game
     * @return the player who has the player number
     * @throws IllegalArgumentException if the player number does not exist
     */
    public Player getPlayer(int playerNumber) throws IllegalArgumentException {
        int maxPlayerNumber = players.size();

        // Only 2 players can play in a game
        if (playerNumber >= maxPlayerNumber || playerNumber < 0) {
            throw new IllegalArgumentException("a player with the passed number does not exists");
        }

        return players.get(playerNumber);
    }

    /**
     * Get the winner of the game.
     * If a player catches the opponent lion,
     * the game will end and have the winner.
     *
     * @return the player who wins the game
     */
    public Player getWinner() {
        // Check if there is winner
        for (Player player : players) {
            if (player.hasWon()) winner = player;
        }
        return winner;
    }
}

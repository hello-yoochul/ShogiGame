package animalchess;

import java.util.ArrayList;

/**
 * This class implements a player participating in a game.
 * <p>
 * General behaviour is managing own state like the state of
 * name, player number, and {@code isWinner}. Another behaviour
 * is dropping a piece on the board and adding a piece to the hand.
 *
 * @author Yoochul Kim
 */
public class Player {
    private String name;
    private int playerNumber;

    private Game.Side side;

    // When a player catches an opponent piece, it will be added.
    private ArrayList<Piece> piecesInHand;

    // true if a player catches an opponent lion.
    private boolean isWinner = false;

    /**
     * Constructor to create a {@code Player} with
     * given {@code String} and {@code int}.
     *
     * @param name         the {@code String} name of this player.
     * @param playerNumber the {@code int} number of this player.
     */
    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        piecesInHand = new ArrayList<>();
        side = playerNumber == 0 ? Game.Side.UPPER : Game.Side.LOWER;
    }

    /**
     * Adds the {@code Piece} to the player's hand.
     * When a player catches an opponent piece it will be invoked.
     *
     * @param piece the {@code Piece} which this player captures
     */
    public void addPieceToHand(Piece piece) {
        piecesInHand.add(piece);
    }

    /**
     * Get the player name whose this player has.
     *
     * @param piece  One of {@code Piece} in {@code piecesInHand} which will be dropped.
     * @param square The {@code Square} which the {@code Piece} go to.
     */
    public void dropPiece(Piece piece, Square square) {
        // Only allows a player to place the piece if he/she has it in his/her hand.
        if (piecesInHand.contains(piece)) {
            // remove the piece in the hand
            piecesInHand.remove(piece);
            // remove the piece from the square where the player wants to go.
            square.removePiece();
            // put the piece to the square where the player wants to go.
            square.placePiece(piece);
        }
    }

    /**
     * Get the player name.
     *
     * @return the player name from the {@code name}.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the player number from the {@code playerNumber}.
     *
     * @return the player number from the {@code playerNumber}.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Get the pieces in the player hand, {@code piecesInHand}.
     * It can be used for testing if {@code dropPiece} and {@code addPiece}
     * working well.
     *
     * @return the {@code ArrayList<Piece>} from the {@code piecesInHand}.
     */
    public ArrayList<Piece> getHand() {
        return piecesInHand;
    }

    /**
     * Change this player's victory status to the true.
     */
    public void winGame() {
        isWinner = true;
    }

    /**
     * Returns the state of this player's victory.
     *
     * @return true if this player has won.
     */
    public boolean hasWon() {
        return isWinner;
    }

    public Game.Side getSide() {
        return side;
    }
}

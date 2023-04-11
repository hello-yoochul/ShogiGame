package animalchess;

import java.util.ArrayList;

/**
 * This class implements a lion piece which extends {@code Piece}.
 * <p>
 * The lion can move one space in any direction.
 * Once lion is caught, the game is over.
 *
 * @author Yoochul Kim
 */
public class Lion extends Piece {

    /**
     * Constructor to create {@code Lion} with
     * given {@code Player} and {@code Square}.
     *
     * @param owner the {@code Player} will be set on this {@code Lion}.
     * @param square the {@code Square} will be set on this {@code Lion}.
     */
    public Lion(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * Get the array of {@code Square} which this chick can move to.
     * If this chick is promoted, then move like {@code Dog}.
     *
     * @return the array of {@code Square} which this chick can move to.
     */
    @Override
    public ArrayList<Square> getLegalMoves() {
        return super.getLegalMoves();
    }

    /**
     * The lion will be captured by the capturer and call the {@code winGame()} of the capturer.
     *
     * @param capturer The {@code Player} which will catch this lion.
     */
    @Override
    public void beCaptured(Player capturer) {
        capturer.winGame();
    }
}

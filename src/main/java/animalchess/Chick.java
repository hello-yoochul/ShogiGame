package animalchess;

import java.util.ArrayList;

/**
 * This class implements a chick piece. By extending
 * {@code PromotablePiece}, a chick will have ability
 * to change the way of their movement like a dog.
 * <p>
 * For example, if a player moves a chick into the farthest
 * two rows from them (the two rows closest to their opponent)
 * then it promotes.
 * <p>
 * In a game, a chick can only go one space forward, except when promoted.
 *
 * @author Yoochul Kim
 */
public class Chick extends PromotablePiece {
    /**
     * Constructor to create {@code Chick} with
     * given {@code Player} and {@code Square}.
     *
     * @param owner the {@code Player} will be set on this {@code Chick}.
     * @param square the {@code Square} will be set on this {@code Chick}.
     */
    public Chick(Player owner, Square square) {
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
        ArrayList<Square> legalMovesFromSuper = super.getLegalMoves();

        if (isPromoted) {
            return Dog.getLegalMoves(getSquare(), legalMovesFromSuper, getOwner());
        }

        int row = getSquare().getRow();
        int col = getSquare().getCol();

        int[] front;

        if (getOwner().getSide() == Game.Side.UPPER) {
            front = new int[]{row + 1, col};
        } else { // Game.Side.LOWER
            front = new int[]{row - 1, col};
        }

        ArrayList<Square> legalMoves = new ArrayList<>();

        for (Square square : legalMovesFromSuper) {
            if (square.getRow() == front[0] && square.getCol() == front[1]) {
                legalMoves.add(square);
            }
        }

        return legalMoves;
    }
}

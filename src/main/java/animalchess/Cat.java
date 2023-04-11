package animalchess;

import java.util.ArrayList;

/**
 * This class implements a cat piece. By extending
 * {@code PromotablePiece}, a cat will have ability
 * to change the way of their movement like a dog.
 * <p>
 * For example, if a player moves a cat into the farthest
 * two rows from them (the two rows closest to their opponent)
 * then it promotes.
 * <p>
 * In a game, cats can move all the way except to the sides and
 * directly behind them (the move to two diagonals behind it is ok).
 *
 * @author Yoochul Kim
 * @see Piece
 * @see PromotablePiece
 * @see AnimalMoveCalculator
 */
public class Cat extends PromotablePiece {
    /**
     * Constructor to create {@code Cat} with
     * given {@code Player} and {@code Square}.
     *
     * @param owner the {@code Player} will be set on this {@code Cat}.
     * @param square the {@code Square} will be set on this {@code Cat}.
     */
    public Cat(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * Get the array of {@code Square} which this cat can move to.
     * If this cat is promoted, then move like {@code Dog}.
     *
     * @return the array of {@code Square} which this cat can move to.
     */
    @Override
    public ArrayList<Square> getLegalMoves() {
        ArrayList<Square> legalMovesFromSuper = super.getLegalMoves();

        if (isPromoted) {
            return Dog.getLegalMoves(getSquare(), legalMovesFromSuper, getOwner());
        }

        int row = getSquare().getRow();
        int col = getSquare().getCol();

        int[] back;
        int[] left;
        int[] right;

        if (getOwner().getSide() == Game.Side.UPPER) {
            back = new int[]{row - 1, col};
            left = new int[]{row, col + 1};
            right = new int[]{row, col - 1};
        } else { // Game.Side.LOWER
            back = new int[]{row + 1, col};
            left = new int[]{row, col - 1};
            right = new int[]{row, col + 1};
        }

        ArrayList<Square> legalMoves = new ArrayList<>();

        for (Square square : legalMovesFromSuper) {
            if (!(square.getRow() == back[0] && square.getCol() == back[1])
                    && !(square.getRow() == left[0] && square.getCol() == left[1])
                    && !(square.getRow() == right[0] && square.getCol() == right[1])) {
                legalMoves.add(square);
            }
        }

        return legalMoves;
    }
}

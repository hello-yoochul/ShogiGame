package animalchess;

import java.util.ArrayList;

/**
 * This class implements a dog piece, and extends {@code Piece}.
 * <p>
 * The dog can make any movement (only one step) except
 * for the two diagonals behind it.
 *
 * @author Yoochul Kim
 */
public class Dog extends Piece {
    /**
     * Constructor to create {@code Dog} with
     * given {@code Player} and {@code Square}.
     *
     * @param owner the {@code Player} will be set on this {@code Dog}.
     * @param square the {@code Square} will be set on this {@code Dog}.
     */
    public Dog(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * Get the array of {@code Square} which this dog can move to.
     *
     * @return the array of {@code Square} which this dog can move to.
     */
    @Override
    public ArrayList<Square> getLegalMoves() {
        ArrayList<Square> legalMovesFromSuper = super.getLegalMoves();
        return Dog.getLegalMoves(getSquare(), legalMovesFromSuper, getOwner());
    }

    public static ArrayList<Square> getLegalMoves(Square toSquare, ArrayList<Square> legalMovesFromSuper, Player player){
        ArrayList<Square> legalMoves = new ArrayList<>();
        int row = toSquare.getRow();
        int col = toSquare.getCol();

        // Dog can't move to 2 diagonal directions behind of it.
        int[] leftBack;
        int[] rightBack;

        // Depending on a player, a calculation of the movement varies
        // player 0's animals is heading downward and
        // player 1's animals is heading upward.
        if (player.getSide() == Game.Side.UPPER ) {
            leftBack = new int[]{row - 1, col - 1};
            rightBack = new int[]{row - 1, col + 1};
        } else { // Game.Side.Lower
            leftBack = new int[]{row + 1, col - 1};
            rightBack = new int[]{row + 1, col + 1};
        }

        for (Square square : legalMovesFromSuper) {
            int rowTemp = square.getRow();
            int colTemp = square.getCol();

            if (!(leftBack[0] == rowTemp && leftBack[1] == colTemp)
                    && !(rightBack[0] == rowTemp && rightBack[1] == colTemp)) {
                legalMoves.add(square);
            }
        }

        return legalMoves;
    }
}

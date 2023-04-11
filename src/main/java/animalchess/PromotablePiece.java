package animalchess;

import java.util.ArrayList;

/**
 * This class implements PromotablePiece.
 * <p>
 * This class was created by generalizing the behaviors and
 * states of promotable animals: {@code Cat} and {@code Chick}.
 *
 * @author Yoochul Kim
 */
public abstract class PromotablePiece extends Piece {
    // Once the piece go in the farthest two ranks from the player
    // it will has the state of true.
    protected boolean isPromoted;

    // player 0's promotion row
    private static final int P0_PROMOTE_RANK = 4;

    // player 1's promotion row
    private static final int P1_PROMOTE_RANK = 1;

    private Dog dog;

    /**
     * Constructor to create {@code PromotablePiece} with
     * given {@code Player} and {@code Square}.
     *
     * @param owner the {@code Player} will own this {@code PromotablePiece}.
     * @param square the {@code Square} which this {@code PromotablePiece} is laid on.
     */
    PromotablePiece(Player owner, Square square) {
        super(owner, square);
        isPromoted = false;
    }

    /**
     * Get the state of the {@code isPromoted}.
     *
     * @return true if the piece extending {@code PromotablePiece} is promoted
     */
    public boolean getIsPromoted() {
        return isPromoted;
    }

    /**
     * Promotes the piece which extends {@code PromotablePiece}.
     */
    public void promote() {
        isPromoted = true;
    }

    /**
     * Unpromotes the piece which extends {@code PromotablePiece}.
     */
    public void unPromote() {
        isPromoted = false;
    }


    /**
     * Get all legal moves of the animal which has
     * extended this {@code PromotablePiece}.
     *
     * @return the array of {@code Square} which the piece, which has
     * extended this {@code PromotablePiece}, can be laid on.
     */
//    @Override
//    public ArrayList<Square> getLegalMoves() {
//        ArrayList<Square> legalMovesFromSuper = super.getLegalMoves();
//        return isPromoted ? Dog.getLegalMoves(getSquare(), legalMovesFromSuper, getOwner()) : legalMovesFromSuper;
//    }

    /**
     * Move the piece extending this {@code PromotablePiece} to the {@code Square}.
     *
     * @param toSquare The {@code Square} which the piece extending this {@code PromotablePiece} will move to.
     */
    @Override
    public void move(Square toSquare) {
        // Calculates that this piece can go by considering the
        // opponent's pieces and own pieces. (refer internal for more information)
        // In short, brings about which square can be legally moved.
        ArrayList<Square> legalMovesSquareArray = getLegalMoves();

        // Should consider if the square passed as a parameter
        // is included in the legal movement.
        boolean isLegalMove = legalMovesSquareArray.contains(toSquare);

        if (isLegalMove) {
            // If there is a piece where you want to go,
            // it should be owned by the opponent since all squares,
            // where your pieces are located, were filtered out of
            // getLegalMoves() above.
            if (!toSquare.isEmptySquare()) {
                // Captures the piece located on the square where you want to move to.
                toSquare.getPiece().beCaptured(getOwner());

                // As this piece is leaving, remove the piece information from
                // the square where the piece is currently placed.
                getSquare().removePiece();

                // Set the square to this piece and
                // set this piece to the square. (bi-directional)
                setSquare(toSquare);
                toSquare.placePiece(this);
            } else {
                // As this piece is leaving, remove the piece information from
                // the square where the piece is currently placed.
                getSquare().removePiece();

                // Set the square to this piece and
                // set this piece to the square. (bi-directional)
                setSquare(toSquare);
                toSquare.placePiece(this);
            }

            // When entering a certain rank, promote the piece.
            // if it is already promoted, ignore this process.
            if (!isPromoted) {
                if (getOwner().getSide().equals(Game.Side.UPPER) && toSquare.getRow() >= P0_PROMOTE_RANK) {
                    promote();
                } else if (getOwner().getSide().equals(Game.Side.LOWER) && toSquare.getRow() <= P1_PROMOTE_RANK) {
                    promote();
                }
            }
        }
    }

    /**
     * Be captured by the {@code Player} which is object of the capturer.
     *
     * @param capturer The {@code Player} which will catch the piece extending this {@code PromotablePiece}.
     */
    @Override
    public void beCaptured(Player capturer) {
        // Once the piece, which extends PromotablePiece, is
        // caught, it should be unpromoted.
        if (isPromoted) {
            unPromote();
        }
        // 1 remove the piece from the square,
        // 2 remove square information from piece. (bi-directional)
        // 3 change owner information to piece.
        // 4 add the piece caught in the capturer's hand.
        getSquare().removePiece();
        removeSqare();
        setOwner(capturer);
        capturer.addPieceToHand(this);
    }
}

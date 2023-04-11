package animalchess;

import java.util.ArrayList;

import static animalchess.Game.*;

/**
 * This abstract class was made by generalizing various
 * animals. i.e, the common behavior and state of the
 * animals are consisted in this class.
 * <p>
 * The classes extending this class are {@code Lion},
 * {@code Dog}, and {@code PromotablePiece} which is
 *  extended by {@code Chick} and {@code Cat}.
 * <p>
 * Each animal should override {@code getLegalMoves()} to
 * find their legal movement.
 *
 * @author Yoochul Kim
 */
public abstract class Piece {
    // Except when in the player's hand,
    // this piece will be laid on certain square.
    private Square square;

    // Piece owner will always exist.
    private Player owner;

    /**
     * Constructor to create {@code Piece} with
     * given {@code Player} and {@code Square}.
     *
     * @param owner  the {@code Player} will own this {@code Piece}.
     * @param square the {@code Square} which this {@code Piece} is laid on.
     */
    Piece(Player owner, Square square) {
        // Throws an exception if a player put their
        // piece to the square occupied by another piece.
        if (!square.isEmptySquare()) {
            throw new IllegalArgumentException("player put their piece to the square occupied by another piece");
        }

        this.owner = owner;

        // Set the passed square to this piece and
        // set this piece to the square. (bi-directional)
        this.square = square;
        square.placePiece(this);
    }

    /**
     * Move this piece to the {@code Square}.
     *
     * @param toSquare The {@code Square} which this piece will move to.
     * @throws IllegalArgumentException if player move a piece wrongly in
     *                                  the official game.
     */
    public void move(Square toSquare) {
        // When a square is created without a game object (= consider it as a test),
        // allows all movement that cannot be taken.
        // e.g. The dog of [1,2] must successfully move to [1,0].
        if (getSquare().getGame() == null) {
            // As Piece has Square and Square has Piece in their variable.
            // Thus, should be placed on both sides.
            setSquare(toSquare);
            toSquare.placePiece(this);
        } else {
            // Calculates that this piece can go by considering the
            // opponent's pieces and own pieces.
            // In short, brings about which square can be legally moved.
            // (refer internal for more information)
            ArrayList<Square> legalMovesFromSuper = getLegalMoves();

            // Should consider if the square passed as a parameter
            // is included in the legal movement.
            boolean isLegalMove = legalMovesFromSuper.contains(toSquare);

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
            } else {
                throw new IllegalArgumentException("the piece cannot move to the square passed as a parameter");
            }
        }

    }

    /**
     * Be captured by the {@code Player} which is object of
     * the capturer implementing {@code Piece}.
     * <p>
     * Only dogs use this behavior, chick and cat will use the
     * beCaptured of PromotablePiece. The lion will use its own
     * beCaptured behavior as the game is over if caught.
     *
     * @param capturer the {@code Player} which will
     *                 catch the piece extending this {@code Piece}.
     */
    public void beCaptured(Player capturer) {
        // 1 remove the piece from the square,
        // 2 remove square information from piece. (bi-directional)
        // 3 change owner information to piece.
        // 4 add the piece caught in the capturer's hand.
        getSquare().removePiece();
        removeSqare();
        setOwner(capturer);
        capturer.addPieceToHand(this);
    }

    /**
     * Remove the square which is set in this piece.
     */
    public void removeSqare() {
        square = null;
    }

    /**
     * Set the owner in this piece.
     *
     * @param owner the {@code Player} which will
     *              be set on this {@code owner}.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Set the square in this piece.
     *
     * @param square the {@code Square} which will be set on this piece.
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * Get all legal moves of the animal.
     *
     * All pieces require a common logic.
     * Pieces placed on the border of the chessboard cannot be moved outside, and
     * pieces cannot be moved to the place where one's own pieces are placed,
     * so this method will return available moves correspondingly.
     *
     * @return the array of {@code Square} which this piece can be laid on.
     */
    public ArrayList<Square> getLegalMoves(){
        int row = square.getRow();
        int col = square.getCol();

        ArrayList<Square> legalMovesWithoutAnimalSpecificMoveConsideration = new ArrayList<>();
        // Check three lines for rows (above, the same row, and below)
        for (int i = row - 1; i <= row + 1; i++) {
            // when the row is outbound
            if(i < MIN_ROW || MAX_ROW < i) continue;
            // Check three lines for columns (left, the same column, and right)
            for (int j = col - 1; j <= col + 1; j++) {
                // when the column is outbound
                if(j < MIN_COL || MAX_COL < j) continue;
                // when the same owner's piece is laid, ignore it
                if (square.getGame().getSquare(i, j).getPiece() != null
                        && square.getGame().getSquare(i, j).getPiece().getOwner().equals(this.getOwner())) continue;
                legalMovesWithoutAnimalSpecificMoveConsideration.add(square.getGame().getSquare(i, j));
            }
        }

        return legalMovesWithoutAnimalSpecificMoveConsideration;
    }

    /**
     * Get the {@code Square} this piece is on.
     *
     * @return the {@code Square} this piece is on
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Get the owner {@code Player} of this {@code Piece}.
     *
     * @return the {@code Player} owns this piece.
     */
    public Player getOwner() {
        return owner;
    }
}

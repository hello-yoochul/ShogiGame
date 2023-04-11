package animalchess;

/**
 * This square implements one of squares in a game.
 * In a game, 30 number of squares will be created.
 * <p>
 * It will mainly manage piece on it and have a role
 * of bridge connecting to the game object.
 *
 * @author Yoochul Kim
 */
public class Square {
    private Game game;
    private final int row;
    private final int col;
    private Piece piece;

    /**
     * Constructor to create a Square with given row and column.
     *
     * @param row the {@code int} will be set on this {@code Square}.
     * @param col the {@code int} will be set on this {@code Square}.
     */
    public Square(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Constructor to create a Square with given {@code Game}, row and column.
     *
     * @param game the {@code Game} which this {@code Square} belong to.
     * @param row  the {@code int} will be set on this {@code Square}.
     * @param col  the {@code int} will be set on this {@code Square}.
     */
    public Square(Game game, int row, int col) {
        this.game = game;
        this.row = row;
        this.col = col;
    }

    /**
     * Places the {@code Piece} on this square.
     *
     * @param piece the {@code Piece} will be laid on this square.
     * @throws IllegalArgumentException if a player tries to put their piece on
     *                                  the square occupied by their pieces.
     */
    public void placePiece(Piece piece) {
        // If a square is created that does not belong to any game,
        // consider it as a test so all wanting movements are allowed.
        // Same owner, however, cannot put any piece on the
        // square occupied by their pieces. If they tried to put,
        // it will throw IllegalArgumentException.
        if (getGame() != null) {
            this.piece = piece;
            piece.setSquare(this);
        } else {
            if (getPiece() == null) {
                this.piece = piece;
                piece.setSquare(this);
            } else if (piece.getOwner().equals(getPieceOwner())) {
                throw new IllegalArgumentException("cannot put a piece on the square occupied by their pieces");
            } else {
                getPiece().beCaptured(piece.getOwner());
            }
        }
    }

    /**
     * Removes the piece laid on this square.
     */
    public void removePiece() {
        this.piece = null;
    }

    /**
     * Get the {@code Game} which this square belong to.
     *
     * @return {@code Game} which this square belong to.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Get the {@code Piece} on this square.
     *
     * @return {@code Piece} currently on this square.
     */
    public Piece getPiece() {
        if (piece == null) {
            return null;
        }
        return piece;
    }

    /**
     * Get the owner of the piece set on this square.
     *
     * @return {@code Player} who owns the piece set on this square.
     */
    public Player getPieceOwner() {
        if (piece == null) {
            return null;
        }
        return piece.getOwner();
    }

    /**
     * Returns the presence of piece in this square.
     *
     * @return true if the piece in this square is null
     */
    public boolean isEmptySquare() {
        return getPiece() == null;
    }

    /**
     * Get the row on which this square is laid in the game.
     *
     * @return the row of which this square is laid in the game.
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column on which this square is laid in the game.
     *
     * @return the column on which this square is laid in the game.
     */
    public int getCol() {
        return col;
    }
}

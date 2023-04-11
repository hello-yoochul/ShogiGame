package animalchess.Test05_PieceTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

import java.util.ArrayList;

public class CatTest {

    private Player p0;
    private Player p1;
    private Game game;
    
    @Before
    public void setup() {
        p0 = new Player("Michael", 0);
        p1 = new Player("Ozgur", 1);
        game = new Game(p0, p1);
    }

    @Test
    public void testPromote() {
        Cat c = new Cat(p1, game.getSquare(1, 1));
        assertFalse(c.getIsPromoted());
        c.promote();
        assertTrue(c.getIsPromoted());
    }

    @Test
    public void testMoveToFinalRank() {
        Cat c = new Cat(p1, game.getSquare(1, 1));
        assertFalse(c.getIsPromoted());
        c.move(game.getSquare(0, 1));  // move to final rank (and promote)
        assertTrue(c.getIsPromoted());
    }

    @Test
    public void testMoveToPenultimateRank() {
        Cat c = new Cat(p1, game.getSquare(2, 4));
        assertFalse(c.getIsPromoted());
        assertEquals(2, c.getLegalMoves().size());
        c.move(game.getSquare(1, 4));  // move to final rank (and promote)
        assertTrue(c.getIsPromoted());
        assertEquals(4, c.getLegalMoves().size());
    }

    @Test
    public void testGetLegalMovesTestEdge() {
        // Add an extra cat to the board, near the opponent's pieces
        Cat c = new Cat(p1, game.getSquare(1, 4));
        // From this position it can make three moves (up, up-left, down-left)
        ArrayList<Square> moves = c.getLegalMoves();
        assertEquals(3, moves.size());
        assertTrue(moves.contains(game.getSquare(0, 3)));
        assertTrue(moves.contains(game.getSquare(0, 4)));
        assertTrue(moves.contains(game.getSquare(2, 3)));
    }

    @Test
    public void testGetLegalMovesPromoted() {
        // Add an extra cat to the board, near the opponent's pieces
        PromotablePiece c = new Cat(p1, game.getSquare(1, 4));
        // Promote it so it has extra movement options
        c.promote();
        // It can now make four moves (but can't move right off the board)
        ArrayList<Square> moves = c.getLegalMoves();
        assertEquals(4, moves.size());
    }

    @Test
    public void testGetLegalMovesExisting() {
        // Find P1's left cat - can only move forward or forward-right
        Piece c = game.getSquare(5, 0).getPiece();
        assertEquals(1, c.getOwner().getPlayerNumber());
        assertTrue(c instanceof Cat);
        ArrayList<Square> moves = c.getLegalMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(game.getSquare(4, 0)));
        assertTrue(moves.contains(game.getSquare(4, 1)));
    }

    @Test
    public void testGetLegalMovesPromoteExisting() {
        // Find P1's right cat - can only move forward or forward-left
        Cat c = (Cat) game.getSquare(5, 4).getPiece();
        assertEquals(1, c.getOwner().getPlayerNumber());
        assertTrue(c instanceof Cat);
        ArrayList<Square> moves = c.getLegalMoves();
        assertEquals(2, moves.size());
        // Promote it
        c.promote();
        // Can still only move 2 directions
        moves = c.getLegalMoves();
        assertEquals(2, moves.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLegalMovesTestFail() {
        // Add an extra cat to the board in an occupied space
        Cat c = new Cat(p0, game.getSquare(2, 1));
        fail("the last line should have thrown an exception");
    }
}

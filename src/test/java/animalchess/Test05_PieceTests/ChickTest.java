package animalchess.Test05_PieceTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

import java.util.ArrayList;

public class ChickTest {

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
        Chick ch = new Chick(p1, game.getSquare(1, 2));
        assertFalse(ch.getIsPromoted());
        ch.promote();
        assertTrue(ch.getIsPromoted());
    }

    @Test
    public void testMoveToPromote() {
        Chick ch = new Chick(p1, game.getSquare(1, 2));
        assertFalse(ch.getIsPromoted());
        ch.move(game.getSquare(0, 2));  // move to final rank (and promote)
        assertTrue(ch.getIsPromoted());
    }

    @Test
    public void testGetLegalMovesTestEdge() {
        // Add an extra chick to the board, near the opponent's pieces
        Chick ch = new Chick(p1, game.getSquare(1, 2));
        // From this position it can make only one move
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(1, moves.size());
        assertEquals(game.getSquare(0, 2), moves.get(0));
    }

    @Test
    public void testGetLegalMovesPromoted() {
        // Add an extra chick to the board, near the opponent's pieces
        Chick ch = new Chick(p1, game.getSquare(1, 4));
        // Promote it so it has extra movement options
        ch.promote();
        // It can now make four moves (but can't move right off the board)
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(4, moves.size());
    }

    @Test
    public void testGetLegalMovesExisting() {
        // Find P1's left chick - can only move forward
        Piece ch = game.getSquare(3, 1).getPiece();
        assertEquals(1, ch.getOwner().getPlayerNumber());
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(1, moves.size());
        assertEquals(game.getSquare(2, 1), moves.get(0));
    }

    @Test
    public void testGetLegalMovesPromoteExisting() {
        // Find P1's left chick - can only move forward
        Chick ch = (Chick) game.getSquare(3, 1).getPiece();
        assertEquals(1, ch.getOwner().getPlayerNumber());
        // Promote it
        ch.promote();
        // Can now move 5 directions (but not right onto P1's other chick)
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(5, moves.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLegalMovesTestFail() {
        // Add an extra chick to the board in an occupied space
        Chick ch = new Chick(p0, game.getSquare(2, 1));
        fail("the last line should have thrown an exception");
    }
}

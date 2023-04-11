package animalchess.Test05_PieceTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

import java.util.ArrayList;

public class LionTest {

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
    public void testGetLegalMovesTestEdge() {
        // Add an extra lion to the board, to the left of its own chicks
        Lion li = new Lion(p1, game.getSquare(3, 0));
        // From this position it can make four moves
        // (it can't move to its own chick, or off the board)
        ArrayList<Square> moves = li.getLegalMoves();
        assertEquals(4, moves.size());
        assertTrue(moves.contains(game.getSquare(2, 0)));
        assertTrue(moves.contains(game.getSquare(2, 1)));
        assertTrue(moves.contains(game.getSquare(4, 0)));
        assertTrue(moves.contains(game.getSquare(4, 1)));
    }

    @Test
    public void testGetLegalMovesExisting() {
        // Find P1's lion (bottom-middle)
        // (can move to any of the three squares directly in front of it)
        Piece li = game.getSquare(5, 2).getPiece();
        assertTrue(li instanceof Lion);
        ArrayList<Square> moves = li.getLegalMoves();
        assertEquals(3, moves.size());
        assertTrue(moves.contains(game.getSquare(4, 1)));
        assertTrue(moves.contains(game.getSquare(4, 2)));
        assertTrue(moves.contains(game.getSquare(4, 3)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLegalMovesTestFail() {
        // Add an extra lion to the board in an occupied space
        Lion li = new Lion(p0, game.getSquare(2, 1));
        fail("the last line should have thrown an exception");
    }

    @Test
    public void testBeCaptured() {
        // Find P0's lion (top-middle)
        Piece li = game.getSquare(0, 2).getPiece();
        assertFalse(p1.hasWon());

        // P1 captures P0's lion and wins the game
        li.beCaptured(p1);
        assertTrue(p1.hasWon());
    }
}

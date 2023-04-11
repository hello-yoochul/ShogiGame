package animalchess.Test05_PieceTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

import java.util.ArrayList;

public class DogTest {

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
        // Add an extra dog to the board
        Dog d = new Dog(p1, game.getSquare(4, 4));
        // From this position it can only make two moves - up and left
        ArrayList<Square> moves = d.getLegalMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(game.getSquare(3, 4)));
        assertTrue(moves.contains(game.getSquare(4, 3)));
    }

    @Test
    public void testGetLegalMovesStart() {
        // Find P1's dog (bottom-left) - three legal moves
        Piece d = game.getSquare(5, 1).getPiece();
        assertTrue(d instanceof Dog);
        assertEquals(3, d.getLegalMoves().size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetLegalMovesTestFail() {
        // Add an extra dog to the board
        Dog d = new Dog(p0, game.getSquare(0, 3));
    }
}

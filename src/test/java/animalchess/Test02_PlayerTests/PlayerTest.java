package animalchess.Test02_PlayerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

import java.util.ArrayList;

public class PlayerTest {

    private Player michael;
    private Player oz;

    private Piece gold;
    private Square square0;
    private Square square1;

    @Before
    public void setup() {
        michael = new Player("Michael", 0);
        oz = new Player("Ozgur", 1);

        // This stuff should work even without a Game object!
        square0 = new Square(null, 0, 0);
        square1 = new Square(null, 0, 2);
        gold = new Dog(michael, square1);
    }

    @Test
    public void testExists() {
        assertNotNull(michael);
    }

    @Test
    public void testGetName() {
        assertEquals("Michael", michael.getName());
        assertEquals("Ozgur", oz.getName());
    }

    @Test
    public void testGetPlayerNumber() {
        assertEquals(0, michael.getPlayerNumber());
        assertEquals(1, oz.getPlayerNumber());
    }

    @Test
    public void testHasWonFalse() {
        assertFalse(michael.hasWon());
    }

    @Test
    public void testHasWonTrue() {
        michael.winGame();
        assertTrue(michael.hasWon());
    }

    @Test
    public void testGetHandEmpty() {
        ArrayList<Piece> hand = michael.getHand();
        assertEquals(0, hand.size());
    }

    @Test
    public void testAddPieceToHand() {
        michael.addPieceToHand(gold);
        ArrayList<Piece> hand = michael.getHand();
        assertEquals(1, hand.size());
        assertEquals(gold, hand.get(0));
    }

    @Test
    public void testDropPiece() {
        michael.addPieceToHand(gold);
        michael.dropPiece(gold, square1);
        ArrayList<Piece> hand = michael.getHand();
        assertEquals(0, hand.size());
        assertEquals(gold, square1.getPiece());
        assertEquals(square1, gold.getSquare());
    }

}

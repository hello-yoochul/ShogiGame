package animalchess.Test03_SquareTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

public class SquareTest {

    private Square square;
    private Square square2;
    private Piece gold;
    private Piece silver;
    private Player michael;

    @Before
    public void setup() {
        square = new Square(null, 1, 2);  // no game (null)
        square2 = new Square(null, 3, 1);
        michael = new Player("Michael", 0);
        gold = new Dog(michael, square);
        silver = new Cat(michael, square2);
    }

    @Test
    public void testExists() {
        assertNotNull(square);
    }

    @Test
    public void testGetRow() {
        assertEquals(1, square.getRow());
    }

    @Test
    public void testGetCol() {
        assertEquals(2, square.getCol());
    }

    @Test
    public void testGetGameNull() {
        assertNull(square.getGame());  // this one was created with a null game
    }

    @Test
    public void testGetPiece() {
        assertEquals(gold, square.getPiece());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacePieceFail() {
        square.placePiece(silver);  // place silver on square occupied by gold
        fail("the last line should have thrown an exception");
    }

    @Test
    public void testRemovePiece() {
        square.removePiece();
        assertNull(square.getPiece());
    }

    @Test
    public void testPlacePieceSuccess() {
        square.removePiece();
        square.placePiece(silver);
        assertEquals(silver, square.getPiece());
    }
}

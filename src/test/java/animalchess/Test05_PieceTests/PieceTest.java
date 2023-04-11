package animalchess.Test05_PieceTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

public class PieceTest {
    
    private Piece gold;
    private Piece silver;

    private Square square1;
    private Square square2;
    private Square square3;
    private Player michael;
    private Player oz;

    @Before
    public void setup() {
        square1 = new Square(null, 1, 2);
        square2 = new Square(null, 1, 0);
        square3 = new Square(null, 2, 1);
        michael = new Player("Michael", 0);
        oz = new Player("Ozgur", 1);
        gold = new Dog(michael, square1);
        silver = new Cat(oz, square3);
    }

    @Test
    public void testMoveAndCapture() {
        assertEquals(square3, silver.getSquare());
        assertEquals(oz, silver.getOwner());
        gold.move(square3);  // gold takes silver by moving to its square
        assertEquals(square3, gold.getSquare());
        assertNull(silver.getSquare());
        assertEquals(michael, silver.getOwner());
        assertNotEquals(oz, silver.getOwner());
    }

    @Test
    public void testGetSquare() {
        assertEquals(square1, gold.getSquare());
    }

    @Test
    public void testGetOwner() {
        assertEquals(michael, gold.getOwner());
    }

    @Test
    public void testBeCaptured() {
        gold.beCaptured(oz);
        assertEquals(oz, gold.getOwner());
        assertNotEquals(michael, gold.getOwner());
    }

    @Test
    public void testMove() {
        gold.move(square2);
        assertEquals(square2, gold.getSquare());
        assertNotEquals(square1, gold.getSquare());
    }

    /*@Test
    public void testMoveAndCapture() {
        assertEquals(square3, silver.getSquare());
        assertEquals(oz, silver.getOwner());
        gold.move(square3);  // gold takes silver by moving to its square
        assertEquals(square3, gold.getSquare());
        assertNull(silver.getSquare());
        assertEquals(michael, silver.getOwner());
        assertNotEquals(oz, silver.getOwner());
    }*/

}

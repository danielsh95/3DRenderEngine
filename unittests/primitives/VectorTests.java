package primitives;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

public class VectorTests {

    @Test
    public void subtract() {
        Vector v1 = new Vector(4,2,8);
        Vector v2 = new Vector(1,-2,-3);
        Vector result = v1.subtract(v2);

        assertEquals(new Vector(3,4,11), result);
    }

    @Test
    public void add() {
        Vector v1 = new Vector(4,2,8);
        Vector v2 = new Vector(1,-2,-3);
        Vector result = v1.add(v2);

        assertEquals(new Vector(5,0,5), result);

    }

    @Test
    public void scale() {
        Vector v = new Vector(1,2,3);
        Vector result = v.scale(2);

        assertEquals(new Vector(2,4,6), result);

    }

    @Test
    public void dotProduct() {
        Vector v1 = new Vector(4,5.5,6.2);
        Vector v2 = new Vector(7.65,4.87,6.26);
        double result = v1.dotProduct(v2);
        assertEquals(96.197,result,0.00001);
    }

    @Test
    public void crossProduct() {

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }

    @Test
    public void lengthSquared() {
        Vector v1 = new Vector(5,8,4);
        double result = v1.lengthSquared();
        assertEquals(105,result,0.0001);
    }

    @Test
    public void length() {

        Vector v1 = new Vector(6.5,7.5,4.9);
        double result = v1.length();
        assertEquals(11.06842,result,0.0001);
    }

    @Test
    public void normalize() {
        Vector v = new Vector(1,2,3);
        Vector result = v.normalize();

        assertEquals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), result);
        assertEquals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), v);
    }

    @Test
    public void normalized() {
        Vector v = new Vector(1,2,3);
        Vector result = v.normalize();

        assertEquals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), result);
    }
}
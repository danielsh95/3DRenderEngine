package primitives;

import org.junit.Test;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * This unitTest for Vector class
 **/
public class VectorTests {
    private Vector v1 = new Vector(1, 2, 3);
    private Vector v2 = new Vector(-2, -4, -6);
    private Vector v3 = new Vector(0, 3, -2);

    /**
     * Test to subtract between twe vectors
     **/
    @Test
    public void subtract() {
        Point3D p1 = new Point3D(1, 2, 3);
        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
            fail("ERROR: Point - Point does not work correctly");
    }

    /**
     * Test to add between twe points
     **/
    @Test
    public void add() {
        Point3D p1 = new Point3D(1, 2, 3);
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            fail("ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test to get vector multiplier scalar
     **/
    @Test
    public void scale() {
        Vector v = new Vector(1, 2, 3);
        Vector result = v.scale(2);

        assertEquals(new Vector(2, 4, 6), result);
    }

    /**
     * Test to dotProduct - Scalar multiplication
     **/
    @Test
    public void dotProduct() {
        if (!isZero(v1.dotProduct(v3)))
            fail("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            fail("ERROR: dotProduct() wrong value");
    }

    /**
     * Test Vector multiplication - Returns a new vector that is perpendicular to the two existing vectors,
     * The test will check that function is working
     **/
    @Test
    public void crossProduct() {
        try { // test zero vector
            v1.crossProduct(v2);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
        Vector vr = v1.crossProduct(v3);
        if (!isZero(vr.length() - v1.length() * v3.length()))
            fail("ERROR: crossProduct() wrong result length");
        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
            fail("ERROR: crossProduct() result is not orthogonal to its operands");
    }

    /**
     * Test to lengthSquared Calculate the vector length squared
     * The test will check that function is working
     **/
    @Test
    public void lengthSquared() {
        Vector v = new Vector(1, 2, 3);
        if (!isZero(v.lengthSquared() - 14))
            fail("ERROR: lengthSquared() wrong value");
    }

    /**
     * Test for check the length of vector
     **/
    @Test
    public void length() {

        if (!isZero(new Vector(0, 3, 4).length() - 5))
            fail("ERROR: length() wrong value");
    }

    /**
     * Test vector normalization vs vector length and cross-product
     **/
    @Test
    public void normalizeAndNormalized() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            fail("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            fail("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        if (u == v)
            fail("ERROR: normalizated() function does not create a new vector");
    }

    /**
     * Test for check the normalize of vector (note: the test will not change the instance of object to normalize)
     **/
    @Test
    public void normalized() {
        Vector v = new Vector(1, 2, 3);
        Vector result = v.normalize();

        assertEquals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), result);
    }

    /**
     * Test for checking ctors is vector (0,0,0)
     **/
    @Test
    public void ctor() {
        try {
            Vector v1 = new Vector(new Coordinate(0), new Coordinate(0), new Coordinate(0));
            fail("vector can't be (0,0,0)");
        } catch (IllegalArgumentException e) {
        }

        try {
            Vector v2 = new Vector(new Vector(0, 0, 0));
            fail("vector can't be (0,0,0)");
        } catch (IllegalArgumentException e) {
        }

        try {
            Vector v3 = new Vector(0, 0, 0);
            fail("vector can't be (0,0,0)");
        } catch (IllegalArgumentException e) {
        }
    }
}
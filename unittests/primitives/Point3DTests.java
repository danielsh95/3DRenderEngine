package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This unitTest for Point3D class
 **/
public class Point3DTests {

    /**
     * Test to subtract between twe points
     **/
    @Test
    public void subtract() {
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(2, 3, 5);
        Vector result = p1.subtract(p2);

        assertEquals(new Vector(-1, -1, -2), result);
    }

    /**
     * Test to add between twe points
     **/
    @Test
    public void add() {
        Point3D p1 = new Point3D(1, 2, 3);
        Vector v2 = new Vector(2, 3, 5);
        Point3D result = p1.add(v2);

        assertEquals(new Point3D(3, 5, 8), result);
    }

    /**
     * Test to distanceSquared to check is distanceSquared will be ok
     **/
    @Test
    public void distanceSquared() {
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(2, 3, 5);
        double result = p1.distanceSquared(p2);

        assertEquals(6, result, 0.00001);
    }

    /**
     * Test to distance to check is root of distanceSquared will be ok
     **/
    @Test
    public void distance() {
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(2, 3, 5);
        double result = p1.distance(p2);

        assertEquals(2.4494897, result, 0.00001);
    }
}
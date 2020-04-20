package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * This unitTest for Plane class
 **/
public class PlaneTests {

    /**
     * Test to method getNormal, the Test will check method of GetNormal of plane
     **/
    @Test
    public void getNormal() {
        Plane p1 = new Plane(new Point3D(1, 2, 4), new Point3D(4, 5, 7), new Point3D(7, 0, 9));
        assertEquals(new Vector(0.6556100681071858, 0.09365858115816941, -0.7492686492653553), p1.getNormal());
    }

    /**
     * Test to method findIntersections of Plane, the tests will check 9 tests, 2 EP, and 7 BVA
     * **/
    @Test
    public void findIntersections() {
        Plane plane = new Plane(new Point3D(1, 0, 0), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is intersection the plane (1 points)
        Ray ray1 = new Ray(new Point3D(0.5, 0, 0), new Vector(0, 0, 1));
        assertEquals("Ray's line is intersection the plane",
                List.of(new Point3D(0.5, 0, 0.5)), plane.findIntersections(ray1));

        // TC02: Ray's line is not intersection the plane (0 points)
        Ray ray2 = new Ray(new Point3D(0.5, 0, 0), new Vector(-1, 0, 0));
        assertNull("Ray's line is not intersection the plane", plane.findIntersections(ray2));

        // =============== Boundary Values Tests ==================

        // TC03: Ray's line included with the plane (0 points)
        Ray ray3 = new Ray(new Point3D(0, 2, 0), new Vector(1, -1, 0));
        assertNull("Ray's line included with the plane", plane.findIntersections(ray3));

        // TC04: Ray's line is parallel the plane (0 points)
        Ray ray4 = new Ray(new Point3D(0, 2, 0), new Vector(2, -2, 0));
        assertNull("Ray's line is parallel the plane", plane.findIntersections(ray4));

        // TC05: Ray's line is orthogonal and starts before the plane (1 points)
        Ray ray5 = new Ray(new Point3D(0.5, 0, 0), new Vector(2, 2, 2));
        assertEquals("Ray's line is orthogonal and starts before the plane",
                List.of(new Point3D(2d / 3, 1d / 6, 1d / 6)), plane.findIntersections(ray5));

        // TC06: Ray's line is orthogonal and starts in the plane (0 points)
        Ray ray6 = new Ray(new Point3D(0, 1, 0), new Vector(1, 1, 1));
        assertNull("Ray's line is orthogonal and starts in the plane", plane.findIntersections(ray6));

        // TC07: Ray's line is orthogonal and starts after the plane (0 points)
        Ray ray7 = new Ray(new Point3D(3, 3, 3), new Vector(1, 1, 1));
        assertNull("Ray's line is orthogonal and starts after the plane", plane.findIntersections(ray7));

        // TC08: Ray's line starts in the plane (0 points)
        Ray ray8 = new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 1));
        assertNull("Ray's line starts in the plane", plane.findIntersections(ray8));

        // TC09: Ray's line starts in point of plane (0 points)
        Ray ray9 = new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 1));
        assertNull("Ray's line starts in point of plane", plane.findIntersections(ray9));
    }
}
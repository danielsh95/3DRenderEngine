package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This unitTest for Triangle class
 **/
public class TriangleTests {
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        Triangle t1 = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        assertEquals("Bad normal to trinagle", new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258),
                t1.getNormal(new Point3D(0, 0, 1)));
    }

    /**
     * Test to method findIntersections of Triangle, the tests will check 6 tests, 3 EP, and 3 BVA
     **/
    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point3D(5, 0, 0), new Point3D(0, 5, 0), new Point3D(0, 0, 5));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is intersection with the Triangle (1 points)
        Ray ray1 = new Ray(new Point3D(1.95, 0, 0), new Vector(0.16, 0.33, 2));
        assertEquals("Ray's line is intersection with the Triangle",
                List.of(new GeoPoint(triangle,new Point3D(2.1459839357429717, 0.4042168674698794, 2.449799196787148))), triangle.findIntersections(ray1));
        // TC02: Ray's line is against edge (0 points)
        Ray ray2 = new Ray(new Point3D(6.76, 4.91, 4.89), new Vector(-2.38, -1.39, -4.02));
        assertNull("Ray's line is against edge", triangle.findIntersections(ray2));

        // TC03: Ray's line is against vertex (0 points)
        Ray ray3 = new Ray(new Point3D(-7.3, -6.14, 0), new Vector(2.73, 1.08, 6.26));
        assertNull("Ray's line is against vertex", triangle.findIntersections(ray3));

        // =============== Boundary Values Tests ==================

        // TC04: Ray's line On edge (0 points)
        Ray ray4 = new Ray(new Point3D(0.82, -2.67, 0), new Vector(1.77, 2.67, 2.41));
        assertNull("Ray's line On edge", triangle.findIntersections(ray4));

        // TC05: Ray's line In vertex (0 points)
        Ray ray5 = new Ray(new Point3D(3.84, -2.4, 0), new Vector(-3.84, 2.4, 5));
        assertNull("Ray's line In vertex", triangle.findIntersections(ray5));

        // TC06: Ray's line On edge's continuation (0 points) d
        Ray ray6 = new Ray(new Point3D(1, -6, 0), new Vector(-1, 3, 8));
        assertNull("Ray's line On edge's continuation", triangle.findIntersections(ray6));
    }
}
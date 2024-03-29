package geometries;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Testing Polygons
 *
 * @author Dan
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(Point3D...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {
        }

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {
        }

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {
        }

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle",
                new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }

    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Polygon polygon = new Polygon(new Point3D(0, 4, 4),
                new Point3D(0, 0, 4), new Point3D(5, 0, 0), new Point3D(5, 4, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is intersection with the Polygon (1 points)
        Ray ray1 = new Ray(new Point3D(-3.53, 2.31, 1.5), new Vector(6.07, -0.02, 0.47));
        assertEquals("Ray's line is intersection with the Polygon",
                List.of(new GeoPoint(polygon, new Point3D(2.5377206158467893, 2.2900075103266992, 1.9698235073225685))), polygon.findIntersections(ray1));

        // TC02: Ray's line is against edge (0 points)
        Ray ray2 = new Ray(new Point3D(-3.53, 2.31, 1.5), new Vector(5.5, 4.24, 0.92));
        assertNull("Ray's line is against edge", polygon.findIntersections(ray2));

        // TC03: Ray's line is against vertex (0 points)
        Ray ray3 = new Ray(new Point3D(-3.53, 2.31, 1.5), new Vector(2.01, 2.92, 3.72));
        assertNull("Ray's line is against vertex", polygon.findIntersections(ray3));

        // =============== Boundary Values Tests ==================

        // TC04: Ray's line On edge (0 points)
        Ray ray4 = new Ray(new Point3D(0, -3, 0), new Vector(2.99, 3, 1.61));
        assertNull("Ray's line On edge", polygon.findIntersections(ray4));

        // TC05: Ray's line In vertex (0 points)
        Ray ray5 = new Ray(new Point3D(0, -3, 0), new Vector(0, 3, 4));
        assertNull("Ray's line In vertex", polygon.findIntersections(ray5));

        // TC06: Ray's line On edge's continuation (0 points) d
        Ray ray6 = new Ray(new Point3D(-4.61, 7.75, 0), new Vector(4.61, -1.75, 4));
        assertNull("Ray's line On edge's continuation", polygon.findIntersections(ray6));
    }
}

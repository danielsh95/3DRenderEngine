package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static org.junit.Assert.*;

/**
 * This unitTest for Sphere class
 **/
public class SphereTests {

    /**
     * Test to method getNormal, the Test will check method of GetNormal of Sphere
     **/
    @Test
    public void getNormal() {
        Sphere sphere = new Sphere(new Point3D(1, 2, 3), 2);
        assertEquals(new Vector(0.5773502691896257, 0.5773502691896257, 0.5773502691896257),
                sphere.getNormal(new Point3D(7, 8, 9)));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0)._point.getX().get() > result.get(1)._point.getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(new GeoPoint(sphere, p1), new GeoPoint(sphere, p2)), result);

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals("Ray starts inside the sphere",
                List.of(new GeoPoint(sphere, new Point3D(1.512857900032008, -0.8534121024000318, 0.09308360678399147))),
                sphere.findIntersections(new Ray(new Point3D(1.16, 0.55, 0.47), new Vector(0.44, -1.75, -0.47))));

        // TC04: Ray starts after the sphere (0 points)
        assertNull("Ray starts after the sphere",
                sphere.findIntersections(new Ray(new Point3D(0.76, -2.04, 1.4), new Vector(-0.33, 0.38, -1.4))));

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray starts at sphere and goes inside",
                List.of(new GeoPoint(sphere, new Point3D(1.4487444794280058, 0.7989955835424046, 0.400292954800132)),
                        new GeoPoint(sphere, new Point3D(0.2748620779490434, -0.1401103376407652, 0.6741988484785566))),
                sphere.findIntersections(new Ray(new Point3D(1.45, 0.8, 0.4),
                        new Vector(-0.3, -0.24, 0.07))));

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray starts at sphere and goes outside",
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 1, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        assertEquals("Ray starts before the sphere", List.of(
                new GeoPoint(sphere, new Point3D(0.982145703583117, 0.9998405993454447, 0.0)),
                new GeoPoint(sphere, new Point3D(1.0178542964168829, -0.9998405993454444, 0.0))),
                sphere.findIntersections(new Ray(new Point3D(0.97, 1.68, 0), new Vector(0.03, -1.68, 0))));

        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray starts at sphere and goes inside",
                List.of(new GeoPoint(sphere, new Point3D(0.6596083242296494, -0.8910252689282708, -0.30034559626795637))),
                sphere.findIntersections(new Ray(new Point3D(1.34, 0.89, 0.3),
                        new Vector(-0.34, -0.89, -0.3))));

        // TC15: Ray starts inside (1 points)
        assertEquals("Ray starts inside",
                List.of(new GeoPoint(sphere, new Point3D(0.7086424693821544, -0.77767118308789, -0.5570801740745696))),
                sphere.findIntersections(new Ray(new Point3D(1.17, 0.43, -0.15), new Vector(-0.34, -0.89, -0.3))));

        // TC16: Ray starts at the center (1 points)
        assertEquals("Ray starts at the center",
                List.of(new GeoPoint(sphere, new Point3D(1.9518605578567332, 0.3065313660894565, 0.0))),
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1.18, 0.38, 0))));

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray starts at sphere and goes outside",
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0.8, 0, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertNull("Ray starts after sphere",
                sphere.findIntersections(new Ray(new Point3D(1.43, 1.12, -0.38), new Vector(0.44, 1.15, -0.38))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull("Ray starts before the tangent point",
                sphere.findIntersections(new Ray(new Point3D(2, 5, 0), new Vector(0, -8, 0))));

        // TC20: Ray starts at the tangent point
        assertNull("Ray starts at the tangent point",
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, -3, 0))));

        // TC21: Ray starts after the tangent point
        assertNull("Ray starts after the tangent point",
                sphere.findIntersections(new Ray(new Point3D(2, -1.12, 0), new Vector(0, -1.88, 0))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull("Ray's line is outside, ray is orthogonal to ray start to sphere's center line",
                sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 0, 4))));
    }
}
package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class GeometriesTests {

    @Test
    public void findIntersections() {
        // =============== Boundary Values Tests ==================

        // TC01: not have geometries
        Geometries geometries1 = new Geometries();
        Ray ray1 = new Ray(new Point3D(-5,-7,0),new Vector(-7,5,0));
        assertNull("not have geometries",geometries1.findIntersections(ray1));

        // TC02: all geometries without intersection
        Sphere sphere1 = new Sphere(new Point3D(4,4,4),5);
        Plane plane1 = new Plane(new Point3D(0,-4,0), new Point3D(0,0,4),new Point3D(0,4,0));
        Triangle triangle1 = new Triangle(new Point3D(1.42,-3.87,0),
                new Point3D(1.61,2.76,0),new Point3D(-0.23,2.62,0));
        Ray ray2 = new Ray(new Point3D(-6.95,-7.16,0),new Vector(-5.12,12.51,0));
        Geometries geometries2 = new Geometries(sphere1,plane1,triangle1);
        assertNull("all geometries without intersection", geometries2.findIntersections(ray2));

        // TC03: just one geometry intersections
        Sphere sphere2 = new Sphere(new Point3D(-3,-5,4),6);
        Plane plane2 = new Plane(new Point3D(12,0,0), new Point3D(0,16.25,0),new Point3D(0,0,14));
        Triangle triangle2 = new Triangle(new Point3D(4,0,0),
                new Point3D(0,0,12),new Point3D(15.95,15,0));
        Ray ray3 = new Ray(new Point3D(11.78,-8.76,1.29),new Vector(4.63,14.29,-1.29));
        Geometries geometries3 = new Geometries(sphere2,plane2,triangle2);
        assertEquals("just one geometry intersections",1, geometries3.findIntersections(ray3).size());

        // TC04: all geometries intersections
        Sphere sphere3 = new Sphere(new Point3D(-6,-9,4),6);
        Plane plane3 = new Plane(new Point3D(3.79,0,0), new Point3D(0,0,5),new Point3D(-0.26,7.17,0));
        Triangle triangle3 = new Triangle(new Point3D(6.85,0,0),
                new Point3D(0,10.15,0),new Point3D(0,0,8));
        Ray ray4 = new Ray(new Point3D(13.08,17.38,0),new Vector(-11.38,-16.44,5.17));
        Geometries geometries4 = new Geometries(sphere3,plane3,triangle3);
        assertEquals("all geometries intersections",4, geometries4.findIntersections(ray4).size());

        // ============ Equivalence Partitions Tests ==============
        // TC05: intersection of some geometries
        Ray ray5 = new Ray(new Point3D(-16.89,12.32,0),new Vector(19.44,-8.5,2.01));
        assertEquals("intersection of some geometries",2, geometries4.findIntersections(ray5).size());
    }
}
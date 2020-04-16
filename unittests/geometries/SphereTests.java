package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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
        assertEquals(new Vector(0.5773502691896257, 0.5773502691896257, 0.5773502691896257), sphere.getNormal(new Point3D(7, 8, 9)));
    }
}
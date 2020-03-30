package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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
}
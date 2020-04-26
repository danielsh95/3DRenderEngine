package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * This unitTest to class Cylinder
 **/
public class CylinderTests {

    /**
     * Test to getNormal method to get normal to Cylinder, and the Test check validation of function
     **/
    @Test
    public void getNormal() {
        //test 1
        Cylinder cylinder = new Cylinder(5, new Ray(new Point3D(1, 2, 3), new Vector(4, 7, 4)), 7);
        Vector n = cylinder.getNormal(new Point3D(4, 9, 6));
        assertEquals(new Vector(-0.5499719409228705, 0.6285393610547086, -0.5499719409228705), n);

        //test
        Cylinder cylinder2 = new Cylinder(6, new Ray(new Point3D(1, 2, 2), new Vector(0, 1, 1)), 8);
        Vector n2 = cylinder2.getNormal(new Point3D(2, 2, 2));
        assertEquals(new Vector(new Point3D(0, 1, 1)).normalized(), n2);
    }
}
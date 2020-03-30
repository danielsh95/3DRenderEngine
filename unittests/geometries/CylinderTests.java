package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/***
 * This unitTest to class Cylinder
 * */
public class CylinderTests {

    /**
     * Test to getNormal method to get normal to Cylinder, and the Test check validation of function
     **/
    @Test
    public void getNormal() {
        Cylinder cylinder = new Cylinder(8, new Ray(new Point3D(2, 5, 2), new Vector(1, 2, 3)), 5);
        Vector n = cylinder.getNormal(new Point3D(0, 5, 7));
        assertEquals(new Vector(-0.711779811670285, -0.45137256349822963, 0.5381749795555811), n);
    }
}
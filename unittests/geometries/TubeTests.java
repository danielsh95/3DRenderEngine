package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static org.junit.Assert.*;

public class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        Tube tube = new Tube(new Ray(new Point3D(2, 5, 2), new Vector(1, 2, 3)), 5);
        Vector n = tube.getNormal(new Point3D(0, 5, 7));
        assertEquals(new Vector(-0.711779811670285, -0.45137256349822963, 0.5381749795555811), n);
    }
}
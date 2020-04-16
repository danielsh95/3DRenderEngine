package geometries;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class TriangleTests {
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        Triangle t1 = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        assertEquals("Bad normal to trinagle", new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258), t1.getNormal(new Point3D(0, 0, 1)));
    }

}
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This Triangle class implement from Polygon to achieve Triangle
 */
public class Triangle extends Polygon {

    /**
     * Constructor for triangle by three points
     *
     * @param point3D_1 point 1 of triangle
     * @param point3D_2 point 2 of triangle
     * @param point3D_3 point 3 of triangle
     */
    public Triangle(Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        super(point3D_1, point3D_2, point3D_3);
    }
}

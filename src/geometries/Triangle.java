package geometries;

import primitives.Point3D;

/**
 * This Triangle class implement from Polygon to achieve Triangle
 */
public class Triangle extends Polygon {


    /**
     * Constructor for triangle by three points
     *
     * @param point3D_1
     * @param point3D_2
     * @param point3D_3
     */
    public Triangle(Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        super(point3D_1, point3D_2, point3D_3);
    }
}

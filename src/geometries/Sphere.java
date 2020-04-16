package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This Sphere class implement from RadialGeometry to achieve sphere
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

    /**
     * GetNormal to Sphere
     *
     * @param point3D
     * @return normal to Sphere
     **/
    @Override
    public Vector getNormal(Point3D point3D) {
        return point3D.subtract(_center).normalize();
    }

    /**
     * This Sphere class implement from RadialGeometry to achieve sphere
     *
     * @param center point center of sphere
     * @param radius radius of sphere
     */
    public Sphere(Point3D center, double radius) {
        super(radius);
        _center = center;
    }

    /**
     * Get center of Sphere
     *
     * @return center of sphere
     */
    public Point3D getCenter() {
        return _center;
    }

    @Override
    public String toString() {
        return " center = " + _center;

    }
}

package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This Sphere class implement from RadialGeometry to achieve sphere
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

    @Override
    public Vector getNormal(Point3D point3D) {

        return new Vector(point3D.subtract(_center)).normalize();
    }

    /**
     * This Sphere class implement from RadialGeometry to achieve sphere
     */
    public Sphere(Point3D center, double radius) {
        super(radius);
        this._center = center;
    }

    /**
     * Get center of Sphere
     */
    public Point3D get_center() {
        return _center;
    }

    @Override
    public String toString() {
        return " center = " + _center;

    }
}

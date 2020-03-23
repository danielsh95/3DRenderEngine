package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    Point3D _center;
    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }
    public Sphere(Point3D center, double radius){
        super(radius);
        this._center = center;
    }
    public Point3D get_center() {
        return _center;
    }

    @Override
    public String toString() {
        return " center = " + _center;

    }
}

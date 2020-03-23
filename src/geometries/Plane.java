package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
    Point3D _p;
    Vector _normal;
    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }

    public Plane(Point3D point3D, Vector normal){
        this._p = point3D;
        this._normal = normal;
    }
    public Plane(Point3D point3D_1, Point3D point3D_2, Point3D point3D_3){
        this._p = point3D_1;
        this._normal = null;
    }

    public Point3D get_p() {
        return _p;
    }

    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return " p = " + _p + ", normal = " + _normal;
    }
}

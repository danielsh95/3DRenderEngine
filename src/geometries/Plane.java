package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This class is used to represent Plane by using shape geometry
 */
public class Plane implements Geometry {
    private Point3D _p;
    private Vector _normal;

    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }


    /**
     * constructor get point and normal for Plane
     */
    public Plane(Point3D point3D, Vector normal) {
        this._p = point3D;
        this._normal = normal;
    }

    /**
     * constructor get three points for shape plane
     */
    public Plane(Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
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

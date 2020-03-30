package geometries;

import com.sun.jdi.VoidValue;
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
        return _normal;
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
        Vector v1 = point3D_1.subtract(point3D_2);
        Vector v2 = point3D_2.subtract(point3D_3);
        Vector n = v1.crossProduct(v2).normalize();
        this._normal = n;
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

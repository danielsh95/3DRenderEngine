package geometries;

import com.sun.jdi.VoidValue;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.List;


/**
 * This class used to represent Plane by using shape geometry
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
     *
     * @param point3D point in the plane
     * @param normal  normal to Plane
     */
    public Plane(Point3D point3D, Vector normal) {
        this._p = point3D;
        this._normal = normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray){
        Vector p0ToP;
        try {
            p0ToP = _p.subtract(ray.getPOO());
        } catch (IllegalArgumentException e) {
            return null; //ray start from point on the plane - no intersections
        }

        double nDotV = _normal.dotProduct(ray.getDirection());
        if (isZero(nDotV)) //ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(p0ToP) / nDotV);

        return t <= 0 ? null : List.of(ray.getPoint(t));
    }

    /**
     * constructor get three points for shape plane
     *
     * @param point3D_1 on the plane
     * @param point3D_2 on the plane
     * @param point3D_3 on the plane
     */
    public Plane(Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        this._p = point3D_1;
        Vector v1 = point3D_1.subtract(point3D_2);
        Vector v2 = point3D_2.subtract(point3D_3);
        Vector n = v1.crossProduct(v2).normalize();
        this._normal = n;
    }

    /**
     * get point of plane
     *
     * @return point on the plane
     **/
    public Point3D getPoint() {
        return _p;
    }

    /**
     * get normal of plane
     *
     * @return normal of plane
     **/
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return " p = " + _p + ", normal = " + _normal;
    }
}

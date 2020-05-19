package geometries;

import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.List;

/**
 * This class used to represent Plane by using shape geometry
 */
public class Plane extends Geometry {
    private Point3D _p;
    private Vector _normal;

    /**
     * constructor get point and normal for Plane
     *
     * @param point3D point in the plane
     * @param normal  normal to Plane
     */
    public Plane(Point3D point3D, Vector normal) {
        this(Color.BLACK, point3D, normal);
    }

    /**
     * constructor get point and normal for Plane and emissionLight
     *
     * @param emissionLight
     * @param point3D       point in the plane
     * @param normal        normal to Plane
     */
    public Plane(Color emissionLight, Point3D point3D, Vector normal) {
        this(new Material(0, 0, 0), emissionLight, point3D, normal);
    }

    /**
     * constructor get point and normal for Plane and emissionLight
     *
     * @param material
     * @param emissionLight
     * @param point3D       point in the plane
     * @param normal        normal to Plane
     */
    public Plane(Material material, Color emissionLight, Point3D point3D, Vector normal) {
        super(emissionLight, material);
        _p = point3D;
        _normal = normal;
    }

    /**
     * constructor get three points for shape plane
     *
     * @param point3D_1 on the plane
     * @param point3D_2 on the plane
     * @param point3D_3 on the plane
     */
    public Plane(Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        this(Color.BLACK, point3D_1, point3D_2, point3D_3);
    }

    /**
     * constructor get three points for shape plane and emissionLight
     *
     * @param emissionLight
     * @param point3D_1     on the plane
     * @param point3D_2     on the plane
     * @param point3D_3     on the plane
     */
    public Plane(Color emissionLight, Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        this(new Material(0, 0, 0), emissionLight, point3D_1, point3D_2, point3D_3);
    }

    /**
     * constructor get three points for shape plane
     *
     * @param material
     * @param point3D_1 on the plane
     * @param point3D_2 on the plane
     * @param point3D_3 on the plane
     */
    public Plane(Material material, Color emissionLight, Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        super(emissionLight, material);
        _p = point3D_1;
        Vector v1 = point3D_1.subtract(point3D_2);
        Vector v2 = point3D_2.subtract(point3D_3);
        Vector n = v1.crossProduct(v2).normalize();
        _normal = n;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Vector vP0ToP;
        try {
            vP0ToP = _p.subtract(ray.getPOO());
        } catch (IllegalArgumentException e) {
            return null; //ray start from point on the plane - no intersections
        }

        double nDotV = _normal.dotProduct(ray.getDirection());
        if (isZero(nDotV)) //ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(vP0ToP) / nDotV);
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
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
    public Vector getNormal(Point3D point3D) {
        return _normal;
    }

    @Override
    public String toString() {
        return " p = " + _p + ", normal = " + _normal;
    }
}

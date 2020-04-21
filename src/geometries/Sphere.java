package geometries;

import primitives.Point3D;
import primitives.Ray;
import static primitives.Util.alignZero;
import primitives.Vector;

import java.util.List;

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

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPOO();
        Vector v = ray.getDirection();
        Vector u;
        double radius = getRadius();
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException e) {
            //p0 start in the center
            return List.of(ray.getPoint(radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquare = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquare = alignZero(radius * radius - dSquare);

        //thSquare cant be minus, and not have intersections - because the ray out of sphere
        if (thSquare <= 0) return null;

        double th = alignZero(Math.sqrt(thSquare));
        if (th == 0) return null;//not have intersections

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;//not have intersections

        //there are two intersections points
        if (t1 > 0 && t2 > 0) return List.of(ray.getPoint(t1), ray.getPoint(t2));
        if (t1 > 0)//one intersection point
            return List.of(ray.getPoint(t1));
        else//one intersection point
            return List.of(ray.getPoint(t2));
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

package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;

import primitives.Vector;

import java.util.List;

/**
 * This Tube class implement from RadialGeometry to achieve Tube
 */
public class Tube extends RadialGeometry {
    protected Ray _axisRay;

    /**
     * GetNormal to Tube
     *
     * @param point3D point in the tube
     * @return normal to tube
     **/
    @Override
    public Vector getNormal(Point3D point3D) {
        Vector p0ToP = point3D.subtract(_axisRay.getPOO());
        Vector v = _axisRay.getDirection();
        double t = p0ToP.dotProduct(v);

        //check if projection is zero
        if (isZero(t))
            return p0ToP.normalize();

        Point3D o = _axisRay.getPOO().add(v.scale(t));
        Vector n = point3D.subtract(o).normalize();

        return n;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector v = ray.getDirection();
        Point3D p = ray.getPOO();
        Vector va = _axisRay.getDirection();
        Point3D Pa = _axisRay.getPOO();
        Vector deltaP = null;// p-pa equal to deltaP.
        Vector temp;// v-(v,va) * va -- auxiliary calculation.
        double a,b,c;
        double t1, t2;
        try {
            temp = v.subtract(va.scale(v.dotProduct(va)));
            a = alignZero(temp.lengthSquared());
        } catch (IllegalArgumentException ex) {
            if (isZero(v.dotProduct(va))) {
                a = alignZero(v.lengthSquared());
                temp = v;
            } else {
                // there is no intersection the ray is parallel or contained in the tube.
                return null;
            }
        }

        //delta
        try {
            deltaP = p.subtract(Pa);
        } catch (IllegalArgumentException ex) {
            // If vectors in the same or opposite direction-there is no points.
            if (v.equals(va) || v.equals(va.scale(-1))) {
                return null;
            }
            b = 0;
        }
        if (deltaP == null)
            b = 0;
        else {
            try {
                b = alignZero(deltaP.subtract(va.scale(deltaP.dotProduct(va))).dotProduct(temp) * 2);
            } catch (IllegalArgumentException ex) {
                b = alignZero(deltaP.dotProduct(temp) * 2);
            }
        }
        if (deltaP == null)
            c = alignZero(-_radius * _radius);
        else {
            try {
                c = alignZero(deltaP.subtract(va.scale(deltaP.dotProduct(va))).lengthSquared() - _radius * _radius);
            } catch (IllegalArgumentException ex) {
                if (isZero(deltaP.dotProduct(va))) {
                    c = alignZero(deltaP.lengthSquared() - _radius * _radius);
                } else {
                    c = alignZero(-_radius * _radius);
                }
            }
        }

        //discriminant
        double discriminant = alignZero(b * b - 4 * a * c);
        // there is no intersection points.
        if (discriminant < 0)
            return null;
        discriminant = alignZero(Math.sqrt(discriminant));

        //if start point is inside the tube
        if (isZero(discriminant)) {
            // if the discriminant is zero it must be that the start point is inside the tube.
            t1 = alignZero(-b / (2d * a));
            // if the start point of the ray is outside the tube,can't be solution.
            if (p.distance(Pa) > _radius)
                return null;
            return t1 > 0 ? List.of(ray.getPoint(t1)) : null;
        }

        t1 = alignZero(-b + discriminant) / (2d * a);
        t2 = alignZero(-b - discriminant) / (2d * a);

        if (t1 > 0 & t2 > 0) {
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        } else if (t1 > 0) {
            return List.of(ray.getPoint(t1));
        } else if (t2 > 0) {
            return List.of(ray.getPoint(t2));
        }
        return null;
    }

    /**
     * Constructor for Tube by using axis and radius
     *
     * @param axisRay ray in the tube
     * @param radius  radius to tube
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        _axisRay = axisRay;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    /**
     * Get axis ray of tube
     *
     * @return axisRay
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    @Override
    public String toString() {
        return super.toString() + " axisRay = " + _axisRay;
    }
}

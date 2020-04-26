package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;

import static primitives.Util.isZero;

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

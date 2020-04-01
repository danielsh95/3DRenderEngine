package geometries;


import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * This Tube class implement from RadialGeometry to achieve Tube
 */
public class Tube extends RadialGeometry {
    private Ray _axisRay;

    /**
     * GetNormal to Tube
     * @param point3D
     * @return normal to Tube
     * **/
    @Override
    public Vector getNormal(Point3D point3D) {
        Vector P0_to_P = point3D.subtract(_axisRay.getPOO());
        Vector v = _axisRay.getDirection().normalized();
        double t = P0_to_P.dotProduct(v);


        Point3D o = _axisRay.getPOO().add(v.scale(t));
        Vector n = point3D.subtract(o).normalize();

        return n;
    }

    /**
     * Constructor for Tube by using axis and radius
     *
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this._axisRay = axisRay;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    /**
     * Get axis of tube
     *
     * @return _axisRay
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    @Override
    public String toString() {
        return super.toString() + " axisRay = " + _axisRay;
    }
}

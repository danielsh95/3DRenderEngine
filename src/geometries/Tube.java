package geometries;


import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * This Tube class implement from RadialGeometry to achieve Tube
 */
public class Tube extends RadialGeometry {
    private Ray _axisRay;

    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }

    /**
     * Constructor for Tube by using axis and radius
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius){
        super(radius);
        this._axisRay = axisRay;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    /**
     * Get axis of tube
     * @return _axisRay
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    @Override
    public String toString() {
        return super.toString() + " axisRay = " +  _axisRay;
    }
}

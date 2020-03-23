package geometries;


import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
    Ray _axisRay;

    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }

    public Tube(Ray axisRay, double radius){
        super(radius);
        this._axisRay = axisRay;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    public Ray get_axisRay() {
        return _axisRay;
    }

    @Override
    public String toString() {
        return super.toString() + " axisRay = " +  _axisRay;
    }
}

package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    double _height;

    @Override
    public Vector getNormal(Point3D point3D) {
        return  null;
    }
    public Cylinder(double height, Ray axisRay, double radius ){
        super(axisRay, radius);
        this._height = height;
    }

    public double get_height() {
        return _height;
    }

    @Override
    public String toString() {
        return super.toString() + " height = " + _height;

    }
}

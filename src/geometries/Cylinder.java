package geometries;

import jdk.jfr.Description;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


/**
 * This class is used to represent Cylinder by extend from Tube class
 */
public class Cylinder extends Tube {
    private double _height;


    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }


    /**
     * Constractor of Cylinder
     * @param height height of Cylinder
     * @param axisRay axis of Cylinder
     * @param radius radius of Cylinder
     * **/
    public Cylinder(double height, Ray axisRay, double radius ){
        super(axisRay, radius);
        this._height = height;
    }

    /**
     * Get height of Cylinder
     * @return height in type double
     * **/
    public double get_height() {
        return _height;
    }

    @Override
    public String toString() {
        return super.toString() + " height = " + _height;

    }
}

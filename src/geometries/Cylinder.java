package geometries;

import jdk.jfr.Description;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;

import static primitives.Util.isZero;

import primitives.Vector;

import java.util.List;

/**
 * This class used to represent Cylinder by extend from Tube class
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * Get Normal to Cylinder by the point3D
     *
     * @param point3D point in the cylinder
     * @return normal vector
     **/
    @Override
    public Vector getNormal(Point3D point3D) {
        Vector v = _axisRay.getDirection();
        Vector p0ToP;
        try {
            p0ToP = point3D.subtract(_axisRay.getPOO());
        } catch (IllegalArgumentException e) {// point3D == Poo
            return v;
        }

        //if point3D in the bases
        if (isZero(v.dotProduct(p0ToP)))
            return v;

        //else - point is inside the cylinder
        return super.getNormal(point3D);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    /**
     * Constructor of Cylinder
     *
     * @param height  height of Cylinder
     * @param axisRay axis of Cylinder
     * @param radius  radius of Cylinder
     **/
    public Cylinder(double height, Ray axisRay, double radius) {
        super(axisRay, radius);
        this._height = height;
    }

    /**
     * Get height of Cylinder
     *
     * @return height in type double
     **/
    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return super.toString() + " height = " + _height;
    }
}

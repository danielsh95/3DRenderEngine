package geometries;

import jdk.jfr.Description;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;

import primitives.Vector;

import java.util.ArrayList;
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
        List<Point3D> intersectionsWithTube = super.findIntersections(ray);
        Vector axisDirection = _axisRay.getDirection();
        // the point is at the center of the circle on the top base.
        Point3D pToHeight = _axisRay.getPoint(_height);
        Point3D p0 = _axisRay.getPOO();

        // plane of up cylinder and plane down cylinder
        Plane planeUp = new Plane(pToHeight, axisDirection);
        Plane planeDown = new Plane(p0, _axisRay.getDirection().scale(-1));

        // check if the point that on the tube is on the cylinder.
        List<Point3D> intersectionsWithCylinder = new ArrayList<Point3D>();
        if (intersectionsWithTube != null) {
            for (int i = 0; i < intersectionsWithTube.size(); i++) {
                double projection = alignZero(intersectionsWithTube.get(i).subtract(p0).dotProduct(axisDirection));
                if (projection < _height && projection > 0)
                    intersectionsWithCylinder.add(intersectionsWithTube.get(i));
            }
        }

        //intersection with plane upd and down of cylinder
        List<Point3D> intersectionsWithPlaneUp = planeUp.findIntersections(ray);
        List<Point3D> intersectionsWithPlaneDown = planeDown.findIntersections(ray);

        // if not have intersections
        if (intersectionsWithPlaneUp == null && intersectionsWithPlaneDown == null)
            return intersectionsWithCylinder.size() > 0 ? intersectionsWithCylinder : null;

        // check if have some intersection in the plane up , if yes add to intersectionsWithCylinder
        if (intersectionsWithPlaneUp != null) {
            Point3D intersectPointWIthPlane = intersectionsWithPlaneUp.get(0);
            double distance = alignZero(intersectPointWIthPlane.distance(pToHeight));
            if (distance < _radius)
                intersectionsWithCylinder.add(intersectPointWIthPlane);
        }

        // check if have some intersection in the plane down , if yes add to intersectionsWithCylinder
        if (intersectionsWithPlaneDown != null) {
            Point3D intersectPointWIthPlane = intersectionsWithPlaneDown.get(0);
            double distance = alignZero(intersectPointWIthPlane.distance(_axisRay.getPOO()));
            if (distance < _radius)
                intersectionsWithCylinder.add(intersectPointWIthPlane);
        }

        // check if there is intersection if not return null
        return intersectionsWithCylinder.size() > 0 ? intersectionsWithCylinder : null;
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

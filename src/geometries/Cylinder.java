package geometries;

import primitives.*;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used to represent Cylinder by extend from Tube class
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * Constructor of Cylinder
     *
     * @param material
     * @param emissionLight
     * @param height        height of Cylinder
     * @param axisRay       axis of Cylinder
     * @param radius        radius of Cylinder
     **/
    public Cylinder(Material material, Color emissionLight, double height, Ray axisRay, double radius) {
        super(material, emissionLight, axisRay, radius);
        this._height = height;
    }

    /**
     * Constructor of Cylinder
     *
     * @param emissionLight
     * @param height        height of Cylinder
     * @param axisRay       axis of Cylinder
     * @param radius        radius of Cylinder
     **/
    public Cylinder(Color emissionLight, double height, Ray axisRay, double radius) {
        this(new Material(0, 0, 0), emissionLight, height, axisRay, radius);
    }

    /**
     * Constructor of Cylinder
     *
     * @param height  height of Cylinder
     * @param axisRay axis of Cylinder
     * @param radius  radius of Cylinder
     **/
    public Cylinder(double height, Ray axisRay, double radius) {
        this(Color.BLACK, height, axisRay, radius);
    }

    /**
     * Get Normal to Cylinder by the point3D
     *
     * @param point3D point in the cylinder
     * @return normal vector
     **/
    @Override
    public Vector getNormal(Point3D point3D) {
        Point3D o = _axisRay.getPOO();
        Vector v = _axisRay.getDirection();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point3D.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t))
            return v;

        //point is outside
        o = o.add(v.scale(t));
        return point3D.subtract(o).normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionsWithTube = super.findIntersections(ray);
        Vector axisDirection = _axisRay.getDirection();
        // the point is at the center of the circle on the top base.
        Point3D pTop = _axisRay.getPoint(_height);
        Point3D p0 = _axisRay.getPOO();

        // create the plane that the top base and lower base are Contained on it
        Plane topBase = new Plane(pTop, axisDirection);
        Plane lowerBase = new Plane(p0, _axisRay.getDirection().scale(-1));
        List<GeoPoint> intersectionsWithTopPlane = null;
        List<GeoPoint> intersectionsWithLowPlane = null;

        // check if the point that on the tube is on the cylinder.
        // calculate the projection on the axis if it bigger than height or smallest
        // than 0-no intersection point.
        List<GeoPoint> intersectionsWithCylinder = new ArrayList<GeoPoint>();
        if (intersectionsWithTube != null) {
            for (int i = 0; i < intersectionsWithTube.size(); i++) {
                double projection = alignZero(intersectionsWithTube.get(i)._point.subtract(p0).dotProduct(axisDirection));
                if (projection < _height && projection > 0)
                    intersectionsWithCylinder.add(intersectionsWithTube.get(i));
            }
        }

        intersectionsWithTopPlane = topBase.findIntersections(ray);
        intersectionsWithLowPlane = lowerBase.findIntersections(ray);
        // if there is no more intersection.
        if (intersectionsWithTopPlane == null && intersectionsWithLowPlane == null)
            return intersectionsWithCylinder.size() > 0 ? intersectionsWithCylinder : null;
        // there is more intersection with the plane, need to check if it is on the top
        // base or lower base.
        if (intersectionsWithTopPlane != null) {
            Point3D intersectPointWIthPlane = intersectionsWithTopPlane.get(0)._point;
            double distance = alignZero(intersectPointWIthPlane.distance(pTop));
            if (distance < _radius)
                intersectionsWithCylinder.add(new GeoPoint(this, intersectPointWIthPlane));
        }
        if (intersectionsWithLowPlane != null) {
            Point3D intersectPointWIthPlane = intersectionsWithLowPlane.get(0)._point;
            double distance = alignZero(intersectPointWIthPlane.distance(_axisRay.getPOO()));
            if (distance < _radius)
                intersectionsWithCylinder.add(new GeoPoint(this, intersectPointWIthPlane));
        }
        // check if there is intersection if not return null
        return intersectionsWithCylinder.size() > 0 ? intersectionsWithCylinder : null;
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

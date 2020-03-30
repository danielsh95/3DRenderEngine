package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This abstract class for RadialGeometry
 */
public abstract class RadialGeometry implements Geometry {
    private double _radius;

    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }

    /**
     * constructor with parameter radius
     *
     * @param radius
     */
    public RadialGeometry(double radius) {
        this._radius = radius;
    }

    /**
     * copy constructor
     *
     * @param radialGeometry
     */
    public RadialGeometry(RadialGeometry radialGeometry) {
        this._radius = radialGeometry._radius;
    }

    /**
     * Get the radius
     **/
    public double getRadius() {
        return _radius;
    }
}

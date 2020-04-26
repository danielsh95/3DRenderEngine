package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This abstract class for RadialGeometry
 */
public abstract class RadialGeometry implements Geometry {
    protected double _radius;

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
     *
     * @return get the radius of geometry
     **/
    public double getRadius() {
        return _radius;
    }
}

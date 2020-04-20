package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This class is used to represent shape geometry
 */
interface Geometry extends Intersectable {
    /**
     * Get normal to geometry shape
     *
     * @param point3D point in the geometry shape
     * **/
    public Vector getNormal(Point3D point3D);
}
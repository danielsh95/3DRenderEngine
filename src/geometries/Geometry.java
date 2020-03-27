package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This class is used to represent shape geometry
 */
interface Geometry {
    public Vector getNormal(Point3D point3D);
}

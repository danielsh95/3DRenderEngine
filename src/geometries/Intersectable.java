package geometries;

import primitives.Ray;
import primitives.Point3D;

import java.util.List;

/**
 * Interface of Intersections
 **/
public interface Intersectable {
    /**
     * Method that finding some points of intersections in geometry
     * If not have intersections will return null
     *
     * @param ray ray to geometry
     * @return List<Point3D> return some points of intersections
     **/
    List<Point3D> findIntersections(Ray ray);
}

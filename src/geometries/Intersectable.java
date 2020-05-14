package geometries;

import primitives.Ray;
import primitives.Point3D;

import java.util.List;
import java.util.Objects;

/**
 * Interface of Intersections
 **/
public interface Intersectable {

    //Class static for some point on geometry
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * Ctor for GeoPoint
         *
         * @param geometry
         * @param point
         **/
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) &&
                    point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }

    /**
     * Method that finding some points of intersections in geometry
     * If not have intersections will return null
     *
     * @param ray ray to geometry
     * @return List<Point3D> return some points of intersections
     **/
    List<GeoPoint> findIntersections(Ray ray);
}

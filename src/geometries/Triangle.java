package geometries;

import primitives.*;

import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.List;

/**
 * This Triangle class implement from Polygon to achieve Triangle
 */
public class Triangle extends Polygon {

    /**
     * Constructor for triangle
     *
     * @param material
     * @param emissionLight
     * @param point3D_1     point 1 of triangle
     * @param point3D_2     point 2 of triangle
     * @param point3D_3     point 3 of triangle
     */
    public Triangle(Material material, Color emissionLight, Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        super(material, emissionLight, point3D_1, point3D_2, point3D_3);
    }

    /**
     * Constructor for triangle
     *
     * @param emissionLight
     * @param point3D_1     point 1 of triangle
     * @param point3D_2     point 2 of triangle
     * @param point3D_3     point 3 of triangle
     */
    public Triangle(Color emissionLight, Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        this(new Material(0, 0, 0), emissionLight, point3D_1, point3D_2, point3D_3);
    }

    /**
     * Constructor for triangle
     *
     * @param point3D_1 point 1 of triangle
     * @param point3D_2 point 2 of triangle
     * @param point3D_3 point 3 of triangle
     */
    public Triangle(Point3D point3D_1, Point3D point3D_2, Point3D point3D_3) {
        this(Color.BLACK, point3D_1, point3D_2, point3D_3);
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionsOfPlane = _plane.findIntersections(ray);
        if (intersectionsOfPlane == null)
            return null;//out of plane - not intersections

        Point3D p0 = ray.getPOO();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        //Check if all three vectors is against the triangle
        double projV1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(projV1))
            return null;
        double projV2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(projV2))
            return null;
        double projV3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(projV3))
            return null;

        //Check if all the projV is same sign
        if ((projV1 > 0 && projV2 > 0 && projV3 > 0) || (projV1 < 0 && projV2 < 0 && projV3 < 0)) {

            //update intersections to be geometry of polygon
            List<GeoPoint> intersectionsOfTriangle = new ArrayList<>();
            for (GeoPoint g : intersectionsOfPlane) {
                intersectionsOfTriangle.add(new GeoPoint(this, g.point));
            }
            return intersectionsOfTriangle;
        } else return null;
    }
}

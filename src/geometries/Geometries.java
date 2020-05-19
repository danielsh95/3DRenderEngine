package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * This class to show all geometries
 **/
public class Geometries implements Intersectable {
    private List<Intersectable> _geometries;

    /**
     * Default constructor for the geometries
     **/
    public Geometries() {
        _geometries = new ArrayList<Intersectable>();
    }

    /**
     * constructor for the geometries
     *
     * @param geometries list of geometries
     **/
    public Geometries(Intersectable... geometries) {
        _geometries = new ArrayList<Intersectable>();
        add(geometries);
    }

    /**
     * that method add geometries
     *
     * @param geometries list of geometries
     **/
    public void add(Intersectable... geometries) {
        for (Intersectable g : geometries) {
            _geometries.add(g);
        }
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> allIntersection = null;

        //loop on all geometries
        for (Intersectable g : _geometries) {
            List<GeoPoint> intersections = g.findIntersections(ray);

            if (intersections != null) {
                if (allIntersection == null) {//for the first time
                    allIntersection = new ArrayList<GeoPoint>();
                }
                allIntersection.addAll(intersections);
            }
        }
        return allIntersection;
    }
}

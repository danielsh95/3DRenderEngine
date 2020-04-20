package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> _geometries;

    /**
     * Default constructor for the geometries
     **/
    public Geometries() {
        _geometries = new LinkedList<Intersectable>();
    }

    /**
     * constractor for the geometries
     *
     * @param geometries
     **/
    public Geometries(Intersectable... geometries) {
        _geometries = new LinkedList<Intersectable>();
        add(geometries);
    }

    /**
     * that method add geometries
     **/
    public void add(Intersectable... geometries) {
        for (Intersectable g : geometries) {
            _geometries.add(g);
        }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> allIntersection = null;

        //loop on all geometries
        for (Intersectable g : _geometries) {
            List<Point3D> intersections = g.findIntersections(ray);

            if (intersections != null) {
                if (allIntersection == null) {//for the first time
                    allIntersection = new LinkedList<Point3D>();
                }
                allIntersection.addAll(intersections);
            }
        }
        return allIntersection;
    }
}

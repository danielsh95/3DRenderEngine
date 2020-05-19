package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class is using to represent shape geometry
 */
public abstract class Geometry implements Intersectable {

    protected Color _emission;
    protected Material _material;

    /**
     * Constructor for Geometry
     *
     * @param emission
     * @param material
     **/
    public Geometry(Color emission, Material material) {
        _emission = emission;
        _material = material;
    }

    /**
     * Constructor for Geometry
     *
     * @param emission
     **/
    public Geometry(Color emission) {
        this(emission, new Material(0d, 0d, 0));
    }

    /**
     * Constructor for Geometry
     **/
    public Geometry() {
        this(Color.BLACK);
    }

    /**
     * Get normal to geometry shape
     *
     * @param point3D point in the geometry shape
     **/
    public abstract Vector getNormal(Point3D point3D);

    /**
     * Get emission of geometry
     *
     * @return emission
     **/
    public Color getEmission() {
        return _emission;
    }

    /**
     * Get material of geometry
     **/
    public Material getMaterial() {
        return _material;
    }
}
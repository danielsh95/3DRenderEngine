package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * This abstract class for RadialGeometry
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /**
     * constructor of radialGeometry
     *
     *  @param material
     * @param emissionLight
     * @param radius
     */
    public RadialGeometry(Material material, Color emissionLight, double radius) {
        super(emissionLight, material);
        this._radius = radius;
    }

    /**
     * constructor of radialGeometry
     *
     * @param emissionLight
     * @param radius
     */
    public RadialGeometry(Color emissionLight, double radius) {
        this(new Material(0, 0, 0), emissionLight, radius);
    }

    /**
     * constructor of radialGeometry
     *
     * @param radius
     */
    public RadialGeometry(double radius) {
        this(Color.BLACK, radius);
    }

    /**
     * copy constructor
     *
     * @param radialGeometry
     */
    public RadialGeometry(RadialGeometry radialGeometry) {
        this(radialGeometry._material, radialGeometry._emission, radialGeometry._radius);
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

package element;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for Light Sources
 **/
public interface LightSource {

    /**
     * Get intensity of LightSource in point
     *
     * @param p point
     * @return a new color of Intensity in point
     **/
    public Color getIntensity(Point3D p);

    /**
     * Get direction vector from light source to point
     *
     * @param p point
     * @return a new direction vector
     **/
    public Vector getL(Point3D p);
}

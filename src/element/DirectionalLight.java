package element;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class for DirectionalLight
 **/
public class DirectionalLight extends Light implements LightSource {
    private Vector _direction;

    /**
     * Ctor for DirectionalLight
     *
     * @param intensity
     * @param direction
     **/
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public double getDistance(Point3D point){
        return Double.POSITIVE_INFINITY;
    }
}

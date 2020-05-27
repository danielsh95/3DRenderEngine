package element;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Class for PointLight
 **/
public class PointLight extends Light implements LightSource {
    protected Point3D _position;
    protected double _kC, _kL, _kQ;

    /**
     * C'tor for PointLight
     *
     * @param intensity
     * @param position
     * @param kC
     * @param kL
     * @param kQ
     **/
    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
        super(intensity);
        _position = position;
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double distance = p.distanceSquared(_position);
        double d = p.distance(_position);

        return _intensity.reduce(_kC + _kL * d + _kQ * distance);
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position))
            return null;
        return p.subtract(_position).normalize();
    }

    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
}

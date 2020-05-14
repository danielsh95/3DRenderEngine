package element;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class for SpotLight
 **/
public class SpotLight extends PointLight {
    private Vector _direction;

    /**
     * Ctor for SpotLight
     *
     * @param intensity
     * @param position
     * @param direction
     * @param kC
     * @param kL
     * @param kQ
     **/
    public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(intensity, position, kC, kL, kQ);
        _direction = direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double dsquerd = p.distanceSquared(_position);

        double distance = p.distance(_position);
        double projDToL = getL(p).dotProduct(_direction);

        if (alignZero(projDToL) <= 0) {
            return Color.BLACK;
        }
        return _intensity.scale(projDToL).reduce(_kC + _kL * distance + _kQ * dsquerd);
    }
}

package element;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;

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
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        double projDToL = getL(p).dotProduct(_direction);
        if (alignZero(projDToL) <= 0)
            return Color.BLACK;

        return super.getIntensity(p).scale(projDToL);
    }
}

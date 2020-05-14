package primitives;

/**
 * Class for material
 **/
public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;

    /**
     * C'tor for class material
     **/
    public Material(double kD, double kS, int nShininess) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
    }

    /**
     * Get kD - coefficient to diffuse
     *
     * @return kD
     **/
    public double getKD() {
        return _kD;
    }

    /**
     * Get kS - coefficient to specular
     *
     * @return kS
     **/
    public double getKS() {
        return _kS;
    }

    /**
     * Get nShininess - pow of specular
     *
     * @return nShininess
     **/
    public int getNShininess() {
        return _nShininess;
    }
}

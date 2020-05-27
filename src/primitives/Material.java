package primitives;

/**
 * Class for material
 **/
public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;
    private double _kT;
    private double _kR;

    /**
     * C'tor for class material
     *
     * @param kT
     * @param kR
     * @param kD         pre diffuse
     * @param kS         pre specular
     * @param nShininess pow n
     **/
    public Material(double kT, double kR, double kD, double kS, int nShininess) {
        _kT = kT;
        _kR = kR;
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
    }

    /**
     * C'tor for class material
     *
     * @param kD         pre diffuse
     * @param kS         pre specular
     * @param nShininess pow n
     **/
    public Material(double kD, double kS, int nShininess) {
        this(0, 0, kD, kS, nShininess);
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

    /**
     * Get kr
     *
     * @return kr
     * **/
    public double getKr() {
        return _kR;
    }

    /**
     * Get kt
     *
     * @return kt
     * **/
    public double getKt() {
        return _kT;
    }
}

package element;

import primitives.Color;

/**
 * class for color
 * **/
public class AmbientLight {
    private Color _intensity;

/**
 * constractor for color
 *
 * @param ia color
 * @param ka intensity
 * **/
    public AmbientLight(Color ia, double ka) {
        _intensity = ia.scale(ka);
    }

    /**
     * get intensity
     *
     * @return intensity
     * **/
    public java.awt.Color GetIntensity(){
        return _intensity.getColor();
    }
}

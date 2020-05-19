package element;

import primitives.Color;

/**
 * class AmbientLight for color light
 **/
public class AmbientLight extends Light {

    /**
     * constructor for color - (Ia * Ka)
     *
     * @param ia color
     * @param ka intensity
     **/
    public AmbientLight(Color ia, double ka) {
        super(ia.scale(ka));
    }
}

package element;

import primitives.Color;

/**
 * class for color
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

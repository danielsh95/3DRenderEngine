package element;

import primitives.Color;

/**
 * class for light
 **/
abstract class Light {
    protected Color _intensity;

    /**
     * Ctor for light
     *
     * @param intensity
     **/
    public Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * Get intensity
     *
     * @return intensity of light
     **/
    public Color getIntensity() {
        return _intensity;
    }
}

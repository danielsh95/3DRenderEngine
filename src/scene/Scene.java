package scene;

import primitives.Color;
import element.AmbientLight;
import element.Camera;
import geometries.Geometries;
import geometries.Intersectable;

/**
 * scene class with geometries, camera, and light
 **/
public class Scene {
    private String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private Camera _camera;
    private double _distance;

    /**
     * Ctor of scene
     *
     * @param name of scene
     **/
    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
    }

    /**
     * Add one or more geometries to scene
     *
     * @param geometries one or more geometries
     **/
    public void addGeometries(Intersectable... geometries) {
        _geometries.add(geometries);
    }

    /**
     * Get the name of scene
     *
     * @return name
     **/
    public String getName() {
        return _name;
    }

    /**
     * Get the background color of scene
     *
     * @return background color
     **/
    public Color getBackground() {
        return _background;
    }

    /**
     * Get the ambientLight of scene
     *
     * @return ambientLight
     **/
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * Get the geometries of scene
     *
     * @return geometries
     **/
    public Geometries getGeometries() {
        return _geometries;
    }

    /**
     * Get the camera of scene
     *
     * @return camera
     **/
    public Camera getCamera() {
        return _camera;
    }

    /**
     * Get the distance of scene
     *
     * @return distance
     **/
    public double getDistance() {
        return _distance;
    }

    /**
     * Set background to scene
     *
     * @param background set a new background to scene
     **/
    public void setBackground(Color background) {
        _background = background;
    }

    /**
     * Set ambientLight to scene
     *
     * @param ambientLight set a new ambientLight to scene
     **/
    public void setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
    }

    /**
     * Set camera to scene
     *
     * @param camera set a new camera to scene
     **/
    public void setCamera(Camera camera) {
        _camera = camera;
    }

    /**
     * Set distance to scene
     *
     * @param distance set a new distance to scene
     **/
    public void setDistance(double distance) {
        _distance = distance;
    }
}

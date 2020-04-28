package renderer;

import element.Camera;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.awt.*;
import java.util.List;

/**
 * Engine for create image from scene
 * **/
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

    /**
     * constractor for render
     *
     * @param imageWriter
     * @param scene
     * **/
    public Render(ImageWriter imageWriter, Scene scene) {
        _scene = scene;
        _imageWriter = imageWriter;
    }

    /**
     * method that paint the image
     *
     * **/
    public void renderImage() {

        //get details from scene
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        double distance = _scene.getDistance();
        Intersectable geometries = _scene.getGeometries();

        //get details from imageWriter
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();

        //loop on every pixel and check if has intersection/s, if not so will paint the pixel at background,
        //if yes more than one so will paint by ambientLight
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, i, j, distance, width, height);
                List<Point3D> result = geometries.findIntersections(ray);
                if (result == null) {
                    _imageWriter.writePixel(i, j, background);
                } else {
                    Point3D nearly = getClosestPoint(result);
                    _imageWriter.writePixel(i, j, calcColor(nearly));
                }
            }
        }
    }

    /**
     * Method to get the color of ambientLight
     *
     * @param p point for paint
     * @return intensity of color
     * **/
    public Color calcColor(Point3D p) {
        return _scene.getAmbientLight().GetIntensity();
    }

    /**
     * Get the closest point to p0 from list of points
     *
     * @param points list of points
     * @return closest point
     * **/
    public Point3D getClosestPoint(List<Point3D> points) {
        Point3D closesPoint = null;
        Point3D p0 = _scene.getCamera().getP0();
        double minDistance = Double.MAX_VALUE;

        for (Point3D p : points) {
            double distance = p0.distance(p);
            if (distance < minDistance) {
                minDistance = distance;
                closesPoint = p;
            }
        }
        return closesPoint;
    }

    /**
     * Write color to image a new overload grids by interval
     *
     * @param interval interval of some points to write in the matrix
     * @param color color to paint
     * **/
    public void printGrid(int interval, java.awt.Color color) {

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) { //write every interval
                    _imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

/**
 * Create a new file of image and write to it
 *
 * **/
    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}
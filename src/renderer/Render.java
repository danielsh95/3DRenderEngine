package renderer;

import element.Camera;
import element.LightSource;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

/**
 * Engine for create an image from scene
 **/
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

    /**
     * constructor for render
     *
     * @param imageWriter
     * @param scene
     **/
    public Render(ImageWriter imageWriter, Scene scene) {
        _scene = scene;
        _imageWriter = imageWriter;
    }

    /**
     * method that paint the image
     **/
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
                List<GeoPoint> result = geometries.findIntersections(ray);
                if (result == null) {
                    _imageWriter.writePixel(i, j, background);
                } else {
                    GeoPoint nearly = getClosestPoint(result);
                    _imageWriter.writePixel(i, j, calcColor(nearly).getColor());
                }
            }
        }
    }

    /**
     * Method to get the color of ambientLight
     *
     * @param geoPoint geoPoint for paint
     * @return color in the point on geometry
     **/
    private Color calcColor(GeoPoint geoPoint) {

        //Add to result color ambientLight + emission
        Color result = _scene.getAmbientLight().getIntensity();
        result = result.add(geoPoint._geometry.getEmission());

        List<LightSource> lights = _scene.getLights();

        //v= p in the geometry to p0
        Vector v = geoPoint._point.subtract(_scene.getCamera().getP0()).normalize();
        //normal in the point
        Vector n = geoPoint._geometry.getNormal(geoPoint._point);

        Material material = geoPoint._geometry.getMaterial();
        int nShininess = material.getNShininess();
        double kd = material.getKD();
        double ks = material.getKS();

        //if have lights
        if (_scene.getLights() != null) {
            //loop for every lights
            for (LightSource lightSource : lights) {

                Vector l = lightSource.getL(geoPoint._point);
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));

                //if nl and nv are same sign
                if (sign(nl) == sign(nv)) {
                    Color ip = lightSource.getIntensity(geoPoint._point);

                    //add to result defuse + specular
                    result = result.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n, nl, v, nShininess, ip)
                    );
                }
            }
        }
        //return color in the point
        return result;
    }

    /**
     * Check if the number is sign
     *
     * @param val value to check
     * @return result
     **/
    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * Get the closest point to p0 from list of points
     *
     * @param points list of points
     * @return closest point
     **/
    private GeoPoint getClosestPoint(List<GeoPoint> points) {
        GeoPoint closesPoint = null;
        Point3D p0 = _scene.getCamera().getP0();
        double minDistance = Double.MAX_VALUE;

        for (GeoPoint geoPoint : points) {
            double distance = p0.distance(geoPoint._point);
            if (distance < minDistance) {
                minDistance = distance;
                closesPoint = geoPoint;
            }
        }
        return closesPoint;
    }

    /**
     * Write color to image a new overload grids by interval
     *
     * @param interval interval of some points to write in the matrix
     * @param color    color to paint
     **/
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
     **/
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * Calc diffusing
     *
     * @param kd pre diffuse
     * @param nl the angle between normal in the point to L
     * @param ip light in the point
     **/
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) nl = -nl;
        return ip.scale(nl * kd);
    }

    /**
     * Get the color of the specular
     *
     * @param ks         pre specular
     * @param l          vector from light to point in the geometry
     * @param n          normal in the point
     * @param nl         the angle between normal in the point to L
     * @param v          normalize vector form camera to point in geometry
     * @param nShininess n Shininess
     * @param ip         light in the point
     **/
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        double p = nShininess;

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        return ip.scale(ks * Math.pow(minusVR, p));
    }
}
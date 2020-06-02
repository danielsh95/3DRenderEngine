package renderer;

import element.Camera;
import element.LightSource;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Engine for create an image from scene
 **/
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;
    private static final int MAX_CALC_COLOR_LEVEL = 10;//stop recursion by max calls
    private static final double MIN_CALC_COLOR_K = 0.001;//stop recursion by min pre k

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
                    _imageWriter.writePixel(i, j, calcColor(nearly, ray).getColor());
                }
            }
        }
    }

    /**
     * calculate a color in the point by ray
     *
     * @param geoPoint point
     * @param inRay    ray
     * @return color in the point
     **/
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        Color color = calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
        color = color.add(_scene.getAmbientLight().getIntensity());
        return color;
    }

    /**
     * Method to get the color of ambientLight
     *
     * @param geoPoint geoPoint for paint
     * @param inRay    ray from camera to geometry
     * @param level    max iteration of recursion
     * @param k        min iteration of recursion
     * @return color in the point on geometry
     **/
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        if (level == 1 || k < MIN_CALC_COLOR_K) {
            return Color.BLACK;
        }

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

                double ktr = transparency(lightSource, l, n, geoPoint);
                if (ktr * k > MIN_CALC_COLOR_K) {

                    //if nl and nv are same sign
                    if (nl * nv > 0) {
                        //if (sign(nl) == sign(nv)) {
                        Color ip = lightSource.getIntensity(geoPoint._point).scale(ktr);

                        //add to result defuse + specular
                        result = result.add(
                                calcDiffusive(kd, nl, ip),
                                calcSpecular(ks, l, n, nl, v, nShininess, ip)
                        );
                    }
                }
            }
        }

        double kr = geoPoint._geometry.getMaterial().getKr();
        double kt = geoPoint._geometry.getMaterial().getKt();
        double kKr = k * kr;
        double kKt = k * kt;

        //add to color from the ray reflected recursive
        if (kKr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geoPoint._point, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                //result = kr*ir
                result = result.add(calcColor(reflectedPoint, reflectedRay, level - 1, kKr).scale(kr));
            }
        }

        //add to color from the ray refracted recursive
        if (kKt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(geoPoint._point, inRay, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                //result = kt*it
                result = result.add(calcColor(refractedPoint, refractedRay, level - 1, kKt).scale(kt));
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
     * @return diffusive color
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
     * @return specular color
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

    /**
     * check if there are intersection with any geometry between light source and point
     *
     * @param lightSource
     * @param l
     * @param n
     * @param gp
     * @return if there are geometry between light source and point on our geometry
     **/
    private boolean unshaded(LightSource lightSource, Vector l, Vector n, GeoPoint gp) {
        //against l - vector from point to light source
        Vector lightDirection = l.scale(-1);
        //check where point finding
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? Ray.DELTA : -Ray.DELTA);
        //add point the delta vector
        Point3D point = gp._point.add(delta);

        //create ray from new point to light source
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        //check if there are intersection
        if (intersections == null) {
            return true;
        }

        //distance from point and light source
        double lightDistance = lightSource.getDistance(gp._point);
        for (GeoPoint geoPoint : intersections) {
            //temp = difference between lightDistance and (distance from point and some geometry)
            double difference = geoPoint._point.distance(gp._point) - lightDistance;
            //if difference is positive its mean intersection with geometry is outside and not transparent
            if (alignZero(difference) <= 0 && isZero(gp._geometry.getMaterial().getKt()))
                return false;
        }

        //because intersection with geometry is outside
        return true;
    }

    /**
     * Get reflected ray
     *
     * @param normal   normal to geometry
     * @param geoPoint intersection point in geometry
     * @param inRay    ray from light source
     * @return reflected ray
     **/
    private Ray constructReflectedRay(Vector normal, Point3D geoPoint, Ray inRay) {
        double vN = inRay.getDirection().dotProduct(normal);

        //v orthogonal to n
        if (isZero(vN))
            return null;

        Vector r;
        try {
            r = inRay.getDirection().subtract(normal.scale(vN).scale(2));
        } catch (IllegalArgumentException e) {
            return null;
        }

        //r = v-2*n(v*n)
        return new Ray(geoPoint, r, normal);
    }

    /**
     * Get refracted ray
     *
     * @param geoPoint
     * @param inRay
     * @param n        vector normal
     * @return refracted ray
     **/
    private Ray constructRefractedRay(Point3D geoPoint, Ray inRay, Vector n) {
        return new Ray(geoPoint, inRay.getDirection(), n);
    }

    /**
     * find the closest intersection point
     *
     * @param ray
     * @return closest intersection point
     **/
    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null)
            return null;

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
        if (intersections == null)//if not find intersections points
            return null;

        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.getPOO();

        //find the closest intersection point
        for (GeoPoint geoPoint : intersections) {
            double distance = ray_p0.distance(geoPoint._point);
            if (distance < closestDistance) {
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }

    /**
     * Get partial shading
     *
     * @param light    light source
     * @param l        vector from light source
     * @param n        normal
     * @param geopoint point
     * @return degree of transparency
     **/
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint._point, lightDirection, n);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) return 1.0;
        double lightDistance = light.getDistance(geopoint._point);
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp._point.distance(geopoint._point) - lightDistance) <= 0) {
                ktr *= gp._geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }
}
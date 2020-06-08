package element;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.isZero;

/**
 * class for a camera that will see geometry by view plane
 **/
public class Camera {
    private Point3D _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;
    private int numOfRays = 1; //num of rays in every pixel(default = 1)
    private boolean useManyRays = false; //default is off

    /**
     * constructor for camera
     *
     * @param p0
     * @param vUp
     * @param vTo
     **/
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) throw new IllegalArgumentException("vectors are not orthogonal");
        _p0 = p0;
        _vUp = vUp.normalized();
        _vTo = vTo.normalize();
        _vRight = vTo.crossProduct(vUp).normalized();
    }

    /**
     * Get Ray for every point that on the view plane
     *
     * @param i              the location of p in an axis x
     * @param i              the location of p in an axis y
     * @param nX             pixels of axis x
     * @param nY             pixels of axis y
     * @param screenDistance distance from p0 to view plane
     * @param screenHeight   height dots for every pixel
     * @param screenWidth    width dots for every pixel
     * @return new list of rays from p0 to area of p
     **/
    public List<Ray> constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) throw new IllegalArgumentException("distance can't be 0");

        List<Ray> rays = new LinkedList<>();
        Random random = new Random();

        //the center point of view plane
        Point3D pC = _p0.add(_vTo.scale(screenDistance));

        double rX = screenWidth / nX;
        double rY = screenHeight / nY;

        double pX = (j - nX / 2d) * rX + rX / 2d;
        double pY = (i - nY / 2d) * rY + rY / 2d;

        Point3D p = pC; //the point p start from the center
        Point3D pointInCenterPixel = getPointInPixel(p, pX, pY);// p is center of the pixel
        rays.add(new Ray(_p0, pointInCenterPixel.subtract(_p0)));

        Point3D pointInPixel;
        if (useManyRays) {
            //add some rays in pixel
            for (int k = 1; k < numOfRays; k++) {
                double x = (random.nextDouble() - 0.5) * rX;
                double y = (random.nextDouble() - 0.5) * rY;
                pointInPixel = getPointInPixel(pointInCenterPixel, x, y);
                rays.add(new Ray(_p0, pointInPixel.subtract(_p0)));
            }
        }
        return rays;
    }

    /**
     * Get point in pixel
     *
     * @param p center point in the pixel
     * @param x move by x from the center
     * @param y move by y from the center
     * @return new point in the pixel
     **/
    private Point3D getPointInPixel(Point3D p, double x, double y) {
        if (!isZero(x)) {//can't be vector 0
            p = p.add(_vRight.scale(x));
        }

        if (!isZero(y)) {//can't be vector 0
            p = p.add(_vUp.scale((-1 * y))); // same as p.substruct(vUp.scale(pY))
        }
        return p;
    }

    /**
     * Get center of camera
     *
     * @return center of camera
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * Get vector right of camera
     *
     * @return vector right of camera
     */
    public Vector getvRight() {
        return _vRight;
    }

    /**
     * Get vector toward of camera
     *
     * @return vector toward of camera
     */
    public Vector getvTo() {
        return _vTo;
    }

    /**
     * Get vector up of camera
     *
     * @return vector up of camera
     */
    public Vector getvUp() {
        return _vUp;
    }

    /**
     * Set num of rays for pixel
     *
     * @param numOfRays
     **/
    public void setNumOfRays(int numOfRays) {
        if (numOfRays < 1)
            throw new IllegalArgumentException("num of rays must be one or more");
        this.numOfRays = numOfRays;
    }

    /**
     * Set options of use many rays
     *
     * @param useManyRays
     **/
    public void setUseManyRays(boolean useManyRays) {
        this.useManyRays = useManyRays;
    }
}

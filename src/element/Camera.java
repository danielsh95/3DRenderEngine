package element;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * class for camera that will see geometry by view plane
 **/
public class Camera {
    private Point3D _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;

    /**
     * constractor for camera
     *
     * @param p0
     * @param vUp
     * @param vTo
     **/
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) throw new IllegalArgumentException("vectors are not ortogonal");
        _p0 = p0;
        _vUp = vUp.normalized();
        _vTo = vTo.normalize();
        _vRight = vTo.crossProduct(vUp).normalized();
    }

    /**
     * Get Ray for every point that on the view plane
     *
     * @param i              the location of p in axis x
     * @param i              the location of p in axis y
     * @param nX             pixels of axis x
     * @param nY             pixels of axis y
     * @param screenDistance distance from p0 to view plane
     * @param screenHeight   height dots for every pixel
     * @param screenWidth    width dots for every pixel
     * @return new ray from p0 to p
     **/
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) throw new IllegalArgumentException("distance can't be 0");

        //the center point of view plane
        Point3D pC = _p0.add(_vTo.scale(screenDistance));

        double rX = screenWidth / nX;
        double rY = screenHeight / nY;

        double pX = (j - nX / 2d) * rX + rX / 2d;
        double pY = (i - nY / 2d) * rY + rY / 2d;

        Point3D p = pC; //the point p start from the center
        if (!isZero(pX)) {//can't be vector 0
            p = p.add(_vRight.scale(pX));
        }

        if (!isZero(pY)) {//can't be vector 0
            p = p.add(_vUp.scale((-1 * pY))); // same as p.substruct(vUp.scale(pY))
        }

        //the vector from p0 to p
        Vector vP0ToP = p.subtract(_p0);

        return new Ray(_p0, vP0ToP);
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
}

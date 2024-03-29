package primitives;

import static primitives.Util.isZero;

/**
 * This Ray class to represent point that not start from axis main.
 * And Ray contain direction vector.
 **/
public class Ray {
    private Point3D _POO;
    private Vector _direction;
    public static final double DELTA = 0.1; // to move in the epsilon

    /**
     * Constructor of Ray that contain Point3D and direction to object Ray
     *
     * @param POO       for starting point
     * @param direction for direction vector
     **/
    public Ray(Point3D POO, Vector direction) {
        this._POO = POO;
        this._direction = direction.normalized();
    }

    /**
     * Constructor of Ray that contain Point3D and direction and normal to object Ray
     *
     * @param point     for starting point
     * @param direction for direction vector
     * @param normal    normal to object
     **/
    public Ray(Point3D point, Vector direction, Vector normal) {
        //point + normal.scale(±DELTA)
        _direction = direction.normalized();

        double nv = normal.dotProduct(direction);

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _POO = point.add(normalDelta);
    }

    /**
     * Copy Constructor of Ray
     *
     * @param ray
     **/
    public Ray(Ray ray) {
        this._POO = ray._POO;
        this._direction = ray._direction;
    }

    /**
     * Get POO
     *
     * @return POO
     **/
    public Point3D getPOO() {
        return _POO;
    }

    /**
     * Get direction
     *
     * @return direction
     **/
    public Vector getDirection() {
        return _direction;
    }

    /**
     * Get a new point3D by len of ray
     *
     * @param t
     * @return new Point3D
     */
    public Point3D getPoint(double t) {
        return isZero(t) ? _POO : _POO.add(_direction.scale(t));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray) obj;
        return _POO.equals(ray._POO) &&
                _direction.equals(ray._direction);
    }

    @Override
    public String toString() {
        return " POO: " + _POO + " direction: " + _direction;
    }
}

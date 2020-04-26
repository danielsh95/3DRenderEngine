package primitives;

import static primitives.Util.isZero;

/**
 * This Ray class to represent point that not start from axis main.
 * And Ray contain direction vector.
 **/
public class Ray {
    private Point3D POO;
    private Vector direction;

    /**
     * Constructor of Ray that contain Point3D and direction to object Ray
     *
     * @param POO       for starting point
     * @param direction for direction vector
     **/
    public Ray(Point3D POO, Vector direction) {
        this.POO = POO;
        this.direction = direction.normalized();
    }

    /**
     * Copy Constructor of Ray
     *
     * @param ray
     **/
    public Ray(Ray ray) {
        this.POO = ray.POO;
        this.direction = ray.direction;
    }

    /**
     * Get POO
     *
     * @return POO
     **/
    public Point3D getPOO() {
        return POO;
    }

    /**
     * Get direction
     *
     * @return direction
     **/
    public Vector getDirection() {
        return direction;
    }

    /**
     * Get a new point3D by len of ray
     *
     * @param t
     * @return new Point3D
     */
    public Point3D getPoint(double t) {
        return isZero(t) ? POO : POO.add(direction.scale(t));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray) obj;
        return POO.equals(ray.POO) &&
                direction.equals(ray.direction);
    }

    @Override
    public String toString() {
        return " POO: " + POO + " direction: " + direction;
    }
}

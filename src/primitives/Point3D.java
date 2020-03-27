package primitives;

import java.util.Objects;

/**
 * This Point class to represent point on the plain
 **/
public class Point3D {
    private Coordinate x;
    private Coordinate y;
    private Coordinate z;
    public final static Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * Constractor of Point3D to object point
     *
     * @param x parameter of coordinate x
     * @param y parameter of coordinate y
     * @param z parameter of coordinate z
     **/
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constractor of Point3D to object point
     *
     * @param x parameter of double x
     * @param y parameter of double y
     * @param z parameter of double z
     **/
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     * Copy constractor of Point3D
     *
     * @param point3D
     **/
    public Point3D(Point3D point3D) {
        this.x = point3D.getX();
        this.y = point3D.getY();
        this.z = point3D.getZ();
    }

    /**
     * subtract method between point in parameter to point of this object
     *
     * @param point3D of subtracted
     * @return Vector after subtract
     **/
    public Vector subtract(Point3D point3D) {
        double newX = this.x.get() - point3D.getX().get();
        double newY = this.y.get() - point3D.getY().get();
        double newZ = this.z.get() - point3D.getZ().get();
        return new Vector(newX, newY, newZ);
    }

    /**
     * Add method for add Vector in parameter to point of this object
     *
     * @param vector for add
     * @return Point3D after Added to new object
     **/
    public Point3D add(Vector vector) {
        double newX = this.x.get() + vector.getHead().getX().get();
        double newY = this.y.get() + vector.getHead().getY().get();
        double newZ = this.z.get() + vector.getHead().getZ().get();
        return new Point3D(newX, newY, newZ);
    }

    /**
     * Get distanceSquared method between Point3D in parameter to point in this object
     *
     * @param point3D the 2nd point
     * @return squared distance
     **/
    public double distanceSquared(Point3D point3D) {
        double subX = this.getX().get() - point3D.x.get();
        double subY = this.getY().get() - point3D.y.get();
        double subZ = this.getZ().get() - point3D.z.get();

        return subX * subX + subY * subY + subZ * subZ;
    }

    /**
     * Get distance method between Point3D in parameter to point in this object
     *
     * @param point3D
     * @return distance from type double
     **/
    public double distance(Point3D point3D) {
        return Math.sqrt(distanceSquared(point3D));
    }

    /**
     * Get x
     *
     * @return x
     **/
    public Coordinate getX() {
        return x;
    }

    /**
     * Get y
     *
     * @return y
     **/
    public Coordinate getY() {
        return y;
    }

    /**
     * Get z
     *
     * @return z
     **/
    public Coordinate getZ() {
        return z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D point3D = (Point3D) obj;
        return x.equals(point3D.x) &&
                y.equals(point3D.y) &&
                z.equals(point3D.z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}

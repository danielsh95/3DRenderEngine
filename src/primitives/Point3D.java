package primitives;

/**
 * This Point class to represent point on the plain
 **/
public class Point3D {
    private Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;
    public final static Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * Constructor of Point3D to object point
     *
     * @param x parameter of coordinate x
     * @param y parameter of coordinate y
     * @param z parameter of coordinate z
     **/
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this._x = x;
        this._y = y;
        this._z = z;
    }

    /**
     * Constructor of Point3D to object point
     *
     * @param x parameter of double x
     * @param y parameter of double y
     * @param z parameter of double z
     **/
    public Point3D(double x, double y, double z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }

    /**
     * Copy constructor of Point3D
     *
     * @param point3D
     **/
    public Point3D(Point3D point3D) {
        this._x = point3D.getX();
        this._y = point3D.getY();
        this._z = point3D.getZ();
    }

    /**
     * subtract method between point in parameter to point of this object
     *
     * @param point3D of subtracted
     * @return Vector after subtract
     **/
    public Vector subtract(Point3D point3D) {
        double newX = this._x.get() - point3D.getX().get();
        double newY = this._y.get() - point3D.getY().get();
        double newZ = this._z.get() - point3D.getZ().get();
        return new Vector(newX, newY, newZ);
    }

    /**
     * Add method for add Vector in parameter to point of this object
     *
     * @param vector for add
     * @return Point3D after Added to new object
     **/
    public Point3D add(Vector vector) {
        double newX = this._x.get() + vector.getHead().getX().get();
        double newY = this._y.get() + vector.getHead().getY().get();
        double newZ = this._z.get() + vector.getHead().getZ().get();
        return new Point3D(newX, newY, newZ);
    }

    /**
     * Get distanceSquared method between Point3D in parameter to point in this object
     *
     * @param point3D the 2nd point
     * @return squared distance
     **/
    public double distanceSquared(Point3D point3D) {
        double subX = this.getX().get() - point3D._x.get();
        double subY = this.getY().get() - point3D._y.get();
        double subZ = this.getZ().get() - point3D._z.get();

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
        return _x;
    }

    /**
     * Get y
     *
     * @return y
     **/
    public Coordinate getY() {
        return _y;
    }

    /**
     * Get z
     *
     * @return z
     **/
    public Coordinate getZ() {
        return _z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D point3D = (Point3D) obj;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" + _x + "," + _y + "," + _z + ")";
    }
}

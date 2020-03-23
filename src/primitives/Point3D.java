package primitives;

import java.util.Objects;

public class Point3D {
    Coordinate x;
    Coordinate y;
    Coordinate z;
    public final static Point3D ZERO = new Point3D(0,0,0);

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    public Point3D(Point3D point3D) {
        this.x = point3D.getX();
        this.y = point3D.getY();
        this.z = point3D.getZ();
    }
    public Vector subtract(Point3D point3D){
        double newX = this.x.get() - point3D.getX().get();
        double newY = this.y.get() - point3D.getY().get();
        double newZ = this.z.get() - point3D.getZ().get();
        return new Vector(newX, newY, newZ);
    }

    public Point3D add(Vector vector){
        double newX = this.x.get() + vector.getHead().getX().get();
        double newY = this.y.get() + vector.getHead().getY().get();
        double newZ = this.z.get() + vector.getHead().getZ().get();
        return new Point3D(newX, newY, newZ);
    }


    public double distanceSquared(Point3D point3D)
    {
        double subX = this.getX().get() - point3D.getX().get();
        double subY = this.getY().get() - point3D.getY().get();
        double subZ = this.getZ().get() - point3D.getZ().get();

        double powSubX = subX * subX;
        double powSubY = subY * subY;
        double powSubZ = subZ * subZ;

        double squared = powSubX + powSubY + powSubZ;
        return  squared;
    }

    public double distance(Point3D point3D){
        double squaredRoot = Math.sqrt(distanceSquared(point3D));
        return  squaredRoot;
    }

    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public Coordinate getZ() {
        return z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D point3D = (Point3D) obj;
        return x.equals(point3D.getX()) &&
                y.equals(point3D.getY()) &&
                z.equals(point3D.getZ());
    }

    @Override
    public String toString() {
        return " x: " + x + " y: " + y + " y: " + " z: " + z;
    }
}

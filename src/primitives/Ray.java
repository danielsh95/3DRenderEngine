package primitives;

public class Ray {
    Point3D POO;
    Vector direction;

    public Ray(Point3D POO, Vector direction) throws IllegalArgumentException {
        if (!CheckIsNormal(direction))
            throw new IllegalArgumentException("Error, The vector direction is not normalized!");
        this.POO = POO;
        this.direction = direction;
    }

    public Ray(Ray ray) {
        this.POO = ray.POO;
        this.direction = ray.direction;
    }

    public Point3D getPOO() {
        return POO;
    }

    public Vector getDirection() {
        return direction;
    }

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

    private boolean CheckIsNormal(Vector direction)
    {
        double powX = direction.getHead().getX().get()*direction.getHead().getX().get();
        double powY =direction.getHead().getY().get()*direction.getHead().getY().get();
        double powZ =direction.getHead().getZ().get()*direction.getHead().getZ().get();

        double square = Math.sqrt(powX+powY+powZ);

        if (square != 1)
            return false;
        return true;

    }
}

package primitives;


/**
 * This Vector class to represent Vector
 **/
public class Vector {
    private Point3D head;

    /**
     * constructor of Vector
     * Note: Vector not contain point of Vector (0,0,0)
     *
     * @param head of other Point3D
     **/
    public Vector(Point3D head) {

        if (head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Vector can't be (0,0,0)");
        }
        this.head = head;
    }

    /**
     * Constructor of Vector to object
     * Note: Vector not contain point of Vector (0,0,0)
     *
     * @param x parameter of coordinate x
     * @param y parameter of coordinate y
     * @param z parameter of coordinate z
     **/
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        if (new Point3D(x, y, z).equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Vector can't be (0,0,0)");
        }
        head = new Point3D(x, y, z);
    }

    /**
     * Copy Constructor of Vector to object
     * Note: Vector not contain point of Vector (0,0,0)
     *
     * @param vector
     **/
    public Vector(Vector vector) {
        if (vector.getHead().equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Vector can't be (0,0,0)");
        }
        this.head = vector.getHead();
    }

    /**
     * Constructor of Vector to object
     * Note: Vector not contain point of Vector (0,0,0)
     *
     * @param x parameter of x
     * @param y parameter of y
     * @param z parameter of z
     **/
    public Vector(double x, double y, double z) {
        if (new Point3D(x, y, z).equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Vector can't be (0,0,0)");
        }
        this.head = new Point3D(x, y, z);
    }

    /**
     * Get head
     *
     * @return head
     **/
    public Point3D getHead() {
        return head;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector vector = (Vector) obj;
        return head.equals(vector.head);
    }

    @Override
    public String toString() {
        return " head: " + head;

    }

    /**
     * subtract method between vector in parameter to vector of this object
     *
     * @param vector of subtracted
     * @return Vector after subtract
     **/
    public Vector subtract(Vector vector) {
        return this.head.subtract(vector.getHead());
    }

    /**
     * Add method for add Vector in parameter to point of this object
     *
     * @param vector for add
     * @return vector after Added to new object
     **/
    public Vector add(Vector vector) {
        return new Vector(this.head.add(vector));
    }

    /**
     * Gets a vector by multiplying the scalar
     *
     * @param scalar
     * @return A new vector after multiplying the scalar
     **/
    public Vector scale(double scalar) {
        double newX = this.head.getX().get() * scalar;
        double newY = this.head.getY().get() * scalar;
        double newZ = this.head.getZ().get() * scalar;

        return new Vector(newX, newY, newZ);
    }

    /**
     * Gets a new vector by making a scalar multiplication
     *
     * @param vector
     * @return double of dot product
     **/
    public double dotProduct(Vector vector) {
        double doubleX = this.head.getX().get() * vector.head.getX().get();
        double doubleY = this.head.getY().get() * vector.head.getY().get();
        double doubleZ = this.head.getZ().get() * vector.head.getZ().get();
        return doubleX + doubleY + doubleZ;
    }

    /**
     * Get a new vector that will be a vector multiplier between direction vectors
     *
     * @param vector2
     * @return A new vector that stands for two vectors
     **/
    public Vector crossProduct(Vector vector2) {

        double vector1_x = this.getHead().getX().get();
        double vector1_y = this.getHead().getY().get();
        double vector1_z = this.getHead().getZ().get();

        double vector2_x = vector2.getHead().getX().get();
        double vector2_y = vector2.getHead().getY().get();
        double vector2_z = vector2.getHead().getZ().get();


        double preI = vector1_y * vector2_z - vector1_z * vector2_y;
        double preJ = -(vector1_x * vector2_z - vector1_z * vector2_x);
        double preK = vector1_x * vector2_y - vector1_y * vector2_x;

        return new Vector(preI, preJ, preK);
    }


    /**
     * Gets the length of the vector squared
     *
     * @return double
     **/
    public double lengthSquared() {
        double x = this.head.getX().get();
        double y = this.head.getY().get();
        double z = this.head.getZ().get();

        return x * x + y * y + z * z;
    }

    /**
     * Gets the root of the vector length squared
     *
     * @return double
     **/
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Method to normalize our vector
     *
     * @return Vector of this object after normalize
     **/
    public Vector normalize() {
        double normalizeX = this.head.getX().get() / length();
        double normalizeY = this.head.getY().get() / length();
        double normalizeZ = this.head.getZ().get() / length();

        this.head = new Point3D(normalizeX, normalizeY, normalizeZ);
        return this;
    }

    /**
     * Method to Get normalize of our vector without to change him
     *
     * @return Vector of this object after normalize
     **/
    public Vector normalized() {
        return new Vector(this).normalize();
    }


}



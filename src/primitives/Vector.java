package primitives;

public class Vector {
    Point3D head;

    public Vector(Point3D head) throws IllegalArgumentException {

        if(head.equals(new Point3D(0,0,0))){
            throw new IllegalArgumentException( "Vector can't be (0,0,0)");
        }
        this.head = head;
    }
    public Vector(Coordinate x, Coordinate y, Coordinate z)throws IllegalArgumentException{
        if(new Point3D(x,y,z).equals(new Point3D( 0,0,0))) {
            throw new IllegalArgumentException( "Vector can't be (0,0,0)");
        }
        head = new Point3D(x, y, z);
    }

    public  Vector(Vector vector)throws IllegalArgumentException{
        if(vector.getHead().equals(new Point3D(0,0,0))){
            throw new IllegalArgumentException( "Vector can't be (0,0,0)");
        }
        this.head = vector.getHead();
    }
    public Vector(double x, double y, double z)throws IllegalArgumentException{
        if(new Point3D(x,y,z).equals(new Point3D(0,0,0))){
            throw new IllegalArgumentException( "Vector can't be (0,0,0)");
        }
        this.head = new Point3D(x, y, z);
    }

    public Point3D getHead() {
        return head;
    }

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
    public Vector subtract(Vector vector){
        return this.head.subtract(vector.getHead());
    }

    public Vector add(Vector vector){
        return new Vector(this.head.add(vector));
    }

    public Vector scale(double scalar) {
        double newX = this.head.getX().get() * scalar;
        double newY = this.head.getY().get() * scalar;
        double newZ = this.head.getZ().get() * scalar;

        return new Vector(newX, newY,newZ);
    }
    public double dotProduct(Vector vector){
        double doubleX = this.head.getX().get()*vector.getHead().getX().get();
        double doubleY = this.head.getY().get()*vector.getHead().getY().get();
        double doubleZ = this.head.getZ().get()*vector.getHead().getZ().get();
        double sumXYZ = doubleX + doubleY + doubleZ;
        return  sumXYZ;
    }
    public Vector crossProduct(Vector vector2) {
        Vector vector1 = this;
        double preI = vector1.getHead().getY().get() * vector2.getHead().getZ().get() -
                vector1.getHead().getZ().get() * vector2.getHead().getY().get();

        double preJ = -(vector1.getHead().getX().get() * vector2.getHead().getZ().get() -
                vector1.getHead().getZ().get() * vector2.getHead().getX().get());

        double preK = vector1.getHead().getX().get() * vector2.getHead().getY().get() -
                vector1.getHead().getY().get() * vector2.getHead().getX().get();

        return new Vector(preI, preJ, preK);

    }

    public double lengthSquared() {
        double powX = this.head.getX().get() * this.head.getX().get();
        double powY = this.head.getY().get() * this.head.getY().get();
        double powZ = this.head.getZ().get() * this.head.getZ().get();
        double length_Squared = powX + powY + powZ;
        return  length_Squared;
    }
    public double length(){
        return Math.sqrt(lengthSquared());
    }
    public Vector normalize(){
        double normalizeX = this.head.getX().get() / length();
        double normalizeY = this.head.getY().get() / length();
        double normalizeZ = this.head.getZ().get() / length();

        this.head = new Point3D(normalizeX, normalizeY,normalizeZ);
        return this;
    }

    public Vector normalized(){
        Vector newVector = new Vector(this);
        return newVector.normalize();
    }


}



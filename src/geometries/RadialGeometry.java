package geometries;

import primitives.Point3D;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {
    double _radius;
    @Override
    public Vector getNormal(Point3D point3D) {
        return null;
    }

    public RadialGeometry(double radius){
        this._radius = radius;
    }


    public RadialGeometry(RadialGeometry radialGeometry){
        this._radius = radialGeometry.getRadius();
    }

    public double getRadius() {
        return _radius;
    }
}

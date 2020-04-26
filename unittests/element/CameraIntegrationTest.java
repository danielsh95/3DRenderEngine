package element;

import static org.junit.Assert.*;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Point3D.ZERO;

import java.util.List;

/**
 * Tests for integrations of checking camera and geometry intersections
 **/
public class CameraIntegrationTest {

    /**
     * find intersections of ray that through pixels to geometry
     *
     * @param intersectable
     * @param camera
     * @return amount of intersections
     **/
    private int amountIntersectionsFromPixelsToGeometry(Intersectable intersectable, Camera camera) {
        int countOfInstrections = 0;
        int nX = 3;
        int nY = 3;

        //loop for every pixel in the view model
        List<Point3D> result;
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                result = intersectable.findIntersections(camera.constructRayThroughPixel(nX, nY, j, i,
                        1, 3, 3));
                if (result != null)//not have intersections
                    countOfInstrections += result.size();
            }
        }
        return countOfInstrections;
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view sphere model of
     * radius 2 and start from (0,0,0) point from camera (2 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestSphere1() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(new Point3D(0, 0, 3), 1);

        assertEquals("error, count of intersections is not the same excepted", 2,
                amountIntersectionsFromPixelsToGeometry(sphere, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view sphere model of
     * radius 2.5 and start from (0,0,-0.5) point from camera (18 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestSphere2() {
        Camera camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(new Point3D(0, 0, 2.5), 2.5);

        assertEquals("error, count of intersections is not the same excepted", 18,
                amountIntersectionsFromPixelsToGeometry(sphere, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view sphere model of
     * radius 2 and start from (0,0,-0.5) point from camera (10 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestSphere3() {
        Camera camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(new Point3D(0, 0, 2), 2);

        assertEquals("error, count of intersections is not the same excepted", 10,
                amountIntersectionsFromPixelsToGeometry(sphere, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view sphere model of
     * radius 4 and start from (0,0,0) point from camera (9 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestSphere4() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(new Point3D(0, 0, 1), 4);

        assertEquals("error, count of intersections is not the same excepted", 9,
                amountIntersectionsFromPixelsToGeometry(sphere, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view sphere model of
     * radius 0.5 and start from (0,0,0) point from camera (sphere is back) (0 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestSphere5() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(new Point3D(0, 0, -1), 0.5);

        assertEquals("error, count of intersections is not the same excepted", 0,
                amountIntersectionsFromPixelsToGeometry(sphere, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view plane model (9 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestPlane1() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Plane plane = new Plane(new Point3D(3, 0, 3), new Point3D(0, -3, 3), new Point3D(-3, 0, 3));

        assertEquals("error, count of intersections is not the same excepted", 9,
                amountIntersectionsFromPixelsToGeometry(plane, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view plane model (9 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestPlane2() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Plane plane = new Plane(new Point3D(3, 0, 5), new Point3D(0, 3, 6), new Point3D(-3, 0, 5));

        assertEquals("error, count of intersections is not the same excepted", 9,
                amountIntersectionsFromPixelsToGeometry(plane, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view plane model (6 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestPlane3() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Plane plane = new Plane(new Point3D(3, 0, 3), new Point3D(0, 3, 6), new Point3D(-3, 0, 3));

        assertEquals("error, count of intersections is not the same excepted", 6,
                amountIntersectionsFromPixelsToGeometry(plane, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view triangle model (1 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestTriangle1() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Triangle triangle = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));

        assertEquals("error, count of intersections is not the same excepted", 1,
                amountIntersectionsFromPixelsToGeometry(triangle, camera));
    }

    /**
     * This case test for to check the amount of ray intersections in every pixel in the view triangle model (2 intersections)
     **/
    @Test
    public void constructRayThroughPixelTestTriangle2() {
        Camera camera = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Triangle triangle = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));

        assertEquals("error, count of intersections is not the same excepted", 2,
                amountIntersectionsFromPixelsToGeometry(triangle, camera));
    }
}

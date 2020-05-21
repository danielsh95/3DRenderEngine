package element;

import static org.junit.Assert.*;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
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
     * @param intersectable geometry
     * @param camera
     * @return amount of intersections
     **/
    private int amountIntersectionsFromPixelsToGeometry(Intersectable intersectable, Camera camera) {
        int countOfInstrections = 0;
        int nX = 3;
        int nY = 3;

        //loop for every pixel in the view model
        List<GeoPoint> result;
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
     * The test checking the amount of intersections from pixels to geometry
     **/
    @Test
    public void amountConstructRayThroughPixelTest() {
        Camera camera1 = new Camera(ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera camera2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

        //This case test for to check the amount of ray intersections in every pixel in the view sphere model of
        //radius 2 and start from (0,0,0) point from camera (2 intersections)
        Sphere sphere1 = new Sphere(new Point3D(0, 0, 3), 1);
        assertEquals("error, count of intersections is not the same excepted", 2,
                amountIntersectionsFromPixelsToGeometry(sphere1, camera1));

        //This case test for to check the amount of ray intersections in every pixel in the view sphere model of
        //radius 2.5 and start from (0,0,-0.5) point from camera (18 intersections)
        Sphere sphere2 = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        assertEquals("error, count of intersections is not the same excepted", 18,
                amountIntersectionsFromPixelsToGeometry(sphere2, camera2));

        //This case test for to check the amount of ray intersections in every pixel in the view sphere model of
        //radius 2 and start from (0,0,-0.5) point from camera (10 intersections)
        Sphere sphere3 = new Sphere(new Point3D(0, 0, 2), 2);
        assertEquals("error, count of intersections is not the same excepted", 10,
                amountIntersectionsFromPixelsToGeometry(sphere3, camera2));

        //This case test for to check the amount of ray intersections in every pixel in the view sphere model of
        //radius 4 and start from (0,0,0) point from camera (9 intersections)
        Sphere sphere4 = new Sphere(new Point3D(0, 0, 1), 4);
        assertEquals("error, count of intersections is not the same excepted", 9,
                amountIntersectionsFromPixelsToGeometry(sphere4, camera1));

        //This case test for to check the amount of ray intersections in every pixel in the view sphere model of
        //radius 0.5 and start from (0,0,0) point from camera (sphere is back) (0 intersections)
        Sphere sphere5 = new Sphere(new Point3D(0, 0, -1), 0.5);
        assertEquals("error, count of intersections is not the same excepted", 0,
                amountIntersectionsFromPixelsToGeometry(sphere5, camera1));

        //This case test for to check the amount of ray intersections in every pixel in the view plane model (9 intersections)
        Plane plane1 = new Plane(new Point3D(3, 0, 3), new Point3D(0, -3, 3), new Point3D(-3, 0, 3));
        assertEquals("error, count of intersections is not the same excepted", 9,
                amountIntersectionsFromPixelsToGeometry(plane1, camera1));

        //This case test for to check the amount of ray intersections in every pixel in the view plane model (9 intersections)
        Plane plane2 = new Plane(new Point3D(3, 0, 5), new Point3D(0, 3, 6), new Point3D(-3, 0, 5));
        assertEquals("error, count of intersections is not the same excepted", 9,
                amountIntersectionsFromPixelsToGeometry(plane2, camera1));

        //This case test for to check the amount of ray intersections in every pixel in the view plane model (6 intersections)
        Plane plane3 = new Plane(new Point3D(3, 0, 3), new Point3D(0, 3, 6), new Point3D(-3, 0, 3));
        assertEquals("error, count of intersections is not the same excepted", 6,
                amountIntersectionsFromPixelsToGeometry(plane3, camera1));

        //This case test for to check the amount of ray intersections in every pixel in the view triangle model (1 intersections)
        Triangle triangle1 = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        assertEquals("error, count of intersections is not the same excepted", 1,
                amountIntersectionsFromPixelsToGeometry(triangle1, camera1));

        //This case test for to check the amount of ray intersections in every pixel in the view triangle model (2 intersections)
        Triangle triangle2 = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        assertEquals("error, count of intersections is not the same excepted", 2,
                amountIntersectionsFromPixelsToGeometry(triangle2, camera1));
    }
}

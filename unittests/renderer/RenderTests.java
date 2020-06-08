package renderer;

import org.junit.Test;

import element.*;
import geometries.*;
import primitives.*;

import scene.Scene;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(new Color(75, 127, 90));
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(new Sphere(new Point3D(0, 0, 100), 50));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    /**
     * Test building an image for a basic render
     **/
    @Test
    public void basicRenderMultiColorTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));

        scene.addGeometries(new Sphere(new Point3D(0, 0, 100), 50));

        scene.addGeometries(
                new Triangle(new Color(java.awt.Color.BLUE),
                        new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),      // lower right
                new Triangle(
                        new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),    // upper right
                new Triangle(new Color(java.awt.Color.RED),
                        new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),    // lower left
                new Triangle(new Color(java.awt.Color.GREEN),
                        new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100))); // upper left

        ImageWriter imageWriter = new ImageWriter("color render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50, java.awt.Color.WHITE);
        render.writeToImage();
    }

    /**
     * Test building an image for with some geometries with all lights
     **/
    @Test
    public void imageWithSomeGeometriesWithAllLights() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(6, 0, 2), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
        scene.setDistance(100);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.005));
        scene.addGeometries(
                new Triangle(new Material(0, 1, 0.25, 0.25, 20), new Color(java.awt.Color.darkGray),
                        new Point3D(-8, -26, 7), new Point3D(-15, -5, 7), new Point3D(-8, 13, 20)),
                new Plane(new Material(0.5, 0.01, 0.25, 0.25, 1), new Color(java.awt.Color.BLACK),
                        new Point3D(-20, 0, 0),
                        new Vector(-1, 0, 0)),
                new Triangle(new Material(1, 0, 0.25, 0.25, 20), new Color(90, 90, 9),
                        new Point3D(-4, 0, 0),
                        new Point3D(4, 0, 0),
                        new Point3D(0, -8, 0)),
                new Triangle(new Material(0.5, 0.5, 0.5, 0.5, 30), new Color(java.awt.Color.BLUE),
                        new Point3D(0, 0, 6), new Point3D(0, -12, 0), new Point3D(0, 10, 0)),
                new Tube(new Material(0, 0, 0.25, 0.8, 30), new Color(java.awt.Color.RED),
                        new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 2)), 2));

        scene.addLights(
                new SpotLight(new Color(1020, 400, 400), new Point3D(20, 50, 10),
                        new Vector(-40, -20, -10), 1, 0.00001, 0.000005),
                new PointLight(new Color(500, 300, 0), new Point3D(80, -80, 10),
                        1, 0.000001, 0.0001),
                new DirectionalLight(new Color(50, 50, 0), new Vector(7.88, -35.25, -10)));

        ImageWriter imageWriter = new ImageWriter("imageWithSomeGeometriesWithAllLights",
                500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Image with some geometries with all lights source for mini project 1
     * **/
    @Test
    public void imageMiniProject1() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(10, 0, 11), new Vector(-1, 0, 0), new Vector(0, 0, 1));
        camera.setUseManyRays(true);
        camera.setNumOfRays(30);
        scene.setCamera(camera);
        scene.setDistance(100);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.005));
        scene.addGeometries(
                new Plane(new Material(0, 1, 0, 0.8, 60), new Color(java.awt.Color.GRAY),
                        new Point3D(0, 2, 0),
                        new Vector(0, 0, 1)),

                new Plane(new Material(0, 0.5, 0, 0.8, 60), new Color(java.awt.Color.DARK_GRAY),
                        new Point3D(-20, 0, 0),
                        new Vector(1, 0, 0)),

                new Cylinder(new Material(0.1, 0, 0.25, 0.8, 30), new Color(java.awt.Color.RED),
                        5, new Ray(new Point3D(-10, 0, 0), new Vector(0, 0, 1)), 10),

                new Polygon(new Material(0.5, 0, 0.25, 0.8, 30), new Color(java.awt.Color.blue),
                        new Point3D(0, -16, 5), new Point3D(-14, -16, 5),
                        new Point3D(-14, 16, 5), new Point3D(0, 16, 5)),

                new Triangle(new Material(0, 0, 0.25, 0.8, 30), new Color(java.awt.Color.pink),
                        new Point3D(-14, -45, 5), new Point3D(-14, 45, 5),
                        new Point3D(-16, 0, 50)),

                new Tube(new Material(0, 0, 0.25, 0.8, 30), new Color(java.awt.Color.orange),
                        new Ray(new Point3D(0, 16, 5), new Vector(0, 0, 1)), 0.5),

                new Tube(new Material(0, 0, 0.25, 0.8, 30), new Color(java.awt.Color.orange),
                        new Ray(new Point3D(0, -16, 5), new Vector(0, 0, 1)), 0.5),

                new Tube(new Material(0, 0, 0.25, 0.8, 30), new Color(java.awt.Color.magenta),
                        new Ray(new Point3D(3, 7, 0), new Vector(0, -0.1, 1)), 1.5),

                new Sphere(new Material(0, 0.2, 0.25, 0.25, 0), new Color(java.awt.Color.blue),
                        new Point3D(-15, -10, 30), 13),

                new Sphere(new Material(0, 0, 0.25, 0.8, 30), new Color(java.awt.Color.green),
                        new Point3D(-8, -9, 5), 5),
                new Sphere(new Material(0, 0, 0.25, 0.8, 30), new Color(java.awt.Color.green),
                        new Point3D(-8, 9, 5), 5));

        scene.addLights(
                new SpotLight(new Color(1020, 400, 400), new Point3D(20, 50, 10),
                        new Vector(-40, -20, -10), 1, 0.00001, 0.000005),

                new PointLight(new Color(500, 300, 0), new Point3D(80, -80, 10),
                        1, 0.000001, 0.0001),

                new DirectionalLight(new Color(50, 50, 0), new Vector(7.88, -35.25, -10)));

        ImageWriter imageWriter = new ImageWriter("imageMiniProject1(2)",
                500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}


package renderer;

import org.junit.Test;

import element.*;
import geometries.*;
import org.w3c.dom.*;
import primitives.*;

import scene.Scene;
import geometries.Intersectable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Test rendering abasic image
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
     * Get attribute value from xml by element and name of attribute
     *
     * @param doc           document file
     * @param element       element name
     * @param indexElement  index of element
     * @param nameAttribute name of the attribute
     * @return value of the attribute
     */
    private String getContentFromXml(Document doc, String element, int indexElement, String nameAttribute) {
        Element e = (Element) doc.getElementsByTagName(element).item(indexElement);
        return e.getAttribute(nameAttribute);
    }

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid from file xml
     */
    @Test
    public void basicRenderTwoColorTest2() {
        Scene scene = new Scene("Test scene");
        Document doc = null;

        //load file from xml
        try {
            File file = new File("Xml\\renderTest.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            doc = dbf.newDocumentBuilder().parse(file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //build camera
        String[] strP0 = getContentFromXml(doc, "camera", 0, "P0").split(" ");
        String[] strVUp = getContentFromXml(doc, "camera", 0, "Vup").split(" ");
        String[] strVTo = getContentFromXml(doc, "camera", 0, "Vto").split(" ");

        Point3D p0 = new Point3D(Double.parseDouble(strP0[0]), Double.parseDouble(strP0[1]), Double.parseDouble(strP0[2]));
        Vector vTo = new Vector(Double.parseDouble(strVTo[0]), Double.parseDouble(strVTo[1]), Double.parseDouble(strVTo[2]));
        Vector vUp = new Vector(Double.parseDouble(strVUp[0]), Double.parseDouble(strVUp[1]), Double.parseDouble(strVUp[2]));
        scene.setCamera(new Camera(p0, vTo, vUp));

        //distance
        scene.setDistance(Double.parseDouble(getContentFromXml(doc, "scene", 0, "screen-distance")));

        //background-color
        String[] strBackground = getContentFromXml(doc, "scene", 0, "background-color").split(" ");
        Color color = new Color(Double.parseDouble(strBackground[0]), Double.parseDouble(strBackground[1]), Double.parseDouble(strBackground[2]));
        scene.setBackground(color);

        //ambient-light
        String[] strAmbientLightColor = getContentFromXml(doc, "ambient-light", 0, "color").split(" ");
        String strKa = getContentFromXml(doc, "ambient-light", 0, "ka");
        scene.setAmbientLight(new AmbientLight(new Color(Double.parseDouble(strAmbientLightColor[0]),
                Double.parseDouble(strAmbientLightColor[1]), Double.parseDouble(strAmbientLightColor[2])), Double.parseDouble(strKa)));

        //sphere geometry
        String strSphereRadius = getContentFromXml(doc, "sphere", 0, "radius");
        String[] strCenter = getContentFromXml(doc, "sphere", 0, "center").split(" ");
        scene.addGeometries(new Sphere(new Point3D(Double.parseDouble(strCenter[0]), Double.parseDouble(strCenter[1]), Double.parseDouble(strCenter[2])),
                Double.parseDouble(strSphereRadius)));

        //Triangle 1
        String[] strTriangle1P0 = getContentFromXml(doc, "triangle", 0, "p0").split(" ");
        String[] strTriangle1P1 = getContentFromXml(doc, "triangle", 0, "p1").split(" ");
        String[] strTriangle1P2 = getContentFromXml(doc, "triangle", 0, "p2").split(" ");
        scene.addGeometries(new Triangle(
                new Point3D(Double.parseDouble(strTriangle1P0[0]), Double.parseDouble(strTriangle1P0[1]), Double.parseDouble(strTriangle1P0[2])),
                new Point3D(Double.parseDouble(strTriangle1P1[0]), Double.parseDouble(strTriangle1P1[1]), Double.parseDouble(strTriangle1P1[2])),
                new Point3D(Double.parseDouble(strTriangle1P2[0]), Double.parseDouble(strTriangle1P2[1]), Double.parseDouble(strTriangle1P2[2]))));

        //Triangle 2
        String[] strTriangle2P0 = getContentFromXml(doc, "triangle", 1, "p0").split(" ");
        String[] strTriangle2P1 = getContentFromXml(doc, "triangle", 1, "p1").split(" ");
        String[] strTriangle2P2 = getContentFromXml(doc, "triangle", 1, "p2").split(" ");
        scene.addGeometries(new Triangle(
                new Point3D(Double.parseDouble(strTriangle2P0[0]), Double.parseDouble(strTriangle2P0[1]), Double.parseDouble(strTriangle2P0[2])),
                new Point3D(Double.parseDouble(strTriangle2P1[0]), Double.parseDouble(strTriangle2P1[1]), Double.parseDouble(strTriangle2P1[2])),
                new Point3D(Double.parseDouble(strTriangle2P2[0]), Double.parseDouble(strTriangle2P2[1]), Double.parseDouble(strTriangle2P2[2]))));

        //Triangle 3
        String[] strTriangle3P0 = getContentFromXml(doc, "triangle", 2, "p0").split(" ");
        String[] strTriangle3P1 = getContentFromXml(doc, "triangle", 2, "p1").split(" ");
        String[] strTriangle3P2 = getContentFromXml(doc, "triangle", 2, "p2").split(" ");
        scene.addGeometries(new Triangle(
                new Point3D(Double.parseDouble(strTriangle3P0[0]), Double.parseDouble(strTriangle3P0[1]), Double.parseDouble(strTriangle3P0[2])),
                new Point3D(Double.parseDouble(strTriangle3P1[0]), Double.parseDouble(strTriangle3P1[1]), Double.parseDouble(strTriangle3P1[2])),
                new Point3D(Double.parseDouble(strTriangle3P2[0]), Double.parseDouble(strTriangle3P2[1]), Double.parseDouble(strTriangle3P2[2]))));

        //Triangle 4
        String[] strTriangle4P0 = getContentFromXml(doc, "triangle", 3, "p0").split(" ");
        String[] strTriangle4P1 = getContentFromXml(doc, "triangle", 3, "p1").split(" ");
        String[] strTriangle4P2 = getContentFromXml(doc, "triangle", 3, "p2").split(" ");
        scene.addGeometries(new Triangle(
                new Point3D(Double.parseDouble(strTriangle4P0[0]), Double.parseDouble(strTriangle4P0[1]), Double.parseDouble(strTriangle4P0[2])),
                new Point3D(Double.parseDouble(strTriangle4P1[0]), Double.parseDouble(strTriangle4P1[1]), Double.parseDouble(strTriangle4P1[2])),
                new Point3D(Double.parseDouble(strTriangle4P2[0]), Double.parseDouble(strTriangle4P2[1]), Double.parseDouble(strTriangle4P2[2]))));

        //name image
        String nameOfImage = getContentFromXml(doc, "ImageWriter", 0, "name");

        //width, height, nX, nY
        double width = Double.parseDouble(getContentFromXml(doc, "scene", 0, "screen-width"));
        double height = Double.parseDouble(getContentFromXml(doc, "scene", 0, "screen-height"));
        int nX = Integer.parseInt(getContentFromXml(doc, "ImageWriter", 0, "nx"));
        int nY = Integer.parseInt(getContentFromXml(doc, "ImageWriter", 0, "ny"));
        ImageWriter imageWriter = new ImageWriter(nameOfImage, width, height, nX, nY);

        //build render from the imageWriter and scene
        Render render = new Render(imageWriter, scene);

        //create image
        render.renderImage();
        int interval = Integer.parseInt(getContentFromXml(doc, "ImageWriter", 0, "interval"));
        render.printGrid(interval, java.awt.Color.YELLOW);
        render.writeToImage();
    }
}


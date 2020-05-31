/**
 * Daniel Shtinmez - 205433683
 * Yehuda Zohar - 207320797
 **/

import element.AmbientLight;
import element.Camera;
import element.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import static java.lang.System.out;
import static primitives.Util.*;

/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

    /**
     * @param args irrelevant here
     */
    public static void main(String[] args) {
        Vector v1= new Vector(1.84,-2.93,4);
        Vector v2= new Vector(-12,-13,-4);
        double v3 = v1.dotProduct(v2);
    }
}

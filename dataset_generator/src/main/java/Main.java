import Utils.Generator;
import Utils.ProcessingElement;
import View.Controls;
import View.Shape;
import View.Window;
import processing.core.PApplet;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Main.class, args);
    }

    @Override
    public void settings() {
        size(800, 800, P3D);
    }

    Window window;
    Controls controls;
    Shape boxShape, wrenchShape;
    Generator generator;

    @Override
    public void setup() {
        ProcessingElement.p = this;

        boxShape = new Shape("Box", "box.obj");
        wrenchShape = new Shape("Wrench", "key.obj");
        window = new Window(boxShape);
        generator = new Generator(window);

        controls = new Controls(boxShape, window, generator);

        controls.addShape(boxShape);
        controls.addShape(wrenchShape);
    }

    @Override
    public void draw() {
        window.show(false);
    }
}

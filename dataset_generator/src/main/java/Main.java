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

    @Override
    public void setup() {
        ProcessingElement.p = this;

        boxShape = new Shape("Box", "box.obj");
        wrenchShape = new Shape("Wrench", "key.obj");
        window = new Window(boxShape);
        controls = new Controls(boxShape, window);

        controls.addShape(boxShape);
        controls.addShape(wrenchShape);
    }

    @Override
    public void draw() {
        background(233, 196, 106);
        window.show();
        controls.show();
    }
}

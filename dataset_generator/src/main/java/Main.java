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
    Shape shape;

    @Override
    public void setup() {
        ProcessingElement.p = this;

        shape = new Shape();
        window = new Window(shape);
        controls = new Controls(shape);
    }

    @Override
    public void draw() {
        background(233, 196, 106);
        window.show();
        controls.show();
    }
}

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
    boolean hideFrame = true;
    boolean hideControls = false;

    @Override
    public void setup() {
        ProcessingElement.p = this;

        boxShape = new Shape("Box", "box.obj");
        wrenchShape = new Shape("Wrench", "key.obj");
        window = new Window(wrenchShape);
        generator = new Generator(window);

        controls = new Controls(wrenchShape, window, generator);

        controls.addShape(boxShape);
        controls.addShape(wrenchShape);
    }

    @Override
    public void draw() {
        window.show(false, hideFrame);

        if(hideControls){
            controls.hide();
        } else {
            controls.show();
        }
    }

    @Override
    public void keyPressed(){
        if(key == 'h'){
            hideControls = !hideControls;
            hideFrame = !hideFrame;
        }

        if(key == 'd'){
            wrenchShape.pos[0]++;
        }

        if(key == 'a'){
            wrenchShape.pos[0]--;
        }

        if(key == 'w'){
            wrenchShape.pos[1]--;
        }

        if(key == 's'){
            wrenchShape.pos[1]++;
        }

        if(key == 'q'){
            wrenchShape.rot[1] -= 5;
        }

        if(key == 'e'){
            wrenchShape.rot[1] += 5;
        }
    }
}

package View;

import Utils.ProcessingElement;

public class Window extends ProcessingElement {
    Shape shape;

    public Window(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void show(){
        p.lights();
        p.pushMatrix();

        p.translate(p.width/2f, p.height/2f);
        shape.show();

        p.popMatrix();
    }
}

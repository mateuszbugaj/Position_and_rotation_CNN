import processing.core.PApplet;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Main.class, args);
    }

    @Override
    public void settings() {
        size(800, 800, P3D);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        background(233, 196, 106);
    }
}

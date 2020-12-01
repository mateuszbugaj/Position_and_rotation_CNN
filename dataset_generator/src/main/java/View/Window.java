package View;

import Utils.ProcessingElement;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.HashMap;

public class Window extends ProcessingElement {
    Shape shape;
    HashMap<PVector, Integer[]> background = new HashMap<>();
    int backRectSize = 6;

    public Window(Shape shape) {
        this.shape = shape;

        calculateBackground(0);
    }

    @Override
    public void show(){
        p.lights();
        p.pushMatrix();

        p.noStroke();
        background.forEach((key, value) -> {
            p.fill(value[0], value[1], value[2], value[3]);
            p.rect(key.x, key.y, backRectSize, backRectSize);
        });

        p.translate(p.width/2f, p.height/2f);
        shape.show();

        p.popMatrix();
    }

    public void calculateBackground(float noiseLvl){
        int rows = p.width/backRectSize;
        int offset1 = (int) p.random(10_000);
        int offset2 = (int) p.random(10_000);
        int offset3 = (int) p.random(10_000);

        for (int col = 0; col <= rows; col++) {
            for (int row = 0; row <= rows; row++) {
                PVector rectPos = new PVector(col * backRectSize, row * backRectSize);
                float perlinX = rectPos.x * noiseLvl;
                float perlinY = rectPos.y * noiseLvl;
                int n1 = (int) (PApplet.map(p.noise(perlinX + offset1 * noiseLvl, perlinY + offset1 * noiseLvl), 0, 1, 0, 255));
                int n2 = (int) (PApplet.map(p.noise(perlinX + offset2 * noiseLvl, perlinY + offset2 * noiseLvl), 0, 1, 0, 255));
                int n3 = (int) (PApplet.map(p.noise(perlinX + offset3 * noiseLvl, perlinY + offset3 * noiseLvl), 0, 1, 0, 255));

                background.put(rectPos, new Integer[]{n1, n2, n3, (int)(noiseLvl*10000)});
            }
        }
    }
}

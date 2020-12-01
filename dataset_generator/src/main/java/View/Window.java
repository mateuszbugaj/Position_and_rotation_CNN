package View;

import Utils.ProcessingElement;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Arrays;
import java.util.HashMap;

public class Window extends ProcessingElement {
    public Shape shape;
    HashMap<PVector, Integer[]> background = new HashMap<>();
    int backRectSize = 6;

    public Window(Shape shape) {
        this.shape = shape;

        calculateBackground(0);
    }

    public void show(boolean generating){
        p.lights();

        p.background(255);
        p.pushMatrix();
        p.translate(p.width/2f, p.height/2f);
        shape.show();
        shape.bounding = getBoundingBox(p.get());
        p.popMatrix();

        p.background(233, 196, 106);
        p.noStroke();
        background.forEach((key, value) -> {
            p.fill(value[0], value[1], value[2], value[3]);
            p.rect(key.x, key.y, backRectSize, backRectSize);
        });

        p.pushMatrix();
        p.translate(p.width/2f, p.height/2f);
        shape.show();
        p.popMatrix();

        if(!generating){
            p.noFill();
            p.strokeWeight(5);
            p.stroke(255, 0, 0);
            p.rect(shape.bounding[0], shape.bounding[1], shape.bounding[2], shape.bounding[3]);
        }
    }

    public int[] getBoundingBox(PImage image){
        int[] boundingBox = new int[4];

        PImage blackAndWhite = image.copy();
        blackAndWhite.filter(PConstants.THRESHOLD, 1f);

        int[] pixels = blackAndWhite.pixels;
        PVector farLeft = new PVector().set(p.width, 0);
        PVector farRight = new PVector().set(0, 0);
        PVector farTop = new PVector().set(0, p.height);
        PVector farBottom = new PVector().set(0, 0);

        for (int col = 0; col < p.width; col++) {
            for (int row = 0; row < p.width; row++) {
                int pixel = pixels[col + row * p.width];

                if(pixel != -1){
                    if(col < farLeft.x){
                        farLeft.set(col, row);
                    }

                    if(col > farRight.x){
                        farRight.set(col, row);
                    }

                    if(row < farTop.y){
                        farTop.set(col, row);
                    }

                    if(row > farBottom.y){
                        farBottom.set(col, row);
                    }
                }
            }
        }

        boundingBox[0] = (int) farLeft.x;
        boundingBox[1] = (int) farTop.y;
        boundingBox[2] = (int) (farRight.x - farLeft.x);
        boundingBox[3] = (int) (farBottom.y - farTop.y);

        return boundingBox;
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

package Management;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PreviewWindow extends ProcessingElement {
    public PVector dims;
    public PImage envImage;
    public float noiseLvl = 0.005f;
    HashMap<PVector, Integer> background = new HashMap<>();
    int backRectSize = 5;

    public PreviewWindow(PVector pos, int[] color, PVector dims) {
        super(pos, color);
        this.dims = dims;

        background();
    }

    public void show(Shape mainShape, boolean generating, Shape... shapes){
        ArrayList<Shape> shapesWithMain = new ArrayList<>(Arrays.asList(shapes));
        shapesWithMain.add(mainShape);
        shapes = new Shape[shapesWithMain.size()];
        for (int i = 0; i < shapesWithMain.size(); i++) {
            shapes[i] = shapesWithMain.get(i);
        }

        p.lights();
        p.pushMatrix();
        p.translate(pos.x, pos.y);

        // show main shape on white background
        p.fill(255);
        p.noStroke();
        p.rect(0, 0,dims.x, dims.y);
        p.pushMatrix();
        p.translate(dims.x/2, dims.y/2, 200);
        p.translate(mainShape.posAndRot[0]*10, mainShape.posAndRot[1]*10, mainShape.posAndRot[2]*10);
        p.rotateX(PApplet.radians(mainShape.posAndRot[3]));
        p.rotateY(PApplet.radians(mainShape.posAndRot[4]));
        p.rotateZ(PApplet.radians(mainShape.posAndRot[5]));
        mainShape.show();
        p.popMatrix();

        PImage imageWithOnlyShape = p.get((int)pos.x, (int)pos.y, (int)dims.x, (int)dims.y);

        p.fill(color[0], color[1], color[2]);
        background.forEach((key, value) -> {
            p.fill(value);
            p.rect(key.x, key.y, backRectSize, backRectSize);
        });

        p.pushMatrix();
        p.translate(dims.x/2, dims.y/2, 200);

        for (Shape shape : shapes) {
            p.pushMatrix();
            p.translate(shape.posAndRot[0] * 10, shape.posAndRot[1] * 10, shape.posAndRot[2] * 10);
            p.rotateX(PApplet.radians(shape.posAndRot[3]));
            p.rotateY(PApplet.radians(shape.posAndRot[4]));
            p.rotateZ(PApplet.radians(shape.posAndRot[5]));
            shape.show();
            p.popMatrix();

            if (shape.equals(mainShape)) {
                envImage = p.get(10, 110, (int)dims.x, (int)dims.y);
            }
        }

        p.popMatrix();

        // draw bounding box around main shape
        if(!generating){
            p.strokeWeight(1);
            p.stroke(255, 0, 0);
            p.pushMatrix();
//            p.translate(dims.x/2, dims.y/2);
            p.fill(0);
            p.text("[bx: " +(int) mainShape.boundingBoxPos.x + ", by:" +(int) mainShape.boundingBoxPos.y + ", bw:" +(int) mainShape.boundingBoxDim.x + ", bh:" +(int) mainShape.boundingBoxDim.y + "]", mainShape.boundingBoxPos.x, mainShape.boundingBoxPos.y - 20);
            p.noFill();
            p.rect(mainShape.boundingBoxPos.x, mainShape.boundingBoxPos.y, mainShape.boundingBoxDim.x, mainShape.boundingBoxDim.y);
            p.popMatrix();
        }

        // preview images
        p.pushMatrix();
        int prevImageSize = 100;
        p.translate(10, dims.y - prevImageSize - 10);

        // original
        imageWithOnlyShape.resize(prevImageSize, prevImageSize);

        if(!generating){
            p.image(imageWithOnlyShape, 0,10);
        }

        PImage imageWithOnlyShapeCopy = imageWithOnlyShape.copy();

        // black and white
        imageWithOnlyShapeCopy.filter(PConstants.THRESHOLD, 1f);
        if(!generating){
            p.image(imageWithOnlyShapeCopy, prevImageSize + 10, 10);
        }

        int[] pixels = imageWithOnlyShapeCopy.pixels;
        PVector farLeft = new PVector().set(prevImageSize, 0);
        PVector farRight = new PVector().set(0, 0);
        PVector farTop = new PVector().set(0, prevImageSize);
        PVector farBottom = new PVector().set(0, 0);

        for (int col = 0; col < prevImageSize; col++) {
            for (int row = 0; row < prevImageSize; row++) {
                int pixel = pixels[col + row * prevImageSize];

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

        // bounding box pos and dim
        PVector boundingBoxPos = new PVector(farLeft.x, farTop.y);
        PVector boundingBoxDim = new PVector((farRight.x - farLeft.x)*1.1f, (farBottom.y - farTop.y)*1.1f);

        mainShape.boundingBoxDim.set(boundingBoxDim.copy().mult(dims.x/prevImageSize));
        mainShape.boundingBoxPos.x = p.map(boundingBoxPos.x, 0, prevImageSize, 0, dims.x);
        mainShape.boundingBoxPos.y = p.map(boundingBoxPos.y, 0, prevImageSize, 0, dims.y);

        // show bounding box on black and white prev image
        if(!generating){
            p.noFill();
            p.strokeWeight(1);
            p.stroke(255, 0, 0);
            p.pushMatrix();
            p.translate(10 + prevImageSize, 10);
            p.rect(boundingBoxPos.x, boundingBoxPos.y, boundingBoxDim.x, boundingBoxDim.y);
            p.popMatrix();
        }

        p.popMatrix();

        p.popMatrix();
        p.noLights();
    }

    public void background(){
        int rows = 800/backRectSize;
        int offset = (int) p.random(10_000);
        for (int col = 0; col <= rows; col++) {
            for (int row = 0; row <= rows; row++) {
                PVector rectPos = new PVector(col * backRectSize, row * backRectSize);
                float perlinX = rectPos.x * noiseLvl + offset*noiseLvl;
                float perlinY = rectPos.y * noiseLvl + offset*noiseLvl;
                int n = (int) (p.map(p.noise(perlinX, perlinY), 0, 1, 200, 250));;
                background.put(rectPos, n);
            }
        }
    }
}

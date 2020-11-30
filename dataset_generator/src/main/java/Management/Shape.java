package Management;

import Management.ProcessingElement;
import processing.core.PShape;
import processing.core.PVector;

public class Shape  extends ProcessingElement {
    public PShape content;
    public int scale = 10;
    public int[] posAndRot = new int[6];
    public String dir;
    public PVector boundingBoxPos = new PVector();
    public PVector boundingBoxDim = new PVector();

    public Shape(PVector pos, int[] color, String dir) {
        super(pos, color);
        content = p.loadShape(dir);
        content.setFill(color.length == 4?p.color(color[0], color[1], color[2], color[3]):p.color(color[0], color[1], color[2]));
        this.dir = dir;
    }

    public void show(){
        p.pushMatrix();
        p.scale(scale);
        p.shape(content);
        p.popMatrix();
    }

    public void randomizePos(){
        int z = (int) p.random(15, 30);
        int upperBound = (int) p.map(z, 15, 30, 25, 12);
        int x = (int) p.random(-upperBound, upperBound);
        int y = (int) p.random(-upperBound, upperBound);

        posAndRot[0] = x;
        posAndRot[1] = y;
        posAndRot[2] = z;
    }

    public void randomizeRot(){
        posAndRot[3] = (int) p.random(0, 90);
        posAndRot[4] = (int) p.random(0, 90);
        posAndRot[5] = (int) p.random(0, 90);
    }

    public void setContent(String dir){
        content = p.loadShape(dir);
        this.dir = dir;
    }

    public void setContentAndScale(String dir, int scale){
        setContent(dir);
        this.scale = scale;
    }


}

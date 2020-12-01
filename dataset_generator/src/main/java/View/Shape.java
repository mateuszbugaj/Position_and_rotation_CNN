package View;

import Utils.ProcessingElement;
import processing.core.PApplet;
import processing.core.PShape;

public class Shape extends ProcessingElement {
    public PShape content;
    public int scale = 10;
    public float[] pos = new float[3];
    public float[] rot = new float[3]; // todo: change to quaternions with float[4]
    public String name;

    public Shape(String name, String dir) {
        this.name = name;
        content = p.loadShape(dir);
        content.setFill(p.color(231, 111, 81));
    }

    public void show() {
        p.translate(pos[0] * 10, pos[1] * 10, pos[2] * 10);
        p.pushMatrix();
        p.rotateX(PApplet.radians(rot[0]));
        p.rotateY(PApplet.radians(rot[1]));
        p.rotateZ(PApplet.radians(rot[2]));
        p.scale(10);

        p.shape(content);

        p.popMatrix();
    }

    public void randomizePos(){
        int z = (int) p.random(15, 30);
        int upperBound = (int) PApplet.map(z, 15, 30, 25, 12);
        int x = (int) p.random(-upperBound, upperBound);
        int y = (int) p.random(-upperBound, upperBound);

        pos[0] = x;
        pos[1] = y;
        pos[2] = z;
    }

    public void randomizeRot(){
        rot[0] = (int) p.random(0, 90);
        rot[1] = (int) p.random(0, 90);
        rot[2] = (int) p.random(0, 90);
    }

    public void setPosX(float value){
        pos[0] = value;
    }

    public void setPosY(float value){
        pos[1] = value;
    }

    public void setPosZ(float value){
        pos[2] = value;
    }

    public void setRotX(float value){
        rot[0] = value;
    }

    public void setRotY(float value){
        rot[1] = value;
    }

    public void setRotZ(float value){
        rot[2] = value;
    }
}

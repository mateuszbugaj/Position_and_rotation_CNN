package View;

import Utils.ProcessingElement;
import processing.core.PApplet;
import processing.core.PShape;

public class Shape extends ProcessingElement {
    public PShape content;
    public int scale = 10;
    public int[] pos = new int[3];
    public int[] rot = new int[3];
    public int[] bounding = new int[3];
    public String name;

    public Shape(String name, String dir) {
        this.name = name;
        content = p.loadShape(dir);
        content.setFill(p.color(231, 111, 81));
    }

    public void show() {
        p.translate(pos[0] * 10, pos[1] * 10, pos[2] * 10);
        p.pushMatrix();

//        p.applyMatrix(1 - 2*((float)Math.pow(q[2], 2) + (float)Math.pow(q[3], 2)), 2*(q[1] * q[2] - q[0] * q[3]), 2 * (q[0] * q[2] + q[1] * q[3]), 0,
//                2 * (q[1] * q[2] + q[0] * q[3]), 1 - 2 * ((float)Math.pow(q[1], 2) + (float)Math.pow(q[3], 2)), 2 * (q[2] * q[3] - q[0] * q[1]), 0,
//                2 * (q[1] * q[3] - q[0] * q[2]), 2 * (q[0] * q[1] + q[2] * q[3]), 1 - 2 * ((float)Math.pow(q[1], 2) + (float)Math.pow(q[2], 2)), 0,
//                0, 0, 0, 1);

        p.rotateX(PApplet.radians(rot[0]));
        p.rotateY(PApplet.radians(rot[1]));
        p.rotateZ(PApplet.radians(rot[2]));
        p.scale(10);

        p.shape(content);

        p.popMatrix();
    }

    public void randomizePos(){
        int z = (int) p.random(20, 40);
        int upperBound = (int) PApplet.map(z, 20, 40, 25, 12);
        int x = (int) p.random(-upperBound, upperBound);
        int y = (int) p.random(-upperBound, upperBound);

        pos[0] = x;
        pos[1] = y;
        pos[2] = z;
    }

    public void randomizeRot(){
        rot[0] = (int) p.random(0, 360);
        rot[1] = (int) p.random(0, 360);
        rot[2] = (int) p.random(0, 90);
    }

    public void setPosX(int value){
        pos[0] = value;
    }

    public void setPosY(int value){
        pos[1] = value;
    }

    public void setPosZ(int value){
        pos[2] = value;
    }

    public void setRotX(int value){
        rot[0] = value;
    }

    public void setRotY(int value){
        rot[1] = value;
    }

    public void setRotZ(int value){
        rot[2] = value;
    }
}

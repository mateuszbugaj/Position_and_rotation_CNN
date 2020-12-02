package Utils;

import View.Shape;

public class ShapeJSON {
    public int index;
    public int posX;
    public int posY;
    public int posZ;
    public int rotX;
    public int rotY;
    public int rotZ;
    public int bx;
    public int by;
    public int bh;
    public int bw;

    public ShapeJSON(int index, Shape shape){
        this.index = index;
        posX = shape.pos[0];
        posY = shape.pos[1];
        posZ = shape.pos[2];
        rotX = shape.rot[0];
        rotY = shape.rot[1];
        rotZ = shape.rot[2];

        bx = shape.bounding[0];
        by = shape.bounding[1];
        bw = shape.bounding[2];
        bh = shape.bounding[3];
    }
}

package Utils;

import View.Shape;

public class ShapeJSON {
    public int index;
    public int posX;
    public int posY;
    public int posZ;
//    public float q0;
//    public float q1;
//    public float q2;
//    public float q3;
    public float rotX;
    public float rotY;
    public float rotZ;
    public int bx;
    public int by;
    public int bh;
    public int bw;

    public ShapeJSON(int index, Shape shape){
        this.index = index;
        posX = shape.pos[0];
        posY = shape.pos[1];
        posZ = shape.pos[2];
//        q0 = shape.q[0];
//        q1 = shape.q[1];
//        q2 = shape.q[2];
//        q3 = shape.q[3];

        rotX = shape.q[0];
        rotY = shape.q[1];
        rotZ = shape.q[2];

        bx = shape.bounding[0];
        by = shape.bounding[1];
        bw = shape.bounding[2];
        bh = shape.bounding[3];
    }
}

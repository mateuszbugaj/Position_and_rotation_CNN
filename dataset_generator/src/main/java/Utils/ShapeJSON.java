package Utils;

import View.Shape;

public class ShapeJSON {
    public int index;
    public int posX;
    public int posY;
    public int posZ;
    public float rotX;
    public float rotY;
    public float rx, gx, bxx;
    public float ry, gy, byy;
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
        float[] rgbX = AngleConverter.getRGB(shape.rot[0]);
        float[] rgbY = AngleConverter.getRGB(shape.rot[1]);

        rx = rgbX[0];
        gx = rgbX[1];
        bxx = rgbX[2];

        ry = rgbY[0];
        gy = rgbY[1];
        byy = rgbY[2];

        bx = shape.bounding[0];
        by = shape.bounding[1];
        bw = shape.bounding[2];
        bh = shape.bounding[3];
    }
}

package Management;

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
        posX = shape.posAndRot[0];
        posY = shape.posAndRot[1];
        posZ = shape.posAndRot[2];
        rotX = shape.posAndRot[3];
        rotY = shape.posAndRot[4];
        rotZ = shape.posAndRot[5];

        bx = (int) shape.boundingBoxPos.x;
        by = (int) shape.boundingBoxPos.y;
        bw = (int) shape.boundingBoxDim.x;
        bh = (int) shape.boundingBoxDim.y;
    }
}

package UI;

import Management.ProcessingElement;
import controlP5.ControlP5;
import processing.core.PVector;

public class SliderUI extends ProcessingElement {
    ControlP5 cp5;
    float posX;
    float posY;
    float posZ;
    float rotX;
    float rotY;
    float rotZ;
    public float noiseLvl;

    public int[] posAndRot = new int[6];

    public SliderUI(PVector pos, int[] color) {
        super(pos, color);
        cp5 = new ControlP5(p);

        cp5.addSlider("posX")
                .setRange(-25, 25)
                .setSize(100, 20)
                .setNumberOfTickMarks(51)
                .showTickMarks(false)
                .setPosition(pos.x,pos.y)
                .addListener(controlEvent -> setPosX(controlEvent.getValue()));

        cp5.addSlider("posY")
                .setRange(-25, 25)
                .setSize(100, 20)
                .setNumberOfTickMarks(51)
                .showTickMarks(false)
                .setPosition(pos.x, pos.y + 30)
                .addListener(controlEvent -> setPosY(controlEvent.getValue()));

        cp5.addSlider("posZ")
                .setRange(-15, 50)
                .setSize(100, 20)
                .setNumberOfTickMarks(51)
                .showTickMarks(false)
                .setPosition(pos.x, pos.y + 60)
                .addListener(controlEvent -> setPosZ(controlEvent.getValue()));

        cp5.addSlider("rotX")
                .setRange(0, 90)
                .setSize(100, 20)
                .setNumberOfTickMarks(21)
                .showTickMarks(false)
                .setPosition(pos.x + 130, pos.y)
                .addListener(controlEvent -> setRotX(controlEvent.getValue()));

        cp5.addSlider("rotY")
                .setRange(0, 90)
                .setSize(100, 20)
                .setNumberOfTickMarks(21)
                .showTickMarks(false)
                .setPosition(pos.x + 130, pos.y + 30)
                .addListener(controlEvent -> setRotY(controlEvent.getValue()));

        cp5.addSlider("rotZ")
                .setRange(0, 90)
                .setSize(100, 20)
                .setNumberOfTickMarks(21)
                .showTickMarks(false)
                .setPosition(pos.x + 130, pos.y + 60)
                .addListener(controlEvent -> setRotZ(controlEvent.getValue()));

        cp5.addSlider("noiseLvl")
                .setLabel("Noise")
                .setRange(0, 0.04f)
                .setSize(20, 80)
                .setNumberOfTickMarks(21)
                .showTickMarks(false)
                .setPosition(pos.x + 260, pos.y)
                .addListener(controlEvent -> setNoiseLvl(controlEvent.getValue()));
    }



    public void setPosX(float posX) {
        this.posX = posX;
        posAndRot[0] = (int) posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
        posAndRot[1] = (int) posY;
    }

    public void setPosZ(float posZ) {
        this.posZ = posZ;
        posAndRot[2] = (int) posZ;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
        posAndRot[3] = (int) rotX;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
        posAndRot[4] = (int) rotY;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
        posAndRot[5] = (int) rotZ;
    }

    public void setNoiseLvl(float noiseLvl) {
        this.noiseLvl = noiseLvl;
    }
}

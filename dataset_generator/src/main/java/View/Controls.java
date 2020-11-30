package View;

import Utils.ProcessingElement;
import controlP5.ControlP5;

public class Controls extends ProcessingElement {
    ControlP5 cp5;

    public Controls(Shape shape) {
        cp5 = new ControlP5(p);
        cp5.setFont(p.createFont("Arial", 20));

        cp5.addSlider("posX")
                .setSize(150, 30)
                .setNumberOfTickMarks(81)
                .showTickMarks(false)
                .setPosition(10, 10)
                .setRange(-40, 40)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> shape.setPosX(i.getValue()))
                .setCaptionLabel("POS X");

        cp5.addSlider("posY")
                .setSize(150, 30)
                .setNumberOfTickMarks(81)
                .showTickMarks(false)
                .setPosition(10, 10 + 10 + 30)
                .setRange(-40, 40)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> shape.setPosY(i.getValue()))
                .setCaptionLabel("POS Y");

        cp5.addSlider("posZ")
                .setSize(150, 30)
                .setNumberOfTickMarks(41)
                .showTickMarks(false)
                .setPosition(10, 10 + (10 + 30) * 2)
                .setRange(0, 40)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> shape.setPosZ(i.getValue()))
                .setCaptionLabel("POS Z");

        cp5.addSlider("rotX")
                .setSize(150, 30)
                .setNumberOfTickMarks(37)
                .showTickMarks(false)
                .setRange(0, 360)
                .setPosition(10, 10 + (10 + 30) * 3)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> shape.setRotX(i.getValue()))
                .setCaptionLabel("ROT X");

        cp5.addSlider("rotY")
                .setSize(150, 30)
                .setNumberOfTickMarks(37)
                .showTickMarks(false)
                .setRange(0, 360)
                .setPosition(10, 10 + (10 + 30) * 4)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> shape.setRotY(i.getValue()))
                .setCaptionLabel("ROT Y");

        cp5.addSlider("rotZ")
                .setSize(150, 30)
                .setNumberOfTickMarks(37)
                .showTickMarks(false)
                .setRange(0, 360)
                .setPosition(10, 10 + (10 + 30) * 5)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> shape.setRotZ(i.getValue()))
                .setCaptionLabel("ROT Z");
    }

    @Override
    public void show() {

    }
}

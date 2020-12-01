package View;

import Utils.ProcessingElement;
import controlP5.ControlP5;
import controlP5.DropdownList;

import java.util.ArrayList;
import java.util.List;

public class Controls extends ProcessingElement {
    ControlP5 cp5;
    DropdownList dropdownList;
    List<Shape> shapes = new ArrayList<>();

    public Controls(Shape shape, Window window) {
        cp5 = new ControlP5(p);
        cp5.setFont(p.createFont("Arial", 18));

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

        int defaultNumImages = 10;

        cp5.addTextfield("numImages")
                .setPosition(10, 10 + (10 + 30) * 6)
                .setSize(150, 30)
                .setValue(defaultNumImages)
                .setText(String.valueOf(defaultNumImages))
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setCaptionLabel("");

        cp5.addLabel("NUM")
                .setColor(0)
                .setPosition(10 + 150, 10 + (10 + 30) * 6);

        String defaultDirectory = "/images";

        cp5.addTextfield("imgDir")
                .setPosition(10, 10 + (10 + 30) * 7)
                .setSize(150, 30)
                .setValue(defaultDirectory)
                .setText(defaultDirectory)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setCaptionLabel("");

        cp5.addLabel("DIRECTORY")
                .setColor(0)
                .setPosition(10 + 150, 10 + (10 + 30) * 7);

        cp5.addToggle("RandRot")
                .setPosition(10, 10 + (10 + 30) * 8)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setLabel("")
                .setSize(150, 30)
                .setState(true);

        cp5.addLabel("RANDOM ROTATION")
                .setColor(0)
                .setPosition(10 + 150, 10 + (10 + 30) * 8);

        cp5.addToggle("RandPos")
                .setPosition(10, 10 + (10 + 30) * 9)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setLabel("")
                .setSize(150, 30)
                .setState(true);

        cp5.addLabel("RANDOM POSITION")
                .setColor(0)
                .setPosition(10 + 150, 10 + (10 + 30) * 9);

        cp5.addSlider("noise")
                .setSize(150, 30)
                .setNumberOfTickMarks(11)
                .showTickMarks(false)
                .setRange(0, 10)
                .setPosition(10, 10 + (10 + 30) * 10)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> window.calculateBackground(i.getValue()/1000))
                .setCaptionLabel("NOISE LEVEL");

        cp5.addButton("Generate")
                .setPosition(10, 10 + (10 + 30) * 11)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setLabel("GENERATE")
                .setSize(150, 30);

        dropdownList = cp5.addDropdownList("shapes")
                .setPosition(10, 10 + (10 + 30) * 12)
                .setWidth(150)
                .setBarHeight(30)
                .setItemHeight(30)
                .setLabel("SHAPES")
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83));

    }

    @Override
    public void show() {

    }

    public void addShape(Shape shape){
        shapes.add(shape);
        dropdownList.addItem(shape.name, dropdownList.getItems().size());
    }
}

package View;

import Utils.Generator;
import Utils.ProcessingElement;
import controlP5.ControlP5;
import controlP5.DropdownList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controls extends ProcessingElement {
    ControlP5 cp5;
    DropdownList dropdownList;
    List<Shape> shapes = new ArrayList<>();
    int numberOfImages = 10;
    String directory = "images/";
    boolean randomRotation, randomPosition;
    float noiseLevel;

    public Controls(Shape shape, Window window, Generator generator) {

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
                .addListener(i -> shape.setPosX((int)i.getValue()))
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
                .addListener(i -> shape.setPosY((int)i.getValue()))
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
                .addListener(i -> shape.setPosZ((int)i.getValue()))
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
                .addListener(i -> shape.setRotX((int)i.getValue()))
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
                .addListener(i -> shape.setRotY((int)i.getValue()))
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
                .addListener(i -> shape.setRotZ((int)i.getValue()))
                .setCaptionLabel("ROT Z");

        cp5.addTextfield("numImages")
                .setPosition(10, 10 + (10 + 30) * 6)
                .setSize(150, 30)
                .setValue(numberOfImages)
                .setText(String.valueOf(numberOfImages))
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .addListener(i -> setNumberOfImages((int) i.getValue()))
                .setCaptionLabel("");

        cp5.addLabel("NUM")
                .setColor(0)
                .setPosition(10 + 150, 10 + (10 + 30) * 6);

        cp5.addTextfield("imgDir")
                .setPosition(10, 10 + (10 + 30) * 7)
                .setSize(150, 30)
                .setValue(directory)
                .setText(directory)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .addListener(i -> setDirectory(i.getStringValue()))
                .setAutoClear(false)
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
                .addListener(i -> setRandomRotation((int) i.getValue() == 1))
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
                .addListener(i -> setRandomPosition((int) i.getValue() == 1))
                .setState(true);

        cp5.addLabel("RANDOM POSITION")
                .setColor(0)
                .setPosition(10 + 150, 10 + (10 + 30) * 9);

        cp5.addSlider("noise")
                .setSize(150, 30)
                .setNumberOfTickMarks(21)
                .showTickMarks(false)
                .setRange(0, 20)
                .setPosition(10, 10 + (10 + 30) * 10)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setColorCaptionLabel(p.color(0))
                .addListener(i -> {
                    window.calculateBackground(i.getValue() / 1000);
                    setNoiseLevel(i.getValue() / 1000);
                })
                .setCaptionLabel("NOISE LEVEL");

        cp5.addButton("Generate")
                .setPosition(10, 10 + (10 + 30) * 11)
                .setColorForeground(p.color(42, 157, 143))
                .setColorActive(p.color(42, 157, 143))
                .setColorBackground(p.color(38, 70, 83))
                .setLabel("GENERATE")
                .addListener(i -> {
                    try {
                        generator.generate(directory, numberOfImages, randomRotation, randomPosition, noiseLevel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
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

    public void addShape(Shape shape){
        shapes.add(shape);
        dropdownList.addItem(shape.name, dropdownList.getItems().size());
    }

    public void setNumberOfImages(int numberOfImages) {
        this.numberOfImages = numberOfImages;
    }

    public void setDirectory(String directory) {
        directory = directory.endsWith("/")?directory:directory.concat("/");
        this.directory = directory;
    }

    public void setRandomRotation(boolean randomRotation) {
        this.randomRotation = randomRotation;
    }

    public void setRandomPosition(boolean randomPosition) {
        this.randomPosition = randomPosition;
    }

    public void setNoiseLevel(float noiseLevel) {
        this.noiseLevel = noiseLevel;
    }
}

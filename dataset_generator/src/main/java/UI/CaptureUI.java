package UI;

import Commands.ChangeShapeCommand;
import Commands.GenerateCommand;
import Management.ProcessingElement;
import controlP5.*;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class CaptureUI extends ProcessingElement {
    ControlP5 cp5;
    int numImages = 10;
    String directory = "C:\\Users\\Mateusz\\Desktop\\Object_localization";
    boolean randomizeRot = true;
    boolean randomizePos = true;
    Textlabel label;
    List<String > shapes = new ArrayList<>();
    DropdownList shapesDropdown;


    public CaptureUI(PVector pos, int[] color, GenerateCommand generate, ChangeShapeCommand changeShapeCommand) {
        super(pos, color);
        cp5 = new ControlP5(ProcessingElement.p);

        cp5.addButton("Generate")
                .setPosition(pos.x, pos.y)
                .setSize(50, 20)
                .addListener(event -> {
                    generate.setNumImagesAndDir(numImages, directory);
                    generate.setRandomizePosAndRot(randomizePos, randomizeRot);
                    generate.execute();
                });

        cp5.addTextfield("Images")
                .setPosition(pos.x, pos.y + 30)
                .setSize(50, 20)
                .setValue(numImages)
                .addListener(event -> setNumImages(Integer.parseInt(event.getStringValue())));

        cp5.addTextfield("Directory")
                .setPosition(pos.x, pos.y + 60)
                .setSize(50, 20)
                .setValue(directory)
                .addListener(event -> {
                    String directory = event.getStringValue();
                    setDirectory(directory);
//                    generate.setFileName(directory);
                });

        label = cp5.addLabel("GenerateDesc")
                .setPosition(pos.x + 60, pos.y+ 5)
                .setSize(50, 20)
                .setText(numImages + " images in " + directory);

        cp5.addToggle("Rand rot")
                .setPosition(pos.x + 60, pos.y+ 30)
                .setSize(20, 20)
                .setState(true)
                .addListener(event -> randomizeRot=!randomizeRot);

        cp5.addToggle("Rand pos")
                .setPosition(pos.x + 110, pos.y+ 30)
                .setSize(20, 20)
                .setState(true)
                .addListener(event -> randomizePos=!randomizePos);

        shapesDropdown = cp5.addDropdownList("shapes")
                .setPosition(pos.x + 60, pos.y + 70)
                .setBackgroundColor(200)
                .setItemHeight(20)
                .setBarHeight(15)
                .addListener(new ControlListener() {
                    @Override
                    public void controlEvent(ControlEvent controlEvent) {
                        if(controlEvent.isController()){
                            changeShapeCommand.setDir(shapes.get((int) controlEvent.getController().getValue()));
                            changeShapeCommand.execute();
                        }
                    }
                });

        shapesDropdown.getCaptionLabel().set("Shapes");

    }

    public void addShape(String dir){
        shapes.add(dir);
        shapesDropdown.addItem(dir, shapes.size());

    }

    public void setNumImages(int numImages) {
        this.numImages = numImages;
        label.setText(numImages + " images in " + directory);
    }

    public void setDirectory(String directory) {
        this.directory = directory;

        label.setText(numImages + " images in " + directory);
    }
}

package UI;

import Commands.LoadModelCommand;
import Commands.PredictCommand;
import Management.ProcessingElement;
import controlP5.ControlP5;
import controlP5.Textlabel;
import processing.core.PImage;
import processing.core.PVector;

public class NeuralNetworkUI extends ProcessingElement {
    ControlP5 cp5;
    private String modelName = "model_1";
    Textlabel modelLabel;
    Textlabel posLabel;
    Textlabel rotLabel;
    int[] posAndRot = new int[6];
    public PImage imageToPredict;

    public NeuralNetworkUI(PVector pos, int[] color, LoadModelCommand loadModelCommand, PredictCommand predictCommand) {
        super(pos, color);
        cp5 = new ControlP5(ProcessingElement.p);

        cp5.addButton("Predict")
                .setPosition(pos.x, pos.y)
                .setSize(50, 20)
                .addListener(event -> {
                    predictCommand.execute();
                });

        cp5.addTextfield("Model")
                .setPosition(pos.x, pos.y + 30)
                .setSize(50, 20)
                .addListener(event -> setModelName(event.getStringValue()));

        cp5.addButton("Load")
                .setPosition(pos.x, pos.y+60)
                .setSize(50, 20)
                .addListener(event -> {
                    loadModelCommand.setModelName(modelName);
                    loadModelCommand.execute();
                });

        modelLabel = cp5.addLabel("modelLabel")
                .setPosition(pos.x + 60, pos.y+60)
                .setSize(50, 20)
                .setText(modelName);

        posLabel = cp5.addLabel("posLabel")
                .setPosition(pos.x + 50, pos.y+5)
                .setSize(50, 20)
                .setText("Pos: [" + posAndRot[0] + ", " + posAndRot[1] + ", "+ posAndRot[2] + "]");

        rotLabel = cp5.addLabel("rotLabel")
                .setPosition(pos.x + 50, pos.y+35)
                .setSize(50, 20)
                .setText("Rot: [" + posAndRot[3] + ", " + posAndRot[4] + ", "+ posAndRot[5] + "]");


    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
        modelLabel.setText(modelName);
    }

    public void setImageToPredict(PImage imageToPredict) {
        this.imageToPredict = imageToPredict;
    }

    public void showImage(){
        if(imageToPredict != null){
            imageToPredict.resize(80, 80);
            p.image(imageToPredict, pos.x + 120, pos.y);

        }
    }

    public void setPosAndRot(int[] posAndRot) {
        this.posAndRot = posAndRot;
        posLabel.setText("[" + posAndRot[0] + ", " + posAndRot[1] + ", "+ posAndRot[2] + "]");
        rotLabel.setText("[" + posAndRot[3] + ", " + posAndRot[4] + ", "+ posAndRot[5] + "]");
    }
}

package Management;

import Commands.ChangeShapeCommand;
import Commands.GenerateCommand;
import Commands.LoadModelCommand;
import Commands.PredictCommand;
import UI.CaptureUI;
import UI.NeuralNetworkUI;
import UI.SliderUI;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Main.class, args);
    }

    @Override
    public void settings() {
        size(800, 1000, P3D);
    }

    PreviewWindow previewWindow;
    SliderUI sliderUI;
    CaptureUI captureUI;
    Shape shape, predictedShape;
    GenerateCommand generateCommand;
    ChangeShapeCommand changeShapeCommand;
    NeuralNetworkUI neuralNetworkUI;
    NeuralNetwork neuralNetwork;

    @Override
    public void setup() {
        ProcessingElement.p = this;
        GenerateCommand.p = this;

        previewWindow = new PreviewWindow(new PVector(0, 110), new int[]{209, 209, 209}, new PVector(800, 800));
        sliderUI = new SliderUI(new PVector(10, 10), new int[]{0, 0, 0});
        shape = new Shape(new PVector(), new int[]{67, 184, 13}, "box.obj");
        predictedShape = new Shape(new PVector(), new int[]{212, 62, 21, 50}, shape.dir);

        generateCommand = new GenerateCommand(previewWindow);
        changeShapeCommand = new ChangeShapeCommand(shape);

        captureUI = new CaptureUI(new PVector(300, 10), new int[]{0, 0, 0}, generateCommand, changeShapeCommand);
        captureUI.addShape("box.obj");
        captureUI.addShape("key.obj");
        generateCommand.setShape(shape);

        neuralNetwork = new NeuralNetwork();
        LoadModelCommand loadModelCommand = new LoadModelCommand(neuralNetwork);
        PredictCommand predictCommand = new PredictCommand(neuralNetwork, predictedShape, previewWindow);
        neuralNetworkUI = new NeuralNetworkUI(new PVector(500, 10), new int[]{0, 0, 0}, loadModelCommand, predictCommand);
        predictCommand.setNeuralNetworkUI(neuralNetworkUI);
    }

    @Override
    public void draw() {
        background(160);

        if(previewWindow.noiseLvl != sliderUI.noiseLvl){
            previewWindow.noiseLvl = sliderUI.noiseLvl;
            previewWindow.background();
        }
        previewWindow.show(shape, false); // predictedShape
        shape.posAndRot = sliderUI.posAndRot;
        neuralNetworkUI.showImage();
    }
}

package Commands;

import Management.NeuralNetwork;
import Management.PreviewWindow;
import Management.ProcessingElement;
import Management.Shape;
import UI.NeuralNetworkUI;
import processing.core.PImage;
import processing.core.PVector;

public class PredictCommand implements ICommand{
    private final NeuralNetwork neuralNetwork;
    private NeuralNetworkUI neuralNetworkUI;
    private PreviewWindow previewWindow;
    private final Shape predictedShape;

    public PredictCommand(NeuralNetwork neuralNetwork, Shape predictedShape, PreviewWindow previewWindow) {
        this.neuralNetwork = neuralNetwork;
        this.predictedShape = predictedShape;
        this.previewWindow = previewWindow;
    }

    public void setNeuralNetworkUI(NeuralNetworkUI neuralNetworkUI) {
        this.neuralNetworkUI = neuralNetworkUI;
    }

    @Override
    public void execute() {
        PImage pImage = previewWindow.envImage;
        neuralNetworkUI.setImageToPredict(pImage);
        int[] predict = neuralNetwork.predict(pImage);
        predictedShape.posAndRot[0] = predict[1];
        predictedShape.posAndRot[1] = predict[0];
        predictedShape.posAndRot[2] = predict[2];

        neuralNetworkUI.setPosAndRot(predictedShape.posAndRot);
    }
}

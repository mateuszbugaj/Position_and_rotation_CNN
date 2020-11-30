package Commands;

import Management.NeuralNetwork;

public class LoadModelCommand implements ICommand{
    NeuralNetwork neuralNetwork;
    String modelName = "";

    public LoadModelCommand(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void execute() {
        neuralNetwork.loadModel(modelName);
    }
}

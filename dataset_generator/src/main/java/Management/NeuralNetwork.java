package Management;


import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PConstants;
import processing.core.PImage;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class NeuralNetwork {
    static Logger logger = LoggerFactory.getLogger(NeuralNetwork.class);
    MultiLayerNetwork model;

    public void loadModel(String modelName){
        logger.info("Loading model " + modelName);
        try {
            String modelPatch = new ClassPathResource(modelName).getFile().getPath();
            model = KerasModelImport.importKerasSequentialModelAndWeights(modelPatch);
        } catch (IOException | UnsupportedKerasConfigurationException | InvalidKerasConfigurationException e) {
            e.printStackTrace();
        }
    }

    public int[] predict(PImage image){
        image = image.copy();
        image.filter(PConstants.GRAY);
        image.resize(100, 100);

        // use PImage.filter(GRAY); you dumb fuck

        int[] pixels = image.pixels;
        int[] greyScalePixels = new int[pixels.length];
        for(int i = 0; i < pixels.length; i++){
            int c = pixels[i];
            int r=(c&0x00FF0000)>>16; // red part
            int g=(c&0x0000FF00)>>8; // green part
            int b=(c&0x000000FF); // blue part
            int grey=(r+b+g)/3;
            greyScalePixels[i] = grey;
        }

        // Global Standardization
        double mean = Arrays.stream(greyScalePixels).sum() / (double) greyScalePixels.length;
        double std = Math.sqrt((Arrays.stream(greyScalePixels).map(i -> (int) Math.pow((i -mean), 2)).sum()/(double)(greyScalePixels.length -1)));

        float[] standardized = new float[greyScalePixels.length];
        for (int i = 0; i < greyScalePixels.length; i++) {
            standardized[i] = (float) ((greyScalePixels[i] - mean) / std);
        }

        INDArray inputArray = Nd4j.create(standardized, new int[]{1, 100, 100, 1});
        int[] predict = model.output(inputArray).toIntVector();
        System.out.println("Prediction: " + Arrays.toString(predict));
        return predict;
    }

}

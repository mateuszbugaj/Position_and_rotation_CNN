package Utils;

import View.Window;
import com.fasterxml.jackson.databind.ObjectMapper;
import processing.core.PImage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Generator extends ProcessingElement{
    public Window window;
    FileWriter fileWriter;
    ObjectMapper objectMapper = new ObjectMapper();

    public Generator(Window window) {
        this.window = window;
    }

    public void generate(String dir,
                         int imageNumber,
                         boolean randomizeRotation,
                         boolean randomizePosition,
                         float noiseLevel) throws IOException {

        String fileName = dir + "data.txt";


        fileWriter = new FileWriter("images/data.txt");

        System.out.println(
                        "##############################\n" +
                        "# GENERATING\n" +
                        "# NUMBER: " + imageNumber + "\n" +
                        "# DIRECTORY: " + dir + "\n" +
                        "# RANDOM ROTATION: " + randomizeRotation + "\n" +
                        "# RANDOM POSITION: " + randomizePosition + "\n" +
                        "# NOISE LEVEL: " + noiseLevel + "\n" +
                        "# DATA FILE: " + fileName + "\n" +
                        "##############################");

        while (imageNumber-->0){
            if(randomizeRotation){
                window.shape.randomizeRot();
            }

            if(randomizePosition){
                window.shape.randomizePos();
            }

            window.calculateBackground(noiseLevel);
            window.show(true);

            PImage frame = p.get();
            String frameName = dir + imageNumber + ".jpg";
            frame.save(frameName);

            fileWriter.append(objectMapper.writeValueAsString(new ShapeJSON(imageNumber, window.shape))).append("\n");
            fileWriter.flush();

            System.out.println(imageNumber + " images left");
        }
    }


}

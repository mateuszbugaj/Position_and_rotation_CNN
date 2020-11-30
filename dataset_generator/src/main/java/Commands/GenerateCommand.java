package Commands;

import Management.PreviewWindow;
import Management.Shape;
import Management.ShapeJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateCommand implements ICommand {
    public static PApplet p;

    private int numImages;
    private String dir;
    private Shape shape;
    private boolean randomizePos = true, randomizeRot = true;
    private PreviewWindow previewWindow;
    private FileWriter fileWriter;
    private ObjectMapper objectMapper = new ObjectMapper();

    public GenerateCommand(PreviewWindow previewWindow) {
        this.previewWindow = previewWindow;

//        try {
//            fileWriter = new FileWriter("C:\\Users\\Mateusz\\Desktop\\Object_localization\\data.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void setNumImagesAndDir(int numImages, String dir){
        this.numImages = numImages;
        this.dir = dir;

        try {
            System.out.println(dir+"\\data.txt");
            fileWriter = new FileWriter(dir+"\\data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRandomizePosAndRot(boolean pos, boolean rot){
        this.randomizePos = pos;
        this.randomizeRot = rot;
    }

    public void setFileName(String fileName){
        try {
            fileWriter = new FileWriter("C:\\Users\\Mateusz\\Desktop\\Object_localization\\"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void execute() {
        while (numImages-->0){
            System.out.println("Image nr: " + numImages + " in dir " + dir);

            if(randomizePos){
                shape.randomizePos();
            }

            if(randomizeRot){
                shape.randomizeRot();
            }

            p.background(180);
            previewWindow.background();
            previewWindow.show(shape, true);
            PImage pImage = p.get(
                    (int) previewWindow.pos.x,
                    (int) previewWindow.pos.y,
                    (int) previewWindow.dims.x,
                    (int) previewWindow.dims.y);

            dir = dir.endsWith("/")?dir:dir.concat("/");
            String fileName = dir + numImages + ".jpg";

            pImage.save(fileName);

            try {
                fileWriter.append(objectMapper.writeValueAsString(new ShapeJSON(numImages, shape))).append("\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

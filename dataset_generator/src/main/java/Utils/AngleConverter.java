package Utils;

import processing.core.PApplet;

import static processing.core.PApplet.*;

public class AngleConverter {
    public static float[] getRGB(float angle){
        float[] RGBResult;
        float S = 1; // saturation 0-1
        float V = 1; // value 0-1
        float C, X, m;
        float RPrim, GPrim, BPrim;

        float H = angle;

        C = V * S;
        X = C * (1-abs((H/60)%2-1));
        m = V-C;
        RPrim = C;
        GPrim = X;
        BPrim = 0;

        if(H>=0 && H<60){
            RPrim = C;
            GPrim = X;
            BPrim = 0;
        } else if(H>=60 && H<120){
            RPrim = X;
            GPrim = C;
            BPrim = 0;
        } else if(H>=120 && H<180){
            RPrim = 0;
            GPrim = C;
            BPrim = X;
        } else if(H>=180 && H<240){
            RPrim = 0;
            GPrim = X;
            BPrim = C;
        } else if(H>=240 && H<300){
            RPrim = X;
            GPrim = 0;
            BPrim = C;
        } else if(H>=300 && H<360){
            RPrim = C;
            GPrim = 0;
            BPrim = X;
        }

        RGBResult = new float[]{(RPrim+m)*255,(GPrim+m)*255,(BPrim+m)*255};
        return RGBResult;
    }

    public static float getAngle(float[] rgb){
        float RPrim = rgb[0]/255;
        float GPrim = rgb[1]/255;
        float BPrim = rgb[2]/255;

        float angle = PApplet.atan2(sqrt(3) * (GPrim - BPrim), 2 * RPrim - GPrim - BPrim);
        angle = (degrees(angle) < 0) ? (degrees(angle) + 360):degrees(angle);

        return angle;
    }
}

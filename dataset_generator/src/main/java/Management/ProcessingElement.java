package Management;

import processing.core.PApplet;
import processing.core.PVector;

public class ProcessingElement {
    public static PApplet p;
    public PVector pos;
    public int[] color;

    public ProcessingElement(PVector pos, int[] color) {
        this.pos = pos;
        this.color = color;
    }
}

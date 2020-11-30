package Commands;

import Management.Shape;

public class ChangeShapeCommand implements ICommand {
    private final Shape shape;
    private String dir;

    public ChangeShapeCommand(Shape shape) {
        this.shape = shape;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public void execute() {
        shape.setContent(dir);
    }
}

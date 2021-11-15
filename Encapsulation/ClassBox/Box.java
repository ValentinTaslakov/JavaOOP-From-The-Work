package ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
        this.setLength(length);
    }

    private void setLength(double length) {
        isValidArgument(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
        isValidArgument(width, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
        isValidArgument(height, "Height");
        this.height = height;
    }

    private void isValidArgument(double length, String parameter) {
        if (length <= 0) {
            throw new IllegalArgumentException(parameter + " cannot be zero or negative.");
        }
    }

    public double calculateSurfaceArea() {
        return (2 * length * width) + (2 * length * height) + (2 * width * height);
    }

    public double calculateLateralSurfaceArea() {
        return (2 * length * height) + (2 * width * height);
    }

    public double calculateVolume() {
        return length * width * height;
    }
}

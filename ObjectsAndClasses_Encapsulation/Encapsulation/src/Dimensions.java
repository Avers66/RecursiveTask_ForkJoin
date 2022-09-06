public class Dimensions {
    private final int length; // мм
    private final int width; // мм
    private final int height; // мм


    public Dimensions(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double volumeCalc() {
        return (double) (length * width * height)/1000000000; //куб. м
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
       return ("Длина:  " + length + " мм" + "\n" +
               "Ширина: " + width + " мм" + "\n" +
               "Высота: " + height + " мм" + "\n" +
               "Объем: " + volumeCalc() + " куб. м");
    }


}

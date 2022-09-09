public class Monitor {
    private final int screenDiagonal;
    private final MonitorMatrixType type;
    private final double weight;

    public Monitor(int screenDiagonal, MonitorMatrixType type, double weight) {
        this.screenDiagonal = screenDiagonal;
        this.type = type;
        this.weight = weight;
    }

    public int getScreenDiagonal() {
        return screenDiagonal;
    }

    public MonitorMatrixType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Monitor: {" +
                "Screen Diagonal = " + screenDiagonal + '"' +
                ", Type = " + type +
                ", Weight = " + weight + " kg " +
                '}';
    }
}

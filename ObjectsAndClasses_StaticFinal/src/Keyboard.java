public class Keyboard {
    private final KeyboardType type;
    private final KeyboardBackLight backLight;
    private final double weight;

    public Keyboard(KeyboardType type, KeyboardBackLight backLight, double weight) {
        this.type = type;
        this.backLight = backLight;
        this.weight = weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public KeyboardBackLight getBackLight() {
        return backLight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Keyboard: {" +
                "Type = " + type +
                ", BackLight = " + backLight +
                ", Weight = " + weight + " kg " + '}';
    }
}

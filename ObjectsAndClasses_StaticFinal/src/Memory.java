public class Memory {
    private final String type;
    private final int memoryVolume;
    private final double weight;

    public Memory(String type, int volume, double weight) {
        this.type = type;
        this.memoryVolume = volume;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public int getVolume() {
        return memoryVolume;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Memory: {" +
                "Type = '" + type + '\'' +
                ", Memory Volume = " + memoryVolume + " Gb " +
                ", Weight = " + weight + " kg " +
                '}';
    }
}

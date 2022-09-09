public class CPU {
    private final int frequency;
    private final int coreNumber;
    private final CPUManufacturer manufacturer;
    private final double weight;

    public CPU(int frequency, int coreNumber, CPUManufacturer manufacturer, double weight) {
        this.frequency = frequency;
        this.coreNumber = coreNumber;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getCoreNumber() {
        return coreNumber;
    }

    public CPUManufacturer getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "CPU: {" +
                "Frequency = " + frequency + " GHz " +
                ", Core Number = " + coreNumber +
                ", Manufacturer = " + manufacturer +
                ", Weight = " + weight + " kg " +
                '}';
    }
}

public class StorageDevice {
    private final StorageDeviceType type;
    private final int storageVolume;
    private final double weight;

    public StorageDevice(StorageDeviceType type, int storageVolume, double weight) {
        this.type = type;
        this.storageVolume = storageVolume;
        this.weight = weight;
    }

    public StorageDeviceType getType() {
        return type;
    }

    public int getStorageVolume() {
        return storageVolume;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Storage Device: {" +
                "Type = " + type +
                ", Storage Volume = " + storageVolume + " Gb " +
                ", Weight = " + weight + " kg " +
                '}';
    }
}

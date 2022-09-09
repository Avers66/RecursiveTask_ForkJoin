public class Computer {
    private final ComputeVendor vendor;
    private final String computerName;
    private CPU cpu;
    private Memory memory;
    private StorageDevice storageDevice;
    private Monitor monitor;
    private Keyboard keybord;



    public Computer(CPU cpu, Memory memory, StorageDevice storageDevice,
                    Monitor monitor, Keyboard keybord, String computerName,
                    ComputeVendor vendor) {
        this.cpu = cpu;
        this.memory = memory;
        this.storageDevice = storageDevice;
        this.monitor = monitor;
        this.keybord = keybord;
        this.computerName = computerName;
        this.vendor = vendor;
    }

    public CPU getCpu() {
        return cpu;
    }

    public Memory getMemory() {
        return memory;
    }

    public StorageDevice getStorageDevice() {
        return storageDevice;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Keyboard getKeybord() {
        return keybord;
    }

    public String getComputerName() {
        return computerName;
    }

    public ComputeVendor getVendor() {
        return vendor;
    }

    public double getWeightComputer() {
        return cpu.getWeight() + memory.getWeight() + storageDevice.getWeight() +
                monitor.getWeight() + keybord.getWeight();
    }

    public CPU setCPU(int frequency, int coreNumber, CPUManufacturer manufacturer, double weight) {
        return new CPU(frequency, coreNumber,manufacturer, weight);
    }

    public Memory setMemory(String type, int volume, double weight) {
        return new Memory(type,volume,weight);
    }

    public StorageDevice setStorageDevice(StorageDeviceType type, int storageVolume, double weight) {
        return new StorageDevice(type, storageVolume, weight);
    }

    public Monitor setMonitor(int screenDiagonal, MonitorMatrixType type, double weight) {
        return new Monitor(screenDiagonal,MonitorMatrixType.TN,weight);
    }

    public Keyboard setKeyboard(KeyboardType type, KeyboardBackLight backLight, double weight) {
        return new Keyboard(type, backLight, weight);
    }

    @Override
    public String toString() {
        return  "Vendor = " + vendor + "\n" +
                "Computer Name = '" + computerName + '\'' + "\n" +
                cpu + "\n" +
                memory + "\n" +
                storageDevice + "\n" +
                monitor + "\n" +
                keybord + "\n" +
                "Computer mass = " + getWeightComputer() + " kg";
    }
}

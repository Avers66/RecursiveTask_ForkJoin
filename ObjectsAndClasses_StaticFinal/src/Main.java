public class Main {
    public static void main(String[] args) {
        CPU cpu1 = new CPU(2200, 8, CPUManufacturer.AMD, 0.02);
        Memory memory1 = new Memory("DDR", 8, 0.025);
        StorageDevice storageDevice1 = new StorageDevice(StorageDeviceType.HDD, 2000, 0.5);
        Monitor monitor1 = new Monitor(21, MonitorMatrixType.IPS, 3.2);
        Keyboard keyboard1 = new Keyboard(KeyboardType.MECHANICAL, KeyboardBackLight.WITH_BACKLIGHT, 0.3);
        Computer computer1 = new Computer(cpu1, memory1, storageDevice1, monitor1, keyboard1, "Tower-325", ComputeVendor.COMPAQ);

        CPU cpu2 = new CPU(2800, 8, CPUManufacturer.INTEL, 0.02);
        Memory memory2 = new Memory("DDR", 4, 0.025);
        StorageDevice storageDevice2 = new StorageDevice(StorageDeviceType.SSD, 1000, 0.2);
        Monitor monitor2 = new Monitor(24, MonitorMatrixType.TN, 3.7);
        Keyboard keyboard2 = new Keyboard(KeyboardType.MEMBRANE, KeyboardBackLight.WITHOUT_BACKLIGHT, 0.25);
        Computer computer2 = new Computer(cpu2, memory2, storageDevice2, monitor2, keyboard2, "MT-1000", ComputeVendor.ACER);

        System.out.println("Computer 1" + "\n" + computer1 + "\n");
        System.out.println("Computer 2" + "\n" + computer2 + "\n");


    }
}

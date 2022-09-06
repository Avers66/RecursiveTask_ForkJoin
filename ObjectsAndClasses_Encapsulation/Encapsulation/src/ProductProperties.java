public class ProductProperties {
    private final Dimensions dimension;
    private final double weight;
    private final String deliveryAddress;
    private final boolean canBeTurnedOver;
    private final String regNumber;
    private final boolean fragile;

    public ProductProperties(Dimensions dimension, double weight,
                             String deliveryAdress, boolean canBeTurnedOver,
                             String regNumber, boolean fragile) {
        this.dimension = dimension;
        this.weight = weight;
        this.deliveryAddress = deliveryAdress;
        this.canBeTurnedOver = canBeTurnedOver;
        this.regNumber = regNumber;
        this.fragile = fragile;
    }

    public Dimensions getDimension() {
        return dimension;
    }

    public double getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isCanBeTurnedOver() {
        return canBeTurnedOver;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public boolean isFragile() {
        return fragile;
    }

    public String toString() {
        return (dimension.toString() + "\n" +
                           "Вес: " + weight + " кг" + "\n" +
                           "Адрес доставки: " + deliveryAddress + "\n" +
                           "Можно переворачивать: " + (canBeTurnedOver ? "Да" : "Нет") + "\n" +
                           "Регистрационный номер: " + regNumber + "\n" +
                           "Хрупкий: " + (fragile ? "Да" : "Нет"));
    }

    public ProductProperties setDimensions(int length, int width, int height) {
        Dimensions dimensions = new Dimensions(length, width, height);
        return new ProductProperties(dimensions, weight, deliveryAddress, canBeTurnedOver, regNumber, fragile);
    }

    public ProductProperties setWeight(double weight) {
        return new ProductProperties(dimension, weight, deliveryAddress, canBeTurnedOver, regNumber, fragile);
    }

    public ProductProperties setDeliveryAddress(String deliveryAddress) {
        return new ProductProperties(dimension, weight, deliveryAddress, canBeTurnedOver, regNumber, fragile);
    }


}

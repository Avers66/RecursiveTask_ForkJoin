import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Elevator elevator = new Elevator(-3, 26);
        while (true) {
            System.out.println("Введите номер этажа или 100 для выхода: ");
            int floor = new Scanner(System.in).nextInt();
            if (floor == 100) {break;}
            elevator.move(floor);
        }
        Dimensions dimensions = new Dimensions(1500, 500, 250);
        ProductProperties productProperties = new ProductProperties(dimensions, 25.3,
                                                        "Baker street, 221",
                                                      false,
                                                           "A4678DF", true);

        ProductProperties productProperties1 = productProperties.setDeliveryAddress("3я улица строителей, 25");
        ProductProperties productProperties2 = productProperties.setDimensions(1700, 700, 500);
        ProductProperties productProperties3 = productProperties.setWeight(37.6);

        System.out.println("\n" + "Объект с новым адресом" + "\n" +  productProperties1);
        System.out.println("\n" + "Объект с новыми габаритами" + "\n" + productProperties2);
        System.out.println("\n" + "Объект с новой массой" + "\n" + productProperties3);
        System.out.println("\n" + "Исходный объект" + "\n" + productProperties);

    }
}

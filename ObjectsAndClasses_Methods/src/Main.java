public class Main {

    public static void main(String[] args) {
        Basket basket1 = new Basket();
        basket1.add("Milk", 40);
        basket1.add("Butter", 120);
        basket1.add("Bread", 30);

        Basket basket2 = new Basket();
        basket2.add("Oil", 110);
        basket2.add("Cake", 20, 4);
        basket2.add("Nuts", 160);

        basket1.print("Basket 1");
        basket2.print("Basket 2");

        System.out.println("All basket total price: " + Basket.getAllBasketPrice());
        System.out.println("All basket products counts: " + Basket.getAllBasketProductCount());
        System.out.println("Products average price: " + Basket.productAveragePrice());
        System.out.println("Basket average price: " + Basket.basketAveragePrice());
    }
}

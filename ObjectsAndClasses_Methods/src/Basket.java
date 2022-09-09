public class Basket {

    private static int count = 0;
    private static int allBasketProductCount = 0;
    private static int allBasketPrice = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight=0;

    public Basket() {
        increaseCount(1);
        items = "Products list:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public static void increaseAllBasketProductCount(int count) {
        Basket.allBasketProductCount = Basket.allBasketProductCount + count;
    }
    public static void increaseAllBasketPrice(int totalPrice) {
        Basket.allBasketPrice = Basket.allBasketPrice + totalPrice;
    }

    public static double productAveragePrice() {
        return (double) allBasketPrice / allBasketProductCount;
    }

    public static double basketAveragePrice() {
        return (double) allBasketPrice / count;
    }


    public static int getAllBasketProductCount() {
        return allBasketProductCount;
    }

    public static int getAllBasketPrice() {
       return allBasketPrice;
    }



    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        error = contains(name);
        if (totalPrice + count * price >= limit) {
            error = true;
        }
        if (error) {
            System.out.println("Error occured :(");
            return;
        }
        items = items + "\n" + name + " - " + count + " шт. - " + price;
        totalPrice = totalPrice + count * price;
        increaseAllBasketPrice(count * price);
        increaseAllBasketProductCount(count);
    }
    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        totalWeight = totalWeight + weight;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0;
    }
    public double getTotalWeight() {return totalWeight;}

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items + "\n");
        }
    }
}

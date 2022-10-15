import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {


    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4 ) { throw new ArrayIndexOutOfBoundsException(); }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if (!components[INDEX_EMAIL].matches("[a-z]+@{1}[a-z]+\\.{1}[a-z]+")) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (!components[INDEX_PHONE].matches("\\+7[0-9]{10}")) {
            throw new ArrayIndexOutOfBoundsException();
        }
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));

    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
    int acccountNumber = 100;
    List<String> fromAccount = new ArrayList<>();
    List<String> toAccount = new ArrayList<>();
    Random random = new Random();
    Bank bank = new Bank(acccountNumber);
    for (int i = 1000; i < 1000 + acccountNumber; i++) {
        fromAccount.add(String.valueOf(i));
        toAccount.add(String.valueOf(i));
    }
    Collections.shuffle(toAccount);
    System.out.println("Общая сумма до: " + bank.getSumAllAccounts());

        for (int i = 0; i < acccountNumber; i++) {
            String from = fromAccount.get(i);
            String to = toAccount.get(i);
            long sum = random.nextLong(52500L);
            new Thread(() -> bank.transfer(from, to, sum)).start();
            //System.out.println(fromAccount.get(i) + " " + toAccount.get(i) );
        }
        try {
            Thread.sleep(1000 * acccountNumber/10);
        }catch (Exception ex) {ex.printStackTrace();}
        System.out.println("Общая сумма после: " + bank.getSumAllAccounts());

    }
}

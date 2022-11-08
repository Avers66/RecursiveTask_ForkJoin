import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BankTest extends TestCase{
    Bank bank;
    int accountNumber = 100;
    List<String> fromAccount = new ArrayList<>();
    List<String> toAccount = new ArrayList<>();
    Random random = new Random();

    @Override
    public void setUp() throws Exception {
        bank = new Bank(accountNumber);
        for (int i = 1000; i < 1000 + accountNumber; i++) {
            fromAccount.add(String.valueOf(i));
            toAccount.add(String.valueOf(i));
        }
        Collections.shuffle(toAccount);
    }

    public void testTransfer() {
        Long expected = bank.getSumAllAccounts();
        for (int i = 0; i < accountNumber; i++) {
            String from = fromAccount.get(i);
            String to = toAccount.get(i);
            long sum = random.nextLong(52500L);
            new Thread(() -> bank.transfer(from, to, sum)).start();
        }
        try {
            Thread.sleep(1000 * accountNumber/10);
        }catch (Exception ex) {ex.printStackTrace();}
        Long actual = bank.getSumAllAccounts();
        assertEquals(expected, actual);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}

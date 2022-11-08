import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();
    private int accountNumber;

    public Bank(int accountNumber) {
        this.accountNumber = accountNumber;
        accounts = new HashMap<>();
        for (int i = 1000; i < 1000 + accountNumber; i++) {
            Account account = new Account(String.valueOf(i), random.nextLong(1000000L));
            accounts.put(account.getAccNumber(), account);
        }
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (fromAccountNum.equals(toAccountNum)) return;
        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)){
                    accounts.get(fromAccountNum).setBlocked(true);
                    accounts.get(toAccountNum).setBlocked(true);
                    return;
                }
            }catch (Exception ex) {ex.printStackTrace();}
        }
        long sum = amount;
        if (accounts.get(fromAccountNum).getMoney() <= amount)  sum =  accounts.get(fromAccountNum).getMoney();
        if (!accounts.get(fromAccountNum).isBlocked() && !accounts.get(toAccountNum).isBlocked()) {
            accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - sum);
            accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + sum);
        }

    }

    public synchronized long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long sumAllAccounts = 0;
        for (String acc : accounts.keySet()) {
            sumAllAccounts += accounts.get(acc).getMoney();
        }
        return sumAllAccounts;
    }
}

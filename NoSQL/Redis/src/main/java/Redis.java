import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.Random;


public class Redis {
    static private RedissonClient redisson;

    static private RScoredSortedSet<String> listUsers;

    static private final  String KEY = "users";

    static private int count =0;

    public static void main(String[] args) {

        init();
        createUsers();
        while (true) {
            String headUser = listUsers.pollFirst();
            System.out.println("- На главной странице показываем пользователя " + headUser);
            listUsers.add(getTs(), headUser);
            count++;
            pause(2000);
            if (count >= 10) {
                String payUser = "user" + new Random().nextInt(20);
                System.out.println("> " + payUser + " оплатил платную услугу");
                listUsers.add(getTs(), payUser);
                count = 0;
            }
        }

    }

    static private double getTs() {
        return new Date().getTime() / 1000;
    }

    static void createUsers() {
        for (int i = 1; i <= 20; i++ ) {
            String user = "user" + i;
            listUsers.add(getTs(), user);
            System.out.println("create user: " + user);
            pause(1000);
        }
    }

    static void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }
        listUsers = redisson.getScoredSortedSet(KEY);
    }

     static void pause(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//          void shutdown() {redisson.shutdown();}
}

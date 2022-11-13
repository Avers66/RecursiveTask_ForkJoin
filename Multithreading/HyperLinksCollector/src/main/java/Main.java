import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
public static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        String rootSite = "https://skillbox.ru/";
        //String rootSite = "https://lenta.ru/";
        int level = 0; // уровень рекурсии
        String root = rootSite; // домен
        set.add(rootSite); //запись оригинальных ссылок
        long start = System.currentTimeMillis();
        HyperLinksCollector collector = new HyperLinksCollector(rootSite, level, root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(collector);

        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!collector.isDone());

       // pool.shutdown();


        List<String> results;
        results = collector.join();

        System.out.println(results);
        System.out.println("Количество страниц " + results.size());

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");

        Path path = Paths.get("data/PageOfSite.txt");

        try{
            Files.write(path, results, Charset.defaultCharset());
        }catch (IOException ex) { ex.printStackTrace();}
    }
}

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
    public static ForkJoinPool pool;
    public static void main(String[] args) throws IOException {
        String rootSite = "https://skillbox.ru/";
        //String rootSite = "https://lenta.ru/";
        int level = 0; // recursion level
        String domain = rootSite; // Domain to scan
        set.add(rootSite); //repository of original links
        long start = System.currentTimeMillis();
        HyperLinksCollector collector = new HyperLinksCollector(rootSite, level, domain);
        pool = new ForkJoinPool();
        pool.execute(collector);
        while (!collector.isDone()) {showParametersForkJoinPool();}
        pool.shutdown();
        List<String> results;
        results = collector.join();
        System.out.println(results);
        System.out.println("Количество страниц " + results.size());
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
        String strSite = rootSite.replaceAll("https?:|/+", "");
        Path path = Paths.get("data/MapOfSite-" + strSite + ".txt");
        try{
            Files.write(path, results, Charset.defaultCharset());
        }catch (IOException ex) { ex.printStackTrace();}
    }

    public static void showParametersForkJoinPool() {
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
    }
}

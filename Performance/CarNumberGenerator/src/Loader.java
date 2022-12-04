import java.io.FileOutputStream;

public class Loader {

    public static void main(String[] args) throws Exception {
        int threadsCount = Runtime.getRuntime().availableProcessors();
        int regionCount = 99;
        int regionsPerThread = regionCount/threadsCount;
        long start = 0;
        FileOutputStream writer;

        for (int i = 0; i < threadsCount; i++) {
            int regionStart = i * regionsPerThread + 1;
            int regionEnd = (i + 1) * regionsPerThread;
            if (regionCount - regionEnd < regionsPerThread) {regionEnd = regionCount;}
            String fileName = "res/numbers" + String.valueOf(i + 1) + ".txt";
            System.out.println(regionStart + " - " + regionEnd + " - " + fileName);
            writer = new FileOutputStream(fileName);
            start = System.currentTimeMillis();
            new Thread(new Generator(regionStart, regionEnd, writer, start)).start();
        }
    }


}

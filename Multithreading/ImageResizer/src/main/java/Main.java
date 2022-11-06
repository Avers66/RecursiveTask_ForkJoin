import java.io.File;

public class Main {

    public static void main(String[] args) {
        String srcFolder = "C:/Multimedia/Photo/Skill/src";
        String dstFolder = "C:/Multimedia/Photo/Skill/dst";
        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        int threads = Runtime.getRuntime().availableProcessors();
        File[] files = srcDir.listFiles();
        int setNumber =  files.length/threads;
        for (int i = 0; i < files.length; i += setNumber) {
            if (files.length - i - setNumber > 0 && files.length - i - setNumber < threads) {
                setNumber = files.length - i;
            }
            File[] fileSet = new File[setNumber];
            System.arraycopy(files, i, fileSet, 0, setNumber);
            new Thread(new ImageResizer(fileSet, dstFolder, start)).start();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}

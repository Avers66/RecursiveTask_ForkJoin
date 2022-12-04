import java.io.FileOutputStream;
import java.io.IOException;

public class Generator implements Runnable{

    long start;
    int regionStart;
    int regionEnd;
    FileOutputStream writer;

    public Generator( int regionStart, int regionEnd, FileOutputStream writer,long start) {
        this.start = start;
        this.regionStart = regionStart;
        this.regionEnd = regionEnd;
        this.writer = writer;
    }

    @Override
    public void run()  {
        int regionCode;
        StringBuilder builder = new StringBuilder();
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (regionCode = regionStart; regionCode <= regionEnd; regionCode++) {
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            String carNumber = firstLetter + Generator.padNumber(number, 3) +
                                    secondLetter + thirdLetter + Generator.padNumber(regionCode, 2);
                            builder.append(carNumber);
                            builder.append("\n");
                        }
                    }
                }
                try {
                    writer.write(builder.toString().getBytes());
                } catch (IOException e) { e.printStackTrace();}
                builder = new StringBuilder();
            }
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {e.printStackTrace();}

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    public static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}

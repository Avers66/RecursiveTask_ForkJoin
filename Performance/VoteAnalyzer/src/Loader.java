import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
public class Loader {
    public static void main(String[] args) throws Exception {
        String fileName = "res/data-1572M.xml";
        SAXParserFactory factory = SAXParserFactory.newInstance(); //XML parser
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        long start = System.currentTimeMillis();
        parser.parse(new File(fileName), handler);
        if (handler.getRecordsCount() > 0) {DBConnection.executeMultiInsert();}
        System.out.println((System.currentTimeMillis() - start) + " ms");
        DBConnection.printVoterCounts();
    }
}
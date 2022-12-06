import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

    private String name = "";
    private String birthDay = "";
    int recordsCount = 0;
    int maxRecordsPacket = 100000;

    public XMLHandler() {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                birthDay = attributes.getValue("birthDay");
                name = attributes.getValue("name");
            }
            if (qName.equals("visit") && !name.isEmpty()) {
                DBConnection.countVoter(name, birthDay);
                recordsCount++;
                if (recordsCount == maxRecordsPacket) {
                    DBConnection.executeMultiInsert();
                    recordsCount = 0;
                };
            }
        }catch (SQLException se) {se.printStackTrace();}
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("voter")) {
                name = "";
            }
    }

    public int getRecordsCount() {
        return recordsCount;
    }
}

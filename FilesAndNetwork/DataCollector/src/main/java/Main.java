import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static List<File> dataFiles; //Список найденных json/csv файлов
    static Map<String, List> metro; //Схема московского метро - линии и станции
    static List<Map> lines; //Список номеров линий метро и их названий
    static List<String> connections; //Список станций с переходами
    static List<Map> stations; //Результирующий список станций с найденными характеристиками
    static Map<String,String> statDate; //Список станций, имеющих даты создания
    static Map<String,Integer> statDepth;// Список станций с глубинами

    public static void main(String[] args) throws ParseException {
        String mskMetroURL = "https://skillbox-java.github.io";
        File searchProp = new File("src/main/resources/data/");
        dataFiles = new ArrayList<>();
        metro = new HashMap<>();
        lines = new ArrayList<>();
        connections = new ArrayList<>();
        statDate = new TreeMap<>();
        statDepth = new TreeMap<>();
        stations = new ArrayList<>();
        getAllDirFiles(searchProp); //получение списка файлов json/csv с данными
        getJsonProp(); //получение характеристик станций из файлов json
        getCSVProp(); //получение характеристик станций из файлов csv
        parseHTML(mskMetroURL); //парсинг HTML по заданному URL, получение схемы метро и списка переходов
        // создание результирующего списка станций с их характеристиками
        for (String ln : metro.keySet()) {
            List<String> st = metro.get(ln);
            for (String s : st) {
                Map m = new HashMap();
                m.put("name", s);
                m.put("line", ln);
                if (connections.contains(s)) {
                    Boolean status = true;
                    m.put("hasConnection", status);
                }
                for (String stName :statDate.keySet()) {
                    if (s.equals(stName)) m.put("date", statDate.get(stName));
                }
                for (String stName :statDepth.keySet()) {
                    if (s.equals(stName)) m.put("depth", statDepth.get(stName));
                }
            stations.add(m);
            }
        }
        //вывод накопленной информации в json файлы
        JSONObject jsonData = new JSONObject();
        jsonData.put("stations", metro);
        jsonData.put("lines", lines);
        String jsonString = jsonData.toJSONString();
        try {
            PrintWriter write = new PrintWriter("data/map.json");
            write.print(jsonData);
            write.flush();
            write.close();
        } catch (FileNotFoundException ex) {ex.printStackTrace();}
        //System.out.println(jsonString);
        JSONObject jsonData2 = new JSONObject();
        jsonData2.put("stations", stations);
        String jsonString2 = jsonData2.toJSONString();
        try {
            PrintWriter write = new PrintWriter("data/stations.json");
            write.print(jsonData2);
            write.flush();
            write.close();
        } catch (FileNotFoundException ex) {ex.printStackTrace();}

        printJson();
    }

    public static void getAllDirFiles(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().matches(".+\\.(json|csv)")) dataFiles.add(f);}
            else getAllDirFiles(f);
        }
        return;
    }
    public static void parseHTML(String URL){
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException ex) {ex.printStackTrace();}
        Elements stations = doc.select(".js-metro-stations");
        stations.forEach(e -> {
            String s = e.text().replaceAll("[^а-яА-Я0-9ё\\-\\.\\s]+", "");
            s = s.substring(2);
            metro.put(e.attr("data-line"), Arrays.asList(s.split("\\s?[0-9]+\\.")));
        });

        Elements line = doc.select(".js-metro-line");
        line.forEach(l -> {
            Map<String, String> map = new HashMap<>();
            map.put("number", l.attr("data-line"));
            map.put("name", l.text());
            lines.add(map);
        });

        Elements connection = doc.select(".t-icon-metroln[title]");
        connection.forEach(c -> {
            String conn = c.attr("title");
            conn = conn.substring(conn.indexOf("«") + 1, conn.lastIndexOf("»"));
            connections.add(conn);
        });
    }
    public static void getJsonProp(){
        for (File f : dataFiles){
            if (f.getName().matches(".+\\.json")){
                StringBuilder builder = new StringBuilder();
                try {
                    List<String> lines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
                    lines.forEach(builder::append);
                } catch (IOException ex) {ex.printStackTrace();}
                JSONParser parser = new JSONParser();
                Object jsonData = null;
                try {
                    jsonData =  parser.parse(builder.toString());
                } catch (ParseException pe) {pe.printStackTrace();}
                    JSONArray mapDepth = (JSONArray) jsonData;
                    for (Object o : mapDepth) {
                        JSONObject propJsonObject = (JSONObject) o;
                        if (propJsonObject.containsKey("name") && propJsonObject.containsKey("date")) {
                            String n = (String) propJsonObject.get("name");
                            String d = (String) propJsonObject.get("date");
//                          Коллекционирование нескольких дат постройки для одной станции
//                          if (statDate.containsKey(n)){
//                              if (!statDate.get(n).equals(d)) d = statDate.get(n) + ", " + d;
//                          }
                            statDate.put(n, d);
                        }
                        if (propJsonObject.containsKey("name") && propJsonObject.containsKey("depth")) {
                            String n = (String) propJsonObject.get("name");
                            String d =  propJsonObject.get("depth").toString();
                            d = d.replaceAll("[^0-9]+", "");
                            if (!d.isEmpty())statDepth.put(n, Integer.parseInt(d));
                        }
                        if (propJsonObject.containsKey("station_name") && propJsonObject.containsKey("depth_meters")) {
                            String n = (String) propJsonObject.get("station_name");
                            String d =  propJsonObject.get("depth_meters").toString();
                            d = d.replaceAll("[^0-9]+", "");
                            if (!d.isEmpty())statDepth.put(n, Integer.parseInt(d));
                        }
                    }
            }

        }
    }

    public static void getCSVProp(){
        for (File f : dataFiles) {
            if (f.getName().matches(".+\\.csv")) {
                List<String> lines = new ArrayList<>();
                try {
                    lines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
                } catch (IOException ex) { ex.printStackTrace();}
                String[] column = lines.get(0).split(",");
                if (column[0].equals("Название станции") && column[1].equals("Дата открытия")) {
                    for (int i = 1; i< lines.size(); i++){
                        String[] entry = lines.get(i).split(",");
//                      Коллекционирование нескольких дат постройки для одной станции
//                        if (statDate.containsKey(entry[0])) {
//                            if (!statDate.get(entry[0]).equals(entry[1])) entry[1] = statDate.get(entry[0]) + ", " + entry[1];
//                        }
                        statDate.put(entry[0], entry[1]);
                    }
                }
                if (column[0].equals("Название") && column[1].equals("Глубина")) {
                    for (int i = 1; i< lines.size(); i++){
                        String[] entry = lines.get(i).split(",");
                        entry[1] = entry[1].replaceAll("[^0-9]+", "");
                        if (!entry[1].isEmpty()) statDepth.put(entry[0], Integer.parseInt(entry[1]));
                    }
                }
            }
        }
    }

    public static void printJson() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("data/map.json"));
            lines.forEach(builder::append);
        } catch (IOException ex) {ex.printStackTrace();}
        JSONParser parser = new JSONParser();
        JSONObject jsonData = null;
        try {
            jsonData =  (JSONObject) parser.parse(builder.toString());
        } catch (ParseException pe) {pe.printStackTrace();}
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        for (Object ln : stationsObject.keySet()) {
            JSONArray stationsArray = (JSONArray) stationsObject.get(ln);
            System.out.println(ln + " line - " + stationsArray.size() + " stations");
        }
    }
}

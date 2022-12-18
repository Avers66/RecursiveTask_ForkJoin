import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


public class HyperLinksCollector extends RecursiveTask<List<String>> {

    private String hyperLink;
    private int level;
    private int recursionLevel = 200;
    private int pageNumberLimit = 1000;

    private Connection session;


    public HyperLinksCollector(String hyperLink, int level, Connection session) {
        this.hyperLink = hyperLink;
        this.level = level;
        this.session = session;

    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        list.add("\t".repeat(level) + hyperLink + "\n");
        if (level >= recursionLevel) return list;
        if (Main.set.size() > pageNumberLimit) return list;
        List<HyperLinksCollector> tasksList = new ArrayList<>();

//ToDo найти гиперссылки
        Document doc = null;
        String responseMessage = "";
        String responseBody = "";
        int responseCode = 0;
        try {
            Connection connection = session.newRequest().url(hyperLink);
            Connection.Method get = Connection.Method.GET;
            connection.method(get).execute();
            //doc = connection.get();
            responseBody = connection.response().body();
            responseMessage = connection.response().statusMessage();
            responseCode = connection.response().statusCode();
            doc = Jsoup.parse(responseBody);


            //IOException, SocketTimeoutException,UnsupportedMimeTypeException,
            //HttpStatusException, MalformedURLException
        } catch (Exception ex) {
            responseMessage = ex.getMessage();
            System.out.println(responseMessage + " Страница недоступна " + hyperLink); return list;
        }
        Elements links = doc.select("a");
        for (Element e : links) {
            String hyperLinkChild = e.attr("href");
            if (hyperLinkChild.matches("/.+[^#](/||.html)")){
                  hyperLinkChild = Main.domain + hyperLinkChild.substring(1);

            }
            boolean term = hyperLinkChild.endsWith("/") || hyperLinkChild.endsWith(".html");
            if (hyperLinkChild.indexOf(Main.domain) >= 0 && term) {
                if (!Main.set.add(hyperLinkChild)) continue;
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ex) { ex.printStackTrace();}
                if (Main.set.size() == 500) System.out.println(responseBody);
                System.out.println(hyperLinkChild + " "
                        + responseCode + " "
                        + responseMessage + " уровень_"
                        + level + " "
                        + Main.set.size() + "_стр. "
                        + responseBody.length() + "_байт"); //debugging
                HyperLinksCollector task = new HyperLinksCollector(hyperLinkChild, level + 1, session);
                task.fork();
                tasksList.add(task);
            }
        }
        for (HyperLinksCollector item : tasksList) {
            list.addAll(item.join());
        }
        return list; //list of links on the current page
    }
}


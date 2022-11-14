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
    private String root; // Domain to scan
    private int recurseDepth = 40; //recursion depth limiter

    public HyperLinksCollector(String hyperLink, int level, String root) {
        this.hyperLink = hyperLink;
        this.level = level;
        this.root = root;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        List<HyperLinksCollector> tasks = new ArrayList<>();
        list.add("\t".repeat(level) + hyperLink + "\n");
//ToDo найти гиперссылки
        Document doc = null;
        try {
            doc = Jsoup.connect(hyperLink).timeout(3000).get();
        } catch (IOException ex) {
            System.out.println("Страница недоступна " + hyperLink); return list;} // the page is unavailable
        Elements links = doc.select("a");
        for (Element e : links) {
            String hyperLinkChild = e.attr("href");
            //if (level > recurseDepth) break; //recursion depth limiter
            if (hyperLinkChild.indexOf(root) >= 0 && hyperLinkChild.endsWith("/")) { //hyperlink is a child
                if (!Main.set.add(hyperLinkChild)) continue;// skip it if the link has already been saved earlier
                try {
                    Thread.sleep(150); //delay before opening a hyperlink
                } catch (InterruptedException ex) { ex.printStackTrace();}
                HyperLinksCollector task = new HyperLinksCollector(hyperLinkChild, level + 1, root);
                task.fork();
                tasks.add(task);
                //System.out.println(hyperLinkChild + " " + level); //debugging
            }
        }
        for (HyperLinksCollector item : tasks) {
            list.addAll(item.join());
        }
        return list; //list of links on the current page
    }
}


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
    private String root; // Домен для сканирования
    private int recurseDepth = 40; //ограничитель глубины рекурсии

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
            System.out.println("Страница недоступна " + hyperLink); return list;} // страница недоступна
        Elements links = doc.select("a");
        for (Element e : links) {
            String hyperLinkChild = e.attr("href");
            //if (level > recurseDepth) break; //ограничение глубины рекурсии
            if (hyperLinkChild.indexOf(root) >= 0 && hyperLinkChild.endsWith("/")) { //гиперссылка  является дочерней
                if (Main.set.contains(hyperLinkChild)) continue; //ссылка уже была сохранена ранее
                Main.set.add(hyperLinkChild);
                try {
                    Thread.sleep(150); //задержка перед открытием гиперссылки
                } catch (InterruptedException ex) { ex.printStackTrace();}
                HyperLinksCollector task = new HyperLinksCollector(hyperLinkChild, level + 1, root);
                task.fork();
                tasks.add(task);
                //System.out.println(hyperLinkChild + " " + level); //отладка
            }
        }
        for (HyperLinksCollector item : tasks) {
            list.addAll(item.join());
        }
        return list; //список ссылок на текущей странице
    }
}


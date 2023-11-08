import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

    public static void main(String[] args) {
        String seed = "https://yssupon.github.io/";
        ArrayList<String> visited = new ArrayList<>();
        crawl(seed, 2,visited);
    }

    public static void crawl(String seed, int count,List<String> visited) {
        if (count != 0) {
            Document doc = request(seed, visited);
            if (doc == null) {
                return;
            }
            for (Element link : doc.select("a[href]")) {

                String nextLink = link.absUrl("href");
                crawl( nextLink, count-1,visited);
            }
        }
    }
    private static Document request(String url, List<String> visited) {
        try {
            Document doc = Jsoup.connect(url).get();
            System.out.println("Title: " + doc.title());
            System.out.println("URL: " + url);
            visited.add(url);
            return doc;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}

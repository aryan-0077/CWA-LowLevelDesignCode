import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class WebCrawler {
    // Store the hostname from startUrl to filter URLs
    private String hostName;

    // Thread-safe map to store visited URLs and avoid duplicates
    private ConcurrentHashMap<String, Boolean> urlHashMap = new ConcurrentHashMap<>();

    // Thread pool to process URLs concurrently
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    // Counter to track URLs to be processed
    private AtomicInteger numOfUrlsToParse = new AtomicInteger(0);

    // Reference to the HtmlParser
    private HtmlParser htmlParser;

    // Worker Task that processes URLs in separate threads
    class Task implements Runnable {
        private String url;

        Task(String url) {
            this.url = url;
        }

        public void run() {
            // Get all URLs from the current page
            for (String extractedUrl : htmlParser.getUrls(url)) {
                // Extract hostname from URL
                String curHostName = extractedUrl.split("/")[2];
                // Check if URL has same hostname and hasn't been visited
                if (curHostName.equals(hostName) && urlHashMap.putIfAbsent(extractedUrl, true) == null) {
                    // Increment counter for active tasks
                    numOfUrlsToParse.addAndGet(1);
                    // Submit new task to process this URL
                    executor.submit(new Task(extractedUrl));
                }
            }
            // Decrement counter when task is complete
            numOfUrlsToParse.addAndGet(-1);
        }
    }

    // startUrl = "http://news.yahoo.com/news/topics/"
    public List crawl(String startUrl, HtmlParser htmlParser) {
        // Extract hostname from startUrl
        // startUrl Split Array = ["http:", "", "news.yahoo.com", "news", "topics", ""]
        // hostname = "news.yahoo.com"
        hostName = startUrl.split("/")[2];
        this.htmlParser = htmlParser;

        // Mark startUrl as visited
        urlHashMap.put(startUrl, true);

        // Initialize counter and submit first task
        numOfUrlsToParse.addAndGet(1);
        executor.submit(new Task(startUrl));

        // Wait until all URLs have been processed
        while (numOfUrlsToParse.get() > 0) {
            try {
                // Sleep to avoid busy waiting
                Thread.sleep(80);
            } catch (Exception e) {
                // Handle exceptions
            }
        }

        // Shutdown the thread pool
        executor.shutdown();

        // Return all discovered URLs
        return new ArrayList<>(urlHashMap.keySet());
    }
}

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize the WebCrawler and MockHtmlParser
        WebCrawler crawler = new WebCrawler();
        HtmlParser htmlParser = new MockHtmlParser();

        // Start crawling from the given URL
        List<String> result = crawler.crawl("http://news.yahoo.com/news/topics/", htmlParser);

        // Print the discovered URLs
        System.out.println("\nDiscovered URLs:");
        for (String url : result) {
            System.out.println(url);
        }
    }
}

/*

Output :

pool-1-thread-1 fetching URLs from: http://news.yahoo.com/news/topics/
pool-1-thread-2 fetching URLs from: http://news.yahoo.com/news/topics/sports
pool-1-thread-3 fetching URLs from: http://news.yahoo.com/news/topics/technology
pool-1-thread-4 fetching URLs from: http://news.yahoo.com/news
pool-1-thread-1 fetching URLs from: http://news.yahoo.com/news/topics/sports/basketball
pool-1-thread-4 fetching URLs from: http://news.yahoo.com/news/politics
pool-1-thread-5 fetching URLs from: http://news.yahoo.com/news/topics/sports/football

Discovered URLs:
http://news.yahoo.com/news/topics/technology
http://news.yahoo.com/news/topics/
http://news.yahoo.com/news/topics/sports
http://news.yahoo.com/news/topics/sports/football
http://news.yahoo.com/news
http://news.yahoo.com/news/politics
http://news.yahoo.com/news/topics/sports/basketball

*/
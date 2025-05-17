import java.util.Arrays;
import java.util.List;

public class MockHtmlParser implements HtmlParser {
    @Override
    public List<String> getUrls(String url) {
        // Mock implementation for testing
        System.out.println(Thread.currentThread().getName() + " fetching URLs from: " + url);
        return switch (url) {
            case "http://news.yahoo.com/news/topics/" -> Arrays.asList(
                    "http://news.yahoo.com/news/topics/sports",
                    "http://news.yahoo.com/news/topics/technology",
                    "http://news.yahoo.com/news",
                    "http://news.yahoo.com/news/topics/"
            );
            case "http://news.yahoo.com/news" -> Arrays.asList(
                    "http://news.yahoo.com/news/topics/",
                    "http://news.yahoo.com/news/politics"
            );
            case "http://news.yahoo.com/news/topics/sports" -> List.of(
                    "http://news.yahoo.com/news/topics/sports/football",
                    "http://news.yahoo.com/news/topics/sports/basketball"
            );
            default -> List.of();
        };
    }
}


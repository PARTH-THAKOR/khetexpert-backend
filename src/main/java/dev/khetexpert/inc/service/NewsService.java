// NewsService Class

package dev.khetexpert.inc.service;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.clients.NewsClient;
import dev.khetexpert.inc.entity.News;
import dev.khetexpert.inc.entity.NewsContent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    private NewsClient newsClient;

    public List<NewsContent> getNews() {
        News news = newsClient.getNews();
        List<NewsContent> results = news.getResults();
        List<NewsContent> newsContents = new ArrayList<>(results);
        String page = news.getNextPage();
        while (true) {
            if (page == null) {
                break;
            } else {
                news = newsClient.getNewsPage(page);
                newsContents.addAll(news.getResults());
                page = news.getNextPage();
            }
        }
        return newsContents;
    }

}

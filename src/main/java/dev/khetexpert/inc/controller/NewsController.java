// NewsController Class

package dev.khetexpert.inc.controller;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.NewsContent;
import dev.khetexpert.inc.service.NewsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private NewsService newsService;

    @GetMapping("/get")
    @Cacheable(value = "newsCache-v7")
    public List<NewsContent> getNews() {
        return newsService.getNews();
    }

}

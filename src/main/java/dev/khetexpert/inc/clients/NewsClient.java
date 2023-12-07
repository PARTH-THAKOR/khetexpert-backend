// NewsAPIClient

package dev.khetexpert.inc.clients;

import dev.khetexpert.inc.constants.Constants;
import dev.khetexpert.inc.entity.News;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "news", url = Constants.NEWS_URL)
public interface NewsClient {

    @GetMapping
    News getNews();

    @GetMapping
    News getNewsPage(@RequestParam("page") String page);

}

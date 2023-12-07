// News Entity

package dev.khetexpert.inc.entity;

import lombok.Data;

import java.util.List;

@Data
public class News {

    public String status;
    public List<NewsContent> results;
    public String nextPage;

}

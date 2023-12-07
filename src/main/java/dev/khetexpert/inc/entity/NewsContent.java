// NewsContent Class

package dev.khetexpert.inc.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsContent implements Serializable {

    public String title;
    public String link;
    public String description;
    public String content;
    public String pubDate;
    public String image_url;

}

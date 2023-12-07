// ExpertBlog Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Blog {

    private String blogId;

    @NotEmpty(message = "Expert Name is null")
    private String expertName;
    private String expertImageUrl;

    @NotEmpty(message = "Expert Email is null")
    @Email(message = "Enter valid Email Address")
    private String expertEmail;
    private final String date = new Date().toString();

    @NotEmpty(message = "Blog title is null")
    private String title;

    @NotEmpty(message = "Blog content is null")
    private String content;
    private String externalLink;
    private String imageUrl;
    private int ratting;

}

// DoubtAnswers Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoubtAnswers {

    @NotEmpty(message = "expert Id is null")
    private String expertId;

    @NotEmpty(message = "expert name is null")
    private String expertName;
    private String expertImageUrl;

    @Email(message = "Enter valid Email")
    @NotEmpty(message = "expert email is null")
    private String expertEmailId;

    @NotEmpty(message = "solution is null")
    private String solution;
    private String externalLink;

}

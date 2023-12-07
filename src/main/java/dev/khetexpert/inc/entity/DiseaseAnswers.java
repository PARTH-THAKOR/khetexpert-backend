// DiseaseAnswers Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiseaseAnswers {

    @NotEmpty(message = "expert Id is null")
    private String expertId;

    @NotEmpty(message = "expert name is null")
    private String expertName;
    private String expertImageUrl;

    @NotEmpty(message = "expert phoneNumber is null")
    private String expertPhoneNumber;

    @Email(message = "Enter valid Email")
    @NotEmpty(message = "expert email is null")
    private String expertEmailId;

    @NotEmpty(message = "disease is null")
    private String disease;

    @NotEmpty(message = "description is null")
    private String description;

    @NotEmpty(message = "solution is null")
    private String solution;
    private String externalLink;

}

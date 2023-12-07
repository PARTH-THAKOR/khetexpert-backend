// Expert Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Expert {

    @Id
    private String expertId;

    @NotEmpty(message = "Expert Name is null")
    private String expertName;

    @NotEmpty(message = "Expert Name is null")
    private String expertMobileNumber;

    @NotEmpty(message = "Expert Name is null")
    @Email(message = "Enter valid Email Address")
    private String expertEmailId;
    private String stateName;

    @NotEmpty(message = "Expert Name is null")
    private String password;
    private String docUrl;
    private String imageUrl;
    private String docNumber;

}

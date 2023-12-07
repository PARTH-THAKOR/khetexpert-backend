// AppLoginEntity Class

package dev.khetexpert.inc.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AppLoginEntity {

    @Id
    private String id;

    @Email(message = "Enter valid Email-Id")
    @NotEmpty(message = "Email is Null")
    private String email;
    private String verificationCode;

}

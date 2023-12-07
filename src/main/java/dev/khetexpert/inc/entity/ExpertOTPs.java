// ExpertOTPs Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@Document
public class ExpertOTPs {

    @Id
    private String OTPId;

    @NotEmpty(message = "Email can't be null")
    @Email(message = "Enter valid Email Address")
    private String email;

    @NotEmpty(message = "password can't be null")
    @Size(min = 8, message = "OTP must be have 8 characters")
    private String OTP;

    @Indexed(name = "createdAtIndex", expireAfter = "150s")
    private Date createdAt = Date.from(Instant.now());

}

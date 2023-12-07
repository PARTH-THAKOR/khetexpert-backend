// Appointment Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Appointment {

    private String docId;
    private String expertId;

    @Email(message = "Enter valid Email-Id")
    @NotEmpty(message = "expertEmailId is Null")
    private String expertEmailId;

    @NotEmpty(message = "expertName is Null")
    private String expertName;
    private String expertImageUrl;

    @NotEmpty(message = "expertMobileNumber is Null")
    private String expertMobileNumber;

    @NotEmpty(message = "farmerName is Null")
    private String farmerName;

    @NotEmpty(message = "farmerMobileNumber is Null")
    private String farmerMobileNumber;
    private String farmerImgUrl;
    private boolean accepted;

    @NotEmpty(message = "reason is Null")
    private String reason;
    private String hour;
    private String minutes;
    private String ampm;
    private String message;
    private boolean rejected = false;

}

// FarmerDiseaseDetection Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class FarmerDiseaseDetection {

    private String docId;

    @NotEmpty(message = "PhoneNumber is null")
    private String phoneNumber;

    @NotEmpty(message = "farmerName is null")
    private String farmerName;
    private String farmerImgUrl;
    private String date = new Date().toString();

    @NotEmpty(message = "State is null")
    private String state;

    @NotEmpty(message = "Question is null")
    private String question;
    private List<String> imgUrls = new ArrayList<>();
    private List<DiseaseAnswers> diseaseAnswersList = new ArrayList<>();

}

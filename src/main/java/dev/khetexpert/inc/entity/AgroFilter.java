// AgroFilter Entity

package dev.khetexpert.inc.entity;

import lombok.Data;
import dev.khetexpert.inc.constants.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class AgroFilter {

    @Id
    private String filterId;
    private String filterAuthToken = Constants.Filter_AUTH_TOKEN;

    List<String> vegetables = new ArrayList<>();
    List<String> agriWords = new ArrayList<>();
    List<String> diseases = new ArrayList<>();
    List<String> fruits = new ArrayList<>();
    List<String> fertilizers = new ArrayList<>();

}
